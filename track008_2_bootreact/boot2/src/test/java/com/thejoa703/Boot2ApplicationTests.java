package com.thejoa703;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.thejoa703.domain.DeptUser;
import com.thejoa703.entity.AppUser;
import com.thejoa703.entity.Comment;
import com.thejoa703.entity.Hashtag;
import com.thejoa703.entity.Image;
import com.thejoa703.entity.Post;
import com.thejoa703.entity.PostLike;
import com.thejoa703.mapper.DeptUserMapper;
import com.thejoa703.repository.AppUserRepository;
import com.thejoa703.repository.CommentRepository;
import com.thejoa703.repository.DeptUserRepository;
import com.thejoa703.repository.HashtagRepository;
import com.thejoa703.repository.ImageRepository;
import com.thejoa703.repository.PostLikeRepository;
import com.thejoa703.repository.PostRepository;

@SpringBootTest
@Transactional
class Boot2ApplicationTests {
	@Autowired private AppUserRepository appUserRepo;
	@Autowired private PostRepository postRepo;
	@Autowired private ImageRepository imageRepo;
	@Autowired private HashtagRepository hastagRepo;
	@Autowired private CommentRepository commentRepo;
	@Autowired private PostLikeRepository postLikeRepo;
	
	//
	@Autowired private DeptUserMapper deptUserMapper;
	@Autowired private DeptUserRepository deptUserRepo;
    @Autowired private jakarta.persistence.EntityManager entityManager;
	
	// 테스트공통데이터: 사용자2명 + 게시글1개
	private AppUser user1;
	private AppUser user2;
	private Post post;
	
	@BeforeEach
	void setup() {
		// 사용자 생성
		String email1 = "user1_" + UUID.randomUUID() + "@test.com";
		String email2 = "user2_" + UUID.randomUUID() + "@test.com";

		user1 = new AppUser();
		user1.setEmail(email1);
		user1.setPassword("pass123");
		user1.setNickname("user1");
		user1.setProvider("local");
		user1.setDeleted(false);

		user2 = new AppUser();
		user2.setEmail(email2);
		user2.setPassword("pass123");
		user2.setNickname("user2");
		user2.setProvider("local");
		user2.setDeleted(false);
		
		appUserRepo.save(user1);
		appUserRepo.save(user2);

		// 게시글 생성
		post = new Post();
		post.setContent("테스트 게시글");
		post.setUser(user1);
		post.setDeleted(false);
		postRepo.save(post);
	}
	
	// -------------------------------------
	// AppUserRepository
	// -------------------------------------	
	@Test
	@DisplayName("■ AppUserRepository-CRUD")
	void testAppUserRepository() {
		//이메일 중복검사
		assertThat(appUserRepo.findByEmail(user1.getEmail()).get().getEmail())
			.isEqualTo(user1.getEmail());
	}

	@Test
	@DisplayName("■ ImageRepository-CRUD")
	void testImageRepository() {
		// 이미지생성가능
		Image image = new Image();
		image.setSrc("1.png");
		image.setPost(post);
		imageRepo.save(image);
		// 단건조회
		assertThat(imageRepo.findById(image.getId()).get().getSrc())
			.isEqualTo("1.png");
		// 삭제후조회불가확인
		imageRepo.delete(image);
		assertThat(imageRepo.findById(image.getId()))
			.isEmpty();
	}
	// --------------------------------------------
	// HashtagRepository
	// --------------------------------------------
	// insert : save / select:findBy / update:save / delete:delete
	@Test
	@DisplayName("■ HashtagRepository-CRUD")
	void testHashtagRepository() {
		// 해쉬태그저장
		Hashtag tag = new Hashtag();
		tag.setName("haha");
		hastagRepo.save(tag);
		// 포스트에 저장
		post.getHashtags().add(tag);
		tag.getPosts().add(post);
		postRepo.save(post);
		// 검색
		Optional<Hashtag> witPosts = hastagRepo.findByNameWithPosts("haha");
		assertThat(witPosts).isPresent();
		assertThat(witPosts.get().getPosts()).isNotEmpty();
		assertThat(witPosts.get().getName()).isEqualTo("haha");
	}
	
	@Test
	@DisplayName("■ CommentRepository - CRUD")
	void testCommentRepository() {
		// 댓글 생성
		Comment comment = new Comment();
		comment.setContent("테스트댓글");
		comment.setDeleted(false);
		comment.setUser(user1);
		comment.setPost(post);
		commentRepo.save(comment);
		
		// 댓글 조회
		List<Comment> comments = commentRepo.findByPostIdAndDeletedFalse(post.getId());
		assertThat(comments.size()).isEqualTo(1);
		
		// 댓글 수정
		comment.setContent("수정된 댓글");
		commentRepo.save(comment);
		assertThat(commentRepo.findById(comment.getId()).get().getContent())
			.isEqualTo("수정된 댓글");
		
		// 댓글 삭제
		comment.setDeleted(true);
		commentRepo.save(comment);
		List<Comment> rcomments = commentRepo.findByPostIdAndDeletedFalse(post.getId());
		assertThat(rcomments.size()).isEqualTo(0);
	}
	
	@Test
	@DisplayName("■ PostLikeRepository - CRUD")
	void testPostLikeRepository() {
		//좋아요 생성
		PostLike like = new PostLike(user2, post);
		postLikeRepo.save(like);
		
		//특정유저가 특정게시글 좋아요 했는지
		Optional<PostLike> found = postLikeRepo.findByUser_IdAndPost_Id(user2.getId(), post.getId());
		assertThat(found).isPresent();
		//특정게시글 좋아요 수 집계
		long count = postLikeRepo.countByUser_IdAndPost_Id(user2.getId(), post.getId());
		assertThat(count).isEqualTo(1L);
		
		//특정게시글 좋아요 수 취소
		postLikeRepo.deleteByUser_IdAndPost_Id(user2.getId(), post.getId());
		long rcount = postLikeRepo.countByUser_IdAndPost_Id(user2.getId(), post.getId());
		assertThat(rcount).isEqualTo(0L);
	}
	
	@Test
	@DisplayName("DeptUserMapper - CRUD")
	void testDeptUserMapper() {
		DeptUser dept1 = new DeptUser();
	      dept1.setDeptno(10L);
	      dept1.setDname("영업부");
	      dept1.setLoc("서울");
	      
	      DeptUser dept2 = new DeptUser();
	      dept2.setDeptno(20L);
	      dept2.setDname("개발부");
	      dept2.setLoc("부산");
		  //1.repository - 간단한 curd 이용
	      //JPA 간단한 CRUD
	      deptUserRepo.save(dept1);
	      deptUserRepo.save(dept2);
	      //Mybatis - 복잡한 sql 처리
	      entityManager.flush();
	      entityManager.clear();
	      
	      //2.mapper에 있는 찾기 메서드: findByNameKeyword("영업")
	      List<DeptUser> lists = deptUserMapper.findByNameKeyword("영업");
	      
	      //3.검증 assertThat
	      assertThat(deptUserMapper.findByNameKeyword("영업").get(0).getDname())
			.contains("영업부");
	}
}
