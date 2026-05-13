package cyj.tracker.v1;

public class UserDeleteFunction implements Function {
	private User user;
	private final InputHandler handler = new InputHandler();
	private final UserService service;
	
	public UserDeleteFunction(UserService service) {
		super();
		this.service = service;
	}

	@Override
	public void input() {
		String email; String password;
		email = handler.getString("👉 이메일을 입력하세요 > ");
		password = handler.getString("👉 비밀번호를 입력하세요 > ");
		
		this.user = new User(email, password);
	}

	@Override
	public void execute() {
		if(service.isExists(user)) {
			User res = service.deleteUser(user);
		}
	}

}
