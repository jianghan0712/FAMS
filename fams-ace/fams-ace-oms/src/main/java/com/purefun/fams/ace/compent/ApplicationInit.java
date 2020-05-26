package com.purefun.fams.ace.compent;

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
	private IgniteCache igniteCache;

	@Autowired
	private Ignite ignite;

	@Autowired
	private CacheService redis;

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}

}
