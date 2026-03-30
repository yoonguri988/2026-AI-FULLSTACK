package com.the703.v1;

public class Main {
	public static void main(String[] args) {
		UserRepository repo = new UserRepository();
		UserServiceImpl service = new UserServiceImpl(repo);
		UserInputView view = new UserInputView();

		int num;
		
		System.out.println("WELCOME! (주)CODE_NOAH BANK");
		while (true) {
			num = view.init();
			User user = view.userInput(num);
			if (num == 1) {
				service.regUser(user);
			} else if (num == 2) {
				if (service.isExists(user)) {
					service.showUserInfo(user.getUserId());
				}

			} else if (num == 3) {
				if (service.isExists(user)) {
					service.depositAccountByUserId(user);
				}
			} else if (num == 4) {
				if (service.isExists(user)) {
					if (service.isEmpty(user)) {
						service.withdrawalAccountByUserId(user);
					}
				}
			} else if (num == 5) {
				if (service.isExists(user)) {
					String Yn = view.confirmPopup();
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
