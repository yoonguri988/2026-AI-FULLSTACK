package com.the703.basic008_ex;

import java.util.Scanner;

public class Array2Ex005 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char ch = scan.next().charAt(0);
		System.out.println();	//'\0x2192': →
		// 1. → 입력 받으면 시계방향으로 90도 돌아가
		int n = 5;
		int[][] arr = new int[n][n];
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = (i*5)+(j+1);
			}
		}
		
		//출력
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+(j == arr[i].length-1? "\n":" "));
			}
		}
		
		// 90도 회전
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[j][arr.length-1-i] = (i*5)+(j+1);
			}
		}
		
		System.out.println();System.out.println();
		//출력
		System.out.println("\n90도 회전>>");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+(j == arr[i].length-1? "\n":" "));
			}
		}
		
		
		// 180도 회전
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[arr.length-1-i][arr[i].length-1-j] = (i*5)+(j+1);
			}
		}
		
		//출력
		System.out.println("\n180도 회전>>");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+(j == arr[i].length-1? "\n":" "));
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[arr[i].length-1-j][i] = (i*5)+(j+1);
			}
		}
		
		//출력
		System.out.println("\n반시계 방향 90도 회전>>");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+(j == arr[i].length-1? "\n":" "));
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