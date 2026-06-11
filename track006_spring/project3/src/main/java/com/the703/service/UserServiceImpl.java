package com.the703.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.the703.dao.UserMapper;
import com.the703.dto.AuthDto;
import com.the703.dto.AuthUserDto;
import com.the703.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired UserMapper dao;
	@Autowired @Qualifier ("passwordEncoder") PasswordEncoder pwencoder;
	
	@Override
	public AuthUserDto readAuth(String email) {
		return dao.readAuth(email);
	}

	@Override
	public UserDto findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public UserDto findByNickname(String nickname) {
		return dao.findByNickname(nickname);
	}

	@Override
	public int insert(UserDto dto) {
		AuthDto auth = new AuthDto();
		auth.setEmail(dto.getEmail()); 
		auth.setBpass(dto.getBpass());
		auth.setAuth("MEMBER_ROLE");
		dao.insertAuth(auth);
		
		dto.setBpass(pwencoder.encode(dto.getBpass()));
		try {
			dto.setBip(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) { e.printStackTrace(); }
		return dao.insert(dto);
	}
}
