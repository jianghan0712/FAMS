package com.purefun.fams.framework.core.service;

import java.util.List;

import com.purefun.fams.framework.core.domain.FamsGlobalParam;

public interface GlobalParamService extends ExposeService {

	/**
	 * 获取缓存中全局变量表fams_global_param表的数据
	 * 
	 * @MethodName: getParamValueWithCache
	 * @author jianghan
	 * @date 2020-03-04 00:27:55
	 * @param paramScope 参数归集
	 * @param paramName  参数名
	 * @return
	 */
	List<FamsGlobalParam> getParamValueWithCache(String paramScope, String paramName);

	/**
	 * 更新缓存，并同步到数据库
	 * 
	 * @MethodName: updateParamValue
	 * @author jianghan
	 * @date 2020-03-04 00:28:56
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	boolean updateParamValue(String paramScope, String paramName, String paramValue);

}
