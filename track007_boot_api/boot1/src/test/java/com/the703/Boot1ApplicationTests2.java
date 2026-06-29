package com.the703;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.the703.dao.AppUserDao;
import com.the703.dto.AppUserAuthDto;
import com.the703.dto.AppUserDto;
import com.the703.dto.AuthDto;
import com.the703.service.AppUserService;

@SpringBootTest
public class Boot1ApplicationTests2 {
	@Autowired AppUserDao dao;
	@Autowired AppUserService service;
	
	//삭제
	@Disabled //@Test
	public void delete_Service_User() {
		AppUserDto user = new AppUserDto();
		user.setEmail("2@2"); user.setPassword("2"); user.setProvider("local");
		
		int result = service.delete(user, true);
		
		assertEquals(1, result);
	}
	
	//수정
	@Test
	public void update_Service_User() {
		AppUserDto user = new AppUserDto();
		user.setAppUserId(43);
		user.setEmail("2@2"); user.setPassword("2"); user.setProvider("local");
		
		user.setMbtiTypeId(3); user.setUfile("3.png"); user.setMobile("01033333333"); user.setNickname("3");
		user.setProviderId("local_003");
		
		MockMultipartFile file = new MockMultipartFile("file", "test_3.txt", "text/plain","data".getBytes());
		
		int result = service.update(file, user); 
		
		assertEquals(1, result);
	}
	
	//아이디중복
	@Disabled //@Test
	public void iddouble_Service_User() {
		int result = service.iddoubleByEmail("2@2","local");
		
		assertEquals(1, result);
	}
	
	//마이페이지
	@Disabled //@Test
	public void mypage_Service_User() {
		AppUserDto result = service.selectEmail("2@2", "local");
		
		assertNotNull(result);
		assertEquals("2@2", result.getEmail());
	}
	
	//로그인
	@Disabled //@Test
	public void login_Service_User() {
		AppUserAuthDto login = service.readAuthByEmail("2@2", "local");
		
		assertNotNull(login);
		assertEquals("2@2", login.getEmail());
		assertTrue(login.getAuthList().stream().anyMatch(a->"ROLE_MEMBER".equals(a.getAuth())));
	}

	//회원가입
	@Disabled //@Test
	public void insert_Service_User() {
		AppUserDto user = new AppUserDto();
		user.setEmail("2@2"); user.setPassword("2"); user.setMbtiTypeId(2);
		user.setUfile("2.png"); user.setMobile("01022222222"); user.setNickname("2");
		user.setProvider("local"); user.setProviderId("local_002");
		
		MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain","data".getBytes());
		
		int result = service.insert(file, user);
		assertEquals(1, result);
	}
	
	/////////////////////////////////////////////////////
	
	@Disabled // @Test
	public void update_User() {	//6. 수정 (동적 sql)
		AppUserDto user = new AppUserDto();
		user.setAppUserId(22);
		user.setPassword("2"); user.setMbtiTypeId(2);
		user.setUfile("2.png"); user.setMobile("01022222222"); user.setNickname("2");
		user.setProvider("local"); user.setProviderId("local_002");
	
		int result = dao.updateAppUser(user);
		assertEquals(1, result);
	}
	
	@Disabled // @Test
	public void delete_User() {//5. 사용자 삭제 + 권한 삭제
		AppUserDto user = new AppUserDto();
		user.setEmail("1@1");
		
		int result = dao.deleteAppUser(user);
		assertEquals(1, result);
		
		AuthDto auth = new AuthDto();
		auth.setEmail("1@1");
		
		int result_auth = dao.deleteAuth(auth);
		assertEquals(1, result_auth);
	}
	
	@Disabled// @Test
	public void mypage_User() {	//4. 마이페이지
		AppUserDto user = new AppUserDto();
		user.setEmail("1@1");
		
		AppUserDto result = dao.findByEmail(user);
		assertNotNull(result);
	}
	
	@Disabled // @Test 
	public void iddouble_User() { //3. 아이디 중복
		AppUserDto user = new AppUserDto();
		user.setEmail("1@1");
		
		int result = dao.iddoubleByEmail(user);
		assertEquals(1, result);
	}
	
	
	@Disabled // @Test 
	public void login_User() { //2. 로그인
		AppUserDto user = new AppUserDto();
		user.setEmail("1@1");
		
		AppUserAuthDto result = dao.readAuthByEmail(user);
		assertNotNull(result);
	}
	
	
	@Disabled// @Test
	public void insert_User() { //1. 회원 가입 - 유저등록 + 권한등록
		AppUserDto user = new AppUserDto();
		user.setEmail("1@1"); user.setPassword("1"); user.setMbtiTypeId(1);
		user.setUfile("1.png"); user.setMobile("01011111111"); user.setNickname("1");
		user.setProvider("local"); user.setProviderId("local_001");
		
		int result = dao.insertAppUser(user);
		assertEquals(1,result); // 예상되는 결과, 코드
		//org.junit.jupiter.api.Assertions.assertEquals
		
		AuthDto auth = new AuthDto();
		auth.setEmail("1@1"); auth.setAuth("ROLE_USER");
		int result_auth = dao.insertAuth(auth);
		assertEquals(1, result_auth); // 예상되는 결과, 코드
	}
	
}
