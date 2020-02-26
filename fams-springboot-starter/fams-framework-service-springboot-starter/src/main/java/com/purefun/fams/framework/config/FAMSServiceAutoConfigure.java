/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Classname: FAMSAutoConfigure
 * @Description:
 * @author jiang
 * @date 2020-02-11 14:39:11
 */
@EnableConfigurationProperties({ FAMSServiceProperties.class, FAMSRedisConfig.class, FAMSServiceBeanFactory.class })
public class FAMSServiceAutoConfigure extends FAMSAutoConfigure {
	@Autowired
	private FAMSServiceProperties clientProperties;

}
