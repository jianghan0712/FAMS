/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.example.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.annotation.EnableFAMSRegisterCenter;

/**
 * @Classname: FAMSRegisterCenter
 * @Description: FAMS注册中心
 * @author jianghan
 * @date 2020-03-03 18:19:30
 */
@EnableFAMSRegisterCenter
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.example.monitor", "com.purefun.fams.framework.service.monitor" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao" })
public class FAMSRegisterCenter {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(FAMSRegisterCenter.class);
		application.run(args);
	}
}
