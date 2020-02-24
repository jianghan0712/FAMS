package com.purefun.fams.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ClientService-${Env}")
public interface RemoteService {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	String hello();

}
