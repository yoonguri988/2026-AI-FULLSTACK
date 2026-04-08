package com.the703.basic006_ex;

import java.util.Scanner;

public class ForEx12_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//변수
		int num1 = -1, num2 = -1, sum = 0;
		//입력
		System.out.print("숫자 1 입력 : ");
		num1 = sc.nextInt();
		System.out.print("숫자 2 입력 : ");
		num2 = sc.nextInt();
		//처리
		
		// version-2-출력
		for(int i = num1;num1 > num2 ? i>=num2 : i<=num2;) {
			sum += i;
			// System.out.print(i);
			if(num1>num2) i--;
			else if(num1<num2) i++;
			else break;
		}
		System.out.println(sum);
		
	}
}
// 두수 사이의 합