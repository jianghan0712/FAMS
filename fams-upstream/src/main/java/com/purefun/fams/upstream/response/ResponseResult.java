package com.purefun.fams.upstream.response;

import com.purefun.fams.common.domain.BaseDomain;
import com.purefun.fams.upstream.enums.ResponseEnum;

/**
 * 
 * @Classname: ResponseResult
 * @Description:
 * @author jianghan
 * @param <T>
 * @date 2020-05-03 21:09:51
 */
public class ResponseResult<T> extends BaseDomain {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 890715596272386972L;
	private int code = 20000;
	private String msg = "SUCCESS";
	private T result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Getter method for property <tt>result</tt>.
	 * 
	 * @return property value of result
	 */

	public T getResult() {
		return result;
	}

	/**
	 * Setter method for property <tt>result</tt>.
	 * 
	 * @param result value to be assigned to property result
	 */
	public void setResult(T result) {
		this.result = result;
	}

	/**
	 * 返回错误信息
	 * 
	 * @MethodName: fail
	 * @author jianghan
	 * @date 2020-05-04 15:16:22
	 * @param errorCode
	 * @param errMsg
	 * @return
	 */
	public void fail(ResponseEnum errorCode, String errMsg) {
		this.code = errorCode.getCode();
		this.msg = errMsg;
	}

}
