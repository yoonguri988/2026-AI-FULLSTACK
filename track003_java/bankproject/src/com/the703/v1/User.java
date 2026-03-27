package com.the703.v1;

public class User {
	  String id;
	  String pw;
	  int age;
	  long account;
	  
	  public User(String id, String pw, int age, long account) {
		  this.id = id;
		  this.pw = pw;
		  this.age = age;
		  this.account = account;
	  }
	  
	  public boolean isExists(String id, String pw) {
		  boolean result = false;
		  result = this.id.equals(id) && this.pw.equals(pw);
		  return result;
	  }
	  
	  public boolean isEmpty(long account) {
		  return this.account >= account;
	  }
	  
	  public void selectOneById(String id) {
		  System.out.println("=== 계좌 조회");
		  System.out.println("ID: "+this.id);
		  System.out.println("PASS: "+this.pw);
		  System.out.println("나이: "+this.age);
		  System.out.println("잔액: "+this.account);
	  }
	  
	  public long updateAccountById(String id, long account) {
		  this.account += account;
		  return this.account;
	  }

	}