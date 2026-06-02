package ex02;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the703.dao.BoardMapper;
import com.the703.dao.TestMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/root-context.xml")
public class ModelTest001 {
	@Autowired ApplicationContext context;
	@Autowired DataSource dataSource;
	@Autowired SqlSession sqlSession;
	
	@Autowired TestMapper test;
	@Autowired BoardMapper board;
	
	@Test
	public void test() {
		//System.out.println(board.insert(null));
		//System.out.println(board.delete(17));
		//System.out.println(board.update(null));
		System.out.println(board.select(17));
		System.out.println(board.selectAll());
	}
}
