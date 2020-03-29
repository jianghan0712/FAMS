/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.constant;

/**
 * @Classname: DirectionEnum
 * @Description: 交易方向定义
 * @author jianghan
 * @date 2020-03-05 17:24:16
 */
public enum MDTypeEnum {
	BAR("BAR", "BAR级数据"),

	TICK("TICK", "TICK级数据"),;

	/** 枚举代码 */
	private String code;

	/** 枚举描述 */
	private String desc;

	private MDTypeEnum(String code, String desc) {
		this.code = code;
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
	public static MDTypeEnum getByCode(String code) {
		for (MDTypeEnum eachValue : MDTypeEnum.values()) {
			if (code.equalsIgnoreCase(eachValue.getCode())) {
				return eachValue;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
