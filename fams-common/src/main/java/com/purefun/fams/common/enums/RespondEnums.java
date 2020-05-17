/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.common.enums;

/**
 * @Classname: RespondEnums
 * @Description:
 * @author jianghan
 * @date 2020-05-17 20:04:13
 */
public enum RespondEnums {
	SUCCESS(0, "成功"),

	FAIL(-1, "失败"),;

	/** 枚举代码 */
	private int code;

	/** 枚举描述 */
	private String desc;

	private RespondEnums(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * Getter method for property <tt>code</tt>.
	 * 
	 * @return property value of code
	 */

	public int getCode() {
		return code;
	}

	/**
	 * Setter method for property <tt>code</tt>.
	 * 
	 * @param code value to be assigned to property code
	 */
	public void setCode(int code) {
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
