package com.the703.basic014;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
 * 1. 콜렉션 플레임워크 : [배열]의 단점을 개션한 [객체]만 저장가능 [동적배열]
 * 2. List, Set, Map
 *    List (기차) - 순서 O, 중복 O / add, get, size, remove, contains
 *    Set (주머니) - 순서 X, 중복 X / add, 향상된 for/Iterator, size, remove, contains
 */
public class Set001 {
	public static void main(String[] args) {
		Set<Integer> set1 = new HashSet<>();
		// The constructor Integer(int) is deprecated since version 9
		set1.add(new Integer(1)); //  Integer e = new Integer(1);    부품객체
		set1.add(1); // Integer e = 1     (기본값)
		set1.add(1); // 부품 객체   = 기본값  (Integer - wrapper 클래스) 
		set1.add(1); // 기본 값을 자동으로 - 객체화 - 부품객체(wrapper 클래스)
		set1.add(2);
		set1.add(3);
		
		Integer i1 = 1; // 부품 객체   = 기본값
		int     i2 = i1;// 기본값      =  부품객체
		float f = i1.floatValue();
		// Cannot invoke floatValue() on the primitive type int
		// float f2 = i2.floatValue();
		// Wrapper - Interger, Float, Double,,,
		System.out.println(i1 + "\t" + f);
		
		System.out.println(set1);
		
		//add, get(x), size, remove, contains
		Set<Candy> set2 = new HashSet<>();
		set2.add( new Candy("츕파춥스", 200));
		set2.add( new Candy("츕파춥스", 200));
		set2.add( new Candy("츕파춥스", 200));
		set2.add( new Candy("청포도알사탕", 4500));
		set2.add( new Candy("아이셔", 1500));
		
		System.out.println(set2);
		System.out.println(set2.size());
		System.out.println(set2.remove(new Candy("츕파춥스", 200))? "냠냠~!" : "못먹었어ㅠ!");
		System.out.println(set2.contains(new Candy("아이셔", 1500))? "겸둥이꺼" : "없엉");
		
		for (Candy c : set2) {
			System.out.println(c.name + "-" + c.price);
		}
	}// end main
}// end class

class Candy {
	String name;
	int    price;
	
	public Candy() { super(); }
	public Candy(String name, int price) { super(); this.name = name; this.price = price; }
	
	@Override public String toString() { return "Candy [name=" + name + ", price=" + price + "]"; }
	
	//
	@Override public int hashCode() { return Objects.hash(name, price); }
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Candy other = (Candy) obj;
		return Objects.equals(name, other.name) && price == other.price;
	}
}