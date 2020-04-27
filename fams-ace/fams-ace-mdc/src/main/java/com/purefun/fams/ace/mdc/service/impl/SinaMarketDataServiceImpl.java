/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.mdc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.purefun.fams.ace.mdc.config.SinaConfig;
import com.purefun.fams.ace.mdc.service.SinaMarketDataService;
import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.core.bo.tool.BoFactory;
import com.purefun.fams.framework.core.communication.FAMSProducer;
import com.purefun.fams.framework.core.dao.FamsSecurityBasicinfoMapper;
import com.purefun.fams.framework.core.domain.FamsSecurityBasicinfo;
import com.purefun.fams.framework.core.http.LogInterceptor;
import com.purefun.fams.md.MdStockSnapshotTempBO;
import com.purefun.fams.md.MdStockSnapshotTempletBO;
import com.purefun.fams.md.otw.MdStockSnapshotTempBO_OTW;
import com.purefun.fams.md.otw.MdStockSnapshotTempletBO_OTW;

/**
 * @Classname: SinaMarketDataServiceImpl
 * @Description:
 * @author jianghan
 * @date 2020-04-26 16:52:19
 */
@Service
public class SinaMarketDataServiceImpl implements SinaMarketDataService {

	/** 向sina发起restful请求 */
	private RestTemplate sinaTemplate;

	@Autowired
	private SinaConfig config;

	@Autowired
	private FamsSecurityBasicinfoMapper mapper;

	private HashSet<String> stockSet = new HashSet<String>();

	@Autowired
	private FAMSProducer producer;

	long size = 0l;

