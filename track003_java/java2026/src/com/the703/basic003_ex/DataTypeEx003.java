package com.the703.basic003_ex;

import java.util.Scanner;

public class DataTypeEx003 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int birth;
		int year = 2026;
		System.out.println("태어난 년도를 입력하세요. >");
		birth = sc.nextInt();
		System.out.println("당신의 나이는 "+(year-birth-1)+"살 입니다.");
	}
}
/*
 *  Scanner이용해서 태어난 년도를 입력받아 나이 계산하기
태어난 년도를 입력하세요. >
2000
당신의 나이는 25살 입니다.
 */