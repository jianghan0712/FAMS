package com.purefun.fams.framework.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.framework.core.domain.FamsSecurityBasicinfo;

@Mapper
public interface FamsSecurityBasicinfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(FamsSecurityBasicinfo record);

	int insertSelective(FamsSecurityBasicinfo record);

	FamsSecurityBasicinfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(FamsSecurityBasicinfo record);

	int updateByPrimaryKey(FamsSecurityBasicinfo record);

	List<FamsSecurityBasicinfo> selectAll();
}