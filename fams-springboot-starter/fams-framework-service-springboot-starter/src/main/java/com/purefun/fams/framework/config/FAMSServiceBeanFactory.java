/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.purefun.fams.framework.core.service.impl.GlobalParamServiceImpl;
import com.purefun.fams.framework.core.service.impl.RedisCacheLoaderServiceImpl;

/**
 * @Classname: FAMSServiceBeanFactory
 * @Description: framework-core自有服务对下层服务的beanFactory
 * @author jianghan
 * @date 2020-02-26 20:33:52
 */
@ConfigurationProperties
public class FAMSServiceBeanFactory {

	/**
	 ** 提供redis数据加载服务
	 * 
	 * @MethodName: getIgniteLoader
	 * @author jianghan
	 * @date 2020-02-26 20:56:17
	 * @return
	 */
	@Bean
	public RedisCacheLoaderServiceImpl getIgniteLoader() {
		return new RedisCacheLoaderServiceImpl();
	}

	/**
	 ** 提供全局参数查询服务
	 * 
	 * @MethodName: getGlobalParamServiceImpl
	 * @author jianghan
	 * @date 2020-02-26 20:56:36
	 * @return
	 */
	@Bean
	public GlobalParamServiceImpl getGlobalParamServiceImpl() {
		return new GlobalParamServiceImpl();
	}
}
