package com.the703.basic008_ex;

public class ArrayEx009 {
	public static void main(String[] args) {
		int[] su = {-3,5,-1,9,-7,2,-11};
		int sum = 0;
		
		for (int i = 0; i < su.length; i++) {
			if(su[i] > 0 && su[i] % 2 == 1) {
				sum += su[i];
			}
		}
		System.out.printf("양수 중에서 홀수의 합: %d\n",sum);
	}
}
/*
양수 중에서 홀수의 합
*/