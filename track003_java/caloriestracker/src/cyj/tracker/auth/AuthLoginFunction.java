package cyj.tracker.auth;

import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.user.User;

public class AuthLoginFunction implements TrackerFunction {
	private final AuthService service;
	private User user;
	private AuthView authView = new AuthView();
	
	public AuthLoginFunction(AuthService service) {
		super();
		this.service = service;
	}

	@Override
	public void input() {
		String email = authView.getEmail();
		String password = authView.getPassword();
		
		this.user = new User(email, password);
	}

	@Override
	public void execute() {
		service.login(this.user.getEmail(), this.user.getPassword());
		if(!service.isLoggedIn()) authView.wrongLogin();
	}

}
