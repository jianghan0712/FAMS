/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.common.util;

import java.math.BigDecimal;

/**
 * @Classname: MathUtil
 * @Description: 一些数据工具
 * @author JiangHan
 * @date 2020-05-27 11:47:27
 */
public class MathUtil {
	/**
	 * 检查value是否大于0，
	 * 
	 * @MethodName: checkThanZero
	 * @author JiangHan
	 * @date 2020-05-27 11:48:58
	 * @param value
	 * @return
	 */
	public static int compareThanZero(BigDecimal value) {
		return value.compareTo(BigDecimal.ZERO);
	}
}
