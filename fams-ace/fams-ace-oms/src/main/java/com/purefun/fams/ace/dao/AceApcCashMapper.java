package com.purefun.fams.ace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.ace.oms.AceApcCashBO;

@Mapper
public interface AceApcCashMapper {
	int deleteByPrimaryKey(Long id);

	int insert(AceApcCashBO record);

	int insertSelective(AceApcCashBO record);

	AceApcCashBO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AceApcCashBO record);

	int updateByPrimaryKey(AceApcCashBO record);

	List<AceApcCashBO> selectAll();
}