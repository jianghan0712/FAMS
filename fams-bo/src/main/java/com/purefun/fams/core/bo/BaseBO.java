package com.purefun.fams.core.bo;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.common.domain.BaseDomain;

public class BaseBO extends BaseDomain {

	private static final long serialVersionUID = -4322899359980729622L;

	@QuerySqlField(index = true)
	public String uuid;

	public long boid;

//	public String destination;
}
