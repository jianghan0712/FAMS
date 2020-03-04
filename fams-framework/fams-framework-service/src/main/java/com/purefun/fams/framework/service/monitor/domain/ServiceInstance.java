/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.service.monitor.domain;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: ServiceInstance
 * @Description:
 * @author jianghan
 * @date 2020-03-03 19:17:02
 */
public class ServiceInstance extends BaseDomain {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 1059426019985618328L;

	private String serviceName;

	private String env;

	private String instance;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

}
