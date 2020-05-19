/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Classname: IgniteAutoConfigure
 * @Description: 配置默认的ignite主容器
 * @author jiang
 * @date 2020-02-21 00:20:56
 */
@ImportResource(locations = "${fams.framework.ignite.path}")
@Configuration
public class IgniteAutoConfigure {
	/** 从默认配置中读取到的bean */
	@Autowired
	private IgniteConfiguration config;

	/**
	 * 生成主ignite的容器
	 * 
	 * @MethodName: getIgnite
	 * @author jiang
	 * @date 2020-02-21 00:24:25
	 * @return
	 */
	@Bean
	public Ignite getIgnite() {
//		return Ignition.start(config);
		return Ignition.start("classpath:ignite-oms2.xml");
	}
}
