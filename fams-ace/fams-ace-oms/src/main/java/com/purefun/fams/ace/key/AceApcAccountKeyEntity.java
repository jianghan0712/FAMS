/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.key;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: AceApcAccountKeyEntity
 * @Description: account表的key
 * @author jianghan
 * @date 2020-05-26 23:19:58
 */
public class AceApcAccountKeyEntity extends BaseDomain {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 3877401582839604683L;

	private String account;

	/**
	 * Getter method for property <tt>account</tt>.
	 * 
	 * @return property value of account
	 */

	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 */

	public AceApcAccountKeyEntity(String account) {
		super();
		this.account = account;
	}

	/**
	 * Setter method for property <tt>account</tt>.
	 * 
	 * @param account value to be assigned to property account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

}
