package com.purefun.fams.ace.dao;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.ace.oms.AceApcPositionStatementBO;

@Mapper
public interface AceApcPositionStatementMapper {
	int deleteByPrimaryKey(Long id);

	int insert(AceApcPositionStatementBO record);

	int insertSelective(AceApcPositionStatementBO record);

	AceApcPositionStatementBO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AceApcPositionStatementBO record);

	int updateByPrimaryKey(AceApcPositionStatementBO record);
}