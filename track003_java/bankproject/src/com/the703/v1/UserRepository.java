package com.the703.v1;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
	private Map<String, User> store = new HashMap<>();

	public User insertUser(User user) {
		return store.put(user.getUserId(), user);
	}
	
	public User selectByUserId(String userId) {
		return store.get(userId);
	}

	public User deleteByUserId(String userId) {
		return store.remove(userId);
	}
	
	public User updateAccountByUserId(User user) {
		String userId = user.getUserId();
		User existUser = store.get(userId);
		existUser.setAccount(existUser.getAccount() + user.getAccount());
		return existUser;
	}

}
