package com.the703.basic006_ex;

import java.util.Scanner;

public class ForIn001 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//변수
		String stdId="", level="가", pass="불합격", best="";
		int kor=-1, eng=-1, math=-1, tot=-1;
		double avg = 0.0;
		
		//입력
		System.out.print("학번 입력 >");
		stdId = sc.next();
		
		//ver1
//		for(;kor < 0 || kor > 100;) {
//			System.out.println("국어점수 입력(0~100만입력받기) >");
//			kor = sc.nextInt();
//		}
//		for(;eng < 0 || eng > 100;) {
//			System.out.println("영어점수 입력(0~100만입력받기) >");
//			eng = sc.nextInt();
//		}
//		for(;math < 0 || math > 100;) {
//			System.out.println("수학점수 입력(0~100만입력받기) >");
//			math = sc.nextInt();
//		}
		
		//ver2
		for(;math < 0 || math > 100;) {
			if(kor < 0 || kor > 100) {
				System.out.println("국어점수 입력(0~100만입력받기) >");
				kor = sc.nextInt();				
			} else if(eng < 0 || eng > 100) {
				System.out.println("영어점수 입력(0~100만입력받기) >");
				eng = sc.nextInt();
			} else {
				System.out.println("수학점수 입력(0~100만입력받기) >");
				math = sc.nextInt();				
			}
		}
		
		//ver3.
//		for(int i = 0; i <= 2;) {
//	         
//		      if(i == 0) {   
//		         System.out.print("국어 점수 (0~100) 입력 > ");
//		         kor = sc.nextInt();
//		            if(kor > 100 || kor < 0 ) {
//		               System.out.println("0~100 사이값을 입력해주세요.\n");
//		               continue;
//		               }
//		            else {i++;}      }
//		      
//		      else if(i == 1) {
//		         System.out.print("영어 점수 (0~100) 입력 > ");
//		         eng = sc.nextInt();
//		            if(eng > 100 || eng < 0) {
//		               System.out.println("0~100 사이값을 입력해주세요.\n");
//		               continue;
//		            }
//		            else {i++;}
//		            //System.out.println(i);
//		      }
//		      
//		      else if(i == 2) {
//		      System.out.print("수학 점수 (0~100) 입력 > ");
//		         math = sc.nextInt();
//		            if(math > 100 || math < 0) {
//		               System.out.println("0~100 사이값을 입력해주세요.\n");
//		               continue;
//		            }
//		            else {i++;}
//		            //System.out.println(i);
//		         }
//		      }
//		
		//처리
		// 1. 총점 구하기
		tot = kor + eng + math;
		// 2. 평균 구하기
		avg = tot/3.0;
		// 3. 평균이 60점이상이고  각과목이 40점 미만이면 아니라면 합격/ 아니면 불합격
		pass = avg < 60? "불합격": (kor < 40|| eng<40 || math<40)?"불합격":"합격";
		// 4. 평균이 95점이상이면 장학생
		best = avg < 95? "" : "장학생";
		// 5. 평균이 90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가
		switch((int)avg/10) {
			case 10: case 9: level ="수"; break;
			case 8: level = "우"; break;
			case 7: level = "미"; break;
			case 6: level = "양"; break;
		}
		
		//출력
		System.out.printf("=====================================================================\n"
		                  +"학번\t국어\t영어\t수학\t총점\t평균\t합격여부\t레벨\t장학생\n"
				          +"=====================================================================\n"
		                  +"%s\t%d\t%d\t%d\t%d\t%.2f\t%s\t%s\t%s\n"
				          ,stdId,kor,eng,math,tot,avg,pass,level,best);

	}
}
/*
	연습문제1)   
	패키지명 : com.company.java006_ex
	클래스명 : ForIn001
	출력내용 : 성적처리 프로그램입니다.
	
	0. 국어,영어, 수학(0~100만입력받기)  
	1. 총점 구하기
	2. 평균 구하기
	3. 평균이 60점이상이고  각과목이 40점 미만이면 아니라면 합격/ 아니면 불합격
	4. 평균이 95점이상이면 장학생
	5. 평균이 90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가 
	
	학번 입력 > std111
	국어점수 입력 > 100  
	수학점수 입력 > 100
	영어점수 입력 > 99
	=================================================================================== 
	학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생
	=================================================================================== 
	std111   100   100   99   299   99.67   합격   수   장학생
*/