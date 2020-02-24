/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.register.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Classname: FAMSServerProperties
 * @Description: FAMS的server端常量
 * @author jiang
 * @date 2020-02-11 14:41:39
 */
@ConfigurationProperties(prefix = "fams.framework.server")
public class FAMSServerProperties {
	public static final String SERVER_PREFIX = "fams.framework.server";

	private String serviceName;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
