package com.the703.basic009_ex;

public class MethodEx038_2 {
	public static void main(String[] args) {
		int[] arr = {1,3,9,7,5};
		System.out.println(reverseArray(arr, arr.length-1));
	}

	public static int reverseArray(int[] arr, int length) {
		if(length == 0) return arr[length];
		// arr의 위치 교환
		System.out.print(arr[length]+(length != 0?" ":""));
		return reverseArray(arr, length-1);
	}
}
/*
 * 2. 배열 역순출력
*/