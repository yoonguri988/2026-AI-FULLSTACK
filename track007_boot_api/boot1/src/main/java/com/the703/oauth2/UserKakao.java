package com.the703.oauth2;

import java.util.Map;

import com.the703.UserInfoOAuth2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserKakao implements UserInfoOAuth2 {
	private final Map<String, Object> attributes;
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> getAccount() {
		if (attributes == null) return null;
		Object account = attributes.get("kakao_account");
		return account instanceof Map? (Map<String, Object>)account : null;
	}
	
	@SuppressWarnings("unchecked")
    private Map<String, Object> getProfile() {
        Map<String, Object> account = getAccount();
        if (account == null) return null;
        Object profile = account.get("profile");
        return profile instanceof Map ? (Map<String, Object>) profile : null;
    }
	
	@Override
	public String getProvider() { return "kakao"; }

	@Override
	public String getProviderId() {
		if (attributes == null) return null;
		Object id = attributes.get("id");
		return id != null ? id.toString() : null;
	}

	@Override
	public String getEmail() {
		Map<String, Object> account = getAccount();
		if (account == null) return null;
		
		Object email = account.get("email");
		return email != null? email.toString() : null;
	}

	@Override
	public String getNickname() {
		Map<String, Object> profile = getProfile();
        if (profile == null) return null;
        
        Object nickname = profile.get("nickname");
        return nickname != null ? nickname.toString() : null;	}

	@Override
	public String getImage() {
        Map<String, Object> profile = getProfile();
        if (profile == null) return null;

        Object imageUrl = profile.get("profile_image_url");
        return imageUrl != null ? imageUrl.toString() : null;
	}


}
/*
<kakao>
{
    id=2632890179, 
    connected_at=2023-01-22T08:17:54Z, 
    properties = {nickname=효정}, 
    kakao_account = {
        profile_nickname_needs_agreement=false, 
        profile={nickname=효정}, 
        has_email=true, 
        email_needs_agreement=false, 
        is_email_valid=true, 
        is_email_verified=true, 
        email=sally03915@naver.com
    }
}
 */