package com.purefun.fams.framework.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.framework.core.domain.FamsExchange;

@Mapper
public interface FamsExchangeMapper {
	int deleteByPrimaryKey(Long id);

	int insert(FamsExchange record);

	int insertSelective(FamsExchange record);

	FamsExchange selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(FamsExchange record);

	int updateByPrimaryKey(FamsExchange record);
}