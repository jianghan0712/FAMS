package com.purefun.fams.queen.rds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.core.util.constant.RedisConstant;
import com.purefun.fams.queen.rds.service.impl.StockRDSImpl;

@Component
public class RDSApplicationRunner implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(RDSApplicationRunner.class);

	@Autowired
	private StockRDSImpl stockRDS;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		stockRDS.load2cache("com.purefun.fams.queen.dao.QueenRdsStockMapper", "selectAll",
				RedisConstant.RedisCacheTableName.GLOBAL_QUEEN_RDS_STOCK_TABLE, "securityId");

	}

}
