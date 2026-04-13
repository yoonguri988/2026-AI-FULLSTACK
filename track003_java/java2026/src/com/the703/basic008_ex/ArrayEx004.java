package com.the703.basic008_ex;

public class ArrayEx004 {
	public static void main(String[] args) {
		char[] ch = {'B' , 'a' , 'n' , 'a', 'n' , 'a'};
		int count = 0;
		
		for (int i = 0; i < ch.length; i++) {
			if(ch[i] == 'a') count++;
		}
		
		System.out.printf("a의 갯수: %d\n",count);
	}
}
/*
연습문제4)  array
패키지명 : com.the703.basic008_ex
클래스명 :  ArrayEx004
    1. 배열명 : ch
    2. 값 넣기 : 'B' , 'a' , 'n' , 'a', 'n' , 'a'
    3. ch 배열에서 a의 갯수 세기

*/