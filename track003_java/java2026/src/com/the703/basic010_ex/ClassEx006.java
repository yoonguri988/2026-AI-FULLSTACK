package com.the703.basic010_ex;

class Score{
	String stdid; 
	int kor,eng,math,total;
	double avg;
	
	public Score() {}
	
	public Score(String stdid, int kor, int eng, int math) {
		this.stdid = stdid;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	void total() {
		this.total = kor + eng + math;
	}
	
	void avg() {
		this.avg = this.total / 3.0;
	}
	
	void info() {
		total();
		avg();
		System.out.printf("학번\tkor\teng\tmath\ttotal\tavg\n"
				        + "%s\t%d\t%d\t%d\t%d\t%.2f\n"
				        , stdid, kor, eng, math, total, avg);
	}
}

public class ClassEx006 {
	public static void main(String[] args) {
		Score  s1= new Score("std1234" , 100, 100 , 99 ); 
		s1.info();
	}
}
/*
	연습문제4)  class
	패키지명 : com.the703.basic010_ex
	클래스명 :  ClassEx006
	-- 생성자 작성하시오.
	class Score{
	   //상태-멤버변수  :  String stdid; int kor,eng,math,total,avg;   
	   //행위-멤버함수  :  void total() 총점구해주기
	   //               void avg()  평균구하기
	   //                      void info()   학생정보출력  ※힌트2)  info(){    total();  avg();     }  다른메서드에서 메서드 사용가능  
	   //※ 힌트1) 생성자 :   Score() / Score(stdid, kor, eng, math)
	}
	public class ClassEx006{
	   public static void main(String[] args) {
	   Score  s1= new Score("std1234" , 100, 100 , 99 ); 
	   s1.info();
	   }
	}
	
	출력내용 :
	학번   kor   eng   math   total   avg
	std1234   100   100   99   299   99.67
*/