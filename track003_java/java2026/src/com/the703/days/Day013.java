package com.the703.days;

import java.util.Scanner;

public class Day013 { 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("요일 입력(1~7) > ");
		int day = scanner.nextInt();
		
		//1. if 버전  [~20분 : 11시 50분]
		//		정수를 하나 입력받아 다음 조건에 따라 요일을 출력하는 프로그램을 작성하시오.
		//		1 → "월요일" /  	2 → "화요일" / 3 → "수요일" / 4 → "목요일" / 5 → "금요일" / 그 외 → "주말"
		if(day == 1) System.out.println("월요일");
		else if(day == 2) System.out.println("화요일");
		else if(day == 3) System.out.println("수요일");
		else if(day == 4) System.out.println("목요일");
		else if(day == 5) System.out.println("금요일");
		else System.out.println("주말");
		
		//2. switch 버전 - 위의 문제를 switch 문으로 작성하시오.
		switch(day) {
			case 1: System.out.println("월요일"); break;
			case 2: System.out.println("화요일"); break;
			case 3: System.out.println("수요일"); break;
			case 4: System.out.println("목요일"); break;
			case 5: System.out.println("금요일"); break;
			default: System.out.println("주말");
		}
 
		//3. for, while, do while 버전
		//		1 2 3 4 5 6 7 8 9 10
		for(int i = 1; i <= 10; i++) {
			System.out.print(i+(i!=10?" ":"\n"));
		}
		
		int i = 1;
		while(i <= 10) {
			System.out.print(i+(i!=10?" ":"\n"));
			i++;
		}
		
		i = 1;
		do {
			System.out.print(i+(i!=10?" ":"\n"));
			i++;
		} while(i <= 10);
		
		//4. 이중 for 버전 - 다음과 같은 모양을 출력하는 프로그램을 작성하시오.
		//		 
		//		1234
		//		1234
		//		1234
		//		1234

		for(int j = 1; j <= 4; j++ ) {
			for(int k = 1; k <= 4; k++) {
				System.out.print(k);
			}
			System.out.println();
		}
	}
}
