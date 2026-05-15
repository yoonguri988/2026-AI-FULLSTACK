package cyj.tracker.basic;

import cyj.tracker.auth.AuthService;
import cyj.tracker.food.FoodService;
import cyj.tracker.user.UserService;

public interface TrackerService {
	AuthService getAuthService();
    UserService getUserService();
    FoodService getFoodService();
}
