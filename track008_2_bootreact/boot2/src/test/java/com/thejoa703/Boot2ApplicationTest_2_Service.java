package com.thejoa703;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import com.thejoa703.dto.LoginRequest;
import com.thejoa703.dto.UserDto.UserRequestDto;
import com.thejoa703.dto.UserDto.UserResponseDto;
import com.thejoa703.service.PostService;
import com.thejoa703.service.UserService;

@SpringBootTest
@Transactional
class Boot2ApplicationTest_2_Service {
	// 서비스
	@Autowired UserService userSerivce;
	@Autowired PostService postService;

	private Long  createTestUser(String email, String provider) {
		UserRequestDto signupDto = new UserRequestDto();
	    signupDto.setEmail(email);
	    signupDto.setPassword("password123");
	    signupDto.setNickname("user1");
	    signupDto.setProvider(provider);

	    MockMultipartFile profileImage = new MockMultipartFile(
                "profileImage",
                "test.png",
                "image/png",
                "test image content".getBytes()
        );

        UserResponseDto res = userSerivce.createUser(signupDto, profileImage);
        
        return res.getId();
	}

	// ---------------------------------------------------------------------
	// AppUserService
	// ---------------------------------------------------------------------
	@Test
	@Order(1)
	@DisplayName("■ AppUserService-CRUD ")
	void testAppUserService() {
		long userId =  createTestUser("test@email.com","local");
		
		LoginRequest loginDto = new LoginRequest("test@email.com", "password123", "local");
		
		UserResponseDto loginRes = userSerivce.login(loginDto);
		assertThat(loginRes).isNotNull();
		assertThat(loginRes.getId()).isEqualTo(userId);

		// 이메일 중복 검사 - 존재 확인
		assertThat(userSerivce.existsByEmail("test@email.com")).isTrue();
		// 닉네임 중복 검사 - 존재 확인
		assertThat(userSerivce.existsByNickname("user1")).isTrue();
		
		// 마이페이지
		UserResponseDto foundUser = userSerivce.findById(userId);
		assertThat(foundUser.getNickname()).isEqualTo("user1");
		// 유저 닉넴 수정
		UserResponseDto updatedUser = userSerivce.updateNickname(userId, "1111");
		assertThat(updatedUser.getNickname()).isEqualTo("1111");
		// 유저 삭제
		userSerivce.deleteById(userId);
//		UserResponseDto deletedUser = userSerivce.findById(userId);
//		assertThat(deletedUser).isNull();
	}

	// ---------------------------------------------------------------------
	// PostService
	// ---------------------------------------------------------------------
//		@Test
//		@DisplayName("■ PostService-CRUD ")
//		void testPostService() { 
//			//게시글 단건조회
//			PostResponseDto found = postService.getPost(post.getId());
//			assertThat(    found.getContent()     ).isEqualTo(       "테스트 게시글"   ); 
//			
//			//게시글 수정
//			PostRequestDto updateReq = new PostRequestDto("수정된 게시글" , "#newTag");
//			PostResponseDto updated  = postService.updatePost(user1Dto.getId(), post.getId(), updateReq, null);
//			assertThat(    updated.getContent()     ).isEqualTo(       "수정된 게시글"   ); 
//			
//			//해시태그검색  
//			 List<PostResponseDto>  byTag =   postService.getPostsByHashtag("#newTag");
//			 assertThat(    byTag     ).isNotEmpty();
//			
//			 //deletePost
//			 postService.deletePost( user1Dto.getId(), post.getId());
//			 assertThrows(     IllegalArgumentException.class , () -> postService.getPost(post.getId())   );
//			 
//		}

	// ---------------------------------------------------------------------
	// CommentService
	// ---------------------------------------------------------------------
//		@Test
//		@DisplayName("■ CommentService-CRUD ")
//		void testCommentService() { 
//			// 댓글작성
//			CommentRequestDto   commentReq = new CommentRequestDto( post.getId() , "테스트 댓글" );
//			CommentResponseDto  comment    = commentService.createComment(user2Dto.getId() , commentReq  );
//			assertThat(    comment.getContent()     ).isEqualTo(       "테스트 댓글"   ); 
//			
//			// 댓글조회
//			List<CommentResponseDto>  comments =  commentService.getCommentsByPost(post.getId());
//			assertThat( comments ).hasSize(1);
//			
//			// 댓글수정
//			CommentRequestDto   updateReq = new CommentRequestDto( post.getId() , "수정된 댓글" );
//			CommentResponseDto  updated   = commentService.updateComment(user2Dto.getId() , comment.getId() , updateReq );
//			assertThat(    updated.getContent()     ).isEqualTo(       "수정된 댓글"   ); 
//			
//			// 댓글삭제
//			commentService.deleteComment(user2Dto.getId() , comment.getId());
//			assertThat(  commentService.countComments(post.getId())      ).isEqualTo(0);
//		}

