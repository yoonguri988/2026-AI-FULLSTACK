package com.the703.v1;

import java.util.HashMap;
import java.util.Map;

public class UserController {
	private final AppStatus status;
	private final UserService service;
    private final UserInputView view;
    
	private Map<Integer, MenuCommand> commands = new HashMap<>();

    public UserController(AppStatus status, UserService service, UserInputView view) {
        this.service = service;
        this.view = view;
        this.status = status;
        
        commands.put(1, new RegisterCommand(service, view));
        commands.put(2, new SearchCommand(service, view));
        commands.put(3, new DepositCommand(service, view));
        commands.put(4, new WithdrawalCommand(service, view));
        commands.put(5, new RemoveCommand(service, view));
        commands.put(9, new ExitCommand(status, view));        
    }

	public void run() {
		int num;
		while (status.isRunning()) {
			num = view.init();
			
			MenuCommand command = commands.get(num);
			if(command != null) {
				command.execute();
			} else {
				view.showNotExistFunction();
			}
		}
	}
}
