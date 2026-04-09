package com.the703.basic007_ex;

public class For2Ex007 {
	public static void main(String[] args) {
		for (int i = 4; i >= 1; i--) {
			for (int j = 0; j < 4; j++) {
				if(j < 4-i) System.out.print("");
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