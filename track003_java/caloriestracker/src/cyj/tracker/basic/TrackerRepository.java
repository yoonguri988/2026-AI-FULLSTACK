package cyj.tracker.basic;

import cyj.tracker.food.FoodRepository;
import cyj.tracker.user.UserRepository;

public interface TrackerRepository {
	UserRepository getUserRepository();
    FoodRepository getFoodRepository();
}
