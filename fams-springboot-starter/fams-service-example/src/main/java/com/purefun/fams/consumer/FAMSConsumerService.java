package com.purefun.fams.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.server.annotation.EnableFAMS;

@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.consumer" })
public class FAMSConsumerService {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(FAMSConsumerService.class);
		application.run(args);
	}
}
