package com.the703.basic008;

import java.util.Arrays;

public class Arr2001 {
	public static void main(String[] args) {
		int[][] arr2 = {
				{1,2,3},
				{4,5,6},
		};
		System.out.println(arr2);
		System.out.println(Arrays.toString(arr2));
		
		//ver-1 눈에 보이는 대로
		System.out.print(arr2[0][0]+"\t");
		System.out.print(arr2[0][1]+"\t");
		System.out.print(arr2[0][2]+"\t"); System.out.println();
		
		System.out.print(arr2[1][0]+"\t");
		System.out.print(arr2[1][1]+"\t");
		System.out.print(arr2[1][2]+"\t"); System.out.println();
		
		System.out.println();System.out.println();
		
		//ver-2 칸 정리
		for(int kan = 0; kan < 3; kan++){
			System.out.print(arr2[0][kan]+"\t");
		}System.out.println();
		
		for(int kan = 0; kan < 3; kan++){
			System.out.print(arr2[1][kan]+"\t");
		}System.out.println();

		System.out.println();System.out.println();
		
		//ver-3 층 정리
		for(int ch = 0; ch < 2; ch++){
			for(int kan = 0; kan < 3; kan++){
				System.out.print(arr2[ch][kan]+"\t");
			}System.out.println();
		}

		System.out.println();System.out.println();
		
		//ver-length 이용
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length; j++) {
				System.out.print(arr2[i][j]+"\t");
			}System.out.println();
		}
	}
}
