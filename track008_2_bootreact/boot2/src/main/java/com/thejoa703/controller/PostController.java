package com.thejoa703.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thejoa703.dto.PostDto.PostRequestDto;
import com.thejoa703.dto.PostDto.PostResponseDto;
import com.thejoa703.entity.Post;
import com.thejoa703.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="Post Api", description = "게시글 관련 API")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class PostController {
	private final PostService postService;
	
	// 게시글 작성
	// POST      /api/posts      게시글 작성
	@Operation(summary="게시글작성", description = "특정 유저 ID와 내용을 받아 게시글을 작성합니다.")
	@PostMapping
	public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto){
		Post createdPost = postService.createPost(requestDto.getUserId(), requestDto.getContent());
		return ResponseEntity.ok(new PostResponseDto(createdPost));
	}
	
	// 전체 게시글 조회
	// GET      /api/posts      전체 게시글 조회
	// getAllPosts, getPostPaged
	@Operation(summary="전체 게시글 조회", description = "전체 게시글 조회")
	@GetMapping
	public ResponseEntity<List<PostResponseDto>> getPosts(){
		List<Post> posts = postService.getAllPosts();
		List<PostResponseDto> response = posts.stream()
	                                          .map(PostResponseDto::new)
	                                          .collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}
	
	// 게시글 단건 조회
	// GET      /api/posts/{id}      게시글 단건 조회
	@Operation(summary="게시글 단건 조회", description = "게시글 단건 조회")
	@GetMapping("{id}")
	public ResponseEntity<PostResponseDto> getPost(@PathVariable("id") Long id){
		PostResponseDto response = new PostResponseDto(postService.getPostById(id));
		return ResponseEntity.ok(response);
	}
	
	// 게시글 수정
	// PUT      /api/posts/{id}
	@Operation(summary="게시글 수정", description = "게시글 수정")
	@PutMapping("{id}")
	public ResponseEntity<PostResponseDto> updatePost(@PathVariable("id") Long id, @RequestBody PostRequestDto requestDto){
		PostResponseDto response = new PostResponseDto(postService.updatePost(id, requestDto.getContent()));
		return ResponseEntity.ok(response);
	}
	
	// 게시글 삭제
	// DELETE      /api/posts/{id}      게시글 삭제
	@Operation(summary="게시글 삭제", description = "게시글 삭제")
	@DeleteMapping("{id}")
	public ResponseEntity<PostResponseDto> deletePost(@PathVariable("id") Long id){
		postService.deletePost(id);
		return ResponseEntity.noContent().build(); // 204

	}

}
/*
2. Post API     - 게시글 관련 API
- GET      /api/posts/{id}      게시글 단건 조회
- PUT      /api/posts/{id}      게시글 수정
- DELETE      /api/posts/{id}      게시글 삭제
- GET      /api/posts      전체 게시글 조회
- POST      /api/posts      게시글 작성
 */