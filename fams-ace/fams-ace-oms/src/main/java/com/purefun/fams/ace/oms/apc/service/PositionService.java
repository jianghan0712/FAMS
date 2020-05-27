/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.apc.service;

import com.purefun.fams.ace.oms.apc.request.PositionRequest;
import com.purefun.fams.ace.oms.apc.response.PositionRespond;

/**
 * @Classname: PositionService
 * @Description:
 * @author JiangHan
 * @date 2020-05-27 16:28:04
 */
public interface PositionService {

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
	PositionRespond unfreezePosition(PositionRequest request);

}
