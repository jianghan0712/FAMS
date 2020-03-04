package com.purefun.fams.framework.core.domain;

import java.util.Date;

import com.purefun.fams.common.domain.BaseDomain;

public class FamsExchange extends BaseDomain {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 2291313057003320380L;

	private Long id;

	private String exch;

	private String exchName;

	private String tsName;

	private Date modifyTime;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExch() {
		return exch;
	}

	public void setExch(String exch) {
		this.exch = exch == null ? null : exch.trim();
	}

	public String getExchName() {
		return exchName;
	}

	public void setExchName(String exchName) {
		this.exchName = exchName == null ? null : exchName.trim();
	}

	public String getTsName() {
		return tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName == null ? null : tsName.trim();
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