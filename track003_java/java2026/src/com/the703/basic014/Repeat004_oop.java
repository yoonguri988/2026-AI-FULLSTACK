package com.the703.basic014;

abstract class Fruit {
	@Override public String toString() { return "Fruit "; }
	public abstract void myfruit();
}
class Apple extends Fruit{ 
	@Override public void myfruit() { System.out.println("사과는 빨갛다."); }
}
class Banana extends Fruit{ 
	@Override public void myfruit() { System.out.println("바나나는 노랗다."); }
}
class Coconut extends Fruit{ 
	@Override public void myfruit() { System.out.println("코코넛은 코코하다."); }
}
public class Repeat004_oop {
	public static void main(String[] args) {
	 	Fruit [] fruits = {new Apple() , new Banana() , new Coconut()};
	 	// 부모			= 자식들
	 	// 한개의 자료형(부모)으로 여러개의 객체들(자식들)을 관리
	 	Fruit fruit1 = new Apple();
	 	Fruit fruit2 = fruits[0];
	 	for(Fruit f: fruits) {   f.myfruit(); } 
	}
}
/* 
Fruit (------------)
↑	    ↑	    ↑
Apple  Banana  Coconut -  myfruit() - @Override

*/
 

/*
📝 문제: OOP 개념(4) — 추상화 / 인터페이스
Q1. abstract란 무엇인가? 
-> 추상화
 
Q2. interface란 무엇인가?
-> 
  
Q3. abstract와 interface의 공통점과 차이점을 설명하시오.
->

Q4. 다음과 같이 출력되게 코드를 작성하시오.
1) 출력 화면:
사과는 빨갛다.
바나나는 노랗다.
코코넛은 코코하다.

2) 상속도:

코드
     Fruit
↑       ↑       ↑
Apple  Banana  Coconut

3) 주어진코드
abstract class Fruit {
    @Override public String toString() { return "Fruit"; }
    public abstract void myfruit();
}
public class Abstract005 {
    public static void main(String[] args) {
        Fruit[] fruits = { new Apple(), new Banana(), new Coconut() };
        for (Fruit f : fruits) {
            f.myfruit();
        }
    }
}

-> class Apple extends Fruit {
    @Override void myfruit() { System.out.println("사과는 빨갛다."); }
  }


📘 답: OOP 개념(3) — 추상화 / 인터페이스
A1.
abstract: 추상 클래스, 공통 기능을 일반화하여 자식 클래스가 구체적으로 구현하도록 강제한다.
“IS-A” 관계: 고양이는 동물이다.

A2.
interface: 구현 객체를 통해 특정 기능을 수행할 수 있다는 약속을 정의한다.
“CAN-DO” 관계: ~할 수 있다.

A3.
공통점: 자식 클래스를 통해 설계 부분을 구현한다.
차이점:
추상 클래스: 일반 클래스 + 설계도, 멤버 변수/메서드 포함 가능.
인터페이스: 설계도만, 멤버 변수는 public static final 상수, 메서드는 public abstract 추상 메서드.
추상화 정도는 인터페이스가 더 높다.

A4. 코드 예시:
abstract class Fruit {
    @Override public String toString() { return "Fruit"; }
    public abstract void myfruit();
}

class Apple extends Fruit {
    @Override public void myfruit() { System.out.println("사과는 빨갛다."); }
}

class Banana extends Fruit {
    @Override public void myfruit() { System.out.println("바나나는 노랗다."); }
}

class Coconut extends Fruit {
    @Override public void myfruit() { System.out.println("코코넛은 코코하다."); }
}

public class Abstract005 {
    public static void main(String[] args) {
        Fruit[] fruits = { new Apple(), new Banana(), new Coconut() };
        for (Fruit f : fruits) {
            f.myfruit();
        }
    }
}
*/