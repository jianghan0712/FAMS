/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.middle.mdcontainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;
import com.purefun.fams.md.MdBarDataBO;
import com.purefun.fams.middle.constant.EventTypeEnum;
import com.purefun.fams.middle.mongodb.MongoDBServiceImpl;

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

	private List<MdBarDataBO> mdList = new ArrayList<MdBarDataBO>();

	public void loadData(String industry, String exch, String stockCode, String startDate, String endDate) {
		mdList.clear();
		mdList = mongodb.getValues("fams_stock_bar_" + industry, stockCode, Filters.gte("date", startDate),
				MdBarDataBO.class);
	}

	public List<MdBarDataBO> getMdList() {
		return mdList;
	}

	public void setMdList(List<MdBarDataBO> mdList) {
		this.mdList = mdList;
	}

	public void runHisBar() {
		if (mdList == null || mdList.size() == 0) {
			logger.error("没有找到对应证券的历史行情");
		}
		for (MdBarDataBO each : mdList) {
			setChanged();
			Object[] param = { EventTypeEnum.EVENT_MD_BAR, each };
			notifyObservers(param);
		}
		notifyEnd();
	}

	private void notifyEnd() {
		setChanged();
		Object[] param = { EventTypeEnum.EVENT_MD_END, "" };
		notifyObservers(param);
	}

}
