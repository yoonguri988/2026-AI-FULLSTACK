package com.thejoa703.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="IMAGES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_seq")
	@SequenceGenerator(name="images_seq", sequenceName="IMAGES_SEQ", allocationSize=1)
	@Column(name="ID")
	private Long id;
	
	@Column(length = 200, nullable = false)
	private String src;

	// 연관 관계  postId
	@ManyToOne // 한 글은 여러 이미지를 갖는다.
	// POST_ID 외래키(FK) -> POST 엔티티 PK(ID) 참조
	@JoinColumn(name="POST_ID", nullable = false) 
	private Post post;
	
}
