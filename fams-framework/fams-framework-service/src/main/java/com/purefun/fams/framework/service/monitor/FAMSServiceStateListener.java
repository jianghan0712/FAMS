/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.service.monitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.common.util.StringUtil;
import com.purefun.fams.framework.core.domain.ServiceInstance;
import com.purefun.fams.framework.core.service.RedisService;

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

	@Autowired
	private RedisService redis;

	@Autowired
	private ServiceInstance myself;

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
		String[] envInfo = event.getServerId().substring(0, event.getServerId().indexOf(":")).split("-");
		HashSet<ServiceInstance> instanceSet = onlineServerMap.get(envInfo[0]);
		if (instanceSet == null) {
			logger.warn("[HB] 服务下线 ： {}-{}-{}", envInfo[0], envInfo[1], envInfo[2]);
			return;
		}
		for (Iterator it = instanceSet.iterator(); it.hasNext();) {
			ServiceInstance instance = (ServiceInstance) it.next();
			if (instance.getEnv().equalsIgnoreCase(envInfo[1]) && instance.getInstance().equalsIgnoreCase(envInfo[2])) {
				instanceSet.remove(instance);
				clearRedis(instance);
				break;
			}
		}
		if (instanceSet.isEmpty()) {
			// 清掉map
			onlineServerMap.remove(envInfo[0]);
		}

		logger.info("[HB] service {} status change to offline", envInfo[0]);
	}

	@EventListener
	public void listen(EurekaInstanceRegisteredEvent event) {
		InstanceInfo instanceInfo = event.getInstanceInfo();
		String serverFullName = instanceInfo.getAppName();
		String instanceId = instanceInfo.getInstanceId();
		String[] envInfo = instanceId.substring(0, instanceId.indexOf(":")).split("-");
		logger.info("[HB] Received Registered Event from service: {} ", serverFullName + ":" + instanceInfo.getPort());

		ServiceInstance instance = new ServiceInstance();
		instance.setServiceName(envInfo[0]);
		instance.setEnv(envInfo[1]);
		instance.setInstance(envInfo[2]);

		HashSet<ServiceInstance> instanceSet = onlineServerMap.get(instance.getServiceName());
		if (instanceSet == null) {
			instanceSet = new HashSet<ServiceInstance>();
		}
		instanceSet.add(instance);
		onlineServerMap.put(instance.getServiceName(), instanceSet);
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

	/**
	 * 服务下线后，清除缓存
	 * 
	 * @MethodName: clearRedis
	 * @author 015979
	 * @date 2020-03-13 11:35:36
	 * @param instance
	 */
	private void clearRedis(ServiceInstance instance) {
		String prefix = StringUtil.append(instance.getServiceName(), CommonUtil.CharUtil.rod, instance.getEnv(),
				CommonUtil.CharUtil.rod, instance.getInstance());
		redis.del(prefix);
	}

	@PreDestroy
	private void destory() {
		clearRedis(myself);
	}
}
