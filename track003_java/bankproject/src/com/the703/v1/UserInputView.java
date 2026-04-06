package com.the703.v1;

public class UserInputView {
	InputHandler handler = new InputHandler();
	
	public int init() {
		System.out.println("WELCOME! (주)CODE_NOAH BANK");
		System.out.println("====== BANK ======");
		System.out.println("* 1. 추가");
		System.out.println("* 2. 조회");
		System.out.println("* 3. 입금");
		System.out.println("* 4. 출금");
		System.out.println("* 5. 삭제");
		System.out.println("* 9. 종료");
		System.out.println("==================");
		int num = handler.getInt("입력>>> ");

		return num;
	}
	
	public User userInput(int num) {
		if(num == 9 || (num != 1 && num != 2 && num != 3 && num != 4 && num != 5)) return null;

		String userId;
		String password;
		int age = 0;
		long account = 0;
	
		userId = handler.getString("아이디 입력 : ");
		password = handler.getString("비밀번호 : ");
		if(num == 1) age = handler.getInt("나이 : ");
		
		if(num == 1 || num == 3 || num == 4) {
			account = handler.getLong("잔액 : ");
		}
		
		if(num == 1) {
			return new User(userId, password, age, account);
		} else if(num == 3) {
			return new User(userId, password, account);
		} else if(num == 4) {
			return new User(userId, password, -account);
		} else {
			return new User(userId, password); 
		}
	}
	
	public String confirmPopup() {
		String Yn = handler.getString("계좌를 삭제하시겠습니까? (Y/N) > ");
		return Yn;
	}
	
	public void showExitMessage() {
		System.out.println("종료기능 입니다.");
	}
	
	public void showNotExistFunction() {
		System.out.println("해당 번호의 기능은 존재하지 않습니다.");
	}
	
}
