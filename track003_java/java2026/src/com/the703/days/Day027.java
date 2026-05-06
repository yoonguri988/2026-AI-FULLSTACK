package com.the703.days;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class UserInfo {
	private String name;
	private int age;
	
	public UserInfo() { super(); }
	public UserInfo(String name, int age) { super(); this.name = name; this.age = age; }

	@Override
	public int hashCode() {
		return Objects.hash(age, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		return age == other.age && Objects.equals(name, other.name);
	}
	@Override public String toString() { return "UserInfo [name=" + name + ", age=" + age + "]"; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
}
 
public class Day027 {
	public static void main(String[] args) { 
//		1. UserInfo    Dto 클래스만들기    - 속성 :  private  String name; private  int age;
//		2. users ArrayList 만들기
		List<UserInfo> users = new ArrayList<>();
//		3. 다음의 데이터 넣기
//		   new UserInfo("아이언맨" , 50) , new UserInfo("헐크" , 40) , new UserInfo("캡틴" , 120) , new UserInfo("캡틴" , 120)
		users.add(new UserInfo("아이언맨", 50));
		users.add(new UserInfo("헐크", 40));
		users.add(new UserInfo("캡틴", 120));
		users.add(new UserInfo("캡틴", 120));
//		4. for+size 이용해서  데이터 출력
//
//		1     아이언맨       50
//		2     헐크       	40
//		3     캡틴  	    120
//		4     캡틴  	    120
		System.out.println("== ArrayList version");
		for (int i = 0; i < users.size(); i++) {
			System.out.printf("%d\t%s\t%d\n", (i+1), users.get(i).getName(), users.get(i).getAge());
		}
		// 이름순 정렬 (내림차순)
		users.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
		System.out.println("이름순 정렬 (내림차순)");
		for (int i = 0; i < users.size(); i++) {
			System.out.printf("%d\t%s\t%d\n", (i+1), users.get(i).getName(), users.get(i).getAge());
		}
		// 이름순 정렬 (오름차순)
		users.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		System.out.println("이름순 정렬 (오름차순)");
		for (int i = 0; i < users.size(); i++) {
			System.out.printf("%d\t%s\t%d\n", (i+1), users.get(i).getName(), users.get(i).getAge());
		}
		// 나이순 정렬 (내림차순)
		users.sort((o1, o2) -> o2.getAge() - o1.getAge());
		System.out.println("나이순 정렬 (내림차순)");
		for (int i = 0; i < users.size(); i++) {
			System.out.printf("%d\t%s\t%d\n", (i+1), users.get(i).getName(), users.get(i).getAge());
		}
		// 나이순 정렬 (오름차순)
		users.sort((o1, o2) -> o1.getAge() - o2.getAge());
		System.out.println("나이순 정렬 (오름차순)");
		for (int i = 0; i < users.size(); i++) {
			System.out.printf("%d\t%s\t%d\n", (i+1), users.get(i).getName(), users.get(i).getAge());
		}
		
//		5. sets  HashSet 만들기
//		6. 다음의 데이터 넣기
//		   new UserInfo("아이언맨" , 50) , new UserInfo("헐크" , 40) , new UserInfo("캡틴" , 120)
//		7. 향상된 for 이용해서 데이터 출력
//
//		1     아이언맨       50
//		2     헐크       	40
//		3     캡틴  	    120
		Set<UserInfo> sets = new HashSet<>();
		sets.add(new UserInfo("아이언맨", 50));
		sets.add(new UserInfo("헐크", 40));
		sets.add(new UserInfo("캡틴", 120));
		sets.add(new UserInfo("캡틴", 120));
		
		System.out.println("== HashSet version");
		int k = 1;
		for (UserInfo user : sets) {
			System.out.printf("%d\t%s\t%d\n", k++, user.getName(), user.getAge());
		}
//		for (Iterator iterator = sets.iterator(); iterator.hasNext();) {
//			UserInfo userInfo = (UserInfo) iterator.next();
//			System.out.printf("%d\t%s\t%d\n", k++, userInfo.getName(), userInfo.getAge());
//		}
	}
}
/*
Q1.   다음 빈칸을 채우시오
1. 콜렉션프레임워크
- [  ##1. 배열     ]의 단점을 개선한 클래스 [##2. 객체  ]만 저장가능   
- 저장공간의 크기를 [##3. 동적    ]으로 관리함.
	String []        arr = new String[100];   ※ 자료형 고정, 갯수 고정
	List<UserInfo>  list = new ArrayList<>(); ※ 내가 원하는 객체(틀), 갯수 무한대

2. 핵심 인터페이스 [##4. 3개 :  List ,  Set   ,  Map   ]
- 인터페이스를 통해서 틀이 잡혀 있는 방법으로 다양한 컬렉션 클래스를 이용함.

  List : ##5.   기차   인덱스여부 [ O ] ,  중복허용여부 [ O ] ,  
  				 [ add, get, size, remove, contains  ] 
  Set  : ##6.   주머니  인덱스여부 [ X ] ,  중복허용여부[ X ]  ,  
  				 [ add, get(순서) X → 향상된 for문/Iterator, size, remove, contains   ] 
  Map  : ##7.   사전   [ 키:값 ] - 쌍(Entry),        
  				 [ put, get(key), size, remove, contains  ] 


				 
Q2.  ArrayList, HashSet 을 작성하시오.
1. UserInfo    Dto 클래스만들기    - 속성 :  private  String name; private  int age;
2. users ArrayList 만들기
3. 다음의 데이터 넣기
   new UserInfo("아이언맨" , 50) , new UserInfo("헐크" , 40) , new UserInfo("캡틴" , 120) , new UserInfo("캡틴" , 120)
4. for+size 이용해서  데이터 출력

1     아이언맨       50
2     헐크       	40
3     캡틴  	    120
4     캡틴  	    120
 

5. sets  HashSet 만들기
6. 다음의 데이터 넣기
   new UserInfo("아이언맨" , 50) , new UserInfo("헐크" , 40) , new UserInfo("캡틴" , 120)
7. Iterator 이용해서 데이터 출력

1     아이언맨       50
2     헐크       	40
3     캡틴  	    120

*/