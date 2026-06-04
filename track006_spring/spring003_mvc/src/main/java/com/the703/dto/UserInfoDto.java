package com.the703.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoDto {
	private int uno;
	private String nickname;
	private String bpass;
	private String email;
	private String moblie;
	private String udate;
	private String bip;
}
