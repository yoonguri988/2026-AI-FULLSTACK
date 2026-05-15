package cyj.tracker.auth;

import cyj.tracker.basic.AppStatus;
import cyj.tracker.basic.AppView;
import cyj.tracker.basic.TrackerController;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;
import cyj.tracker.user.UserRegisterFunction;
import cyj.tracker.user.UserService;

public class AuthController implements TrackerController {
	private final AppStatus status;
	private final AuthService authService;
	private final UserService userService;
	private AuthView authView = new AuthView();
	private AppView appView = new AppView();

	public AuthController(AppStatus status, TrackerService service) {
		this.status = status;
		this.authService = service.getAuthService();
		this.userService = service.getUserService();
	}

	@Override
	public void run() {
		int num;
		if (!authService.isLoggedIn()) {
			authView.init();
			num = authView.getNum();

			if (num == 1) {
				TrackerFunction urFunc = new UserRegisterFunction(userService);
				urFunc.input();
				urFunc.execute();
			} else if (num == 2) {
				TrackerFunction login = new AuthLoginFunction(authService);
				login.input();
				login.execute();
				if(authService.isLoggedIn()) return;
			} else if (num == 9) {
				status.stop();
				appView.stop();
			}
		}
	}
}
