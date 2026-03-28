package com.the703.v1;

public interface UserService {
	public boolean isExists(User user);
	public boolean isEmpty(User user);
	
	public User showUserInfo(String userId);
	public int regUser(User user);
	public int delUser(User user);
	public int depositAccountByUserId(User user);
	public int withdrawalAccountByUserId(User user);
}
