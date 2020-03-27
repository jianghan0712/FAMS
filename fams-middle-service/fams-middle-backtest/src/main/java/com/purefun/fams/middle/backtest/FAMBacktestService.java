package com.purefun.fams.middle.backtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.purefun.fams.framework.annotation.EnableFAMS;
import com.purefun.fams.framework.ignite.cache.EnableIgniteCache;
import com.purefun.fams.middle.strategy.config.StragtegyAutoConfigure;

@EnableFAMS
@EnableIgniteCache
@SpringBootApplication
@ComponentScan(basePackages = { "com.purefun.fams.middle.*" })
@MapperScan(basePackages = { "com.purefun.fams.framework.core.dao" })
@Import({ StragtegyAutoConfigure.class })
public class FAMBacktestService {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FAMBacktestService.class);
		app.run(args);
	}

}
