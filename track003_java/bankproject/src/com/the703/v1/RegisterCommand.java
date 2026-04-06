package com.the703.v1;

public class RegisterCommand implements MenuCommand{
	private final UserService service;
    private final UserInputView view;
    
    public RegisterCommand(UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
    }

	@Override
	public void execute() {
		User user = view.userInput(1);
		service.regUser(user);
	}

}
