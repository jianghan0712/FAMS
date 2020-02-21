/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.transactions.TransactionException;

/**
 * @Classname: IgniteCache
 * @Description: 对底层igniteCache进行的封装
 * @author jiang
 * @date 2020-02-20 23:59:06
 */
public class IgniteCache<K,V> {
	/** ignite主线程*/
	private Ignite ignite = null;
	/** 实际的cache**/
	private org.apache.ignite.IgniteCache<K,V> cache = null;
	
	/**
	 * 
	 * @param ignite  	主ignite
	 * @param cacheCfg	cache config
	 */
	public IgniteCache(Ignite ignite, CacheConfiguration<K,V> cacheCfg) {
		this.ignite = ignite;
		cache = ignite.getOrCreateCache(cacheCfg);
	}
	
	/**
	 * 写入缓存
	 * @MethodName: put
	 * @author jiang
	 * @date 2020-02-21 01:01:06
	 * @param key
	 * @param val
	 * @throws TransactionException
	 */
	public void put(K key, V val) throws TransactionException{
		cache.put(key, val);
	}
	
	/** 
	 * 读取缓存
	 * @MethodName: get
	 * @author jiang
	 * @date 2020-02-21 01:01:38
	 * @param key
	 * @return
	 * @throws TransactionException
	 */
	public V get(K key) throws TransactionException{
		return cache.get(key);
	}
	
}
