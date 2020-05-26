/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.key;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: AceApcCashKeyEntity
 * @Description: Cash表的key
 * @author 015979
 * @date 2020-05-26 20:10:39
 */
public class AceApcCashKeyEntity extends BaseDomain {

	/** @Fields: */
	private static final long serialVersionUID = 455127702508771069L;

	private String account;

	private String currency;

	/**
	 * @param account
	 * @param currency
	 */

	public AceApcCashKeyEntity(String account, String currency) {
		super();
		this.account = account;
		this.currency = currency;
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

}
