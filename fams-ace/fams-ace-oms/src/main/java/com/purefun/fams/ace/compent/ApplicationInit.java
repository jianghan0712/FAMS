package com.purefun.fams.ace.compent;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.ace.oms.apc.request.DealRequest;
import com.purefun.fams.ace.oms.apc.service.CashService;
import com.purefun.fams.ace.oms.apc.service.PositionService;
import com.purefun.fams.ace.oms.apc.service.PostTradeService;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private PositionService positionService;

	@Autowired
	private CashService cashService;

	@Autowired
	private PostTradeService postService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		CashRequest request = new CashRequest("000001", "CNY", new BigDecimal("200.60"));
//		CashRespond respond = cashService.unfreezeCash(request);
//		System.out.println(respond);

		DealRequest request = new DealRequest();
		request.setAccount("000001");
		request.setExch("sh");
		request.setSecurityId("600000");
		request.setSecurityName("浦东银行");
		request.setCurrency("CNY");
		request.setPrice(new BigDecimal("10.00"));
		request.setVolume(1000l);
		request.setCash(new BigDecimal("10000.00"));

		postService.cutCashAndAddPosition(request);
	}

}
