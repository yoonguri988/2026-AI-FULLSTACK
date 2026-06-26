package com.the703;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.the703.dao.Sboard2Dao;
import com.the703.dao.TestDao;
import com.the703.dto.Sboard2Dto;
import com.the703.service.Sboard2Service;

@SpringBootTest
class Boot1ApplicationTests {
	@Autowired TestDao dao;
	@Autowired Sboard2Dao sboard2Dao;
	@Autowired Sboard2Service sboard2Service;
	
	@Disabled //@Test 
	public void test10_delete() {
		Sboard2Dto dto = new Sboard2Dto();
		dto.setId(3);
		
		assertEquals(1, sboard2Service.delete(dto));
	}
	
	@Disabled //@Test
	public void test09_update() {
		Sboard2Dto dto = new Sboard2Dto();
		dto.setAppUserId(1); dto.setBtitle("title-new"); dto.setBcontent("title-new");
		dto.setBpass("1111"); dto.setId(8);
		
		MockMultipartFile file = new   MockMultipartFile("file" , "test-1.txt" , "text/plain" , "data".getBytes());
	    //import org.springframework.mock.web.MockMultipartFile;
		
		int result = sboard2Service.update(file, dto);
		assertEquals(1, result);
	}
	
	@Test
	public void test08_detail() {
		Sboard2Dto dto = new Sboard2Dto();
		dto.setId(8);
		
		assertEquals(8, sboard2Service.detail(dto).getId());
	}
	
	@Disabled  //@Test 
	public void test07_service_paging() {
	    List<Sboard2Dto> result = sboard2Service.list10(1);
	    assertEquals(7, result.size());
	}
	
	@Disabled  //@Test 
	public void test06_service_insert() {
		Sboard2Dto dto = new Sboard2Dto();
		dto.setAppUserId(1); dto.setBtitle("title"); dto.setBcontent("content");
		dto.setBpass("1111");
		
	    MockMultipartFile file = new   MockMultipartFile("file" , "test.txt" , "text/plain" , "data".getBytes());
	    //import org.springframework.mock.web.MockMultipartFile;
	      
	    int result = sboard2Service.insert(file, dto);
	    assertEquals(1, result);
	}
	
	@Disabled //@Test
	public void test05_delete(){
		// 삭제
		Sboard2Dto dto = new Sboard2Dto();
		dto.setId(2);
		
		int result = sboard2Dao.delete(dto);
		assertEquals(1, result);  
		
	}
	@Disabled //@Test
	public void test04_update(){
		// 수정
		Sboard2Dto dto = new Sboard2Dto();
		dto.setId(3);
		dto.setBtitle("title-new"); dto.setBcontent("content-new"); dto.setBfile("1.png2");
		
		int result = sboard2Dao.update(dto);
		assertEquals(1, result);  
	}
	@Disabled //@Test
	public void test03_byId(){
		//id, 조회수 올리기
		Sboard2Dto dto = new Sboard2Dto();
		dto.setId(5);
		
		sboard2Dao.updateHit(dto);
		
		Sboard2Dto res = sboard2Dao.selectById(dto);
	}
	
	@Disabled //@Test
	public void test02_paging(){
		Map<String, Integer> map = new HashMap<>();
		map.put("start", 0);
		map.put("end", 10);
		List<Sboard2Dto> list10 = sboard2Dao.selectPaging(map);
		
		assertEquals(4, list10.size());
		assertNull(list10);
		//assertEquals(6, sboard2Dao.selectCnt());
	}
	
	@Disabled //@Test
	public void test01_Insert() throws UnknownHostException {
		Sboard2Dto dto = new Sboard2Dto();
		dto.setAppUserId(1); dto.setBtitle("title"); dto.setBcontent("content");
		dto.setBpass("1111"); dto.setBfile("1.png"); dto.setBip(InetAddress.getLocalHost().getHostAddress());
		
		int result = sboard2Dao.insert(dto);
		// System.out.println("......1 >" + result); // 기존 방식: 수동으로 값 확인 - 콘솔에 1
		assertEquals(1, result);                     // 자동 확인: 결과물이 1인지 junit 체크
	}
	
	//@Ignore - JUnit4
	@Disabled //@Test
	void contextLoads() {
		System.out.println("...................................");
		System.out.println(dao.readTime());
		System.out.println("...................................");
	}

}
