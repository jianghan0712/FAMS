package com.purefun.fams.example.ignite.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.annotation.EnableFAMS;
import com.purefun.fams.framework.ignite.cache.EnableIgniteCache;

@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.example.ignite.service" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao" })
@EnableIgniteCache
public class FAMIgniteService {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FAMIgniteService.class);
		app.run(args);
	}

}
