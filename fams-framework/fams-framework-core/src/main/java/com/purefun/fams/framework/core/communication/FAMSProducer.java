/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.util.AssertUtil;

/**
 * @Classname: FAMSProducer
 * @Description: 简单封装下层mq的发送
 * @author jiang
 * @date 2020-02-11 18:20:40
 */
public class FAMSProducer {
	private static final Logger logger = LogManager.getLogger(FAMSProducer.class);
	private KafkaTemplate<String, byte[]> kafkaTemplate;

	/**
	 * 初始化生产模块
	 * 
	 * @MethodName: initProducer
	 * @author jiang
	 * @date 2020-02-11 21:59:13
	 * @param kafkaTemplate         publish
	 * @param replyingKafkaTemplate request-reply
	 */
	public void initProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	/**
	 * 广播bo，无回调方法
	 * 
	 * @MethodName: publish
	 * @author jiang
	 * @date 2020-02-11 19:44:30
	 * @param bo
	 * @return
	 */
	public void publish(ICommon_OTW bo) {
		AssertUtil.assertNotNull(bo, ErrorCodeEnum.UNKNOWN_EXCEPTION);

		ListenableFuture<SendResult<String, byte[]>> listenableFuture = kafkaTemplate.send(bo.getDestination(),
				bo.getBuilder().build().toByteArray());

		SuccessCallback<SendResult> successCallback = new SuccessCallback<SendResult>() {
			@Override
			public void onSuccess(SendResult result) {
				logger.info("publish bo:{}", result.getProducerRecord());
				// TODO 插入缓存对应的队列上
			}
		};

		FailureCallback failureCallback = new FailureCallback() {
			@Override
			public void onFailure(Throwable ex) {
				logger.error("publish bo fail, Throwable={}", ex);
			}
		};
		listenableFuture.addCallback(successCallback, failureCallback);
	}

}
