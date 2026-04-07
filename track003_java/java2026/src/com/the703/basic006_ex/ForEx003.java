package com.the703.basic006_ex;

public class ForEx003 {
	public static void main(String[] args) {
		int sum = 0;
		for(int i = 1; i <= 10; i++) sum += i;
		System.out.println(sum);
		
		// upgrade-1
		sum = 0;
		for(int i = 1; i <= 10; i++) {
			sum += i;
			System.out.print(i+(i!=10?"+":"="+sum+"\n"));
		}
		
		// upgrade-2
		sum = 0;
		for(int i = 1; i <= 10; i++) System.out.printf(i!=10?"%d+":"%d="+"%d\n", i, sum+=i);
	}
}
/*
	for 이용
	1~10까지의 합을 구하시오.
	
	upgrade)  시간나면 도전!
	1+2+3+4+5+6+7+8+9+10=55
*/