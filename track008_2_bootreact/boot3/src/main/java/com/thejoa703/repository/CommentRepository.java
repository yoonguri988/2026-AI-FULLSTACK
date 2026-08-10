package com.thejoa703.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.thejoa703.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{
	// 특정 계시글의 삭제되지 않은 댓글 목록 조회
	// @ManyToOne - post가 1개여서 join 쿼리를 만들어줌
	// SELECT c FROM Comment WHERE c.post.id = :postId AND c.deleted = false
	List<Comment> findByPostIdAndDeletedFalse(Long postId);
	
	// SELECT COUNT(c.id) FROM Comment c WHERE c.post.id = :postId AND c.deleted=false
	long countByPostIdAndDeletedFalse(Long postId);
}
/*
1. findBy 조회         + AND
2. countBy 갯수        + OR
3. existsBy 존재여부    + 엔티티 안에 다른 엔티티 Long id
4. deleteBy
*/