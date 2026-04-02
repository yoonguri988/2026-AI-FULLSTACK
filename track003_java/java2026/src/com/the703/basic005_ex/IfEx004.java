package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx004 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자를 입력 > ");
		String str = sc.next();
		char ch = str.charAt(0);
		
		if(ch>='a'  &&  ch<='z') {
			System.out.println("소문자");
			
		}else if(ch>='A' && ch<='Z') {
			System.out.println("대문자");
			
		}else {
			System.out.println("영어 알파벳 문자가 아닙니다.");
		}
		
		//ver.2
		System.out.print("ver.2(삼항연산자)> ");
		System.out.println(ch>='a'  &&  ch<='z'?"소문자":ch>='A' && ch<='Z'?"대문자":"영어 알파벳 문자가 아닙니다.");
		
	}
}
/*
 * 문자한개를 입력받아 
   대문자인지,  소문자인지 판별하는 프로그램을 작성하시오.
   ※  대문자  ch>='A' && ch<='Z' / 소문자  ch>='a'  &&  ch<='z'  
 * 
 */