package com.the703.v1;

public class SearchCommand implements MenuCommand{
	private User user;
	private final UserService service;
    private final UserInputView view;
    
    public SearchCommand(UserService service, UserInputView view) {
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
			User existUser = service.showUserInfo(user);
			view.showUserInfo(existUser);
		}else {
			view.reconfirmInput();
		}
	}


}
