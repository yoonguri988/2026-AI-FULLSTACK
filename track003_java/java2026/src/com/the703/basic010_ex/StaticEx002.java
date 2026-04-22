package com.the703.basic010_ex;

class Mobile2{  
    String   serialNo;
    static  int count=0;
    
	public Mobile2() {
		super();
		this.serialNo = "2030-" + ++count;
	}
	
	@Override
	public String toString() {
		return "Mobile2 [serialNo=" + serialNo + "]";
	}
	
} 

public class StaticEx002 {
	  public static void main(String[] args) {
		     Mobile2 m1 = new Mobile2(); //1. new 공간빌리기  2. 생성자()  3. 번지
		     Mobile2 m2 = new Mobile2(); 
		     Mobile2 m3 = new Mobile2();  
		     Mobile2 m4 = new Mobile2();  

		     System.out.println("모바일 갯수는 모두 "+ Mobile2.count +"개 입니다.");   
		     System.out.println("m1의 제품번호 " + m1.serialNo);  //1
		     System.out.println("m2의 제품번호 " + m2.serialNo);  //2
		     System.out.println("m3의 제품번호 " + m3.serialNo);  //3
		     System.out.println("m4의 제품번호 " + m4.serialNo);  //4
		  }
}
/*
연습문제2)  static
패키지명 : com.the703.basic010_ex
클래스명 :  StaticEx002
-- class Mobile2   작성해주세요    


class Mobile2{  
      String   serialNo;
      static  int count=0; 
} 

public class StaticEx002{
  public static void main(String[] args) {
     Mobile2 m1 = new Mobile2(); //1. new 공간빌리기  2. 생성자()  3. 번지
     Mobile2 m2 = new Mobile2(); 
     Mobile2 m3 = new Mobile2();  
     Mobile2 m4 = new Mobile2();  

     System.out.println("모바일 갯수는 모두 "+ Mobile2.count +"개 입니다.");   
     System.out.println("m1의 제품번호 " + m1.serialNo);  //1
     System.out.println("m2의 제품번호 " + m2.serialNo);  //2
     System.out.println("m3의 제품번호 " + m3.serialNo);  //3
     System.out.println("m4의 제품번호 " + m4.serialNo);  //4
  }
}

출력된결과:
모바일 갯수는 모두 4개 입니다.
m1의 제품번호 2030-1
m2의 제품번호 2030-2
m3의 제품번호 2030-3
m4의 제품번호 2030-4
*/