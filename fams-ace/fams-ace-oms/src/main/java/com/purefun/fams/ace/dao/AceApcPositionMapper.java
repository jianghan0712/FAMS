package com.purefun.fams.ace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.ace.oms.AceApcPositionBO;

@Mapper
public interface AceApcPositionMapper {
	int deleteByPrimaryKey(Long id);

	int insert(AceApcPositionBO record);

	int insertSelective(AceApcPositionBO record);

	AceApcPositionBO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AceApcPositionBO record);

	int updateByPrimaryKey(AceApcPositionBO record);

	List<AceApcPositionBO> selectAll();
}