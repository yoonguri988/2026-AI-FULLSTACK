package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx007 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double avg = 0.0;
		
		System.out.print("평균 한 개 입력 > ");
		avg = sc.nextDouble();
		
		switch((int)avg/10) {
		case 10:
		case 9:
			System.out.println("수");
			break;
		case 8:
			System.out.println("우");
			break;
		case 7:
			System.out.println("미");
			break;
		case 6:
			System.out.println("양");
			break;
		default:
			System.out.println("가");
			break;
		}
		
	}
}
/*
	평균 한 개 입력받아
    90~100점대면 수
    80~90점(90점미만)대면  우
    70~80점(80점미만)대면  미
    60~70점(70점미만)대면  양
    나머지 가
 */