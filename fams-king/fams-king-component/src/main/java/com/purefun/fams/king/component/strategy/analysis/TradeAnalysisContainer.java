/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.analysis;

import com.purefun.fams.king.constant.DirectionEnum;
import com.purefun.fams.trade.order.otw.OrderBO_OTW;

/**
 * @Classname: TradeAnalysisContainer
 * @Description: 策略交易结果分析器
 * @author 015979
 * @date 2020-03-27 15:52:12
 */
public class TradeAnalysisContainer implements TradeEventListener {

	@Override
	public void handlerOrder(OrderBO_OTW orderEvent) {
		String direction = orderEvent.getDirection();
		if (direction.equalsIgnoreCase(DirectionEnum.BUY.getCode())) {
			buy(orderEvent);
		} else if (direction.equalsIgnoreCase(DirectionEnum.SELL.getCode())) {
			sell(orderEvent);
		}
	}

	@Override
	public void handlerEvent(Object event) {
		System.out.println("收到非交易事件：" + event);
	}

	public void buy(OrderBO_OTW orderEvent) {
		System.out.println("收到买入订单：" + orderEvent);
	}

	public void sell(OrderBO_OTW orderEvent) {
		System.out.println("收到卖出订单：" + orderEvent);
	}

}
