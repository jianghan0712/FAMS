/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.service.test.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.otw.TestBO2_OTW;
import com.purefun.fams.core.bo.otw.TestBO_OTW;
import com.purefun.fams.framework.communication.FAMSEventListener;
import com.purefun.fams.framework.core.communication.listener.IEventListener;

/**
 * @Classname: KafkaConsumer
 * @Description:
 * @author jiang
 * @date 2020-02-11 17:15:40
 */

@Component
public class KafkaConsumer implements IEventListener {
	private static final Logger logger = LogManager.getLogger(KafkaConsumer.class);

	@FAMSEventListener(topics = { "fams.core.rpc.testone" })
	public void process(byte[] content) throws InvalidProtocolBufferException {
		TestBO_OTW bo = new TestBO_OTW(content);
		logger.info("receive {} bo : {}", TestBO_OTW.class.getName(), bo.toString());
	}

	@FAMSEventListener(topics = { "fams.core.rpc.testtwo" })
	public void process2(byte[] content) throws InvalidProtocolBufferException {
		TestBO2_OTW bo = new TestBO2_OTW(content);
		logger.info("receive {} bo : {}", TestBO2_OTW.class.getName(), bo.toString());
	}
}
