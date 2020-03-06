package com.purefun.fams.example.rpc.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.annotation.EnableFAMS;

@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.example.rpc.server" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao" })
public class FAMSrpcServer {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FAMSrpcServer.class);
		app.run(args);
	}

}
