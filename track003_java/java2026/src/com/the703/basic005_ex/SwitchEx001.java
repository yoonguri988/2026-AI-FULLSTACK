package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx001 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = -1;
		
		System.out.print("숫자하나를 입력 > ");
		num = sc.nextInt();
		
		switch(num) {
		case 3:
			System.out.println("봄");
			break;
		case 6:
			System.out.println("여름");
			break;
		case 9:
			System.out.println("가을");
			break;
		case 12:
			System.out.println("겨울");
			break;
		default:
			System.out.println("해당 되는 계절이 존재하지 않습니다.");
			break;
		}

	}
}

//숫자한개 입력받아
//3이면 봄
//6이면 여름
//9이면 가을
//12이면 겨울

