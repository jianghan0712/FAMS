/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.common.util;

/**
 * @Classname: CommonUtil
 * @Description: 存储通用的常变量
 * @author jiang
 * @date 2020-02-06 14:59:23
 */
public interface CommonUtil {
	/**
	 * 字符常量
	 * 
	 * @Classname: CharUtil
	 * @Description:
	 * @author jiang
	 * @date 2020-02-06 15:01:53
	 */
	interface CharUtil {
		String point = ".";
		String tab = "    ";
		String slash = "/";
		String re_slash = "\\";
		String semicolon = ";";
		String empty = "";
		String underline = "_";
		String caret = "^";
	}

	/**
	 * 存储有特殊语义的词语
	 * 
	 * @Classname: SpecialWordUtil
	 * @Description:
	 * @author jiang
	 * @date 2020-02-06 15:44:48
	 */
	interface SpecialWordUtil {
		String classpath = "classpath";
		String resource = "resource";
	}

	interface FileSuffixUtil {
		String proto = ".proto";
		String java = ".java";
	}
}
