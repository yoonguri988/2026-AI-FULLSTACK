package com.the703.basic007_ex;

public class For2Ex001 {
	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print("#"+(j==3?"\n":""));
			}
		}
	}
}
