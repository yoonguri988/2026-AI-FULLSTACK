package com.the703.basic014;

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

public class Repeat002_oop_ans {
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
📘 답: OOP 개념(2) — 캡슐화 / static / final
A1.
캡슐화: 데이터와 메서드를 하나로 묶고, 외부 접근을 제한하는 것.
구현: balance를 private으로 선언하고, deposit, withdraw, getBalance 메서드를 통해서만 접근 가능하다.

A2.
접근제어자 범위 (넓은 → 좁은):
public
protected
default(package-private)
private

A3.
static: 클래스 변수로 모든 객체가 공유한다.
메모리 구조: static 변수는 메서드 영역(Method Area)에 저장되며, 프로그램 시작 시 로드되고 객체 생성 여부와 관계없이 접근 가능하다.
적용: Account.accountCount

A4.
final: 한 번 초기화되면 변경 불가.
적용: Account.id
주의: 기본 생성자에서 id를 반드시 초기화해야 하며, 이 코드에서는 this.id = ++accountCount;로 초기화하여 컴파일 에러 없이 동작한다.

A5.
값: 2
이유: Account 객체가 2개 생성될 때마다 기본 생성자가 호출되어 accountCount++ 실행됨.

A6.
a1.id = 1
a2.id = 2

A7. 출력 결과

코드
Account [id=1, balance=150]
Account [id=2, balance=170]
총 계좌 수 = 2
a1.id = 1
a2.id = 2

A8.
static 메서드: 객체 생성 없이 호출 가능, 클래스에 속함.
인스턴스 메서드: 객체 생성 후 호출 가능, 객체에 속함.

A9.
final 키워드:
변수: 값 변경 불가(상수).
메서드: 오버라이딩 불가.
클래스: 상속 불가.

A10.
캡슐화 장점:
데이터 보호(외부 직접 접근 차단).
유지보수 용이(내부 구현 변경 시 외부 영향 최소화).
코드 안정성 향상.
내부 구현을 숨기고 인터페이스만 제공하여 사용자가 쉽게 활용 가능.
 */