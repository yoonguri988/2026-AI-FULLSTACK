package com.the703.basic008_ex;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;


class ArrowKeyInput extends JFrame {
    public ArrowKeyInput() {
        setTitle("방향키 입력");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel("콘솔 창/이 창에서 방향키를 누르세요"));

        // 키 리스너 등록
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: System.out.println("위"); break;
                    case KeyEvent.VK_DOWN: System.out.println("아래"); break;
                    case KeyEvent.VK_LEFT: System.out.println("왼쪽"); break;
                    case KeyEvent.VK_RIGHT: System.out.println("오른쪽"); break;
                }
            }
        });
        setVisible(true);
    }
}

public class ArrayEx017 {
	public static void main(String[] args) {
		int[] mon = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		String[] yoil = {"일","월","화","수","목","금","토"};
		int year = 2020, month = 10, date = 13;
		int birthYear = 1990, birthMonth = 1, birthDate = 1;
		int dateTot = 1, livingDateTot = 0;
	    int firstDay = 0;
		
		// 평년/윤년
	    dateTot += calculateDate(year,month, date, mon);
	    livingDateTot = calculateDate(birthYear,birthMonth, birthDate, mon);
		
	    // 최종 날짜 계산
		firstDay = (dateTot)%7;
		dateTot += date;

		// 나 살아온날 최종 날짜 계산
		livingDateTot = dateTot - livingDateTot;
		
		//출력
		System.out.printf("* 서기 1년 ~ %d년 %d월 %d일 까지의 총 일수: %d\n",year,month, date, dateTot);
		System.out.printf("* 내 생일 : %d년 %d월 %d일\n", birthYear, birthMonth, birthDate);
		System.out.printf("* 살아온 날수: %d\n", livingDateTot);
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
		System.out.println();
		
		new ArrowKeyInput();
	}
	
	private static int calculateDate(int year, int month, int date, int[] monArr) {
		int result = 0;
		boolean yoon = false;

		for(int i = 1; i < year; i++) {
			if(i % 4 == 0) {
				result += (i % 100 == 0 && i % 400 != 0)? 365 : 366;
				yoon = (year % 100 == 0 && year % 400 != 0)? true: false;
			} else {
				result += 365;
			}
		}
		
		for(int i = 1; i < month; i++) {
			result += (yoon && i == 2)? 29 : monArr[i];
		}
		
		return result;
	}
}
/*
  1. 서기 1년 ~ 2020년 10월 13일 까지의 총 일수
*/