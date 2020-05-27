package com.purefun.fams.ace.oms;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.tool.fstpbo;

@fstpbo(boid = 1004L, destination = "fams.ace.oms.position")
public class AceApcPositionBO extends BaseBO {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 8300075262644487090L;
	private Long id;
	@QuerySqlField
	private String account;
	@QuerySqlField
	private String securityId;

	private String securityName;
	@QuerySqlField
	private String exch;

	private Long totalQty;

	private Long availableQty;

	private Long freezeQty;

	private Long onwayQty;

	private BigDecimal aveCostPrice;

	private BigDecimal marketValue;

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

	public Long getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Long totalQty) {
		this.totalQty = totalQty;
	}

	public Long getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(Long availableQty) {
		this.availableQty = availableQty;
	}

	public Long getFreezeQty() {
		return freezeQty;
	}

	public void setFreezeQty(Long freezeQty) {
		this.freezeQty = freezeQty;
	}

	public Long getOnwayQty() {
		return onwayQty;
	}

	public void setOnwayQty(Long onwayQty) {
		this.onwayQty = onwayQty;
	}

	public BigDecimal getAveCostPrice() {
		return aveCostPrice;
	}

	public void setAveCostPrice(BigDecimal aveCostPrice) {
		this.aveCostPrice = aveCostPrice;
	}

	/**
	 * Getter method for property <tt>marketValue</tt>.
	 * 
	 * @return property value of marketValue
	 */

	public BigDecimal getMarketValue() {
		return marketValue;
	}

	/**
	 * Setter method for property <tt>marketValue</tt>.
	 * 
	 * @param marketValue value to be assigned to property marketValue
	 */
	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
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