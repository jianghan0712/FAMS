/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.mdcontainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.purefun.fams.king.constant.EventTypeEnum;
import com.purefun.fams.king.constant.KingConstant;
import com.purefun.fams.king.mongodb.MongoDBServiceImpl;
import com.purefun.fams.king.request.MDRequest;
import com.purefun.fams.md.MdBarDataBO;

/**
 * @Classname: MarketDataContainer
 * @Description:
 * @author 015979
 * @date 2020-03-26 20:08:51
 */
@Component
public class MarketDataContainer extends Observable {
	private static final Logger logger = LogManager.getLogger(MarketDataContainer.class);

	@Autowired
	private MongoDBServiceImpl mongodb;

	/** 行情bar的缓存 */
	private List<MdBarDataBO> barList = new ArrayList<MdBarDataBO>();

	/**
	 * *订阅行情
	 * 
	 * @MethodName: subscribe
	 * @author jianghan
	 * @date 2020-03-29 20:23:32
	 * @param request
	 */
	public void subscribe(MDRequest request) {
		String requestType = request.getRequestType();

		if (requestType.equalsIgnoreCase(KingConstant.RunType.BACKTEST)) {
			// 回测行情
			barList.clear();
			barList = mongodb.getValues("fams_stock_bar_" + request.getIndustry(), request.getStockCode(),
					Filters.gte("date", request.getStartDate()), MdBarDataBO.class);

		} else if (requestType.equalsIgnoreCase(KingConstant.RunType.TRADING)) {
			// 订阅实时交易行情

		}
	}

	/**
	 ** 回放行情开始
	 * 
	 * @MethodName: runHisBar
	 * @author jianghan
	 * @date 2020-03-29 20:29:37
	 */
	public void runHisBar() {
		if (barList == null || barList.size() == 0) {
			logger.error("没有找到对应证券的历史行情");
		}
		String stockCode = barList.get(0).security_code;
		for (MdBarDataBO each : barList) {
			setChanged();
			Object[] param = { EventTypeEnum.EVENT_MD_BAR, each };
			notifyObservers(param);
		}
		// 回放行情结束
		notifyEnd(barList.get(barList.size() - 1));
	}

	/**
	 ** 通知下游行情结束
	 * 
	 * @MethodName: notifyEnd
	 * @author jianghan
	 * @date 2020-03-29 20:28:59
	 */
	private void notifyEnd(MdBarDataBO lastMarketData) {
		setChanged();
		Object[] param = { EventTypeEnum.EVENT_MD_END, lastMarketData };
		notifyObservers(param);
	}

}
