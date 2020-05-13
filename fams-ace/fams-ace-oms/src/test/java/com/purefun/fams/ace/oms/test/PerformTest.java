/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.ignite.Ignite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.purefun.fams.ace.oms.ApplicationInit;
import com.purefun.fams.framework.core.dao.FamsSecurityBasicinfoMapper;
import com.purefun.fams.framework.core.service.CacheService;
import com.purefun.fams.framework.core.thread.FAMSCoreThreadPool;
import com.purefun.fams.framework.ignite.cache.IgniteCacheLoaderServiceImpl;
import com.purefun.fams.framework.ignite.expose.IgniteCache;

/**
 * @Classname: PerformTest
 * @Description:
 * @author jianghan
 * @date 2020-05-13 23:12:35
 */
@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PerformTest {
	@Autowired
	private FAMSCoreThreadPool threadPool;
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private IgniteCacheLoaderServiceImpl service;

	@Autowired
	private FamsSecurityBasicinfoMapper mapper;

	@Autowired
	private IgniteCache cache;

	@Autowired
	private Ignite ignite;

	@Autowired
	private CacheService redis;

	@org.junit.Test
	public void testPerformSinleThread() throws InterruptedException, ExecutionException {
		Future<?> ret = threadPool.submit(new TestThread(10000, "IgniteCacheRead"));
		logger.info("单线程IgniteCacheRead执行{}次耗时：{}", 10000, ret.get());
	}

	private class TestThread implements Callable<Long> {
		int count;
		String type;

		public TestThread(int count, String type) {
			this.count = count;
			this.type = type;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see java.util.concurrent.Callable#call()
		 * @return
		 * @throws Exception
		 */

		@Override
		public Long call() throws Exception {
			// TODO Auto-generated method stub
			long start = System.currentTimeMillis();
			if (type.equalsIgnoreCase("IgniteCacheRead")) {
				for (int i = 0; i < count; i++) {
					cache.get("FamsSecurityBasicinfoBO", "000001.sz");
				}
			} else if (type.equalsIgnoreCase("IgniteCacheWrite")) {

			}

			logger.info("{} 耗时：{}", Thread.currentThread().getName(), System.currentTimeMillis() - start);
			return System.currentTimeMillis() - start;
		}

	}
}
