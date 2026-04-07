package com.the703.basic006_ex;

import java.util.Scanner;

public class ForEx002 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//변수
		int dan = 0;
		//입력
		System.out.print("단을 입력 > ");
		dan = sc.nextInt();
		//처리 출력
		for(int i = 1; i < 10; i++) System.out.printf("%d * %d = %d\n",dan,i,dan*i);
	}
}
/*
   사용자에게 단을 입력받아 해당하는 
   단을 출력해주는 프로그램을 작성하시오. FOR문을 이용하시오.
*/