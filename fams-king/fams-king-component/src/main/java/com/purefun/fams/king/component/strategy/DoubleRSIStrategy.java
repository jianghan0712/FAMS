package com.purefun.fams.king.component.strategy;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ignite.IgniteDataStreamer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.purefun.fams.ace.md.MdBarDataBO;
import com.purefun.fams.framework.ignite.expose.IgniteCache;
import com.purefun.fams.king.constant.DirectionEnum;
import com.purefun.fams.trade.order.otw.OrderBO_OTW;

/**
 ** RSI策略。当前一日rsi<30，今日rsi>30时买入；当前一日rsi>70,今日rsi<70时卖出
 * 
 * @Classname: DoubleMAStrategy
 * @Description:
 * @author 015979
 * @date 2020-03-30 15:37:27
 */
@Component
public class DoubleRSIStrategy extends StrategyContainer {
	private static final Logger logger = LogManager.getLogger(DoubleRSIStrategy.class);

	@Autowired
	private IgniteCache cache;

	/** 上一交易区间，是否rsi>70 */
	private Boolean lastDayGood = null;
	/** 上一交易区间，是否rsi<30 */
	private Boolean lastDayBad = null;

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

		lastDayGood = null;
		lastDayBad = null;
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

			// 算法1
			List<List<?>> avgGood = cache.getBySQL("MdBarDataBO",
					"select sum(change) from (select change from MdBarDataBO order by date desc limit 14) where change>=0");
			List<List<?>> avgBad = cache.getBySQL("MdBarDataBO",
					"select sum(change) from (select change from MdBarDataBO order by date desc limit 14) where change<0");
			double good = (double) avgGood.get(0).get(0);
			double bad = (double) avgBad.get(0).get(0);
//			double rsi = 100 - 100 / (1 + good / (bad * (-1)));
			double rs = good / (bad * (-1));
			double rsi = 100 - 100 / (rs + 1);
//

//			double rsi = good / (good + bad * (-1)) * 100;
			System.out.println(barData.date + " good=" + good + " ,bad=" + bad + " ,rsi=" + rsi);

			// 只有一天的数据时
			if (lastDayGood == null || lastDayBad == null) {
				lastDayGood = rsi > 70 ? true : false;
				lastDayBad = rsi < 40 ? true : false;
				return;
			}

			// 当前是持仓状态
			if (positionFlag) {
				// 当昨日的rsi>70 且今日rsi<70，说明下行了，则卖出
				if (lastDayGood && rsi <= 70) {
					positionFlag = false;
					signalNotify(DirectionEnum.SELL, barData.security_code);
				}
			} else {// 当前是空仓状态
				// 当昨日的rsi<30且今日的rsi>30，则买入
				if (lastDayBad && rsi >= 40) {
					positionFlag = true;
					signalNotify(DirectionEnum.BUY, barData.security_code);
				}
			}

			lastDayGood = rsi > 70 ? true : false;
			lastDayBad = rsi < 40 ? true : false;
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
