package com.the703.basic018;

import java.awt.Toolkit;

//1. 프로세스: 실행중인 프로그램
//2. 프로세스 구성: 자원(데이터) + 쓰레드(실제작업수행)
//3. 작업수행 클래스: Thread(상속-run-start), Runnable
class PigSound extends Thread{//#1
	@Override public void run() {//#2 해야할 일 
		try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
		for (int i = 0; i < 5; i++) {
			System.out.print("꿀");
			try { Thread.sleep(1000); } // 1000 = 1초 쉬기 
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}

class PigCnt extends Thread {
	@Override public void run() {
		try { Thread.sleep(15); } catch (InterruptedException e) { e.printStackTrace(); }
		for (int i = 0; i < 5; i++) {
			System.out.print(" >"+(i+1)+"마리 ");
			try { Thread.sleep(1000); } // 1000 = 1초 쉬기 
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}

class PigTest extends Thread {
	@Override public void run() {
		try { Thread.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
		Toolkit toolkit = Toolkit.getDefaultToolkit(); //import java.awt.Toolkit;
		
		for (int i = 0; i < 5; i++) {
			toolkit.beep();
			try { Thread.sleep(1000); } // 1000 = 1초 쉬기 
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}

public class Thread002 {
	public static void main(String[] args) {
		Thread sound = new PigSound();
		Thread count = new PigCnt();
		Thread beep = new PigTest();
		sound.start();
		count.start();
		beep.start();
		for (int i = 0; i < 5; i++) {
			System.out.print("˙Ꙫ˙");
			try { Thread.sleep(1000); } // 1000 = 1초 쉬기 
			catch (InterruptedException e) { e.printStackTrace(); }
		}
		
	}
}
/*
Thread
1. 프로세스   : 실행중인 하나의 프로그램
2. 멀티프로세스: 동시에 여러 프로세스 실행
3. 구성
  - 자원(Resource) + Thread(자원으로 실제 작업 수행)
  - 모든 프로세스는 최소한 하나의 스레스 (main)
  - 같은 프로세스 내의 스레드들은 서로 자원을 공유
  //˙Ꙫ˙ ˙Ꙫ˙ ˙Ꙫ˙ ˙Ꙫ˙ ˙Ꙫ˙ 
  // 꿀   꿀   꿀   꿀   꿀
  
*/