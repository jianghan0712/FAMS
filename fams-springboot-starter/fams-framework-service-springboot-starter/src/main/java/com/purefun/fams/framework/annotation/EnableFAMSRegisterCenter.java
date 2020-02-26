/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.purefun.fams.framework.config.FAMSRegisterAutoConfigure;

/**
 * @Classname: EnableFAMSServerRegister
 * @Description: 当服务担当注册中心时
 * @author jiang
 * @date 2020-02-11 14:37:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
@Documented
@Import({ FAMSRegisterAutoConfigure.class })
//@ComponentScan(basePackages = { "com.purefun.fams.core.framework" })
@EnableEurekaServer
@EnableFeignClients
public @interface EnableFAMSRegisterCenter {

}
