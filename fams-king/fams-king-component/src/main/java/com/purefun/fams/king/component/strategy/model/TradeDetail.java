/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.model;

import com.purefun.fams.common.domain.BaseDomain;
import com.purefun.fams.king.constant.DirectionEnum;

/**
 * @Classname: TradeDetail
 * @Description:
 * @author 015979
 * @date 2020-03-27 14:04:22
 */
public class TradeDetail extends BaseDomain {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -8506942371189779041L;

	public String tradeDate;

	public DirectionEnum direction;

	public String exch;

	public String securityCode;

	public double price;

	public double amount;

	public int volume;

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public DirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}

	public String getExch() {
		return exch;
	}

	public void setExch(String exch) {
		this.exch = exch;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

}
