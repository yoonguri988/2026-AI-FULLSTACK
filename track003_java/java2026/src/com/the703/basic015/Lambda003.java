package com.the703.basic015;

class RefClass{       void method(String str) {  System.out.println(str);}  }
interface InterUsing{ void inter( RefClass c  , String str); }  

public class Lambda003 {
	public static void main(String[] args) {
		//#1. 익명 클래스
		InterUsing a1 = new InterUsing() {
			@Override public void inter(RefClass c, String str) { c.method(str); }
		};
		a1.inter(new RefClass(), "Hello :>");
		
		//#2. 람다식 ()->{} [RefClass]의 [method]를 사용
//		InterUsing a2 = (RefClass c, String str) -> { c.method(str); };
		InterUsing a2 = (         c,        str) ->   c.method(str);    // 직접 구현
		a2.inter(new RefClass(), "Hello :> :)"); // 부품객체(RefClass)의 method를 사용
		
		//#3. :: 표현식(참고)
		InterUsing a3 = RefClass::method; //자동연결 1) RefClass 2) method
		a3.inter(new RefClass(), "Hello :> :) :ㅁ");
		
		//////////////////////////////////////////////////////////////////////////////////
		// interface  InterBasic{  int method(int a, int b);         }
		InterBasic basic1 = (int a, int b) -> { return Math.max(a, b); }; // max static (바로 사용가능)
		System.out.println(basic1.method(10, 3));
		
		InterBasic basic2 = Math::max; // Math 부품 객체, max 기능 사용 static (바로 사용가능)
		System.out.println(basic2.method(10, 3));

		// Math.min
		InterBasic basic4 = (a,b)->Math.min(a, b); // min static (바로 사용가능)
		System.out.println(basic4.method(10, 3));
		
		InterBasic basic5 = Math::min;// Math 부품 객체, min 기능 사용 static (바로 사용가능)
		System.out.println(basic5.method(10, 3));
		
		// interface  InterString{ int compare(String a, String b);  } 
		InterString basic6 = (String a, String b) -> { return a.compareTo(b); };
		System.out.println(basic6.compare("apple", "banana"));
		// 문자열이 같으면 0, (음수) a < b a가 b보다 앞에 와요~, (양수) a > b a가 b보다 뒤에 와요~, 
		
		InterString basic7 = String::compareTo;
		System.out.println(basic7.compare("apple", "banana"));
		
		// interface  InterParse{  int parse(String s);              }  
		InterParse basic8 = (String a) -> { return Integer.parseInt(a); };
		System.out.println(basic8.parse("10") + 3);
		
		InterParse basic9 = Integer::parseInt;
		System.out.println(basic9.parse("10") + 3);
		
		//interface  InterAbs  {  int apply(int a);                 }  
		InterAbs basic10 = (int a) -> {return Math.abs(a);};
		System.out.println(basic10.apply(-10));
		
		InterAbs basic11 = Math::abs;
		System.out.println(basic11.apply(-10));
		
		// interface  InterPrint{  void print(String s);             }  
		InterPrint basic12 = (String s) -> System.out.println(s);
		basic12.print("나으르렁으르렁으르렁으르렁대");
		
		InterPrint basic13 = System.out::println;
		basic13.print("첨그날처럼그댈보면행복해져");
		
		//interface  Ex1{  int getLength(String s);  } 
		Ex1 basic14 = (String s) -> s.length();
		System.out.println(basic14.getLength("거꾸로강을거슬러오르는저힘찬연어들처럼"));
		
		Ex1 basic15 = String::length;
		System.out.println(basic15.getLength("힘이들땐하늘을봐,나는항상혼자가아니야"));
		
		//interface  Ex2{  void print(String s);     }  
		Ex2 basic16 = (String s) -> System.out.println(s);
		basic16.print("문이열리네요,그대가들어오죠");
		
		Ex2 basic17 = System.out::println;
		basic17.print("이윽고내가첫눈에너를알아봤을때~");
		
		
	}
}


interface  InterBasic{  int method(int a, int b);         }  
interface  InterString{ int compare(String a, String b);  }  
interface  InterParse{  int parse(String s);              }  
interface  InterAbs  {  int apply(int a);                 }  
interface  InterPrint{  void print(String s);             }  

interface  Ex1{  int getLength(String s);  }   
interface  Ex2{  void print(String s);     }  
