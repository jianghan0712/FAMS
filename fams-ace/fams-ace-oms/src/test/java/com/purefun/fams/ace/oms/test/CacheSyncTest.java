/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.test;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.cache.Cache;

import org.apache.ignite.Ignite;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.purefun.fams.ace.key.AceApcAccountKeyEntity;
import com.purefun.fams.ace.key.AceApcCashKeyEntity;
import com.purefun.fams.ace.key.AceApcPositionKeyEntity;
import com.purefun.fams.ace.oms.AceApcAccountBO;
import com.purefun.fams.ace.oms.AceApcCashBO;
import com.purefun.fams.ace.oms.AceApcPositionBO;
import com.purefun.fams.framework.core.service.CacheService;
import com.purefun.fams.framework.ignite.cache.IgniteCacheLoaderServiceImpl;
import com.purefun.fams.framework.ignite.expose.IgniteCache;

/**
 * @Classname: CacheSyncTest
 * @Description:
 * @author jianghan
 * @date 2020-05-26 23:41:12
 */
@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CacheSyncTest {

	@Autowired
	private IgniteCacheLoaderServiceImpl service;

	@Autowired
	private IgniteCache igniteCache;

	@Autowired
	private Ignite ignite;

	@Autowired
	private CacheService redis;

	@BeforeClass
	public static void init() {
		org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.getInstance();
	}

	@Test
	public void testCache() {
		testAccountCache();
		testCashCache();
		testPositionCache();
	}

	/**
	 * @MethodName: testPositionCache
	 * @author jianghan
	 * @date 2020-05-26 23:40:31
	 */
	private void testPositionCache() {
		// TODO Auto-generated method stub
		AceApcPositionBO bo = new AceApcPositionBO();
		bo.setAccount("000001");
		bo.setExch("sh");
		bo.setSecurityName("浦东银行");
		bo.setSecurityId("600000");
		bo.setAvailableQty(2000l);
		bo.setTotalQty(2000l);
		bo.setAveCostPrice(new BigDecimal("12.65"));
		bo.setFreezeQty(0l);
		bo.setOnwayQty(0l);
		AceApcPositionKeyEntity key = new AceApcPositionKeyEntity(bo.getAccount(), bo.getSecurityId(), bo.getExch());

		org.apache.ignite.IgniteCache<AceApcPositionKeyEntity, AceApcPositionBO> cashCache = igniteCache
				.getCache("PositionCache");
		cashCache.put(key, bo);

		Iterator<Cache.Entry<AceApcPositionKeyEntity, AceApcPositionBO>> it = cashCache.iterator();
		while (it.hasNext()) {
			Cache.Entry<AceApcPositionKeyEntity, AceApcPositionBO> each = it.next();
			System.out.println("key=" + each.getKey() + " , value=" + each.getValue());
		}
	}

	/**
	 * @MethodName: testAccountCache
	 * @author jianghan
	 * @date 2020-05-26 23:35:58
	 */
	private void testAccountCache() {
		// TODO Auto-generated method stub
		AceApcAccountBO bo = new AceApcAccountBO();
		bo.setAccount("000004");
		bo.setAccountJobid("015979");
		bo.setAccountLevel(1);
		bo.setAccountName("江汉");
		bo.setAccountType("common");
		AceApcAccountKeyEntity key = new AceApcAccountKeyEntity(bo.getAccount());
		org.apache.ignite.IgniteCache<AceApcAccountKeyEntity, AceApcAccountBO> cashCache = igniteCache
				.getCache("AccountCache");
		cashCache.put(key, bo);

		Iterator<Cache.Entry<AceApcAccountKeyEntity, AceApcAccountBO>> it = cashCache.iterator();
		while (it.hasNext()) {
			Cache.Entry<AceApcAccountKeyEntity, AceApcAccountBO> each = it.next();
			System.out.println("key=" + each.getKey() + " , value=" + each.getValue());
		}
	}

	/**
	 * @MethodName: testCashCache
	 * @author jianghan
	 * @date 2020-05-26 23:35:39
	 */
	private void testCashCache() {
		// TODO Auto-generated method stub
		AceApcCashBO cashBo = new AceApcCashBO();
		cashBo.setAccount("000001");
		cashBo.setCurrency("USD");
		cashBo.setAvailableAmount(new BigDecimal("1234567.00"));
		cashBo.setFreezeAmount(new BigDecimal("123456.00"));
		cashBo.setTotalAmount(new BigDecimal("123456.00"));
		cashBo.setOnwayAmount(new BigDecimal("123456.00"));
		AceApcCashKeyEntity cashKey = new AceApcCashKeyEntity(cashBo.getAccount(), cashBo.getCurrency());

		org.apache.ignite.IgniteCache<AceApcCashKeyEntity, AceApcCashBO> cashCache = igniteCache.getCache("CashCache");
		cashCache.replace(cashKey, cashBo);

		Iterator<Cache.Entry<AceApcCashKeyEntity, AceApcCashBO>> it = cashCache.iterator();
		while (it.hasNext()) {
			Cache.Entry<AceApcCashKeyEntity, AceApcCashBO> each = it.next();
			System.out.println("key=" + each.getKey() + " , value=" + each.getValue());
		}
	}
}
