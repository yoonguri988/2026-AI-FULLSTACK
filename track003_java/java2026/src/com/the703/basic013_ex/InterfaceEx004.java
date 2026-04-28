package com.the703.basic013_ex;

class Barista {
   public void make(Drink drink) {
	   drink.serve();
	   if(drink instanceof Coffee) ((Coffee) drink).addSugar();
	   else if(drink instanceof Tea) ((Tea) drink).addLemon();
	   else if(drink instanceof Juice) ((Juice) drink).addIce();
   }
}

interface Drink {
	public void serve();
}

class Coffee implements Drink {
	@Override public void serve() { System.out.println("커피가 준비되었습니다."); }
	public void addSugar() { System.out.println("커피에 설탕을 넣습니다."); }
}

class Tea implements Drink {
	@Override public void serve() { System.out.println("차가 준비되었습니다."); }
	public void addLemon() { System.out.println("차에 레몬을 넣습니다."); }
}

class Juice implements Drink {
	@Override public void serve() { System.out.println("주스가 준비되었습니다."); }
	public void addIce() { System.out.println("주스에 얼음을 넣습니다."); }
}

public class InterfaceEx004 {
	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		Barista barista = new Barista();

		// 배열에 객체들을 담아둠
		// 부모       = 자식 {업 캐스팅}
		Drink[] menu = { new Coffee(), new Tea(), new Juice() };
		//               menu[0],      menu[1],   menu[2]

		while (true) {
			System.out.println("=== 카페 메뉴판 ===");
			System.out.println("1. 커피");
			System.out.println("2. 차");
			System.out.println("3. 주스");
			System.out.println("0. 종료");
			System.out.print("선택: ");
			int choice = sc.nextInt();

			if (choice == 0) {
				System.out.println("카페 주문을 종료합니다.");
				break;
			}

			if (choice >= 1 && choice <= menu.length) {
				Drink drink = menu[choice - 1]; // 배열에서 꺼내오기
				barista.make(drink);
			} else {
				System.out.println("잘못된 선택입니다.");
			}
			System.out.println();
		}
		sc.close();
	}
}
/*
 *         Drink { serve() }
 *  ↑               ↑            ↑
 *  Coffee          Tea          Juice
 *  {@serve()  }    {@serve()}   {@serve()}
 *  {addSugar()}    {addLemon}   {addIce  }        
 */
/*
1. Barista 클래스를 작성하시오.  
2. 주어진조건


3. 메인화면


4. 실행화면
=== 카페 메뉴판 ===
1. 커피
2. 차
3. 주스
0. 종료
선택: 1
커피가 준비되었습니다.
커피에 설탕을 넣습니다.

선택: 2
차가 준비되었습니다.
차에 레몬을 넣습니다.

선택: 3
주스가 준비되었습니다.
주스에 얼음을 넣습니다.

선택: 0
카페 주문을 종료합니다.
*/