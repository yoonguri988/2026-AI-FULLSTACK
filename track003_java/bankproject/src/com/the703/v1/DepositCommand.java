package com.the703.v1;

public class DepositCommand implements MenuCommand {
	private User user;
	private final UserService service;
    private final UserInputView view;
    
    public DepositCommand(UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
    }

	@Override
	public void input() {
		String userId;
		String password;
		long account = 0;
		
		userId = view.getUserId();
		password = view.getPassword();
		account = view.getAccount();
		
		this.user = new User(userId, password, account);
	}
    
	@Override
	public void execute() {
		if(service.isExists(user)) {			
			User success = service.depositAccountByUserId(user);
			if(success != null) {
				view.successUpdateDepositAccount(success);
			}
		}else {
			view.reconfirmInput();
		}
	}


}
