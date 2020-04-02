/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.model;

import java.math.BigDecimal;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: Account
 * @Description:
 * @author 015979
 * @date 2020-03-31 21:21:24
 */
public class Account extends BaseDomain {

	/** @Fields: */
	private static final long serialVersionUID = 3282264010293509695L;

	private String acct;// 资金账户

	private BigDecimal totalCapital;// 总现金资产

	private BigDecimal availableCapital;// 可用现金资产

	private BigDecimal freezeCapital;// 冻结中的现金资产

	public String getAcct() {
		return acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	public BigDecimal getTotalCapital() {
		return totalCapital;
	}

	public void setTotalCapital(BigDecimal totalCapital) {
		this.totalCapital = totalCapital;
	}

	public BigDecimal getAvailableCapital() {
		return availableCapital;
	}

	public void setAvailableCapital(BigDecimal availableCapital) {
		this.availableCapital = availableCapital;
	}

	public BigDecimal getFreezeCapital() {
		return freezeCapital;
	}

	public void setFreezeCapital(BigDecimal freezeCapital) {
		this.freezeCapital = freezeCapital;
	}

}
