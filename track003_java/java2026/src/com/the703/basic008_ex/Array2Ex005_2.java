package com.the703.basic008_ex;

import java.util.Arrays;

public class Array2Ex005_2 {

   public static void main(String[] args) {
//      연습문제5)  array
//   패키지명 : ccom.the703.basic008_ex
//   클래스명 :  Array2Ex005
//
//   2차원 배열 회전하기
//   다음과 같은 5×5 배열이 있습니다.
//    
//    1   2   3   4   5
//    6   7   8   9  10
//   11  12  13  14  15
//   16  17  18  19  20
//   21  22  23  24  25
      
      int count = 1;
      int [] [] num = new int[5][5];
      
      for(int i=0; i<num.length; i++) {
         for(int j=0; j<num[i].length; j++) {
            num[i][j] = count;
            count++;
         }
         System.out.println(Arrays.toString(num[i]));
      }
    
//   배열을 시계 방향으로 90도 회전해서 출력하시오.
//    
//   21 16 11  6  1  4,0 3,0 2,0 1,0 0,0 
//   22 17 12  7  2  4,1 3,1 2,1 1,1 0,1
//   23 18 13  8  3  4,2 3,2 2,2 1,2 0,2
//   24 19 14  9  4  4,3 3,3 2,3 1,3 0,3
//   25 20 15 10  5  4,4 3,4 2,4 1,4 0,4
      System.out.println();
      
      for(int i=0; i<num.length; i++) {
         for(int j=4; j>num.length-6; j--) {
            System.out.print(num[j][i]+" ");
         }
         System.out.println();
      }

      
//   배열을 반시계 방향으로 90도 회전하는 메서드를 작성하세요.
//    5 10 15 20 25 
//    4  9 14 19 24
//    3  8 13 18 23
//    2  7 12 17 22
//    1  6 11 16 21
      System.out.println();

      for(int i=4; i>num.length-6; i--) {
         for(int j=0; j<num.length; j++) {
            System.out.print(num[j][i]+" ");
         }
         System.out.println();
      }
      
//   배열을 180도 회전하는 메서드를 작성하세요.
//   25 24 23 22 21
//   20 19 18 17 16
//   15 14 13 12 11
//   10  9  8  7  6
//    5  4  3  2  1
      System.out.println();
      
      for(int i=4; i>num.length-6; i--) {
         for(int j=4; j>num.length-6; j--) {
            System.out.print(num[i][j]+" ");
         }
         System.out.println();
      }

   }

}
