package cyj.tracker.auth;

public interface AuthService {
	public boolean isLoggedIn();
	
	public void login(String email, String password);
	public void logout();
}
