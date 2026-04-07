package com.the703.v1;

public class RemoveCommand implements MenuCommand{
	private User user;
	private final UserService service;
    private final UserInputView view;
    
    public RemoveCommand(UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void input() {
    	String userId;
		String password;
	
		userId = view.getUserId();
		password = view.getPassword();
    	
		this.user = new User(userId, password);
    }

    @Override
	public void execute() {
		if(service.isExists(user)) {
			String Yn = view.confirmPopup();
			if (Yn.toUpperCase().equals("Y")) {				
				service.delUser(user);
				view.successDeleteUser();
			}
		}else {
			view.reconfirmInput();
		}
	}


}
