package com.the703.basic009_ex;

public class MethodEx004 {
	public static int fibo(int num) {
		if(num == 0) return 0;
		else if(num == 1) return 1;
		return fibo(num-1) + fibo(num-2);
	}
	
	public static void main(String[] args) {
		int n = 10; 
		int result = fibo(n);
		System.out.println(result);
	}
}
/*
 피보나치 수열.................
*/