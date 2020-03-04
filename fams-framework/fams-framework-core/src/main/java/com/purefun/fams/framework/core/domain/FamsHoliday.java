package com.purefun.fams.framework.core.domain;

import java.util.Date;

import com.purefun.fams.common.domain.BaseDomain;

public class FamsHoliday extends BaseDomain {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -8141268224977792499L;

	private Long id;

	private String date;

	private String exch;

	private String openFlag;

	private Date modifyTime;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date == null ? null : date.trim();
	}

	public String getExch() {
		return exch;
	}

	public void setExch(String exch) {
		this.exch = exch == null ? null : exch.trim();
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag == null ? null : openFlag.trim();
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