package com.thejoa703.dto;

import java.time.LocalDateTime;

import com.thejoa703.entity.Post;

import lombok.Getter;
import lombok.Setter;

public class PostDto {
	
	// 글 요청 DTO
	@Getter @Setter
	public static class PostRequestDto{
		private Long userId;
		private String content;
	}
	
	// 글 응답 DTO
	@Getter
	public static class PostResponseDto{
		private Long id;
		private String content;
		private LocalDateTime createdAt;
		private String userNickname;
		
		public PostResponseDto(Post post) {
			super();
			this.id = post.getId();
			this.content = post.getContent();
			this.createdAt = post.getCreatedAt();
			if(post.getUser() != null) {
				this.userNickname = post.getUser().getNickname();
			}
		}
	}
}
