package com.purefun.fams.ace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.annotation.EnableFAMS;
import com.purefun.fams.framework.ignite.EnableIgnite;
import com.purefun.fams.framework.ignite.cache.EnableIgniteCache;

@EnableIgniteCache
@EnableIgnite
@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.ace.*" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao", "com.purefun.fams.ace.oms.dao" })
public class OrderManagerService {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OrderManagerService.class);
		app.run(args);
	}

}
