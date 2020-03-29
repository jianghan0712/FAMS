/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.request;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: MDRequest
 * @Description:
 * @author jianghan
 * @date 2020-03-29 20:05:31
 */
public class MDRequest extends BaseDomain {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 3424549638962109239L;

	private String requestType;

	private String mdType;

	private String industry;

	private String exch;

	private String stockCode;

	private String startDate;

	private String endDate;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getMdType() {
		return mdType;
	}

	public void setMdType(String mdType) {
		this.mdType = mdType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getExch() {
		return exch;
	}

	public void setExch(String exch) {
		this.exch = exch;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
