package ex02;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the703.dao.BoardMapper;
import com.the703.dao.TestMapper;
import com.the703.dao.UserInfoMapper;
import com.the703.dto.BoardDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/root-context.xml")
public class ModelTest001 {
	@Autowired ApplicationContext context;
	@Autowired DataSource dataSource;
	@Autowired SqlSession sqlSession;
	
	@Autowired TestMapper test;
	@Autowired BoardMapper board;
	@Autowired UserInfoMapper userinfo;
	
	@Test
	public void usersTest() {
		System.out.println(userinfo.selectAll());
	}
	
	@Ignore //@Test
	public void test3() { System.out.println(context);}
	@Ignore //@Test
	public void test2() { System.out.println(sqlSession);}
	
	@Ignore //@Test
	public void test1() { System.out.println(test.now());}
	
	@Ignore	//@Test
	public void test() throws UnknownHostException {
//		BoardDto dto = new BoardDto();
//		dto.setBname("사용자"); dto.setBpass("1234");
//		dto.setBtitle("n번째 글쓰기"); dto.setBcontent("내용: n번째 글쓰기");
//		dto.setBip(InetAddress.getLocalHost().getHostAddress());
//		System.out.println(board.insert(dto));
		
		//System.out.println(board.delete(18));
		
//		BoardDto dto = new BoardDto();
//		dto.setBtitle("수정)n번째 글쓰기"); dto.setBcontent("수정)내용: n번째 글쓰기");
//		dto.setBno(19);
//		dto.setBip(InetAddress.getLocalHost().getHostAddress());
//		System.out.println(board.update(dto));
		
//		System.out.println(board.select(17));
		
		System.out.println(board.selectAll());
	}
}
