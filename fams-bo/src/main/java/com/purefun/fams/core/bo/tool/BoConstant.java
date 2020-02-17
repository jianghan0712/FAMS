/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.core.bo.tool;

/**
 * @Classname: BoConstant
 * @Description: bo模块的常量
 * @author jiang
 * @date 2020-02-06 15:04:28
 */
public interface BoConstant {

	interface FilePath {
		String protofile = "protofile";
		String otwfile = "otw";
	}

	interface SpecialWord {
		String com = "com";
		String bo_big = "BO";
		String bo_small = "bo";
		String generate = "Generate";
		String otw_big = "OTW";
	}
}
