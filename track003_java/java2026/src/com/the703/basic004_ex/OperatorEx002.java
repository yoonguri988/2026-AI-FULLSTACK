package com.the703.basic004_ex;

public class OperatorEx002 {
	public static void main(String[] args) {
//		q1-1 int형 변수 x가 3보다 크고 10보다 작을때 true인 조건식 
		int x = 5; char ch = '\u0000';
		System.out.println(x > 3 && x < 10);
//		q1-2 char형 변수 ch가 'a' 또는 'A'일때   true인 조건식
		ch = 'a';
		System.out.println(ch == 'a' || ch == 'A');
//		q1-3 char형 변수 ch가 숫자('0'~'9')일때   true인 조건식
		ch = '5';
		System.out.println(ch >= '0' && ch <= '9');
//		q1-4 char형 변수 ch가 영문자(대문자 또는 소문자) 일때   true인 조건식
		ch = 'B';
		System.out.println(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z');
		
	}
}
