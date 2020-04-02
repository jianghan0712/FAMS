/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.analysis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.king.component.account.AccountContainer;
import com.purefun.fams.king.component.strategy.config.StrategyConfig;
import com.purefun.fams.king.component.strategy.model.TradeDetail;
import com.purefun.fams.md.MdBarDataBO;
import com.purefun.fams.trade.exec.otw.ExecutedOrderBO_OTW;
import com.purefun.fams.trade.order.otw.OrderBO_OTW;

/**
 * @Classname: TradeEventListener
 * @Description: 用于交易中台进行回测交易时的交易事件监听
 * @author 015979
 * @date 2020-03-27 16:01:12
 */
public abstract class OrderExecutor {
	private static final Logger logger = LogManager.getLogger(OrderExecutor.class);

	protected AccountContainer accountContainer;
	/**
	 * 存储新委托 <orderId,order>
	 */
	protected Map<String, OrderBO_OTW> neworderMap = new HashMap<String, OrderBO_OTW>();

	/**
	 * 全部处理结束的订单，完全成交，部分成交其余撤单等情况的单
	 */
	protected Map<String, OrderBO_OTW> fullOrderMap = new HashMap<String, OrderBO_OTW>();

	/**
	 * 成交回报缓存
	 */
	protected Map<String, List<ExecutedOrderBO_OTW>> executedOrderMap = new HashMap<String, List<ExecutedOrderBO_OTW>>();

	/**
	 * 持仓记录<stock_code, 持仓记录list>，对于一支证券，可能同时有多个当前持仓（分批买入）
	 */
	protected Map<String, List<TradeDetail>> positionMap = new HashMap<String, List<TradeDetail>>();

	/**
	 * 交易结果分析器
	 */
	protected DefaultTradeAnalysisContainer analysisContainer;

	/**
	 * 对OrderExecutor进行初始化
	 * 
	 * @MethodName: initOrderExecutorContainer
	 * @author 015979
	 * @date 2020-03-30 15:33:03
	 * @param config
	 */
	public void initOrderExecutorContainer(StrategyConfig config, AccountContainer accountContainer,
			DefaultTradeAnalysisContainer analysisContainer) {
		this.accountContainer = accountContainer;
		this.analysisContainer = analysisContainer;
	}

	/**
	 ** 处理订单类型事件
	 * 
	 * @MethodName: handlerOrder
	 * @author 015979
	 * @date 2020-03-27 16:02:43
	 * @param orderEvent
	 */
	public abstract void handlerOrder(OrderBO_OTW order);

	/**
	 * 处理成交回报
	 * 
	 * @MethodName: handlerExecutor
	 * @author 015979
	 * @date 2020-03-30 13:33:16
	 * @param executedOrder
	 */
	public abstract void handlerExecutor(ExecutedOrderBO_OTW executedOrder);

	/**
	 * 生成交易结果报告
	 * 
	 * @MethodName: analysisTrading
	 * @author 015979
	 * @date 2020-03-31 17:18:30
	 */
	public void genTradingResult(MdBarDataBO lastMarketData) {
		List<TradeDetail> tradingRecord = positionMap.get(lastMarketData.security_code);
		if (tradingRecord == null || tradingRecord.size() == 0) {
			logger.warn("没有该证券的交易记录，stockCode = {}", lastMarketData.security_code);
			return;
		}
		int size = tradingRecord.size();
		TradeDetail lastTrade = tradingRecord.get(size - 1);
		BigDecimal closePrice = lastTrade.getClosePrice();
		if (closePrice == null) {// 到行情回放截止时，仍有持仓，按最后一个bar计算总资产
			closePrice = new BigDecimal(lastMarketData.close).setScale(CommonUtil.ScaleUtil.CASH_SCALE,
					BigDecimal.ROUND_FLOOR);
			lastTrade.setClosePrice(closePrice);
			lastTrade.setCloseDate(lastMarketData.date);
			lastTrade.setFreezeVolume(lastTrade.getVolume());
		}
		tradingRecord.set(size - 1, lastTrade);

		analysisContainer.computeResultByStock(tradingRecord);

	}

	public Map<String, OrderBO_OTW> getNeworderMap() {
		return neworderMap;
	}

	public void setNeworderMap(Map<String, OrderBO_OTW> neworderMap) {
		this.neworderMap = neworderMap;
	}

	public Map<String, OrderBO_OTW> getFullOrderMap() {
		return fullOrderMap;
	}

	public void setFullOrderMap(Map<String, OrderBO_OTW> fullOrderMap) {
		this.fullOrderMap = fullOrderMap;
	}

	public Map<String, List<ExecutedOrderBO_OTW>> getExecutedOrderMap() {
		return executedOrderMap;
	}

	public void setExecutedOrderMap(Map<String, List<ExecutedOrderBO_OTW>> executedOrderMap) {
		this.executedOrderMap = executedOrderMap;
	}

	public Map<String, List<TradeDetail>> getPositionMap() {
		return positionMap;
	}

	public void setPositionMap(Map<String, List<TradeDetail>> positionMap) {
		this.positionMap = positionMap;
	}

}
