package com.purefun.fams.example.rpc.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoAPI {

	@GetMapping("/hello")
	public String hello() {
		System.out.println("我被调到了");
		return "hello, DEMO!";
	}

}
