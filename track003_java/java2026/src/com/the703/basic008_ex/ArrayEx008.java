package com.the703.basic008_ex;

public class ArrayEx008 {
	public static void main(String[] args) {
		int[] su = {-3,5,-1,9,-7};
		int count = 0;
		
		for (int i = 0; i < su.length; i++) {
			if(su[i] < 0) count++;
		}
		System.out.printf("음수의 갯수: %d\n",count);
	}
}
/*
음수의 갯수
*/