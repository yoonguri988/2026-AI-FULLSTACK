package com.the703.basic007_ex;

public class For2Ex008 {
	public static void main(String[] args) {
		System.out.println("ver-1:: for");
		for (int i = 1; i <= 5 ; i++) {
			for (int j = 5; j >= 1; j--) {
				if(i+j != 6) continue;
				System.out.printf("%d+%d=%d\n",i,j,i+j);
			}
		}
		System.out.println("ver-2:: while");
		int x = 1;
		while(x <= 5) {
			int y = 5;
			while(y >= 1) {
				if(x+y == 6) System.out.printf("%d+%d=%d\n",x,y,x+y);
				y--;
			}
			x++;
		}
		System.out.println("ver-3:: do while");
		x = 1;
		do {
			int y = 5;
			do{
				if(x+y == 6) System.out.printf("%d+%d=%d\n",x,y,x+y);
				y--;
			}while(y >= 1);
			x++;
		}while(x <= 5);
		
	}
}
/*
1 5
2 4
3 3
4 2
5 1
*/