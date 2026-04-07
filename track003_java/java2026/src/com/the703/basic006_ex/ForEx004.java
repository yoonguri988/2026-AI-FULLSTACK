package com.the703.basic006_ex;

public class ForEx004 {
	public static void main(String[] args) {
		int cnt = 0;
		for(int i = 1; i <= 10; i++) if(i%3==0) cnt++;
		System.out.println(cnt+"개\n");
		
		//upgrade: 끝지점은 모르고 첫지점의 값을 알때
		cnt = 0;
		String result = "";
		for(int i = 1; i <= 10; i++) {
			if(i%3==0) {
				result += (cnt!=0?",":"3의배수 : ")+i;
				cnt++;
			}
		}
		System.out.println(result+"\n갯수: "+cnt+"개");

	}
}
/*
	1~10까지 3의 배수 갯수를 출력   
	
	upgrade)  시간나면 도전!
	3의배수 : 3,6,9    
	갯수 : 3개
*/