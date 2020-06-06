/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.util;

import com.purefun.fams.common.domain.BaseRespond;
import com.purefun.fams.framework.common.exception.FAMSException;

/**
 * 
 * @Classname: ResponseUtil
 * @Description:
 * @author jianghan
 * @date 2020-06-06 12:41:25
 */
public class ResponseUtil {

	/**
	 * 创建统一的错误回报
	 * 
	 * @MethodName: buildErrorResponse
	 * @author jianghan
	 * @date 2020-06-06 12:45:36
	 * @param response
	 * @param e
	 */
	public static void buildErrorResponse(BaseRespond response, Exception e) {
		ErrorCode error = null;
		if (e instanceof FAMSException) {
			FAMSException exception = (FAMSException) e;
			error = exception.getErrorCode();
		} else {
			error = ErrorCodeUtil.error();
		}

		if (response == null) {
			response = new BaseRespond();
		}

		response.setError(error.getCode(), error.getDesc());
	}

}