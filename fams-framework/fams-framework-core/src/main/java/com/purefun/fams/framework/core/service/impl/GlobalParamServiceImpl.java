/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.framework.common.enums.ExpireEnum;
import com.purefun.fams.framework.core.dao.FamsGlobalParamMapper;
import com.purefun.fams.framework.core.domain.FamsGlobalParam;
import com.purefun.fams.framework.core.service.GlobalParamService;
import com.purefun.fams.framework.core.service.RedisService;
import com.purefun.fams.framework.core.util.constant.RedisConstant;

/**
 * @Classname: GlobalParamServiceImpl
 * @Description:
 * @author jianghan
 * @date 2020-02-26 14:00:13
 */
public class GlobalParamServiceImpl implements GlobalParamService {
	@Autowired
	private RedisService redisCache;

	@Autowired
	private FamsGlobalParamMapper globalParamMapper;

	@Override
	public List<FamsGlobalParam> getParamValueWithCache(String paramScope, String paramName) {
		List<FamsGlobalParam> paramValues = null;

		/** 如果paramName或者paramScope没有添值，直接从数据库获取 ，cache中不好处理 */
		if (StringUtils.isBlank(paramName) || StringUtils.isBlank(paramScope)) {
			paramValues = globalParamMapper.selectByParamNameAndScope(paramName, paramScope);
			for (FamsGlobalParam each : paramValues) {
				String key = new StringBuilder(each.getParamScope()).append(CommonUtil.CharUtil.caret)
						.append(each.getParamName()).toString();
				redisCache.hset(RedisConstant.RedisCacheTableName.GLOBAL_PARAM_TABLE, key, each,
						ExpireEnum.REDIS_CACHE_EXPIRE.getTime());
			}
			return paramValues;
		}

		FamsGlobalParam ret = null;
		String key = new StringBuilder(paramScope).append(CommonUtil.CharUtil.caret).append(paramName).toString();
		Object value = redisCache.hget(RedisConstant.RedisCacheTableName.GLOBAL_PARAM_TABLE, key.toString());
		if (value instanceof FamsGlobalParam) {
			ret = (FamsGlobalParam) value;
		}

		// 如果redis中没有，就从数据库中取出并放入redis
		if (ret == null) {
			paramValues = globalParamMapper.selectByParamNameAndScope(paramName, paramScope);
		} else {
			paramValues = Collections.singletonList(ret);
		}

		return paramValues;
	}

	@Override
	public boolean updateParamValue(String paramName, String paramValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refresh(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(String key, String value) {
		// TODO Auto-generated method stub

	}
}
