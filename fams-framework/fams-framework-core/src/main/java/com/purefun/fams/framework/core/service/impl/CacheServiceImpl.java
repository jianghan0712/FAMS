/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.common.util.StringUtil;
import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.util.AssertUtil;
import com.purefun.fams.framework.core.domain.ServiceInstance;
import com.purefun.fams.framework.core.service.CacheService;
import com.purefun.fams.framework.core.service.RedisService;

/**
 * @Classname: CacheServiceImpl
 * @Description: 对于每一个service，分配一个redis-key：${ServiceName}-${Env}-${Instance}
 **               该service的所有缓存都存在其中，使用jackson进行序列化和反序列化
 * @author 015979
 * @date 2020-03-11 16:25:02
 */
public class CacheServiceImpl implements CacheService {
	private static final Logger logger = LogManager.getLogger(CacheServiceImpl.class);

	@Autowired
	ServiceInstance instance;

	@Autowired
	RedisService cache;

	/** 每个服务cache的前缀 service-env-instance */
	String serviceCacheName = null;

	@PostConstruct
	public void init() {
		serviceCacheName = StringUtil.append(instance.getServiceName(), CommonUtil.CharUtil.rod, instance.getEnv(),
				CommonUtil.CharUtil.rod, instance.getInstance());
	}

	@Override
	public boolean expire(long time) {
		return cache.expire(serviceCacheName, time);
	}

	@Override
	public long getExpire() {
		return cache.getExpire(serviceCacheName);
	}

	@Override
	public boolean hasKey(String key) {
		return cache.hHasKey(serviceCacheName, key);
	}

	@Override
	public void del(String... key) {
		AssertUtil.assertNotNull(key, ErrorCodeEnum.PARAM_EXCEPTION);
		cache.hdel(serviceCacheName, key);
	}

	@Override
	public Object get(String key) {
		AssertUtil.assertNotNull(key, ErrorCodeEnum.PARAM_EXCEPTION);
		return cache.hget(serviceCacheName, key);
	}

	@Override
	public boolean set(String key, Object value) {
		return cache.hset(serviceCacheName, key, value);
	}

