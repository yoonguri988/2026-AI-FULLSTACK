package com.the703.days;

import java.util.Scanner;

public class Day008 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//변수
		String stdId = "", level = "가", pass = "불합격", best = "";
		int kor = -1, math = -1, eng = -1, tot = -1;
		double avg = 0.0;
		
		//입력
		System.out.print("학번 입력 > ");
		stdId = sc.next();
		// 국어 점수는 0~100 사이만 입력 받게
		for(;kor < 0 || kor > 100;) {
			System.out.print("국어점수 입력 > ");
			kor = sc.nextInt();			
		}
		// 수학 점수는 0~100 사이만 입력 받게
		for(;math < 0 || math > 100;) {
			System.out.print("수학점수 입력 > ");
			math = sc.nextInt();
		}
		// 영어 점수는 0~100 사이만 입력 받게
		for(;eng < 0 || eng > 100;) {
			System.out.print("영어점수 입력 > ");
			eng = sc.nextInt();
		}
		
		//처리
		//1. 총점 구하기
		tot = kor + eng + math;
		//2. 평균 구하기
		avg = tot / 3.0;
		//3. 평균이 60점이상이고  각과목이 40점 미만이면 아니라면 합격/ 아니면 불합격
		pass = avg < 60? "불합격" : (kor < 40 || eng < 40 || math < 40)? "불합격" : "합격";
		//4. 평균이 95점이상이면 장학생
		best = avg < 95? "" : "장학생";
		//5. 평균이  90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가
		if(avg >= 90) {
			level = "수";
		}else if(avg >= 80) {
			level = "우";
		}else if(avg >= 70) {
			level = "미";
		}else if(avg >= 60) {
			level = "양";
		}
		
		//출력
		System.out.printf("=====================================================================\n"
		                  +"학번\t국어\t영어\t수학\t총점\t평균\t합격여부\t레벨\t장학생\n"
				          +"=====================================================================\n"
		                  +"%s\t%d\t%d\t%d\t%d\t%.2f\t%s\t%s\t%s\n"
				          ,stdId,kor,eng,math,tot,avg,pass,level,best);
	}
}
// 3. 필수조건
// q1-1 int형 변수 x가 60이상일때 조건식:                    x >= 60
// q1-2 char형 변수 ch가 'a' 또는 'A'일때 true인 조건식:     ch == 'a' || ch == 'A'
// q1-3 char형 변수 ch가 숫자('0'~'9')일때 조건식:          '0' <= ch && ch <= '9'
// q1-4 char형 변수 ch가 영문자(대문자) 일때 조건식:          'A' <= ch && ch <= 'Z'


/*  
  4.  eclipse 열어서 작성해주세요! [20분]

   패키지명 : com.the703.days
   클래스명 :  Day008
   출력내용 :  성적처리 프로그램입니다.
   1. 총점 구하기
   2. 평균 구하기
   3. 평균이 60점이상이고  각과목이 40점 미만이면 아니라면 합격/ 아니면 불합격
   4. 평균이 95점이상이면 장학생
   5. 평균이  90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가 

   학번 입력 > std111
   국어점수 입력 > 100
   수학점수 입력 > 100
   영어점수 입력 > 99
   ======================================================== 
   학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생
   ======================================================== 
   std111   100   100   99   299   99.67   합격   수   장학생
 */