package com.purefun.fams.service.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CustumerService")
public interface ConsumerService {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	String hello(@RequestParam("name") String name);
}
