package com.the703.basic008_ex;

import java.util.Arrays;

public class ArrayEx007 {
	public static void main(String[] args) {
		char[] ch = {'B' , 'a' , 'n' , 'a', 'n' , 'a'};
		
		for (int i = 0; i < ch.length; i++) {
			if('a' <= ch[i] && ch[i] <= 'z') {
				ch[i] = (char)(ch[i]-32);
			} else if('A' <= ch[i] && ch[i] <= 'Z') {
				ch[i] = (char)(ch[i]+32);
			}
		}
		System.out.println(Arrays.toString(ch));
	}
}
/*
대소문자를 서로 바꿔서 출력
*/