package com.the703.basic008_ex;

public class ArrayEx005 {
	public static void main(String[] args) {
		char[] ch = {'B' , 'a' , 'n' , 'a', 'n' , 'a'};
		int lgCnt = 0, smCnt = 0;
		
		for (int i = 0; i < ch.length; i++) {
			if('a' <= ch[i] && ch[i] <= 'z') smCnt++;
			else if('A' <= ch[i] && ch[i] <= 'Z') lgCnt++;
		}
		
		System.out.printf("대문자 갯수: %d, 소문자 갯수: %d\n", lgCnt, smCnt);
	}
}
/*
연습문제5)  array
패키지명 : com.the703.basic008_ex
클래스명 :  ArrayEx005
    1. 배열명 : ch
    2. 값 넣기 : 'B' , 'a' , 'n' , 'a', 'n' , 'a'
    3. ch 배열에서 대문자의 갯수카운트, 소문자의 갯수 카운트

*/