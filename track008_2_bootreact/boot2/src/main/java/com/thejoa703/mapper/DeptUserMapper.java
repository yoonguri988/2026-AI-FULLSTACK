package com.thejoa703.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.thejoa703.domain.DeptUser;

@Mapper
public interface DeptUserMapper {
	public List<DeptUser> findByNameKeyword(@Param("keyword") String keyword);
}
