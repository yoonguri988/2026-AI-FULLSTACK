package com.the703.basic003_ex;

import java.util.Scanner;

public class DataTypeEx005 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int kor; int eng; int math;
		System.out.print("국어점수를 입력하시오 >");
		kor = sc.nextInt();
		System.out.print("영어점수를 입력하시오 >");
		eng = sc.nextInt();
		System.out.print("수학점수를 입력하시오 >");
		math = sc.nextInt();
		System.out.println();
		
		int sum = kor + eng + math;
		double avg = sum / 3.0;
		System.out.println("총점: "+sum);
		System.out.println("평균: "+String.format("%.2f", avg));
	}
}
/*
 *  Scanner이용해서  성적처리를 입력받고 출력하시오.
   국어점수를 입력하시오.  _입력받기    100 
   영어점수를 입력하시오.  _입력받기    100 
   수학점수를 입력하시오.  _입력받기    99

   총점 :  299
   평균 :  99.67 
 * */
