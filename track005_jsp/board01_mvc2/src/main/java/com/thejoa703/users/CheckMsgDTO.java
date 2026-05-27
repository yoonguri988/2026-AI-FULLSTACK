package com.thejoa703.users;

public class CheckMsgDTO {
	private boolean isDist;
	private String message;
	
	public CheckMsgDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckMsgDTO(boolean isDist, String message) {
		super();
		this.isDist = isDist;
		this.message = message;
	}

	public boolean isDist() {
		return isDist;
	}

	public void setDist(boolean isDist) {
		this.isDist = isDist;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
