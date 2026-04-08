package com.the703.basic006;

public class WhileBasic {
	public static void main(String[] args) {

		System.out.println("ver-1 for");
		for (int i = 1; i <= 3; i++) {
			System.out.print(i);
		}

		System.out.println("\nver-2 while");
		int i = 1;
		while (i <= 3) {
			System.out.print(i);
			i++;
		}
		
		System.out.println("\nver-3 do while");
		i = 1;
		do {
			System.out.print(i);
			i++;
		} while (i <= 3);
	}
}
