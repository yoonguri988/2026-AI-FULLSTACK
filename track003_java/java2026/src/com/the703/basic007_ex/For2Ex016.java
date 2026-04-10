package com.the703.basic007_ex;

public class For2Ex016 {
    public static void main(String[] args) {
    	int n = 25;
    	
    	for(int i = 0; i < n; i++) {
    		System.out.print(i+1+(i%5 == 4?"\n":"\t"));
    	}
    }
}

/*
1 2 3 4 5
6 7 8 9 10
11 12 13 14 15
16 17 18 19 20
21 22 23 24 25
*/