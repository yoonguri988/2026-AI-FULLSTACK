package cyj.tracker.user;

import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;
import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.InputHandler;

public class UserUpdateFunction implements TrackerFunction {
	private User user;
	private User curUser;
	private final UserService service;
	private final AuthService authService;
	private UserView view = new UserView();
	
	public UserUpdateFunction(TrackerService service) {
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
				updateUserInfo(user);
			} else { view.printWrongUser(); }
		} else { 
			if(service.isExistsByAdmin(user)) {
				user = service.searchUser(user);
				updateUserInfo(user);
			}
		}
	}
	
	private void updateUserInfo(User user) {
		view.printUpdUserDesc();
		String name = view.getName();
		int age = view.getAge();
		double height = view.getHeight();
		double weight = view.getWeight();
		int activityLevel = -1;
		int cnt = 10;
		while(activityLevel < 0 || activityLevel > 3) {			
			activityLevel = view.getActivityLevel();
			cnt--;
			if(cnt == 0) {
				view.printTryOverflow();
				activityLevel = 0;
			}
		}
		
		user.setName(name);
		user.setAge(age);
		user.setHeight(height);
		user.setWeight(weight);
		user.setActivityLevel(activityLevel);
		user.setTargetCalories(user.calculateTargetCalories());
		
		User updUser = service.updateUser(user);
	}
}
