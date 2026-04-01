package com.the703.basic004_ex;

public class OperatorEx001 {
	public static void main(String[] args) {
		// 결과값은? 연산되는 순서는?
	    int a=3, b=10;
	    
	    System.out.println(  b+=10 - a-- ); // 17   
	    // a=2, b=17
	    System.out.println(  a+=5 ); // 7
	    // false || false && true
	    System.out.println(  a>=10 || a<0 && a>3); // false
	}
}
