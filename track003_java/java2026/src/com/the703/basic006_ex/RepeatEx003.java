package com.the703.basic006_ex;

public class RepeatEx003 {
	public static void main(String[] args) {
		// FOR
		System.out.println("for version >>");
		int n = 1;
		for(int i = 1; i <= 30; i++) {
			if(i % 3 != 0 || i % 2 != 0) continue;
			System.out.printf("%d. 3의 배수이면서 2의 배수인 숫자 : %d\n",n++,i);
		}
		
		
		//while
		System.out.println("while version >>");
		int m = 1;
		int x = 1;
		while(x <= 30) {
			if(x % 3 == 0 && x % 2 == 0) System.out.printf("%d. 3의 배수이면서 2의 배수인 숫자 : %d\n",m++,x);
			x++;
		}
		
		//do while
		System.out.println("do while version >>");
		int k = 1;
		int y = 1;
		do {
			if(y % 3 == 0 && y % 2 == 0) System.out.printf("%d. 3의 배수이면서 2의 배수인 숫자 : %d\n",k++,y);
			y++;
		} while(y <= 30);
	}
}
/*
	30의 범위 에서 3의 배수이면서, 2의배수인숫자 와 개수를 구한다.
	1. 3의 배수이면서 2의 배수인 숫자 : 6
	2. 3의 배수이면서 2의 배수인 숫자 : 12
	3. 3의 배수이면서 2의 배수인 숫자 : 18
	4. 3의 배수이면서 2의 배수인 숫자 : 24
	5. 3의 배수이면서 2의 배수인 숫자 : 30
*/