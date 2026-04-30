package com.the703.basic014_ex;

/*
📝 문제: OOP 개념(2) — 캡슐화 / static / final
Q1. 캡슐화(Encapsulation)란 무엇이며, 위 코드에서 어떻게 구현되었는지 설명하시오.
Q2. 접근제어자의 범위를 넓은 것부터 좁은 것까지 순서대로 쓰시오.
Q3. static 키워드의 의미를 메모리 구조와 연결지어 설명하고, 위 코드에서 어떤 변수에 적용되었는지 쓰시오.
Q4. final 키워드의 의미를 설명하고, 위 코드에서 어떤 변수에 적용되었는지 쓰시오.
Q5. Account.accountCount의 값은 얼마인가? 왜 그렇게 되는지 설명하시오.
Q6. a1.id와 a2.id의 값은 각각 얼마인가?
Q7. 출력 결과를 쓰시오.
Q8. static 메서드와 인스턴스 메서드의 차이를 설명하시오.
Q9. final 키워드가 변수, 메서드, 클래스에 각각 적용될 때 의미를 설명하시오.
Q10. 캡슐화의 장점은 무엇인가?
 */
// 1. 클래스는 부품객체
// 2. 상태(멤버변수:클래스변수, 인스턴스변수)와 행위(멤버함수:클래스메서드, 인스턴스메서드) 

/*   
Account a1 = new Account(1, 100);
Account a2 = new Account(2, 200);
-----------------------------------------------------------------------------
    [method(정보, static , final)] : Account.class /  Account.accountCount=1
   
   [heap]                        [stack]
                              
2번지:Account{balance=170,id=2}   ←   a2 (2번지)
1번지:Account{balance=150,id=1}   ←   a1 (1번지)                     
                              [main]
*/            


class Account {
    private int balance;              
    public static int accountCount=0; 
    public final int id;              

	public Account(){this.id = ++accountCount;}
    public Account(int id, int balance) {
        this();
        this.balance = balance; 
    }

    // getter/setter
    public int getBalance() { return balance; }
    public void deposit(int amount) { balance += amount; }
    public void withdraw(int amount) { balance -= amount; }

    @Override
    public String toString() {
        return "Account [id=" + id + ", balance=" + balance + "]";
    }
}

public class Repeat002_oop_ex {
    public static void main(String[] args) {
        Account a1 = new Account(1, 100);
        Account a2 = new Account(2, 200);

        a1.deposit(50);
        a2.withdraw(30);

        System.out.println(a1);  
        System.out.println(a2); 

        System.out.println("총 계좌 수 = " + Account.accountCount); 
        System.out.println("a1.id = " + a1.id); 
        System.out.println("a2.id = " + a2.id); 
    }
}

/*
📝 문제: OOP 개념(2) — 캡슐화 / static / final
Q1. 캡슐화(Encapsulation)란 무엇이며, 위 코드에서 어떻게 구현되었는지 설명하시오.
-> 캡슐화: 데이터와 메서드를 하나로 묶고, 외부 접근을 제한하는 것.
   구현: balance를 private으로 선언하고, deposit, withdraw, getBalance 메서드를 통해서만 접근 가능하다.

A1. 정의: 외부에서 접근 제어
    구현: balance를 private 선언, deposit, withdraw, getBalance 메서드를 통해서만 접근 가능 

Q2. 접근제어자의 범위를 넓은 것부터 좁은 것까지 순서대로 쓰시오.
-> public > protected > default(package) > private

A2. public (어디서든지) > protected (상속) > default(package-폴더) > private (클래스내부) 

Q3. static 키워드의 의미를 메모리 구조와 연결지어 설명하고, 위 코드에서 어떤 변수에 적용되었는지 쓰시오.
-> static : method 메모리에 파일 정보로 저장
-> public static int accountCount=0;

A3. 정의 : 모든 객체에서 공유 
    메모리: method area 저장 - 객체 생성과 관계없이 접근, 클래스명.변수명, 클래스명.메서드명
    적용 : Account.accountCount

Q4. final 키워드의 의미를 설명하고, 위 코드에서 어떤 변수에 적용되었는지 쓰시오.
-> final : 값을 변경하지마
-> public final int id;

A4. 의미 : 변경 불가, 클래스(상속X), 변수(상수), 메서드(오버라이드X)
    id, 한번 초기화되면 변경 불가, this.id = ++accountCount; 초기화

Q5. Account.accountCount의 값은 얼마인가? 왜 그렇게 되는지 설명하시오.
-> 2
-> 객체를 만들 때마다 값을 ++ 증가 시켰기 때문

A5. 값: 2
    Account.accountCount 클래스 변수
    Account 객체가 2개, 생성될때마다 기본생성자가 호출되어 ++accountCount 실행됨.

Q6. a1.id와 a2.id의 값은 각각 얼마인가?
-> a1.id = 1;, a1.id = 2;

A6. a1.id = 1
    a1.id = 2

Q7. 출력 결과를 쓰시오.

A7. Account [id=1, balance=150]
   Account [id=2, balance=170]
   
   총 계좌 수 = 2
   a1.id = 1
   a2.id = 2

Q8. static 메서드와 인스턴스 메서드의 차이를 설명하시오.
-> static 메서드는 method area, 파일의 정보로 저장되며, 생성자 X, this X
-> 인스턴스 메서드는 heap area, 생성자 O, this 각각

A8. static 메서드 - 객체 생성 없이 호출가능, 클래스에 속함
    인스턴스 메서드 - 객체 생성 후에 호출가능, 객체에 속함

Q9. final 키워드가 변수, 메서드, 클래스에 각각 적용될 때 의미를 설명하시오.
-> final 키워드:
   변수: 값 변경 불가(상수).
   메서드: 오버라이딩 불가.
   클래스: 상속 불가.
   
A9. 변수: 값 변경 불가(상수), 메서드: 오버라이딩 불가, 클래스: 상속 불가

Q10. 캡슐화의 장점은 무엇인가?
-> 데이터 보호(외부 직접 접근 차단).
   유지보수 용이(내부 구현 변경 시 외부 영향 최소화).
   코드 안정성 향상.
   내부 구현을 숨기고 인터페이스만 제공하여 사용자가 쉽게 활용 가능.

A10. 데이터 보호(외부 직접 접근 차단)
     유지보수 용이(내부 구현 변경시 외부 영향 최소화)
 */