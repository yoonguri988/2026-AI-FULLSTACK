package com.the703.basic003;

import java.util.Scanner;

public class Input001_Int {
	public static void main(String[] args) {
		// 자료형의 분류 : 기본형 / 참조형
		// 기본형★ : 논리형 boolean, 
		//         정수형 byte(1)-short(2)-int(4)★-long(8), 
		//         실수형 float(4)-double(8)★
		
		// OOP : 클래스(부품객체) 조립해 완성해가는 프로그램
		int one = 0; // one[0]
		Scanner sc = new Scanner(System.in); // ctrl + shift + o : 자동 import
		//1. new 공간 빌리기  1000번지
		//2. Scanner(System.in) Scanner를 사용할 수 있게 초기화 - System.in 키보드 입력
		//3. sc(1000번지) = 1000번지[Scanner]
		//4. 사용법 : sc.next
		
		System.out.print("정수를 입력하세요> ");
		one = sc.nextInt();
		System.out.println("입력한 정수는 "+one+"입니다.");
	}
}
