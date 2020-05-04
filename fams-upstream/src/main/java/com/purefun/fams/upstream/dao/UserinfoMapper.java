package com.purefun.fams.upstream.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.purefun.fams.upstream.domain.Userinfo;

@Mapper
public interface UserinfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Userinfo record);

	int insertSelective(Userinfo record);

	Userinfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Userinfo record);

	int updateByPrimaryKey(Userinfo record);

	/** 根据name或token获取用户信息 */
	Userinfo selectByEntity(@Param("name") String name, @Param("token") String token);

}