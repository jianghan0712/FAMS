/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import com.purefun.fams.framework.core.communication.FAMSProducer;

/**
 * @Classname: FAMSAutoConfigure
 * @Description:
 * @author jiang
 * @date 2020-02-11 14:39:11
 */
@EnableConfigurationProperties({ FAMSClientProperties.class })
public class FAMSClientAutoConfigure {
	@Autowired
	private FAMSClientProperties clientProperties;

	@Autowired
	private KafkaTemplate<String, byte[]> kafkaTemplate;

	@Bean(name = "producer")
	public FAMSProducer createProducer() {
		FAMSProducer producer = new FAMSProducer();
		producer.initProducer(kafkaTemplate);
		return producer;
	}
}
