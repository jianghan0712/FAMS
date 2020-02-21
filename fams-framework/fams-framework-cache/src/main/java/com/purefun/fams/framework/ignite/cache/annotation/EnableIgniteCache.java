/**
 * 
 */
package com.purefun.fams.framework.ignite.cache.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.purefun.fams.framework.core.config.FAMSClientAutoConfigure;
import com.purefun.fams.framework.ignite.cache.config.IgniteCacheAutoConfigure;

/**
 * 
 * @Classname: EnableIgniteCache
 * @Description: 
 * @author jiang
 * @date 2020-02-20 23:24:25
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
@Import({ IgniteCacheAutoConfigure.class })
public @interface EnableIgniteCache {
	
}
