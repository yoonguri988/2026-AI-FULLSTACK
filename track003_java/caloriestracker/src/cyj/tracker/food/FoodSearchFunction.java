package cyj.tracker.food;

import java.util.List;

import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.InputHandler;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.user.User;

public class FoodSearchFunction implements TrackerFunction {
	private List<Food> foodList;
	private final FoodService service;
	private final AuthService authService;
	private FoodView view = new FoodView();
	
	public FoodSearchFunction(FoodService service, AuthService authService) {
		super();
		this.service = service;
		this.authService = authService;
	}

	@Override public void input() { }

	@Override
	public void execute() {
		User currentUser = authService.getCurrentUser();
		foodList = service.searchFood(currentUser);
		
		view.printFoodList(foodList, currentUser);
		
	}

}
