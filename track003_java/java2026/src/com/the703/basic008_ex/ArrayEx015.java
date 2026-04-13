package com.the703.basic008_ex;

public class ArrayEx015 {
	public static void main(String[] args) {
		int[] mon = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		String[] yoil = {"일","월","화","수","목","금","토"};
		int year = 2020, month = 10, date = 13;
		boolean yoon = false; 
		int dateTot = 1;
	    int firstDay = 0;
		
		// 평년/윤년
		for(int i = 1; i < year; i++) {
			if(i % 4 == 0) {
				if (i % 100 == 0 && i % 400 != 0) {
					dateTot += 365;
				}else {
					dateTot += 366;
				}
			}else {
				dateTot += 365;
			}
		}
		
		yoon = year % 4 != 0? false: (year % 100 == 0 && year % 400 != 0)? true : false;
		for(int i = 1; i < month; i++) {
			if(yoon && i == 2) {
				 dateTot += 29;
				 continue;
			}
			dateTot += mon[i];
		}
		firstDay = (dateTot)%7;
		dateTot += date;
		
		//출력
		System.out.printf("* 서기 1년 ~ %d년 %d월 %d일 까지의 총 일수: %d\n",year,month, date, dateTot);
		System.out.printf("* %d년 %d월 %d일 %s요일\n",year,month, date, yoil[dateTot%7]);
		System.out.printf("====== %d월 ======\n", month);
		

		for(int i = 0; i <= mon[month]+firstDay+yoil.length; i++) {
			if(i/7 == 0) {
				// 요일 출력 부분
				System.out.print(yoil[i%7]+(i%7==6?'\n':'\t'));
			}else {				
				// 달력 출력 부분
				if(i/7 == 1 && i-yoil.length <= firstDay) {
						System.out.print("*\t");
						continue;
					}
					System.out.print((i-firstDay-yoil.length) + (i%7==6?"\n":"\t"));
				}
			}
		
//		for(int i = 0; i < yoil.length; i++) {
//			System.out.print(yoil[i]+(i==6?'\n':'\t'));
//		}	
//		for(int i = 0; i <= firstDay; i++) {
//			System.out.print("*\t");
//		}
//		for(int i = 1; i <= mon[month]; i++) {
//			System.out.print(i + (i%7==(6-firstDay)?"\n":"\t"));
//		}
		
	
	}
}
/*
  1. 서기 1년 ~ 2020년 10월 13일 까지의 총 일수
*/