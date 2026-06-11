package com.the703.service;

import com.the703.dto.AuthUserDto;
import com.the703.dto.UserDto;

public interface UserService {
	public AuthUserDto readAuth(String email);
	
	public UserDto findByEmail(String email);
	public UserDto findByNickname(String nickname);

	public int insert(UserDto dto);
	
}
