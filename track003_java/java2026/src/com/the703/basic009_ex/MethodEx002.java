package com.the703.basic009_ex;

public class MethodEx002 {
	public static void test1(int i) {
		System.out.println(i);
	}
	
	public static void test2(double dou) {
		System.out.println(dou);
	}
	
	public static void hap(int s, int e) {
		int result = 0;
		for (int i = s; i <= e; i++) {
			result += i;
		}
		System.out.println(result);
	}
	
	public static void disp(int num, char ch) {
		String result = "";
		for (int i = 0; i < num; i++) {
			result += ch;
		}
		System.out.println(result);
	}
	
	public static void main(String[] args) {
	    // public static  리턴값 메서드명(파라미터)
	    test1(10);    //10 출력
	     test2(1.2);   // 1.2 출력
	     hap(3,5);     // 3+4+5한값  12 출력
	     disp(7, '*');  // *******출력
	}

	
}
/*
	연습문제2)  method
	패키지명 : com.the703.basic009_ex
	클래스명 :  MethodEx002
	다음과 같이 test1(), test2(), hap(), disp()메서드를 정의하시오.
	
	public static void main(String[] args) {
	    // public static  리턴값 메서드명(파라미터)
	    test1(10);    //10 출력
	     test2(1.2);   // 1.2 출력
	     hap(3,5);     // 3+4+5한값  12 출력
	     disp(7, '*');  // *******출력
	}
*/