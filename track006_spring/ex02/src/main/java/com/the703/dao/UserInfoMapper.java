package com.the703.dao;

import java.util.List;

import com.the703.dto.UserInfoDto;

@Mapper
public interface UserInfoMapper {
	public List<UserInfoDto> selectAll();
	public UserInfoDto select(int bno);
	public int insert(UserInfoDto dto);
	public int update(UserInfoDto dto);
	public int delete(int bno);
}
