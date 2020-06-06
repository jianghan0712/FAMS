package com.purefun.fams.framework.core.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;

import com.purefun.fams.framework.common.util.ErrorCodeUtil;
import com.purefun.fams.framework.core.thread.CommondThread;
import com.purefun.fams.framework.core.thread.FAMSCoreThreadPool;

public class FAMSApplicationRunner implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(FAMSApplicationRunner.class);
	@Autowired
	private FAMSCoreThreadPool pool;

	@Autowired
	private CommondThread commondThread;

	@Value("${fams.framework.error}")
	private Resource errorCode;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		pool.execute(commondThread);
		ErrorCodeUtil.createMap(errorCode.getInputStream());
	}

}
