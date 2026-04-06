package com.the703.v1;

import java.util.HashMap;
import java.util.Map;

public class UserController {
	private final UserService service;
    private final UserInputView view;
    
	private Map<Integer, MenuCommand> commands = new HashMap<>();

    public UserController(UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
        
        commands.put(1, new RegisterCommand(service, view));
        commands.put(2, new SearchCommand(service, view));
        commands.put(3, new DepositCommand(service, view));
        commands.put(4, new WithdrawalCommand(service, view));
        commands.put(5, new RemoveCommand(service, view));
    }

	public void run() {
		int num;
		while (true) {
			num = view.init();
			
			MenuCommand command = commands.get(num);
			if(command != null) {
				command.execute();
			} else if(num == 9) {
				view.showExitMessage();
				break;
			}else {
				view.showNotExistFunction();
			}
		}
	}
}
