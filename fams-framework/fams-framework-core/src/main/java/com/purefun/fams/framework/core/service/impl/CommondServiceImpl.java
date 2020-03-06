/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.common.util.StringUtil;
import com.purefun.fams.framework.common.enums.CommondEnum;
import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.exception.FAMSException;
import com.purefun.fams.framework.common.util.AssertUtil;
import com.purefun.fams.framework.core.http.LogInterceptor;
import com.purefun.fams.framework.core.http.SelfHttpHeadInterceptor;
import com.purefun.fams.framework.core.service.CommondService;
import com.purefun.fams.framework.core.util.UrlUtil;

/**
 * @Classname: CommondServiceImpl
 * @Description: 处理单目的简单命令，如exit：服务结束 等
 * @author jianghan
 * @date 2020-03-05 17:20:45
 */
public class CommondServiceImpl implements CommondService {
	private static final Logger logger = LogManager.getLogger(CommondServiceImpl.class);

	/** 向注册中心发送指令 */
	private RestTemplate selfTemplate;

	private String URL = null;

	@Value("${server.port}")
	private int port;

	@Value("${spring.application.name}")
	private String serviceName;

	@PostConstruct
	public void init() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new SelfHttpHeadInterceptor());
		restTemplate.getInterceptors().add(new LogInterceptor());
		this.selfTemplate = restTemplate;
		URL = StringUtil.append(UrlUtil.selfURL, CommonUtil.CharUtil.colon, String.valueOf(port),
				UrlUtil.serviceRegistryURL);
	}

	@Override
	public void executeCommond(String commond) throws FAMSException {
		String[] commondLine = commond.split(CommonUtil.CharUtil.blank);

		CommondEnum commondInstance = CommondEnum.getByCode(commondLine[0]);
		checkCommond(commondInstance, commondLine);

		try {
			Method method = this.getClass().getDeclaredMethod(commondInstance.getMethodName(), String[].class);
			method.invoke(this, new Object[] { commondLine });
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error("{}", e);
		}
	}

	/**
	 * 检查commond的有效性
	 * 
	 * @MethodName: checkCommond
	 * @author jianghan
	 * @date 2020-03-05 19:03:16
	 * @param commondInstance
	 * @param commondLine
	 */
	private void checkCommond(CommondEnum commondInstance, String[] commondLine) {
		// TODO Auto-generated method stub
		AssertUtil.assertNotNull(commondInstance, ErrorCodeEnum.COMMOND_UNKNOWN);
		AssertUtil.assertEquals(commondInstance.getParamSize(), commondLine.length - 1, ErrorCodeEnum.PARAM_EXCEPTION);
	}

	/********************** 命令的实际执行方法 ***********************************/

	/**
	 ** 执行服务退出命令
	 * 
	 * @MethodName: exitMethod
	 * @author jianghan
	 * @date 2020-03-05 22:12:43
	 * @param params 无参数
	 */
	private void exitMethod(String... params) {
		logger.info("service {} exit", serviceName);
		selfTemplate.postForEntity(URL + "?status=DOWN", "", String.class);
		System.exit(0);
	}

	/**
	 ** 执行服务下线的指令
	 * 
	 * @MethodName: downMethod
	 * @author jianghan
	 * @date 2020-03-06 12:00:29
	 * @param params
	 */
	private void downMethod(String... params) {
		logger.info("service {} down", serviceName);
		selfTemplate.postForEntity(URL + "?status=DOWN", "", String.class);
	}

	/**
	 ** 执行服务上线的指令
	 * 
	 * @MethodName: upMethod
	 * @author jianghan
	 * @date 2020-03-06 12:01:04
	 * @param params
	 */
	private void upMethod(String... params) {
		logger.info("service {} up", serviceName);
		selfTemplate.postForEntity(URL + "?status=UP", "", String.class);
	}

}
