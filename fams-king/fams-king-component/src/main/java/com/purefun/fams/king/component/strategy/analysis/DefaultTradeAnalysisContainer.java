/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.analysis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.king.component.strategy.model.AnalysisDetail;
import com.purefun.fams.king.component.strategy.model.AnalysisResult;
import com.purefun.fams.king.component.strategy.model.TradeDetail;

/**
 * @Classname: DefaultTradeAnalysisContainer
 * @Description: 默认的交易结果分析器
 * @author 015979
 * @date 2020-03-31 20:20:13
 */
public class DefaultTradeAnalysisContainer {
	/** 按股票计算的交易结果分析<stockCode, result> */
	protected Map<String, AnalysisResult> resultMap = new HashMap<String, AnalysisResult>();

	public void computeResultByStock(List<TradeDetail> tradeDetailList) {
		if (tradeDetailList == null || tradeDetailList.size() == 0) {
			return;
		}
		AnalysisResult result = new AnalysisResult();
		int happyCount = 0;//
		int sadCount = 0;
		BigDecimal maxProfitRate = BigDecimal.ZERO;
		BigDecimal maxRetreatRate = BigDecimal.ZERO;

		BigDecimal startAsset = BigDecimal.ZERO;// 回测开始时资产
		BigDecimal endAsset = BigDecimal.ZERO;// 回测结束时资产

		List<AnalysisDetail> detailList = new ArrayList<AnalysisDetail>();

		for (int i = 0; i < tradeDetailList.size(); i++) {
			TradeDetail eachTrade = tradeDetailList.get(i);
			AnalysisDetail detail = new AnalysisDetail();
			BigDecimal openPrice = eachTrade.getOpenPrice();
			BigDecimal closePrice = eachTrade.getClosePrice();

			/** 期初资产 = 期初股票价格*持仓量 +期初现金 */
			BigDecimal openAsset = openPrice.multiply(new BigDecimal(eachTrade.getFreezeVolume()))
					.add(eachTrade.getCash());
			/** 期末资产 = 期末股票价格*持仓量 +期末现金 */
			BigDecimal closeAsset = closePrice.multiply(new BigDecimal(eachTrade.getFreezeVolume()))
					.add(eachTrade.getCash());
			BigDecimal profitRate = closeAsset
					.divide(openAsset, CommonUtil.ScaleUtil.RATE_SCALE, BigDecimal.ROUND_FLOOR)
					.subtract(BigDecimal.ONE);
			detail.setProfitRate(profitRate);
			detail.setTradeDetail(eachTrade);
			if (profitRate.compareTo(BigDecimal.ZERO) < 0) {
				// 亏损的交易
				sadCount++;
				maxRetreatRate = maxRetreatRate.compareTo(profitRate) < 0 ? maxRetreatRate : profitRate;
			} else {
				happyCount++;
				maxProfitRate = maxProfitRate.compareTo(profitRate) > 0 ? maxProfitRate : profitRate;
			}
			// 记录每一次的收益率
			detailList.add(detail);

			if (i == 0) {
				startAsset = openAsset;
			} else if (i == tradeDetailList.size() - 1) {
				endAsset = closeAsset;
			}
			System.out.println(detail);
		}

		result.setHappyCount(happyCount);
		result.setSadCount(sadCount);
		result.setMaxProfitRate(maxProfitRate);
		result.setMaxRetreatRate(maxRetreatRate);
		result.setTradeList(detailList);
		result.setSecurityCode(tradeDetailList.get(0).getSecurityCode());

		// 成功率
		result.setHappyRate(new BigDecimal(happyCount / (happyCount + sadCount))
				.setScale(CommonUtil.ScaleUtil.RATE_SCALE, BigDecimal.ROUND_FLOOR));
		BigDecimal profitRate = endAsset.divide(startAsset, CommonUtil.ScaleUtil.RATE_SCALE, BigDecimal.ROUND_FLOOR)
				.subtract(BigDecimal.ONE);
		result.setProfitRate(profitRate);
		result.setHappy(endAsset.compareTo(startAsset) > 0 ? true : false);

		System.out.println(result);
//		System.out.println(happyCount);
//		System.out.println(sadCount);
//		System.out.println("maxRetreatRate=" + maxRetreatRate);
//		System.out.println("maxProfitRate=" + maxProfitRate);

//		for (BigDecimal eachRate : profitRateList) {
//
//		}
	}

}
