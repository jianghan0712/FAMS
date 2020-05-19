/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.apc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.purefun.fams.ace.oms.apc.request.CashOpRequest;
import com.purefun.fams.ace.oms.apc.request.CashRequest;
import com.purefun.fams.ace.oms.apc.request.DealRequest;
import com.purefun.fams.ace.oms.apc.request.PositionRequest;
import com.purefun.fams.ace.oms.apc.response.CashOpRespond;
import com.purefun.fams.ace.oms.apc.response.CashRespond;
import com.purefun.fams.ace.oms.apc.response.DealRespond;
import com.purefun.fams.ace.oms.apc.response.PositionRespond;
import com.purefun.fams.ace.oms.apc.service.APCService;

/**
 * @Classname: APCServiceImpl
 * @Description:
 * @author jianghan
 * @date 2020-05-17 20:49:32
 */
public class APCServiceImpl implements APCService {
	private static final Logger logger = LogManager.getLogger(APCServiceImpl.class);

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.ace.oms.apc.service.APCService#freezeCash(com.purefun.fams.ace.oms.apc.request.CashRequest)
	 * @param request
	 * @return
	 */

	@Override
	public CashRespond freezeCash(CashRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.ace.oms.apc.service.APCService#unfreezeCash(com.purefun.fams.ace.oms.apc.request.CashRequest)
	 * @param request
	 * @return
	 */

	@Override
	public CashRespond unfreezeCash(CashRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.ace.oms.apc.service.APCService#freezePosition(com.purefun.fams.ace.oms.apc.request.PositionRequest)
	 * @param request
	 * @return
	 */

	@Override
	public PositionRespond freezePosition(PositionRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.ace.oms.apc.service.APCService#unfreezeCash(com.purefun.fams.ace.oms.apc.request.PositionRequest)
	 * @param request
	 * @return
	 */

	@Override
	public PositionRespond unfreezeCash(PositionRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.ace.oms.apc.service.APCService#cutCashAndAddPosition(com.purefun.fams.ace.oms.apc.request.DealRequest)
	 * @param request
	 * @return
	 */

	@Override
	public DealRespond cutCashAndAddPosition(DealRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.ace.oms.apc.service.APCService#cutPositionAndAddCash(com.purefun.fams.ace.oms.apc.request.DealRequest)
	 * @param request
	 * @return
	 */

	@Override
	public DealRespond cutPositionAndAddCash(DealRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.ace.oms.apc.service.APCService#cashIn(com.purefun.fams.ace.oms.apc.request.CashOpRequest)
	 * @param request
	 * @return
	 */

	@Override
	public CashOpRespond cashIn(CashOpRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.ace.oms.apc.service.APCService#cashOut(com.purefun.fams.ace.oms.apc.request.CashOpRequest)
	 * @param request
	 * @return
	 */

	@Override
	public CashOpRespond cashOut(CashOpRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.ace.oms.apc.service.APCService#cashInAndCashOut(com.purefun.fams.ace.oms.apc.request.CashOpRequest)
	 * @param request
	 * @return
	 */

	@Override
	public CashOpRespond cashInAndCashOut(CashOpRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
