package com.the703.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.the703.dao.UserInfoMapper;
import com.the703.dto.UserInfoDto;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired UserInfoMapper dao;
	
	@Override
	public List<UserInfoDto> list() {
		return dao.selectAll();
	}

	@Override
	public UserInfoDto select(int uno) {
		return dao.select(uno);
	}

	@Override
	public int insert(UserInfoDto dto) {
		try {
			dto.setBip(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) { e.printStackTrace(); }
		return dao.insert(dto);
	}

	@Override
	public int update(UserInfoDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int uno) {
		return dao.delete(uno);
	}

	@Override
	public UserInfoDto selectOneByDto(UserInfoDto dto) {
		return dao.selectOneByDto(dto);
	}
	
	@Override
	public UserInfoDto selectOneByEmail(String email) {
		return dao.selectOneByEmail(email);
	}

}
