package com.the703.basic007_ex;

public class For2Ex002 {
	public static void main(String[] args) {
		//i == j 
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(i==j?"@":"#"+(j==3?"\n":""));
			}
		}
	}
}
/*
@###
#@##
##@#
###@
*/