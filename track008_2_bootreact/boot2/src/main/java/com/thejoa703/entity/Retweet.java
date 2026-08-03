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
@Table(name = "RETWEET", 
	uniqueConstraints = @UniqueConstraint(
			name="UK_RETWEET_USER_ORIG",
			columnNames = { "APP_USER_ID", "ORIGINSL_POST_ID" })
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Retweet {
	@Id // 기본키 primary 키 붙히기
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "retweet_seq")
	@SequenceGenerator(name = "retweet_seq", sequenceName = "RETWEET_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name="CREATED_AT", nullable=false)
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name="APP_USER_ID", nullable = false)	
	private AppUser user;
	
	@ManyToOne
	@JoinColumn(name="ORIGINSL_POST_ID", nullable = false)	
	private Post originalPost;
	
	@PrePersist
	void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	public Retweet(AppUser user, Post originalPost) {
		super();
		this.user = user;
		this.originalPost = originalPost;
	}
	
}
