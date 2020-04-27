package com.purefun.fams.ace.mdc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.annotation.EnableFAMS;

@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.ace.*" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao" })
public class MarketDataCenterService {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MarketDataCenterService.class);
		app.run(args);
	}

}
