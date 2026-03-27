package com.the703.v1;

public interface UserServiceImpl {
	public boolean isExists(String userId, String password);
	public boolean isEmpty(String userId, long account);
	
	public void showUserInfo(String userId);
	public User addUser(String userId, User user);
	public User removeUserById(String userId, String password);
	public User depositAccountById(String userId, long account);
	public User withdrawalAccountById(String userId, long account);
}
