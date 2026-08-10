package com.thejoa703.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

 
// 1. 토큰 (출입증)
@Data
@Configuration    // 설정 클래스
@ConfigurationProperties(prefix = "jwt") // application.yml jwt.* 연결   
public class JwtProperties {
    private String issuer;   // 누가: JWT 발급 주체
    private String secret;   // 토큰 서명에서 사용되는 비밀키
    private int accessTokenExpSeconds;   // accessToken 만료시간
    private int refreshTokenExpSeconds;  // refreshToken 만료시간
    private String header;  // JWT를 담을 HTTP 헤더 이름 - Authorization
    private String prefix;  // 헤더값 앞에 붙는 접두어 Bearer
}