/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.mdc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.purefun.fams.ace.mdc.config.SinaConfig;
import com.purefun.fams.ace.mdc.service.SinaMarketDataService;
import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.core.bo.tool.BoFactory;
import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.exception.FAMSException;
import com.purefun.fams.framework.core.communication.FAMSProducer;
import com.purefun.fams.framework.core.dao.FamsSecurityBasicinfoMapper;
import com.purefun.fams.framework.core.domain.FamsSecurityBasicinfo;
import com.purefun.fams.framework.core.http.LogInterceptor;
import com.purefun.fams.md.MdStockSnapshotBO;
import com.purefun.fams.md.otw.MdStockSnapshotBO_OTW;

/**
 * @Classname: SinaMarketDataServiceImpl
 * @Description:
 * @author jianghan
 * @date 2020-04-26 16:52:19
 */
@Service
public class SinaMarketDataServiceImpl implements SinaMarketDataService {
	private static final Logger logger = LogManager.getLogger(SinaMarketDataServiceImpl.class);
	/** 向sina发起restful请求 */
	private RestTemplate sinaTemplate;

	@Autowired
	private SinaConfig config;

	@Autowired
	private FamsSecurityBasicinfoMapper mapper;

	private HashSet<String> stockSet = new HashSet<String>();

	@Autowired
	private FAMSProducer producer;

	ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

	long size = 0l;
	long size2 = 0l;

	@PostConstruct
	public void init() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new LogInterceptor());
		this.sinaTemplate = restTemplate;
		start();
	}

	/**
	 * 
	 * @MethodName: initSet
	 * @author jianghan
	 * @date 2020-04-28 22:37:38
	 * @param stockSet 自定义codelist，如果没有指定，则订阅全市场行情
	 */
	public void initSet(HashSet<String> stockSet) {
		if (stockSet == null || stockSet.size() == 0) {
			List<FamsSecurityBasicinfo> list = mapper.selectAll();
			for (FamsSecurityBasicinfo each : list) {
				this.stockSet.add(each.getExch() + each.getExchangeId());
			}
		} else {
			this.stockSet = stockSet;
		}
	}

	/**
	 * 启动服务
	 * 
	 * @MethodName: start
	 * @author jianghan
	 * @date 2020-04-28 22:31:49
	 */
	public void start() {
		pool.scheduleAtFixedRate(new QueryMDThread(), 0, config.getInterval(), TimeUnit.SECONDS);
	}

	/**
	 ** 向新浪订阅行情
	 * 
	 * @MethodName: subMarketDate
	 * @author jianghan
	 * @date 2020-04-27 21:08:44
	 */
	public void subMarketDate() {
		size = 0;
		size2 = 0;
		StringBuffer stockList = null;
		/** 如果调用方还没有初始化codelist，则默认为订阅全部行情 */
		if (stockSet == null || stockSet.size() == 0) {
			initSet(null);
		}

		Iterator<String> it = stockSet.iterator();
		/** 订阅行情，stockSet中的都会请求，也可以使用自定义的方式 ，如果要自定义 */
		while (it.hasNext()) {
			stockList = new StringBuffer();
			int i = 0;
			/** 一次请求maxnum只 */
			while (it.hasNext() && i++ < config.getMaxnum()) {
				stockList.append(it.next()).append(CommonUtil.CharUtil.comma);
			}

			try {
				ResponseEntity<String> response = sinaTemplate
						.getForEntity(config.getUrl() + "list=" + stockList.toString(), String.class);
				if (response == null) {
					throw new FAMSException(ErrorCodeEnum.ACE_MARKETDATA_SUBSCRIBE_FAIL);
				}
				String body[] = response.getBody().trim().replace("\n", "").replace("\"", "").replace("=", ",")
						.split(";");
				for (String e : body) {
					parseMDAndPublish(e.substring(e.indexOf("str_") + 4).split(","));
				}
				logger.info("条数：{}，bo字节长度：{}，json字节长度：{}", body.length, size, size2);
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("行情请求失败：{}", e.getMessage());
			}
		}

	}

	/**
	 * @MethodName: parseMDAndPublish
	 * @author jianghan
	 * @param eachDetail
	 * @date 2020-04-27 20:10:52
	 */
	private void parseMDAndPublish(String[] eachDetail) {
		// TODO Auto-generated method stub
		MdStockSnapshotBO_OTW bo = (MdStockSnapshotBO_OTW) BoFactory.createBo(MdStockSnapshotBO.class, false);
		int i = 0;
		String code = eachDetail[i].substring(2);
		String exch = eachDetail[i].substring(0, 2).toUpperCase();
		bo.setSecurity_code(code + CommonUtil.CharUtil.point + exch);
		bo.setExch(exch);
		i++; // 跳过中文名称
		bo.setOpen(Double.valueOf(eachDetail[++i]));
		bo.setPreClose(Double.valueOf(eachDetail[++i]));
		bo.setLastPrice(Double.valueOf(eachDetail[++i]));
		bo.setHigh(Double.valueOf(eachDetail[++i]));
		bo.setLow(Double.valueOf(eachDetail[++i]));
		i = i + 2;// 跳过 竞买价 和 竞卖价
		bo.setTotalVolume(Long.valueOf(eachDetail[++i]));
		bo.setTotalAmont(new BigDecimal(eachDetail[++i]).doubleValue());

		List<Long> buyVolumeList = new ArrayList<Long>();
		List<Double> buyPriceList = new ArrayList<Double>();
		List<Long> sellVolumeList = new ArrayList<Long>();
		List<Double> sellPriceList = new ArrayList<Double>();
		for (int k = 0; k < 10; k += 2) {
			buyVolumeList.add(Long.valueOf(eachDetail[i + 1 + k]));
			buyPriceList.add(Double.valueOf(eachDetail[i + 2 + k]));
			sellVolumeList.add(Long.valueOf(eachDetail[i + 11 + k]));
			sellPriceList.add(Double.valueOf(eachDetail[i + 12 + k]));
		}
		bo.setBuyPriceList(buyPriceList);
		bo.setBuyQtyList(buyVolumeList);
		bo.setSellPriceList(sellPriceList);
		bo.setSellQtyList(sellVolumeList);
		i += 20;// 跳过买1-买5,卖1-卖5
		bo.setDateTime(Long.valueOf(eachDetail[++i].replace(CommonUtil.CharUtil.rod, "") + "000000")
				+ Long.valueOf(eachDetail[++i].replace(CommonUtil.CharUtil.colon, "")));
		byte[] bytes = bo.getBuilder().build().toByteArray();
		size += bytes.length;

		byte[] a = JSON.toJSONBytes(bo.getBo());
		size2 += a.length;
//		producer.publish(bo);
	}

	private class QueryMDThread implements Runnable {
		@Override
		public void run() {
			subMarketDate();
		}
	}
}
