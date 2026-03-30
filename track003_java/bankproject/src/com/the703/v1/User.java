package com.the703.v1;

public class User {
	String userId;
	String password;
	int age;
	long account;

	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	
	public User(String userId, String password, long account) {
		this.userId = userId;
		this.password = password;
		this.account = account;
	}
	
	public User(String userId, String password, int age, long account) {
		this.userId = userId;
		this.password = password;
		this.age = age;
		this.account = account;
	}
	
	// getter, setter
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public long getAccount() {
		return this.account;
	}
	
	public void setAccount(long account) {
		this.account = account;
	}
	
	public String toString() {
		return String.format("userId: %s, password: %s, age: %d, account: %d\n", userId, password, age, account );
	}
}