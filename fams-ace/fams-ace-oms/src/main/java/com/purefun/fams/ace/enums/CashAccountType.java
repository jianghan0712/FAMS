/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.enums;

/**
 * @Classname: CashAccountType
 * @Description:
 * @author jianghan
 * @date 2020-05-17 20:39:26
 */
public enum CashAccountType {
	AVAILABLE("AVAILABLE_ACCOUNT", "操作可用资金账户"), FREEZE("FREEZE_ACCOUNT", "操作冻结资金账户"),
	ONWAY("ONWAY_ACCOUNT", "操作在途资金账户"),;

	/** 枚举代码 */
	private String code;

	/** 枚举描述 */
	private String desc;

	private CashAccountType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static CashAccountType getByCode(String code) {
		for (CashAccountType eachValue : CashAccountType.values()) {
			if (code.equalsIgnoreCase(eachValue.getCode())) {
				return eachValue;
			}
		}
		return null;
	}

	/**
	 * Getter method for property <tt>code</tt>.
	 * 
	 * @return property value of code
	 */

	public String getCode() {
		return code;
	}

	/**
	 * Setter method for property <tt>code</tt>.
	 * 
	 * @param code value to be assigned to property code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter method for property <tt>desc</tt>.
	 * 
	 * @return property value of desc
	 */

	public String getDesc() {
		return desc;
	}

	/**
	 * Setter method for property <tt>desc</tt>.
	 * 
	 * @param desc value to be assigned to property desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
