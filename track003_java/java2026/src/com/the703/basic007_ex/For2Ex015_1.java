package com.the703.basic007_ex;

public class For2Ex015_1 {
	public static void main(String[] args) {

		//version -1
//		for(int i = 0; i <= 2; i++) {
//			for(int j = 0; j <= 4; j++) {
//				System.out.print((i <= j && j <= 4-i)?"#":" ");
//			}
//			System.out.println();
//		}
//		
//		for(int i = 1; i >= 0; i--) {
//			for(int j = 0; j <= 4; j++) {
//				System.out.print((i <= j && j <= 4-i)?"#":" ");
//			}
//			System.out.println();
//		}
		
		//version -2
		boolean flag = false;
		int i = 0;
		do {
			for(int j = 0; j <= 4; j++) {
				System.out.print((i <= j && j <= 4-i)?"#":" ");
			}
			System.out.println();
			if(flag) i--;
			else i++;
			if(i == 2) flag = true;
		}while(i >= 0);
		
		//version - 3

		
//		for(int i = 0; i <= 2; i++) {}
//		i == 3 5-1-3=1
//		for(int i = 1; i >= 0; i--) {}
		
	}
}
/*
  #####
   ###
    #
   ###
  #####
 */