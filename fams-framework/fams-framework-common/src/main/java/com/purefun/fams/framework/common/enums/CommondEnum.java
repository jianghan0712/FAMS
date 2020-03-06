/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.enums;

/**
 * @Classname: CommondEnum
 * @Description:
 * @author jianghan
 * @date 2020-03-05 17:24:16
 */
public enum CommondEnum {
	DOWN("down", "下线服务", 0, "downMethod"),

	EXIT("exit", "退出服务", 0, "exitMethod"),

	UP("up", "启动服务", 0, "upMethod"),;

	/** 枚举代码 */
	private String commondCode;

	/** 枚举描述 */
	private String desc;

	/** 参数个数 **/
	private int paramSize;

	/** 执行的方法名 */
	private String methodName;

	private CommondEnum(String commondCode, String desc, int paramSize, String methodName) {
		this.commondCode = commondCode;
		this.desc = desc;
		this.paramSize = paramSize;
		this.methodName = methodName;
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
	public static CommondEnum getByCode(String code) {
		for (CommondEnum eachValue : CommondEnum.values()) {
			if (code.equalsIgnoreCase(eachValue.getCommondCode())) {
				return eachValue;
			}
		}
		return null;
	}

	public String getCommondCode() {
		return commondCode;
	}

	public void setCommondCode(String commondCode) {
		this.commondCode = commondCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getParamSize() {
		return paramSize;
	}

	public void setParamSize(int paramSize) {
		this.paramSize = paramSize;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
