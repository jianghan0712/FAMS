package com.purefun.fams.example.ignite.client;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
	private Ignite ignite;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		pool.execute(commondThread);

		// Mark this cluster member as client.
		Ignition.setClientMode(true);
		ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
		executorService2.scheduleAtFixedRate(new QueryThread(), 1000, 2000, TimeUnit.MILLISECONDS);

		List<List<?>> t = cache.getBySQL("TestBO", "select * from TestBO ");
//		List<List<?>> t = stmCache.query(new SqlQuery<Long, Person>(Person.class, sql).setArgs(0, 1000)).getAll();

		for (List<?> each : t) {
			System.out.println(each);
		}
//		System.out.println(each);

	}

	private class QueryThread implements Runnable {

		@Override
		public void run() {

			List<List<?>> t = cache.getBySQL("TestBO", "select * from TestBO where age>10");
//				List<List<?>> t = stmCache.query(new SqlQuery<Long, Person>(Person.class, sql).setArgs(0, 1000)).getAll();
			for (List<?> each : t) {
				System.out.println(each);
			}

//				stmr.addData(bo.getUuid(), bo.getBo());

		}
	}

}
