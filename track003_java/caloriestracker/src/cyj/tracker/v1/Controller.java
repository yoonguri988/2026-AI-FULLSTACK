package cyj.tracker.v1;

public class Controller {
	private final AppStatus status;
	private final UserService service;
	private final InputHandler handler;
	
	public Controller(AppStatus status, UserService service, InputHandler handler) {
		this.status = status;
		this.service = service;
		this.handler = handler;
	}

	public void run() {
		int num;
		
		while(status.isRunning()) {			
			// view.init();
			System.out.println("=== 🏃‍♀️ 영양소 / 칼로리 트래커 ‍‍🏃‍♂️ ===");
			System.out.println("[1] ➕ 회원 정보 등록");
			System.out.println("[2] 😉 회원 정보 조회");
			System.out.println("[3] ❓ 회원 정보 수정");
			System.out.println("[4] ❌ 회원 정보 삭제");
			System.out.println("[5] 🍔 음식 기록");
			System.out.println("[6] 🔍 분석 보기");
			System.out.println("[9] ❎ 종료");
			System.out.println("================================");
			num = handler.getInt("👉 번호를 선택하세요: ");
			
			if(num == 1) {// 회원 정보 등록
				Function urFunc = new UserRegisterFunction(service);
				urFunc.input();
				urFunc.execute();
			} else if(num == 2) {// 회원 정보 조회
				Function usFunc = new UserSearchFunction(service);
				usFunc.input();
				usFunc.execute();
			} else if(num == 3) { // 회원 정보 수정
				Function uuFunc = new UserUpdateFunction(service);
				uuFunc.input();
				uuFunc.execute();
			} else if(num == 4) { // 회원 정보 삭제
				Function udFunc = new UserDeleteFunction(service);
				udFunc.input();
				udFunc.execute();
			} else if(num == 9) {
				status.stop();
				System.out.println("종료합니다.");
			}
		}
	}
}
