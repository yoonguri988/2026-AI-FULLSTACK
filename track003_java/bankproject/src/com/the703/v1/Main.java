package com.the703.v1;

public class Main {
	public static void main(String[] args) {
		AppStatus status = new AppStatus();
		UserRepository repo = new UserRepository();
		UserServiceImpl service = new UserServiceImpl(repo);
		UserInputView view = new UserInputView();
		UserController controller = new UserController(status, service, view);

		controller.run();
	}
}
