package com.the703.basic009_ex;

public class MethodEx038_1 {
	public static void main(String[] args) {
		int[] arr = {1,3,9,7,5};
		System.out.println(sumArray(arr, arr.length-1));
	}

	public static int sumArray(int[] arr, int length) {
		if(length == 0) return arr[length];
		// arr의 위치 교환
		arr[length-1] += arr[length];
		return sumArray(arr, length-1);
	}
}
/*
 * 1. 배열의 합
*/