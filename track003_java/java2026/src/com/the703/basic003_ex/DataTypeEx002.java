package com.the703.basic003_ex;

import java.util.Scanner;

public class DataTypeEx002 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num;
		System.out.print("좋아하는 수(정수) 입력하시오 >");
		num = sc.nextInt();
		System.out.println("좋아하는 숫자는 "+num+" 입니다.");
	}
}

/*
 * Scanner이용해서 나이 입력받고 출력하시오.
    좋아하는 수(정수)   입력하시오 > _입력받기
    좋아하는 숫자는 ** 입니다.
 * */
