package cyj.tracker.food;

import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.InputHandler;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.user.User;

public class FoodDeleteFunction implements TrackerFunction {
	private Food food;
	private final InputHandler handler = new InputHandler();
	private final FoodService service;
	private final AuthService authService;
	private FoodView view = new FoodView();

	public FoodDeleteFunction(FoodService service, AuthService authService) {
		super();
		this.service = service;
		this.authService = authService;
	}

	@Override
	public void input() {
		String name = view.getName();
		food = new Food(name);
	}

	@Override
	public void execute() {
		User currentUser = authService.getCurrentUser();
		if(service.isExists(food, currentUser)) {
			String yn = view.getIsCheck();
			if(yn.toLowerCase().equals("y")) {
				service.deleteFood(food);
				view.printDelSuccess();
			} else { view.printDelFail(); }
		} else { view.printWrongFood(); }

	}

}
