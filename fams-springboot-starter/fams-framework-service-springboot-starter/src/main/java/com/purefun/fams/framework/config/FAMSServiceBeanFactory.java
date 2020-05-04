/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.purefun.fams.framework.core.component.FAMSApplicationRunner;
import com.purefun.fams.framework.core.domain.ServiceInstance;
import com.purefun.fams.framework.core.service.CacheService;
import com.purefun.fams.framework.core.service.impl.CacheServiceImpl;
import com.purefun.fams.framework.core.service.impl.CommondServiceImpl;
import com.purefun.fams.framework.core.service.impl.GlobalParamServiceImpl;
import com.purefun.fams.framework.core.service.impl.RedisCacheLoaderServiceImpl;
import com.purefun.fams.framework.core.thread.CommondThread;
import com.purefun.fams.framework.core.thread.FAMSCoreThreadPool;

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
	public RedisCacheLoaderServiceImpl getRedisLoader() {
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

	/**
	 * fams的core线程池，非用户线程池
	 * 
	 * @MethodName: taskExecutro
	 * @author jianghan
	 * @date 2020-03-04 17:45:59
	 * @return
	 */
	@Bean
	public FAMSCoreThreadPool taskExecutro() {
		FAMSCoreThreadPool taskExecutor = new FAMSCoreThreadPool();
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setMaxPoolSize(50);
		taskExecutor.setQueueCapacity(200);
		taskExecutor.setKeepAliveSeconds(60);
		taskExecutor.setThreadNamePrefix("FAMS-core-thread-");
		taskExecutor.initPool();
		return taskExecutor;
	}

	@Bean
	public ServiceInstance initServiceInstance() {
		return new ServiceInstance();
	}

	@Bean
	public CommondThread commondThread() {
		return new CommondThread();
	}

	@Bean
	public CommondServiceImpl commondService() {
		return new CommondServiceImpl();
	}

	@Bean
	public CacheService cacheService() {
		return new CacheServiceImpl();
	}

	@Bean
	public FAMSApplicationRunner createApplicationRunner() {
		return new FAMSApplicationRunner();
	}

}
