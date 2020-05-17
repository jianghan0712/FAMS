/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.apc.request;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: CashRequest
 * @Description: 冻结/解冻持仓请求
 * @author jianghan
 * @date 2020-05-17 19:48:14
 */
public class PositionRequest extends BaseDomain {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -5325509664294136567L;
	/** 交易账户 */
	private String account;
	/** 资金币种 */
	private String securityId;
	/** 市场 */
	private String exch;
	/** 冻结/解冻数量 */
	private long volume;

	/**
	 * @param account
	 * @param securityId
	 * @param exch
	 * @param volume
	 */
	public PositionRequest(String account, String securityId, String exch, long volume) {
		super();
		this.account = account;
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
