/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.upstream.enums;

/**
 * @Classname: Http
 * @Description:
 * @author jianghan
 * @date 2020-05-03 21:11:02
 */
public enum ResponseEnum {
	// ok
	SUCCESS(20000, "SUCCESS"),
	// error
	FAIL(50000, "FAIL"),;

	/** 前台请求回报代码 */
	private int code;

	/** 前台请求回报描述 */
	private String desc;

	private ResponseEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
