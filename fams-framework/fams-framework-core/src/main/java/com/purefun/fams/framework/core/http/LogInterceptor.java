package com.purefun.fams.framework.core.http;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LogInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger logger = LogManager.getLogger(LogInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		logger.info("===========================request begin================================================");
		logger.info("URI         : {}", request.getURI());
		logger.info("Method      : {}", request.getMethod());
		logger.info("Headers     : {}", request.getHeaders());
		logger.info("Request body: {}", new String(body, "UTF-8"));
		logger.info("==========================request end================================================");

		return execution.execute(request, body);
	}

}
