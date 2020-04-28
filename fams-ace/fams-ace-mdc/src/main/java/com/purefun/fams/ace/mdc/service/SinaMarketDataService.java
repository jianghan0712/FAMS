/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.mdc.service;

import java.util.HashSet;

/**
 * @Classname: SinaMarketDataService
 * @Description:
 * @author jianghan
 * @date 2020-04-26 16:50:31
 */
public interface SinaMarketDataService {

	/**
	 * 
	 * @MethodName: initSet
	 * @author jianghan
	 * @date 2020-04-28 22:41:43
	 * @param stockSet
	 */
	public void initSet(HashSet<String> stockSet);
}
