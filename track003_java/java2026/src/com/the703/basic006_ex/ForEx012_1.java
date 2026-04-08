package com.the703.basic006_ex;

import java.util.Scanner;

public class ForEx012_1 {

	public static void main(String[] args) {
		//두 수 입력받음
		//2,5로 입력받으면 2+3+4+5=14 출력, 5,2로 입력받으면 5+4+3+2=14 출력
		
		int a=-1,b=-1, num=0;
		int c=-1;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("첫번째 숫자 입력 : ");
		a=scanner.nextInt();
		System.out.print("두번째 숫자 입력 : ");
		b=scanner.nextInt();

//		for(; ;) {
//		if(a<b) {
//			for(int i= a; i<=b; i++) {
//				num = num+i;
//				System.out.print((i==a?"":"+")+i);
//			} break;
//		}
//		else if(a>b) {
//			for(int i1= a; i1>=b; i1--) {
//				num = num+i1;
//				System.out.print((i1==a?"":"+")+i1);
//			} break;
//		}
//	}
		
		c = a<b ? 1:-1;	
		//i<=b   i-b<=0  2-5=-3(성립x)
		//a-2, b-5  3-5x1=-2 / 4-5x1=-1 / 6-5x1=1->종료
		//a-5, b-2  4-2x-1=2 / 3-2x-1=-1 / 1-2x-1=1->종료
						
                //   i=i+c(1,-1)
		for(int i = a; (i-b)*c <= 0 ; i+=c) {
			num = num+i;
			System.out.print((i==a?"":"+")+i);
		} 
		
		
		System.out.println("="+num);
	}

}
