package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx001 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("평균을 입력 > ");
		double avg = sc.nextDouble();
		
		if(avg >= 60.0) {
			System.out.println("합격");
		}else {
			System.out.println("불합격");
		}
		
		//ver.2
		System.out.print("ver.2(삼항연산자)> ");
		System.out.println(avg >= 60.0? "합격" : "불합격");
		
	}
}
/* 평균을 입력받아 60점이상이면 합격,  불합격여부를 출력하는 프로그램을 IF로 작성하시오. */