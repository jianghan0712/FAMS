/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.king.component.strategy.analysis.TradeEventListener;
import com.purefun.fams.king.component.strategy.config.StrategyConfig;
import com.purefun.fams.king.constant.EventTypeEnum;
import com.purefun.fams.md.MdBarDataBO;
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

	/** 当前已加载的行情数 */
	int currentCount = 0;

	/** 后续事件监听器 */
	Map<EventTypeEnum, List<TradeEventListener>> eventListenerMap = new HashMap<EventTypeEnum, List<TradeEventListener>>();;

	@Override
	public void update(Observable o, Object arg) {
		Object[] param = (Object[]) arg;
		// 参数类型
		EventTypeEnum eventType = (EventTypeEnum) param[0];
		Object paramValue = param[1];

		if (eventType.equals(EventTypeEnum.EVENT_MD_BAR)) {
			onBar((MdBarDataBO) paramValue);
		} else if (eventType.equals(EventTypeEnum.EVENT_MD_END)) {
			onStop();
		}
	}

	/**
	 ** 向下游注册， 将eventType到指定的下游listener EVENT_TRADE_CANCEL_ORDER：撤单
	 * 
	 * @MethodName: register
	 * @author 015979
	 * @date 2020-03-27 19:34:54
	 * @param eventType: EVENT_TRADE_NEW_ORDER/ EVENT_TRADE_CANCEL_ORDER
	 * @param listener
	 */
	public void register(EventTypeEnum[] eventType, TradeEventListener listener) {
		for (EventTypeEnum eachEventType : eventType) {
			List<TradeEventListener> listenerList = eventListenerMap.get(eachEventType);
			if (listenerList == null) {
				listenerList = new ArrayList<TradeEventListener>();
			}
			listenerList.add(listener);
			eventListenerMap.put(eachEventType, listenerList);
		}
	}

	/**
	 ** 取消某种类型的监听器
	 * 
	 * @MethodName: unregister
	 * @author jianghan
	 * @date 2020-03-29 20:37:54
	 * @param eventType
	 * @param listenerClass
	 */
	public void unregister(EventTypeEnum eventType, Class<?> listenerClass) {
		List<TradeEventListener> listenerList = eventListenerMap.get(eventType);
		if (listenerList == null) {
			;
			return;
		}

		listenerList.forEach(item -> {
			if (item.getClass().equals(listenerClass)) {
				listenerList.remove(item);
			}
		});

	}

	/**
	 ** 发信号给下游
	 * 
	 * @MethodName: notifyEvent
	 * @author jianghan
	 * @date 2020-03-29 20:36:19
	 * @param eventType
	 * @param order
	 */
	public void notifyOrderEvent(EventTypeEnum eventType, OrderBO_OTW order) {
		List<TradeEventListener> listenerList = eventListenerMap.get(eventType);
		if (listenerList == null) {
			logger.warn("当前没有listener注册到{}事件", eventType.getEventType());
			return;
		}

		for (TradeEventListener each : listenerList) {
			each.handlerOrder(order);
		}
	}

	/**
	 ** 处理非交易类的事件
	 * 
	 * @MethodName: notifyOtherEvent
	 * @author jianghan
	 * @date 2020-03-29 21:11:35
	 * @param eventType
	 * @param event
	 */
	private void notifyOtherEvent(EventTypeEnum eventType, Object event) {
		List<TradeEventListener> listenerList = eventListenerMap.get(eventType);
		if (listenerList == null) {
			logger.warn("当前没有listener注册到{}事件", eventType.getEventType());
			return;
		}

		for (TradeEventListener each : listenerList) {
			each.handlerEvent(event);
		}
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
		notifyOtherEvent(EventTypeEnum.EVENT_TRADE_INIT, straConfig);
	}

	/**
	 * 启动策略容器
	 * 
	 * @MethodName: onStart
	 * @author 015979
	 * @date 2020-03-27 10:37:14
	 */
	public void onStart() {
		notifyOtherEvent(EventTypeEnum.EVENT_TRADE_START, null);
	}

	/**
	 * 停止策略容器
	 * 
	 * @MethodName: onStop
	 * @author 015979
	 * @date 2020-03-27 10:37:31
	 */
	public void onStop() {
		notifyOtherEvent(EventTypeEnum.EVENT_TRADE_END, null);
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
	 * 发送新订单到oms
	 * 
	 * @MethodName: sendNewOrder
	 * @author 015979
	 * @date 2020-03-27 10:33:10
	 */
	public abstract void sendNewOrder(OrderBO_OTW order);

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
