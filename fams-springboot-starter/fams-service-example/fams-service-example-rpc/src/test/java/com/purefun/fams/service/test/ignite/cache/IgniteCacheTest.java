/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.service.test.ignite.cache;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.purefun.fams.framework.ignite.expose.IgniteCache;

/**
 * @Classname: KafkaTest
 * @Description:
 * @author jianghan
 * @date 2020-02-22 00:09:12
 */
@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IgniteCacheTest {

	@Autowired
	private IgniteCache cache;

//	@SuppressWarnings("unchecked")
//	@Test
//	public void testCachePutAndGet() throws TransactionException, InvalidProtocolBufferException {
//		TestBO_OTW bo = (TestBO_OTW) BoFactory.createBo(TestBO.class, true);
//		bo.setUsername("jianghan");
//		bo.setAge(20);
//		TestBO2_OTW bo2 = (TestBO2_OTW) BoFactory.createBo(TestBO2.class, true);
//		bo2.setWorkid("zhangjunqing");
//		bo2.setHomeaddress("31231");
//
//		cache.put("jianghan", "1", bo.getBuilder().build().toByteArray());
//		cache.put("jianghan", "2", bo2.getBuilder().build().toByteArray());
//		cache.put("jianghan2", "3", 55412);
//
//		TestBO_OTW bo_incache = new TestBO_OTW((byte[]) cache.get("jianghan", "1"));
//		TestBO2_OTW bo2_incache = new TestBO2_OTW((byte[]) cache.get("jianghan", "2"));
//
//		Assert.assertEquals(bo_incache.getUuid(), bo.getUuid());
//		Assert.assertEquals(bo2_incache.getUuid(), bo2.getUuid());
//		Assert.assertEquals(cache.get("jianghan2", "3"), 55412);
//		System.out.println(cache.get("jianghan", "3"));
//	}
}
