/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.common.util;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: ClassHandleUtil
 * @Description: 反射类相关工具类
 * @author jianghan
 * @date 2020-02-21 22:15:20
 */
public class ClassHandleUtil {
	private static final Logger logger = LogManager.getLogger(ClassHandleUtil.class);

	/**
	 ** 获取对象o中的 fieldName属性，通过反射get方法实现。如果该方法不符合规范，则无法使用该方法
	 * 
	 * @MethodName: getFieldValue
	 * @author jianghan
	 * @date 2020-02-21 23:12:03
	 * @param <T>       o的类型
	 * @param o         对象o
	 * @param fieldName o的属性名
	 * @return 对象o的fieldName的属性。若为null，
	 * @exception RuntimeException             当出现参数或其他情况异常时抛出此错误
	 * @exception ReflectiveOperationException 反射异常
	 */
	public static <T> Object getFieldValue(T o, String fieldName)
			throws RuntimeException, ReflectiveOperationException {
		if (o == null || StringUtils.isBlank(fieldName)) {
			logger.error("target class or fieldName MUST NOT NULL ");
			throw new RuntimeException("target class or fieldName MUST NOT NULL ");
		}

		Class<?> classType = o.getClass();
		if (classType.getDeclaredField(fieldName) == null) {
			logger.error("{} not contain FIELD :{}", classType, fieldName);
			throw new RuntimeException(classType.getName() + "not contain FIELD " + fieldName);
		}

		StringBuffer getMethodName = new StringBuffer("get").append(fieldName.substring(0, 1).toUpperCase())
				.append(fieldName.substring(1));
		Method method = classType.getMethod(getMethodName.toString());
		if (method == null) {
			logger.error("{} not contain method :{}", classType, getMethodName);
			throw new RuntimeException(classType.getName() + "not contain method " + getMethodName);
		}

		return method.invoke(o);
	}

	/**
	 ** 对对象o中的 fieldName属性进行设置，通过反射set方法实现。故如果该方法不符合规范，则无法使用该方法
	 * 
	 * @MethodName: setFieldValue
	 * @author jianghan
	 * @date 2020-02-21 23:40:06
	 * @param <T>       对象o的类型
	 * @param <V>       field的类型
	 * @param o         对象o
	 * @param fieldName field的名称
	 * @param value     要set的值
	 * @exception RuntimeException             当出现参数或其他情况异常时抛出此错误
	 * @exception ReflectiveOperationException 反射异常
	 */
	public static <T, V> void setFieldValue(T o, String fieldName, V value)
			throws RuntimeException, ReflectiveOperationException {
		if (value == null)
			return;

		if (o == null || StringUtils.isBlank(fieldName)) {
			logger.error("target class or fieldName MUST NOT NULL ");
			throw new RuntimeException("target class or fieldName MUST NOT NULL ");
		}

		Class<?> classType = o.getClass();
		if (classType.getDeclaredField(fieldName) == null) {
			logger.error("{} not contain FIELD :{}", classType, fieldName);
			throw new RuntimeException(classType.getName() + "not contain FIELD " + fieldName);
		}

		StringBuffer setMethodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase())
				.append(fieldName.substring(1));
		Method method = classType.getMethod(setMethodName.toString(), value.getClass());
		if (method == null) {
			logger.error("{} not contain method :{}", classType, setMethodName);
			throw new RuntimeException(classType.getName() + "not contain method " + setMethodName);
		}

		method.invoke(o, value);
	}

}
