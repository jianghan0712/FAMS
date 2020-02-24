package com.purefun.fams.service.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerServiceImpl implements ConsumerService {

	@Override
	public String hello(String name) {
		// TODO Auto-generated method stub
		return "hello" + name;
	}

}
