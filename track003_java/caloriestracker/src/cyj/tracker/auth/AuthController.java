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
	private final TrackerService service;
	private TrackerFunction trackFunc;
	private AuthView authView = new AuthView();
	private AppView appView = new AppView();

	public AuthController(AppStatus status, TrackerService service) {
		this.status = status;
		this.service = service;
	}

	@Override
	public void run() {
		int num;
		if (!service.getAuthService().isLoggedIn()) {
			authView.init();
			num = authView.getNum();

			if(num != 9) {
				if (num == 1) {
					trackFunc = new UserRegisterFunction(service);
				} else if (num == 2) {
					trackFunc = new AuthLoginFunction(service);
					if(service.getAuthService().isLoggedIn()) return;
				} else {
					trackFunc = null;
				}
				
				if(trackFunc != null) {
					trackFunc.input();
					trackFunc.execute();
				} else {
					authView.printShowNotExistFunction();
				}
			} else {
				status.stop();
				appView.stop();
			}
		}
	}
}
