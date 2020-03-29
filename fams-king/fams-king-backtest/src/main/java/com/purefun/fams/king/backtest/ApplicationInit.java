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
import com.purefun.fams.king.component.strategy.DoubleMAStrategy;
import com.purefun.fams.king.component.strategy.analysis.TradeAnalysisContainer;
import com.purefun.fams.king.constant.EventTypeEnum;
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

	@Autowired
	private DoubleMAStrategy strategy;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		pool.execute(commondThread);

		// 给策略容器添加分析容器
		strategy.register(new EventTypeEnum[] { EventTypeEnum.EVENT_TRADE_NEW_ORDER,
				EventTypeEnum.EVENT_TRADE_CANCEL_ORDER, EventTypeEnum.EVENT_TRADE_INIT, EventTypeEnum.EVENT_TRADE_START,
				EventTypeEnum.EVENT_TRADE_END, }, new TradeAnalysisContainer());

		mdContainer.addObserver(strategy);
		MDRequest mdRequest = new MDRequest();
		mdRequest.setIndustry("银行");
		mdRequest.setMdType(MDTypeEnum.BAR.getCode());
		mdRequest.setRequestType(KingConstant.RunType.BACKTEST);
		mdRequest.setStockCode("000001.SZ");
		mdRequest.setStartDate("20190101");

		mdContainer.subscribe(mdRequest);
		mdContainer.runHisBar();

	}

}
