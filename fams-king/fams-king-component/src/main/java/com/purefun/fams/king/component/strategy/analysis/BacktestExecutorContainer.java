/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.analysis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.core.bo.tool.BoFactory;
import com.purefun.fams.king.component.account.AccountContainer;
import com.purefun.fams.king.component.strategy.config.StrategyConfig;
import com.purefun.fams.king.component.strategy.model.TradeDetail;
import com.purefun.fams.king.constant.DirectionEnum;
import com.purefun.fams.king.constant.OrderStatusEnum;
import com.purefun.fams.trade.exec.ExecutedOrderBO;
import com.purefun.fams.trade.exec.otw.ExecutedOrderBO_OTW;
import com.purefun.fams.trade.order.otw.OrderBO_OTW;

/**
 * @Classname: BackTestExecutorContainer
 * @Description:
 * @author 015979
 * @date 2020-03-30 13:29:03
 */
public class BacktestExecutorContainer extends OrderExecutor {

	public BacktestExecutorContainer(StrategyConfig config, AccountContainer accountContainer,
			DefaultTradeAnalysisContainer analysisContainer) {
		initOrderExecutorContainer(config, accountContainer, analysisContainer);
	}

	@Override
	public void handlerOrder(OrderBO_OTW order) {
		String direction = order.getDirection();

		ExecutedOrderBO_OTW execOrder = (ExecutedOrderBO_OTW) BoFactory.createBo(ExecutedOrderBO.class, true);
		execOrder.setExecutePrice(order.getOrderPrice());
		execOrder.setExecuteVolume(order.getOrderVolume());
		execOrder.setSecurity_code(order.getSecurity_code());
		execOrder.setOrderId(order.getOrderId());
		execOrder.setExecuteId(execOrder.getUuid());
		execOrder.setTradeDate(order.getTradeDate());
		execOrder.setDirection(order.getDirection());
		execOrder.setAccount(order.getAccount());

		// 模拟成交结果
		handlerExecutor(execOrder);

		// 插入完全成交full map
		order.setOrderStatus(OrderStatusEnum.DONE.getCode());
		fullOrderMap.put(order.getOrderId(), order);
	}

	@Override
	public void handlerExecutor(ExecutedOrderBO_OTW executedOrder) {
		// 插入成交委托map
		List<ExecutedOrderBO_OTW> execList = executedOrderMap.getOrDefault(executedOrder.getOrderId(),
				new ArrayList<ExecutedOrderBO_OTW>());
		execList.add(executedOrder);
		executedOrderMap.put(executedOrder.getOrderId(), execList);

		// 更新持仓map
		List<TradeDetail> positionList = positionMap.getOrDefault(executedOrder.getSecurity_code(),
				new ArrayList<TradeDetail>());
		String side = executedOrder.getDirection();
		if (side.equalsIgnoreCase(DirectionEnum.BUY.getCode())) {
			// 如果是买，只需要插入list即可
			TradeDetail detail = new TradeDetail();
			detail.setAccount(executedOrder.getAccount());
			detail.setOpenDate(executedOrder.getTradeDate());
			BigDecimal price = new BigDecimal(executedOrder.getExecutePrice()).setScale(CommonUtil.ScaleUtil.CASH_SCALE,
					BigDecimal.ROUND_FLOOR);
			detail.setOpenPrice(price);
			detail.setSecurityCode(executedOrder.getSecurity_code());
			detail.setVolume(executedOrder.getExecuteVolume());
			detail.setCash(accountContainer.getAccount(executedOrder.getAccount()).getAvailableCapital());
			positionList.add(detail);
			BigDecimal cashOut = new BigDecimal(executedOrder.getExecuteVolume()).multiply(price)
					.setScale(CommonUtil.ScaleUtil.CASH_SCALE, BigDecimal.ROUND_FLOOR);
			// 买入成功，解冻资金，减少资产
			accountContainer.unfreeze(executedOrder.getAccount(), cashOut, true);

//			setCash(cash.subtract(cashOut));
		} else if (side.equalsIgnoreCase(DirectionEnum.SELL.getCode())) {
			// 卖出操作时，扣减持仓
			for (int i = 0; i < positionList.size(); i++) {
				TradeDetail detail = positionList.get(i);
				if (detail.getVolume() > 0) {
					detail.setVolume(0l);
					detail.setClosePrice(new BigDecimal(executedOrder.getExecutePrice())
							.setScale(CommonUtil.ScaleUtil.CASH_SCALE, BigDecimal.ROUND_FLOOR));
					detail.setCloseDate(executedOrder.getTradeDate());
					positionList.set(i, detail);
				}
			}
			BigDecimal cashIn = new BigDecimal(executedOrder.getExecuteVolume())
					.multiply(new BigDecimal(executedOrder.getExecutePrice()))
					.setScale(CommonUtil.ScaleUtil.CASH_SCALE, BigDecimal.ROUND_FLOOR);
			accountContainer.cashin(positionList.get(0).getAccount(), cashIn);
//			setCash(cash.add(cashIn));
		}
		positionMap.put(executedOrder.getSecurity_code(), positionList);
//		System.out.println(positionMap);
	}

}
