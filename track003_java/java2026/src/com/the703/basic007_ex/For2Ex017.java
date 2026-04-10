package com.the703.basic007_ex;

public class For2Ex017 {
    public static void main(String[] args) {
    	int n = 25;
    	int sq = (int) Math.sqrt(n);
    	
//    	System.out.println(0);
//    	for(int i = 1; i <= 5; i++) {
//    		System.out.println(i);
//    	}
//    	System.out.println(1);
//    	for(int i = 10; i >= 6; i--) {
//    		System.out.println(i);
//    	}
//    	System.out.println(2);
//    	for(int i = 11; i <= 15; i++) {
//    		System.out.println(i);
//    	}
//    	System.out.println(3);
//    	for(int i = 20; i >= 16; i--) {
//    		System.out.println(i);
//    	}
    	
//    	int x = 2; //1,2,3,4
//    	System.out.println(x);
//    	for(int i = x*5+1; i <= x+1*5; i++) {
//    		System.out.println(i);
//    	}
//    	x = 3; //1,2,3,4
//    	System.out.println(x); //3
//    	for(int i = x+1*5; i >= x*5+1; i--) {
//    		System.out.println(i);
//    	} 
    	
    	//version-1
    	for(int i = 0; i < n; i++) {
    		int x = i / sq;
    		int y = i % sq;
    		System.out.printf("%d\t",(i / sq) % 2 == 0? x*sq+y+1: (x+1)*sq-y);
    		if(i%sq == sq-1) System.out.println(); 
    	}
    	
    }
}

/*
1 2 3 4 5
10 9 8 7 6
11 12 13 14 15
20 19 18 17 16
21 22 23 24 25
*/