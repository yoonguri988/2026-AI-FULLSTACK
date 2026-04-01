package com.the703.basic004_ex;

import java.util.Scanner;

public class CastingEx003 {
	public static void main(String[] args) {
		//1. 문자는 저장시 숫자 저장 / 출력시 문자
		System.out.println("1. "+'A'+"\t"+(int)'A'); // 'A' 문자
		System.out.println("2. "+'a'+"\t"+(int)'a'); // "ABC" 문자열
		System.out.println("3. "+"ABC".charAt(0)); // A
		System.out.println("3. "+"ABC".charAt(1)); // B
		
		//- 대문자입력받아서 소문자로 변경프로그램을 작성하시오.
		//변수
		Scanner sc = new Scanner(System.in);
		char ch = '\u0000';
		char lower = '\u0000';
		//입력
		System.out.print("대문자 입력 > ");
		ch = sc.next().charAt(0);
		//처리
		lower = (char) (ch + 32);
		//출력
		System.out.println(ch + "를 소문자로 "+ lower);
	}
}

/*
 * 연습문제3)
패키지명 : com.the703.basic004_ex
클래스명 : CastingEx003
- 대문자입력받아서 소문자로 변경프로그램을 작성하시오.
 */

