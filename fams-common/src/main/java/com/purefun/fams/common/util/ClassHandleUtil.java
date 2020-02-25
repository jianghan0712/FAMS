/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.common.util;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
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

	/**
	 * 通过反射执行mybatis代理的mapper的具体方法
	 * 
	 * @MethodName: executeMybatisProxy
	 * @author jianghan
	 * @date 2020-02-25 23:29:00
	 * @param <M>        mapper类
	 * @param <V>        返回结果的domin类
	 * @param sqlSession mybatis的session
	 * @param mapperName 要执行的mapper名
	 * @param methodName 要执行的方法名
	 * @param paramTypes 参数类型列表
	 * @param paramValue 参数值列表
	 * @return 执行后的结果
	 * @throws RuntimeException
	 * @throws ReflectiveOperationException
	 */
	public static <M, V> List<V> executeMybatisProxy(SqlSession sqlSession, String mapperName, String methodName,
			Class[] paramTypes, Object[] paramValue) throws RuntimeException, ReflectiveOperationException {
		if (sqlSession == null || StringUtils.isBlank(mapperName) || StringUtils.isBlank(methodName)) {
			logger.error("sqlSession or mapperName or methodName MUST NOT NULL ");
			throw new RuntimeException("sqlSession or mapperName or methodName MUST NOT NULL");
		}
		if (!checkParam(paramTypes, paramValue)) {
			logger.error("执行参数有误paramTypes={}，paramValue={} ", paramTypes, paramValue);
			throw new RuntimeException("执行参数有误");
		}

		Class<?> clazz = Class.forName(mapperName);
		M mapperDao = (M) sqlSession.getMapper(clazz);
		Method method = clazz.getMethod(methodName, paramTypes);
		List<V> result = (List<V>) method.invoke(mapperDao, paramValue);

		return result;
	}

	/**
	 * executeMybatisProxy的传参类型和传参值得检查
	 * 
	 * @MethodName: checkParam
	 * @author jianghan
	 * @date 2020-02-25 23:31:20
	 * @param paramTypes 参数类型数组
	 * @param paramValue 参数值数组
	 * @return
	 */
	private static boolean checkParam(Class[] paramTypes, Object[] paramValue) {
		boolean ret = true;
		if (paramTypes == null && paramValue == null) {
			return true;
		} else if ((paramTypes == null && paramValue != null) || (paramTypes != null && paramValue == null)) {
			return false;
		}

		if (paramTypes.length != paramValue.length)
			ret = false;
		else {
			for (int i = 0; i < paramTypes.length; i++) {
				if (paramTypes[i] == null || paramValue[i] == null) {
					ret = false;
					break;
				}
				Class<?> eachParamType = paramTypes[i];
				Class<?> eachParamValueType = paramValue[i].getClass();
				if (!eachParamValueType.isAssignableFrom(eachParamType)) {
					ret = false;
					break;
				}
			}
		}

		return ret;
	}
}
