package com.the703.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)  {
		Scanner scan = new Scanner(System.in);
		String id;
		String pw;
		int age;
		long account;
		
		List<User> list = new ArrayList<>();
		int num;
		
		System.out.println("WELCOME! (주)CODE_NOAH BANK");
		while(true) {
			explain();
			System.out.print("입력>>> ");
			if(scan.hasNextInt()) {
				num = scan.nextInt();								
				if(num == 1) {
					System.out.print("아이디 입력 : ");
					id = scan.next();
					System.out.print("비밀번호 : ");
					pw = scan.next();
					System.out.print("나이 : ");
					age = scan.nextInt();
					System.out.print("잔액 : ");
					account = scan.nextInt();
					User user = new User(id, pw, age, account);
					list.add(user);
				}else if(num == 2) {
					System.out.print("id : ");
					id = scan.next();
					System.out.print("pass : ");
					pw = scan.next();
					boolean isExists = false;
					for(int i = 0; i < list.size(); i++) {
						User user = list.get(i);
						isExists = user.isExists(id, pw);
						if(isExists) {
							// 계좌 조회
							user.selectOneById(id);
							break;
						}
					}
					if(!isExists) {					
						System.out.println("다시 확인해주세요.");
					}
				}else if(num == 3) {
					System.out.print("id : ");
					id = scan.next();
					System.out.print("pass : ");
					pw = scan.next();
					System.out.print("금액 : ");
					account = scan.nextInt();
					boolean isExists = false;
					for(int i = 0; i < list.size(); i++) {
						User user = list.get(i);
						isExists = user.isExists(id, pw);
						if(isExists) {
							// 계좌 입금
							account = user.updateAccountById(id, account);
							System.out.println("=== 입금 완료");
						    System.out.println("잔액: "+account);
							break;
						}
					}
					if(!isExists) {					
						System.out.println("다시 확인해주세요.");
					}
				}else if(num == 4) {
					System.out.print("id : ");
					id = scan.next();
					System.out.print("pass : ");
					pw = scan.next();
					System.out.print("금액 : ");
					account = scan.nextInt();
					boolean isExists = false;
					for(int i = 0; i < list.size(); i++) {
						User user = list.get(i);
						isExists = user.isExists(id, pw);
						if(isExists) {
							// 계좌 출금
							if(user.isEmpty(account)) {
								account = user.updateAccountById(id, -account);
								System.out.println("=== 출금 완료");
								System.out.println("잔액: "+account);
								break;
							}else {
								System.out.println("계좌에 돈이 부족합니다.");
							}
						}
					}
					if(!isExists) {					
						System.out.println("다시 확인해주세요.");
					}
				}else if(num == 5) {
					System.out.print("id : ");
					id = scan.next();
					System.out.print("pass : ");
					pw = scan.next();
					boolean isExists = false;
					for(int i = 0; i < list.size(); i++) {
						User user = list.get(i);
						isExists = user.isExists(id, pw);
						if(isExists) {
							// 사용자 삭제
							System.out.println("계좌를 삭제하시겠습니까? (Y/N)");
							String Yn = scan.next().toUpperCase();
							if(Yn.equals("Y")) {
								list.remove(i);
								System.out.println("계좌가 삭제되었습니다.");
							}
							break;
						}
					}
					if(!isExists) {					
						System.out.println("다시 확인해주세요.");
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
}
