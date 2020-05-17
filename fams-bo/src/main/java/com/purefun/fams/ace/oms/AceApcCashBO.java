package com.purefun.fams.ace.oms;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.tool.fstpbo;

@fstpbo(boid = 1002L, destination = "fams.ace.oms.cash")
public class AceApcCashBO extends BaseBO {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 4895651078287189572L;

	private Long id;
	@QuerySqlField
	private String account;
	@QuerySqlField
	private String currency;

	private BigDecimal totalAmount;

	private BigDecimal availableAmount;

	private BigDecimal freezeAmount;

	private BigDecimal onwayAmount;

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency == null ? null : currency.trim();
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = availableAmount;
	}

	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public BigDecimal getOnwayAmount() {
		return onwayAmount;
	}

	public void setOnwayAmount(BigDecimal onwayAmount) {
		this.onwayAmount = onwayAmount;
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