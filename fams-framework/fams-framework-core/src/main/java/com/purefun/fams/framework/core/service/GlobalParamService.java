package com.purefun.fams.framework.core.service;

import java.util.List;

import com.purefun.fams.framework.core.domain.FamsGlobalParam;

public interface GlobalParamService extends ExposeService {

	List<FamsGlobalParam> getParamValueWithCache(String paramScope, String paramName);

	boolean updateParamValue(String paramName, String paramValue);

	void refresh(String key);

	void put(String key, String value);

}
