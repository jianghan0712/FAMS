package com.purefun.fams.king.component.strategy;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ignite.IgniteDataStreamer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.purefun.fams.framework.ignite.expose.IgniteCache;
import com.purefun.fams.king.constant.DirectionEnum;
import com.purefun.fams.md.MdBarDataBO;
import com.purefun.fams.trade.order.otw.OrderBO_OTW;

/**
 * 双移动平均线策略：当shortMA上穿longMA时，买入；当longMA下穿shortMA时，卖出
 * 
 * @Classname: DoubleMAStrategy
 * @Description:
 * @author 015979
 * @date 2020-03-30 15:37:27
 */
@Component
public class DoubleMAStrategy extends StrategyContainer {
	private static final Logger logger = LogManager.getLogger(DoubleMAStrategy.class);

	@Autowired
	private IgniteCache cache;

	/** 上一交易区间，longMA > shortMA */
	private Boolean lastDay = null;

	/** 当前是否有持仓 */
	private boolean positionFlag = false;

	@PostConstruct
	public void onInit() {
		super.onInit();
	}

	@Override
	/** {@inheritDoc} */
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop(MdBarDataBO lastMarketData) {
		super.onStop(lastMarketData);

		lastDay = null;
		positionFlag = false;
	}

	@SuppressWarnings("unchecked")
	@Override
	/** {@inheritDoc} */
	public void onBar(MdBarDataBO barData) {
		// 检查上一周期是否触发买卖信号
		checkNewOrderSet(barData);

		// 将行情引入ignite
		try (IgniteDataStreamer stmr = cache.getIgnite().dataStreamer("MdBarDataBO")) {
			stmr.addData(barData.date, barData);

			if (currentCount++ < straConfig.initCount)
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
					signalNotify(DirectionEnum.SELL, barData.security_code);
				}
			} else {// 当前是空仓状态，如果shortMA上穿longMA，进行买入操作
				if (!lastDay && longK > shortK) {
					positionFlag = true;
					signalNotify(DirectionEnum.BUY, barData.security_code);
				}
			}

			lastDay = longK > shortK ? true : false;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void onTick() {
	}

	/** {@inheritDoc} */

	/** {@inheritDoc} */
	@Override
	public void sendCancelOrder(OrderBO_OTW order) {
//		notifyOrderEvent(EventTypeEnum.EVENT_TRADE_CANCEL_ORDER, order);
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
