/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.example.ignite.service.datastream;

import java.io.IOException;

import org.apache.ignite.IgniteDataStreamer;
import org.springframework.stereotype.Service;

import com.purefun.fams.core.bo.TestBO;
import com.purefun.fams.core.bo.otw.TestBO_OTW;
import com.purefun.fams.core.bo.tool.BoFactory;
import com.purefun.fams.framework.ignite.datastream.IgniteDataStream;

/**
 * @Classname: TestBODataStream
 * @Description:
 * @author 015979
 * @date 2020-03-17 15:04:23
 */
@Service
public class TestBODataStream extends IgniteDataStream<String, TestBO> {

	@Override
	public boolean receiveDataToStream(IgniteDataStreamer<String, TestBO> stmr) throws IOException {
		stmr.allowOverwrite(true);
		for (int i = 0; i < 100; i++) {
			TestBO_OTW bo = (TestBO_OTW) BoFactory.createBo(TestBO.class, true);
			bo.setAge(i);
			bo.setUsername("test");
			stmr.addData(bo.getUuid(), bo.getBo());
		}
		System.out.println(stmr.cacheName());
		return false;
	}

}
