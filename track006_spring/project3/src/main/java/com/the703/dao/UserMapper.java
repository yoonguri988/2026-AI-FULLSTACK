package com.the703.dao;

import com.the703.dto.AuthDto;
import com.the703.dto.AuthUserDto;
import com.the703.dto.UserDto;

@Mapper
public interface UserMapper {
	public AuthUserDto readAuth(String email);

	public UserDto findByEmail(String email);

	public UserDto findByNickname(String nickname);

	public void insertAuth(AuthDto auth);
	public int insert(UserDto dto);
}
