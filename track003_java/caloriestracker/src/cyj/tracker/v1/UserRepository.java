package cyj.tracker.v1;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
	private Map<String, User> store = new HashMap<>();

	public User insertUser(User user) {
		return store.put(user.getEmail(), user);
	}

	public User selectByUserId(String email) {
		return store.get(email);
	}

	public User updateUser(User user) {
		return store.put(user.getEmail(), user);
	}

	public User deleteByUserId(String email) {
		return store.remove(email);
	}
}
