package com.purefun.fams.framework.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.queen.rds.FamsSecurityBasicinfoBO;

@Mapper
public interface FamsSecurityBasicinfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(FamsSecurityBasicinfoBO record);

	int insertSelective(FamsSecurityBasicinfoBO record);

	FamsSecurityBasicinfoBO selectByPrimaryKey(Long id);

	FamsSecurityBasicinfoBO selectBySecurityId(String securityId);

	int updateByPrimaryKeySelective(FamsSecurityBasicinfoBO record);

	int updateByPrimaryKey(FamsSecurityBasicinfoBO record);

	List<FamsSecurityBasicinfoBO> selectAll();
}