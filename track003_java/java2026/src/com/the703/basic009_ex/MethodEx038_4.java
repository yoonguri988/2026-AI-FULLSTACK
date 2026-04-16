package com.the703.basic009_ex;

import java.util.Arrays;

public class MethodEx038_4 {
	public static void main(String[] args) {
		int[] arr = {1,3,9,7,5};
		System.out.println(binaryArray(arr, 0, arr.length-1));
	}

	public static String binaryArray(int[] arr, int start, int length) {
		if(length == start) return Arrays.toString(arr);
		// 이진 탐색
		int mid = start + (length-start)/2;
		
		for(int i = start; i < length; i++) {
			if(arr[i] > arr[i+1]) {
				int tmp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = tmp;
			}
		}
		
		if(arr[start] < arr[mid]) {		
			return binaryArray(arr, mid, length);		
		}else {
			return binaryArray(arr, start, mid);		
		}
	}
}
/*
 * 4. 이진탐색(재귀)
*/