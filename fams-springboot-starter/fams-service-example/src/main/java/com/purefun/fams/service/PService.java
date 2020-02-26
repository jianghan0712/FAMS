package com.purefun.fams.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.annotation.EnableFAMSRegisterCenter;
import com.purefun.fams.framework.ignite.cache.EnableIgniteCache;

@EnableIgniteCache
@EnableFAMSRegisterCenter
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.service" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao" })
//@ImportResource(locations="classpath:config/${ServiceName}/${Env}/${Instance}/Ignite.xml")
public class PService {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(PService.class);
		application.run(args);
	}
}
