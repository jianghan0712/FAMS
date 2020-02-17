/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.util;

import org.apache.commons.lang3.StringUtils;

import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.exception.FAMSException;

/**
 * @Classname: AssertUtil
 * @Description: 断言常量
 * @author jiang
 * @date 2020-02-11 15:38:34
 */
public class AssertUtil {

	/**
	 * 断言表达式的值为true，否则抛FAMSException
	 * 
	 * @param expValue      断言表达式
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertTrue(boolean expValue, ErrorCodeEnum errorCodeEnum) {

		if (!expValue) {
			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言表达式的值为false，否则抛RuntimeException
	 * 
	 * @param expValue      断言表达式
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertFalse(boolean expValue, ErrorCodeEnum errorCodeEnum) {

		if (expValue) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言两个对象相等，否则抛RuntimeException
	 * 
	 * @param obj1
	 * @param obj2
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertEquals(Object obj1, Object obj2, ErrorCodeEnum errorCodeEnum) {

		if (obj1 == null) {

			assertNull(obj2, errorCodeEnum);
			return;
		}

		if (!obj1.equals(obj2)) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言两个对象不等，否则抛RuntimeException
	 * 
	 * @param obj1
	 * @param obj2
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertNotEquals(Object obj1, Object obj2, ErrorCodeEnum errorCodeEnum) {

		if (obj1 == null) {

			assertNotNull(obj2, errorCodeEnum);
			return;
		}

		if (obj1.equals(obj2)) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言对象至少等于容器中的一个
	 * 
	 * @param obj1
	 * @param objects
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertEqualsAny(Object obj1, Object[] objects, ErrorCodeEnum errorCodeEnum) {

		if (null == objects) {

			throw new FAMSException(errorCodeEnum);
		}

		for (Object obj2 : objects) {

			if (obj1 == null) {

				if (obj2 == null) {
					return;
				}

				continue;
			}

			if (obj1.equals(obj2)) {

				return;
			}
		}

		throw new FAMSException(errorCodeEnum);
	}

	/**
	 * 断言字符串至少等于容器中的一个
	 *
	 * @param obj1
	 * @param objects
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertEqualsAnyString(String obj1, String[] objects, ErrorCodeEnum errorCodeEnum) {

		if (null == objects) {

			throw new FAMSException(errorCodeEnum);
		}

		for (String obj2 : objects) {

			if (obj1 == null) {

				if (obj2 == null) {
					return;
				}

				continue;
			}

			if (obj1.equalsIgnoreCase(obj2)) {

				return;
			}
		}

		throw new FAMSException(errorCodeEnum);
	}

	/**
	 * 断言两个对象相同，否则抛RuntimeException
	 * 
	 * @param obj1
	 * @param obj2
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertIs(Object obj1, Object obj2, ErrorCodeEnum errorCodeEnum) {

		if (obj1 != obj2) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言两个对象不同，否则抛RuntimeException
	 * 
	 * @param obj1
	 * @param obj2
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertIsNot(Object obj1, Object obj2, ErrorCodeEnum errorCodeEnum) {

		if (obj1 == obj2) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言对象在容器中
	 * 
	 * @param obj1
	 * @param objs
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertIn(Object obj1, Object[] objs, ErrorCodeEnum errorCodeEnum) {

		if (null == objs) {

			throw new FAMSException(errorCodeEnum);
		}

		for (Object obj : objs) {

			if (obj == obj1) {

				return;
			}
		}

		throw new FAMSException(errorCodeEnum);
	}

	/**
	 * 断言对象为空，否则抛RuntimeException
	 * 
	 * @param str           断言字符串
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertEmpty(String str, ErrorCodeEnum errorCodeEnum) {

		if (StringUtils.isNotEmpty(str)) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言对象为非空，否则抛RuntimeException
	 * 
	 * @param str           断言字符串
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertNotEmpty(String str, ErrorCodeEnum errorCodeEnum) {

		if (StringUtils.isEmpty(str)) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言对象为空，否则抛RuntimeException
	 * 
	 * @param str           断言字符串
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertBlank(String str, ErrorCodeEnum errorCodeEnum) {

		if (StringUtils.isNotBlank(str)) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言对象为非空，否则抛RuntimeException
	 * 
	 * @param str           断言字符串
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertNotBlank(String str, ErrorCodeEnum errorCodeEnum) {

		if (StringUtils.isBlank(str)) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言对象为null，否则抛RuntimeException
	 * 
	 * @param object        断言对象
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertNull(Object object, ErrorCodeEnum errorCodeEnum) {

		if (object != null) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言对象非null，否则抛RuntimeException
	 * 
	 * @param object        断言对象
	 * @param errorCodeEnum 异常描述
	 */
	public static void assertNotNull(Object object, ErrorCodeEnum errorCodeEnum) {

		if (null == object) {

			throw new FAMSException(errorCodeEnum);
		}
	}

	/**
	 * 断言对象非null，否则抛RuntimeException
	 * 
	 * @param object 断言对象
	 */
	public static void assertNotNull(Object object) {

		if (null == object) {

			throw new FAMSException();
		}
	}
}
