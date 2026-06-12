package com.the703.dao;
import org.apache.ibatis.annotations.Param;

import com.the703.dto.AuthDto;
import com.the703.dto.AuthUserDto;
import com.the703.dto.UserDto;

@Mapper
public interface UserMapper { 

	public     AuthUserDto    readAuth(String email);
	
	public  int          insertAuth(AuthDto  dto); 
	public int      	 insert(UserDto dto);  

	public String   findByEmail( @Param("email") String email);
	public UserDto  findByEmailUserInfo(   String email);
	public String  findByNickname(   String nickname);
}
