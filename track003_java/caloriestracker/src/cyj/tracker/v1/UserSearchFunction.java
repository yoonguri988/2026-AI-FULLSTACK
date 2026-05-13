package cyj.tracker.v1;

public class UserSearchFunction implements Function {
	private User user;
	private final InputHandler handler = new InputHandler();
	private final UserService service;
	
	public UserSearchFunction(UserService service) {
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
			User res = service.searchUser(user);
			// 출력
			System.out.println("\n--------------------");
			System.out.println("⚜ 사용자 정보 조회");
			System.out.println("■ 이메일: " + res.getEmail());
			//System.out.println("👉 비밀번호: " + "*".repeat(user.getPassword().length()));
			System.out.println("■ 이름: " + res.getName());
			System.out.println("■ 나이: " + res.getAge());
			System.out.println("■ 키: " + res.getHeight());
			System.out.println("■ 몸무게: " + res.getWeight());
			System.out.println("--------------------\n");
		}
	}
	
}
