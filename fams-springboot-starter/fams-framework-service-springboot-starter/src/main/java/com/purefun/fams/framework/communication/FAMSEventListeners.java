/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.communication;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname: FAMSEventListener
 * @Description: 消费者注解，封装了@KafkaListener，通过 @see FAMSListenerBeanPostPocessor
 *               进行拦截设置
 * @author jiang
 * @date 2020-02-11 14:37:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD,
		java.lang.annotation.ElementType.ANNOTATION_TYPE })
@Documented
public @interface FAMSEventListeners {
	FAMSEventListener[] value();
}
