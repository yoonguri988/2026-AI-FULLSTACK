package com.the703.basic012_ex;
/*
//Q1. 상속도 그리기
//Q2. 각클래스에서 사용할수있는 멤버변수/멤버메서드
  Object {                  }
    ↑
   Papa  {money=10000, sing(){GOD-거짓말}}
    ↑
   Son   {money=1500, @sing(){빅뱅-거짓말}}
 */
class Papa extends Object {
	int money = 10000;

	public Papa() { super(); }
	public void sing() { System.out.println("GOD-거짓말"); }
}// end class

class Son extends Papa {
	int money = 1500;

	public Son() { super(); }
	@Override public void sing() { System.out.println("빅뱅-거짓말"); }
} // end class

public class PolyEx001 {
	public static void main(String[] args) {
		//부모       = 자식 ( 업 캐스팅 )
		Papa mypapa = new Son();
		// Q3. Papa mypapa 의미?
		// - {money=10000, sing()} 사용할 수 있게 해드림.
		// Q4. 인스턴스화한 실제 메모리 빌려온그림 - new Son()
		// Son() → Papa() → Object() / /생성자 호출 순서 
		// 1번지: {money=1500, @sing(){빅뱅-거짓말}} - {money=10000, sing(){GOD-거짓말}}
		// mypapa = 1번지
		// ---->>>
		//{money=10000, sing()} = 
		//                 1번지: {money=1500, @sing(){빅뱅-거짓말}} - {money=10000, sing(){GOD-거짓말}}
		/*
		------------------------------------------
		Papa    {money=10000, sing()}                         ← 1번지 {money=10000, sing()}
		new Son {money=1500, @sing()} - {money=10000, sing()} ← 1번지 
		mypapa 보장 {money=10000, sing()} [1번지] = [1번지] {money=1500, @sing()} - {money=10000, sing()}
		------------------------------------------
		 */
		System.out.println(mypapa.money); // Q5. 출력
		mypapa.sing(); // Q6. 출력
		// Q7. mypapa.money 를 이용해서 1500 출력되게 해주세요.
		System.out.println(((Son) mypapa).money);
	}
}
/*

*/