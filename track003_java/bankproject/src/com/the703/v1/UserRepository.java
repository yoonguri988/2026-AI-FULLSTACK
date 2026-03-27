package com.the703.v1;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
	private Map<String, User> store = new HashMap<>();

	public User insertUser(String userId, User user) {
		return store.put(userId, user);
	}
	
	public User selectOneById(String userId) {
		return store.get(userId);
	}
	
	public User updateAccountById(String userId, long account) {
		User user = store.get(userId);
		user.setAccount(user.getAccount() + account);
		return user;
	}

	public User deleteUserById(String userId, String password) {
		return store.remove(userId);
	}
}
