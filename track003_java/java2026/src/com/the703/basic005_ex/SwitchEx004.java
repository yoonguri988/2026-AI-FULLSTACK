package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx004 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char ch = '\u0000';
		
		System.out.print("문자 한개 입력 > ");
		ch = sc.next().charAt(0);
		
		switch(ch) {
		case 'a':
		case 'A':
			System.out.println("APPLE");
			break;
		case 'b':
		case 'B':
			System.out.println("BANANA");
			break;
		case 'c':
		case 'C':
			System.out.println("COCONUT");
			break;
		}
	}
}
//switch 이용
//문자한개 입력받아
//a , A이면 APPLE
//b , B이면 BANANA
//c , C이면 COCONUT