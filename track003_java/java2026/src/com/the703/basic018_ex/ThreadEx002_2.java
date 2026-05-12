package com.the703.basic018_ex;

class Candy3 {  
    String name;  
    public void sell() {  
        System.out.println(name + "가 1개 팔렸습니다.");  
    }  
}

class MentolSeller3 extends Candy implements Runnable {
	@Override public void run() {
		for (int i = 0; i < 20; i++) {  
			sell();
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }  
        } 
	}
}

public class ThreadEx002_2 {  
    public static void main(String[] args) {  
    	System.out.println(".................................... main start");
    	MentolSeller3 seller = new MentolSeller3();  
        seller.name = "멘톨캔디";  
        Thread t = new Thread(seller);  
        t.start();  

        for (int i = 0; i < 5; i++) {  
            System.out.println("  손님 기다리는 중.....");  
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }  
        }  
        System.out.println(".................................... main end");
    }  
}
/*

.................................... main start
  손님 기다리는 중.....
멘톨캔디가 1개 팔렸습니다.
  손님 기다리는 중.....
멘톨캔디가 1개 팔렸습니다.
  손님 기다리는 중.....
멘톨캔디가 1개 팔렸습니다.
  손님 기다리는 중.....
멘톨캔디가 1개 팔렸습니다.
  손님 기다리는 중.....
멘톨캔디가 1개 팔렸습니다.
.................................... main end
멘톨캔디가 1개 팔렸습니다.
멘톨캔디가 1개 팔렸습니다.

*/
