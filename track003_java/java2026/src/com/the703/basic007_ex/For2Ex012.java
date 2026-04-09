package com.the703.basic007_ex;

public class For2Ex012 {
	public static void main(String[] args) {
		System.out.println("ver-1:: for");
		for (int i = 1; i <= 10 ; i++) {
			int sum = 0;
			for(int j = 1; j <= i*10; j++) {
				sum += j;
				System.out.printf(j != i*10?"%d+":"%d=%d\n", j, sum);
			}
		}

		System.out.println("ver-2:: while");
		int x = 1; 
		while(x <= 10) {
			int sum = 0;
			int y = 1; 
			while(y <= x*10) {
				sum += y;
				System.out.printf(y != x*10?"%d+":"%d=%d\n", y, sum);
				y++;
			}
			x++;
		}

		System.out.println("ver-3:: do while");
		x = 1; 
		do {
			int sum = 0;
			int y = 1; 
			do {
				sum += y;
				System.out.printf(y != x*10?"%d+":"%d=%d\n", y, sum);
				y++;
			} while(y <= x*10);
			x++;
		}while(x <= 10);

	}
}
/*
1+...+10 = 55
1+...+20 = 210
*/