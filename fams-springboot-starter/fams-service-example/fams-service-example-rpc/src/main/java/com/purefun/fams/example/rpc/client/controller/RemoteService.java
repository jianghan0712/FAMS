package com.purefun.fams.example.rpc.client.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.purefun.fams.framework.common.ace.oms.apc.request.CashRequest;
import com.purefun.fams.framework.common.ace.oms.apc.respond.CashRespond;

@FeignClient(name = "OrderManagerService-${Env}")
public interface RemoteService {

	@RequestMapping(value = "/ace/oms/cash/freeze", method = RequestMethod.POST)
	CashRespond freeze(@RequestBody CashRequest request);

	@RequestMapping(value = "/ace/oms/apc/hello")
	String hello();

}
