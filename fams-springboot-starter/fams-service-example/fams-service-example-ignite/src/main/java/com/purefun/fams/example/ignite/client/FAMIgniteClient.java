package com.purefun.fams.example.ignite.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.annotation.EnableFAMS;
import com.purefun.fams.framework.ignite.cache.EnableIgniteCache;

@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.example.ignite.client" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao" })
@EnableIgniteCache
public class FAMIgniteClient {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FAMIgniteClient.class);
		app.run(args);
	}

}
