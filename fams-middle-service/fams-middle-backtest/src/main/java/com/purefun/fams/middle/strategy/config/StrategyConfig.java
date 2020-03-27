/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.middle.strategy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.purefun.fams.middle.constant.MiddleServiceConstant;

/**
 * @Classname: StrategyConfig
 * @Description:
 * @author 015979
 * @date 2020-03-27 13:41:46
 */
@ConfigurationProperties(prefix = "fams.middle.strategy")
public class StrategyConfig {
	public static final String PREFIX = "fams.middle.strategy";

	public String name = "anonymous-strategy";

	/** 用于初始化策略的行情数据个数 */
	public int initCount = 20;
	/** */
	public String runType = MiddleServiceConstant.RunType.BACKTEST;
	/** 初始时的资金量 */
	public double cash = 100_000_000;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInitCount() {
		return initCount;
	}

	public void setInitCount(int initCount) {
		this.initCount = initCount;
	}

	public String getRunType() {
		return runType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

}
