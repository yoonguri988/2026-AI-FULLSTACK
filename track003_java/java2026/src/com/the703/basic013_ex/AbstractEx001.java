package com.the703.basic013_ex;

abstract class Robot{
	private String model;
	private int battery;

	public String getModel() { return model; }
	public void setModel(String model) { this.model = model; }
	public int getBattery() { return battery; }
	public void setBattery(int battery) { this.battery = battery; }
	
	abstract void charge();
	abstract void move();
	abstract void speak();

}
class CleaningRobot extends Robot {
	String name = "청소로봇";
	@Override void charge() { System.out.println(super.getModel()+" "+this.name + " 충전 중... 배터리 "+super.getBattery()+"%"); }
	@Override void move() { System.out.print(super.getModel()+" "+this.name+" : 청소 중"); }
	@Override void speak() { System.out.println(super.getModel()+" "+this.name + ": 먼지를 제거합니다!"); }
}
class SecurityRobot extends Robot {
	String name = "경비로봇";
	
	@Override void charge() { System.out.println(super.getModel()+" "+this.name + " 태양광 충전 중... 배터리 "+super.getBattery()+"%"); }
	@Override void move() { System.out.print(super.getModel()+" "+this.name+" : 경비 중"); }
	@Override void speak() { System.out.println(super.getModel()+" "+this.name + ": 이상 없음. 안전 확보!"); }
}
class CookingRobot extends Robot {
	String name = "요리로봇";
	
	@Override void charge() { System.out.println(super.getModel()+" "+this.name + " 인덕션 충전 중... 배터리 "+super.getBattery()+"%"); }
	@Override void move() { System.out.print(super.getModel()+" "+this.name+" : 요리 중"); }
	@Override void speak() { System.out.println(super.getModel()+" "+this.name + ": 오늘의 메뉴는 파스타입니다!"); }
}
public class AbstractEx001 {
	public static void main(String[] args) {
		// Robot robot = new Robot(); // Q1.why? 오류이유?
		// 추상 클래스는 인스턴스 객체를 만들 수 없다.
		System.out.println("\n--- 로봇 배열 시뮬레이션 ---");
		Robot[] bots = { new CleaningRobot(), new SecurityRobot(), new CookingRobot() };
		int[] levels = { 50, 70, 95 };
		
		for (int i = 0; i < bots.length; i++) {
			bots[i].setModel("Robo"+(i+1));
			bots[i].setBattery(levels[i]);
			
			bots[i].charge();
			bots[i].speak();
		}

	}
}
/*
1) 상속도
Object
  ↑
 Robot {   abstract charge() , move() , speak() }
↑          ↑               ↑ 
CleaningRobot  SecurityRobot   CookingRobot   
{@charge() ,    {@charge() ,    {@charge() ,
@ move() ,     @move() ,           @move() , 
@ speak() }}         @speak() }}          @speak() }}

2)main
public class AbstractEx001 {
public static void main(String[] args) {
//Robot robot  = new Robot();  // Q1.why? 오류이유?
System.out.println("\n--- 로봇 배열 시뮬레이션 ---");
Robot [] bots = { new CleaningRobot(), new SecurityRobot() , new CookingRobot() };
int[] levels = { 50, 70, 95 };

}
}

출력화면 :  
--- 로봇 배열 시뮬레이션 ---
Robo1 청소로봇 충전 중... 배터리 50%
Robo1 청소로봇: 먼지를 제거합니다!
Robo2 경비로봇 태양광 충전 중... 배터리 70%
Robo2 경비로봇: 이상 없음. 안전 확보!
Robo3 요리로봇 인덕션 충전 중... 배터리 95%
Robo3 요리로봇: 오늘의 메뉴는 파스타입니다!
*/