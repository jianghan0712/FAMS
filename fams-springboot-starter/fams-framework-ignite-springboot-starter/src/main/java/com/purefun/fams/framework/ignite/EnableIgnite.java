/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * @Classname: EnableIgnite
 * @Description: ignite节点的启动类
 * @author jianghan
 * @date 2020-02-23 12:54:53
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
@Import({ IgniteAutoConfigure.class })
public @interface EnableIgnite {

}
