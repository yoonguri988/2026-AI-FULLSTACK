package com.the703.basic008_ex;

public class ArrayEx006 {
	public static void main(String[] args) {
		char[] ch = new char[52];
		int k = 0, count = 0;
		
		for(char i = 'A'; i <= 'Z'; i++) {
			ch[k++] = i;
		}
		for(char i = 'a'; i <= 'z'; i++) {
			ch[k++] = i;
		}
		
		for (int i = 0; i < ch.length; i++) {
			char alpha = ch[i];
			if(alpha=='a'||alpha=='e'||alpha=='i'||alpha=='o'||alpha=='u'
					||alpha=='A'||alpha=='E'||alpha=='I'||alpha=='O'||alpha=='U') {
				count++;
			}
		}
		System.out.printf("모음의 갯수: %d\n",count);
	}
}
/*
	 모음의 갯수 : a,e,i,o,u /  A,E,I,O,U
*/