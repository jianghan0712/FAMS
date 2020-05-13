/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.common.util.ClassHandleUtil;
import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.framework.core.service.CacheLoaderService;

/**
 * 数据加载实现类
 * 
 * @Classname: IgniteCacheLoaderService
 * @Description:
 * @author jianghan
 * @date 2020-02-25 19:34:46
 */
public class IgniteCacheLoaderServiceImpl extends CacheLoaderService {
	private static final Logger logger = LogManager.getLogger(IgniteCacheImpl.class);

	@Autowired
	private Ignite ignite;

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
	@SuppressWarnings("rawtypes")
	@Override
	public <V> void loadData2Cache(List<V> list, String cacheName, String... keyFieldName) {
		if (list == null || list.size() <= 0 || ignite.cache(cacheName) == null) {
			return;
		}
		V first = list.get(0);
		Class<?> vClass = first.getClass();
		try (IgniteDataStreamer streamer = ignite.dataStreamer(cacheName)) {
			// 取出keyFieldName数组，并检查
			checkKeyFields(keyFieldName, vClass);
			StringBuilder key = new StringBuilder();

			// key=各个keyFieldName之间用^拼接，如 paramScope^paramName
			for (V eachValue : list) {
				for (String eachKey : keyFieldName) {
					Object keyValue = ClassHandleUtil.getFieldValue(eachValue, eachKey);
					key.append(keyValue.toString()).append(CommonUtil.CharUtil.caret);
				}
				// 去掉最后的^
				key.deleteCharAt(key.length() - 1);
				streamer.addData(key.toString(), eachValue);
				key.delete(0, key.length());
			}
			logger.info("加载cache成功，cacheName:{},数据量：{}", cacheName, list.size());
		} catch (Exception e) {
			logger.info("加载cache失败，cacheName:{},失败原因：{}", cacheName, e);
		}
	}

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
	@Override
	public <V> void loadData2CacheBySelf(List<V> list, String cacheName, String... keyFieldName) {
		loadData2Cache(list, cacheName, keyFieldName);
	}

}
