package com.the703.v1;

public class SearchCommand implements MenuCommand{
	private final UserService service;
    private final UserInputView view;
    
    public SearchCommand(UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
    }

	@Override
	public void execute() {
		User user = view.userInput(2);
		service.showUserInfo(user);
	}

}
