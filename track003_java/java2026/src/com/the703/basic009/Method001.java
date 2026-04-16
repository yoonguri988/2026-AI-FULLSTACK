package com.the703.basic009;

public class Method001 {
	
	//#1. 마법 상자( 함수, 메서드, function) - 원하는 기능을 해주는 box
	//아무나 접근    메모리상에 미리 업로드  결과물(리턴값) 마법상자이름(메서드명)  재료(파라미터)
	public         static            void        hello             ()
	{// 여기서 부터
		//원하시는 기능 해드림
		System.out.println("Hello");
	}// 여기 까지
	
	public static void line() {
		System.out.println("---------------");
	}
	
	///////////////////////////////////////////
	public static void main(String[] args) {
		//#2. 마법상자 사용하기 (마법상자이름을 부르면 { } 처리해줄께)
		hello();
		line();
		hello();
		line();
		hello();
		line();
	}
	///////////////////////////////////////////
}
