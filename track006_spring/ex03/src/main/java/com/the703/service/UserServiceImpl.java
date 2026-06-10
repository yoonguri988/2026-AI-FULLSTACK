package com.the703.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.the703.dao.UserMapper;
import com.the703.dto.AuthDto;
import com.the703.dto.AuthListDto;
import com.the703.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired UserMapper dao;
	@Autowired @Qualifier ("passwordEncoder") PasswordEncoder pwencoder;
	
	@Override
	public List<UserDto> list() {
		return dao.selectAll();
	}

	@Override
	public UserDto select(int uno) {
		return dao.select(uno);
	}

	@Override
	public int insert(UserDto dto) {
		AuthDto auth = new AuthDto();
		auth.setEmail(dto.getEmail()); auth.setBpass(dto.getBpass()); auth.setAuth("ROLE_MEMBER");
		dao.insertAuth(auth);// 권한 추가
		
		dto.setBpass(pwencoder.encode(dto.getBpass()));
		try {
			dto.setBip(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) { e.printStackTrace(); }
		return dao.insert(dto);
	}

	@Override
	public int update(UserDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int uno) {
		return dao.delete(uno);
	}
	
	// VERSION-1
//	@Override
//	public UserDto selectOneByDto(UserDto dto) {
//		return dao.selectOneByDto(dto);
//	}
//	
	@Override
	public UserDto findByEmailUserInfo(String email) {
		return dao.findByEmailUserInfo(email);
	}

	@Override
	public boolean isDupByEmail(String email) {
		return dao.findByEmailUserInfo(email) != null ? true : false;
	}

	@Override
	public int findLogin(UserDto dto) {
		return dao.findLogin(dto);
	}

	@Override
	public UserDto findByUno(int uno) {
		return dao.findByUno(uno);
	}

	@Override
	public String findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public AuthListDto readAuth(AuthDto dto) {
		return dao.readAuth(dto);
	}
}
