package com.thejoa703.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thejoa703.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow,Long>{
	// 팔로우 단건 조회 - 팔로워/ 팔로잉 findBy Optional<Follow>
    Optional<Follow> findByFollower_IdAndFollowee_Id(Long followerId, Long followeeId);
	// 팔로잉 목록 조회 - findBy -> AppUser follower Id 찾기  List<Follow>
    @EntityGraph(attributePaths = {"followee"})
    List<Follow> findByFollower_Id(Long followerId);
	// 팔로워 목록 조회 - findBy -> AppUser followee Id 찾기  List<Follow>
    @EntityGraph(attributePaths = {"follower"})
    List<Follow> findByFollowee_Id(Long followeeId);
	// 팔로잉 수 집계 - countBy -> AppUser follower Id 찾기  long
    long countByFollower_Id(Long followerId);
	// 팔로워 수 집계 - countBy -> AppUser followee Id 찾기  long
    long countByFollowee_Id(Long followeeId);
}
