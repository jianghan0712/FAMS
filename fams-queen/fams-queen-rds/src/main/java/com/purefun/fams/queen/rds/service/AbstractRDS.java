/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.queen.rds.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.framework.core.service.impl.RedisCacheLoaderServiceImpl;

/**
 * @Classname: 基础RDS服务
 * @Description:
 * @author jianghan
 * @date 2020-05-17 15:17:46
 */
public abstract class AbstractRDS {

	@Autowired
	protected RedisCacheLoaderServiceImpl redisLoader;

	/**
	 ** 将db中的数据load进RDS管理的global-redis
	 * 
	 * @MethodName: load2cache
	 * @author jianghan
	 * @date 2020-05-17 15:30:21
	 * @param mapperName
	 * @param methodName
	 * @param cacheName
	 * @param keyFildName
	 */
	public void load2cache(String mapperName, String methodName, String cacheName, String... keyFildName) {
		redisLoader.loadDataFromDB2Cache(mapperName, methodName, cacheName, keyFildName);
	}

}
