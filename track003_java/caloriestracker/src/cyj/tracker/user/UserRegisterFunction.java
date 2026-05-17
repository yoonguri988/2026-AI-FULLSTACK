package cyj.tracker.user;

import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;

public class UserRegisterFunction implements TrackerFunction{
	private User user;
	private final UserService service;
	private UserView view = new UserView();
	
	
	public UserRegisterFunction(TrackerService service) {
		super();
		this.service = service.getUserService();
	}

	@Override
	public void input() {
		String email; String password;
		String name; int age;
		double height; double weight; int activityLevel;
		double targetCalories = 0.0;
		int cnt = 10;
		
		email = view.getEmail();
		password = view.getPassword();
		name = view.getName();
		age = view.getAge();
		height = view.getHeight();
		weight = view.getWeight();
		activityLevel = -1;
		while(activityLevel < 0 || activityLevel > 3) {			
			activityLevel = view.getActivityLevel();
			cnt--;
			if(cnt == 0) {
				view.printTryOverflow();
				activityLevel = 0;
			}
		}
		
		this.user = new User(email, password, name, age, height, weight, activityLevel, targetCalories);
		this.user.setTargetCalories(this.user.calculateTargetCalories());
	}

	@Override 
	public void execute() {
		User res = service.registerUser(user);
	}

}
