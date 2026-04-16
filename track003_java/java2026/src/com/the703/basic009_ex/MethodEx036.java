package com.the703.basic009_ex;

import java.util.Arrays;

public class MethodEx036 {
	public static void main(String[] args) {
		int[] answer = new int[2];
		int n = 2, m = 5;
		int GCD = getGCD(Math.min(n, m), Math.max(n, m));
		answer[0] = GCD;
		answer[1] = n * m / GCD;
		System.out.println(Arrays.toString(answer));
	}

	public static int getGCD(int min, int max) {
		if(max % min == 0) return min;
		return getGCD(max%min, min);
	}
}
/*
*/