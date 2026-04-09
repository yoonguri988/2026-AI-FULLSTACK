package com.the703.basic007_ex;

public class For2Ex004 {
	public static void main(String[] args) {
		//4 0
		//3 1
		//2 2
		//1 3
		for (int i = 4; i >= 1; i--) {
			for (int j = 0; j < 4; j++) {
				if(j < 4-i) System.out.print("");
				else System.out.print("#");
			}
			System.out.println();
		}
	}
}
/*
####
###
##
#
*/