package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx007Final {

   public static void main(String[] args) {

	   /* 코드리뷰 - 김주엽님 */
	   
	   /*연습문제7)  ※ 숙제
	   패키지명 : com.company.java004_ex
	   클래스명 :  IfEx007
	   출력내용 :  계산기
	
	   1. 정수를 하나 입력해주세요 > 10
	   2. 정수를 하나 입력해주세요 > 3
	   3. 연산자를 입력해주세요(+,-,*,/) > +
	   10+3=13*/
      
      int num1 , num2 = 0;
      double result = 0.0;
      char ch = '\u0000';
      
      Scanner sc = new Scanner(System.in);
      
      System.out.println("정수를 하나 입력해주세요 > ");
      num1 = sc.nextInt();
      
      System.out.println("정수를 하나 입력해주세요 > ");
      num2 = sc.nextInt();
      
      System.out.println("연산자를 입력해주세요 (+,-,*,/) > ");
      ch = sc.next().charAt(0);
      
      if(ch == '+') {
         result = (num1 + num2);
      }
      else if(ch == '-') {
         result = num1 - num2;
      }
      else if(ch == '*') {
         result = num1 * num2;
      }
      else if(ch == '/') {
         result = ((double)num1 / num2);
      }

//      System.out.printf(ch == '/' ? "%d %s %d = %.2f" :
//          "%d %s %d = %.0f", num1, ch, num2, result);
      System.out.printf("%d %s %d = " + (ch == '/' ? "%.2f" : "%.0f" ), num1, ch, num2, result);

   }
   
}