package com.thejoa703;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thejoa703.entity.AppUser;
import com.thejoa703.entity.Post;
import com.thejoa703.repository.AppUserRepository;
import com.thejoa703.repository.PostRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional // 테스트 완료 후에 데이터 자동 롤백
class Boot1ApplicationTests {
	
	@Autowired AppUserRepository appRepo;
	@Autowired PostRepository postRepo;
	
	private AppUser savedUser;
	private Post savedPost;
	
	// insert, update(save) select(findBy필드명) deleteById
	
	
    @BeforeEach
	void createTest() {
		// ========================
		// 1. CREATE (생성테스트)
		// ========================
		//// AppUser
		AppUser user = AppUser.builder()
				.email("z@z")
				.password("z")
				.nickname("first")
				.provider("local")
				.build();
		
		savedUser = appRepo.save(user);
		
		//// Post
		Post post = new Post();
		post.setContent("CRUD테스트용 게시글 내용 입니다.");
		post.setUser(savedUser);
		savedPost = postRepo.save(post);
	}
	
	@Test
	@DisplayName("1. CREATE 생성 테스트(save)")
	void testCreate() {
		assertThat(savedUser.getId()).isNotNull();		
		assertThat(savedPost.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("2. READ 생성 테스트(findBy)")
	void testRead() {
		Optional<AppUser> foundUser = appRepo.findById(savedUser.getId());
		assertThat(foundUser).isPresent(); //true, false		
		assertThat(foundUser.get().getNickname()).isEqualTo("first");
		
		Optional<AppUser> foundEmail = appRepo.findByEmail("z@z");
		assertThat(foundEmail).isPresent();
		assertThat(foundEmail.get().getEmail()).isEqualTo("z@z");
		
		List<Post> posts = postRepo.findByDeletedFalse();
		assertThat(posts).isNotEmpty();
	}

	@Test
	@DisplayName("3. UPDATE 생성 테스트(save)")
	void testUpdate() {
		savedUser.setNickname("zero");		
		appRepo.save(savedUser);
		
		savedPost.setContent("new ..... 20260727");
		postRepo.save(savedPost);
		
		AppUser updatedUser = appRepo.findById(savedUser.getId()).get();
		Post updatedPost = postRepo.findById(savedPost.getId()).get();
		
		assertThat(updatedUser).isNotNull();
		assertThat(updatedUser.getNickname()).isEqualTo("zero");
		assertThat(updatedPost).isNotNull();
		assertThat(updatedPost.getContent()).isEqualTo("new ..... 20260727");
	}
	
	@Test
	@DisplayName("4. DELETE 생성 테스트(delete)")
	void testDelete() {
		appRepo.delete(savedUser);
		postRepo.delete(savedPost);
		
		Optional<AppUser> deletedUser = appRepo.findById(savedUser.getId());
		Optional<Post> deletedPost = postRepo.findById(savedPost.getId());
		
		assertThat(deletedUser).isEmpty();
		assertThat(deletedPost).isEmpty();
	}
	
}
/// jpa: save(insert, update) / delete(delete) / findBy필드명(select)