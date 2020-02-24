/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Classname: IgniteCacheProperties
 * @Description:
 * @author jiang
 * @date 2020-02-21 00:32:24
 */
@ConfigurationProperties(prefix = "fams.framework.ignite.cache")
public class IgniteCacheProperties {

	private List<IgniteCachePropertie> cacheList;

	public List<IgniteCachePropertie> getCacheList() {
		return cacheList;
	}

	public void setCacheList(List<IgniteCachePropertie> cacheList) {
		this.cacheList = cacheList;
	}

}
