package com.purefun.fams.framework.core.domain;

import java.util.Date;

import com.purefun.fams.common.domain.BaseDomain;

public class FamsGlobalParam extends BaseDomain {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -1206838149770312589L;

	private Long id;

	private String paramName;

	private String paramValue;

	private String paramScope;

	private String paramDescribe;

	private Date modifyTime;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName == null ? null : paramName.trim();
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue == null ? null : paramValue.trim();
	}

	public String getParamScope() {
		return paramScope;
	}

	public void setParamScope(String paramScope) {
		this.paramScope = paramScope == null ? null : paramScope.trim();
	}

	public String getParamDescribe() {
		return paramDescribe;
	}

	public void setParamDescribe(String paramDescribe) {
		this.paramDescribe = paramDescribe == null ? null : paramDescribe.trim();
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}