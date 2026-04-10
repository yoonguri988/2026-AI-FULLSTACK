package com.the703.days;

import java.util.Scanner;

public class Day012 {
	public static void main(String[] args) {
		int avg = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("평균입력 > ");
		avg = scanner.nextInt();
		
		//1. if버젼에 해당하는 다음에 연결해서 문제를 작성하시오.
		//정수를 하나 입력받아 다음 조건에 따라 성적을 출력하는 프로그램을 작성하시오.
		if(avg >= 90) System.out.println("A 학점");
		else if(avg >= 80) System.out.println("B 학점");
		else if(avg >= 70) System.out.println("C 학점");
		else System.out.println("F 학점");
		
		//2. switch버젼에 해당하는 다음에 연결해서 문제를 작성하시오.
		//위의 문제를 switch 로
		switch(avg/10) {
			case 10: case 9: System.out.println("A 학점"); break;
			case 8: System.out.println("B 학점"); break;
			case 7: System.out.println("C 학점"); break;
			default: System.out.println("F 학점"); break;
		}
		
		//3. for, while, do while 버젼으로  문제를 풀으시오!  
		//1 2 3
		System.out.println("for >>");
		for(int i = 1; i <= 3; i++) {
			System.out.print(i+(i==3?"\n":" "));
		}
		//while
		System.out.println("while >>");
		int x = 1;
		while( x <= 3) {
			System.out.print(x+(x==3?"\n":" "));
			x++;
		}		
		//do while
		System.out.println("do while >>");
		x = 1;
		do{
			System.out.print(x+(x==3?"\n":" "));
			x++;
		}while( x <= 3);
		
		//4. 이중 for를 이용해서 다음문제를 풀으시오.
		//★★★★
		//★★★★
		//★★★★
		//★★★★
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print("★"+(j==3?"\n":""));
			}
		}
	}
}
/*
1. if버젼에 해당하는 다음에 연결해서 문제를 작성하시오.
정수를 하나 입력받아 다음 조건에 따라 성적을 출력하는 프로그램을 작성하시오.
 90점 이상 → "A 학점"
 80점 이상 → "B 학점"
 70점 이상 → "C 학점"
 그 외 → "F 학점"

2. switch버젼에 해당하는 다음에 연결해서 문제를 작성하시오.
위의 문제를 switch 로

 int avg=0;
 Scanner scanner = new Scanner(System.in);
 System.out.print("평균입력 > ");  avg = scanner.nextInt();
 
3. for, while, do while 버젼으로  문제를 풀으시오!  
 1 2 3

4. 이중 for를 이용해서 다음문제를 풀으시오.
★★★★
★★★★
★★★★
★★★★
*/