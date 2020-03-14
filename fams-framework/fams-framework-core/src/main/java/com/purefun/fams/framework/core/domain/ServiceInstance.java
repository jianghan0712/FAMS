/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.domain;

import org.springframework.beans.factory.annotation.Value;

import com.purefun.fams.common.domain.BaseDomain;

/**
 * @Classname: ServiceInstance
 * @Description: 保存service实体的相关信息
 * @author jianghan
 * @date 2020-03-03 19:17:02
 */
public class ServiceInstance extends BaseDomain {
	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 1059426019985618328L;

	@Value("${ServiceName}")
	private String serviceName;

	@Value("${Env}")
	private String env;

	@Value("${Instance}")
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceInstance other = (ServiceInstance) obj;
		if (env.equalsIgnoreCase(other.getEnv()) && serviceName.equalsIgnoreCase(other.getServiceName())
				&& instance.equalsIgnoreCase(other.getInstance()))
			return true;

		return false;
	}

	@Override
	public int hashCode() {

		int result = 17;
		result = 31 * result + serviceName.hashCode();
		result = 31 * result + env.hashCode();
		result = 31 * result + instance.hashCode();

		return result;
	}

}
