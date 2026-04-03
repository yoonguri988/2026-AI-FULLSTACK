package com.the703.basic006;

import java.util.Scanner;

public class ForInfinit {
	public static void main(String[] args) {
		// 제어문
		// 조건문 ~라면  if  / switch
		// 반복문: 반복  for / while / do while
		// 제어조건 : break / continue
		
		// 1. 영역
		// for(;;) { System.out.println("Hello"); }
		Scanner sc = new Scanner(System.in);
		int a = -1;
		
		for(;;){
			System.out.println("Hello");
			System.out.println("빠져나오실? 0이면 종료");
			a = sc.nextInt();
			// 2. 빠져나올 조건
			if(a == 0) break;
		}
		
//		for(int a = -1; a != 0;){// a가 0이 아니면 반복해
//			System.out.println("Hello");
//			System.out.println("빠져나오실? 0이면 종료");
//			a = sc.nextInt();
//		}
	}
}
