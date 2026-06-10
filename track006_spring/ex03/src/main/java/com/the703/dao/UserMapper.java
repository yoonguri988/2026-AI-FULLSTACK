package com.the703.dao;

import java.util.List;

import com.the703.dto.AuthDto;
import com.the703.dto.AuthListDto;
import com.the703.dto.UserDto;

@Mapper
public interface UserMapper {
	public List<UserDto> selectAll();
	public UserDto select(int bno);
	public int insert(UserDto dto);
	public int update(UserDto dto);
	public int delete(int bno);
	
	//VERSION-1
	//public UserDto selectOneByDto(UserDto dto);
	public UserDto findByEmailUserInfo(String email);
	
	// ADD
	//public int      insert(UserDto dto);
	public int      findLogin(UserDto dto);
	public UserDto  findByUno(   int  uno);
	public String   findByEmail( String email);
	
	/* security */
	public int insertAuth(AuthDto dto);
	public AuthListDto readAuth(AuthDto dto);
}

/*
1)  insert 구문찾기    first@gmail.com  /  ROLE_MEMBER
     insert into  authorities (email, auth) values ( #{email} , #{auth} ) 

2)  JOIN 이용해서    first@gmail.com의   email, bpass, auth 필드값찾기 
  select     u.email,  u.bpass,  a.auth
  from      users u    left   join authorities a    on u.email   = a.email  
  where     u.email =#{email}  
 */

