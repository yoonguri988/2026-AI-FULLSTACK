package com.the703.basic009_ex;

public class MethodEx038 {
	public static void main(String[] args) {
		int[] arr = {1,3,9,7,5};
		System.out.println(maxArray(arr, arr.length-1));
	}

	public static int maxArray(int[] arr, int length) {
		if(length == 0) return arr[length];
		// arr의 위치 교환
		if(arr[length-1] < arr[length]) {
			int tmp = arr[length];
			arr[length] = arr[length-1];
			arr[length-1] = tmp;
		}
		return maxArray(arr, length-1);
	}
}
/*
 * 1. 배열의 합    2. 배열 역순출력   3. 배열정렬(재귀버블정렬) 4. 이진탐색(재귀)
*/