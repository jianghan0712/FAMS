/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.exception;

import com.purefun.fams.framework.common.util.ErrorCode;
import com.purefun.fams.framework.common.util.ErrorCodeUtil;

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
	private ErrorCode errorCode = null;

	public FAMSException() {
		super();
		errorCode = ErrorCodeUtil.error();
	}

	public FAMSException(String errMsg) {
		super(errMsg);
		errorCode = ErrorCodeUtil.error();
	}

	public FAMSException(int errorId) {
		super();
		this.errorCode = ErrorCodeUtil.error(errorId);
	}

	public FAMSException(String message, int errorId) {
		super(message);
		this.errorCode = ErrorCodeUtil.error(errorId);
	}

	public FAMSException(String message, Throwable cause, int errorId) {
		super(message, cause);
		this.errorCode = ErrorCodeUtil.error(errorId);
	}

	public FAMSException(Throwable cause, int errorId) {
		super(cause);
		this.errorCode = ErrorCodeUtil.error(errorId);
	}

	/**
	 * Getter method for property <tt>errorCode</tt>.
	 * 
	 * @return property value of errorCode
	 */

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * Setter method for property <tt>errorCode</tt>.
	 * 
	 * @param errorCode value to be assigned to property errorCode
	 */
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
