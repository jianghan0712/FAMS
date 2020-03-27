/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.middle.strategy.model;

import java.math.BigDecimal;

import com.purefun.fams.middle.constant.DirectionEnum;

/**
 * @Classname: OrderSingal
 * @Description:
 * @author 015979
 * @date 2020-03-27 11:44:14
 */
public class OrderSingal {
	private DirectionEnum direction; // 订单防线

	private BigDecimal cash; // 买入时，cash不为0

	private long position; // 卖出时，position不为0

	public DirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

}
