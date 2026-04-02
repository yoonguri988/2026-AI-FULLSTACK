package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx002 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력 > ");
		int num = sc.nextInt();
		
		if(num > 0) {
			System.out.println("양수");			
		}else if(num < 0) {
			System.out.println("음수");
		}else {
			System.out.println("zero");
		}
		
		//ver.2
		System.out.print("ver.2(삼항연산자)> ");
		System.out.println(num > 0? "양수": num < 0? "음수":"zero");
		
		//ver.3
		String result = "zero";
		if(num != 0) result = num > 0? "양수": "음수";
		System.out.println(result);
	}
}
/* 숫자 한개를 입력받아 
   양수라면 양수  , 음수라면 음수  ,0이라면 zero를 출력하는 프로그램을 작성하시오. */