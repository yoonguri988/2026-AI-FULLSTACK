package cyj.tracker.food;

import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.InputHandler;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.user.UserService;

public class FoodRegisterFunction implements TrackerFunction {
	private Food food;
	private final FoodService service;
	private final AuthService authService;
	private FoodView view = new FoodView();
	
	public FoodRegisterFunction(FoodService service, AuthService authService) {
		super();
		this.service = service;
		this.authService = authService;
	}

	@Override
	public void input() {
		String name;
		double calories;
		double carbs; double protein; double fat;
		
		name = view.getName();
		calories = view.getCalories();
		carbs = view.getCarbs();
		protein = view.getProtein();
		fat = view.getFat();
		
		this.food = new Food(name, calories, carbs, protein, fat);
	}

	@Override
	public void execute() {
		service.registerFood(food, authService.getCurrentUser());

	}

}
