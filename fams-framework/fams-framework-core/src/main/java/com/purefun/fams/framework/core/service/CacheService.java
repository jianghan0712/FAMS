/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Classname: CacheService
 * @Description: 专属于各自service的redis缓存服务
 * @author 015979
 * @date 2020-03-11 16:24:10
 */
public interface CacheService extends ExposeService {
	/**
	 * 取global的cache
	 * 
	 * @MethodName: globalCacheGet
	 * @author jianghan
	 * @date 2020-05-03 13:28:00
	 * @param key
	 * @return
	 */
	public Object globalCacheGet(String key);

	/**
	 * globalCacheHGet
	 * 
	 * @param key  键 不能为null
	 * @param item 项 不能为null
	 * @return 值
	 */
	public Object globalCacheHGet(String key, String item);

	/**
	 * 分页取global的值,如果不需要分页，填pagesize=Integer.MAX_VALUE
	 * 
	 * @param <T>
	 * 
	 * @param key  键 不能为null
	 * @param item 项 不能为null
	 * @return 值
	 */
	public <T> List<T> globalCacheHMGet(String key, int page, int pagesize);

	/**
	 * global hash的总数
	 * 
	 * @param key  键 不能为null
	 * @param item 项 不能为null
	 * @return 值
	 */
	public int globalCacheHSize(String key);

	/**
	 * 指定服务缓存失效时间
	 * 
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean expire(long time);

	/**
	 * 获取服务cache 过期时间
	 * 
	 * @return 时间(秒) 返回0代表为永久有效
	 */
	public long getExpire();

	/**
	 * 判断key是否存在
	 * 
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public boolean hasKey(String key);

	/**
	 * 删除缓存
	 * 
	 * @param key 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public void del(String... key);

	/**
	 * 普通缓存获取
	 * 
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key);

	/**
	 * 普通缓存放入
	 * 
	 * @param key   键
	 * @param value 值
	 * @return true成功 false失败
	 */
	public boolean set(String key, Object value);

	/**
	 * HashGet
	 * 
	 * @param key  键 不能为null
	 * @param item 项 不能为null
	 * @return 值
	 */
	public Object hget(String key, String item);

	/**
	 * 获取hashKey对应的所有键值
	 * 
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public Map<String, Object> hmget(String key);

	/**
	 * HashSet
	 * 
	 * @param key 键
	 * @param map 对应多个键值
	 * @return true 成功 false 失败
	 */
	public boolean hmset(String key, Map<String, Object> map);

	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 * 
	 * @param key   键
	 * @param item  项
	 * @param value 值
	 * @return true 成功 false失败
	 */
	public boolean hset(String key, String item, Object value);

	/**
	 * 删除hash表中的值
	 * 
	 * @param key  map名
	 * @param item map中的key名
	 */
	public void hdel(String key, String... item);

	/**
	 * 判断hash表中是否有该项的值
	 * 
	 * @param key  键 不能为null
	 * @param item 项 不能为null
	 * @return true 存在 false不存在
	 */
	public boolean hHasKey(String key, String item);

	/**
	 * 根据key获取Set中的所有值
	 * 
	 * @param key 键
	 * @return
	 */
	public Set<Object> sGet(String key);

	/**
	 * 根据value从一个set中查询,是否存在
	 * 
	 * @param key   键
	 * @param value 值
	 * @return true 存在 false不存在
	 */
	public boolean sHasKey(String key, Object value);

	/**
	 * 将数据放入set缓存
	 * 
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public long sSet(String key, Object... values);

	/**
	 * 获取set缓存的长度
	 * 
	 * @param key 键
	 * @return
	 */
	public long sGetSetSize(String key);

	/**
	 * 移除值为value的
	 * 
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 移除的个数
	 */
	public long setRemove(String key, Object... values);

	/**
	 * 获取list缓存的内容
	 * 
	 * @param key   键
	 * @param start 开始
	 * @param end   结束 0 到 -1代表所有值
	 * @return
	 */
	public List<Object> lGet(String key, int start, int end);

	/**
	 * 获取list缓存的长度
	 * 
	 * @param key 键
	 * @return
	 */
	public long lGetListSize(String key);

	/**
	 * 通过索引 获取list中的值
	 * 
	 * @param key   键
	 * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return
	 */
	public Object lGetIndex(String key, int index);

	/**
	 * 将list放入缓存
	 * 
	 * @param key   键
	 * @param value 值
	 * @return
	 */
	public boolean lSet(String key, Object... value);

	/**
	 * 弹出右边的值 --- 并且移除这个值
	 * 
	 * @param listKey
	 */
	public Object rifhtPop(String listKey);
}
