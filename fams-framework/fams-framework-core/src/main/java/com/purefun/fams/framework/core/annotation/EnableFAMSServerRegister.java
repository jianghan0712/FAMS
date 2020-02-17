/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Import;

import com.purefun.fams.framework.core.config.FAMSServerAutoConfigure;

/**
 * @Classname: EnableFAMSServerRegister
 * @Description: 当服务担当EurekaServer职责时
 * @author jiang
 * @date 2020-02-11 14:37:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
@Documented
@Import({ FAMSServerAutoConfigure.class })
@EnableEurekaServer
public @interface EnableFAMSServerRegister {

}
