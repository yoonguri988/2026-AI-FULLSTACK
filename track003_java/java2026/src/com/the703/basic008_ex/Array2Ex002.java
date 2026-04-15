package com.the703.basic008_ex;

public class Array2Ex002 {
	public static void main(String[] args) {
		int[][] arr = new int[2][3];
		int data = 101;
		//  for + length 이용해서 대입 
		for (int i = 0; i < arr.length; i++,data=201) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = data++;
			}
		}
		
		// for + length 이용해서 출력 
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+(j == arr[i].length-1?"\n":"\t"));
			}
		}
		
	}
}
/*
	연습문제2)  array
	패키지명 : ccom.the703.basic008_ex
	클래스명 :  Array2Ex002
	배열을 이용하여 다음의 프로그램을 작성하시오.   
	1. new 연산자 이용하여 다차원배열만들기
	2. for + length 이용해서 대입   
	3. for + length 이용해서 출력 
	   101   102   103
	   201   202   203
*/