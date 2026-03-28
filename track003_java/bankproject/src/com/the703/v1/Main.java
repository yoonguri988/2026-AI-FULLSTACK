package com.the703.v1;

import java.util.Scanner;

public class Main {
	private static void explain() {
		System.out.println("====== BANK ======");
		System.out.println("* 1. 추가");
		System.out.println("* 2. 조회");
		System.out.println("* 3. 입금");
		System.out.println("* 4. 출금");
		System.out.println("* 5. 삭제");
		System.out.println("* 9. 종료");
		System.out.println("==================");
	}

	public static void main(String[] args) {
		UserRepository repo = new UserRepository();
		UserServiceImpl service = new UserServiceImpl(repo);
		InputHandler handler = new InputHandler();

		String userId;
		String password;
		int age;
		long account;

		int num;
		System.out.println("WELCOME! (주)CODE_NOAH BANK");
		while (true) {
			explain();
			num = handler.getInt("입력>>> ");
			if (num == 1) {
				userId = handler.getString("아이디 입력 : ");
				password = handler.getString("비밀번호 : ");
				age = handler.getInt("나이 : ");
				account = handler.getLong("잔액 : ");

				User user = new User(userId, password, age, account);
				service.regUser(user);

			} else if (num == 2) {
				userId = handler.getString("id : ");
				password = handler.getString("pass : ");

				User user = new User(userId, password);
				if (service.isExists(user)) {
					service.showUserInfo(userId);
				}

			} else if (num == 3) {
				userId = handler.getString("id : ");
				password = handler.getString("pass : ");
				account = handler.getLong("금액 : ");

				User user = new User(userId, password, account);
				if (service.isExists(user)) {
					service.depositAccountByUserId(user);
				}
			} else if (num == 4) {
				userId = handler.getString("id : ");
				password = handler.getString("pass : ");
				account = handler.getLong("금액 : ");

				User user = new User(userId, password, account);
				if (service.isExists(user)) {
					if (service.isEmpty(user)) {
						service.withdrawalAccountByUserId(user);
					}
				}
			} else if (num == 5) {
				userId = handler.getString("id : ");
				password = handler.getString("pass : ");

				User user = new User(userId, password);
				if (service.isExists(user)) {
					String Yn = handler.getString("계좌를 삭제하시겠습니까? (Y/N) > ");
					if (Yn.toUpperCase().equals("Y")) {
						service.delUser(user);
					}
				}
			} else if (num == 9) {
				System.out.println("종료기능 입니다.");
				break;
			} else {
				System.out.println("해당 번호의 기능은 존재하지 않습니다.");
			}
		}
	}
}
