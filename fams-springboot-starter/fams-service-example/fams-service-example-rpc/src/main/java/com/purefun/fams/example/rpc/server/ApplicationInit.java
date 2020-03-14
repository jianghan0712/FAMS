package com.purefun.fams.example.rpc.server;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.core.domain.ServiceInstance;
import com.purefun.fams.framework.core.service.CacheService;
import com.purefun.fams.framework.core.thread.CommondThread;
import com.purefun.fams.framework.core.thread.FAMSCoreThreadPool;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private FAMSCoreThreadPool pool;

	@Autowired
	private CommondThread commondThread;

	@Autowired
	private ServiceInstance instance;

	@Autowired
	private CacheService cache;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		pool.execute(commondThread);

		cache.set("test", instance);
		System.out.println(cache.get("test"));

//		cache.hset("test-map", "mapKey", "456");
		cache.hset("test-map", "mapKey2", "456");
		System.out.println(cache.hHasKey("test-map", "mapKey2"));
		System.out.println(cache.hHasKey("test-map", "mapKey3"));
		System.out.println(cache.hget("test-map", "mapKey2"));

		cache.hset("test-map", "mapKey4", instance);
		System.out.println(cache.hget("test-map", "mapKey4"));

		cache.hdel("test-map", "mapKey4");
		cache.sSet("test-set", instance);
		System.out.println(cache.get("test-set"));
		List<String> list = new LinkedList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		cache.lSet("test-list", "1", "2", "3", "4", "5");
		System.out.println(cache.lGetIndex("test-list", -2));
		System.out.println(cache.rifhtPop("test-list"));
	}

}
