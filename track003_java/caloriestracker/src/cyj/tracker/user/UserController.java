package cyj.tracker.user;

import cyj.tracker.auth.AuthLoginFunction;
import cyj.tracker.auth.AuthService;
import cyj.tracker.basic.AppStatus;
import cyj.tracker.basic.InputHandler;
import cyj.tracker.basic.TrackerFunction;

public class UserController {
	private final AppStatus status;
	private final UserService service;
	private final AuthService authService;
	private final InputHandler handler;

	public UserController(AppStatus status, AuthService authService, UserService service, InputHandler handler) {
		this.status = status;
		this.service = service;
		this.authService = authService;
		this.handler = handler;
	}

	public void run() {
		int num;

		System.out.println("=== 🏃‍♀️ 오늘 나의 기록 ‍‍🏃‍♂️ ===");
		System.out.println("[1] ➕ 회원 정보 등록");
		System.out.println("[2] 😉 회원 정보 조회");
		System.out.println("[3] ❓ 회원 정보 수정");
		System.out.println("[4] ❌ 회원 정보 삭제");
		System.out.println("[5] 🍔 음식 기록하기");
		System.out.println("[6] 🔍 오늘 분석 보기");
		System.out.println("[9] ❎ 로그아웃");
		System.out.println("================================");
		num = handler.getInt("👉 번호를 선택하세요: ");

		if (num == 1) {// 회원 정보 등록
			TrackerFunction urFunc = new UserRegisterFunction(service);
			urFunc.input();
			urFunc.execute();
		} else if (num == 2) {// 회원 정보 조회
			TrackerFunction usFunc = new UserSearchFunction(service);
			usFunc.input();
			usFunc.execute();
		} else if (num == 3) { // 회원 정보 수정
			TrackerFunction uuFunc = new UserUpdateFunction(service);
			uuFunc.input();
			uuFunc.execute();
		} else if (num == 4) { // 회원 정보 삭제
			TrackerFunction udFunc = new UserDeleteFunction(service);
			udFunc.input();
			udFunc.execute();
		} else if (num == 9) {
			authService.logout();
			System.out.println("로그아웃 합니다.");
		}
	}
}
