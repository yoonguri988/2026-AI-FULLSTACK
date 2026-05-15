package cyj.tracker.basic;

import cyj.tracker.food.FoodRepository;
import cyj.tracker.user.UserRepository;

public class TrackerRepositoryImpl implements TrackerRepository {
	private final UserRepository userRepo = new UserRepository();
    private final FoodRepository foodRepo = new FoodRepository();
    		
	@Override public UserRepository getUserRepository() { return userRepo; }
	@Override public FoodRepository getFoodRepository() { return foodRepo; }

}
