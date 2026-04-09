package com.the703.basic007;

public class For2Basic {
	public static void main(String[] args) {
		// ver-1
		System.out.println(1+"층");
		System.out.print("1");System.out.print("2");System.out.print("3\n");
		
		System.out.println(2+"층");
		System.out.print("1");System.out.print("2");System.out.print("3\n");
		
		// ver-2 각 칸 정리 for 정리_ { 영역 } { 변수 } { 패턴 } for(시작;종료;변화)
		System.out.println();
		System.out.println(1+"층");
		for(int kan = 1; kan <= 3; kan++){System.out.print(kan);} System.out.println();
		
		System.out.println(2+"층");
		for(int kan = 1; kan <= 3; kan++){System.out.print(kan);} System.out.println();
		
		// ver-2 각 칸 정리 for
		System.out.println();
		for(int i = 1; i <=2; i++) {
			System.out.println(i+"층");
			for(int kan = 1; kan <= 3; kan++){System.out.print(kan);} System.out.println();
		}
		
		// 이중 for 문
		System.out.println();
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				System.out.print(j+(j==4?"\n":""));
			}
		}
		
	}
}
