package com.the703.service;

import java.util.List;

import com.the703.dto.UserInfoDto;

public interface UserInfoService {
	public List<UserInfoDto> list();
	public UserInfoDto select(int uno);
	public int insert(UserInfoDto dto);
	public int update(UserInfoDto dto);
	public int delete(int uno);

	public UserInfoDto selectOneByDto(UserInfoDto dto);
}
