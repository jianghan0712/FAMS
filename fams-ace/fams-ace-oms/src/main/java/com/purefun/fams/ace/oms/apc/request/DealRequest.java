/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.apc.request;

import com.purefun.fams.common.domain.BaseRequest;

/**
 * @Classname: DealRequest
 * @Description: 成交时的请求，扣减（冻结中）资金并增加股份—买单成交、增加资金并扣减（冻结中）股份—卖单成交
 * @author jianghan
 * @date 2020-05-17 20:20:03
 */
public class DealRequest extends BaseRequest {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 5654095584996835713L;
	/** 交易账户 */
	private String account;
	/** 资金币种 */
	private String currency;
	/** 资金币种 */
	private String securityId;
	/** 市场 */
	private String exch;
	/** 冻结/解冻数量 */
	private long volume;

	/**
	 * @param account
	 * @param currency
	 * @param securityId
	 * @param exch
	 * @param volume
	 */

	public DealRequest(String account, String currency, String securityId, String exch, long volume) {
		super();
		this.account = account;
		this.currency = currency;
		this.securityId = securityId;
		this.exch = exch;
		this.volume = volume;
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
	 * Getter method for property <tt>securityId</tt>.
	 * 
	 * @return property value of securityId
	 */

	public String getSecurityId() {
		return securityId;
	}

	/**
	 * Setter method for property <tt>securityId</tt>.
	 * 
	 * @param securityId value to be assigned to property securityId
	 */
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	/**
	 * Getter method for property <tt>exch</tt>.
	 * 
	 * @return property value of exch
	 */

	public String getExch() {
		return exch;
	}

	/**
	 * Setter method for property <tt>exch</tt>.
	 * 
	 * @param exch value to be assigned to property exch
	 */
	public void setExch(String exch) {
		this.exch = exch;
	}

	/**
	 * Getter method for property <tt>volume</tt>.
	 * 
	 * @return property value of volume
	 */

	public long getVolume() {
		return volume;
	}

	/**
	 * Setter method for property <tt>volume</tt>.
	 * 
	 * @param volume value to be assigned to property volume
	 */
	public void setVolume(long volume) {
		this.volume = volume;
	}

}
