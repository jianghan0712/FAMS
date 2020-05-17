package com.purefun.fams.ace.mdc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.ace.mdc.service.impl.SinaMarketDataServiceImpl;

@Component
public class MDCApplicationRunner implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(MDCApplicationRunner.class);

	@Autowired
	private SinaMarketDataServiceImpl sinaService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		sinaService.subMarketDate();

	}

}
