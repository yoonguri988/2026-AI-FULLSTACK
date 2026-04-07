package com.the703.basic006_ex;

public class ForEx001 {
	public static void main(String[] args) {
		// q1  for문을 이용해서 다음과 같이 출력하시오 :   1 2 3 4 5
		for(int i = 1; i <= 5; i++) System.out.print(i != 5?i+" ":i+"\n");
		
		// q2  for문을 이용해서 다음과 같이 출력하시오 :   5 4 3 2 1 
		for(int i = 5; i >= 1; i--) System.out.print(i != 1?i+" ":i+"\n");

		// q3  for문을 이용해서 다음과 같이 출력하시오 :   JAVA1 , JAVA2 , JAVA3 
		for(int i = 1; i <= 3; i++) System.out.print("JAVA"+(i != 3?i+" ":i+"\n"));
		
		// q4  for문을 이용해서 다음과 같이 출력하시오 :   HAPPY3 ,HAPPY2, HAPPY1
		for(int i = 3; i >= 1; i--) System.out.print("HAPPY"+(i != 1?i+" ":i+"\n"));
		
		// q5  for문을 이용해서 다음과 같이 출력하시오 :   0,1,2
		for(int i = 0; i < 3; i++) System.out.print(i != 2?i+",":i+"\n");

		// q6  for문을 이용해서 다음과 같이 출력하시오 :   0,1,2, ,,,중간생략 ,,, 99  
		for(int i = 0; i < 100; i++) System.out.print(i != 99?i+",":i+"\n");
		
		// q7  for문을 이용해서 다음과 같이 출력하시오 :   10, 9,,,,중간생략 ,,, , 1 
		for(int i = 10; i > 0; i--) System.out.print(i != 1?i+",":i+"\n");
		
		// q8  for문을 이용해서 다음과 같이 출력하시오 :   0, 2, 4, 6, 8   
		for(int i = 0; i < 10; i+=2) System.out.print(i != 8?i+",":i+"\n");
		
		// q9  for문을 이용해서 다음과 같이 출력하시오 :   0, 2, 4, 6, 8 ,,,중간생략 ,,, 18 
		for(int i = 0; i < 20; i+=2) System.out.print(i != 18?i+",":i+"\n");
	}
}
/*
	 
	
	
 */