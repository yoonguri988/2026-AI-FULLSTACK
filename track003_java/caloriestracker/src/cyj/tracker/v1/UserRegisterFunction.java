package cyj.tracker.v1;

public class UserRegisterFunction implements Function{
	private User user;
	private final InputHandler handler = new InputHandler();
	private final UserService service;
	
	public UserRegisterFunction(UserService service) {
		super();
		this.service = service;
	}

	@Override
	public void input() {
		String email; String password;
		String name; int age;
		double height; double weight;
		
		email = handler.getString("👉 이메일을 입력하세요 > ");
		password = handler.getString("👉 비밀번호를 입력하세요 > ");
		name = handler.getString("👉 이름을 입력하세요 > ");
		age = handler.getInt("👉 나이를 입력하세요 > ");
		height = handler.getDouble("👉 키를 입력하세요 > ");
		weight = handler.getDouble("👉 몸무게를 입력하세요 > ");
		
		this.user = new User(email, password, name, age, height, weight);
	}

	@Override
	public void execute() {
		service.regUser(user);
	}

}
