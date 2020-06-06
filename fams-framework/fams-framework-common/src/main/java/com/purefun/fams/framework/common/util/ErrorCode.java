/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.util;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: err
 * @Description:
 * @author jianghan
 * @date 2020-06-06 13:30:05
 */

public class ErrorCode extends BaseDomain {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -6610009014400864816L;
	int id;
	String code;
	String desc;

	/**
	 * @param id
	 * @param code
	 * @param desc
	 */

	public ErrorCode(int id, String code, String desc) {
		super();
		this.id = id;
		this.code = code;
		this.desc = desc;
	}

	/**
	 * Getter method for property <tt>id</tt>.
	 * 
	 * @return property value of id
	 */

	public int getId() {
		return id;
	}

	/**
	 * Setter method for property <tt>id</tt>.
	 * 
	 * @param id value to be assigned to property id
	 */
	public void setId(int id) {
		this.id = id;
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