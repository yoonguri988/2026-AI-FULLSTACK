package com.the703.basic006;

import java.util.Iterator;

public class ForBasic {
	public static void main(String[] args) {
		// step1) 줄바꿈안된 print 이용해서
		System.out.println("\n■ STEP1) 출력");
		System.out.print(1);
		System.out.print(2);
		System.out.print(3);
		
		System.out.println("\n■ STEP2) FOR");
		// step2-1) 반복되는 영역
//		{
//			System.out.print( );
//		}
		// step2-2) 반복되는 영역 -> 변수 찾기
//		{
//			System.out.print(     i    );
//		}
		// step2-3) 패턴 찾기 (시작;종료;변화)
		//                  ( 1 ; 3 ; 1 )
		for(int i = 1; i <=3; i++)
		{
			System.out.print(i);
		}
		
		// step3)
		System.out.println("\n■ STEP3) FOR 연습");
		
		//패턴: 시작 1;종료 10;변화 1
		for (int i = 1; i <= 10; i++) { System.out.print(i);} System.out.println();
		
		//패턴: 시작 2;종료 8;변화 1
		for (int i = 2; i <= 8; i++) { System.out.print(i);} System.out.println();

		//패턴: 시작 3;종료 9;변화 3
		for (int i = 3; i <= 9; i+=3) { System.out.print(i);} System.out.println();
		
		//패턴: 시작 5;종료 1;변화 2
		for (int i = 5; i >= 1; i-=2) { System.out.print(i);} System.out.println();
		
		//Hi1 Hi2 Hi3
		for (int i = 1; i <= 3; i++) { System.out.print("Hi"+i); } System.out.println();
	}
}
