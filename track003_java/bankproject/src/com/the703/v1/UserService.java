package com.the703.v1;

public interface UserService {
	public boolean isExists(User user);
	public boolean isEmpty(User user);
	
	public User showUserInfo(User user);
	public int regUser(User user);
	public int delUser(User user);
	public User depositAccountByUserId(User user);
	public User withdrawalAccountByUserId(User user);
}
