package com.thejoa703.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.thejoa703.config.RedisConfig;
import com.thejoa703.dto.LoginRequest;
import com.thejoa703.dto.UserDto.UserRequestDto;
import com.thejoa703.dto.UserDto.UserResponseDto;
import com.thejoa703.security.JwtProperties;
import com.thejoa703.security.JwtProvider;
import com.thejoa703.security.TokenStore;
import com.thejoa703.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Tag(name="User Api", description = "사용자 관련 API") //swagger
@RestController  // @Controller + @ResponseBody
@RequestMapping("/auth")
@RequiredArgsConstructor // 권장사항
//@CrossOrigin(origins="*")
public class UserController {

    private final RedisConfig redisConfig;
	
    private final JwtProperties props;       // jwt 출입증 (설정값)    
    private final JwtProvider jwtProvider;   // jwt 토큰생성/검정 (accessToken/refreshToken)
    private final TokenStore tokenStore;     // jwt 저장소 
	private final UserService userService;
	
//    UserController(RedisConfig redisConfig) {
//        this.redisConfig = redisConfig;
//    } // @Autowire
	
	// 사용자 등록 (회원가입)
	// ResponseEntity - 상태코드 전달
	@Operation(summary="회원가입", description = "새로운 사용자를 등록합니다.") //swagger
	@PostMapping(value="/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<UserResponseDto> createUser(
			@ModelAttribute UserRequestDto request,
			@Parameter(description="프로필 이미지 파일")
			@RequestPart(name="ufile", required = false) MultipartFile ufile
	){
		return ResponseEntity.ok(userService.createUser(request, ufile));
//		UserResponseDto response = userService.createUser(request, ufile);
//		return ResponseEntity.status(HttpStatus.CREATED).body(response); // HttpStatus.CREATED 201
	}
	// 이메일 중복 확인
	@Operation(summary="이메일 중복 확인", description = "사용 중인 이메일인지 중복여부를 확인합니다") //swagger
	@GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(
    		@Parameter(description = "확인할 이메일") @RequestParam("email") String email) {
        return ResponseEntity.ok(userService.existsByEmail(email));
    }
	// 닉네임 중복 확인
	@Operation(summary = "닉네임 중복 확인", description = "사용 중인 닉네임인지 중복여부를 확인합니다")
    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(
    		@Parameter(description = "확인할 닉네임") @RequestParam("nickname") String nickname) {
        return ResponseEntity.ok(userService.existsByNickname(nickname));
    }
	// 로그인
//	@Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인 하여 세션을 생성합니다.")
//    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserResponseDto> login(
//            @RequestBody LoginRequest request,
////            HttpSession session // jakarta.servlet.http.HttpSession
//            HttpServletResponse response
//    ) {
////		Long userId = (Long) session.getAttribute("LOGIN_USER_ID");
////		if(userId == null) { return ResponseEntity.status(401).build(); } //권한 없음
////        return ResponseEntity.ok(userService.findById(userId));
//		// 사용자 인증처리
//		UserResponseDto user = userService.login(request);
//		// 사용자 id + 역할
//		String accessToken = jwtProvider.createAccessToken(user.getId().toString(), Map.of("role", user.getRole()));
//		// room 아예 빼기 (checkout)
//		String refreshToken = jwtProvider.createRefreshToken(user.getId().toString());
//		// redis 저장
//		tokenStore.saveRefreshToken(user.getId().toString(), refreshToken, (long) props.getRefreshTokenExpSeconds());
//		
//		// 3. 쿠키 설정
//		ResponseCookie cookie = ResponseCookie.from("refreshToken",refreshToken)
//				.httpOnly(true)
//				.secure(true)
//				.sameSite("Strict")
//				.path("/")
//				.maxAge(props.getRefreshTokenExpSeconds())
//				.build();
//		response.addHeader(org.springframework.http.HttpHeaders.SET_COOKIE, cookie.toString());
//		
//		
//		return ResponseEntity.ok(Map.of(
//                "accessToken", accessToken,
//                "user", user
//        ));
//    }
    @Operation(summary = "로그인 (Access Token + Refresh Token 발급)")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody LoginRequest request,
            HttpServletResponse response   
    ) { 
        UserResponseDto user = userService.login(request);
 
        String accessToken = jwtProvider.createAccessToken(
                user.getId().toString(),
                Map.of("role", user.getRole())
        );
 
        String refreshToken = jwtProvider.createRefreshToken(user.getId().toString());
 
        tokenStore.saveRefreshToken(
                user.getId().toString(),
                refreshToken,
                (long) props.getRefreshTokenExpSeconds()
        );
         
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)   
                .secure(true)  
                .sameSite("Strict")  
                .path("/")   
                .maxAge(props.getRefreshTokenExpSeconds())  
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString()); 
        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "user", user
        ));
    }

	
	// 로그아웃
