package com.the703.basic010;

import java.awt.Color;
import java.awt.color.ColorSpace;

//1. final 변경하지마
//1) 클래스는 부품객체
//2) 클래스(상속:x)는 상태(멤버 변수:상수)와 행위(멤버 함수:)
/* final class : 재사용하지마 - 상속 */
class FinalEx extends Object {
	 static final String child = "5-5"; // 클래스 변수 - method area - new X - this X
	 String name; // 인스턴스 변수 - heap area - new o - 생성자() - this
	 /* final */ void show() { System.out.println(child + "\t" + name); } // 인스턴스 메서드
}
class FinalExSon extends FinalEx {
	@Override
	void show() { System.out.println("나한테 맞게 수정"); } // 인스턴스 메서드	
}
//class Test extends Color {}
public class Class006_Final {
	public static void main(String[] args) {
			//FinalEx.child = "5-12"; // 상수로 바뀌어서 할당 못함
	}
}
/*
final (하지마)

1. 클래스 (상속x / 재사용x / extends 사용 못함)
2. 멤버변수 (상수o / 값 변경x)
3. 멤버함수 (부모기능 수정 x / @Override 못함)
 */
