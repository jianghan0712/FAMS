/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.model;

import java.math.BigDecimal;
import java.util.List;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: AnalysisResult
 * @Description: 当只股票历史回测的总指标
 * @author 015979
 * @date 2020-03-31 20:25:59
 */
public class AnalysisResult extends BaseDomain {

	/** @Fields: */
	private static final long serialVersionUID = 4635485952769683926L;

	private String securityCode;

	private BigDecimal profitRate = new BigDecimal("0.0000");// 总盈利率

	private boolean isHappy;// 是否盈利

	private int happyCount;// 赚钱次数

	private int sadCount;// 赔钱次数

	private BigDecimal maxRetreatRate = new BigDecimal("0.0000");// 最大回撤

	private BigDecimal maxProfitRate = new BigDecimal("0.0000");// 最大收益

	private BigDecimal happyRate = new BigDecimal("0.0000");// 交易成功率

	private List<AnalysisDetail> tradeList;// 逐笔交易的指标明细

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public boolean isHappy() {
		return isHappy;
	}

	public void setHappy(boolean isHappy) {
		this.isHappy = isHappy;
	}

	public int getHappyCount() {
		return happyCount;
	}

	public void setHappyCount(int happyCount) {
		this.happyCount = happyCount;
	}

	public int getSadCount() {
		return sadCount;
	}

	public void setSadCount(int sadCount) {
		this.sadCount = sadCount;
	}

	public BigDecimal getMaxRetreatRate() {
		return maxRetreatRate;
	}

	public void setMaxRetreatRate(BigDecimal maxRetreatRate) {
		this.maxRetreatRate = maxRetreatRate;
	}

	public BigDecimal getMaxProfitRate() {
		return maxProfitRate;
	}

	public void setMaxProfitRate(BigDecimal maxProfitRate) {
		this.maxProfitRate = maxProfitRate;
	}

	public BigDecimal getHappyRate() {
		return happyRate;
	}

	public void setHappyRate(BigDecimal happyRate) {
		this.happyRate = happyRate;
	}

	public List<AnalysisDetail> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<AnalysisDetail> tradeList) {
		this.tradeList = tradeList;
	}

}
