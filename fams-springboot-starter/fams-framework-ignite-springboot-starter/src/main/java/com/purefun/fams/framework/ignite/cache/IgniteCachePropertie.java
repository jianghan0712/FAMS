/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.ignite.cache;

import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;

/**
 * @Classname: IgniteCachePropertie
 * @Description:
 * @author jianghan
 * @date 2020-02-23 13:43:17
 */
public class IgniteCachePropertie {
	/** */
	String name = "default-cache";
	/** */
	CacheAtomicityMode atomicityMode = CacheAtomicityMode.ATOMIC;
	/** */
	CacheMode cacheMode = CacheMode.LOCAL;
	/** */
	Class<?>[] indexedTypes = { java.lang.String.class, java.lang.String.class };

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CacheAtomicityMode getAtomicityMode() {
		return atomicityMode;
	}

	public void setAtomicityMode(CacheAtomicityMode atomicityMode) {
		this.atomicityMode = atomicityMode;
	}

	public CacheMode getCacheMode() {
		return cacheMode;
	}

	public void setCacheMode(CacheMode cacheMode) {
		this.cacheMode = cacheMode;
	}

	public Class<?>[] getIndexedTypes() {
		return indexedTypes;
	}

	public void setIndexedTypes(Class<?>[] indexedTypes) {
		this.indexedTypes = indexedTypes;
	}

}
