package com.the703.basic004;

public class Casting001 {
	public static void main(String[] args) {
		//1. 자료형 (기본형 / 참조형)
		//2. 기본형 - 논리형: boolean - true/false 
		//        - 정수형: byte(1) - short(2) - int★(4) - long(8L) 
		//        - 실수형: float(4f) - double★(8)
		//3. #1.형변환 - 자동 타입 변환[묵시적 promotion] (boolean 빼고)
		byte by = 1; short sh = 2; int in = 4; long lo = 8L;
		
		float fl = 3.14f; double dou = 3.14; 
		boolean bl = true;
		
		sh = by; // 정수형 2byte <- 정수형 1byte
		in = by; // 정수형 4byte <- 정수형 1byte
		lo = by; // 정수형 8byte <- 정수형 1byte
		// lo = fl; // 정수형 8byte <X- 실수형 4byte
		            // Type mismatch: cannot convert from float to long
		fl = lo;    // 실수형 4byte <- 정수형 8byte
		// in = bl; // 정수형 4byte <X- 불린형 1byte
		            // Type mismatch: cannot convert from boolean to int
		by = -128;  
		by = 0;
		// by = 128;    // byte : -128 ~ 127
		// 1) 1bit : 1 0  < 8 bit == 1byte
		// 2) 10 -> 0 1(2개) / 10 10 -> 00 01 10 11(4개) / ... 
		//          2^1= 2             2^2= 4             ... 2^8= 128
		// 2) 음수 0 양수 -128 ~ 127 (:0 포함)
		
		//   #2.형변환 - 강제 타입 변환[명시적 casting]
		by = (byte) in; // 정수형 1byte <X- 정수형 4byte :: Add cast to ?
		
		int in2 = (int) 1.2;
		float fl2 = (float) 1.2;
		
		System.out.println(in2+"\t"+fl2);
		
		//Q1) System.out.println(  1.5 + 2.7 ); 형변환이용해서 3으로 나오게 만들기
		int i = (int)1.5;
		int j = (int)2.7;
		System.out.println(i+j);

		System.out.println((int)1.5 + (int)2.7);
	}
}
