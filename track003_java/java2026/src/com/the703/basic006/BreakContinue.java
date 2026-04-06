package com.the703.basic006;

import java.util.Scanner;

public class BreakContinue {
	public static void main(String[] args) {
		// ver-0
		//{ int a = 1; System.out.println(a); } // 영역
		//a = 2; //why? X 영역 안에 선언한 것을 영역 밖에서 사용불가
		
		//ver-1 for 반복
		// 반복해   영역 {여기서부터     여기까지}
		//for(;;) {System.out.println(1);}
		
		//ver-2 반복 빠져나오기
		for(int i = 1;i < 5; i++){// (초기화식;조건식;증감식)
			if(i == 3) break;
			System.out.println(i); 
		}
		// 초기값: 
		// 증감식: 영역이 끝나면 해야할 일
		// break: 제일 가까운 for문 영역을 나가
		
		//////////////////////////////
		System.out.println();
		
		for(int i = 1;i < 5; i++){// (초기화식;조건식;증감식)
			if(i == 3) continue;
			System.out.println(i); 
		}
		//continue: 아래 처리를 진행하지 말고 스킵
		
		//ver-3
		int a = -1;
		Scanner sc = new Scanner(System.in);
		for(;;) {
			System.out.print("1 입력 > ");
			a = sc.nextInt();
			if(a == 1) { break; } // 잘 입력하면 나가~~
		}
		
	}
}
