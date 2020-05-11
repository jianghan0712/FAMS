package com.purefun.fams.framework.service.monitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.purefun.fams.core.bo.tool.BoFactory;
import com.purefun.fams.framework.core.dao.FamsSecurityBasicinfoMapper;
import com.purefun.fams.framework.core.service.impl.RedisCacheLoaderServiceImpl;
import com.purefun.fams.framework.core.util.constant.RedisConstant;
import com.purefun.fams.queen.rds.FamsSecurityBasicinfoBO;
import com.purefun.fams.queen.rds.otw.FamsSecurityBasicinfoBO_OTW;

@Component
public class FAMSApplicationRunner implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(FAMSApplicationRunner.class);

	@Autowired
	private RedisCacheLoaderServiceImpl cacheLoader;
	@Autowired
	private FamsSecurityBasicinfoMapper mapper;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		cacheLoader.loadDataFromDB2Cache("com.purefun.fams.framework.core.dao.FamsSecurityBasicinfoMapper", "selectAll",
				RedisConstant.RedisCacheTableName.GLOBAL_SECURITY_INFO_TABLE, "securityId");
//		FamsSecurityBasicinfoBO bo = new FamsSecurityBasicinfoBO();
//		bo.setSecurityId("test");
//		bo.setExch("sh");
//		bo.setTsId("test");
//
//		mapper.insertSelective(bo);

		FamsSecurityBasicinfoBO_OTW bo = (FamsSecurityBasicinfoBO_OTW) BoFactory.createBo(FamsSecurityBasicinfoBO.class,
				false);
		System.out.println(bo.toString());

	}

}
