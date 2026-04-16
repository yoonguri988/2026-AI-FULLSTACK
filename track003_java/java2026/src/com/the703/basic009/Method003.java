package com.the703.basic009;

public class Method003 {
	//1. 함수 구조 public static 리턴값(결과물) 메서드명(마법상자이름) (파라미터: 재료) {    할 일   }
	public static              int         hello            (String name) { return 1; } 
//	public static              void        hello            (String name) {           } 
	
	public static String sing() { return "용감한 자바 전사"; }

	public static String intro(String name, int level) { return name+"님의 레벨은 "+level;}
	public static double spell(int num) { return num / 2.0; }
	public static int luck() { return (int) (Math.random()*100)+1; }
	private static int sam(int raise) { return raise * 100;}
	/////////////////////////////////////////////
	public static void main(String[] args) {
		hello("aaa");
		System.out.println(hello("bbb"));
		// public static 리턴값 메서드명(파라미터) { 해야할 일 }
		System.out.println("1. 당신의 이름은? "+ sing()); // 
		System.out.println("2. 당신의 소개? "+ intro("홍길동", 9));
		System.out.println("2. 당신의 소개? "+ intro(sing(), 10)); // 용감한 자바전사님의 레벨은 10
		System.out.println("3. 반타작의 저주 > "+ spell(9)); //4.5
		System.out.println("4. 운세 > "+ luck());
		System.out.println("5. 주식 > "+ sam(1)); 
	}
	/////////////////////////////////////////////
}
