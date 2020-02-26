/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import com.purefun.fams.framework.core.communication.FAMSProducer;
import com.purefun.fams.framework.core.util.RedisUtil;

/**
 * @Classname: FAMSAutoConfigure
 * @Description: fams服务共有的config，目前包括kafka、redis
 * @author jianghan
 * @date 2020-02-26 18:40:45
 */
public class FAMSAutoConfigure {
	@Autowired
	private KafkaTemplate<String, byte[]> kafkaTemplate;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Bean(name = "producer")
	public FAMSProducer createProducer() {
		FAMSProducer producer = new FAMSProducer();
		producer.initProducer(kafkaTemplate);
		return producer;
	}

	/**
	 * redis操作类
	 * 
	 * @MethodName: getRedis
	 * @author jianghan
	 * @date 2020-02-26 20:32:24
	 * @return
	 */
	@Bean
	public RedisUtil getRedis() {
		RedisUtil redisUtil = new RedisUtil(redisTemplate);
		return redisUtil;
	}

}
