package ex03;

import java.net.UnknownHostException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the703.dao.UserMapper;
import com.the703.dto.AuthDto;
import com.the703.dto.UserDto;
import com.the703.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:config/*-context.xml")
@ContextConfiguration(locations = {
		"classpath:config/root-context.xml",
		"classpath:config/security-context.xml"		
})
public class ModelTest002 {
	@Autowired UserService service;
	@Autowired UserMapper userMapper;
	
	@Autowired @Qualifier ("passwordEncoder") PasswordEncoder pwencoder;
	// import org.springframework.security.crypto.password.PasswordEncoder;

	
	/* security  */
	@Test public void test3() {
		AuthDto dto2 = new AuthDto();
		dto2.setEmail("a@a");
		System.out.println(userMapper.readAuth(dto2));
		
		/* 권한 두개 주고 해당 유저 정보 가져오기*/
//		AuthDto dto1 = new AuthDto();
//		dto1.setEmail("a@a"); dto1.setAuth("ROLE_MEMBER");
//		dto1.setEmail("a@a"); dto1.setAuth("ROLE_ADMIN");
//		System.out.println(userMapper.insertAuth(dto1));
		
		/* 회원가입 (암호화) pwencoder.encode("a") */
//		UserDto dto = new UserDto();
//		dto.setNickname("a"); dto.setBpass(pwencoder.encode("a"));
//		dto.setEmail("a@a"); dto.setMobile("01011111111");
//		System.out.println(service.insert(dto));
		
	}
	
	@Ignore //@Test
	public void test2() {
		AuthDto dto = new AuthDto();
		dto.setEmail("first@gmail.com");
		System.out.println(userMapper.readAuth(dto));
	}
	
	@Ignore //@Test
	public void test1() throws UnknownHostException {
		// 이메일 중복: findByEmail - email
		System.out.println(service.findByEmail("second@gmail.com"));
		
		// 마이페이지: findByUno - uno
		System.out.println(service.findByUno(22)); // 갖고있는 유저 번호
		
		// 로그인: findLogin - email=#{email} and bpass=#{bpass}
		UserDto dto = new UserDto();
		dto.setEmail("second@gmail.com"); dto.setBpass("2222");
		System.out.println(service.findLogin(dto));
		
		// 회원가입: insert - UserDto: nickname, bpass, email, mobile, bip
//		UserDto dto2 = new UserDto();
//		dto2.setNickname("seconqqqd"); dto.setBpass("2222");
//		dto2.setEmail("seconqqqd@gmail.com"); dto.setMobile("01022222222");
//		dto2.setBip(InetAddress.getLocalHost().getHostAddress());
//		System.out.println(service.insert(dto));
	}
}
