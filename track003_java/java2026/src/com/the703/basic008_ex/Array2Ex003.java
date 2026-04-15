package com.the703.basic008_ex;

public class Array2Ex003 {
	public static void main(String[] args) {
		int[][] arr = {
				   { 1, 1, 1,},
				   { 2, 2, 2,},
				   { 3, 3, 3,},
				   { 4, 4, 4,},
				 };
		int total=0;  double avg=0.0;
		
		for (int i = 0; i < arr.length; i++) {
			
			for (int j = 0; j < arr[i].length; j++) {
				total += arr[i][j];
			}
		}
		avg = (double) total / (arr.length * arr[0].length);
		
		// 출력
		System.out.println("총점 : "+total);
		System.out.println("평균 : "+avg);
	}
}
/*
	연습문제3)  array
	패키지명 : ccom.the703.basic008_ex
	클래스명 :  Array2Ex003
	배열을 이용하여 다음의 프로그램을 작성하시오.   
	1. 다음의 주어진조건을 이용하여 총점과 평균을 구하시오.
	
	 int[][] arr = {
	   { 1, 1, 1,},
	   { 2, 2, 2,},
	   { 3, 3, 3,},
	   { 4, 4, 4,},
	 };
	 int total=0;  double avg=0.0;
	
	출력내용:
	총점 : 30
	평균 : 2.5
*/