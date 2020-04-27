package com.purefun.fams.king.backtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.core.thread.CommondThread;
import com.purefun.fams.framework.core.thread.FAMSCoreThreadPool;
import com.purefun.fams.king.component.mdcontainer.MarketDataContainer;
import com.purefun.fams.king.component.strategy.DoubleRSIStrategy;
import com.purefun.fams.king.constant.KingConstant;
import com.purefun.fams.king.constant.MDTypeEnum;
import com.purefun.fams.king.request.MDRequest;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private FAMSCoreThreadPool pool;

	@Autowired
	private CommondThread commondThread;

	@Autowired
	private MarketDataContainer mdContainer;

//	@Autowired
//	private DoubleMAStrategy strategy;

	@Autowired
	private DoubleRSIStrategy strategy;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		pool.execute(commondThread);

		mdContainer.addObserver(strategy);
		MDRequest mdRequest = new MDRequest();
		mdRequest.setIndustry("银行");
		mdRequest.setMdType(MDTypeEnum.BAR.getCode());
		mdRequest.setRequestType(KingConstant.RunType.BACKTEST);
		mdRequest.setStockCode("000001.SZ");
		mdRequest.setStartDate("20200101");

		mdContainer.subscribe(mdRequest);
		mdContainer.runHisBar();

	}

}