//	@Operation(summary = "로그아웃", description = "현재 세션을 만료시켜 로그아웃합니다.")
//    @PostMapping("/logout")
//    public ResponseEntity<Void> logout(HttpSession session) {
//		session.invalidate();
//        return ResponseEntity.noContent().build();
//    }
    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
          @CookieValue(name = "refreshToken", required = false) String refreshToken,
                                       HttpServletResponse response) {
        var claims = jwtProvider.parse(refreshToken).getBody();
        String userId = claims.getSubject();

        tokenStore.deleteRefreshToken(userId);
 
        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());

        return ResponseEntity.noContent().build();
    }
	
	// 마이페이지
//    @Operation(summary = "현재 로그인한 사용자 정보 조회", description = "세션기반으로 현재 로그인된 사용자의 정보를 조회합니다.")
//    @GetMapping("/me")
//    public ResponseEntity<UserResponseDto> me(HttpSession session) {
//        Long userId = (Long)session.getAttribute("LOGIN_USER_ID");
//        if( userId == null ) {  return  ResponseEntity.status(401).build();  }   // 권한없음.
//        return  ResponseEntity.ok(  userService.findById(userId));  
//    }
    
    @Operation(summary = "현재 로그인한 사용자 정보 조회")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> me(HttpServletRequest request,
                 @CookieValue(name = "refreshToken", required = false) String refreshToken) {
        try { 
               
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);  // Bearer 제거
                var claims = jwtProvider.parse(token).getBody();  // 토큰 파싱
                String userId = claims.getSubject();  // 사용자 id 추출
                UserResponseDto user = userService.findById(Long.valueOf(userId));  // 사용자 조회
                return ResponseEntity.ok(user); // 사용자 변환
            }  
            if (refreshToken != null) {
                var claims = jwtProvider.parse(refreshToken).getBody();
                String userId = claims.getSubject();
                UserResponseDto user = userService.findById(Long.valueOf(userId));  // 사용자 조회
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(401).build();  // 인증 실패 401
        } catch (Exception e) {
            return ResponseEntity.status(401).build();  // 예외 발생시 인증 실패  401
        }
    }
    
	
	
	// 사용자 단건 조회 - /api/users/1  해당번호
	@Operation(summary="사용자 단건조회", description = "사용자 ID로 특정회원정보를 조회합니다.") //swagger
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id, HttpSession session){
		Long userId = (Long) session.getAttribute("LOGIN_USER_ID");
		if(userId == null) { return ResponseEntity.status(401).build(); } 
		
		UserResponseDto response = userService.findById(id);
		return ResponseEntity.ok(response); // ok 200
		// return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	//닉네임수정
	@Operation(summary = "닉네임 변경", description = "특정 사용자의 닉네임을 변경합니다.")
    @PatchMapping("/{userId}/nickname")  // Patch
    public ResponseEntity<UserResponseDto> updateNickname(
            @Parameter(description = "사용자 ID") @PathVariable("userId") Long userId,  // 경로에서 UserId 추출
            @Parameter(description = "변경할 닉네임") @RequestParam("nickname") String nickname   
    ) {
        return ResponseEntity.ok(userService.updateNickname(userId, nickname));
    }
	
	//이미지프로필수정
	@Operation(summary = "프로필 이미지 업로드/교체", description = "특정 사용자의 프로필이미지를 변경합니다.")
    @PostMapping(value = "/{userId}/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDto> updateProfileImage(
    		@Parameter(description = "사용자 ID") @PathVariable("userId") Long userId,
    		@Parameter(description = "변경할 프로필 이미지") @RequestParam("ufile") MultipartFile ufile
    ) {
        return ResponseEntity.ok(userService.updateProfileImage(userId, ufile));
    }
	
	//탈퇴
//	@Operation(summary = "회원 탈퇴", description = "로그인된 사용자 계정을 삭제하고 세션을 만료시킵니다.")
//	@DeleteMapping("/me")
//	public ResponseEntity<Void> deleteMe(HttpSession session) {
//		Long userId = (Long) session.getAttribute("LOGIN_USER_ID");
//		if(userId == null) { return ResponseEntity.status(401).build(); } 
//		
//		userService.deleteById(userId);
//		session.invalidate();
//		return ResponseEntity.noContent().build();
//	}
	
    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMe(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @CookieValue(name = "refreshToken", required = false) String refreshToken) {
        try { 
        	//accessToken 확인
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).build();
            }
            //accessToken 추출
            String accessToken = authHeader.substring(7);
            var claims = jwtProvider.parse(accessToken).getBody();
            String userId = claims.getSubject();
            // 해당 유저 삭제
            userService.deleteById(Long.valueOf(userId));
            // refreshToken 삭제
            if (refreshToken != null) {
                tokenStore.deleteRefreshToken(userId);
            } 
            // 쿠키 삭제
            ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Strict")
                    .path("/")
                    .maxAge(0)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
   
    @Operation(summary = "Access Token 재발급")
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refresh(@CookieValue("refreshToken") String refreshToken) {
        var claims = jwtProvider.parse(refreshToken).getBody();
        String userId = claims.getSubject();

        String stored = tokenStore.getRefreshToken(userId);
        if (stored == null || !stored.equals(refreshToken)) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid refresh token"));
        }

        String role = userService.findRoleByUserId(Long.valueOf(userId));

        String newAccessToken = jwtProvider.createAccessToken(
                userId,
                Map.of("role", role)
        );

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

}
