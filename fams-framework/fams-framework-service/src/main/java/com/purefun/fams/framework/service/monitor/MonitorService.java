/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.service.monitor;

import java.util.HashSet;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purefun.fams.framework.core.domain.ServiceInstance;

/**
 * @Classname: MonitorService
 * @Description:
 * @author jianghan
 * @date 2020-03-03 12:53:00
 */
public class MonitorService {
	private Map<String, HashSet<ServiceInstance>> onlineServerMap;

	@Autowired
	private FAMSServiceStateListener stateListner;

	@PostConstruct
	public void init() {
		this.onlineServerMap = stateListner.getOnlineServerMap();
	}

}
