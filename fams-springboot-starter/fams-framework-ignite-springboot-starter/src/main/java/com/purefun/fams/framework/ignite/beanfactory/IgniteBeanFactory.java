/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.beanfactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.purefun.fams.framework.ignite.cache.IgniteCacheLoaderServiceImpl;

/**
 * @Classname: IgniteBeanFactory
 * @Description:
 * @author jianghan
 * @date 2020-02-26 23:02:23
 */
@ConfigurationProperties
public class IgniteBeanFactory {

	/**
	 ** 提供ignite数据加载服务
	 * 
	 * @MethodName: getIgniteCacheLoaderServiceImpl
	 * @author jianghan
	 * @date 2020-02-26 23:07:58
	 * @return
	 */
	@Bean
	public IgniteCacheLoaderServiceImpl getIgniteCacheLoaderServiceImpl() {
		return new IgniteCacheLoaderServiceImpl();
	}
}
