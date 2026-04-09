package com.the703.basic007_ex;

public class For2Ex005 {
	public static void main(String[] args) {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				System.out.print(j+(j==4?"\n":""));
			}
		}
	}
}
/*
1234
1234
1234
1234
*/