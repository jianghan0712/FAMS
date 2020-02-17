package com.purefun.fams.framework.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 用于管理server的bean
 * 
 * @Classname: SpringUtil
 * @Description:
 * @author jiang
 * @date 2020-02-10 18:35:14
 */
@Component
public class SpringUtil implements ApplicationContextAware {
	private static final Logger logger = LogManager.getLogger(SpringUtil.class);

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
		logger.info("ApplicationContext配置成功,applicationContext对象：" + SpringUtil.getApplicationContext());
	}

	/**
	 * 获取context
	 * 
	 * @MethodName: getApplicationContext
	 * @author jiang
	 * @date 2020-02-10 18:37:23
	 * @return
	 */
	private static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据bean名字获取对应的对象
	 * 
	 * @MethodName: getBean
	 * @author jiang
	 * @date 2020-02-10 18:35:42
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	/**
	 * 根据类型获取对应的对象
	 * 
	 * @MethodName: getBean
	 * @author jiang
	 * @date 2020-02-10 18:36:01
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * 根据bean的名字和类型获取对象
	 * 
	 * @MethodName: getBean
	 * @author jiang
	 * @date 2020-02-10 18:36:17
	 * @param <T>
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);

	}
}
