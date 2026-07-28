package com.thejoa703;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thejoa703.dto.UserDto.UserRequestDto;
import com.thejoa703.dto.UserDto.UserResponseDto;
import com.thejoa703.entity.AppUser;
import com.thejoa703.entity.Post;
import com.thejoa703.repository.AppUserRepository;
import com.thejoa703.service.PostService;
import com.thejoa703.service.UserService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional // 테스트 완료 후에 데이터 자동 롤백
class Boot1ApplicationTests2_Service {
	
	@Autowired UserService userService;
	@Autowired PostService postService;
	
	@Autowired AppUserRepository userRepo;
	
	private AppUser testUser;
	
	@BeforeEach
	void createTest() {
		//// AppUser 공통으로 사용할 테스트용 회원
		AppUser user = AppUser.builder()
				              .email("z@z")
				              .password("z")
				              .nickname("first")
				              .provider("local")
				              .build();
		testUser = userRepo.save(user);
	}	
	
	@Test
	@DisplayName("1. 회원가입 및 사용자 간단 테스트")
	void testCreate() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setEmail("1@1");
        requestDto.setPassword("1");
        requestDto.setNickname("first");
        requestDto.setMobile("010111111");
        requestDto.setMbtiTypeId(2);
        
        UserResponseDto createdUser = userService.createUser(requestDto);
        assertThat(createdUser.getId()).isNotNull();
        assertThat(createdUser.getEmail()).isEqualTo("1@1"); // first
        
        UserResponseDto foundUser = userService.getUser(createdUser.getId());
        assertThat(foundUser.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("2. 게시글 작성 테스트")
	void testPost() {
		Post createdPost = postService.createPost(testUser.getId(), "테스트");
		assertThat(createdPost.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("3. 게시글 수정 및 검색 테스트")
	void testUpdatePost() {
		Post createdPost = postService.createPost(testUser.getId(), "테스트");
		assertThat(createdPost.getId()).isNotNull();
		
		Post updatedPost = postService.updatePost(createdPost.getId(), "수정");
		assertThat(updatedPost.getContent()).isEqualTo("수정");
		
		Post foundPost = postService.getPostById(createdPost.getId());
		assertThat(foundPost.getContent()).isEqualTo("수정");		
	}
	
	@Test
	@DisplayName("4. 게시글 삭제 테스트")
	void testDeletePost() {
		Post createdPost = postService.createPost(testUser.getId(), "테스트");
		assertThat(createdPost.getId()).isNotNull();
		
		postService.deletePost(createdPost.getId());
		
		// 여러 글 조회
		List<Post> posts = postService.getAllPosts();
		
		boolean exists = posts.stream()
		                      .anyMatch((post)->post.getId().equals(createdPost.getId()));
		assertThat(exists).isFalse();
	}
}
/// jpa: save(insert, update) / delete(delete) / findBy필드명(select)