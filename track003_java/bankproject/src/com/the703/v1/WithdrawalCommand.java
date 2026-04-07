package com.the703.v1;

public class WithdrawalCommand implements MenuCommand {
	private User user;
	private final UserService service;
    private final UserInputView view;
    
    public WithdrawalCommand(UserService service, UserInputView view) {
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
			if(!service.isEmpty(user)) {				
				User success = service.withdrawalAccountByUserId(user);
				if(success != null) {
					view.successUpdateWithdrawalAccount(success);
				}
			} else {
				view.confirmAccount();
			}
		}else {
			view.reconfirmInput();
		}
	}



}
