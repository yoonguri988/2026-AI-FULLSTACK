package com.the703.basic008_ex;

import java.util.Scanner;

public class Array2Ex005_1 {
	public static void main(String[] args) {
		// 변수
		int n = 5, deg = 0;
		int[][] arr = new int[n][n];
		Scanner scan = new Scanner(System.in);
		char ch = '\u0000';
		// 변수
		
		while(ch != '0') {
			System.out.print("← 또는 → 입력(0입력시 종료)  > ");
			ch = scan.next().charAt(0);
			if(ch == '0') break;
			
			switch(ch) {
			case '\u2192': // → 
				deg = (deg+90)%360;
				break;
			case '\u2190': // ←
				if(deg < 90) deg = 360;
				deg = (deg-90)%360;
				break;
			}
			
			//  1. → 입력 받으면 시계방향으로 90도 돌아가
			for(int i = 1; i <= n*n; i++) {
				int row = (i-1) / n;
				int col = (i-1) % n;
				switch(deg/90) {
					case 0: arr[row][col] = row * n + (col+1); break;
					case 1: arr[col][n-1-row] = row * n + (col+1); break;
					case 2: arr[n-1-row][n-1-col] = row * n + (col+1); break;
					case 3: arr[n-1-col][row] = row * n + (col+1); break;
				}
			}
			
			//출력
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					System.out.print(arr[i][j]+(j == arr[i].length-1? "\n":" "));
				}
			}
		}
		
	}
}
/*
	연습문제5)  array
	패키지명 : ccom.the703.basic008_ex
	클래스명 :  Array2Ex005
	
	2차원 배열 회전하기
	다음과 같은 5×5 배열이 있습니다.
	 
	 1   2   3   4   5
	 6   7   8   9  10
	11  12  13  14  15
	16  17  18  19  20
	21  22  23  24  25
	
	배열을 시계 방향으로 90도 회전해서 출력하시오.
	 
	21 16 11  6  1
	22 17 12  7  2
	23 18 13  8  3
	24 19 14  9  4
	25 20 15 10  5

*/