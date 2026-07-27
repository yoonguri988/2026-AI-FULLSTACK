package com.thejoa703.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="POSTS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
	@SequenceGenerator(name="post_seq", sequenceName = "POST_SEQ", allocationSize=1)
	@Column(name="ID")
	private Long id;
	
	@Lob // 대용량 처리 - clob(문자열) blob(이미지,파일,오디오,영상)
	@Column(nullable = false)
	private String content;
	
	@Column
	private Boolean deleted=false;
	
	@Column(name="CREATED_AT", nullable=false)
	private LocalDateTime createdAt;
	@Column(name="UPDATED_AT", nullable=false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	@PreUpdate
	void onUpdate() {
		this.updatedAt = LocalDateTime.now();		
	}
	
	// 한 사람이 여러글(기준★)을 쓸 수 있다. (POST)
	@ManyToOne
	@JoinColumn(name="APP_USER_ID", nullable = false)
	private AppUser user;

}
