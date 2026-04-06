package com.the703.v1;

public class RemoveCommand implements MenuCommand{
	private final UserService service;
    private final UserInputView view;
    
    public RemoveCommand(UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
    }

	@Override
	public void execute() {
		User user = view.userInput(5);
		String Yn = view.confirmPopup();
		if (Yn.toUpperCase().equals("Y")) {
			service.delUser(user);
		}
	}

}
