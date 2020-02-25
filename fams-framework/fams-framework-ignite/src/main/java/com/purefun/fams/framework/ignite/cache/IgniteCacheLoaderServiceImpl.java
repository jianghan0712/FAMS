/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicSequence;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.common.util.ClassHandleUtil;
import com.purefun.fams.framework.ignite.expose.IgniteCacheLoaderService;

/**
 * 数据加载实现类
 * 
 * @Classname: IgniteCacheLoaderService
 * @Description:
 * @author jianghan
 * @date 2020-02-25 19:34:46
 */
public abstract class IgniteCacheLoaderServiceImpl implements IgniteCacheLoaderService {
	private static final Logger logger = LogManager.getLogger(IgniteCacheImpl.class);

	@Autowired
	private Ignite ignite;

	@Autowired
	private SqlSession sqlSession;

	@Override
	/** {@inheritDoc} */
	public <V> void loadDataFromDB2Cache(String mapperName, String methodName, String cacheName) {
		try {
			logger.info("---------load all key from DB table:{}---------", mapperName);
			List<V> result = ClassHandleUtil.executeMybatisProxy(sqlSession, mapperName, methodName, null, null);
			loadData2Cache(result, cacheName);
		} catch (RuntimeException | ReflectiveOperationException e) {
			logger.error("从db加载数据到ignite失败，mapper:{},cacheName:{},e:{}", mapperName, cacheName, e);
		}
	}

	@Override
	/** {@inheritDoc} */
	public <V> void loadData2Cache(List<V> list, String cacheName) {
		// 分布式id生成器
		IgniteAtomicSequence sequence = ignite.atomicSequence(cacheName + "PK", 0, true);

		try (IgniteDataStreamer streamer = ignite.dataStreamer(cacheName)) {
			list.stream().forEach(r -> streamer.addData(sequence.incrementAndGet(), r));
			streamer.flush();
			logger.info("加载cache成功，cacheName:{},数据量：{}", cacheName, list.size());
		}
	}

	@Override
	/** {@inheritDoc} */
	public <V> void loadData2CacheBySelf(List<V> list, String cacheName) {
		loadData2Cache(list, cacheName);
	}

}
