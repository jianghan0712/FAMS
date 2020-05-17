package com.purefun.fams.queen.rds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.purefun.fams.framework.annotation.EnableFAMS;

@EnableFAMS
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.queen.*" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao", "com.purefun.fams.queen.rds.dao" })
public class ReferenceDataService {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ReferenceDataService.class);
		app.run(args);
	}

}
