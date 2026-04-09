package com.the703.basic007_ex;

public class For2Ex013 {
	public static void main(String[] args) {
		System.out.println("ver-1:: for");
		for (int i = 2; i < 10 ; i++) {
			System.out.println(i+"단");
			for(int j = 1; j < 10; j++) {
				System.out.printf("%d*%d=%d\n",i, j, i*j);
			}
			System.out.println();
		}
	}
}
/*
1+...+10 = 55
1+...+20 = 210
*/