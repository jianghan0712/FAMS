package com.purefun.fams.framework.ignite.expose;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.configuration.CacheConfiguration;

/**
 * 封装ignite的cache方法
 * 
 * @Classname: IgniteCache
 * @Description:
 * @author jianghan
 * @date 2020-02-24 19:32:52
 */
public interface IgniteCache<K, V> {

	/**
	 * 初始化各cache
	 * 
	 * @param ignite   主ignite
	 * @param cacheCfg cache config
	 */
	public void initCache(Ignite ignite, List<CacheConfiguration<K, V>> cacheCfg);

	/**
	 ** 输出底层cache
	 * 
	 * @MethodName: getCache
	 * @author jianghan
	 * @date 2020-02-23 14:45:33
	 * @param cacheName
	 * @return
	 */
	public org.apache.ignite.IgniteCache<K, V> getCache(String cacheName);

	/**
	 * 写入缓存
	 * 
	 * @MethodName: put
	 * @author jiang
	 * @date 2020-02-21 01:01:06
	 * @param key
	 * @param val
	 */
	public void put(String cacheName, K key, V val);

	/**
	 * 读取缓存
	 * 
	 * @MethodName: get
	 * @author jiang
	 * @date 2020-02-21 01:01:38
	 * @param key
	 * @return
	 */
	public V get(String cacheName, K key);

	/**
	 * 从所有cache中找到第一个符合key的值返回
	 * 
	 * @MethodName: get
	 * @author jianghan
	 * @date 2020-02-24 19:18:16
	 * @param key
	 * @return
	 */
	public V get(K key);

	/**
	 * 获取ignite实例
	 * 
	 * @MethodName: getIgnite
	 * @author 015979
	 * @date 2020-03-16 16:23:31
	 * @return
	 */
	public Ignite getIgnite();

	/**
	 * 判断当前是否有cacheName对应的cache
	 * 
	 * @MethodName: contain
	 * @author 015979
	 * @date 2020-03-17 13:38:02
	 * @param cacheName
	 * @return
	 */
	public boolean contain(String cacheName);

	/**
	 * 获取cacheName下所有的缓存
	 * 
	 * @MethodName: getAll
	 * @author 015979
	 * @date 2020-03-17 15:28:28
	 * @param cacheName
	 * @return
	 */
	public List<List<?>> getAll(String cacheName);

	public List<List<?>> getBySQL(String cacheName, String sql);

}
