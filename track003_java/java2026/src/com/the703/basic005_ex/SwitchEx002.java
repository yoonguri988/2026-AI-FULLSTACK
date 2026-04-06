package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx002 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = -1;
		
		System.out.print("숫자하나를 입력 > ");
		num = sc.nextInt();
		
		switch(num/3) {
		case 1 :
			System.out.println("봄");
			break;
		case 2:
			System.out.println("여름");
			break;
		case 3:
			System.out.println("가을");
			break;
		case 0:
		case 4:
			System.out.println("겨울");
			break;
		default:
			System.out.println("해당 되는 계절이 존재하지 않습니다.");
			break;
		}
	}
}


//switch 이용
//숫자한개 입력받아
//3,4,5이면 봄/3
//6,7,8이면 여름/3
//9,10,11이면 가을/3
//12,1,2이면 겨울/3
