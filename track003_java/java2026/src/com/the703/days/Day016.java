package com.the703.days;

public class Day016 {
	public static void main(String[] args) {
		int[][] arr = new int[2][3];
		
		int n = 101;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = n++;
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + (j == arr[i].length-1?"\n":"\t"));
			}
		}
	}
}
/*
1. new 연산자 이용하여 다차원배열만들기
2. for + length 이용해서 대입   
3. for + length 이용해서 출력 
   101   102   103
   104   105   106 
*/