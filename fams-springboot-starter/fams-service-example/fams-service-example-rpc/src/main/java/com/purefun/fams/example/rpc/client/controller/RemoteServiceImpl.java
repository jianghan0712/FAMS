package com.purefun.fams.example.rpc.client.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import com.purefun.fams.framework.common.ace.oms.apc.request.CashRequest;
import com.purefun.fams.framework.common.ace.oms.apc.respond.CashRespond;

@RestController
public class RemoteServiceImpl {
	@Resource
	private RemoteService service;

	public CashRespond freeze(CashRequest request) {
		return service.freeze(request);
	}

	public String hello() {
		return service.hello();
	}
}
