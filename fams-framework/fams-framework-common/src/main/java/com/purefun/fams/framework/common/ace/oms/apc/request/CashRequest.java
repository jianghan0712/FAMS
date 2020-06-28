/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.ace.oms.apc.request;

import java.math.BigDecimal;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: CashRequest
 * @Description: 冻结/解冻资金请求
 * @author jianghan
 * @date 2020-05-17 19:48:14
 */
public class CashRequest extends BaseDomain {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -5881010853753169680L;
	/** 交易账户 */
	private String account;
	/** 资金币种 */
	private String currency;
	/** 操作金额 */
	private BigDecimal amount;

	/**
	 * @param account
	 * @param currency
	 * @param amount
	 */
	public CashRequest(String account, String currency, BigDecimal amount) {
		super();
		this.account = account;
		this.currency = currency;
		this.amount = amount;
	}

	public CashRequest() {
	}

	/**
	 * Getter method for property <tt>account</tt>.
	 * 
	 * @return property value of account
	 */

	public String getAccount() {
		return account;
	}

	/**
	 * Setter method for property <tt>account</tt>.
	 * 
	 * @param account value to be assigned to property account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Getter method for property <tt>currency</tt>.
	 * 
	 * @return property value of currency
	 */

	public String getCurrency() {
		return currency;
	}

	/**
	 * Setter method for property <tt>currency</tt>.
	 * 
	 * @param currency value to be assigned to property currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Getter method for property <tt>amount</tt>.
	 * 
	 * @return property value of amount
	 */

	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Setter method for property <tt>amount</tt>.
	 * 
	 * @param amount value to be assigned to property amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
