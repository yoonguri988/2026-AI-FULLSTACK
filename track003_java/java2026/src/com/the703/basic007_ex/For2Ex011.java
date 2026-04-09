package com.the703.basic007_ex;

public class For2Ex011 {
	public static void main(String[] args) {
		System.out.println("ver-1:: for");
		for(int i = 0; i < 10; i++) {
			int sum = 0;
			for(int j = 1; j <= 10; j++) {
				sum += i*10+j;
				System.out.printf(j != 10?"%d+":"%d=%d\n", i*10+j, sum);
			}
		}

		System.out.println("ver-2:: while");
		int x = 0;
		while(x < 10) {
			int sum = 0;
			int y = 1;
			while(y <= 10) {
				sum += x*10+y;
				System.out.printf(y != 10?"%d+":"%d=%d\n", x*10+y, sum);
				y++;
			}
			x++;
		}

		System.out.println("ver-3:: do while");
		x = 0;
		do {
			int sum = 0;
			int y = 1;
			do {
				sum += x*10+y;
				System.out.printf(y != 10?"%d+":"%d=%d\n", x*10+y, sum);
				y++;
			} while(y <= 10);
			x++;
		} while(x < 10);

	}
}
/*
1+...+10 = 55
11+...+20 = 155
*/