/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.constant;

/**
 * @Classname: EventTypeEnum
 * @Description: 交易中台服务的事件定义
 * @author jianghan
 * @date 2020-03-05 17:24:16
 */
public enum EventTypeEnum {
	EVENT_INIT("EVENT_INIT", "初始化回测框架"),

	/** 行情容器事件 */
	EVENT_MD_END("EVENT_MD_END", "行情结束"),

	EVENT_MD_BAR("EVENT_MD_BAR", "bar型数据"),

	EVENT_MD_TICK("EVENT_MD_TICK", "tick型数据"),

	/** 策略容器事件 */
	EVENT_TRADE_INIT("EVENT_TRADE_INIT", "策略初始化"),

	EVENT_TRADE_START("EVENT_TRADE_INIT", "策略开始"),

	EVENT_TRADE_NEW_ORDER("EVENT_TRADE_NEW_ORDER", "新订单事件"),

	EVENT_TRADE_CANCEL_ORDER("EVENT_TRADE_CANCEL_ORDER", "撤单事件"),

	EVENT_TRADE_END("EVENT_TRADE_END", "策略停止运行事件"),;

	/** 枚举代码 */
	private String eventType;

	/** 枚举描述 */
	private String desc;

	private EventTypeEnum(String eventType, String desc) {
		this.eventType = eventType;
		this.desc = desc;
	}

	/**
	 * 
	 * 根据代码获取枚举，如果code对应的枚举不存在，则返回null
	 * 
	 * @param code 枚举代码
	 * 
	 * @return 对应的枚举对象
	 * 
	 */
	public static EventTypeEnum getByCode(String code) {
		for (EventTypeEnum eachValue : EventTypeEnum.values()) {
			if (code.equalsIgnoreCase(eachValue.getEventType())) {
				return eachValue;
			}
		}
		return null;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
