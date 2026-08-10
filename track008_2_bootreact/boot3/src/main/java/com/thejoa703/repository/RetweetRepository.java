package com.thejoa703.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thejoa703.entity.AppUser;
import com.thejoa703.entity.Post;
import com.thejoa703.entity.Retweet;

@Repository
public interface RetweetRepository extends JpaRepository<Retweet,Long> {
	// 특정유저가(AppUser user) 특정게시글(Post originalPost) 리트윗 (단건조회)
    Optional<Retweet> findByUser_IdAndOriginalPost_Id(Long userId, Post postId);

    // 중복방지용: 집계 - 특정유저(AppUser user) 특정게시글(Post originalPost) 리트윗
    long countByUser_IdAndOriginalPost_Id(Long userId, Post postId);

    // 리트윗취소: 특정유저(AppUser user) 특정게시글(Post originalPost) 취소
    @Modifying
    @Transactional
    void deleteByUser_IdAndOriginalPost_Id(Long userId, Post postId);

    // 특정게시글(Post originalPost) 리트윗 수 집계
    long countByOriginalPost_Id(Post postId);

    // 특정유저 리트윗한 글 id 목록 조회
    @Query("SELECT r.originalPost.id FROM Retweet r WHERE r.user.id = :userId")
    List<Long> findOriginalPostIdsByUserId(@Param("userId") Long userId);

	/*
	 * "SELECT po.* FROM POSTS po " + "WHERE po.ID IN ( " +
	 * "    SELECT DISTINCT r.ORIGINAL_POST_ID " + "    FROM RETWEETS r " +
	 * "    WHERE r.APP_USER_ID = :userId " + ") AND po.DELETED = 0 " +
	 * "ORDER BY po.CREATED_AT DESC " +
	 * "OFFSET :offset ROWS FETCH FIRST :size ROWS ONLY"
	 */
    // 내가 리트윗한 글 페이징조회 (복잡한 native SQL)
    @Query(
        value ="SELECT po.* FROM POSTS po " +
                "WHERE po.ID IN ( " +
                "    SELECT DISTINCT r.ORIGINAL_POST_ID " +
                "    FROM RETWEETS r " +
                "    WHERE r.APP_USER_ID = :userId " +
                ") AND po.DELETED = 0 " +
                "ORDER BY po.CREATED_AT DESC " +
                "OFFSET :offset ROWS FETCH FIRST :size ROWS ONLY",
        nativeQuery = true
    )
    List<Post> findRetweetedPostsByUserId(@Param("userId") Long userId, @Param("offset") int offset, @Param("size") int size);
}
