package com.the703.basic007_ex;

public class For2Ex014 {
	public static void main(String[] args) {
		System.out.println("ver-1:: for");
		
		for(int k = 2; k < 10; k+=3) {
			for (int j = 1; j < 10; j++) {
				for (int i = k; i < k+3; i++) {
					if(i == 10) break;
					System.out.printf("%d*%d=%d\t",i,j,i*j);
				}
				System.out.println();
			}
			System.out.println();
		}

//		for (int j = 1; j < 10; j++) {
//			for (int i = 2; i < 5; i++) {
//				System.out.print(i);
//			}
//			System.out.println();
//		}
//
//		System.out.println();
//		for (int j = 1; j < 10; j++) {
//			for (int i = 5; i < 8; i++) {
//				System.out.print(i);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (int j = 1; j < 10; j++) {
//			for (int i = 8; i < 10; i++) {
//				System.out.print(i);
//			}
//			System.out.println();
//		}
//		System.out.println();
	}
}
/*
 * 2 3 4 1+1 2+1 3+1 5 6 7 4+1 5+1 6+1 8 9 10 7+1 8+1
 */