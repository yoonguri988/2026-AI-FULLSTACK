package com.the703.basic010_ex;

class LunchTray {
	//인스턴스 변수
	String owner;
	//인스턴스 변수
	int rice = 90;
	//인스턴스 변수
	int soup = 85;

	//클래스 변수
	static int trayCount = 0;
	//클래스 변수
//	static int totalFood = rice + soup; // 클래스 변수는 method 영역에 저장되는 정보, heap영역에 저장되는 인스턴스 변수인 rice, soup가 저장되지 않았기 때문
	//클래스 변수
	static int maxRice = 100;

	// 생성자
	public LunchTray() {
		super();
		this.owner = "std-"+ ++trayCount;
	}

	//인스턴스 메서드
	public int getFoodAmount() {
		return rice + soup;
	}

	//클래스 메서드
	public static void showTrayCount() {
		System.out.println("전체 급식판 수: " + trayCount);
	}

	//클래스 메서드
	public static void showOwner() {
//		System.out.println(owner); // 클래스 변수는 method 영역에 저장되는 정보, heap영역에 저장되는 인스턴스 변수인 owner이 저장되지 않았기 때문
	}

	//인스턴스 메서드
	public void showTray() {
		System.out.println("\n\n:: 주인 이름: " + owner);
		System.out.println("총 음식량: " + getFoodAmount());
	}
}

public class MemberVarEx003 {
	public static void main(String[] args) {
		// 지역 변수
		LunchTray tray1 = new LunchTray();
		tray1.showTray();
		LunchTray.showTrayCount();

		// 지역 변수
		LunchTray tray2 = new LunchTray();
		tray2.showTray();
		LunchTray.showTrayCount();
	}
}
//////////////////////////////////////////////////////
/* [RUNTIME DATA AREA]
-------------------------------------------------------------------------
[METHOD:정보, static, final] LunchTray.class, MemberVarEx003.class
LunchTray.trayCount = 0; LunchTray.totalFood = 0; LunchTray.maxRice = 100;
LunchTray.showTrayCount(); LunchTray.showOwner();
-------------------------------------------------------------------------
[HEAP:동적]                                |  [STACK:지역]
2번지: {owner="std-2", rice=90, soup=85}   ←   tray2(2번지)
1번지: {owner="std-1", rice=90, soup=85}   ←   tray2(1번지)
                                              main#2
-------------------------------------------------------------------------
*/
//////////////////////////////////////////////////////

/*
연습문제3)  멤버변수
패키지명 : com.company.java010_ex
클래스명 :  MemberVarEx003
//- 문제 1. 다음 코드에서 인스턴스변수, 클래스변수, 지역변수를 구분하시오.  ( 보관되는 영역도 추가 )
//- 문제 2. 인스턴스메서드와 클래스메서드를 구분하시오.  
//- 문제 3. 오류가 발생하는 이유를 설명하시오.
//- 문제 4. runtime data area 위치영역 그림그리기
//- 문제 5. 다음과 같이 출력되도록 코드를 작성하시오.
:: 주인 이름: std-1
총 음식량: 175
전체 급식판 수: 1


:: 주인 이름: std-2
총 음식량: 175
전체 급식판 수: 2

 
class LunchTray {
    String owner;        
    int rice = 90;               
    int soup = 85;               

    static int trayCount = 0;      

    static int totalFood = rice + soup;

    static int maxRice = 100;       

    public int getFoodAmount() {
        return rice + soup;         
    }

    public static void showTrayCount() {
        System.out.println("전체 급식판 수: " + trayCount);   
    }

    public static void showOwner() { 
       System.out.println(owner);
    }

    public void showTray() {
        System.out.println("\n\n:: 주인 이름: " + owner);                
        System.out.println("총 음식량: " + getFoodAmount());     
    }
}


public class MemberVarEx003 {
   public static void main(String[] args) {
        LunchTray tray1 = new LunchTray();   
        tray1.showTray();                    
        LunchTray.showTrayCount();         

        LunchTray tray2 = new LunchTray();   
        tray2.showTray();                   
        LunchTray.showTrayCount();         
   }
} 
*/