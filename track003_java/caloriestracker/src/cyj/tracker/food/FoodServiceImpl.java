package cyj.tracker.food;

import cyj.tracker.user.User;

public class FoodServiceImpl implements FoodService {
	private final FoodRepository foodRepo;

	public FoodServiceImpl(FoodRepository foodRepo) {
		this.foodRepo = foodRepo;
	}

	@Override
	public void registerFood(Food food, User user) {
		foodRepo.loadFoodData(user.getEmail());
		foodRepo.insertFood(food);
	}
	
	
	
}
