/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.common.util;

/**
 * @Classname: StringUtil
 * @Description: 字符串的工具类
 * @author jiang
 * @date 2020-02-06 15:10:33
 */
public class StringUtil {

	/**
	 * 将string数组拼成一个string输出
	 * 
	 * @MethodName: append
	 * @author jiang
	 * @date 2020-02-06 15:16:38
	 * @param values 每个string
	 * @return null：values为空
	 */
	public static String append(String... values) {
		StringBuilder ret = new StringBuilder();
		if (values == null && values.length == 0)
			return null;

		for (String each : values) {
			if (each == null)
				continue;
			ret.append(each);
		}

		return ret.toString().trim();
	}

	/**
	 * 拼接指定的路径
	 * 
	 * @MethodName: appendFilePath
	 * @author jiang
	 * @date 2020-02-06 15:20:18
	 * @param prefix           第一个路径的前缀：classpath：/ . / 或者指定的路径前缀
	 * @param subPath          路径的数组
	 * @param discordLastSlash 是否需要丢弃最后一个/
	 * @return
	 */
	public static String appendFilePath(String prefix, boolean discordLastSlash, String... subPath) {
		String ret = prefix == null ? null : prefix;
		if (subPath == null || subPath.length == 0) {
			return ret;
		}

		String[] tempPathArray = new String[2 * (subPath.length + 1)];
		int i = 0, j = 0;
		// 如果prefix不是null时，把prefix作为第一个path
		if (ret != null) {
			tempPathArray[j++] = ret;
			if (!ret.startsWith(CommonUtil.SpecialWordUtil.classpath))
				tempPathArray[j++] = CommonUtil.CharUtil.slash;
		}

		// 按/ + path组成一个string组
		while (i < subPath.length) {
			if (subPath[i] != null && subPath[i].equals(CommonUtil.CharUtil.empty)) {
				i++;
				continue;
			}
			tempPathArray[j++] = subPath[i++];
			tempPathArray[j++] = CommonUtil.CharUtil.slash;
		}

		ret = append(tempPathArray);

		return discordLastSlash ? ret.substring(0, ret.length() - 1) : ret;
	}

}
