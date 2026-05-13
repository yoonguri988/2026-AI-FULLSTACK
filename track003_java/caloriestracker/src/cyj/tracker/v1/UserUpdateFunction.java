package cyj.tracker.v1;

public class UserUpdateFunction implements Function {
	private User user;
	private final InputHandler handler = new InputHandler();
	private final UserService service;
	
	public UserUpdateFunction(UserService service) {
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
			System.out.println("\n== 새롭게 바뀔 회원 정보를 입력해주세요 ==");
			String name = handler.getString("👉 이름을 입력하세요 > ");
			int age = handler.getInt("👉 나이를 입력하세요 > ");
			double height = handler.getDouble("👉 키를 입력하세요 > ");
			double weight = handler.getDouble("👉 몸무게를 입력하세요 > ");
			
			user.setName(name);
			user.setAge(age);
			user.setHeight(height);
			user.setWeight(weight);
			
			User updUser = service.updateUser(user);
			if(updUser != null) {
				System.out.println("⭕ 회원 정보 수정 성공");
			}else {
				System.out.println("❌ 회원 정보 수정 실패");
			}
		}
	}
}
