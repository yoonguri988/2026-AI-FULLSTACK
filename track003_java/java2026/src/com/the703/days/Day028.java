package com.the703.days;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class MilkDto {
	private String mname; 
	private int mprice;
	
	public String getMname() { return mname; }
	public void setMname(String mname) { this.mname = mname; }
	public int getMprice() { return mprice; }
	public void setMprice(int mprice) { this.mprice = mprice; }
	
	public MilkDto() { super(); }
	public MilkDto(String mname, int mprice) { super(); this.mname = mname; this.mprice = mprice; }
	
	@Override
	public String toString() {
		return "MilkDto [mname=" + mname + ", mprice=" + mprice + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(mname, mprice);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MilkDto other = (MilkDto) obj;
		return Objects.equals(mname, other.mname) && mprice == other.mprice;
	}
	
}

public class Day028 {
	public static void main(String[] args) {
		System.out.println("List >>>");
		List<MilkDto> list = new ArrayList<>();
		
		list.add(new MilkDto("바나나우유", 1300));
		list.add(new MilkDto("메론맛우유", 1800));
		list.add(new MilkDto("커피우유", 1500));
		list.add(new MilkDto("커피우유", 1500));
		
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%d\t%s\t\t%d\n",(i+1),list.get(i).getMname(),list.get(i).getMprice());
		}
		
		// 오름차순
		System.out.println("\n\n가격순으로 오름차순");
		
		// 1. 익명적 객체
//		list.sort(new Comparator<MilkDto>() {
//			@Override public int compare(MilkDto o1, MilkDto o2) { return Integer.compare(o1.getMprice(), o2.getMprice()); };
//		});
		// 2. 람다식
//		 list.sort((m1, m2) -> Integer.compare(m1.getMprice(), m2.getMprice()));
		
		// 3. 참조형 Integer 부품객체에 compare라는 기능 박스
		// error: list.sort(  Integer:: compare  ); MilkDto 객체에서 가격 꺼내야 함.
		list.sort(Comparator.comparingInt(MilkDto::getMprice));
		
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%d\t%s\t\t%d\n",(i+1),list.get(i).getMname(),list.get(i).getMprice());
		}
		// void java.util.List.sort(Comparator<? super MilkDto> c)
		// 리턴값 void (안에서 알아서 처리)
		// Comparator<? super MilkDto> c - Comparator 비교 부품 객체 <? super MilkDto>  MilkDto 포함한 부모객체
		
		
		System.out.println("Set >>>");
		Set<MilkDto> set = new HashSet<>();
		
		set.add(new MilkDto("바나나우유", 1300));
		set.add(new MilkDto("메론맛우유", 1800));
		set.add(new MilkDto("커피우유", 1500));
		set.add(new MilkDto("커피우유", 1500));
		
		Iterator<MilkDto> iter = set.iterator();
		int k = 1;
		while(iter.hasNext()) {
			MilkDto dto = iter.next();
			System.out.printf("%d\t%s\t\t%d\n",k++,dto.getMname(),dto.getMprice());
		}
		
		System.out.println("Map >>>");
		Map<String, MilkDto> maps = new HashMap<>();
		
		maps.put("banana", new MilkDto("바나나우유", 1300));  
		maps.put("melon", new MilkDto("메론맛우유", 1800));  
		maps.put("coffee", new MilkDto("커피우유", 1500));  
		maps.put("coffee2", new MilkDto("커피우유", 1500));  
		
		for(String str : maps.keySet()) {
			MilkDto milk = maps.get(str);
			System.out.printf("%s\t   %s\t   %s\n",str,milk.getMname(),milk.getMprice());
		}
	}
}


/*
Q1. 빈칸 채우기
1.  List는 순서가 [ 있는 ] 구조로 데이터를 관리하며, 중복을 [ 허용 ]
    - 주요 메서드: add, get, size, remove, contains
2. Set은 순서가[ 없는 ] 구조로 데이터를 관리하며,  중복을 [허용X]
    - 주요 메서드: add, getX -> 향상된 for문/iterator, size, remove, contains
3. Map은 [ key ]와 [ value ]의 쌍으로 데이터를 관리한다. 
    - 주요 메서드: put, get(key), size, remove, contains
 
---

Q2. ArrayList, HashSet, HashMap을 작성하시오.  

1. Milk Dto 클래스 만들기  
   - 속성 : private String mname; private int mprice  

2. milks 이름으로 ArrayList 만들기  
3. 다음의 데이터 넣기  
   new Milk("바나나우유", 1300),  
   new Milk("메론맛우유", 1800),  
   new Milk("커피우유", 1500),  
   new Milk("커피우유", 1500)  
4. for + size 이용해서 데이터 출력  
```
1     바나나우유       1300
2     메론맛우유       1800
3     커피우유         1500
4     커피우유         1500
```
 
5. sets 이름으로 HashSet 만들기  
6. 다음의 데이터 넣기  
   new Milk("바나나우유", 1300),  
   new Milk("메론맛우유", 1800),  
   new Milk("커피우유", 1500),  
   new Milk("커피우유", 1500)  
7. Iterator 이용해서 데이터 출력   
```
1     바나나우유       1300
2     메론맛우유       1800
3     커피우유         1500
```
 
8. maps 이름으로 HashMap 만들기  
9. 다음의 데이터 넣기 (Key-Value 구조)  
   maps.put("banana", new Milk("바나나우유", 1300));  
   maps.put("melon", new Milk("메론맛우유", 1800));  
   maps.put("coffee", new Milk("커피우유", 1500));  
   maps.put("coffee2", new Milk("커피우유", 1500));  

10. for-each + keySet 이용해서 데이터 출력  
```
banana    바나나우유       1300
melon     메론맛우유       1800
coffee    커피우유         1500
coffee2   커피우유         1500
``` 

*/