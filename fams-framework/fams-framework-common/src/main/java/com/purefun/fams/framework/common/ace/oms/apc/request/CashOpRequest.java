/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.ace.oms.apc.request;

import java.math.BigDecimal;

import com.purefun.fams.common.domain.BaseRequest;

/**
 * @Classname: CashOpRequest
 * @Description: 入金/出金请求
 * @author jianghan
 * @date 2020-05-17 20:37:48
 */
public class CashOpRequest extends BaseRequest {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 6518921130006682153L;

	/** 交易账户 */
	private String account;
	/** 资金币种 */
	private String currency;
	/** 操作金额 */
	private BigDecimal amount;
	/** 入金账户类型 */
	private String inAccountType;
	/** 出金账户类型 */
	private String outAccountType;

	public CashOpRequest() {

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

	/**
	 * Getter method for property <tt>inAccountType</tt>.
	 * 
	 * @return property value of inAccountType
	 */

	public String getInAccountType() {
		return inAccountType;
	}

	/**
	 * Setter method for property <tt>inAccountType</tt>.
	 * 
	 * @param inAccountType value to be assigned to property inAccountType
	 */
	public void setInAccountType(String inAccountType) {
		this.inAccountType = inAccountType;
	}

	/**
	 * Getter method for property <tt>outAccountType</tt>.
	 * 
	 * @return property value of outAccountType
	 */

	public String getOutAccountType() {
		return outAccountType;
	}

	/**
	 * Setter method for property <tt>outAccountType</tt>.
	 * 
	 * @param outAccountType value to be assigned to property outAccountType
	 */
	public void setOutAccountType(String outAccountType) {
		this.outAccountType = outAccountType;
	}

}
