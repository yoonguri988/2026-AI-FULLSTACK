package com.thejoa703.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser {
	
	@Id // jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appuser_seq")
	@SequenceGenerator(name="appuser_seq", sequenceName="APPUSER_SEQ", allocationSize=1)
	@Column(name="APP_USER_ID")
	private Long id;
	
	@Column(length = 120, nullable = false)
	private String email;
	@Column(length = 200, nullable = false)
	private String password;
	
	@Builder.Default
	@Column(length = 50, nullable = false)
	private String role="ROLE_USER"; // 기본권한
	
	@Column(length = 150)
	private String provider =  "local"; 
	@Column(name="PROVIDER_ID", length = 150)
	private String providerId =  "local"; 
	
	@Column(length = 255)
	private String ufile;
	@Column(length = 50, nullable = false)
	private String nickname;
	@Column(length = 30)
	private String moblie;
	@Column(name="MBTI_TYPE_ID", length = 150)
	private Integer mbtiTypeId;
	
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

	public AppUser(String email, String password, String provider, String nickname, String role) {
		super();
		this.email = email;
		this.password = password;
		this.provider = provider;
		this.nickname = nickname;
		this.role = "ROLE_USER";
		this.mbtiTypeId = 0;
	}
	
	// 한 사람(기준★)이 여러글을 쓸 수 있다.
	// 1. mappedBy = "user" : POST 엔티티에 있는 user 필드와 연결 - 읽기만 가능 / 수정 X
	// 2. cascade = CascadeType.ALL : AppUser 변화(생성,수정,삭제)등과 연결된 Post에 반영
	// 3. orphanRemoval = true : 유저 탈퇴시 작성한 글들이 전부 삭제
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> posts = new ArrayList<>();
	
	
}
