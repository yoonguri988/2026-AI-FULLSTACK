package com.thejoa703.dto;

import com.thejoa703.entity.AppUser;

import lombok.Getter;
import lombok.Setter;

public class UserDto {

	// 회원가입 - 요청 DTO
	@Setter @Getter
	public static class UserRequestDto {
		private String email;
		private String password;
		private String nickname;
		private String mobile;
		private Integer mbtiTypeId;
	}
	
	// 회원정보 - 응답 DTO
	@Getter
	public static class UserResponseDto {
		private Long id;
		private String email;
		private String password;
		private String nickname;
		private String mobile;
		private Integer mbtiType;
		private String role;
		
		public UserResponseDto(AppUser user) { // insert, update 결과물
			super();
			this.id = user.getId();
			this.email = user.getEmail();
			this.password = user.getPassword();
			this.nickname = user.getNickname();
			this.mobile = user.getMoblie();
			this.mbtiType = user.getMbtiType();
			this.role = user.getRole();
		}
		
		
	}
}
