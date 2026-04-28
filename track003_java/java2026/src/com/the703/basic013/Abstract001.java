package com.the703.basic013;
/*
Abstract (is A) 일반 클래스 +  설계
	고양이도 동물이다
	개도    동물이다
	<<abstract>>
	   Animal {name / eat(), sleep(), poo()}
	↑           ↑
	Cat         Dog  { @eat(), @sleep(), @poo() }
 */
abstract class Animal { // 공통부분을 묶어 놓은 클래스
	String name; // 인스턴스 변수 - heap area - new O - this 각각
	
	abstract void eat();   // 추상메서드 {} 구현부가 없음.
	abstract void sleep(); // 추상메서드 추상클래스로 만들어줘야 함.
	abstract void poo();   // 추상화, 일반화, 설계목적: 공통의 속성, 구체적인 내용 없음
}
class Cat extends Animal { // 구현 클래스 - 고양이는 동물이다
	@Override void eat()   { System.out.println(super.name + "냥냥이 냠냠!"); }
	@Override void sleep() { System.out.println(super.name + "냥냥이 쿨쿨!"); }
	@Override void poo()   { System.out.println(super.name + "냥냥이 시원!"); }
}
class Dog extends Animal { // 구현 클래스 - 강아지는 동물이다
	@Override void eat()   { System.out.println(super.name + "댕댕이 냠냠!"); }
	@Override void sleep() { System.out.println(super.name + "댕댕이 쿨쿨!"); }
	@Override void poo()   { System.out.println(super.name + "댕댕이 시원!"); }
}

public class Abstract001 {
	public static void main(String[] args) {
		//1. abstract: 일반 클래스 + 설계
		//Animal ani = new Animal(); // new: 메모리빌리고 객체 생성, Animal 초기화 {} 구현부가 없음
		// Cannot instantiate the type Animal
		// abstract void eat(); 구현부가 미존재하기 때문에 실체화 할 수 없음.
		
		Animal ani = null; // {name, eat(), sleep(), poo()}
		ani = new Cat();   // 부모 = 자식, 업 캐스팅, 타입 캐스팅 필요 X
		ani.name = "sally";    ani.eat();
		
		ani = new Dog();
		ani.name = "alpha";    ani.eat();
		
		//2. 사용목적
		Animal[] arr = {new Cat(),new Cat(),new Dog(),new Dog()};
		int cnt = 0;
		for(Animal a : arr) { a.name = "ani"+ ++cnt; a.eat(); }
		// 각각 인스턴스에 이름 / eat() 자식꺼 오버라이드 (최신기능 사용 가능)
	}
}

/*
1. 추상화 (abstract)
- 실체화된 객체들 간 공통되는 특성의 추출
- 미완성적인 개념 (new 사용하지 못함)
- 공통된 필드와 메서드의 이름을 통일할 목적

2. 추상 클래스 

 */
