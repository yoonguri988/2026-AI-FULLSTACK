package com.the703.basic008_ex;

public class ArrayEx011 {
	public static void main(String[] args) {
		int[] su = {-3,-5,-1,-9,-7};
		int max = -99999, min = 99999;
		
		for (int i = 0; i < su.length; i++) {
			int num = su[i];
			if(max < num) max = num;
			if(min > num) min = num;
		}
		System.out.printf("최댓값: %d, 최솟값: %d\n",max, min);
	}
}
/*
최댓값 최솟값 구하기
*/