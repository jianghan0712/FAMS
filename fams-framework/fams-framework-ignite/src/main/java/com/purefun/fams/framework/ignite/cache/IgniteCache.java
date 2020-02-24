/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.transactions.TransactionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: IgniteCache
 * @Description: 对底层igniteCache进行的封装
 * @author jiang
 * @date 2020-02-20 23:59:06
 */
public class IgniteCache<K, V> extends BaseDomain {
	private static final long serialVersionUID = -52240658148084463L;

	private static final Logger logger = LogManager.getLogger(IgniteCache.class);
	/** cacheMap，<key：cacheName ,value：具体的cache> */
	private Map<String, org.apache.ignite.IgniteCache<K, V>> cacheMap = null;

	/**
	 * 初始化各cache
	 * 
	 * @param ignite   主ignite
	 * @param cacheCfg cache config
	 */
	public void initCache(Ignite ignite, CacheConfiguration<K, V>[] cacheCfg) {
		cacheMap = new HashMap<String, org.apache.ignite.IgniteCache<K, V>>();
		for (CacheConfiguration<K, V> each : cacheCfg) {
			org.apache.ignite.IgniteCache<K, V> cache = ignite.getOrCreateCache(each);
			cacheMap.put(cache.getName(), cache);
		}
	}

	/**
	 ** 输出底层cache
	 * 
	 * @MethodName: getCache
	 * @author jianghan
	 * @date 2020-02-23 14:45:33
	 * @param cacheName
	 * @return
	 */
	public org.apache.ignite.IgniteCache<K, V> getCache(String cacheName) {
		return cacheMap == null ? null : cacheMap.get("cacheName");
	}

	/**
	 * 写入缓存
	 * 
	 * @MethodName: put
	 * @author jiang
	 * @date 2020-02-21 01:01:06
	 * @param key
	 * @param val
	 * @throws TransactionException
	 */
	public void put(String cacheName, K key, V val) throws TransactionException {
		org.apache.ignite.IgniteCache<K, V> cache = cacheMap.get(cacheName);
		if (cache == null) {
			logger.error("ignite没有该cache，CacheName:{}", cacheName);
		}
		cache.put(key, val);
	}

	/**
	 * 读取缓存
	 * 
	 * @MethodName: get
	 * @author jiang
	 * @date 2020-02-21 01:01:38
	 * @param key
	 * @return
	 * @throws TransactionException
	 */
	public V get(String cacheName, K key) throws TransactionException {
		org.apache.ignite.IgniteCache<K, V> cache = cacheMap.get(cacheName);
		if (cache == null) {
			logger.error("ignite没有该cache，CacheName:{}", cacheName);
		}
		return cache.get(key);
	}

}
