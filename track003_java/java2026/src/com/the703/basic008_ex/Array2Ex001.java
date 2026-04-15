package com.the703.basic008_ex;

public class Array2Ex001 {
	public static void main(String[] args) {
		int[][] arr2={{100,200,300},{400,500,600}};
		
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length; j++) {
				System.out.print(arr2[i][j]+(j==arr2[i].length-1?"\n":"\t"));
			}
		}
	}
}
/*
	연습문제1)  array
	패키지명 : com.the703.basic008_ex
	클래스명 :  Array2Ex001
	배열을 이용하여 다음의 프로그램을 작성하시오.   
	  
	   int[][] arr2={{100,200,300},{400,500,600}};
	
	   이중for+ length 이용해서 출력하기   
*/