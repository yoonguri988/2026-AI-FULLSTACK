package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx003 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력 > ");
		int num = sc.nextInt();
		
		if(num == 1) {
			System.out.println("one");
		}else if(num == 2) {
			System.out.println("two");
		}else if(num == 3) {
			System.out.println("three");
		}else {
			System.out.println("1,2,3이 아니다");
		}
		
		//ver.2
		System.out.print("ver.2(삼항연산자)> ");
		System.out.println(num == 1?"one":num == 2?"two":num == 3?"three":"1,2,3이 아니다");
		
		//ver.3
		String result = "";
		if(num == 1) result = "one";
		else if(num == 2) result = "two";
		else result = num == 3 ? "three" :"1,2,3이 아니다";
		System.out.println(result);
	}
}
/*
 * 숫자한개를 입력받아 
   만약 1을 입력했다면   one ,   
   만약 2을 입력했다면   two ,
   만약 3을 입력했다면   three ,
   1,2,3이 아니라면  1,2,3이 아니다를 출력하는 프로그램을 작성하시오.
 * */
