package com.thejoa703.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "FOLLOWS", 
	uniqueConstraints = @UniqueConstraint(
	columnNames = { "FOLLOWR_ID", "FOLLOWW_ID" })
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Follow {
	@Id // 기본키 primary 키 붙히기
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follow_seq")
	@SequenceGenerator(name = "follow_seq", sequenceName = "FOLLOW_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name="CREATED_AT", nullable=false)
	private LocalDateTime createdAt;
	
	// 팔로워: 나를 구독하는 사람들
	@ManyToOne(fetch = FetchType.LAZY) //1. 연관된 엔티티(AppUser) 당장 가져오는거 아니고
	@JoinColumn(name="FOLLOWR_ID", nullable = false)	
	private AppUser follower;
	
	// 팔로잉: 내가 한 구독
	@ManyToOne(fetch = FetchType.LAZY) //2. 실제 객체 사용하는 시점에서 쿼리실행, 불필요한 join 줄이기
	@JoinColumn(name="FOLLOWW_ID", nullable = false)	
	private AppUser followee;

	@PrePersist
	void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	public Follow(AppUser follower, AppUser followee) {
		super();
		this.follower = follower;
		this.followee = followee;
	}
	
	
}
