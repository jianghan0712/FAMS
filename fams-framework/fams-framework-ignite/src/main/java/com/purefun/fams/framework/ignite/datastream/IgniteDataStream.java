/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.datastream;

import java.io.IOException;
import java.util.Collections;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.exception.FAMSException;
import com.purefun.fams.framework.core.service.ExposeService;
import com.purefun.fams.framework.ignite.expose.IgniteCache;

/**
 * @Classname: IgniteDataStream
 * @Description:
 * @author 015979
 * @date 2020-03-17 11:31:24
 */
public abstract class IgniteDataStream<K, V> implements ExposeService {

	@Autowired
	protected IgniteCache cache;

	@Autowired
	protected Ignite ignite;

	protected org.apache.ignite.IgniteDataStreamer<K, V> stmr;

	/**
	 * 通过设立一个新的cache启动datastream服务
	 * 
	 * @MethodName: start
	 * @author 015979
	 * @date 2020-03-17 13:45:17
	 * @param cacheConfig
	 * @throws IOException
	 */
	public void start(CacheConfiguration<K, V> cacheConfig) throws IOException {
		cache.initCache(ignite, Collections.singletonList(cacheConfig));
		this.stmr = ignite.dataStreamer(cacheConfig.getName());
		try (IgniteDataStreamer<K, V> stmr = ignite.dataStreamer(cacheConfig.getName())) {
			receiveDataToStream(stmr);
		}
	}

	/**
	 * 使用已有的cache启动datasream服务
	 * 
	 * @MethodName: start
	 * @author 015979
	 * @date 2020-03-17 13:45:42
	 * @param cacheName
	 * @throws IOException
	 */
	public void start(String cacheName) throws FAMSException, IOException {
		if (!cache.contain(cacheName))
			throw new FAMSException(ErrorCodeEnum.IGNITE_NO_CACHE);
		stmr = ignite.dataStreamer(cacheName);
		receiveDataToStream(stmr);
	}

	/**
	 * 定义如何将数据流流向stream
	 * 
	 * @MethodName: receiveDataToStream
	 * @author 015979
	 * @date 2020-03-17 13:47:09
	 * @param stmr
	 * @return
	 * @throws IOException
	 */
	public abstract boolean receiveDataToStream(IgniteDataStreamer<K, V> stmr) throws IOException;

	public IgniteCache getCache() {
		return cache;
	}

	public void setCache(IgniteCache cache) {
		this.cache = cache;
	}

	public Ignite getIgnite() {
		return ignite;
	}

	public void setIgnite(Ignite ignite) {
		this.ignite = ignite;
	}

	public org.apache.ignite.IgniteDataStreamer<K, V> getStmr() {
		return stmr;
	}

	public void setStmr(org.apache.ignite.IgniteDataStreamer<K, V> stmr) {
		this.stmr = stmr;
	}

}
