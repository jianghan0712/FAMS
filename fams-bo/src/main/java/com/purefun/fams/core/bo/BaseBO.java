package com.purefun.fams.core.bo;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.common.domain.BaseDomain;

public class BaseBO extends BaseDomain {

	private static final long serialVersionUID = -4322899359980729622L;

	@QuerySqlField(index = true)
	public String uuid;

	public long boid;

//	public String destination;

	/**
	 * Getter method for property <tt>uuid</tt>.
	 * 
	 * @return property value of uuid
	 */

	public String getUuid() {
		return uuid;
	}

	/**
	 * Setter method for property <tt>uuid</tt>.
	 * 
	 * @param uuid value to be assigned to property uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Getter method for property <tt>boid</tt>.
	 * 
	 * @return property value of boid
	 */

	public long getBoid() {
		return boid;
	}

	/**
	 * Setter method for property <tt>boid</tt>.
	 * 
	 * @param boid value to be assigned to property boid
	 */
	public void setBoid(long boid) {
		this.boid = boid;
	}

}
