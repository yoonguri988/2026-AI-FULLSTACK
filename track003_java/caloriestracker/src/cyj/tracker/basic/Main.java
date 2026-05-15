package cyj.tracker.basic;

public class Main {
	public static void main(String[] args) {
		AppStatus status = new AppStatus();
		
		TrackerRepository repo = new TrackerRepositoryImpl();
//		UserRepository repo = new UserRepository();
//		FoodRepository foodRepo = new FoodRepository();

		TrackerService service = new TrackerServiceImpl(repo);
//		UserService userService = new UserServiceImpl(repo);
//		AuthService authSerivce = new AuthServiceImpl();
//		FoodService foodService = new FoodServiceImpl(foodRepo);
		
		TrackerControllerManger manager = new TrackerControllerMangerImpl(status, service);
//		UserController userCtl = new UserController(authSerivce, userService, foodService, handler);
//		AuthController authCtl = new AuthController(status, authSerivce, userService, handler);
		
		while (status.isRunning()) {
            TrackerController controller = manager.getController();
            controller.run();
        }
	}
}
