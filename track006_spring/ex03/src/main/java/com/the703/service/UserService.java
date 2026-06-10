package com.the703.service;

import java.util.List;

import com.the703.dto.AuthDto;
import com.the703.dto.AuthListDto;
import com.the703.dto.UserDto;

public interface UserService {
	public List<UserDto> list();
	public UserDto select(int uno);
	public int insert(UserDto dto); //1. 회원가입 + 권한 추가
	public int update(UserDto dto);
	public int delete(int uno);

	//VERSION-1
//	public UserDto selectOneByDto(UserDto dto);
	public UserDto findByEmailUserInfo(String Email);
	public boolean isDupByEmail(String email);
	
	public int findLogin(UserDto dto); //2. 로그인
	public UserDto findByUno(int uno); //3. 마이페이지
	public String findByEmail(String email);//4. 아이디 중복검사
	
	/* security login */
	public AuthListDto readAuth(AuthDto dto);
	
}
