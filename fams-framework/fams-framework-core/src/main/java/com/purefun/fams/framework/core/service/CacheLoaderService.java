/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.common.util.ClassHandleUtil;
import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.exception.FAMSException;

/**
 * @Classname: CacheLoaderService
 * @Description: DB数据加载到cache（redis或者ignite)
 * @author jianghan
 * @date 2020-02-25 22:56:27
 */
public abstract class CacheLoaderService implements DataLoaderService {
	private static final Logger logger = LogManager.getLogger(CacheLoaderService.class);

	@Autowired
	private SqlSession sqlSession;

	/**
	 ** 将指定mybatis代理的mapper和对应的method来获取要加载的数据
	 * 
	 * @MethodName: loadDataFromDB2Cache
	 * @author jianghan
	 * @date 2020-02-25 21:59:33
	 * @param <V>         domin
	 * @param mapperName  mybatis的Dao类
	 * @param methodName  Dao的方法
	 * @param cacheName   ignite或redis内部的缓存名
	 * @param keyFildName 用v的哪个字段做索引
	 */
	public <V> void loadDataFromDB2Cache(String mapperName, String methodName, String cacheName,
			String... keyFildName) {
		try {
			logger.info("---------load all key from DB table:{}---------", mapperName);
			List<V> result = ClassHandleUtil.executeMybatisProxy(sqlSession, mapperName, methodName, null, null);
			loadData2Cache(result, cacheName, keyFildName);
		} catch (RuntimeException | ReflectiveOperationException e) {
			logger.error("从db加载数据到ignite失败，mapper:{},cacheName:{},e:{}", mapperName, cacheName, e);
		}
	}

	/**
	 ** 检查传入的key是否合法
	 * 
	 * @MethodName: checkKeyFields
	 * @author jianghan
	 * @date 2020-02-26 22:17:37
	 * @param keyFieldName 将做key的属性名称的数组
	 * @param vClass       domain类型
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	protected void checkKeyFields(String[] keyFieldName, Class<?> vClass)
			throws NoSuchFieldException, SecurityException {
		if (keyFieldName == null || keyFieldName.length == 0)
			throw new FAMSException("由于keyFileName非法，无法加载到cache中", ErrorCodeEnum.PARAM_EXCEPTION);

		for (String eachKey : keyFieldName) {
			if (StringUtils.isBlank(eachKey) || vClass.getDeclaredField(eachKey) == null) {
				throw new FAMSException("由于keyFileName非法，无法加载到cache中", ErrorCodeEnum.PARAM_EXCEPTION);
			}
		}
	}

	/**
	 * 
	 * @MethodName: loadData2Cache
	 * @author jianghan
	 * @date 2020-02-26 19:18:19
	 * @param <V>         值
	 * @param list        值得list
	 * @param cacheName   ignite或redis内部的缓存名
	 * @param keyFildName 用v的哪个字段做索引
	 */
	abstract public <V> void loadData2Cache(List<V> list, String cacheName, String... keyFildName);

	/**
	 * 
	 * @MethodName: loadData2CacheBySelf
	 * @author jianghan
	 * @date 2020-02-26 19:18:19
	 * @param <V>         值
	 * @param list        值得list
	 * @param cacheName   ignite或redis内部的缓存名
	 * @param keyFildName 用v的哪个字段做索引
	 */
	abstract public <V> void loadData2CacheBySelf(List<V> list, String cacheName, String... keyFildName);
}
