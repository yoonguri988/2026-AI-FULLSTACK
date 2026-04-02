package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx005 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자를 입력 > ");
		String str = sc.next();
		char ch = str.charAt(0);
		
		if('a'<= ch && ch <= 'z') {
			ch = (char)(ch - 32);
		}else if('A' <= ch && ch <= 'Z') {
			ch = (char)(ch + 32);			
		}
		System.out.println(ch);
	}

}
/*
 * 문자한개를 입력받아 
   대문자인지 이면 소문자로,  소문자이면 대문자로 변경하는 프로그램을 작성하시오.
   ※  a = 'A' + 32    
 * 
 */