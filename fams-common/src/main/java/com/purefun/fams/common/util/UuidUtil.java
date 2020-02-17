/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.common.util;

/**
 * @Classname: UuidUtil
 * @Description:
 * @author jiang
 * @date 2020-02-06 14:46:08
 */
public class UuidUtil {
	/**
	 * 统一的uuid生成器
	 * 
	 * @MethodName: createUuid
	 * @Description:
	 * @author jiang
	 * @date 2020-02-06 14:46:26
	 * @return
	 */
	public static String createUuid() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
}
