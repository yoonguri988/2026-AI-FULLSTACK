package com.the703.basic008_ex;

public class ArrayEx010 {
	public static void main(String[] args) {
		int[] su = {-3,-5,-1,-9,-7};
		int rank = 1;
		
		for (int i = 0; i < su.length; i++) {
			if(i != 4 && su[4] < su[i]) rank++;
		}
		System.out.printf("%d등\n",rank);
	}
}
/*
su[4] 요소의 등수 구하기
*/