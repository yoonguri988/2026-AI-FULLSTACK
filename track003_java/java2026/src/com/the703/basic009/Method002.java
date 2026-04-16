package com.the703.basic009;

public class Method002 {
	// 1.        리턴값 메서드명 ( 파라미터: 재료 ) ★
	//                 hello( "sally" )
	public static void hello(String name) { // String name = "sally"
		System.out.println("Hello~ "+ name);
	}
	
	//                 icecream(   1   )
	public static void icecream(int num) {
		System.out.println("아이스크림 "+ num +" 개");
	}
	
	public static void info(String name, int score) {
		System.out.println(name + " 최종 "+ score+"점");
	}
	
	/////////////////////////////////////////////
	public static void main(String[] args) {
		hello("NOAH");
		hello("YEJUN");
		
		icecream(1);
		icecream(2);
		
		info("Bambi", 10); // Bambi 최종 10점
		info("Eunho", 9);  // Eunho 최종 9점
	}
	/////////////////////////////////////////////

}
