package com.the703.basic006_ex;

public class RepeatEx004 {
	public static void main(String[] args) {
		
		System.out.println("for version >>> ");
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.print(ch + ( ch % 5 == 4 || ch == 'Z'?"\n":""));
		}
		
		System.out.println("while version >>> ");
		char ch2 = 'A';
		while(ch2 <= 'Z') {
			System.out.print(ch2 + ( ch2 % 5 == 4 || ch2 == 'Z' ?"\n":""));
			ch2++;
		}
		
		System.out.println("do while version >>> ");
		char ch3 = 'A';
		do {
			System.out.print(ch3 + ( ch3 % 5 == 4 || ch3 == 'Z' ?"\n":""));
			ch3++;
		} while(ch3 <= 'Z');
	}
}
/*
'A' ~ 'Z' 까지 출력
9
ABCDE 65 66 67 68 69
FGHIJ 70 71 72 73 74
KLMNO 
PQRST
UVWXY 
Z     91
*/