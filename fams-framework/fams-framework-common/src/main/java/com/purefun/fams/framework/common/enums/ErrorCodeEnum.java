/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Classname: ErrorEnum
 * @Description: FAMS错误码
 * @author jiang
 * @date 2020-02-11 16:14:41
 */
public enum ErrorCodeEnum {
	SUCCESS("SUCCESS", "成功"),

	UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", "未知错误"),

	;

	/** 枚举代码 */
	private String code;

	/** 枚举描述 */
	private String desc;

	private ErrorCodeEnum(String code, String desc) {
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
	public static ErrorCodeEnum getByCode(String code) {
		for (ErrorCodeEnum eachValue : ErrorCodeEnum.values()) {
			if (StringUtils.equals(code, eachValue.getCode())) {
				return eachValue;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
