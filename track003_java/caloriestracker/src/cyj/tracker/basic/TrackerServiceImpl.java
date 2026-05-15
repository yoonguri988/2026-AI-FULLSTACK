package cyj.tracker.basic;

import cyj.tracker.auth.AuthService;
import cyj.tracker.auth.AuthServiceImpl;
import cyj.tracker.food.FoodService;
import cyj.tracker.food.FoodServiceImpl;
import cyj.tracker.user.UserService;
import cyj.tracker.user.UserServiceImpl;

public class TrackerServiceImpl implements TrackerService {
	private final AuthService auth;
    private final UserService user;
    private final FoodService food;
    
	public TrackerServiceImpl(TrackerRepository repo) {
        this.auth = new AuthServiceImpl(repo.getUserRepository());
        this.user = new UserServiceImpl(repo.getUserRepository());
        this.food = new FoodServiceImpl(repo.getFoodRepository());
    }

	@Override public AuthService getAuthService() { return auth; }
	@Override public UserService getUserService() { return user; }
	@Override public FoodService getFoodService() { return food; }
}
