package com.the703.days;

public class Day015 {
	public static void main(String[] args) {
		for (int i = 3; i >= 1; i--) {
			System.out.print(i+(i==1?"\n":" "));
		}
		
		int x = 3;
		while( x >= 1 ) {
			System.out.print(x+(x==1?"\n":" "));
			x--;
		}
		
		x = 3;
		do {
			System.out.print(x+(x==1?"\n":" "));
			x--;
		}while( x >= 1 );
		
		for(int i = 1; i <= 3; i++) {
			for (int j = i; j <= 3; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		
		char arr[] = new char[3];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (char)('A'+i);
		}
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + (i==arr.length-1?"\n":" "));
		}
	}
}
/*
1.  for, while , do while을 이용해서 문제를 풀으시오.
3   2   1

2 이중 for 버전
다음과 같은 모양을 출력하는 프로그램을 작성하시오.
★★★
★★
★

3.  1차원배열      new 연산자 이용해서 배열만들기
1. 배열명 : arr     
2. 값 넣기 :   A B C        for+length 이용서 값 대입
3. for + length 로 출력 
*/