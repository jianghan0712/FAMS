/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.purefun.fams.framework.ignite.expose.IgniteCache;

/**
 * @Classname: IgniteCache
 * @Description: 对底层igniteCache进行的封装
 * @author jiang
 * @date 2020-02-20 23:59:06
 */
public class IgniteCacheImpl<K, V> implements IgniteCache<K, V> {
	private static final long serialVersionUID = -52240658148084463L;

	private static final Logger logger = LogManager.getLogger(IgniteCacheImpl.class);
	/** cacheMap，<key：cacheName ,value：具体的cache> */
	private Map<String, org.apache.ignite.IgniteCache<K, V>> cacheMap = null;

	@Override
	/** {@inheritDoc} */
	public void initCache(Ignite ignite, CacheConfiguration<K, V>[] cacheCfg) {
		cacheMap = new HashMap<String, org.apache.ignite.IgniteCache<K, V>>();
		for (CacheConfiguration<K, V> each : cacheCfg) {
			org.apache.ignite.IgniteCache<K, V> cache = ignite.getOrCreateCache(each);
			cacheMap.put(cache.getName(), cache);
		}
	}

	@Override
	/** {@inheritDoc} */
	public org.apache.ignite.IgniteCache<K, V> getCache(String cacheName) {
		return cacheMap == null ? null : cacheMap.get("cacheName");
	}

	@Override
	/** {@inheritDoc} */
	public void put(String cacheName, K key, V val) {
		org.apache.ignite.IgniteCache<K, V> cache = cacheMap.get(cacheName);
		if (cache == null) {
			logger.error("ignite没有该cache，CacheName:{}", cacheName);
		}
		cache.put(key, val);
	}

	@Override
	/** {@inheritDoc} */
	public V get(String cacheName, K key) {
		org.apache.ignite.IgniteCache<K, V> cache = cacheMap.get(cacheName);
		if (cache == null) {
			logger.error("ignite没有该cache，CacheName:{}", cacheName);
		}
		return cache.get(key);
	}

	@Override
	/** {@inheritDoc} */
	public V get(K key) {
		if (cacheMap == null)
			return null;

		V ret = null;
		for (Map.Entry<String, org.apache.ignite.IgniteCache<K, V>> eachCache : cacheMap.entrySet()) {
			org.apache.ignite.IgniteCache<K, V> cache = eachCache.getValue();
			if (cache == null)
				continue;
			ret = cache.get(key);
			if (ret != null)
				break;
		}

		return ret;
	}

}
