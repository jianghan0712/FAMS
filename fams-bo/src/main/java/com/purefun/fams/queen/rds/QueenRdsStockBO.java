package com.purefun.fams.queen.rds;

import java.util.Date;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.tool.fstpbo;

@fstpbo(boid = 3001L, destination = "fams.queen.rds.stock")
public class QueenRdsStockBO extends BaseBO {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -4236011581033976573L;

	private Long id;
	@QuerySqlField
	private String exch;
	@QuerySqlField
	private String securityId;
	@QuerySqlField
	private String tsId;
	@QuerySqlField
	private String exchangeId;

	private String securityName;
	@QuerySqlField
	private String area;
	@QuerySqlField
	private String industry;
	@QuerySqlField
	private String marketType;

	private String currency;
	@QuerySqlField
	private String status;

	private String listDate;

	private String htFlag;

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

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId == null ? null : securityId.trim();
	}

	public String getTsId() {
		return tsId;
	}

	public void setTsId(String tsId) {
		this.tsId = tsId == null ? null : tsId.trim();
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId == null ? null : exchangeId.trim();
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName == null ? null : securityName.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry == null ? null : industry.trim();
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType == null ? null : marketType.trim();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency == null ? null : currency.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getListDate() {
		return listDate;
	}

	public void setListDate(String listDate) {
		this.listDate = listDate == null ? null : listDate.trim();
	}

	public String getHtFlag() {
		return htFlag;
	}

	public void setHtFlag(String htFlag) {
		this.htFlag = htFlag == null ? null : htFlag.trim();
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