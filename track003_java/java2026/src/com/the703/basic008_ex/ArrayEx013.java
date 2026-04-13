package com.the703.basic008_ex;

import java.util.Arrays;

public class ArrayEx013 {
	public static void main(String[] args) {
		int[] data = {1,3,1,1,4,4,3,1,4,1,2};
		int[] star = new int[4];
		String result = "";
		
		for (int i = 0; i < data.length; i++) {
			star[data[i]-1]++;
		}
		
		for (int i = 0; i < star.length; i++) {
			for(int j = 0; j < star[i]; j++) {
				result += '*';
			}
			result += '\n';
		}
		
		System.out.println(Arrays.toString(star));
		System.out.println(result);
	}
}
/*
별 누적
data = {1,3,1,1,4,4,3,1,4,1,2};
star = new int[4]
*/