package cyj.tracker.food;

import cyj.tracker.user.User;

public interface FoodService {
	public void registerFood(Food food, User currentUser);
}
