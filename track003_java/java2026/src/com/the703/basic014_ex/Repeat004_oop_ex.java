package com.the703.basic014_ex;

/*
📝 문제: OOP 개념(4) — 추상화 / 인터페이스
Q1. abstract란 무엇인가?  
Q2. interface란 무엇인가?  
Q3. abstract와 interface의 공통점과 차이점을 설명하시오.
Q4. 다음과 같이 출력되게 코드를 작성하시오.

      Fruit {myfruit}
  ↑            ↑           ↑
Apple       Banana      Coconut
{@myfruit}  {@myfruit}  {@myfruit} 


*/

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

public class Repeat004_oop_ex {
    public static void main(String[] args) {
        Fruit[] fruits = { new Apple(), new Banana(), new Coconut() };
        // 부모         = 자식들
        // 다형성: 한개의 자료형(부모)으로 여러개의 객체들(자식들)을 관리
        // {myfruit}   = {@myfruit}
        for (Fruit f : fruits) { f.myfruit(); }
    }
}
/* 
Q1. abstract란 무엇인가?  
-> abstract: 추상 클래스, 공통 기능을 일반화하여 자식 클래스가 구체적으로 구현하도록 강제한다.
   “IS-A” 관계: 고양이는 동물이다.


Q2. interface란 무엇인가?  
-> interface: 구현 객체를 통해 특정 기능을 수행할 수 있다는 약속을 정의한다.
   “CAN-DO” 관계: ~할 수 있다.

Q3. abstract와 interface의 공통점과 차이점을 설명하시오.
-> 공통점: 자식 클래스를 통해 설계 부분을 구현한다.
   차이점: 추상화 정도는 인터페이스가 더 높다
     추상 클래스: 일반 클래스 + 설계도, 멤버 변수/메서드 포함 가능.
     인터페이스 :             설계도, 멤버 변수는 상수(public static final)/추상메서드(public abstract)
*/
