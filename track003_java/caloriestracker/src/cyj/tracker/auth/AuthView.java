package cyj.tracker.auth;

import cyj.tracker.basic.InputHandler;

public class AuthView {
	private final InputHandler handler = new InputHandler();
	
	public void init() {
		System.out.println("\n=== 🏃‍♀️ 영양소 / 칼로리 트래커 ‍‍🏃‍♂️ ===");
		System.out.println("[1] 회원 가입");
		System.out.println("[2] 로그인");
		System.out.println("[9] 종료");
	}
	public int getNum() {
		return handler.getInt("👉 번호를 선택하세요: ");
	}
	public String getEmail() {
		return handler.getString("👉 이메일을 입력하세요 > ");
	}
	public String getPassword() {
		return handler.getString("👉 비밀번호를 입력하세요 > ");
	}
	public void wrongLogin() {
		System.out.println("❌ 이메일 또는 비밀번호가 틀립니다.");
	}
	
	public void printShowNotExistFunction() {
		System.out.println("해당 번호의 기능은 존재하지 않습니다.");
	}
}
