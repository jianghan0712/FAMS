package com.purefun.fams.ace.oms;

import java.util.Date;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.tool.fstpbo;

@fstpbo(boid = 1005L, destination = "fams.ace.oms.positionstatement")
public class AceApcPositionStatementBO extends BaseBO {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 816065630609193259L;
	private Long id;
	@QuerySqlField
	private String account;
	@QuerySqlField
	private String relatedOrderId;
	@QuerySqlField
	private String securityId;

	private String securityName;
	@QuerySqlField
	private String exch;
	@QuerySqlField
	private String changeType;

	private Long changeQty;

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

	public String getRelatedOrderId() {
		return relatedOrderId;
	}

	public void setRelatedOrderId(String relatedOrderId) {
		this.relatedOrderId = relatedOrderId == null ? null : relatedOrderId.trim();
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId == null ? null : securityId.trim();
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName == null ? null : securityName.trim();
	}

	public String getExch() {
		return exch;
	}

	public void setExch(String exch) {
		this.exch = exch == null ? null : exch.trim();
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType == null ? null : changeType.trim();
	}

	public Long getChangeQty() {
		return changeQty;
	}

	public void setChangeQty(Long changeQty) {
		this.changeQty = changeQty;
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