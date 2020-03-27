/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.middle.strategy.analysis;

import java.util.EventListener;

import com.purefun.fams.trade.order.otw.OrderBO_OTW;

/**
 * @Classname: TradeEventListener
 * @Description: 用于交易中台进行回测交易时的交易事件监听
 * @author 015979
 * @date 2020-03-27 16:01:12
 */
public interface TradeEventListener extends EventListener {
	/**
	 * 订单类型事件监听
	 * 
	 * @MethodName: handlerOrder
	 * @author 015979
	 * @date 2020-03-27 16:02:43
	 * @param orderEvent
	 */
	void handlerOrder(OrderBO_OTW orderEvent);

}
