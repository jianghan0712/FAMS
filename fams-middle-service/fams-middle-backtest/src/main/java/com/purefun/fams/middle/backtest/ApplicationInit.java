package com.purefun.fams.middle.backtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.core.thread.CommondThread;
import com.purefun.fams.framework.core.thread.FAMSCoreThreadPool;
import com.purefun.fams.middle.constant.EventTypeEnum;
import com.purefun.fams.middle.mdcontainer.MarketDataContainer;
import com.purefun.fams.middle.strategy.DoubleMAStrategy;
import com.purefun.fams.middle.strategy.analysis.TradeAnalysisContainer;

@Component
public class ApplicationInit implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(ApplicationInit.class);

	@Autowired
	private FAMSCoreThreadPool pool;

	@Autowired
	private CommondThread commondThread;

	@Autowired
	private MarketDataContainer mdContainer;

	@Autowired
	private DoubleMAStrategy strategy;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		pool.execute(commondThread);

		strategy.register(EventTypeEnum.EVENT_TRADE_NEW_ORDER, new TradeAnalysisContainer());
		mdContainer.addObserver(strategy);
		mdContainer.loadData("银行", null, "000001.SZ", "20190101", null);
		mdContainer.runHisBar();

//		

	}

}
