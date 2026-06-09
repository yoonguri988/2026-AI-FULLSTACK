package com.the703.service;

import java.util.List;

import com.the703.dto.UserDto;

public interface UserService {
	public List<UserDto> list();
	public UserDto select(int uno);
	public int insert(UserDto dto);
	public int update(UserDto dto);
	public int delete(int uno);

	//VERSION-1
//	public UserDto selectOneByDto(UserDto dto);
//	public UserDto selectOneByEmail(String Email);
//	public boolean isDupEmail(String email);
	
	public int findLogin(UserDto dto);
	public UserDto findByUno(int uno);
	public String findByEmail(String email);
}
