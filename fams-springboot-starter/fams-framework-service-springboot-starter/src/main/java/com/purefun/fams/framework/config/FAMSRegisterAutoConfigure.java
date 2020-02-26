/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 服务作为
 * 
 * @Classname: FAMSAutoConfigure
 * @Description:
 * @author jiang
 * @date 2020-02-11 14:39:11
 */
@EnableConfigurationProperties({ FAMSRegisterProperties.class, FAMSRedisConfig.class, FAMSServiceBeanFactory.class })
public class FAMSRegisterAutoConfigure extends FAMSAutoConfigure {
	@Autowired
	private FAMSRegisterProperties registerProperties;

}
