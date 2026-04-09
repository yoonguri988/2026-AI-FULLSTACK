package com.the703.days;

import java.util.Scanner;

public class Day011 {
	public static void main(String[] args) {
	    char ch='\u0000';
	    Scanner scanner =new Scanner(System.in);

	    System.out.print("a,b,c 중에 입력 > ");  
	    ch = scanner.next().charAt(0);
	    
	    // if version
	    System.out.println("if version >>");
	    if(ch == 'a') System.out.println("apple");
	    else if(ch == 'b') System.out.println("banana");
	    else if(ch == 'c') System.out.println("coconut");
	    else System.out.println("a,b,c 가 아닙니다.");
	    
	    // switch version
	    System.out.println("switch version >>");
	    switch(ch) {
	    	case 'a': System.out.println("apple"); break;
	    	case 'b': System.out.println("banana"); break;
	    	case 'c': System.out.println("coconut"); break;
	    	default: System.out.println("a,b,c 가 아닙니다."); break;
	    }
	    
	    //for version
	    System.out.println("for version >>");
	    for (int i = 1; i <= 5; i++) {
			System.out.print(i+(i!=5?" ":"\n"));
		}
	    //while version
	    System.out.println("while version >>");
	    int x = 1;
	    while(x <= 5) {
	    	System.out.print(x+(x!=5?" ":"\n"));
	    	x++;
	    }
	    //do while version
	    System.out.println("do while version >>");
	    x = 1;
	    do {
	    	System.out.print(x+(x!=5?" ":"\n"));
	    	x++;
	    }while(x <= 5);
	}
}
/*
1. if버젼에 해당하는 다음에 연결해서 문제를 작성하시오.
    문자를 한개 입력받아 a이면 apple , b이면 banana, c이면 coconut
2. switch버젼에 해당하는 다음에 연결해서 문제를 작성하시오.
    문자를 한개 입력받아 a이면 apple , b이면 banana, c이면 coconut


    
3. for, while, do while 버젼으로  문제를 풀으시오!  
     1  2  3  4  5
*/