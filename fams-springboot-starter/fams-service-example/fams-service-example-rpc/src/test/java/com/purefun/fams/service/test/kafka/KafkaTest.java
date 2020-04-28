/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.service.test.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.purefun.fams.core.bo.TestBO;
import com.purefun.fams.core.bo.TestBO2;
import com.purefun.fams.core.bo.otw.TestBO2_OTW;
import com.purefun.fams.core.bo.otw.TestBO_OTW;
import com.purefun.fams.core.bo.tool.BoFactory;
import com.purefun.fams.framework.core.communication.FAMSProducer;

/**
 * @Classname: KafkaTest
 * @Description:
 * @author jianghan
 * @date 2020-02-22 00:09:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class KafkaTest {
	@Autowired
	private FAMSProducer producer;

	@Test
	public void testPublish() {
		TestBO_OTW bo = (TestBO_OTW) BoFactory.createBo(TestBO.class, false);
		bo.setUsername("jianghan");
		bo.setAge(20);
		TestBO2_OTW bo2 = (TestBO2_OTW) BoFactory.createBo(TestBO2.class, false);
		bo2.setWorkid("zhangjunqing");
		bo2.setHomeaddress("31231");

		producer.publish(bo);
		producer.publish(bo2);
	}
}
