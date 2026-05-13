package cyj.tracker.basic;

import cyj.tracker.auth.AuthController;
import cyj.tracker.auth.AuthService;
import cyj.tracker.auth.AuthServiceImpl;
import cyj.tracker.user.UserController;
import cyj.tracker.user.UserRepository;
import cyj.tracker.user.UserService;
import cyj.tracker.user.UserServiceImpl;

public class Main {
	public static void main(String[] args) {
		AppStatus status = new AppStatus();
		UserRepository repo = new UserRepository();
		InputHandler handler = new InputHandler();
		UserService userService = new UserServiceImpl(repo);
		AuthService authSerivce = new AuthServiceImpl();
		UserController userCtl = new UserController(status, authSerivce, userService, handler);
		AuthController authCtl = new AuthController(status, authSerivce, userService, handler);
		
		while(status.isRunning()) {
			if(!authSerivce.isLoggedIn()) {
				authCtl.run();
			} else {
				userCtl.run();
			}
		}
	}
}
