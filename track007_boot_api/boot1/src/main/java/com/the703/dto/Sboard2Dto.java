package com.the703.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@Data - 필요없는 컨텐츠를 가져오기 때문에 JPA와 충돌
//        필요한 컨텐츠만 가져오는 것이 좋음
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sboard2Dto {
	private int id;
	private int appUserId;
	private String btitle;
	private String bcontent;
	private String bpass;
	private String bfile;
	private int bhit;
	private String bip;
	private String createdAt;
}