	@Override
	public Object hget(String key, String item) {
		Map<String, Object> innerMap = getInnerMap(key);
		if (innerMap == null)
			return null;

		Object obj = null;
		if (innerMap.containsKey(item)) {
			obj = innerMap.get(item);
		}

		return obj;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.framework.core.service.CacheService#hmget(java.lang.String)
	 * @param key
	 * @return
	 */

	@Override
	public Map<String, Object> hmget(String key) {
		return getInnerMap(key);
	}

	@Override
	public boolean hmset(String key, Map<String, Object> map) {
		return false;
	}

	@Override
	public boolean hset(String key, String item, Object value) {
		// 是否在service的cache中存在，如果不存在，新建map
		Map<String, Object> innerMap = !hasKey(key) ? new HashMap<String, Object>() : getInnerMap(key);
		innerMap.put(item, value);

		return cache.hset(serviceCacheName, key, innerMap);
	}

	@Override
	public void hdel(String key, String... item) {
		Map<String, Object> map = getInnerMap(key);
		if (map == null)
			return;
		for (Object each : item) {
			map.remove(each);
		}
		set(key, map);
	}

	@Override
	public boolean hHasKey(String key, String item) {
		if (hasKey(key)) {
			Map<String, Object> innerMap = getInnerMap(key);
			if (innerMap != null)
				return innerMap.containsKey(item);
		}

		return false;
	}

	@Override
	public Set<Object> sGet(String key) {
		return (Set<Object>) cache.hget(serviceCacheName, key);
	}

	@Override
	public boolean sHasKey(String key, Object value) {
		Set<Object> innerSet = getInnerSet(key);
		if (innerSet == null)
			return false;

		return innerSet.contains(value);
	}

	@Override
	public long sSet(String key, Object... values) {
		Set<Object> innerSet = getInnerSet(key) != null ? getInnerSet(key) : new HashSet<Object>();
		int i = 0;
		for (Object each : values) {
			if (innerSet.add(each))
				i++;
		}
		cache.hset(serviceCacheName, key, innerSet);
		return i;
	}

	@Override
	public long sGetSetSize(String key) {
		Set<Object> innerSet = getInnerSet(key);
		if (innerSet == null)
			return 0;

		return innerSet.size();
	}

	@Override
	public long setRemove(String key, Object... values) {
		Set<Object> innerSet = getInnerSet(key);
		if (innerSet == null)
			return 0;
		int i = 0;
		for (Object each : values) {
			if (innerSet.remove(each))
				i++;
		}
		return i;
	}

	@Override
	public List<Object> lGet(String key, int start, int end) {
		List<Object> list = getInnerList(key);
		if (list == null)
			return null;

		return list.subList(start, end);
	}

	@Override
	public long lGetListSize(String key) {
		List<Object> list = getInnerList(key);
		if (list == null)
			return 0;

		return list.size();
	}

	@Override
	public Object lGetIndex(String key, int index) {
		AssertUtil.assertNotBlank(key, ErrorCodeEnum.PARAM_EXCEPTION);

		List<Object> list = getInnerList(key);
		if (list == null)
			return null;
		int size = list.size();
		if (index < 0) { // 如果小于0，从反向推
			index = size + index;
		}

		return list.get(index);
	}

	@Override
	public boolean lSet(String key, Object... value) {
		AssertUtil.assertNotBlank(key, ErrorCodeEnum.PARAM_EXCEPTION);
		AssertUtil.assertNotNull(value, ErrorCodeEnum.PARAM_EXCEPTION);

		List<Object> list = getInnerList(key);
		if (list == null)
			list = new LinkedList<Object>();
		for (Object each : value) {
			list.add(each);
		}

		return cache.hset(serviceCacheName, key, list);
	}

	@Override
	public Object rifhtPop(String listKey) {
		List<Object> list = getInnerList(listKey);
		if (list == null) {
			return null;
		}
		Object obj = list.remove(list.size() - 1);
		cache.hset(serviceCacheName, listKey, list);

		return obj;
	}

	/**
	 * key类型为hash时，获取内部的hashMap
	 * 
	 * @MethodName: getInnerMap
	 * @author 015979
	 * @date 2020-03-12 11:47:02
	 * @param innerKey
	 * @return
	 */
	private Map<String, Object> getInnerMap(String mapperName) {
		if (!cache.hasKey(serviceCacheName)) {
			return null;
		}
		return (Map<String, Object>) cache.hget(serviceCacheName, mapperName);
	}

	/**
	 * key类型为set时，获取内部的hashset
	 * 
	 * @MethodName: getInnerSet
	 * @author 015979
	 * @date 2020-03-12 16:30:40
	 * @param setName
	 * @return
	 */
	private Set<Object> getInnerSet(String setName) {
		if (!cache.hasKey(serviceCacheName)) {
			return null;
		}
		return (Set<Object>) cache.hget(serviceCacheName, setName);
	}

	/**
	 * key类型为list时，获取内部的LinkedList
	 * 
	 * @MethodName: getInnerList
	 * @author 015979
	 * @date 2020-03-12 16:31:51
	 * @param listName
	 * @return
	 */
	private List<Object> getInnerList(String listName) {
		if (!cache.hasKey(serviceCacheName)) {
			return null;
		}
		return (List<Object>) cache.hget(serviceCacheName, listName);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.framework.core.service.CacheService#globalCacheGet(java.lang.String)
	 * @param key
	 * @return
	 */

	@Override
	public Object globalCacheGet(String key) {
		// TODO Auto-generated method stub
		return cache.get(key);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.framework.core.service.CacheService#globalCacheHGet(java.lang.String,
	 *      java.lang.String)
	 * @param key
	 * @param item
	 * @return
	 */

	@Override
	public Object globalCacheHGet(String key, String item) {
		// TODO Auto-generated method stub
		return cache.hget(key, item);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.framework.core.service.CacheService#globalCacheHMGet(java.lang.String)
	 * @param key
	 * @return
	 */

	@Override
	public <T> List<T> globalCacheHMGet(String key, int page, int pagesize) {
		// TODO Auto-generated method stub
		Map<Object, Object> total = cache.hmget(key);
		List<T> ret = new ArrayList<T>();
		int begin = (page - 1) * pagesize;// 该次请求的起始
		int end = (page) * pagesize;// 该次请求的结束
		int i = 0;
		if (page <= 0) {// 不需要分页的请求
			end = total.size();
		}

		for (Map.Entry<Object, Object> each : total.entrySet()) {
			if (i <= begin) {
				i++;
				continue;
			} else if (i > end) {
				break;
			}
			ret.add((T) each.getValue());
			i++;
		}

		return ret;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.purefun.fams.framework.core.service.CacheService#globalCacheHSize(java.lang.String)
	 * @param key
	 * @return
	 */

	@Override
	public int globalCacheHSize(String key) {
		// TODO Auto-generated method stub
		Map<Object, Object> total = cache.hmget(key);
		return total == null ? 0 : total.size();
	}

}
