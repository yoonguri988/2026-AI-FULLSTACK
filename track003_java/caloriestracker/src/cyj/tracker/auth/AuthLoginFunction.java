package cyj.tracker.auth;

import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;
import cyj.tracker.user.User;

public class AuthLoginFunction implements TrackerFunction {
	private final AuthService authService;
	private User user;
	private AuthView authView = new AuthView();
	
	public AuthLoginFunction(TrackerService service) {
		super();
		this.authService = service.getAuthService();
	}

	@Override
	public void input() {
		String email = authView.getEmail();
		String password = authView.getPassword();
		
		this.user = new User(email, password);
	}

	@Override
	public void execute() {
		authService.login(this.user.getEmail(), this.user.getPassword());
		if(!authService.isLoggedIn()) authView.wrongLogin();
	}

}
