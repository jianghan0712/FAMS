package com.purefun.fams.ace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.ace.oms.AceApcAccountBO;

@Mapper
public interface AceApcAccountMapper {
	int deleteByPrimaryKey(Long id);

	int insert(AceApcAccountBO record);

	int insertSelective(AceApcAccountBO record);

	AceApcAccountBO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AceApcAccountBO record);

	int updateByPrimaryKey(AceApcAccountBO record);

	List<AceApcAccountBO> selectAll();
}