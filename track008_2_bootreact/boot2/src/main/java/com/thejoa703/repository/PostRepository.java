package com.thejoa703.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thejoa703.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	List<Post> findByDeletedFalse(); // List<Post> 결과가 여러개 일때: List
	// 비교 - 결과값이 1개거나 없을때(null) Optional
	//Optional<AppUser> findByEmail(String email);
	
	@Query(
		value="SELECT * FROM ( " +
              "SELECT p.*, ROWNUM AS rnum " +
              "FROM (SELECT * FROM POSTS WHERE DELETED = 0 ORDER BY CREATED_AT DESC) p " + 
              ") " +
              "WHERE rnum BETWEEN :start AND :end",
		nativeQuery=true
	)
	List<Post> findPostsWithPaging(@Param("start") int start, @Param("end") int end);
}
/* 
(1) 사용할 수 있는 기본 SQL
  create - save: insert into posts (컬럼,,,) values (?,,,)
  read   - findAll  : select * from posts
           findById : select * from posts where id=?
  update - save : update posts set 컬럼=?,,, where id=?
  delete - deleteById : delete posts where id=?
(2) 삭제 안 된 게시글 찾기 findBy필드명
(3) 페이징 - @Query

"SELECT * FROM ( " +
                "SELECT p.*, ROWNUM AS rnum " +
                "FROM (SELECT * FROM POSTS WHERE DELETED = 0 ORDER BY CREATED_AT DESC) p " + 
                ") " +
                "WHERE rnum BETWEEN :start AND :end"
 */