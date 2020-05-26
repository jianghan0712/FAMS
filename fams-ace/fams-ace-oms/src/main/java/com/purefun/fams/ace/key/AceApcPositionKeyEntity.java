/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.key;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: AceApcPositionKeyEntity
 * @Description: position表的key
 * @author jianghan
 * @date 2020-05-26 23:21:06
 */
public class AceApcPositionKeyEntity extends BaseDomain {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 4951708463013623543L;

	private String account;

	private String securityId;

	private String exch;

	/**
	 * @param account
	 * @param securityId
	 * @param exch
	 */

	public AceApcPositionKeyEntity(String account, String securityId, String exch) {
		super();
		this.account = account;
		this.securityId = securityId;
		this.exch = exch;
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

}
