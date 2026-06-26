package com.the703.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {
	private Integer authId;
	private String email;
	private String auth;
	private Integer appUserId;
}
/*
SQL> desc authorities;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 AUTH_ID                                   NOT NULL NUMBER(5)
 EMAIL                                              VARCHAR2(255)
 AUTH                                      NOT NULL VARCHAR2(255)
 APP_USER_ID                                        NUMBER(5)
 */