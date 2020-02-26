package com.purefun.fams.framework.common.enums;

import java.util.concurrent.TimeUnit;

/**
 * 过期时间相关枚举
 */
public enum ExpireEnum {
	REDIS_CACHE_EXPIRE(30L, TimeUnit.MINUTES);

	/**
	 * 过期时间
	 */
	private Long time;
	/**
	 * 时间单位
	 */
	private TimeUnit timeUnit;

	ExpireEnum(Long time, TimeUnit timeUnit) {
		this.time = time;
		this.timeUnit = timeUnit;
	}

	public Long getTime() {
		return time;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
}
