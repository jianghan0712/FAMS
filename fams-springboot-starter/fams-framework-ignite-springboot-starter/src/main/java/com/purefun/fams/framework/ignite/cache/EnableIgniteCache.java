/**
 * 
 */
package com.purefun.fams.framework.ignite.cache;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.purefun.fams.framework.ignite.EnableIgnite;

/**
 * @Classname: EnableIgniteCache
 * @Description:ignite节点-cache启用
 * @author jiang
 * @date 2020-02-20 23:24:25
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
@EnableIgnite
@Import({ IgniteCacheAutoConfigure.class })
public @interface EnableIgniteCache {

}
