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

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private IgniteCacheLoaderServiceImpl service;

	@Autowired
	private Ignite ignite;

	@Autowired
	private CacheService redis;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		service.loadDataFromDB2Cache("com.purefun.fams.ace.dao.AceApcCashMapper", "selectAll", "AceApcCashBO",
				"account", "currency");
		service.loadDataFromDB2Cache("com.purefun.fams.ace.dao.AceApcAccountMapper", "selectAll", "AceApcAccountBO",
				"account");
		service.loadDataFromDB2Cache("com.purefun.fams.ace.dao.AceApcPositionMapper", "selectAll", "AceApcPositionBO",
				"account", "securityId");

//		System.out.println(datasource);

//		try (Ignite ignite = Ignition.start("classpath:ignite-oms2.xml")) {
//			// Load data from person table into PersonCache.

//		ignite.getOrCreateCache("AccountCache").loadCache(null);
//		AceApcAccountBO bo = new AceApcAccountBO();
//		bo.setAccount("000003");
//		bo.setAccountType("common");
//		bo.setAccountJobid("015979");
//		bo.setAccountName("jianghan-test");
//		bo.setAccountLevel(2);
//		ignite.cache("AccountCache").put("000003", bo);
////			AceApcAccountBO bo = (AceApcAccountBO) ignite.cache("AccountCache").get("000001");
////			bo.setAccountLevel(3);
////			ignite.cache("AccountCache").put(2, bo);
//		System.out.println(ignite.cache("AccountCache").get("000003"));
//		}

	}

}
