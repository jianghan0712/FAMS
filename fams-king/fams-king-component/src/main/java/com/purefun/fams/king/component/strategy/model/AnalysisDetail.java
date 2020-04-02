/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.model;

import java.math.BigDecimal;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: AnalysisDetail
 * @Description: 单次交易的指标
 * @author 015979
 * @date 2020-04-01 00:05:28
 */
public class AnalysisDetail extends BaseDomain {
	/** @Fields: */
	private static final long serialVersionUID = 2788238679103107035L;

	private TradeDetail tradeDetail;// 交易明细

	private BigDecimal profitRate = new BigDecimal("0.0000");// 单次盈利率

	public TradeDetail getTradeDetail() {
		return tradeDetail;
	}

	public void setTradeDetail(TradeDetail tradeDetail) {
		this.tradeDetail = tradeDetail;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

}
