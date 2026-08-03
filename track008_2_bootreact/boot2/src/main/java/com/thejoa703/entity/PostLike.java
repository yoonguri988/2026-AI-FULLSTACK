package com.thejoa703.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "POST_LIKES", uniqueConstraints = @UniqueConstraint(columnNames = { "APP_USER_ID", "POST_ID" }))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PostLike {
	@Id // 기본키 primary 키 붙히기
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_likes_seq")
	@SequenceGenerator(name = "post_likes_seq", sequenceName = "POST_LIKES_SEQ", allocationSize = 1)
	private Long id;

	@Column(name = "CREATED_AT", nullable = false)
	private LocalDateTime createdAt; // 좋아요 누른 시점

	@ManyToOne
	@JoinColumn(name="APP_USER_ID", nullable = false)
	private AppUser user;

	@ManyToOne
	@JoinColumn(name="POST_ID", nullable = false)
	private Post post;

	@PrePersist
	void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	public PostLike(AppUser user, Post post) {
		super();
		this.user = user;
		this.post = post;
	}
	
}
