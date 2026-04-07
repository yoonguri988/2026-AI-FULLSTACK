package com.the703.v1;

public class ExitCommand implements MenuCommand {
	private final AppStatus status;
    private final UserInputView view;
	
	public ExitCommand(AppStatus status, UserInputView view) {
		this.view = view;
		this.status = status;
	}
	
	@Override
	public void execute() {
		view.showExitMessage();
		status.stop();
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

}
