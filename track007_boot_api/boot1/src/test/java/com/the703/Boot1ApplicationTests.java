package com.the703;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.the703.dao.TestDao;

@SpringBootTest
class Boot1ApplicationTests {
	@Autowired TestDao dao;
	
	@Test
	void contextLoads() {
		System.out.println("...................................");
		System.out.println(dao.readTime());
		System.out.println("...................................");
	}

}
