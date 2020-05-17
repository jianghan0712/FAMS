package com.purefun.fams.ace.dao;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.ace.oms.AceApcCashStatementBO;

@Mapper
public interface AceApcCashStatementMapper {
	int deleteByPrimaryKey(Long id);

	int insert(AceApcCashStatementBO record);

	int insertSelective(AceApcCashStatementBO record);

	AceApcCashStatementBO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AceApcCashStatementBO record);

	int updateByPrimaryKey(AceApcCashStatementBO record);
}