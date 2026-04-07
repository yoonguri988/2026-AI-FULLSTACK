package com.the703.v1;

public class RegisterCommand implements MenuCommand{
	private User user;
	private final UserService service;
    private final UserInputView view;
    
    public RegisterCommand(UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void input() {
    	String userId;
		String password;
		int age = 0;
		long account = 0;
	
		userId = view.getUserId();
		password = view.getPassword();
		age = view.getAge();
		account = view.getAccount();
    	
		this.user = new User(userId, password, age, account);
    }
    
	@Override
	public void execute() {
		service.regUser(user);
	}


}
