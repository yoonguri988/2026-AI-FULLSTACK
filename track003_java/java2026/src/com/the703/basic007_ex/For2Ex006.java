package com.the703.basic007_ex;

public class For2Ex006 {
	public static void main(String[] args) {
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if(j <= 4-i) System.out.print("");
				else System.out.print(i);
			}
			System.out.println();
		}
	}
}
/*
1234
1234
1234
1234
*/