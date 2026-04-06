package com.the703.basic005;

public class Switch001 {
	public static void main(String[] args) {
		//1.if 1이면 1이다, 2이면 2이다, 3이면 3이다
		int num = 2;
		if(num == 1) System.out.println("1이다");
		else if(num == 2) System.out.println("2이다");
		else if(num == 3) System.out.println("3이다");
		
		//2.switch
		/*
		 * switch(대상) {
		 *   조건 1: 처리 break; //처리 후 나와
		 *   조건 2: 처리 break; //처리 후 나와
		 *   default: 조건이 아닐때
		 * }
		 * */
		switch(num) {
		case 1:
			System.out.println("1이다");
			break;
		case 2:
			System.out.println("2이다");
			break;
		case 3:
			System.out.println("3이다");
			break;
		default:
			System.out.println("1,2,3이 아니다");
			break;
		}
	}
}
