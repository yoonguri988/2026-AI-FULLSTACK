package com.the703.basic014_ex;
/*
	Q1. OOP란 무엇인가? 
	-> 객체 지향 프로그래밍, 틀 기반 프로그래밍
	
	A1. Object Oriented Programming
	    - 틀 기반 프로그래밍
	    - 부품객체(클래스) 구성(조립)해서 프로그래밍을 구성하는 방법
	    - 클래스는 부품객체
	    - 객체는 상태(속성, 멤버변수)와 행위(기능, 멤버메서드)
	
	Q2. OOP의 핵심 개념은 무엇인가?
	-> 캡다상추(캡슐화, 다형성, 상속, 추상화)
	
	A2. 캡슐화, 다형성, 상속, 추상화
	    - 캡슐화: 외부에서 직접 접근하지 못하도록 보호
	    - 다형성: 하나의 타입으로 여러개의 타입을 관리
	    - 상속 : 클래스의 재사용(재활용)
	    - 추상화: 핵심적인 기능을 단순화해서 하는 설계 (abstract vs interface)
	
	Q3. 상속도를 그리시오.
	Q4. 각 객체에서 사용할 수 있는 멤버변수와 메서드를 적으시오.
	
	A3, A4.
		Object {                }
		  ↑
		Parent {x=100, method()  → Parent method }
		  ↑
		Child  {x=200, @method() → Child method  }
	
	Q5. Parent p = new Child(); 실행 시 객체 생성, 초기화, 참조값을 설명하시오.
	Q6. Parent 클래스의 멤버변수/메서드의 종류와 메모리 구조를 설명하시오.
	
	// A5, A6, 
    // A5. new 객체 생성, 주소 / Child() 초기화 / p 주소값
    // method Area ( 정보, static, final) Parent.class, Child.class, Repeat001_oop.class #1
    // heap                                       stack
    // 
    // Child() → Parent() → Object() #4
    // Object(){                                 }
    // Parent(){x=100, method()  → Parent method }
    // Child() {x=200, @method() → Child method  }
    // 
    // new 1번지) Parent{x=100, method()} #3       ← p [1번지] #5           
    
	
	Q7. 출력 결과를 쓰시오.
	- p.x의 값은?  
	-> 100
	
	- p.method() 실행 시 어떤 메서드가 호출되는가?   
	-> Child method
	
	- c.x의 값은?  
	-> 200
	
	- c.method() 실행 시 어떤 메서드가 호출되는가?
	-> Child method
	
	Q8. @Override 키워드의 의미와 코드에서 오버라이딩된 부분을 설명하시오.
	-> 부모에게서 상속 받은 메서드를 자식이 가지고 있는 메서드로 덮어쓰겠다는 의미
	   void method() { System.out.println("Child method"); }
	   
	A8. 상속시 부모클래스의 메서드를 자식클래스에서 재정의
	    Parent 의 method()가 Child에서 오버라이딩
	
	Q9. Child c = new Child(); 호출 시 생성자 호출 순서와 객체 생성 순서를 설명하시오.
	Object #3{                }#4
	  ↑
	Parent #2{x=100, method() }#5
	  ↑
	Child  #1{x=200, @method()}#6
	-> 생성자 호출 순서 : Child, Parent, Object
	-> 객체 생성 순서  : Object, Parent, Child
	
	A9. 타입 사용할 수 있는 범위 Child {x=200, method()} - {x=100, method()} 
	    생성자 호출 순서 : Child → Parent → Object
	    객체 생성 순서  : Object → Parent → Child
	  
 */
class Parent {
    int x = 100; //인스턴스 - heap - new - 생성자 - this 각각
    void method() { System.out.println("Parent method"); } //인스턴스 - heap - new - 생성자 - this 각각
}

class Child extends Parent {
    int x = 200;
    void method() { System.out.println("Child method"); }
}

public class Repeat001_oop_ex {
    public static void main(String[] args) {
    	// Q5. Parent p = new Child(); 실행 시 객체 생성, 초기화, 참조값을 설명하시오.
    	// A5, A6.
    	// 1. new 객체 생성, 주소 / Child() 초기화 / p 주소값
    	/* method Area ( 정보, static, final) Parent.class, Child.class, Repeat001_oop.class #1
    	 * heap                                       stack
    	 * 
    	 * Child() → Parent() → Object() #4
    	 * Object(){                                 }
    	 * Parent(){x=100, method()  → Parent method }
    	 * Child() {x=200, @method() → Child method  }
    	 * 
    	 * new 1번지) Parent{x=100, method()} #3       ← p [1번지] #5           
    	 *                                              [main] #2
    	 */
        Parent p = new Child();
        Child c = new Child();
        // 타입 Child {x=200, method()} - {x=100, method()} 
        //    [2번지] {x=200, @method()} - {x=100, ------ } 

        // Q7. 출력 결과를 쓰시오.
        System.out.println("p.x = " + p.x); // 100
        p.method(); // Child method

        System.out.println("c.x = " + c.x); // 200
        c.method(); // Child method 
    }
}


/*
## 📝 문제: OOP 개념(1)

다음 코드를 보고 질문에 답하시오.
Q1. OOP란 무엇인가?
Q2. OOP의 핵심 개념은 무엇인가?
Q3. 상속도를 그리시오.
Q4. 각 객체에서 사용할 수 있는 멤버변수와 메서드를 적으시오.
Q5. Parent p = new Child(); 실행 시 객체 생성, 초기화, 참조값을 설명하시오.
Q6. Parent 클래스의 멤버변수/메서드의 종류와 메모리 구조를 설명하시오.
Q7. 출력 결과를 쓰시오.
- p.x의 값은?  
- p.method() 실행 시 어떤 메서드가 호출되는가?  
- c.x의 값은?  
- c.method() 실행 시 어떤 메서드가 호출되는가?

Q8. @Override 키워드의 의미와 코드에서 오버라이딩된 부분을 설명하시오.
Q9. Child c = new Child(); 호출 시 생성자 호출 순서와 객체 생성 순서를 설명하시오.
*/