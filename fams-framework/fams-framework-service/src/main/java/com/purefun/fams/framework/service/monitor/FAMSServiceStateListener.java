/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.service.monitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.purefun.fams.framework.service.monitor.domain.ServiceInstance;

/**
 * @Classname: FAMSServiceStateListener
 * @Description:
 * @author jianghan
 * @date 2020-03-03 19:23:27
 */
@Component
public class FAMSServiceStateListener {
	private static final Logger logger = LogManager.getLogger(FAMSServiceStateListener.class);

	/** 存储当前在线的服务 */
	private Map<String, HashSet<ServiceInstance>> onlineServerMap = new HashMap<String, HashSet<ServiceInstance>>();

	/**
	 * 服务下线，从上线map中删除
	 * 
	 * @MethodName: listen
	 * @author jianghan
	 * @date 2020-03-03 19:24:38
	 * @param event
	 */
	@EventListener
	public void listen(EurekaInstanceCanceledEvent event) {
		String serverFullName = event.getAppName();
		String serverName = event.getServerId().substring(event.getServerId().indexOf(":"),
				event.getServerId().lastIndexOf(":"));

		logger.info("[HB] service {} status change to offline", serverName);
	}

	@EventListener
	public void listen(EurekaInstanceRegisteredEvent event) {
		InstanceInfo instanceInfo = event.getInstanceInfo();
		String serverFullName = instanceInfo.getAppName();
		logger.info("[HB] Received Registered Event from service: {} ", serverFullName + ":" + instanceInfo.getPort());

		ServiceInstance instance = new ServiceInstance();
		instance.setServiceName(serverFullName);

//		onlineServerMap.put
//		if (service.getOnlineServerMap().putIfAbsent(serverFullName, ServiceStatusConstant.ONLINE_SERVER) == null) {
//			log.info("server {} online ", serverFullName);
//		} else {
//			log.info("server {} online failure", serverFullName + ":" + instanceInfo.getPort());
//		}
	}

	@EventListener
	public void listen(EurekaInstanceRenewedEvent event) {
		logger.info("[HB] Received HB from service: {}", event.getServerId());
	}

	@EventListener
	public void listen(EurekaRegistryAvailableEvent event) {
		logger.info("[HB] Registered Center Startup :{}", event.getSource());
	}

	public Map<String, HashSet<ServiceInstance>> getOnlineServerMap() {
		return onlineServerMap;
	}

	public void setOnlineServerMap(Map<String, HashSet<ServiceInstance>> onlineServerMap) {
		this.onlineServerMap = onlineServerMap;
	}

}
