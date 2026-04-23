package com.the703.basic010_ex;

import com.the703.basic010.Milk;//basic010 패키지에 있는 Milk

public class ModifierEx1 {
	public static void main(String[] args) {
		Milk m1 = new Milk();
		System.out.println(m1);
		m1.setMprice(2000);
		System.out.println(m1);
	} // end main
}
/*
ㅁ출력된화면
Milk [mno=0, mname=null, mprice=0]
Milk [mno=0, mname=null, mprice=2000]
*/