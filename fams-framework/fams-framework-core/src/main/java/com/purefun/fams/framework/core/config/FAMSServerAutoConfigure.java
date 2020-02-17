/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import com.purefun.fams.framework.core.communication.FAMSProducer;

/**
 * 服务作为
 * 
 * @Classname: FAMSAutoConfigure
 * @Description:
 * @author jiang
 * @date 2020-02-11 14:39:11
 */
@EnableConfigurationProperties({ FAMSServerProperties.class })
public class FAMSServerAutoConfigure {
	@Autowired
	private FAMSServerProperties serverProperties;

	@Autowired
	private KafkaTemplate<String, byte[]> kafkaTemplate;

	@Bean(name = "producer")
	public FAMSProducer createProducer() {
		FAMSProducer producer = new FAMSProducer();
		producer.initProducer(kafkaTemplate);
		return producer;
	}
}
