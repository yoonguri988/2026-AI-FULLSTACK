package cyj.tracker.user;

import cyj.tracker.basic.InputHandler;

public class UserView {
	private final InputHandler handler = new InputHandler();

	public void initAdmin() {
		System.out.println("\n=== 🏃‍♀️ 나의 기록 ‍‍🏃‍♂️ ===");
		System.out.println("[1] ➕ 회원 정보 등록");
		System.out.println("[2] 😉 회원 정보 조회");
		System.out.println("[3] ❓ 회원 정보 수정");
		System.out.println("[4] ❌ 회원 정보 삭제");
		System.out.println("[5] 🍔 사용자 음식 기록하기");
		System.out.println("[6] 🍕 사용자 음식 목록 조회하기");
		System.out.println("[7] ❌ 사용자 음식 삭제");
		System.out.println("[8] 🔍 사용자 오늘 분석 보기");
		System.out.println("[9] ❎ 로그아웃");
		System.out.println("================================");
	}
	public void init() {
		System.out.println("\n=== 🏃‍♀️ 나의 기록 ‍‍🏃‍♂️ ===");
		System.out.println("[1] 😉 회원 정보 조회");
		System.out.println("[5] 🍔 음식 기록하기");
		System.out.println("[6] 🍕 음식 목록 조회하기");
		System.out.println("[7] ❌ 음식 삭제");
		System.out.println("[8] 🔍 오늘 분석 보기");
		System.out.println("[9] ❎ 로그아웃");
		System.out.println("================================");
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
	
	public String getName() {
		return handler.getString("👉 이름을 입력하세요 > ");
	}
	
	public int getAge() {
		return handler.getInt("👉 나이를 입력하세요 > ");
	}
	
	public double getHeight() {
		return handler.getDouble("👉 키를 입력하세요 > ");
	}
	
	public double getWeight() {
		return handler.getDouble("👉 몸무게를 입력하세요 > ");
	}
	
	public int getActivityLevel() {
		return handler.getInt("👉 본인의 활동 정도를 입력하세요 0(거의안함) ~ 3(적극적) > ");
	}
	
	public void printTryOverflow() {
		System.out.println("지나친 시도로 인해 활동정도를 기본으로 지정합니다.");
	}
	
	public void printLogout() {
		System.out.println("로그아웃 합니다.");
	}
	
	public void printSearchContent(User user) {
		System.out.println("\n--------------------");
		System.out.println("⚜ 사용자 정보 조회");
		System.out.println("■ 이메일: " + user.getEmail());
		System.out.println("■ 이름: " + user.getName());
		System.out.println("■ 나이: " + user.getAge());
		System.out.println("■ 키: " + user.getHeight());
		System.out.println("■ 몸무게: " + user.getWeight());
		
		String str = "";
		
		switch(user.getActivityLevel()) {
			case 0: str = "거의 운동 안 함"; break; 
			case 1: str = "가벼운 운동 (주 1-3회)"; break; 
			case 2: str = "보통 (주 3-5회)"; break; 
			case 3: str = "적극적 운동 (주 6-7회)"; break; 
			default: str = "거의 운동 안 함"; break; 
		};
		System.out.println("■ 활동지표: " +str+"("+user.getActivityLevel()+")");
		System.out.println("■ 하루 권장 칼로리: "+ user.getTargetCalories()+"kcal");
		System.out.println("--------------------\n");
	}
	
	public void printWrongUser() {
		System.out.println("입력하신 이메일과 비밀번호는 존재하지 않습니다.");
	}
	
	public void printUpdUserDesc() {
		System.out.println("\n== 새롭게 바뀔 회원 정보를 입력해주세요 ==");
	}
	
	public void printNotChgAdmin() {
		System.out.println("관리자의 회원 정보는 수정할 수 없습니다.");
	}
	
	public String getIsCheck() {
		return handler.getString("정말로 회원 정보를 삭제하시겠습니까? (y/n) ");
	}
	
	public void printNotDelAdmin() {
		System.out.println("관리자는 삭제가 불가능 합니다.");
	}
	
	public void printDelUser(User user) {
		System.out.println(user.getName()+"님에 대한 회원정보가 삭제되었습니다.");
	}
	
	public void printCancel() {
		System.out.println("삭제가 취소되었습니다.");
	}
	
	public void printNotExistsUser() {
		System.out.println("입력한 사용자가 존재하지 않습니다.");
	}
	
	public void printShowNotExistFunction() {
		System.out.println("해당 번호의 기능은 존재하지 않습니다.");
	}
}
