package com.the703.v1;

public class Main {
	public static void main(String[] args) {
		UserRepository repo = new UserRepository();
		UserServiceImpl service = new UserServiceImpl(repo);
		UserInputView view = new UserInputView();
		UserController controller = new UserController(service, view);

		controller.run();
	}
}
