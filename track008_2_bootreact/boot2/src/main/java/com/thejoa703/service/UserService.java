package com.thejoa703.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thejoa703.dto.UserDto.UserRequestDto;
import com.thejoa703.dto.UserDto.UserResponseDto;
import com.thejoa703.entity.AppUser;
import com.thejoa703.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 데이터 저장(insert)시 rollback / readOnly=true 읽기 전용, 낭비
public class UserService {

	private final AppUserRepository userRepo; //@Autowired 대신

	//1. 회원가입 (사용자 등록)
	@Transactional
	public UserResponseDto createUser(UserRequestDto requestDto) {
		// ※ 이메일 중복 검사 / 닉네임 중복 검사
		// if (userRepo.findByEmail(requestDto.getEmail()) != null) {  }
		AppUser appUser = AppUser.builder()
		       .email(requestDto.getEmail())
		       .password(requestDto.getPassword())
		       .nickname(requestDto.getNickname())
		       .moblie(requestDto.getMobile())
		       .mbtiTypeId(requestDto.getMbtiTypeId())
		       .provider("local")
		       .providerId("local")
		       .role("ROLE_USER")
		       .deleted(false)
		       .build();
			
		AppUser savedUser = userRepo.save(appUser);
		return new UserResponseDto(savedUser);
	}
	
	//2. 사용자 단건 조회
	public UserResponseDto getUser(Long id) { // Optional - 값 1개 가 아니면 null
		AppUser appUser = userRepo.findById(id)
				                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다. id="+id));
		return new UserResponseDto(appUser);
	}
}
