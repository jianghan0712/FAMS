/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.common.domain;

import com.purefun.fams.common.enums.RespondEnums;

/**
 * @Classname: BaseRespond
 * @Description: 服务请求的基准回报类
 * @author jianghan
 * @date 2020-05-17 19:52:56
 */
public class BaseRespond extends BaseDomain {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 4205032875524342148L;
	/** 统一回报码 非0时有错误 */
	private int resultCode = RespondEnums.SUCCESS.getCode();

	private String rejectCode;
	/** 统一回报信息 */
	private String resultMsg = RespondEnums.SUCCESS.getDesc();

	/**
	 * 设置错误原因和错误代码
	 * 
	 * @MethodName: setError
	 * @author jianghan
	 * @date 2020-05-17 20:09:09
	 * @param rejectCode
	 * @param resultMsg
	 */
	public void setError(String rejectCode, String resultMsg) {
		resultCode = RespondEnums.FAIL.getCode();
		this.rejectCode = rejectCode;
		this.resultMsg = resultMsg;
	}

	/**
	 * 返回是否成功
	 * 
	 * @MethodName: isSuccess
	 * @author jianghan
	 * @date 2020-05-17 20:08:51
	 * @return
	 */
	public boolean isSuccess() {
		return resultCode == RespondEnums.SUCCESS.getCode();
	}

	/**
	 * Getter method for property <tt>resultCode</tt>.
	 * 
	 * @return property value of resultCode
	 */

	public int getResultCode() {
		return resultCode;
	}

	/**
	 * Setter method for property <tt>resultCode</tt>.
	 * 
	 * @param resultCode value to be assigned to property resultCode
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * Getter method for property <tt>resultMsg</tt>.
	 * 
	 * @return property value of resultMsg
	 */

	public String getResultMsg() {
		return resultMsg;
	}

	/**
	 * Setter method for property <tt>resultMsg</tt>.
	 * 
	 * @param resultMsg value to be assigned to property resultMsg
	 */
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

}
