/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.purefun.fams.framework.ignite.expose.IgniteCache;

/**
 * @Classname: IgniteCache
 * @Description: 对底层igniteCache进行的封装，对于一个service实例，cacheMap会维护好cache的映射关系
 * @author jiang
 * @date 2020-02-20 23:59:06
 */
public class IgniteCacheImpl<K, V> implements IgniteCache<K, V> {
	private static final long serialVersionUID = -52240658148084463L;

	private static final Logger logger = LogManager.getLogger(IgniteCacheImpl.class);

	private Ignite ignite;

	/** cacheMap，<key：cacheName ,value：具体的cache> */
	private Map<String, org.apache.ignite.IgniteCache<K, V>> cacheMap = new HashMap<String, org.apache.ignite.IgniteCache<K, V>>();

	@Override
	/** {@inheritDoc} */
	public void initCache(Ignite ignite, List<CacheConfiguration<K, V>> cacheCfg) {
		this.ignite = ignite;
		if (cacheCfg == null || cacheCfg.size() == 0) {
			return;
		}
		for (CacheConfiguration<K, V> each : cacheCfg) {
			org.apache.ignite.IgniteCache<K, V> cache = ignite.getOrCreateCache(each);
			if (each.getCacheStoreFactory() != null) {
				cache.loadCache(null);
			}

			cacheMap.put(cache.getName(), cache);
		}
	}

	@Override
	/** {@inheritDoc} */
	public org.apache.ignite.IgniteCache<K, V> getCache(String cacheName) {
		return cacheMap == null ? null : cacheMap.get(cacheName);
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

	@Override
	/** {@inheritDoc} */
	public Ignite getIgnite() {
		return ignite;
	}

	@Override
	/** {@inheritDoc} */
	public boolean contain(String cacheName) {
		return cacheMap.containsKey(cacheName);
	}

	@Override
	public List<List<?>> getAll(String cacheName) {
		if (cacheMap == null)
			return null;
		org.apache.ignite.IgniteCache<K, V> cache = getCache(cacheName);
		if (cache == null)
			return null;

		List<List<?>> ret = null;
		SqlFieldsQuery sql = new SqlFieldsQuery("select * from " + cacheName, true /* collocated */
		);
		ret = cache.query(sql).getAll();

		return ret;
	}

	@Override
	public List<List<?>> getBySQL(String cacheName, String sql) {
		if (cacheMap == null)
			return null;
		org.apache.ignite.IgniteCache<K, V> cache = getCache(cacheName);
		if (cache == null)
			return null;
		SqlFieldsQuery querySql = new SqlFieldsQuery(sql, true);

		List<List<?>> ret = cache.query(querySql).getAll();
		return ret;
	}

}
