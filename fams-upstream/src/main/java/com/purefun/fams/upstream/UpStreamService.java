package com.purefun.fams.upstream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.purefun.fams.framework.annotation.EnableFAMS;

@EnableFAMS
@SpringBootApplication
@EnableCaching
@EnableScheduling
@ComponentScan(basePackages = { "com.purefun.fams.upstream" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao", "com.purefun.fams.upstream.dao" })
public class UpStreamService {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UpStreamService.class);
		app.run(args);
	}

}
