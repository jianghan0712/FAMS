/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.model;

import java.math.BigDecimal;

import com.purefun.fams.common.domain.BaseDomain;
import com.purefun.fams.king.constant.DirectionEnum;

/**
 * @Classname: OrderSingal
 * @Description:
 * @author 015979
 * @date 2020-03-27 11:44:14
 */
public class OrderSingal extends BaseDomain {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -6274914853194658024L;

	private DirectionEnum direction; // 订单方向

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
