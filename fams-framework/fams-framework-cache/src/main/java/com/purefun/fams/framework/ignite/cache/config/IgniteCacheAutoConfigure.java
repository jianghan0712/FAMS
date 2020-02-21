/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import com.purefun.fams.framework.ignite.cache.IgniteCache;
import com.purefun.fams.framework.ignite.config.IgniteAutoConfigure;

/**
 * @Classname: IgniteCacheAutoConfigure
 * @Description: 配置cache相关参数
 * @author jiang
 * @date 2020-02-20 23:25:32
 */

@Configuration
@Import({ IgniteAutoConfigure.class })
@EnableConfigurationProperties({ IgniteCacheProperties.class })
public class IgniteCacheAutoConfigure {
	@Autowired
	private IgniteCacheProperties cacheProperties ;
	
	@Autowired
	private Ignite mainIgniteContainer;

	@Autowired
	private CacheConfiguration cacheCfg;
	
	@Bean
	public IgniteCache getCache() {
		IgniteCache cache = new IgniteCache(mainIgniteContainer, cacheCfg);	
		return cache;	
	}
	
	@Bean
	@Lazy
	public CacheConfiguration getCacheConfig() {
		CacheConfiguration cfg = new CacheConfiguration();
		cfg.setName(cacheProperties.getName());
		cfg.setAtomicityMode(cacheProperties.getAtomicityMode());
		cfg.setCacheMode(cacheProperties.getCacheMode());
		cfg.setIndexedTypes(cacheProperties.getIndexedTypes());
		return cfg;
	}
}
