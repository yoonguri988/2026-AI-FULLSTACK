package com.the703.basic010_ex;

import java.util.Arrays;

interface Launch { // 점심 메뉴 주문을 인터페이스로 추상화
    int MONEY = 10000;
    void eat();
}

class Burger implements Launch{ // Burger 클래스는 각각의 메뉴 정보
    int price;

    public Burger() { super(); }
	public Burger(int price) {
		super();
		this.price = price;
	}

	@Override
	public void eat() {
	   System.out.println("햄버거를 먹는다.");
	}
	@Override
	public String toString() {
		return "Burger [price=" + price + "]";
	}
}

class KimchiStew implements Launch{ // KimchiStew 클래스는 각각의 메뉴 정보
    int price;

	public KimchiStew() { super(); }
	public KimchiStew(int price) {
		super();
		this.price = price;
	}

	@Override
	public void eat() {
		System.out.println("김치스튜를 먹는다.");
	}
	
	@Override
	public String toString() {
		return "KimchiStew [price=" + price + "]";
	}
}

class User {
	// List<Launch> list = new ArrayList<>();	// Array
	int n = 1;
	int k = 0;
	Launch[] arr = new Launch[3*n];
	
	public User() {
		super();
	}
	
	//메뉴를 주문하고
	public void order(Launch menu) {
		menu.eat();
		// array의 크기는 3, 6, 9, 12 ... 3의 배수로 늘어난다.
		if(k >= 3*n) {
			Launch[] copy = Arrays.copyOf(arr, k);
			arr = new Launch[3 * ++n];
			
			for (int i = 0; i < copy.length; i++) {
				arr[i] = copy[i];
			}
		}
		// 주문한 메뉴를 유저의 식사리스트에 추가
		arr[k++] = menu;
	}
	
	//주문 내역을 출력
	public void showReceipt() {
		System.out.println("=== 주문 내역 ===");
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != null) System.out.println(arr[i]);
		}
	}
}

public class InterfaceEx003 {
	public static void main(String[] args) {
		// 점심 메뉴 등록
		Launch lc1 = new Burger(5500);
		Launch lc2 = new KimchiStew(6000);
		
		// 사용자가 점심메뉴를 주문한다.
		User u1 = new User();
		u1.order(lc1);
		u1.order(lc2);
		u1.order(lc1);
		u1.order(lc2);
		u1.showReceipt();
	}
}
/*
1. 인터페이스를 활용한 점심 주문 시뮬레이션
다음은 점심 메뉴 주문을 인터페이스로 추상화한 프로그램이다. 

Launch 인터페이스는 모든 메뉴가 공통적으로 가져야 할 기능을 정의하며, 
Burger, KimchiStew 클래스는 이를 구현하여 각각의 메뉴 정보를 제공한다. 
User 클래스는 메뉴를 주문하고, 주문 내역을 출력하는 기능을 담당한다.

2. 주어진 조건
interface Launch {
    int MONEY = 10000;
    void eat();
}
class Burger      implements Launch{ 
   int price;
*/