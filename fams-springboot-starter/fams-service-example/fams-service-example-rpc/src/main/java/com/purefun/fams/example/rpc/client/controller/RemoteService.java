package com.purefun.fams.example.rpc.client.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "rpcServer-${Env}")
public interface RemoteService {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	String hello();

}
