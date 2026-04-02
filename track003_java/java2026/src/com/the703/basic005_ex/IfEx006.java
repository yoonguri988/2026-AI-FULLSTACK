package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx006 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자를 입력 > ");
		int num = sc.nextInt();
		
		System.out.println(num % 2 == 0? "여자":"남자");
		
	}

}
/*
 * 숫자를입력을받아
   홀수면 남자, 짝수면 여자를 출력하는 프로그램을 작성하시오.
   ※  num%2==0  짝수
 */
