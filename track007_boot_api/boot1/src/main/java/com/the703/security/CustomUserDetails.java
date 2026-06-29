package com.the703.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.the703.dto.AppUserAuthDto;
import com.the703.dto.AppUserDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails{ // UserDetails (security), oauth2-client
	private AppUserDto user;
	private AppUserAuthDto authDto;
	private Map<String, Object> attirubutes = new HashMap<>();

	///// 1. 일반 로그인
	public CustomUserDetails(AppUserDto user, AppUserAuthDto authDto) {
		super();
		this.user = user;
		this.authDto = authDto;
		this.attirubutes.put("email", user.getEmail());
		this.attirubutes.put("provider", user.getProvider());
	}
	/////

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(authDto == null || authDto.getAuthList() == null || authDto.getAuthList().isEmpty()) {
			return List.of(new SimpleGrantedAuthority("ROLE_MEMBER"));
		} // 권한 없으면 ROLE_MEMBER

		return authDto.getAuthList().stream()
				                    .filter(a->a.getAuth() != null && !a.getAuth().isBlank())
				                    .map(a->new SimpleGrantedAuthority(a.getAuth()))
				                    .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail()+":"+user.getProvider(); //1@1 : local, 2@2 : kakao
	}
	
	///// 사용자들이 필요한 정보를 가져올수 있다.
	public Integer getAppUserId() { return user.getAppUserId(); }
	public String getEmail() { return user.getEmail(); }
	public String getProvider() { return user.getProvider(); }

}
