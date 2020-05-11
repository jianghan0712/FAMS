/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.upstream.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.purefun.fams.framework.core.service.CacheService;
import com.purefun.fams.framework.core.service.impl.GlobalParamServiceImpl;
import com.purefun.fams.framework.core.service.impl.RedisCacheLoaderServiceImpl;
import com.purefun.fams.framework.core.util.constant.RedisConstant;
import com.purefun.fams.queen.rds.FamsSecurityBasicinfoBO;
import com.purefun.fams.upstream.enums.ResponseEnum;
import com.purefun.fams.upstream.response.ResponseResult;

/**
 * @Classname: SecurityInfoController
 * @Description:
 * @author jianghan
 * @date 2020-05-03 13:20:18
 */
@RestController
@RequestMapping("/vue-element-admin/security/")
public class SecurityInfoController {
	private static final Logger logger = LogManager.getLogger(SecurityInfoController.class);
	@Autowired
	private RedisCacheLoaderServiceImpl service;
	@Autowired
	private CacheService cache;
	@Autowired
	private GlobalParamServiceImpl configService;

	@GetMapping("/list")
	public ResponseResult<Page<FamsSecurityBasicinfoBO>> paging(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		Page<FamsSecurityBasicinfoBO> page = new Page<FamsSecurityBasicinfoBO>();
		ResponseResult<Page<FamsSecurityBasicinfoBO>> ret = new ResponseResult<Page<FamsSecurityBasicinfoBO>>();
		try {
			int size = cache.globalCacheHSize(RedisConstant.RedisCacheTableName.GLOBAL_SECURITY_INFO_TABLE);

			List<FamsSecurityBasicinfoBO> record = cache
					.globalCacheHMGet(RedisConstant.RedisCacheTableName.GLOBAL_SECURITY_INFO_TABLE, pageNo, pageSize);

			page.setTotal(size);
			page.setSize(size / pageSize + 1);
			page.setCurrent(pageNo);
			page.setRecords(record);
			ret.setResult(page);
		} catch (Exception e) {
			ret.fail(ResponseEnum.FAIL, e.getMessage());
			logger.info("获取信息出错！{}", e);
			return ret;
		}
		return ret;
	}

}
