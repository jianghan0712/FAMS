/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.core.bo.commom;

/**
 * @Classname: ICommon_OTW
 * @Description: 装载otw的通用方法
 * @author jiang
 * @date 2020-02-06 14:19:00
 */
public interface ICommon_OTW {
	/**
	 * 
	 * @MethodName: getDestination
	 * @Description:
	 * @author jiang
	 * @date 2020-02-06 14:20:33
	 * @return 该bo的destination,即topic
	 */
	String getDestination();

	/**
	 * 
	 * @MethodName: getBo
	 * @Description: 获取原始bo
	 * @author jiang
	 * @date 2020-02-06 14:30:05
	 * @return
	 */
	Object getBo();

	/**
	 * 获取bo对应的proto编码器
	 * 
	 * @MethodName: getBuilder
	 * @Description:
	 * @author jiang
	 * @date 2020-02-06 14:30:43
	 * @return
	 */
	com.google.protobuf.GeneratedMessageV3.Builder getBuilder();

	/**
	 * 生成uuid
	 * 
	 * @MethodName: getUuid
	 * @Description:
	 * @author jiang
	 * @date 2020-02-06 14:31:07
	 * @return
	 */
	String getUuid();
}
