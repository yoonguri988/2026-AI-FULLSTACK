package cyj.tracker.v1;

public class Main {
	public static void main(String[] args) {
		AppStatus status = new AppStatus();
		InputHandler handler = new InputHandler();
		UserServiceImpl service = new UserServiceImpl();
		Controller controller = new Controller(status, service, handler);
		
		controller.run();
	}
}
