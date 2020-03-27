/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.middle.constant;

/**
 * @Classname: MiddleServiceConstant
 * @Description:
 * @author 015979
 * @date 2020-03-26 22:49:18
 */
public interface MiddleServiceConstant {
	/**
	 * 定义交易中台的运行模式
	 * 
	 * @Classname: RunType
	 * @Description:
	 * @author 015979
	 * @date 2020-03-27 13:37:44
	 */
	public interface RunType {
		String TRADING = "TRADING";// 真实交易，订单要发送至oms
		String BACKTEST = "BACKTEST";// 回归测试，订单只在本地进行撮合记录
	}
}
