package com.the703.v1;

public class DepositCommand implements MenuCommand {
	private final UserService service;
    private final UserInputView view;
    
    public DepositCommand(UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
    }

	@Override
	public void execute() {
		User user = view.userInput(3);
		service.depositAccountByUserId(user);
	}


}
