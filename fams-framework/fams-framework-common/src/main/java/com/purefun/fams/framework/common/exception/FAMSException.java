/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.exception;

import com.purefun.fams.framework.common.enums.ErrorCodeEnum;

/**
 * @Classname: FAMSException
 * @Description: 业务异常
 * @author jiang
 * @date 2020-02-11 15:48:33
 */
public class FAMSException extends RuntimeException {

	/** uid */
	private static final long serialVersionUID = 4932613288872401908L;

	/** 默认错误码 */
	private ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.UNKNOWN_EXCEPTION;

	public FAMSException() {
		super();
	}

	public FAMSException(String errMsg) {
		super(errMsg);
	}

	public FAMSException(ErrorCodeEnum errorCodeEnum) {
		super();
		this.errorCodeEnum = errorCodeEnum;
	}

	public FAMSException(String message, ErrorCodeEnum errorCodeEnum) {
		super(message);
		this.errorCodeEnum = errorCodeEnum;
	}

	public FAMSException(String message, Throwable cause, ErrorCodeEnum errorCodeEnum) {
		super(message, cause);
		this.errorCodeEnum = errorCodeEnum;
	}

	public FAMSException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
		super(cause);
		this.errorCodeEnum = errorCodeEnum;
	}

	/**
	 * Getter method for property errorCodeEnum.
	 *
	 * @return property value of errorCodeEnum
	 */
	public ErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}

	/**
	 * Setter method for property errorCodeEnum.
	 *
	 * @param errorCodeEnum value to be assigned to property errorCodeEnum
	 */
	public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
		this.errorCodeEnum = errorCodeEnum;
	}
}
