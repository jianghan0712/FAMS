/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.common.util.ClassHandleUtil;
import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.framework.core.service.CacheLoaderService;
import com.purefun.fams.framework.core.service.RedisService;

/**
 * @Classname: RedisCacheLoaderServiceImpl
 * @Description: 将指定的数据从db加载到redis中，主要针对不易变化的数据
 * @author jianghan
 * @date 2020-02-26 20:03:13
 */
public class RedisCacheLoaderServiceImpl extends CacheLoaderService {
	private static final Logger logger = LogManager.getLogger(RedisCacheLoaderServiceImpl.class);
	@Autowired
	private RedisService redisOp;

	@Override
	public <V> void loadData2Cache(List<V> list, String cacheName, String... keyFieldName) {
		if (list == null || list.size() <= 0 || StringUtils.isBlank(cacheName)) {
			return;
		}

		// 先清空该cache
		redisOp.del(cacheName);
		V first = list.get(0);
		Class<?> vClass = first.getClass();

		try {
			checkKeyFields(keyFieldName, vClass);
			StringBuilder key = new StringBuilder();
			for (V eachValue : list) {
				// key=各个keyFieldName之间用^拼接，如 paramScope^paramName
				for (String eachKey : keyFieldName) {
					Object keyValue = ClassHandleUtil.getFieldValue(eachValue, eachKey);
					key.append(keyValue.toString()).append(CommonUtil.CharUtil.caret);
				}
				key.deleteCharAt(key.length() - 1);
				redisOp.hset(cacheName, key.toString(), eachValue);
				key.delete(0, key.length());
			}

			logger.info("加载cache成功，cacheName:{},数据量：{}", cacheName, list.size());
		} catch (Exception e) {
			logger.info("加载cache失败，cacheName:{},失败原因：{}", cacheName, e);
		}

	}

	@Override
	public <V> void loadData2CacheBySelf(List<V> list, String cacheName, String... keyFieldName) {
		loadData2Cache(list, cacheName, keyFieldName);
	}

}
