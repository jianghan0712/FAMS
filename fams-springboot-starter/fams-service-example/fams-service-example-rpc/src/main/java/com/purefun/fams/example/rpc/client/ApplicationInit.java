package com.purefun.fams.example.rpc.client;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.example.rpc.client.controller.RemoteServiceImpl;
import com.purefun.fams.framework.common.ace.oms.apc.request.CashRequest;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private RemoteServiceImpl service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(service.hello());

		CashRequest request = new CashRequest("000001", "USD", new BigDecimal(100.00));
		System.out.println(service.freeze(request));

//		
//
//		;
	}

}
