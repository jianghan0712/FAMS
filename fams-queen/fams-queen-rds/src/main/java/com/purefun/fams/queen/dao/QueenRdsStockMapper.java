package com.purefun.fams.queen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.purefun.fams.queen.rds.QueenRdsStockBO;

@Mapper
public interface QueenRdsStockMapper {
	int deleteByPrimaryKey(Long id);

	int insert(QueenRdsStockBO record);

	int insertSelective(QueenRdsStockBO record);

	QueenRdsStockBO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(QueenRdsStockBO record);

	int updateByPrimaryKey(QueenRdsStockBO record);

	List<QueenRdsStockBO> selectAll();

	QueenRdsStockBO selectBySecurityId(String securityId);

}