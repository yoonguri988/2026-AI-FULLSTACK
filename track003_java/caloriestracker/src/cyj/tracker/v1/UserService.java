package cyj.tracker.v1;

public interface UserService {
	public boolean isExists(User user);
	
	public User registerUser(User user);
	public User searchUser(User user);
	public User updateUser(User user);
	public User deleteUser(User user);
}
