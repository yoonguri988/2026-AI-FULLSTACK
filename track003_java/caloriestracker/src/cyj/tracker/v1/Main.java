package cyj.tracker.v1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		AppStatus status = new AppStatus();
		InputHandler handler = new InputHandler();
		Scanner sc = new Scanner(System.in);
		int num = 0;
		
		while(status.isRunning()) {			
			// view.init();
			System.out.println("=== 🏃‍♀️ 영양소 / 칼로리 트래커 ‍‍🏃‍♂️ ===");
			System.out.println("[1] ➕ 회원 정보 등록");
			System.out.println("[2] 🔍 정보 조회");
			System.out.println("[3] ➕ 음식 기록");
			System.out.println("[4] 🔍 분석 보기");
			System.out.println("[9] ❎ 종료");
			System.out.println("==================");
			num = handler.getInt("👉 번호를 선택하세요: ");
			
			if(num == 1) {// 회원 정보 등록
				UserRegisterFunction urFunc = new UserRegisterFunction();
				urFunc.input();
			}else if(num == 9) {
				status.stop();
				System.out.println("종료합니다.");
			}
		}
		
	}
}