	// ---------------------------------------------------------------------
	// ■ PostLikeService 테스트
	// ---------------------------------------------------------------------
//	    @Test
//	    @DisplayName("■ PostLikeService - 좋아요 추가/중복 방지/취소")
//	    void testPostLikeService() { 
//	    		//좋아요 추가
//	        LikeRequestDto likeReq = new LikeRequestDto(post.getId());
//	        LikeResponseDto like = postLikeService.addLike(user2Dto.getId(), likeReq);
//	        assertThat(like.getCount()).isEqualTo(1);  
//	 
//	        //중복 좋아요
//	        LikeResponseDto duplicate = postLikeService.addLike(user2Dto.getId(), likeReq);
//	        assertThat(duplicate.getCount()).isEqualTo(1);
//	        
//	        //좋아요 취소
//	        LikeResponseDto removed = postLikeService.removeLike(user2Dto.getId(), post.getId());
//	        assertThat( removed.getCount() ).isEqualTo(0);
//	    }

	// ---------------------------------------------------------------------
	// FollowService 테스트
	// ---------------------------------------------------------------------
//	    @Test
//	    @DisplayName("■ FollowService - 팔로우/언팔로우/차단/차단해제")
//	    void testFollowService() {
//	    		//팔로우
//	        FollowRequestDto followReq = new FollowRequestDto(user2Dto.getId());
//	        FollowResponseDto follow = followService.follow(user1Dto.getId(), followReq);  //팔로워, 팔로위
//	        assertThat(follow.getFolloweeId()).isEqualTo(user2Dto.getId());
//	        	//자기자신 팔로우 → 예외
//	        FollowRequestDto selfFollow = new FollowRequestDto(user1Dto.getId());
//	        assertThrows(IllegalStateException.class, () -> followService.follow(user1Dto.getId(), selfFollow));
//	        //언팔로우
//	        Long unfollowedId = followService.unfollow(user1Dto.getId(), user2Dto.getId());
//	        assertThat(unfollowedId).isEqualTo(user2Dto.getId());
//	  
//	    }

	// ---------------------------------------------------------------------
	// RetweetService 테스트
	// ---------------------------------------------------------------------
//		@Test
//		@DisplayName("■ RetweetService - 리트윗 추가/중복/조회/취소/목록")
//		void testRetweetService() { 
//			//1. 작성게시글 준비
//		    RetweetRequestDto retweetReq = new RetweetRequestDto(post.getId()); 
//		    //													 어떤유저가			원본글
//		    RetweetResponseDto retweet = retweetService.addRetweet(user1Dto.getId(), retweetReq);
//		    assertThat(retweet.getOriginalPostId()).isEqualTo(post.getId()); //post
//		    assertThat(retweet.getUserId()).isEqualTo(user1Dto.getId());   //user1Dto
//		    assertThat(retweet.getRetweetCount()).isEqualTo(1);  // 리트윗수가 1개야
//		    // 중복리트윗 → 예외
//		    assertThrows(IllegalStateException.class,
//		        () -> retweetService.addRetweet(user1Dto.getId(), retweetReq));
//		    // 리트윗여부
//		    boolean hasRetweeted = retweetService.hasRetweeted(user1Dto.getId(), post.getId());
//		    assertThat(hasRetweeted).isTrue();
//		    // 리트윗수 확인
//		    long count = retweetService.countRetweets(post.getId());
//		    assertThat(count).isEqualTo(1);
//		    // 리트윗 취소
//		    RetweetResponseDto removed = retweetService.removeRetweet(user1Dto.getId(), post.getId());
//		    assertThat(removed.getRetweetCount()).isEqualTo(0);
//	 
//		}

}
