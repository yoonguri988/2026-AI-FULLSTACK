package com.the703.basic003_ex;

import java.util.Scanner;

public class DataTypeEx004 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double pie;
		System.out.println("파이값을 입력하시오 >");
		pie = sc.nextDouble();
		System.out.println("파이값은 "+pie+"입니다.");
	}
}
/*
 *  Scanner이용해서 파이값을 입력받고 출력하시오. 
     파이값을 입력하시오 > _입력받기    3.141592    ( 자료형선택 )
     파이값은 **입니다.
 */