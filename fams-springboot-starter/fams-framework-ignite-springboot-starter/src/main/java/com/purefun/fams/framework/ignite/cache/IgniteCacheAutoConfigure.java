/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.purefun.fams.common.util.ClassHandleUtil;

/**
 * @Classname: IgniteCacheAutoConfigure
 * @Description: 配置cache相关参数
 * @author jiang
 * @date 2020-02-20 23:25:32
 */

@Configuration
@EnableConfigurationProperties({ IgniteCacheProperties.class })
@SuppressWarnings("rawtypes")
public class IgniteCacheAutoConfigure {
	private static final Logger logger = LogManager.getLogger(IgniteCacheAutoConfigure.class);

	@Autowired
	private IgniteCacheProperties cacheProperties;

	@Autowired
	private Ignite mainIgniteContainer;

	@Bean
	public IgniteCache getCache() {
		IgniteCache cache = new IgniteCache();
		cache.initCache(mainIgniteContainer, getCacheConfigs());
		return cache;
	}

	/**
	 * 解析所有cache config
	 * 
	 * @MethodName: getCacheConfigs
	 * @author jianghan
	 * @date 2020-02-23 14:34:28
	 * @return
	 */
	private CacheConfiguration[] getCacheConfigs() {
		if (cacheProperties == null || cacheProperties.getCacheList() == null
				|| cacheProperties.getCacheList().size() == 0) {
			logger.error("配置文件中未设置fams.framework.ignite.cache.cacheList");
			return null;
		}
		int size = cacheProperties.getCacheList().size();
		CacheConfiguration[] cfg = new CacheConfiguration[size];

		List<IgniteCachePropertie> cacheList = cacheProperties.getCacheList();
		for (int i = 0; i < size; i++) {
			IgniteCachePropertie each = cacheList.get(i);
			Field[] fileds = each.getClass().getDeclaredFields();
			cfg[i] = new CacheConfiguration();
			try {
				for (Field field : fileds) {
					String fieldName = field.getName();
					ClassHandleUtil.setFieldValue(cfg[i], fieldName, ClassHandleUtil.getFieldValue(each, fieldName));
				}
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReflectiveOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return cfg;
	}
}
