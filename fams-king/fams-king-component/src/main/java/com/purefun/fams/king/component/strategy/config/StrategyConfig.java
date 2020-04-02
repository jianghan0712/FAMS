/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.strategy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.purefun.fams.king.constant.KingConstant;

/**
 * @Classname: StrategyConfig
 * @Description:
 * @author 015979
 * @date 2020-03-27 13:41:46
 */
@ConfigurationProperties(prefix = "fams.king.strategy")
public class StrategyConfig {
	public static final String PREFIX = "fams.king.strategy";

	public String name = "anonymous-strategy";

	public String account = "test-backtest-account";

	/** 用于初始化策略的行情数据个数 */
	public int initCount = 20;
	/** */
	public String runType = KingConstant.RunType.BACKTEST;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
