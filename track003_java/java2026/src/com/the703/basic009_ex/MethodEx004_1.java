package com.the703.basic009_ex;

public class MethodEx004_1 {
	public static void main(String[] args) {
		int n = 10; 
		int num1 = 0, num2 = 1, result = 0;
		
		if(n == 0) result = num1;
		if(n == 1) result = num2;
		for(int i = 2; i <= n; i++) {
			result = num1 + num2;
			
			num1 = num2;
			num2 = result;
		}
		System.out.print(result);
	}
}
/*
 피보나치 수열.................
*/