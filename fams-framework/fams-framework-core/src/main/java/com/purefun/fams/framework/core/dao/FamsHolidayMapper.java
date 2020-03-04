package com.purefun.fams.framework.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.framework.core.domain.FamsHoliday;

@Mapper
public interface FamsHolidayMapper {
	int deleteByPrimaryKey(Long id);

	int insert(FamsHoliday record);

	int insertSelective(FamsHoliday record);

	FamsHoliday selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(FamsHoliday record);

	int updateByPrimaryKey(FamsHoliday record);
}