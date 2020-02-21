package com.purefun.fams.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.core.communication.FAMSProducer;
import com.purefun.fams.framework.core.communication.KafkaConsumer;
import com.purefun.fams.framework.core.util.SpringUtil;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private SpringUtil util;

	@Autowired
	private FAMSProducer producer;

	@Autowired
	private KafkaConsumer consumer;
	
	@Autowired
	private com.purefun.fams.framework.ignite.cache.IgniteCache cache;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("serviceName:{}", cache);
		
		cache.put("1", "jianghan");
		cache.put("2", "cuiqing");
		
		logger.info("1:{}", cache.get("1"));
		

//		TestBO_OTW bo = (TestBO_OTW) BoFactory.createBo(TestBO.class);
//		bo.setUsername("jianghan");
//		bo.setAge("20");
//
//		producer.publish(bo);
//
//		TestBO2_OTW bo2 = (TestBO2_OTW) BoFactory.createBo(TestBO2.class);
//		bo2.setWorkid("zhangjunqing");
//		bo2.setHomeaddress("31231");
//
//		producer.publish(bo2);
	}

}
