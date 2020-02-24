package com.purefun.fams.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.server.annotation.EnableFAMS;

@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.client" })
public class FAMSService {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(FAMSService.class);
		application.run(args);
	}
}
