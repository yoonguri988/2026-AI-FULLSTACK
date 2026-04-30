package com.the703.basic014;

import java.util.ArrayList;

/*  1. 클래스는 부품객체 
 *  2. 멤버변수 / 멤버함수
 *  Q2 상속도
 	Object						        Object(){#3  }#4
 	  ↑	
 	Papa     money=10		/--------   Papa(){#2    }#5
 	  ↑	
 	Son      money=150,car=2/toString   Son() {#1    }#6   @Override
 */


class Papa{
	int money=10;

	public Papa() { super(); }
	public Papa(int money) { super(); this.money = money; }
	@Override public String toString() { return "Papa [money=" + money + "]"; }
}
class Son extends Papa{
	int money=150;
	int car=2;
	
	public Son() { super(); }
	public Son(int money) { super(money); }
	public Son(int money, int car) { super(); this.money = money; this.car = car; }
	@Override public String toString() { return "Son [money=" + money + ", car=" + car + "]"; }
}

public class Repeat003_oop {
	public static void main(String[] args) {
		//Step1  Q3
		Papa p1 = new Papa();  //[money=1 ,toString] = 1번지 Papa(){money=10 / toString }
		System.out.println(p1);//Papa [money=10]
		
		//Step2 Q4
		Son s2 = new Son(); // Son s2 [money=150,car=2/toString]-[money=10/toString  ]
		// s2 = 2번지 {money=150,car=2/toString}-{money=10/------}
		System.out.println(s2);  //Son [money=150, car=2]
		
		//Step3  Q5
		//Son s3 = (Son) new Papa(); //Son s3 [money=150,car=2/toString]-[money=10/toString  ]
		//  s3	= 3번지                                            Papa(){money=10 / toString }	
		// error - Type mismatch: cannot convert from Papa to Son
		
		//Step4 Q6
		Papa p4 = new Son();                               //[money=10 ,toString]  
		//  s2 = 4번지 Son(){money=150,car=2/toString}- Papa(){money=10/------}  @Override
		System.out.println(p4);  //Son [money=150, car=2]
		System.out.println(p4.money); // money10
		
		//Step5  Q7
		Papa p5 = new Son();  //1. 부모 = 자식   / 업캐스팅 / 타입캐스팅필요 X
		Son  s5 = (Son) p5;   //2. 자식 = 부모   / 다운캐스팅/ 타입캐스팅필요 O / 부모는 자식생성자를 호출한적이 있어야 한다.
		//Son              s5 [money=150,car=2/toString]-[money=10/toString  ]
		//    p5 =  5번지 Son(){money=150,car=2/ toString}- Papa(){money=10 /------}
		System.out.println(          s5.money       );  //150
		System.out.println(  ((Papa)s5).money       );  //10
		System.out.println(  ((Papa)s5).toString()  );  //Son [money=150, car=2]
		 
		// Q8 오버로딩/오버라이딩이란?
		// 오버로딩   : 메서드의 이름을 같게, 알규먼트 자료형과 갯수를 다르게해 구분 / 비슷한기능
		// 오버라이딩 :  상속시 부모의 메서드를 자식에게 맞게 수정해서 사용 

		 
	}
}

/* 
## 📝 문제: OOP 다형성

다음 코드를 보고 질문에 답하시오.

Q1. 상속도를 그리시오.
Q2. 상속도에서 각 객체에서 사용할 수 있는 멤버변수와 멤버함수를 적으시오.

```
class Papa{
	int money=10;

	public Papa() { super(); }
	public Papa(int money) { super(); this.money = money; }
	@Override public String toString() { return "Papa [money=" + money + "]"; }
}
class Son extends Papa{
	int money=150;
	int car=2;
	
	public Son() { super(); }
	public Son(int money) { super(money); }
	public Son(int money, int car) { super(); this.money = money; this.car = car; }
	@Override public String toString() { return "Son [money=" + money + ", car=" + car + "]"; }
}
```


public class Repeat002_oop {
	public static void main(String[] args) {
		// Q3. Papa p1 = new Papa(); 실행 시 메모리 구조와 출력 결과를 설명하시오.
		// System.out.println(p1);의 결과는 무엇인가?
		Papa p1 = new Papa();   
		System.out.println(p1); 
		
		// Q4. Son s2 = new Son(); 실행 시 메모리 구조와 출력 결과를 설명하시오.
		// System.out.println(s2);의 결과는 무엇인가?
		Son s2 = new Son();   
		System.out.println(s2);   
		
		// Q5.Son s3 = (Son) new Papa(); 실행 시 어떤 문제가 발생하는가?
		Son s3 = (Son) new Papa();  
		
		// Q6. Papa p4 = new Son(); 실행 시 메모리 구조와 출력 결과를 설명하시오.
		// System.out.println(p4);의 결과는 무엇인가?
		// System.out.println(p4.money);의 결과는 무엇인가?
		Papa p4 = new Son();                               
		System.out.println(p4); 
		System.out.println(p4.money); 

		// Q7. p4에서 Son의 moeny를 사용하는 방법은? 
		 
	}
}


Q8 오버로딩/오버라이딩이란?




📘 답: OOP 다형성
A1.
Object
   ↑
 Papa
   ↑
 Son


A2.
Papa: money=10, toString()
Son: money=150, car=2, toString() (Papa의 toString을 오버라이딩)


A3.
Papa p1 = new Papa(); → Papa 객체 생성, money=10
출력: Papa [money=10]

A4.
Son s2 = new Son(); → Son 객체 생성, money=150, car=2
출력: Son [money=150, car=2]

A5.
Son s3 = (Son) new Papa(); → 런타임 에러(ClassCastException) 발생
이유: 실제 생성된 객체는 Papa인데, 이를 Son으로 강제 형변환하려 했기 때문


A6.
Papa p4 = new Son(); → Son 객체 생성, 참조 타입은 Papa
System.out.println(p4); → Son [money=150, car=2] (오버라이딩된 toString 호출)
System.out.println(p4.money); → 10 (참조 타입이 Papa이므로 Papa의 money 사용)

A7.
다운캐스팅 필요:

Son s4 = (Son) p4;
System.out.println(s4.money); // 150

A8.
오버로딩(Overloading): 같은 이름의 메서드를 매개변수 타입/개수로 구분하여 여러 개 정의하는 것.
오버라이딩(Overriding): 부모 클래스의 메서드를 자식 클래스에서 재정의하여 다른 동작을 수행하는 것.

*/