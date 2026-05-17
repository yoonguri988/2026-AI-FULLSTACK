package cyj.tracker.basic;

public class Main {
	public static void main(String[] args) {
		AppStatus status = new AppStatus();
		
		TrackerRepository repo = new TrackerRepositoryImpl();

		TrackerService service = new TrackerServiceImpl(repo);
		
		TrackerControllerManger manager = new TrackerControllerMangerImpl(status, service);
		
		while (status.isRunning()) {
            TrackerController controller = manager.getController();
            controller.run();
        }
	}
}
