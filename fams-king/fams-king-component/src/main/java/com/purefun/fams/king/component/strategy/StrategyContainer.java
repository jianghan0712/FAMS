/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.core.bo.tool.BoFactory;
import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.exception.FAMSException;
import com.purefun.fams.king.component.account.AccountContainer;
import com.purefun.fams.king.component.strategy.analysis.BacktestExecutorContainer;
import com.purefun.fams.king.component.strategy.analysis.DefaultTradeAnalysisContainer;
import com.purefun.fams.king.component.strategy.analysis.OrderExecutor;
import com.purefun.fams.king.component.strategy.config.StrategyConfig;
import com.purefun.fams.king.component.strategy.model.Account;
import com.purefun.fams.king.component.strategy.model.TradeDetail;
import com.purefun.fams.king.constant.DirectionEnum;
import com.purefun.fams.king.constant.EventTypeEnum;
import com.purefun.fams.king.constant.KingConstant;
import com.purefun.fams.king.constant.OrderStatusEnum;
import com.purefun.fams.md.MdBarDataBO;
import com.purefun.fams.trade.order.OrderBO;
import com.purefun.fams.trade.order.otw.OrderBO_OTW;

/**
 * @Classname: StrategyContainer
 * @Description: 策略容器，作为行情容器的观察者，同时作为被监听方，被上游监听
 * @author 015979
 * @date 2020-03-26 15:31:17
 */
public abstract class StrategyContainer implements Observer {
	private static final Logger logger = LogManager.getLogger(StrategyContainer.class);

	@Autowired
	StrategyConfig straConfig;

	/** 运行环境，是回测还是真实交易 */
	protected String runType;

	/** 委托、持仓、持仓处理机 */
	protected OrderExecutor orderEecutor;

	/** 账户管理容器 */
	protected AccountContainer accountContainer;

	/** 交易结果分析容器 */
	protected DefaultTradeAnalysisContainer analysisContainer;

	protected String acct;
	/** 当前已加载的行情数 */
	int currentCount = 0;

	@Override
	public void update(Observable o, Object arg) {
		Object[] param = (Object[]) arg;
		// 参数类型
		EventTypeEnum eventType = (EventTypeEnum) param[0];
		Object paramValue = param[1];

		if (eventType.equals(EventTypeEnum.EVENT_MD_BAR)) {
			onBar((MdBarDataBO) paramValue);
		} else if (eventType.equals(EventTypeEnum.EVENT_MD_END)) {
			onStop((MdBarDataBO) paramValue);
		}
	}

