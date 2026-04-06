package com.the703.v1;

public class WithdrawalCommand implements MenuCommand {
	private final UserService service;
    private final UserInputView view;
    
    public WithdrawalCommand(UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
    }

	@Override
	public void execute() {
		User user = view.userInput(4);
		service.withdrawalAccountByUserId(user);
	}


}
