package com.the703.basic013_ex;

class Driver {
	public void drive(Vehicle vc) {
		if(vc instanceof MotorCycle) ((MotorCycle) vc).helmet();
		vc.run(); // 기본 다형성 동작 
	}
}

interface Vehicle { public void run(); }

class MotorCycle implements Vehicle {
	void helmet() { System.out.println("헬멧을 착용합니다."); }
	@Override public void run() { System.out.println("오토바이가 달립니다."); }
}

class Car implements Vehicle {
	@Override public void run() { System.out.println("자동차가 달립니다."); }
}

public class InterfaceEx002 {
	public static void main(String[] args) {
		Driver driver = new Driver();

		Car car = new Car();
		MotorCycle mo = new MotorCycle();

		driver.drive(car);
		driver.drive(mo);
	}
}
/*
      Vehicle {run()}          ←     Driver
     ↑               ↑    
MotorCycle	        Car
{@run()}            {@run()}

*/


/*
1. Driver 클래스를 작성하시오.  
2. 주어진조건
interface Vehicle {
   public void run();
} 
class MotorCycle implements Vehicle {
   @Override
   public void run() {
      System.out.println("오토바이가 달립니다.");
   }
}
class Car implements Vehicle {
   @Override
   public void run() {
      System.out.println("자동차가 달립니다.");
   }
}

3. 메인화면
public class InterfaceEx002{
   public static void main(String[] args) {
      Driver driver = new Driver();
      
      Car car = new Car();
      MotorCycle mo = new MotorCycle();
      
      driver.drive(car);
      driver.drive(mo);
   }
}

4. 실행화면
자동차가 달립니다.
오토바이가 달립니다.
*/