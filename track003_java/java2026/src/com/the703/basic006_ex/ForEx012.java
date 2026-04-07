package com.the703.basic006_ex;

import java.util.Scanner;

public class ForEx012 {
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
		
		//version-1-출력
//		for(int i = num1; i <= num2; i++) {
//			System.out.printf(i!=num2?"%d+":"%d="+"%d\n", i, sum+=i);
//		}
//		for(int i = num1; i >= num2; i--) {
//			System.out.printf(i!=num2?"%d+":"%d="+"%d\n", i, sum+=i);
//		}
		
		// version-2-출력
		for(int i = num1;;) {
			sum += i;
			if(i == num2) {
				System.out.printf("%d=%d\n", i, sum);
				break;
			}
			System.out.printf("%d+", num1 > num2? i-- : i++);
		}
	}
}
// 두수 사이의 합