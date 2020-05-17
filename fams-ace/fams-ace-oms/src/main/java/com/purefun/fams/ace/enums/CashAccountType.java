/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.enums;

/**
 * @Classname: CashAccountType
 * @Description:
 * @author jianghan
 * @date 2020-05-17 20:39:26
 */
public enum CashAccountType {
	AVAILABLE(0, "操作可用资金账户"), FREEZE(1, "操作冻结资金账户"), ONWAY(2, "操作在途资金账户"),;

	/** 枚举代码 */
	private int code;

	/** 枚举描述 */
	private String desc;

	private CashAccountType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}
