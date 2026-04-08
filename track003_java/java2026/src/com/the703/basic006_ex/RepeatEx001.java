package com.the703.basic006_ex;

public class RepeatEx001 {
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			System.out.print(i+(i!=5?" ":"\n"));
		}

		int z = 1;
		while(z <= 5) {
			System.out.print(z +(z!=5?" ":"\n"));
			z++;
		}
		
		z = 1;
		do {
			System.out.print(z +(z!=5?" ":"\n"));
			z++;
		}while(z <= 5);
		
		
		for (int i = 5; i >= 1; i--) {
			System.out.print(i+(i!=1?" ":"\n"));
		}
		
		int x = 5;
		while(x >= 1) {
			System.out.print(x +(x!=1?" ":"\n"));
			x--;
		}
		
		x = 5;
		do {
			System.out.print(x +(x!=1?" ":"\n"));
			x--;
		}while(x >= 1);
		
		for (int i = 1; i <= 3; i++) {
			System.out.print("JAVA"+i+(i!=3?" ":"\n"));
		}		
		
		int y = 1;
		while(y <= 3) {
			System.out.print("JAVA"+y +(y!=3?" ":"\n"));
			y++;
		}
		
		y = 1;
		do {
			System.out.print("JAVA"+y +(y!=3?" ":"\n"));
			y++;
		}while(y <= 3);
	}
}
/*
1.  for , while , do while문을 이용해서 다음과 같이 출력하시오 :  1 2 3 4 5
2.  for , while , do while문을 이용해서 다음과 같이 출력하시오 :  5 4 3 2 1
3.  for , while , do while문을 이용해서 다음과 같이 출력하시오 :  JAVA1   JAVA2  JAVA3
*/