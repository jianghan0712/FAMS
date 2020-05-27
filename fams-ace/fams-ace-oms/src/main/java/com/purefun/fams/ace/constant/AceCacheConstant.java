package com.purefun.fams.ace.constant;

/**
 * Ace模块的cache相关常量
 * 
 * @Classname: AceCacheConstant
 * @Description:
 * @author Jianghan
 * @date 2020-05-27 10:56:50
 */
public interface AceCacheConstant {
	/**
	 * 
	 * @Classname: APCCacheType
	 * @Description:
	 * @author 015979
	 * @date 2020-05-27 10:56:46
	 */
	interface APCCacheType {
		String CashCache = "CashCache";

		String AccountCache = "AccountCache";

		String PositionCache = "PositionCache";
	}

}
