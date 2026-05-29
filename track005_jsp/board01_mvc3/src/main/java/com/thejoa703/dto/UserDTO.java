package com.thejoa703.dto;

public class UserDTO {
	private int uno;
	private String nickname;
	private String bpass;
	private String email;
	private String mobile;
	private String udate;
	private String bip;
	
	public UserDTO() { super(); }
	
	//insert
	public UserDTO(String nickname, String bpass, String email, String mobile, String bip) {
		super();
		this.nickname = nickname;
		this.bpass = bpass;
		this.email = email;
		this.mobile = mobile;
		this.bip = bip;
	}
	
	//update
	public UserDTO(String nickname, String bpass, String email, String mobile) {
		super();
		this.nickname = nickname;
		this.bpass = bpass;
		this.email = email;
		this.mobile = mobile;
	}
	
	//select - login
	public UserDTO(String bpass, String email) {
		super();
		this.bpass = bpass;
		this.email = email;
	}
	
	//select - findUserByNickAndMoblie
	public UserDTO(String nickname, String mobile, String bip) {
		super();
		this.nickname = nickname;
		this.mobile = mobile;
		this.bip = bip;
	}

	public UserDTO(int uno, String nickname, String email, String mobile, String udate, String bip) {
		super();
		this.uno = uno;
		this.nickname = nickname;
		this.email = email;
		this.mobile = mobile;
		this.udate = udate;
		this.bip = bip;
	}

	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBpass() {
		return bpass;
	}
	public void setBpass(String bpass) {
		this.bpass = bpass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	public String getBip() {
		return bip;
	}
	public void setBip(String bip) {
		this.bip = bip;
	}
	
	@Override
	public String toString() {
		return "UserDTO [uno=" + uno + ", nickname=" + nickname + ", bpass=" + bpass + ", email=" + email + ", mobile="
				+ mobile + ", udate=" + udate + ", bip=" + bip + "]";
	}
}
