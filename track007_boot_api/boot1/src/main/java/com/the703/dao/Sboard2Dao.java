package com.the703.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.the703.dto.Sboard2Dto;

@Mapper
public interface Sboard2Dao {
	// insert
	public int insert(Sboard2Dto dto);
	// 전체 select
	public List<Sboard2Dto> selectAll();
	// 전체 select (페이징을 곁들인)
	public List<Sboard2Dto> selectPaging(Map<String, Integer> para);
	// 전체 갯수
	public int selectCnt();
	// 해당 번호의 select
	public Sboard2Dto selectById(Sboard2Dto dto); 
	// 해당 번호의 조회수 올리기
	public int updateHit(Sboard2Dto dto); 
	// 해당 번호 업데이트
	public int update(Sboard2Dto dto); 
	// 해당 번호 삭제
	public int delete(Sboard2Dto dto);
}
