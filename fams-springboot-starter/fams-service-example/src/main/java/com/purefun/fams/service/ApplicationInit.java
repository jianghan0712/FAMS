package com.purefun.fams.service;

import org.apache.ignite.Ignite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.ignite.cache.IgniteCache;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private IgniteCache cache;

	@Autowired
	private Ignite ignite;

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		TestBO_OTW bo = (TestBO_OTW) BoFactory.createBo(TestBO.class);
//		bo.setUsername("jianghan");
//		bo.setAge("20");
//		TestBO2_OTW bo2 = (TestBO2_OTW) BoFactory.createBo(TestBO2.class);
//		bo2.setWorkid("zhangjunqing");
//		bo2.setHomeaddress("31231");
//
//		cache.put("1", bo.getBuilder().build().toByteArray());
//		cache.put("2", bo2.getBuilder().build().toByteArray());
//		cache.put("3", 55412);
		int keyCnt = 10;

//		for (int i = 0; i < keyCnt; i++)
//			cache.put(i, Integer.toString(i));

//		IgniteCompute compute = ignite.compute(ignite.cluster().forRemotes());
//		compute.broadcast(job)
//		compute.broadcast(() -> {
//		for (int i = 0; i < keyCnt; i++)
//			System.out.println("Got [key=" + i + ", val=" + cache.get(i) + "] ");
//		});
	}

}