	@PostConstruct
	public void init() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new LogInterceptor());
		this.sinaTemplate = restTemplate;

	}

	public void initSet() {
		List<FamsSecurityBasicinfo> list = mapper.selectAll();
		for (FamsSecurityBasicinfo each : list) {
			stockSet.add(each.getExch() + each.getExchangeId());
		}
	}

	/**
	 ** 向新浪订阅行情
	 * 
	 * @MethodName: subMarketDate
	 * @author jianghan
	 * @date 2020-04-27 21:08:44
	 */
	public void subMarketDate() {
		// TODO Auto-generated method stub
		initSet();
		StringBuffer stockList = new StringBuffer();
		int i = 0;
		for (String each : stockSet) {
			stockList.append(each).append(CommonUtil.CharUtil.comma);
			i++;
			if (i == 500)
				break;
		}

		ResponseEntity<String> response = sinaTemplate.getForEntity("http://hq.sinajs.cn/list=" + stockList.toString(),
				String.class);

		String body[] = response.getBody().trim().replace("\n", "").replace("\"", "").replace("=", ",").split(";");
		for (String e : body) {
			parseMDAndPublish(e.substring(e.indexOf("str_") + 4).split(","));
		}
		System.out.println(body.length);
		System.out.println(size);
	}

	/**
	 * @MethodName: parseMDAndPublish
	 * @author jianghan
	 * @param eachDetail
	 * @date 2020-04-27 20:10:52
	 */
	private void parseMDAndPublish(String[] eachDetail) {
		// TODO Auto-generated method stub
		MdStockSnapshotTempBO_OTW bo = (MdStockSnapshotTempBO_OTW) BoFactory.createBo(MdStockSnapshotTempBO.class);
		int i = 0;
		String code = eachDetail[i].substring(2);
		String exch = eachDetail[i].substring(0, 2).toUpperCase();
		bo.setSecurity_code(code + CommonUtil.CharUtil.point + exch);
		bo.setExch(exch);
		i++;
//		bo.setCnName(eachDetail[++i]);
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
		System.out.println(bo);
		byte[] bytes = bo.getBuilder().build().toByteArray();
		size += bytes.length;
	}

	private void parseMDAndPublish2(String[] eachDetail) {
		// TODO Auto-generated method stub
		MdStockSnapshotTempletBO_OTW bo = (MdStockSnapshotTempletBO_OTW) BoFactory
				.createBo(MdStockSnapshotTempletBO.class);
		int i = 0;
		String code = eachDetail[i].substring(2);
		String exch = eachDetail[i].substring(0, 2).toUpperCase();
//		bo.setSecurity_code(code + CommonUtil.CharUtil.point + exch);
//		bo.setExch(exch);
		i++;
//		bo.setCnName(eachDetail[++i]);
		bo.setOpen(Double.valueOf(eachDetail[++i]));
		bo.setPreClose(Double.valueOf(eachDetail[++i]));
		bo.setLastPrice(Double.valueOf(eachDetail[++i]));
		bo.setHigh(Double.valueOf(eachDetail[++i]));
		bo.setLow(Double.valueOf(eachDetail[++i]));
		i = i + 2;// 跳过 竞买价 和 竞卖价
		bo.setTotalVolume(Long.valueOf(eachDetail[++i]));
		bo.setTotalAmont(new BigDecimal(eachDetail[++i]).doubleValue());
		bo.setBuy1Qty(Long.valueOf(eachDetail[++i]));
		bo.setBuy1Price(Double.valueOf(eachDetail[++i]));
		bo.setBuy2Qty(Long.valueOf(eachDetail[++i]));
		bo.setBuy2Price(Double.valueOf(eachDetail[++i]));
		bo.setBuy3Qty(Long.valueOf(eachDetail[++i]));
		bo.setBuy3Price(Double.valueOf(eachDetail[++i]));
		bo.setBuy4Qty(Long.valueOf(eachDetail[++i]));
		bo.setBuy4Price(Double.valueOf(eachDetail[++i]));
		bo.setBuy5Qty(Long.valueOf(eachDetail[++i]));
		bo.setBuy5Price(Double.valueOf(eachDetail[++i]));

		bo.setSell1Qty(Long.valueOf(eachDetail[++i]));
		bo.setSell1Price(Double.valueOf(eachDetail[++i]));
		bo.setSell2Qty(Long.valueOf(eachDetail[++i]));
		bo.setSell2Price(Double.valueOf(eachDetail[++i]));
		bo.setSell3Qty(Long.valueOf(eachDetail[++i]));
		bo.setSell3Price(Double.valueOf(eachDetail[++i]));
		bo.setSell4Qty(Long.valueOf(eachDetail[++i]));
		bo.setSell4Price(Double.valueOf(eachDetail[++i]));
		bo.setSell5Qty(Long.valueOf(eachDetail[++i]));
		bo.setSell5Price(Double.valueOf(eachDetail[++i]));

		bo.setDateTime(Long.valueOf(eachDetail[++i].replace(CommonUtil.CharUtil.rod, "") + "000000")
				+ Long.valueOf(eachDetail[++i].replace(CommonUtil.CharUtil.colon, "")));
		System.out.println(bo);
		byte[] bytes = bo.getBuilder().build().toByteArray();
		size += bytes.length;
	}
//
//	0：”大秦铁路”，股票名字；
//	1：”27.55″，今日开盘价；
//	2：”27.25″，昨日收盘价；
//	3：”26.91″，当前价格；
//	4：”27.55″，今日最高价；
//	5：”26.20″，今日最低价；
//	6：”26.91″，竞买价，即“买一”报价；
//	7：”26.92″，竞卖价，即“卖一”报价；
//	8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
//	9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
//	10：”4695″，“买一”申请4695股，即47手；
//	11：”26.91″，“买一”报价；
//	12：”57590″，“买二”
//	13：”26.90″，“买二”
//	14：”14700″，“买三”
//	15：”26.89″，“买三”
//	16：”14300″，“买四”
//	17：”26.88″，“买四”
//	18：”15100″，“买五”
//	19：”26.87″，“买五”
//	20：”3100″，“卖一”申报3100股，即31手；
//	21：”26.92″，“卖一”报价
//	(22,
//	23), (24, 25), (26,27), (28,
//	29)分别为“卖二”至“卖四的情况”
//	30：”2008-01-11″，日期；
//	31：”15:05:32″，时间；
}
