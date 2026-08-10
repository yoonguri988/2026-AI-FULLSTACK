package com.thejoa703.controller;

import java.net.Authenticator;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.dto.PostDto.PostRequestDto;
import com.thejoa703.dto.PostDto.PostResponseDto;
import com.thejoa703.entity.Post;
import com.thejoa703.security.JwtProperties;
import com.thejoa703.security.JwtProvider;
import com.thejoa703.security.TokenStore;
import com.thejoa703.service.AuthUserJwtService;
import com.thejoa703.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Post Api", description = "Post 관련 API")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
//@CrossOrigin(origins="*")
public class PostController {

	private final PostService postService;
	private final AuthUserJwtService authUserJwtService; //##
	

	@Operation(summary = "게시글 작성", description = "특정유저 ID와 내용을 받아 게시글을 작성합니다.")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<PostResponseDto> createPost(
			Authentication authentication,
//			@Parameter(description = "작성자 사용자 Id") @RequestParam("userId") Long userId,
			@ModelAttribute PostRequestDto dto,
			@Parameter(description = "업로드할 이미지 파일 리스트") @RequestPart(name = "files", required = false) List<MultipartFile> files) {
		Long userId = authUserJwtService.getCurrentUserId(authentication);
		return ResponseEntity.ok(postService.createPost(userId, dto, files)); // 200

	}

//   @Operation(summary="전체 게시글", description = "전체 게시글 조회")
//   @GetMapping
//   public ResponseEntity<List<PostResponseDto>> getAllPosts(){
//      List<Post> posts = postService.getAllPosts();
//      List<PostResponseDto> lists = posts.stream()
//                                 .map(PostResponseDto::new)
//                                 .collect(Collectors.toList());
//      return ResponseEntity.ok(lists);
//      
//   }
	@Operation(summary = "전체 게시글", description = "전체 게시글 조회")
	@GetMapping
	public ResponseEntity<List<PostResponseDto>> getPosts() {
		return ResponseEntity.ok(postService.getAllPosts());
	}

	@Operation(summary = "단건게시", description = "단건게시")
	@GetMapping("/{id}")
	public ResponseEntity<PostResponseDto> getPost(@PathVariable("id") Long id) {
		Post post = postService.getPostById(id);
		return ResponseEntity.ok(new PostResponseDto(post));
	}

//   @Operation(summary="게시글 수정", description = "게시글 수정시") // 수정 put (전체데이트 수정) , patch(데이터 일부 수정)
//   @PatchMapping("/{postId}") // Put ( 리소스의 전체 교체 ) / patch ( 부분 수정 )
//   public ResponseEntity<PostResponseDto> getUpdatePost(@PathVariable("id") Long id,@RequestBody PostRequestDto requestDto){
//      Post post = postService.updatePost(id,requestDto.getContent());
//      return ResponseEntity.ok(new PostResponseDto(post));
//   }

	@Operation(summary = "게시글 수정", description = "게시글 수정시") // 수정 put (전체데이트 수정) , patch(데이터 일부 수정)
	@PatchMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // Put ( 리소스의 전체 교체 ) / patch (
																						// 부분 수정 )
	public ResponseEntity<PostResponseDto> getUpdatePost(
			//@Parameter(description = "작성자 사용자 Id") @RequestParam("userId") Long userId,
			Authentication authentication,
			@Parameter(description = "수정할 게시글 Id") @PathVariable(name = "postId") Long postId,
			@ModelAttribute PostRequestDto dto, // 게시글내용 + 댓글
			@Parameter(description = "업로드할 이미지 파일 리스트") // swagger
			@RequestPart(name = "files", required = false) List<MultipartFile> files) {
		Long userId = authUserJwtService.getCurrentUserId(authentication);
		return ResponseEntity.ok(postService.updatePost(userId, postId, dto, files));
	}

	@Operation(summary = "게시글 삭제", description = "게시글 삭제시")
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deletePost(Authentication authentication, @PathVariable("id") Long id) {
		Long userId = authUserJwtService.getCurrentUserId(authentication);
		postService.deletePost(userId, id);
		return ResponseEntity.ok(id);
	}

}
