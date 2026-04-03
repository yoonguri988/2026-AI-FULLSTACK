package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx008_2 {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		// 변수
		String stNum = "";
		int kor = 0;
		int math = 0;
		int eng = 0;
		int sum = 0;
		double avg = 0.0;
		String pass = "불합격";
		String level = "가";
		String best = "";
		
		// 입력
		System.out.print("학번 입력 > ");
		stNum= sc.next();
		System.out.print("국어점수 입력 > ");
		kor= sc.nextInt();
		System.out.print("수학점수 입력 > ");
		math= sc.nextInt();
		System.out.print("영어점수 입력 > ");
		eng= sc.nextInt();
		
		//처리
		sum = kor + math + eng;
		avg = sum / 3.0;
		
		//ver1.
		// 1. 평균이 60점이상이고  각과목이 40점 미만이 아니라면(삼항 연산자)
		//if(avg >= 60 && kor >= 40 && math >= 40 && eng >= 40) {
 		//	pass = "합격";
 		//}
		
		//ver2.
		// pass = (avg >= 60 && kor >= 40 && math >= 40 && eng >= 40)?"합격":"불합격";
		pass = avg < 60? "불합격": (kor < 40 || math < 40 || eng < 40)? "불합격":"합격";
		
		// 2. 평균이 95점이상이면 장학생
		best =  avg < 95 ? "" : "장학생";
		
		// 3. 평균이  90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가
		//ver1.
		//if(avg >= 90) score = "수";
		//else if(avg >= 70) score = "미";
		//else if(avg >= 80) score = "우";
		//else if(avg >= 60) score = "양";
		
		//ver2.삼항 연산자
		level = avg >= 90? "수": avg >= 80? "우": avg >= 70? "미":avg >= 60?"양":"가";
		
		// 출력
		System.out.println("======================================================================\n"
		                  +"학번\t국어\t영어\t수학\t총점\t평균\t합격여부\t레벨\t장학생\n"
				          +"======================================================================");
		System.out.printf("%s\t%d\t%d\t%d\t%d\t%.2f\t%s\t%s\t%s\n",stNum, kor, eng, math, sum, avg, pass, level, best);
	}
}
/*
 * 패키지명 : com.the703.basic005_ex
클래스명 :  IfEx008
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
============================================= 
학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생
============================================ 
std111   100   100   99   299   99.67   합격   수   장학생
 * 
 */