package com.the703.basic004;

public class Casting002_char {
	public static void main(String[] args) {
		// #1. 문자 - 저장시 숫자, 출력시 문자
		char ch1 = ' '; // 공백 한개라도 있어야 오류가 안남
		char ch2 = '\u0000'; // 정식 초기화

		System.out.println("step1: " + ch1 + "\t" + ch2);
		System.out.println("step2: " + (int) ch1 + "\t" + (int) ch2);

		// #2. 문자
		char ch = 'A'; // 아스키 코드 값 참조
		System.out.println("step3: " + ch + "\t" + (int) ch);
		System.out.println("step4: " + (ch+1));
		//why? 66
		// 2-1) 'A' + 1
		// 2-2) char(65| 2byte) + int(1| 4byte)
		// 2-3) int(66| 4byte)
		System.out.println("step4: " + (char)(ch+1));
		
		//Q.대문자 A 소문자 a로 변환
		char q = 'A';
		char a = (char)(q + 32); //char(65| 2byte) + int(32| 4byte)
		System.out.println((int)'A'+"\t"+(int)'a');
		System.out.println(a);
	}
}
//1. 자료형 (기본형 / 참조형)
//2. 기본형 - 논리형: boolean - true/false 
//        - 정수형: byte(1) - short(2) - int★(4) - long(8L) 
//        - 실수형: float(4f) - double★(8)
//3. #1.형변환 - 자동 타입 변환[묵시적 promotion] (boolean 빼고)