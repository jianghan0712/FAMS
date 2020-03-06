package com.purefun.fams.example.rpc.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.core.thread.CommondThread;
import com.purefun.fams.framework.core.thread.FAMSCoreThreadPool;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private FAMSCoreThreadPool pool;

	@Autowired
	private CommondThread commondThread;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		pool.execute(commondThread);

//		ServiceExitThread t = new ServiceExitThread("jianghan");
//		t.getThread().start();

	}

}
