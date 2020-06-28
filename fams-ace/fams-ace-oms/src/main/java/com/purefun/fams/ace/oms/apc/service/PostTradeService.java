/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.apc.service;

import com.purefun.fams.framework.common.ace.oms.apc.request.DealRequest;
import com.purefun.fams.framework.common.ace.oms.apc.respond.DealRespond;

/**
 * @Classname: PostTradeService
 * @Description: 处理成交后，资金、持仓的变化
 * @author JiangHan
 * @date 2020-05-27 16:41:03
 */
public interface PostTradeService {
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

}
