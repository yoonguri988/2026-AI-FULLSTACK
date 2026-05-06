package com.the703.basic014_ex;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MapEx003 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String region = "", isbn = "";
		
		// 지점이 없으면 만들고 값을 넣는다.
		Map<String, Map<String, BookDto>> library = new HashMap<>();
		library.put("서울점", new HashMap<String, BookDto>());
		library.put("부산점", new HashMap<String, BookDto>());
		
		if(library.containsKey("서울점")) { // 지점이 있으면 기존 지점에 값이 없으면 넣는다.
			library.get("서울점").put("978-11111", new BookDto("자바의 정석", "남궁성"));
			library.get("서울점").put("978-22222", new BookDto("파이썬 기초", "홍길동"));
		}
		
		if(library.containsKey("부산점")) { // 지점이 있으면 기존 지점에 값이 없으면 넣는다.
			library.get("부산점").put("978-33333", new BookDto("자료구조와 알고리즘", "이순신"));
			library.get("부산점").put("978-44444", new BookDto("파이썬 심화", "홍길동"));
		}
		
		System.out.println("=== 도서관 전체 목록 ===");
		for (Entry<String, Map<String, BookDto>> regions : library.entrySet()) {
			System.out.println("📚 "+ regions.getKey() );
			for(Entry<String, BookDto> bookInfo : regions.getValue().entrySet()) {
				System.out.printf("%s | %s | %s\n", bookInfo.getKey(), bookInfo.getValue().getTitle(), bookInfo.getValue().getAuthor());
			}
			System.out.println("---------------------");
		}
		
		System.out.print("지점 이름 입력> ");
		region = sc.next();
		System.out.print("ISBN 입력> ");
		isbn = sc.next();
		
		Map<String, BookDto> map = library.get(region);
		if(library.containsKey(region) && map.containsKey(isbn)) System.out.println("📖 선택한 도서 정보: "+map.get(isbn).getTitle()+" / 저자: "+map.get(isbn).getAuthor());
		else System.out.println("존재하지 않는 도서정보입니다.");
	}
}
/*
## 📘 연습문제3) Collection Framework + 중첩 HashMap
패키지명 : com.company.basic014_ex
클래스명 : MapEx003

### 요구사항
1. 중첩 Map 구조 만들기  
   - Map<String, Map<String, BookDTO>> library = new HashMap<>();  
   - 첫 번째 Key : 도서관 지점 이름 (예: "서울점", "부산점")  
   - 두 번째 Key : ISBN  
   - Value : BookDTO 객체  

2. DTO 클래스  
   java
   class BookDTO {
       private String title;
       private String author;
       // 생성자, getter/setter, toString()
   }
   

3. 출력하기  
   - 각 지점별 도서 목록 출력  

4. 사용자 입력받기  
   - 지점 이름과 ISBN을 입력받아 해당 도서 정보 출력  

### 📌 실행 예시 
=== 도서관 전체 목록 ===
📚 서울점
978-11111 | 자바의 정석 | 남궁성
978-22222 | 파이썬 기초 | 홍길동
---------------------
📚 부산점
978-33333 | 자료구조와 알고리즘 | 이순신
978-44444 | 파이썬 심화 | 홍길동
---------------------
지점 이름 입력> 서울점
ISBN 입력> 978-22222

📖 선택한 도서 정보: 파이썬 기초 / 저자: 홍길동
*/

/*      
 // 초기 데이터 배열 (지점, ISBN, 제목, 저자)
 	String[][] data = {
        {"서울점", "978-11111", "자바의 정석", "남궁성"},
        {"서울점", "978-22222", "파이썬 기초", "홍길동"},
        {"부산점", "978-33333", "자료구조와 알고리즘", "이순신"},
        {"부산점", "978-44444", "파이썬 심화", "홍길동"}
    };

        // 배열 데이터를 Map에 넣기 (putIfAbsent 없이)
        for (String[] row : data) {
            String branch = row[0];
            String isbn = row[1];
            String title = row[2];
            String author = row[3];

            // 그룹이 없으면 새로 생성
            if (!library.containsKey(branch)) {
                library.put(branch, new HashMap<>());
            }
            library.get(branch).put(isbn, new BookDTO(title, author));
        }
*/
