package com.the703.basic008;

public class Arr2002 {
	public static void main(String[] args) {
		// 1. 값을 알고 있는 경우
		int[][] arr2 = {
				{1,2,3}, // 00 01 02
				{4,5,6}, // 10 11 12
		};
		
		// 2. 값을 모르는 경우
		int[][] arr21 = new int[2][3]; 
		// new 메모리 빌려오기 int(자료형) 2층 3칸
		
		arr21[0][0] = 10; arr21[0][1] = 20; arr21[0][2] = 30;
		arr21[1][0] = 40; arr21[1][1] = 50; arr21[1][2] = 60; // for 문으로 줄이기
		
		int k = 1;
		for (int i = 0; i < arr21.length; i++) {
			for (int j = 0; j < arr21[i].length; j++) {
				arr21[i][j] = 10 * k++; 
			}
		}
		
		
		for (int ch = 0; ch < arr21.length; ch++) { // 배열의 층수
			for (int kan = 0; kan < arr21[ch].length; kan++) { // 배열[층]의 칸수
				System.out.print(arr21[ch][kan]+"\t");
			}
			//2-3 한 층이 끝나면 해야할 일
			System.out.println();
			
		}
		
		
	}
}
