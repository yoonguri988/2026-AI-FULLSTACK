package com.the703.basic004_ex;

public class CastingEx004 {
	public static void main(String[] args) {
		short sh1 = 1;
		short sh2 = 2;
		short result = (short)(sh1 + sh2);
		// int 보다 작은 자료형 : byte, short / char
		// 작은 정수형끼리 연산시 내부적으로 자동으로 int로 변환해서 계산
	}
}
/*
 * 내부적으로 연산될 땐 무조건 int 
 * > short, byte, char 형변환 필요.  
 */
