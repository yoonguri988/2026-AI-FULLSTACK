package com.the703.basic008_ex;

import java.util.Arrays;

public class ArrayEx014 {
	public static void main(String[] args) {
		int[] lotto = new int[6];
		
		for (int i = 0; i < lotto.length; i++) {
			lotto[i] = (int)(Math.random()*45)+1;
			int j = 0;
			while (j < lotto.length) {
				if(i!=j && lotto[i] == lotto[j]) {
					lotto[i] = (int)(Math.random()*45)+1;
					j = 0;
					System.out.println(j);
				}
				j++;
			}
		}
		System.out.println(Arrays.toString(lotto));
	}
}
/*
로또
1~45까지 겹치지 않게 6개 발생하게 만들어 로또번호 프로그램을 만드시오
*/