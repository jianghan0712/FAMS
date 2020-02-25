/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.expose;

import java.util.List;

/**
 * @Classname: IgniteCacheLoaderService
 * @Description: 在系统初始化时，将数据加载进ignite
 * @author jianghan
 * @date 2020-02-25 22:56:27
 */
public interface IgniteCacheLoaderService {
	/**
	 * 使用ignite.IgniteDataStreamer将指定mybatis代理的mapper和对应的method来加载数据到cache
	 * 
	 * @MethodName: loadDataFromDB2Cache
	 * @author jianghan
	 * @date 2020-02-25 21:59:33
	 * @param <V>        domin
	 * @param mapperName mybatis的Dao类
	 * @param methodName Dao的方法
	 * @param cacheName  ignite内部的缓存名
	 */
	<V> void loadDataFromDB2Cache(String mapperName, String methodName, String cacheName);

	/**
	 * 使用ignite.IgniteDataStreamer将已经获取到的list数据存入cache
	 * 
	 * @MethodName: loadDBdata2CacheImp
	 * @author jianghan
	 * @date 2020-02-25 22:27:46
	 * @param <V>       domin
	 * @param list      数据list
	 * @param cacheName ignite对应的cache
	 */
	<V> void loadData2Cache(List<V> list, String cacheName);

	/**
	 * 用户自己实现数据加载方法，如果不需要实现自己的方法，可以不实现此方法
	 * 
	 * @MethodName: loadData2CacheBySelf
	 * @author jianghan
	 * @date 2020-02-25 23:04:40
	 * @param <V>
	 * @param list
	 * @param cacheName
	 */
	<V> void loadData2CacheBySelf(List<V> list, String cacheName);
}
