/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.service;

/**
 * @Classname: CommondService
 * @Description: 通用的运行时获取命令的接口
 * @author jianghan
 * @date 2020-03-05 17:18:43
 */
public interface CommondService {
	/**
	 * 执行命令
	 * 
	 * @MethodName: executeCommond
	 * @author jianghan
	 * @date 2020-03-05 17:20:03
	 * @param commond
	 * @return
	 */
	public void executeCommond(String commond);
}
