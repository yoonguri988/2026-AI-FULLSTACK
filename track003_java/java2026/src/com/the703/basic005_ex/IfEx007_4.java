package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx007_4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1 = 0;
		int num2 = 0;
		char oper = '\u0000';
		String result = "";
		
		System.out.print("정수를 하나 입력해주세요 > ");
		num1 = sc.nextInt();
		System.out.print("정수를 하나 입력해주세요 > ");
		num2 = sc.nextInt();
		System.out.print("연산자를 입력해주세요(+,-,*,/) > ");
		String ip = sc.next();
		
		oper = ip.charAt(0);
		
		result = ""+num1+oper+num2+"=";
		
		if(oper == '+') result += num1 + num2;
		else if(oper == '-') result += num1-num2;
		else if(oper == '*') result += num1*num2;
		else if(oper == '/') result += String.format("%.2f", num1/(double)num2);
		
		System.out.println(result);
		
	}
}
/*
 * 계산기
 * 
 * 1. 정수를 하나 입력해주세요 > 10 
 * 2. 정수를 하나 입력해주세요 > 3 
 * 3. 연산자를 입력해주세요(+,-,*,/) > +
 * 10+3=13
 */

/*
 */