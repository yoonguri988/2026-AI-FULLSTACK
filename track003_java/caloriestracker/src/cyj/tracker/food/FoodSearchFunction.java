package cyj.tracker.food;

import java.util.List;

import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.InputHandler;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;
import cyj.tracker.user.User;
import cyj.tracker.user.UserService;
import cyj.tracker.user.UserView;

public class FoodSearchFunction implements TrackerFunction {
	private List<Food> foodList;
	private User user;
	private final FoodService foodService;
	private final AuthService authService;
	private final UserService userService;
	private FoodView view = new FoodView();
	private UserView userView = new UserView();
	
	public FoodSearchFunction(TrackerService service) {
		super();
		this.foodService = service.getFoodService();
		this.authService = service.getAuthService();
		this.userService = service.getUserService();
	}

	@Override public void input() { 
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
	}

	@Override
	public void execute() {
		if(user != null) {
			foodList = foodService.searchFood(user);
			view.printFoodList(foodList, user);
		} else { userView.printNotExistsUser(); }
		
	}

}
