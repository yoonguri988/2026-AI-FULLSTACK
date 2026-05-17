package cyj.tracker.user;

import java.util.List;

import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;
import cyj.tracker.food.Food;
import cyj.tracker.food.FoodService;

public class TargetAnalysisFunction implements TrackerFunction {
	private User user;
	private List<Food> foodList;
	private final FoodService foodService;
	private final AuthService authService;
	private final UserService userService;
	private UserView userView = new UserView();
	
	public TargetAnalysisFunction(TrackerService service) {
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
		foodList = foodService.searchFood(user);
	}

	@Override
	public void execute() {
		double totalCal = 0, totalCarbs = 0, totalProtein = 0, totalFat = 0;

	    for (Food f : foodList) {
	        totalCal += f.getCalories();
	        totalCarbs += f.getCarbs();
	        totalProtein += f.getProtein();
	        totalFat += f.getFat();
	    }
		
		System.out.println("\n=== 📊 오늘의 영양 분석 결과 ===");
		System.out.printf("목표 칼로리: %.1f kcal / 현재 섭취: %.1f kcal\n", user.getTargetCalories(), totalCal);
		
		System.out.println("\n[영양소 섭취 현황]");
	    System.out.printf("탄수화물: %.1fg | 단백질: %.1fg | 지방: %.1fg\n", totalCarbs, totalProtein, totalFat);

	    if (totalCal > user.getTargetCalories()) {
	        System.out.println("\n⚠️ 오늘 목표치를 초과했습니다. 가벼운 산책은 어떨까요?");
	    } else {
	        System.out.println("\n✅ 적절한 식단을 유지하고 계시네요!");
	    }
	}

}
