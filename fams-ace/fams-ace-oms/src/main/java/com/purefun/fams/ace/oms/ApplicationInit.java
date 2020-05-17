package com.purefun.fams.ace.oms;

import org.apache.ignite.Ignite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.core.service.CacheService;
import com.purefun.fams.framework.ignite.cache.IgniteCacheLoaderServiceImpl;
import com.purefun.fams.framework.ignite.expose.IgniteCache;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private IgniteCacheLoaderServiceImpl service;

	@Autowired
	private IgniteCache cache;

	@Autowired
	private Ignite ignite;

	@Autowired
	private CacheService redis;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		service.loadDataFromDB2Cache("com.purefun.fams.framework.core.dao.FamsSecurityBasicinfoMapper", "selectAll",
				"FamsSecurityBasicinfoBO", "securityId");
//		int count = 10000;
//		logger.info("sql start");
//		long startTime = System.currentTimeMillis();
//		for (int i = 0; i < count; i++) {
//			List<List<?>> t = cache.getBySQL("FamsSecurityBasicinfoBO",
//					"select * from FamsSecurityBasicinfoBO where securityId='300632.sz'");
//			System.out.println(i);
//		}
//		long end = System.currentTimeMillis();
//		logger.info("sql end:{}", end - startTime);
//
////		logger.info("query start");
//		startTime = System.currentTimeMillis();
//		for (int i = 0; i < count; i++) {
//			FamsSecurityBasicinfoBO bo = (FamsSecurityBasicinfoBO) cache.get("300632.sz");
//		}
//		end = System.currentTimeMillis();
//		logger.info("query end:{}", end - startTime);
//
//		List<FamsSecurityBasicinfoBO> list = mapper.selectAll();
//		Map<String, FamsSecurityBasicinfoBO> map = new HashMap<String, FamsSecurityBasicinfoBO>();
//		for (FamsSecurityBasicinfoBO each : list) {
//			map.put(each.getSecurityId(), each);
//		}
//		startTime = System.currentTimeMillis();
//		for (int i = 0; i < count; i++) {
//			FamsSecurityBasicinfoBO bo2 = map.get("300632.sz");
//		}
//		end = System.currentTimeMillis();
//		logger.info("map end:{}", end - startTime);
//
//		startTime = System.currentTimeMillis();
//		for (int i = 0; i < count; i++) {
//			FamsSecurityBasicinfoBO bo3 = mapper.selectBySecurityId("300651.sz");
//			System.out.println(i);
//		}
//		end = System.currentTimeMillis();
//		logger.info("mybatis end:{}", end - startTime);
//
//		startTime = System.currentTimeMillis();
//		for (int i = 0; i < count; i++) {
//			Object redisValue = redis.globalCacheHGet(RedisConstant.RedisCacheTableName.GLOBAL_SECURITY_INFO_TABLE,
//					"300651.sz");
//			System.out.println(i);
//		}
//		end = System.currentTimeMillis();
//		logger.info("redis end :{}", end - startTime);
	}

}
