/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.ignite.Ignite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.purefun.fams.ace.compent.ApplicationInit;
import com.purefun.fams.framework.core.service.CacheService;
import com.purefun.fams.framework.core.thread.FAMSCoreThreadPool;
import com.purefun.fams.framework.core.util.constant.RedisConstant;
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
	private IgniteCache igniteCache;

	@Autowired
	private Ignite ignite;

	@Autowired
	private CacheService redisCache;

	String TYPE = "IgniteCacheRead";

	int threadSize = 10;// 多线程总数

	int totalSize = 10000;// 查询的数据量总数，多线程时，每个线程分配到的任务=totalSize/threadSize

	@BeforeClass
	public static void init() {
		org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.getInstance();
	}

	@Test
	public void testPerform() throws InterruptedException, ExecutionException {
		String[] typeArray = { "IgniteCacheRead", "RedisRead", "MybatisRead" };
		for (String each : typeArray) {
			TYPE = each;
			testPerformSinleThread();
			testPerformMulThread();
		}
	}

	public void testPerformSinleThread() throws InterruptedException, ExecutionException {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		Future ret = threadPool.submit(new TestThread(totalSize, TYPE, countDownLatch));
		countDownLatch.countDown();
		logger.info("单线程-{}-执行{}次耗时：{} ms", TYPE, totalSize, ret.get());
	}

	public void testPerformMulThread() throws InterruptedException, ExecutionException {
		CountDownLatch countDownLatch = new CountDownLatch(threadSize);
		int eachThreadTaskSize = totalSize / threadSize;
		Future[] ret = new Future[threadSize];
		for (int i = 0; i < threadSize; i++) {
			ret[i] = threadPool.submit(new TestThread(eachThreadTaskSize, TYPE, countDownLatch));
			countDownLatch.countDown();
		}

		long bigestTime = 0;
		for (int i = 0; i < threadSize; i++) {
			bigestTime = bigestTime < (Long) ret[i].get() ? (Long) ret[i].get() : bigestTime;
		}

		logger.info("多线程-{}-总计{}次，分配给{}个线程，全部任务结束耗时：{} ms", TYPE, totalSize, threadSize, bigestTime);
	}

	private class TestThread implements Callable<Long> {
		int count;
		String type;
		CountDownLatch countDownLatch;

		public TestThread(int count, String type, CountDownLatch countDownLatch) {
			this.count = count;
			this.type = type;
			this.countDownLatch = countDownLatch;
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
			countDownLatch.await();
			long start = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				if (type.equalsIgnoreCase("IgniteCacheRead")) {
					igniteCache.get("FamsSecurityBasicinfoBO", "300651.sz");
				} else if (type.equalsIgnoreCase("RedisRead")) {
					Object redisValue = redisCache.globalCacheHGet(
							RedisConstant.RedisCacheTableName.GLOBAL_QUEEN_RDS_STOCK_TABLE, "300651.sz");
				} else if (type.equalsIgnoreCase("MybatisRead")) {
//					FamsSecurityBasicinfoBO bo3 = mapper.selectBySecurityId("300651.sz");
				}
			}
			logger.info("{} 耗时：{} ms", Thread.currentThread().getName(), System.currentTimeMillis() - start);
			return System.currentTimeMillis() - start;
		}

	}
}
