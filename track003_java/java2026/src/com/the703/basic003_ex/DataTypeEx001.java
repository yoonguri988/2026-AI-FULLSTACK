package com.the703.basic003_ex;

import java.util.Scanner;

public class DataTypeEx001 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int age;
		System.out.print("당신의 나이를 입력하시오 >");
		age = sc.nextInt();
		System.out.println("내 나이는 "+age+" 입니다.");
	}
}
/*
 * 1-1.  나이를 입력받을 자료형 선택후  변수명  age로 하시오.  예) 10,20
   1-2.  Scanner이용해서 나이 입력받고 출력하시오.
     _입력받기
    내 나이는 ** 입니다.
 * 
 */