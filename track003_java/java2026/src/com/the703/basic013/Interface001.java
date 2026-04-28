package com.the703.basic013;

class Papa { int brain; }
class Mama { int brain; }
//class Son extends Papa, Mama {} // 자바에서의 상속은 단일 상속

interface Animal2 {
	String company="(주) 메가스터디"; 
	// sf: static final - 공용: Animal2.company (클래스 변수) method area - new X - this X
	void eat(); // a:abstract {} 구현부가 없어서 - 구현 클래스에서 상세내용
}

class Saram implements Animal2{
	@Override public void eat() { 
		 // company = "kakao"; //The final field Animal2.company cannot be assigned
		System.out.println(Animal2.company + " 랍스타..냠냠");
	}
}

class Pig implements Animal2 {
	@Override public void eat() { 
		System.out.println(Animal2.company + " 꾸꾸리..냠냠");
	}
}

public class Interface001 {
	public static void main(String[] args) {
		//Animal2 ani = new Animal2(); 
		Animal2 [] anis = {new Saram(), new Saram(), new Pig()};
		
		for(Animal2 a: anis) { a.eat(); }
	}
}
/*
 * Animal2 {company="(주) 메가스터디" / eat()}
 * ↑                        ↑
 * Saram{@eat()}            Pig{@eat()}
 */

/*
1. interface
- 개발코드 변경 없이 객체를 바꿔낄 수 있는 역할

2. abstract(Is A: 고양이는 동물이다) vs interface (can do this)
- 추상화 정도가 interface가 더 높다
1) abstract  - 인스턴수 변수, 일반 메서드, 추상 메서드 가질 수 있으나
2) interface - 상수(public static final) + 추상 메서드(public abstract)

 3. 프로젝트 진행시 interface 사용하면
 다른 구성원들이 각각의 부분을 완성할 때까지 기다리지 않고
 규약만 정해놓고 본인부분만 작성
 
 4. 형식
 interface 인터페이스명 {
   상수;      //pubic static final
   추상메서드; //public abstract - {} this X
 }
 
 class 클래스명 implements 인터페이스명 {}
 
 class 클래스명 extends 클래스명 implements 인터페이스명1, 인터페이스명2... {}
 - 구현력이 없어서 다중상속이 가능
 */
