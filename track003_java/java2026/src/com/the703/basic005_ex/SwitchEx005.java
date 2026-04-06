package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx005 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = -1;
		
		System.out.print("숫자하나를 입력 > ");
		num = sc.nextInt();
		
		switch(num % 2) {
		case 0:
			System.out.println("여자");
			break;
		case 1:
			System.out.println("남자");
			break;
		}
	}
}
//숫자한개 입력받아
//홀수면 남자
//짝수면 여자