package com.purefun.fams.middle.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ignite.IgniteDataStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.purefun.fams.core.bo.tool.BoFactory;
import com.purefun.fams.framework.ignite.expose.IgniteCache;
import com.purefun.fams.md.MdBarDataBO;
import com.purefun.fams.middle.constant.DirectionEnum;
import com.purefun.fams.middle.constant.EventTypeEnum;
import com.purefun.fams.middle.strategy.model.OrderSingal;
import com.purefun.fams.trade.order.OrderBO;
import com.purefun.fams.trade.order.otw.OrderBO_OTW;

@Component
public class DoubleMAStrategy extends StrategyContainer {
	@Autowired
	private IgniteCache cache;

	/** 上一交易区间，longMA > shortMA */
	private Boolean lastDay = null;

	/** 当前是否有持仓 */
	private boolean positionFlag = false;

	/** 初始时的资金量 */
	private BigDecimal cash = BigDecimal.ZERO;

	/** 初始时的持仓量 */
	private long position = 0l;

	/** 订单队列 */
	private List<OrderSingal> orderSingal = new ArrayList<OrderSingal>();

	@PostConstruct
	public void onInit() {
		cash = new BigDecimal(straConfig.getCash());
	}

	@Override
	/** {@inheritDoc} */
	public void onStart() {
	}

	@Override
	/** {@inheritDoc} */
	public void onStop() {
		currentCount = 0;
		lastDay = null;
		positionFlag = false;
		orderSingal.clear();
		cash = new BigDecimal(straConfig.getCash());
	}

	@SuppressWarnings("unchecked")
	@Override
	/** {@inheritDoc} */
	public void onBar(MdBarDataBO barData) {
		checkOrderSet(barData);

		// 将行情引入ignite
		try (IgniteDataStreamer stmr = cache.getIgnite().dataStreamer("MdBarDataBO")) {
			stmr.addData(barData.date, barData);
			currentCount++;
			if (currentCount < straConfig.initCount)
				return;

			List<List<?>> shortMA = cache.getBySQL("MdBarDataBO",
					"select avg(close) from (select close from MdBarDataBO  order by date desc limit 6)");
			List<List<?>> longMA = cache.getBySQL("MdBarDataBO",
					"select avg(close) from (select close from MdBarDataBO  order by date desc limit 12)");
			double shortK = (double) shortMA.get(0).get(0);
			double longK = (double) longMA.get(0).get(0);
			// 只有一天的数据时
			if (lastDay == null) {
				lastDay = longK > shortK ? true : false;
				return;
			}

			// 当前是持仓状态，如果longMA下穿shortMA，下一日要进行卖出操作
			if (positionFlag) {
				if (lastDay && longK < shortK) {
					positionFlag = false;
					OrderSingal order = new OrderSingal();
					order.setDirection(DirectionEnum.SELL);
					order.setPosition(position);
					orderSingal.add(order);
				}
			} else {// 当前是空仓状态，如果shortMA上穿longMA，进行买入操作
				if (!lastDay && longK > shortK) {
					positionFlag = true;
					OrderSingal order = new OrderSingal();
					order.setDirection(DirectionEnum.BUY);
					order.setCash(cash);
					orderSingal.add(order);
				}
			}

			lastDay = longK > shortK ? true : false;
		}
	}

	/** {@inheritDoc} */
	/**
	 * 检查订单中是否有要执行的单，如果有，根据当前的行情进行委托
	 * 
	 * @MethodName: checkOrderSet
	 * @author 015979
	 * @date 2020-03-27 15:42:07
	 * @param barData
	 */
	private void checkOrderSet(MdBarDataBO barData) {
		if (orderSingal.isEmpty()) {
			return;
		}
		Iterator<OrderSingal> it = orderSingal.iterator();
		while (it.hasNext()) {
			OrderSingal each = it.next();
			OrderBO_OTW order = (OrderBO_OTW) BoFactory.createBo(OrderBO.class);
			order.setExch(barData.exch);
			order.setPrice(barData.open);
			order.setSecurity_code(barData.security_code);
			order.setSecurity_type(barData.security_type);
			order.setDirection(each.getDirection().getCode());
			BigDecimal cash = each.getCash();
			BigDecimal price = new BigDecimal(barData.open);

			if (each.getDirection().equals(DirectionEnum.BUY)) {
				// 如果是买入，现金减少，持仓增加
				BigDecimal position = cash.divide(price, 2, RoundingMode.DOWN).divide(new BigDecimal("100"), 0,
						RoundingMode.DOWN);
				long l = position.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_DOWN).longValue(); // 向下取整
				order.setVolume(l);
				this.cash = this.cash.subtract(new BigDecimal(l).multiply(price)).setScale(2, BigDecimal.ROUND_FLOOR);
				this.position += l;
			} else {
				BigDecimal addCash = new BigDecimal(each.getPosition()).multiply(price).setScale(2,
						BigDecimal.ROUND_FLOOR);
				this.position -= each.getPosition();
				this.cash = this.cash.add(addCash);
			}
			sendNewOrder(order);
			it.remove();
		}
		System.out.println("position=" + position + ", cash=" + cash);

	}

	/** {@inheritDoc} */
	@Override
	public void onTick() {
	}

	/** {@inheritDoc} */
	@Override
	public void sendNewOrder(OrderBO_OTW order) {
		notifyEvent(EventTypeEnum.EVENT_TRADE_NEW_ORDER, order);
	}

	/** {@inheritDoc} */
	@Override
	public void sendCancelOrder(OrderBO_OTW order) {
	}

	/** {@inheritDoc} */
	@Override
	public void onNewOrder() {
	}

	/** {@inheritDoc} */
	@Override
	public void onCancelOrder() {
	}

	/** {@inheritDoc} */
	@Override
	public void onExecutionOrder() {
	}

}
