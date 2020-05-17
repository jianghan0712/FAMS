package com.purefun.fams.ace.oms;

import java.util.Date;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.tool.fstpbo;

@fstpbo(boid = 1001L, destination = "fams.ace.oms.account")
public class AceApcAccountBO extends BaseBO {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -7206808347056450741L;

	private Long id;
	@QuerySqlField
	private String account;
	@QuerySqlField
	private String accountType;

	private String accountName;
	@QuerySqlField
	private String accountJobid;
	@QuerySqlField
	private Integer accountLevel;

	private Date modifyTime;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType == null ? null : accountType.trim();
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName == null ? null : accountName.trim();
	}

	public String getAccountJobid() {
		return accountJobid;
	}

	public void setAccountJobid(String accountJobid) {
		this.accountJobid = accountJobid == null ? null : accountJobid.trim();
	}

	public Integer getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(Integer accountLevel) {
		this.accountLevel = accountLevel;
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