	/**
	 * 检查上一周期是否有触发交易信号，如果有，按照当前的行情进行委托
	 * 
	 * @MethodName: checkNewOrderSet
	 * @author 015979
	 * @date 2020-03-30 18:09:12
	 * @param barData
	 */
	protected void checkNewOrderSet(MdBarDataBO barData) throws FAMSException {
		Map<String, OrderBO_OTW> orderMap = orderEecutor.getNeworderMap();
		if (orderMap.isEmpty()) {
			return;
		}

		for (Map.Entry<String, OrderBO_OTW> each : orderMap.entrySet()) {
			OrderBO_OTW order = each.getValue();
			// 找到所有new状态的委托，进行发送
			if (!order.getOrderStatus().equalsIgnoreCase(OrderStatusEnum.NEW.getCode())) {
				continue;
			}
			BigDecimal cash = accountContainer.getAccount(acct).getAvailableCapital();

			BigDecimal price = new BigDecimal(barData.open).setScale(CommonUtil.ScaleUtil.CASH_SCALE,
					BigDecimal.ROUND_DOWN);
			String side = order.getDirection();
			order.setTradeDate(barData.date);
			order.setOrderPrice(barData.open);
			if (side.equalsIgnoreCase(DirectionEnum.BUY.getCode())) {
				// 如果是买入，现金减少，持仓增加
				BigDecimal position = cash.divide(price, 2, RoundingMode.DOWN).divide(new BigDecimal("100"), 0,
						RoundingMode.DOWN);
				long l = position.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_DOWN).longValue(); // 向下取整
				order.setOrderVolume(l);
				// 冻结资金
				BigDecimal freezeCash = new BigDecimal(l).multiply(price).setScale(2, BigDecimal.ROUND_FLOOR);
				accountContainer.freeze(acct, freezeCash);
				// 更新股份：在收到明确成交回报后才进行
			} else if (side.equalsIgnoreCase(DirectionEnum.SELL.getCode())) {
				// 卖出订单时，找到持仓，进行冻结操作
				List<TradeDetail> positionList = orderEecutor.getPositionMap().get(order.getSecurity_code());
				if (positionList == null || positionList.isEmpty()) {
					logger.error("账户没有持仓，无法进行卖出操作，stockcode={}", order.getSecurity_code());
					throw new FAMSException(ErrorCodeEnum.KING_ILLEGAL_TRADE);
				}
				// 计算可用数量，默认全部卖出
				long totalVolume = 0l;
				for (TradeDetail eachPostion : positionList) {
					long avaliablVolume = eachPostion.getVolume() - eachPostion.getFreezeVolume();
					if (avaliablVolume > 0) {
						totalVolume += avaliablVolume;
						eachPostion.setFreezeVolume(eachPostion.getFreezeVolume() + avaliablVolume);
					}
				}
				if (totalVolume <= 0) {
					logger.error("账户股份均已冻结，无法进行卖出操作");
					throw new FAMSException(ErrorCodeEnum.KING_ILLEGAL_TRADE);
				}
				// 设置交易量
				order.setOrderVolume(totalVolume);
				order.setOrderStatus(OrderStatusEnum.PENDING.getCode());
			}
			sendNewOrder(order);
		}
	}

	/**
	 * 当前周期出现了交易信号，等待下一周期开始时完成创建委托
	 * 
	 * @MethodName: signalNotify
	 * @author 015979
	 * @date 2020-03-30 18:06:32
	 * @param side
	 * @param securityCoe
	 */
	protected void signalNotify(DirectionEnum side, String securityCoe) {
		OrderBO_OTW order = (OrderBO_OTW) BoFactory.createBo(OrderBO.class, true);
		order.setSecurity_code(securityCoe);
		order.setDirection(side.getCode());
		order.setOrderId(order.getUuid());
		order.setOrderStatus(OrderStatusEnum.NEW.getCode());
		order.setAccount(acct);

		orderEecutor.getNeworderMap().put(order.getOrderId(), order);
	}

	/**
	 * 初始化策略容器
	 * 
	 * @MethodName: onInit
	 * @author 015979
	 * @date 2020-03-27 10:36:44
	 * @param initCount
	 */
	public void onInit() {
		runType = straConfig.getRunType();
		acct = straConfig.getAccount();
		initAccount(straConfig);
		if (straConfig.getRunType().equalsIgnoreCase(KingConstant.RunType.BACKTEST)) {
			analysisContainer = new DefaultTradeAnalysisContainer();
			orderEecutor = new BacktestExecutorContainer(straConfig, accountContainer, analysisContainer);
		}
	}

	/**
	 * 初始化资金账户
	 * 
	 * @MethodName: initAccount
	 * @author 015979
	 * @date 2020-03-31 22:03:16
	 * @param straConfig
	 */
	private void initAccount(StrategyConfig straConfig) {
		accountContainer = new AccountContainer();
		Account account = new Account();
		account.setAcct(straConfig.getAccount());
		account.setAvailableCapital(new BigDecimal(straConfig.cash).setScale(CommonUtil.ScaleUtil.CASH_SCALE));
		account.setTotalCapital(new BigDecimal(straConfig.cash).setScale(CommonUtil.ScaleUtil.CASH_SCALE));
		account.setFreezeCapital(BigDecimal.ZERO.setScale(CommonUtil.ScaleUtil.CASH_SCALE));

		accountContainer.addAccount(account);
	}

	/**
	 * 启动策略容器
	 * 
	 * @MethodName: onStart
	 * @author 015979
	 * @date 2020-03-27 10:37:14
	 */
	public void onStart() {

	}

	/**
	 * 停止策略容器
	 * 
	 * @MethodName: onStop
	 * @author 015979
	 * @date 2020-03-27 10:37:31
	 */
	public void onStop(MdBarDataBO lastMarketData) {
		orderEecutor.genTradingResult(lastMarketData);
		currentCount = 0;
	}

	/**
	 * 发送新订单到oms
	 * 
	 * @MethodName: sendNewOrder
	 * @author 015979
	 * @date 2020-03-27 10:33:10
	 */
	public void sendNewOrder(OrderBO_OTW order) {
		orderEecutor.handlerOrder(order);
	}

	/**
	 * 接收到bar型行情
	 * 
	 * @MethodName: onBar
	 * @author 015979
	 * @date 2020-03-27 10:32:42
	 * @param barData
	 */
	public abstract void onBar(MdBarDataBO barData);

	/**
	 * 接收到tick型行情
	 * 
	 * @MethodName: onTick
	 * @author 015979
	 * @date 2020-03-27 10:32:56
	 */
	public abstract void onTick();

	/**
	 * 发送取消订单到oms
	 * 
	 * @MethodName: sendCancelOrder
	 * @author 015979
	 * @date 2020-03-27 10:34:22
	 */
	public abstract void sendCancelOrder(OrderBO_OTW order);

	/**
	 * 收到oms publish来的新订单推送
	 * 
	 * @MethodName: onNewOrder
	 * @author 015979
	 * @date 2020-03-27 10:41:16
	 */
	public abstract void onNewOrder();

	/**
	 * 收到oms publish来的撤单推送
	 * 
	 * @MethodName: onCancelOrder
	 * @author 015979
	 * @date 2020-03-27 10:42:02
	 */
	public abstract void onCancelOrder();

	/**
	 * 收到订单成交
	 * 
	 * @MethodName: onExecutionOrder
	 * @author 015979
	 * @date 2020-03-27 10:34:42
	 */
	public abstract void onExecutionOrder();
}
