package cyj.tracker.v1;

public class Main {
	public static void main(String[] args) {
		AppStatus status = new AppStatus();
		UserRepository repo = new UserRepository();
		InputHandler handler = new InputHandler();
		UserServiceImpl service = new UserServiceImpl(repo);
		Controller controller = new Controller(status, service, handler);
		
		controller.run();
	}
}
