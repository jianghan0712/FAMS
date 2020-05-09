package com.purefun.fams.example.rpc.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.annotation.EnableFAMS;

@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.example.rpc.client" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao" })
public class FAMSrpcClient {
//	public static void main(String[] args) {
//		SpringApplication application = new SpringApplication(FAMSrpcClient.class);
//		application.run(args);
//	}
}
