package cyj.tracker.user;

import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;

public class UserDeleteFunction implements TrackerFunction {
	private User user;
	private User curUser;

	private final UserService service;
	private final AuthService authService;

	private UserView view = new UserView();
	
	public UserDeleteFunction(TrackerService service) {
		super();
		this.service = service.getUserService();
		this.authService = service.getAuthService();
	}

	@Override
	public void input() {
		String email; String password;
		email = view.getEmail();
		
		curUser = authService.getCurrentUser();
		if(!service.isAdmin(curUser)) {
			password = view.getPassword();
			this.user = new User(email, password);
		} else {
			this.user = new User(email);
		}
	}

	@Override
	public void execute() {
		if(!service.isAdmin(curUser)) {
			if(service.isExists(user)) {
				deleteUser(user);
			}else { view.printWrongUser();}
		} else {
			if(service.isExistsByAdmin(user)) {
				user = service.searchUser(user);
				deleteUser(user);
			}
		}
	}
	
	private void deleteUser(User user) {
		String yn = view.getIsCheck();
		if(yn.toLowerCase().equals("y")) {
			User res = service.deleteUser(user);
			view.printDelUser(res);
		} else { view.printCancel(); }
	}

}
