package com.the703.basic015;

////////////////////////////////////////////////////////////
interface InterA2{  void   hi(); }
interface InterB2{  void   hi(String name); }
interface InterC2{  String hi(); }
interface InterD2{  String   hi(int num , String name); }   
////////////////////////////////////////////////////////////

public class Lambda002 {
	public static void main(String[] args) {
		// interface InterA2{  void   hi(); }
		System.out.println("\n\n[STEP1] 매개변수X, 리턴값X");
		// 1-1. 익명 객체
		InterA2 ia1 = new InterA2() {
			@Override public void hi() { System.out.println("Hi"); }
		};
		ia1.hi();
		// 1-2. 람다식
		InterA2 ia2 = () -> { System.out.println("Hi Hi"); };  ia2.hi();
		InterA2 ia3 = () -> System.out.println("Hi Hi");  ia3.hi(); //처리할일 한줄 {} 생략
		
		// interface InterB2{  void   hi(String name); }
		System.out.println("\n\n[STEP2] 매개변수O, 리턴값X");
		// 2-1. 익명 객체
		InterB2 ib1 = new InterB2() {
			@Override public void hi(String name) { System.out.println("Hi! "+ name);}
		};  ib1.hi("jihun");
		// 2-2. 람다식
		InterB2 ib2 = (String name) -> { System.out.println("Hi! "+ name); };  ib2.hi("noah");
		InterB2 ib3 = (name) -> System.out.println("Hi! "+ name);  ib2.hi("jaewon");
		
		//InterC2{  String hi(); }
		System.out.println("\n\n[STEP3] 매개변수X, 리턴값O");
		// 3-1. 익명 객체   Good :Day
		InterC2 ic1 = new InterC2() {
			@Override public String hi() { return "Good :Day"; }
		};
		System.out.println(ic1.hi());
		// 3-2. 람다식
		InterC2 ic2 = () -> { return "Good :Day"; };
		System.out.println(ic2.hi());
		
		InterC2 ic3 = () -> "Good :Day";
		System.out.println(ic3.hi());
		
		//interface InterD2{  String   hi(int num , String name); }
		System.out.println("\n\n[STEP4] 매개변수O, 리턴값O");
		// 4-1. 익명 객체   hi [name] ★
		InterD2 id1 = new InterD2() {
			@Override public String hi(int num, String name) { return "hi " + name + " " + "★".repeat(num); }
		};
		System.out.println(id1.hi(1, "jeonghan"));
		// 4-2. 람다식
		InterD2 id2 = (int num, String name) -> { return "hi " + name + " " + "★".repeat(num); };
		System.out.println(id2.hi(2, "yejun"));
		
		InterD2 id3 = (num, name) -> "hi " + name + " " + "★".repeat(num);
		System.out.println(id3.hi(3, "dohyun"));
	}
}
