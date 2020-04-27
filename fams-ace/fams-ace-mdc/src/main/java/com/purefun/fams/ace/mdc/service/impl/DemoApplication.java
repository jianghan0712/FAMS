/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.mdc.service.impl;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purefun.fams.ace.sop.ArrayOfString;
import com.purefun.fams.ace.sop.ChinaStockWebServiceSoap;

/**
 * @Classname: cont
 * @Description:
 * @author jianghan
 * @date 2020-04-26 17:23:48
 */
@SpringBootApplication
@RestController
@RequestMapping("/soap")
public class DemoApplication {

//	@Autowired
	private ChinaStockWebServiceSoap stockSoap;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@RequestMapping("/{ip}")
//	public ArrayOfString searchIp(@PathVariable("ip") String ip) {
//		ArrayOfString response = soap.getCountryCityByIp(ip);
//		return response;
//	}

	@RequestMapping("/{stock}")
	public ArrayOfString stockInfo(@PathVariable("stock") String stock) {
		ArrayOfString response = stockSoap.getStockInfoByCode(stock);
		List<String> list = response.getString();
		for (String e : list) {
			System.out.println(e);
		}
		return response;
	}
}
