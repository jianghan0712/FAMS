package com.purefun.fams.ace.oms.apc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.purefun.fams.ace.oms.apc.service.CashService;
import com.purefun.fams.framework.common.ace.oms.apc.request.CashOpRequest;
import com.purefun.fams.framework.common.ace.oms.apc.request.CashRequest;
import com.purefun.fams.framework.common.ace.oms.apc.respond.CashOpRespond;
import com.purefun.fams.framework.common.ace.oms.apc.respond.CashRespond;

/**
 * cash类对外暴露接口
 * 
 * @Classname: CashAPI
 * @Description:
 * @author jianghan
 * @date 2020-06-26 15:22:53
 */
@RestController
@RequestMapping("/ace/oms/cash")
public class CashAPI {
	@Autowired
	private CashService cashService;

	@RequestMapping(value = "/freeze", method = RequestMethod.POST)
	public CashRespond freeze(@RequestBody CashRequest request) {
		CashRespond response = cashService.freezeCash(request);
		return response;
	}

	@RequestMapping(value = "/unfreeze", method = RequestMethod.POST)
	public CashRespond unfreeze(@RequestBody CashRequest request) {
		CashRespond response = cashService.unfreezeCash(request);
		return response;
	}

	@RequestMapping(value = "/cashin", method = RequestMethod.POST)
	public CashOpRespond cashin(@RequestBody CashOpRequest request) {
		CashOpRespond response = cashService.cashIn(request);
		return response;
	}

	@RequestMapping(value = "/cashout", method = RequestMethod.POST)
	public CashOpRespond cashout(@RequestBody CashOpRequest request) {
		CashOpRespond response = cashService.cashOut(request);
		return response;
	}

	@RequestMapping(value = "/cashinAndcashout", method = RequestMethod.POST)
	public CashOpRespond cashInAndOut(@RequestBody CashOpRequest request) {
		CashOpRespond response = cashService.cashInAndCashOut(request);
		return response;
	}
}
