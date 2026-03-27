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
	
	public static void main(String[] args)  {
		UserRepository repo = new UserRepository();
		UserService service = new UserService(repo);

		Scanner scan = new Scanner(System.in);

		String userId;
		String password;
		int age;
		long account;
		
		int num;
		System.out.println("WELCOME! (주)CODE_NOAH BANK");
		while(true) {
			explain();
			System.out.print("입력>>> ");
			if(scan.hasNextInt()) {
				num = scan.nextInt();								
				if(num == 1) {
					System.out.print("아이디 입력 : ");
					userId = scan.next();
					System.out.print("비밀번호 : ");
					password = scan.next();
					System.out.print("나이 : ");
					age = scan.nextInt();
					System.out.print("잔액 : ");
					account = scan.nextInt();
					
					service.addUser(userId, new User(userId, password, age, account));
					
				}else if(num == 2) {
					System.out.print("id : ");
					userId = scan.next();
					System.out.print("pass : ");
					password = scan.next();
					
					if(service.isExists(userId, password)) {
						service.showUserInfo(userId);
					}
					
				}else if(num == 3) {
					System.out.print("id : ");
					userId = scan.next();
					System.out.print("pass : ");
					password = scan.next();
					System.out.print("금액 : ");
					account = scan.nextLong();
					
					if(service.isExists(userId, password)) {
						service.depositAccountById(userId, account);
					}
				}else if(num == 4) {
					System.out.print("id : ");
					userId = scan.next();
					System.out.print("pass : ");
					password = scan.next();
					System.out.print("금액 : ");
					account = scan.nextLong();
					
					if(service.isExists(userId, password)) {
						if(service.isEmpty(userId, account)) {
							service.withdrawalAccountById(userId, account);
						}
					}
				}else if(num == 5) {
					System.out.print("id : ");
					userId = scan.next();
					System.out.print("pass : ");
					password = scan.next();
					
					if(service.isExists(userId, password)) {
						System.out.println("계좌를 삭제하시겠습니까? (Y/N)");
						String Yn = scan.next().toUpperCase();
						if(Yn.equals("Y")) {
							service.removeUserById(userId, password);
						}
					}
				}else if(num == 9) {
					System.out.println("종료기능 입니다.");
					break;
				}else {
					System.out.println("해당 번호의 기능은 존재하지 않습니다.");
				}
			} else {
				System.out.println("숫자만 입력해주세요.");
				scan.next();
			}
		}
	}
}
