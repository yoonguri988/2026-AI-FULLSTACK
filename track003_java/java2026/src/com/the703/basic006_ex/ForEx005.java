package com.the703.basic006_ex;

public class ForEx005 {
	public static void main(String[] args) {
		int result = 0;
		for(char ch = 'a'; ch <= 'z'; ch++) {
			if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u') result++;
		}
		System.out.println(result);
	}
}
/*
	소문자 a~z까지 모음의 갯수를 출력하시오. 
*/