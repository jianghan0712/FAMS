/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.util.constant;

/**
 * @Classname: RedisConstant
 * @Description: 存储redis相关常量
 * @author jianghan
 * @date 2020-02-26 20:48:54
 */
public interface RedisConstant {
	interface RedisCacheTableName {
		/** 全局配置表 */
		String GLOBAL_PARAM_TABLE = "GLOBAL_PARAM_TABLE";
		/** 全局证券信息表，之后再queen的rds维护，暂时由monitor维护 */
		String GLOBAL_SECURITY_INFO_TABLE = "GLOBAL_SECURITY_INFO_TABLE";
	}
}
