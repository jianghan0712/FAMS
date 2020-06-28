/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.apc.service;

import com.purefun.fams.framework.common.ace.oms.apc.request.CashOpRequest;
import com.purefun.fams.framework.common.ace.oms.apc.request.CashRequest;
import com.purefun.fams.framework.common.ace.oms.apc.respond.CashOpRespond;
import com.purefun.fams.framework.common.ace.oms.apc.respond.CashRespond;

/**
 * @Classname: CashService
 * @Description: 资金类操作
 * @author JiangHan
 * @date 2020-05-27 16:27:18
 */
public interface CashService {

	/**
	 * 冻结资金接口，available账户出金，freeze入金，适用于下买单
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
	 * 解冻资金接口，available账户入金，freeze出金，适用于撤买单
	 * 
	 * @MethodName: unfreezeCash
	 * @author jianghan
	 * @date 2020-05-17 20:14:11
	 * @param request
	 * @return
	 */
	CashRespond unfreezeCash(CashRequest request);

	/**
	 * 单向入金操作，增加available账户资金，适用于交易账户的资金增加操作
	 * 
	 * @MethodName: cashIn
	 * @author jianghan
	 * @date 2020-05-17 20:43:14
	 * @param request
	 * @return
	 */
	CashOpRespond cashIn(CashOpRequest request);

	/**
	 * 单向出金操作，减少available账户资金，适用于交易账户的资金减少操作
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
