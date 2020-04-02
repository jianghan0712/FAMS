/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.model;

import java.math.BigDecimal;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: PositionDetail
 * @Description: 持仓详情
 * @author 015979
 * @date 2020-03-30 15:16:18
 */
public class TradeDetail extends BaseDomain {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -3242609833492842469L;

	private String account;

	private String exch;

	private String securityCode;

	private String openDate;// 建仓日期

	private String closeDate;// 清仓日期

	private BigDecimal openPrice;// 成本价格

	private BigDecimal closePrice;// 平仓价格

	private long volume = 0l;// 持仓数量

	private long freezeVolume = 0l;// 其中冻结数量

	private BigDecimal cash;// 当次周期未投入的现金额

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getExch() {
		return exch;
	}

	public void setExch(String exch) {
		this.exch = exch;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public long getFreezeVolume() {
		return freezeVolume;
	}

	public void setFreezeVolume(long freezeVolume) {
		this.freezeVolume = freezeVolume;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

}
