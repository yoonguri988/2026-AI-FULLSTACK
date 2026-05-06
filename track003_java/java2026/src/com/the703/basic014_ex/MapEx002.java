package com.the703.basic014_ex;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MapEx002 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String isbn = "";
		Map<String, BookDto> map = new HashMap<>();
		
		map.put("978-11111", new BookDto("자바의 완성" , "가길동"));
		map.put("978-22222", new BookDto("파이썬 기초" , "홍길동"));
		map.put("978-33333", new BookDto("자료구조와 알고리즘" , "이순신"));
		
		System.out.println("==============================\n"
				+ "ISBN        TITLE        AUTHOR\n"
				+ "==============================");
		for(Entry<String, BookDto> book: map.entrySet()) {
			System.out.println(book.getKey()+" | "+book.getValue().getTitle()+" | "+ book.getValue().getAuthor()
					          +"\n------------------------------");
		}
		System.out.println("도서 정보를 제공중입니다");
		
		System.out.print("ISBN을 입력하세요> ");
		isbn = sc.next();
		
		if(map.containsKey(isbn)) System.out.println("📖 선택한 도서 정보: "+ map.get(isbn).getTitle() +" / 저자: "+  map.get(isbn).getAuthor());
		else System.out.println("존재하지 않는 도서정보입니다.");
	}
}
/*
연습문제2)  Collection  Framework
패키지명 : com.the703.basic014_ex
클래스명 : MapEx002
1. MAP 만들기
KEY         VALUE
978-11111   new BookDto("자바의 완성" , "가길동")
---------------------
978-22222   new BookDto("파이썬 기초" , "홍길동")
---------------------
978-33333   new BookDto("자료구조와 알고리즘" , "이순신")

Map<String, BookDto> map = new HashMap<>();


2 다음과 같이 문제풀기
2-1. BookDto 만들기   {    private String title;  private String author;}
2-2. 다음과 같이 출력
2-3. 사용자에게 KING의 이름을 입력받아 해당하는 값 출력
==============================
ISBN        TITLE        AUTHOR
==============================
978-11111 | 자바의 완성 | 가길동
------------------------------
978-22222 | 파이썬 기초 | 홍길동 
------------------------------
978-33333 | 자료구조와 알고리즘 | 이순신 
------------------------------
도서 정보를 제공중입니다
ISBN을 입력하세요> 978-22222

📖 선택한 도서 정보: 파이썬 기초 / 저자: 홍길동

*/