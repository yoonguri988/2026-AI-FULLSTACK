package cyj.tracker.user;

import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;

public class UserSearchFunction implements TrackerFunction {
	private User user;
	private User curUser;
	private final UserService service;
	private final AuthService authService;
	private UserView view = new UserView();
	
	public UserSearchFunction(TrackerService service) {
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
				User res = service.searchUser(user);
				view.printSearchContent(res);
			}else { view.printWrongUser(); }
		} else {
			if(service.isExistsByAdmin(user)) {
				User res = service.searchUser(user);
				view.printSearchContent(res);
			}
		}
	}
	
}
