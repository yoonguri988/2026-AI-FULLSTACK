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
	
    public String getUserId() { 
    	return handler.getString("아이디 입력 : "); 
    }
    
    public String getPassword() { 
    	return handler.getString("비밀번호 : "); 
    }
    
    public int getAge() { 
    	return handler.getInt("나이 : "); 
    }
    
    public long getAccount() { 
    	return handler.getLong("잔액 : "); 
    }
	
	public String confirmPopup() {
		String Yn = handler.getString("계좌를 삭제하시겠습니까? (Y/N) > ");
		return Yn;
	}
	
	public void reconfirmInput() {
		System.out.println("=== 아이디 및 비밀번호를 다시 확인해주세요.");
	}
	
	public void confirmAccount() {
		System.out.println("=== 계좌에 돈이 부족합니다.");
	}
	
	public void showUserInfo(User user) {
		System.out.println("=== 계좌 조회");
		System.out.println("ID: " + user.getUserId());
		System.out.println("PASS: " + user.getPassword());
		System.out.println("나이: " + user.getAge());
		System.out.println("잔액: " + user.getAccount());
	}
	
	public void successUpdateDepositAccount(User user) {
		System.out.println("=== 입금 완료");
		System.out.println("잔액: "+ user.getAccount());
	}
	
	public void successUpdateWithdrawalAccount(User user) {
		System.out.println("=== 출금 완료");
		System.out.println("잔액: "+ user.getAccount());
	}
	
	public void successDeleteUser() {
		System.out.println("=== 계좌가 삭제되었습니다.");
	}
	
	public void showExitMessage() {
		System.out.println("종료기능 입니다.");
	}
	
	public void showNotExistFunction() {
		System.out.println("해당 번호의 기능은 존재하지 않습니다.");
	}
	
}
