package com.purefun.fams.example.monitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.core.service.RedisService;
import com.purefun.fams.framework.core.service.impl.GlobalParamServiceImpl;
import com.purefun.fams.framework.core.service.impl.RedisCacheLoaderServiceImpl;
import com.purefun.fams.framework.core.util.constant.RedisConstant;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private RedisCacheLoaderServiceImpl service;
	@Autowired
	private RedisService redisOp;
	@Autowired
	private GlobalParamServiceImpl configService;
//
//	@Autowired
//	private IgniteCacheLoaderServiceImpl igniteCacheLoader;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.print(1234);
		service.loadDataFromDB2Cache("com.purefun.fams.framework.core.dao.FamsGlobalParamMapper", "getAllValue",
				RedisConstant.RedisCacheTableName.GLOBAL_PARAM_TABLE, "paramScope", "paramName");

////		service.loadDataFromDB2Cache("com.purefun.fams.framework.core.dao.FamsParamMapper", "selectAll", "student");
//
		System.out.println(configService.getParamValueWithCache("fams", "trade_date"));
//		System.out.println();
//		System.out.println(configService.getParamValueWithCache("fams", "trade_date"));
//
//		igniteCacheLoader.loadDataFromDB2Cache("com.purefun.fams.framework.core.dao.FamsGlobalParamMapper",
//				"getAllValue", "student", "paramScope", "paramName");
	}

}
