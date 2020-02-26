package com.purefun.fams.framework.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.purefun.fams.framework.core.domain.FamsGlobalParam;

@Mapper
public interface FamsGlobalParamMapper {
	int deleteByPrimaryKey(Long id);

	int insert(FamsGlobalParam record);

	int insertSelective(FamsGlobalParam record);

	FamsGlobalParam selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(FamsGlobalParam record);

	int updateByPrimaryKey(FamsGlobalParam record);

	/** 系统启动时,加载所有数据 */
	List<FamsGlobalParam> getAllValue();

	/** 根据paramName和scope来获取对应的param */
	List<FamsGlobalParam> selectByParamNameAndScope(@Param(value = "paramName") String paramName,
			@Param(value = "paramScope") String paramScope);
}