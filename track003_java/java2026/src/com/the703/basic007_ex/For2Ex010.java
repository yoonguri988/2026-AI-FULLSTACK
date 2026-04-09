package com.the703.basic007_ex;

public class For2Ex010 {
	public static void main(String[] args) {
		System.out.println("ver-1:: for");
		String result = "";
		for (char chi = 'Z'; chi >= 'A'; chi--) {
			result = "";
			for(char chj = 'A'; chj <= 'Z'; chj++) {
				if(chi>=chj) result += chj;
				else if(chi == chj) break;
			}
			System.out.println(result);
		}
		System.out.println("ver-2:: while");
		result = "";
		char chx = 'Z';
		while(chx >= 'A') {
			result = "";
			char chy = 'A';
			while(chy <= 'Z') {
				if(chx>=chy) result += chy;
				else if(chx == chy) break;
				chy++;
			}
			System.out.println(result);		
			chx--;
		}
		System.out.println("ver-3:: do while");
		result = "";
		chx = 'Z';
		do {
			result = "";
			char chy = 'A';
			do {
				if(chx>=chy) result += chy;
				else if(chx == chy) break;
				chy++;
			}while(chy <= 'Z');
			System.out.println(result);		
			chx--;
		}while(chx >= 'A');
	}
}
/*
ABCDEFGHIJKLMNOPQRSTUVWXYZ
*/