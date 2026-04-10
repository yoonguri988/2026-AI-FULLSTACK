package com.the703.v1;

import java.util.Scanner;

public class BankProjectV1_0 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 변수
		int num = -1;
		String result = "";
		int id = -1, pass = -1, balance = -1;
		
		for(;;) {
			System.out.println("=== 🌟💰 WELCOME TO BANK SYSTEM 💰🌟 ===\n\n"
		                       +"[1] ➕ 계좌 추가\n"
					           +"[2] 🔍 계좌 조회\n"
		                       +"[3] 💵 입금하기\n"
					           +"[4] 💸 출금하기\n"
		                       +"[5] 🗑️ 계좌 삭제\n"
					           +"[9] ❎ 종료\n\n"
		                       +"=======================================\n\n"
					           +"👉 번호를 선택하세요: ");
			num = sc.nextInt();
			
			//출력 - 삼항연산자
			result = num == 1? "계좌를 추가하는 기능입니다."
					:num == 2? "계좌에 얼마나 들었나 조회하는 기능입니다."
					:num == 3? "계좌에 돈을 입금하는 기능입니다."
					:num == 4? "계좌에 돈을 출금하는 기능입니다."
					:num == 5? "계좌를 삭제하는 기능입니다.ㅠ"
					:num == 9? "종료합니다.😥"
					:"해당 번호는 없는 기능입니다.";
			
			// 출력
			System.out.println(result);
			
			
			if(num == 1) {
				System.out.print("[1]ID   입력> ");
				id = sc.nextInt();
				System.out.print("[2]PASS 입력> ");
				pass = sc.nextInt();
				System.out.print("[3]금액  입력> ");
				balance = sc.nextInt();
			} else if (num == 2) {
				int tid = -1, tpass = -1;
				System.out.print("[1]ID   입력> ");
				tid = sc.nextInt();
				System.out.print("[2]PASS 입력> ");
				tpass = sc.nextInt();
				
				//처리 + 출력
				result = id == tid && pass == tpass? "[1]ID: %d\n[2]PASS: %d\n[3]금액: %d\n":"아이디와 비밀번호를 확인해주세요.";
				System.out.printf(result, id, pass, balance);
			}
			
			if(num == 9) break;
		}
	}
}

/*
Q1. 메뉴판나오게 만들고 사용자가 메뉴 선택시 (삼항연산자)
      1을 입력하면 추가기능입니다. 출력구문까지만
      2를 입력하면 조회기능입니다. 출력구문까지만
      3을 입력하면 입금기능입니다. 출력구문까지만
      4를 입력하면 출금기능입니다. 출력구문까지만
      5를 입력하면 삭제기능입니다. 출력구문까지만
      9를 입력하면 종료합니다.    출력구문까지만

Q2. 무한반복으로 메뉴나오게, 9 나오면 종료
   ■ 힌트
   for(;;) { 
      System.out.println("숫자1을 입력하세요.");
      int a = scanner.nextInt();
      if(a == 1) { break;}
   } 
 */
