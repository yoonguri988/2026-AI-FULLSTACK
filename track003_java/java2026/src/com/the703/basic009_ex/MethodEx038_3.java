package com.the703.basic009_ex;

import java.util.Arrays;

public class MethodEx038_3 {
	public static void main(String[] args) {
		int[] arr = {1,3,9,7,5};
		System.out.println(bubbleArray(arr, arr.length-1));
	}

	public static String bubbleArray(int[] arr, int length) {
		if(length == 0) return Arrays.toString(arr);
		// 버블 정렬
		for(int i = 0; i < length-1; i++) {
			if(arr[i] > arr[i+1]) {
				int tmp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = tmp;
			}
		}
		return bubbleArray(arr, length-1);
	}
}
/*
 * 3. 배열정렬(재귀버블정렬)
*/