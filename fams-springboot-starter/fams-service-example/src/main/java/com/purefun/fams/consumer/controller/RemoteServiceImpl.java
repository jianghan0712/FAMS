package com.purefun.fams.consumer.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoteServiceImpl {
	@Resource
	private RemoteService service;

	@GetMapping("/hello")
	public String hello() {
		return service.hello();
	}
}
