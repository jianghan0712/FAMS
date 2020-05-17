/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.apc.service;

import com.purefun.fams.ace.oms.apc.request.CashOpRequest;
import com.purefun.fams.ace.oms.apc.request.CashRequest;
import com.purefun.fams.ace.oms.apc.request.DealRequest;
import com.purefun.fams.ace.oms.apc.request.PositionRequest;
import com.purefun.fams.ace.oms.apc.response.CashOpRespond;
import com.purefun.fams.ace.oms.apc.response.CashRespond;
import com.purefun.fams.ace.oms.apc.response.DealRespond;
import com.purefun.fams.ace.oms.apc.response.PositionRespond;

/**
 * @Classname: APCService
 * @Description: 账户、持仓、资金管理服务
 * @author jianghan
 * @date 2020-05-17 19:44:20
 */
public interface APCService {

	/**
	 * 冻结资金接口，available账户出金，freeze入金
	 * 
	 * @MethodName: freezeCash
	 * @author jianghan
	 * @date 2020-05-17 19:46:43
	 * @param account  交易账户
	 * @param currency 资金币种
	 * @return
	 */
	CashRespond freezeCash(CashRequest request);

	/**
	 * 解冻资金接口，available账户入金，freeze出金
	 * 
	 * @MethodName: unfreezeCash
	 * @author jianghan
	 * @date 2020-05-17 20:14:11
	 * @param request
	 * @return
	 */
	CashRespond unfreezeCash(CashRequest request);

	/**
	 * 冻结股份接口
	 * 
	 * @MethodName: freezePosition
	 * @author jianghan
	 * @date 2020-05-17 20:14:26
	 * @param request
	 * @return
	 */
	PositionRespond freezePosition(PositionRequest request);

	/**
	 * 解冻股份接口
	 * 
	 * @MethodName: unfreezeCash
	 * @author jianghan
	 * @date 2020-05-17 20:14:35
	 * @param request
	 * @return
	 */
	PositionRespond unfreezeCash(PositionRequest request);

	/**
	 * 扣减（冻结中）资金并增加股份—买单成交 接口
	 * 
	 * @MethodName: cutCashAndAddPosition
	 * @author jianghan
	 * @date 2020-05-17 20:22:32
	 * @param request
	 * @return
	 */
	DealRespond cutCashAndAddPosition(DealRequest request);

	/**
	 * 增加资金并扣减（冻结中）股份—卖单成交
	 * 
	 * @MethodName: cutPositionAndAddCash
	 * @author jianghan
	 * @date 2020-05-17 20:23:04
	 * @param request
	 * @return
	 */
	DealRespond cutPositionAndAddCash(DealRequest request);

	/**
	 * 单向入金操作，默认入金账户为available
	 * 
	 * @MethodName: cashIn
	 * @author jianghan
	 * @date 2020-05-17 20:43:14
	 * @param request
	 * @return
	 */
	CashOpRespond cashIn(CashOpRequest request);

	/**
	 * 单向出金操作，默认出金账户为available
	 * 
	 * @MethodName: cashOut
	 * @author jianghan
	 * @date 2020-05-17 20:43:40
	 * @param request
	 * @return
	 */
	CashOpRespond cashOut(CashOpRequest request);

	/**
	 * 同时操作账户的inAccountType和outAccountType
	 * 
	 * @MethodName: cashInAndCashOut
	 * @author jianghan
	 * @date 2020-05-17 20:47:13
	 * @param request
	 * @return
	 */
	CashOpRespond cashInAndCashOut(CashOpRequest request);

}
