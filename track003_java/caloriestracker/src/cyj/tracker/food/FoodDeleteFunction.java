package cyj.tracker.food;

import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.InputHandler;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;
import cyj.tracker.user.User;
import cyj.tracker.user.UserService;
import cyj.tracker.user.UserView;

public class FoodDeleteFunction implements TrackerFunction {
	private Food food;
	private User user;

	private final FoodService foodService;
	private final AuthService authService;
	private final UserService userService;

	private FoodView view = new FoodView();
	private UserView userView = new UserView();

	public FoodDeleteFunction(TrackerService service) {
		super();
		this.foodService = service.getFoodService();
		this.authService = service.getAuthService();
		this.userService = service.getUserService();
	}

	@Override
	public void input() {
		// 현재 사용자가 admin인 경우에는 어떤 사용자의 음식을 기록할지 지정
		user = authService.getCurrentUser();
		if(userService.isAdmin(user)) {
			User searchUser = null;
			int cnt = 10;
			while(searchUser == null) {
				String email = userView.getEmail();
				searchUser = userService.searchUser(new User(email));
				if(searchUser == null) userView.printNotExistsUser();
				cnt--;
				if(cnt == 0) { break; }
			}
			user = searchUser;
		}
		
		
		String name = view.getName();
		food = new Food(name);
	}

	@Override
	public void execute() {
		if(user != null) {
			if(foodService.isExists(food, user)) {
				String yn = view.getIsCheck();
				if(yn.toLowerCase().equals("y")) {
					foodService.deleteFood(food);
					view.printDelSuccess();
				} else { view.printDelFail(); }
			} else { view.printWrongFood(); }
		}else { userView.printNotExistsUser(); }
	}

}
