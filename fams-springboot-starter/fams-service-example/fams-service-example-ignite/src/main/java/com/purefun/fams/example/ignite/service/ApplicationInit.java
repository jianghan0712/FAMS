package com.purefun.fams.example.ignite.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.core.bo.TestBO;
import com.purefun.fams.core.bo.otw.TestBO_OTW;
import com.purefun.fams.core.bo.tool.BoFactory;
import com.purefun.fams.example.ignite.service.datastream.TestBODataStream;
import com.purefun.fams.framework.core.communication.FAMSProducer;
import com.purefun.fams.framework.core.thread.CommondThread;
import com.purefun.fams.framework.core.thread.FAMSCoreThreadPool;
import com.purefun.fams.framework.ignite.expose.IgniteCache;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private CommondThread commondThread;

	@Autowired
	private FAMSCoreThreadPool pool;

	@Autowired
	private IgniteCache cache;

	@Autowired
	private TestBODataStream testDataStream;

	@Autowired
	private FAMSProducer publisher;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		pool.execute(commondThread);
//		testDataStream.start("TestBO");
//		testDataStream.receiveDataToStream(testDataStream.getStmr());

		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(new PublishThread(), 1000, 3000, TimeUnit.MILLISECONDS);

//		List<List<?>> t = cache.getAll("TestBO");
//		for (List<?> each : t) {
//			System.out.println(each);
//		}

	}

	private class PublishThread implements Runnable {
		int i = 0;

		@Override
		public void run() {

			TestBO_OTW bo = (TestBO_OTW) BoFactory.createBo(TestBO.class);
			bo.setAge(i++);
			bo.setUsername("test");
			publisher.publish(bo);
//			List<List<?>> t = cache.getAll("TestBO");
//			for (List<?> each : t) {
//				System.out.println(each);
//			}

		}
	}

}
