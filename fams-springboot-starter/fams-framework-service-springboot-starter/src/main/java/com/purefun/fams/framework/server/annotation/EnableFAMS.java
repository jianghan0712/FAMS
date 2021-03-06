/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.server.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import com.purefun.fams.framework.ignite.cache.EnableIgniteCache;
import com.purefun.fams.framework.server.config.FAMSClientAutoConfigure;

/**
 * @Classname: EnableFAMS
 * @Description: 服务作为一个普通service提供服务时
 * @author jiang
 * @date 2020-02-11 14:37:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
@Documented
@Import({ FAMSClientAutoConfigure.class })
@EnableEurekaClient
@EnableIgniteCache
//@EnableDiscoveryClient
//@EnableFeignClients
public @interface EnableFAMS {

}
