package com.the703.basic010_ex;

class Sawon3 {
	// 인스턴스변수
	int pay = 10000;
	// 클래스변수
	static int su = 10;
	// 클래스변수
//	static int basicpay = pay; // 클래스 변수는 method area 영역에 미리 저장되는 정보이기 때문에 heap 영역에 저장하는 pay의 값을 사용불가
	// 클래스변수
	static int basicpay2;

	// 클래스메서드
	public static void showSu() {
		System.out.println(su);
	}

	// 클래스메서드
	public static void showPay() {
		// 클래스 메서드는 method area 영역에 미리 저장되는 정보이기 때문에 heap 영역에서 사용되는 this 사용불가
//		System.out.println(this.pay);
	}

	// 인스턴스메서드
	public void showAll001() { 
		System.out.println(su);
		System.out.println(this.pay);
	}

	// 클래스메서드
	public static void showAll002() {
		// 클래스 메서드는 method area 영역에 미리 저장되는 정보이기 때문에 heap 영역에서 사용되는 this 및 인스턴스메서드 사용불가
//		showAll001();
//		System.out.println(this.pay);
	}
}

public class MemberVarEx001 {
	public static void main(String[] args) {
		// 지역변수
		Sawon3 sola = new Sawon3();
		sola.showAll001();
	}
}
/*
-- class Sawon3작성해주세요 
1. 인스턴스변수, 클래스변수, 지역변수 를 구분하시오.
2. 인스턴스메서드, 클래스메서드 구분하시오.
3. 오류나는 이유는?
class Sawon3{ 
    int pay      =10000;    
    static int su=10;     
    static int basicpay=pay;    
    static int basicpay2;    
    
    public static void showSu() {   System.out.println(su);  }          
    public static void showPay() {   System.out.println(this.pay);  }    
  
    public  void  showAll001() {   
       System.out.println(su);  
       System.out.println(this.pay);  
    } 
    public static  void  showAll002() {   
        showAll001();    
       System.out.println(this.pay);
    } 
} 
public class MemberVarEx001{
  public static void main(String[] args) {
   Sawon3   sola = new Sawon3();  
   sola.showAll001();
  }
}

*/