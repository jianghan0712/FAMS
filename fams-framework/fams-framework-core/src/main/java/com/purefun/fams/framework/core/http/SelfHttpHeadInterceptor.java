/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.http;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @Classname: SelfHttpHeadInterceptor
 * @Description: 对自身调取命令时的拦截器
 * @author jianghan
 * @date 2020-03-05 23:14:42
 */
public class SelfHttpHeadInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger logger = LogManager.getLogger(SelfHttpHeadInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpHeaders headers = request.getHeaders();

		headers.set("Content-Type", "application/vnd.spring-boot.actuator.v2+json");
		headers.set("charset", "UTF-8");

		return execution.execute(request, body);
	}

}