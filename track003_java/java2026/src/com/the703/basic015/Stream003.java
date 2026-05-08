package com.the703.basic015;

import java.util.*;
import java.util.stream.*;

public class Stream003 {
	public static void main(String[] args) {
		Integer[]  ages = {17 , 21, 26 , 45, 18}; 
		String[] users = {"김우빈","마동석","김재원","이도현","박보영","김하늘"};
		
		Stream<Integer> sages = Arrays.stream(ages);
		Stream<String> susers = Arrays.stream(users);
		
		//EX1. 짝수만 출력
		System.out.println("EX1. 짝수만 출력 >>");
		sages.filter(x->x % 2 == 0).forEach(System.out::println);
		System.out.println("EX1-2. 성이 김씨인 사람만 출력 >>");
		susers.filter(s->s.startsWith("김")).forEach(System.out::println);
		
		//EX2. 평균값 (숫자들을 더해서 연산)
		System.out.println("EX2. 평균값 >>");
		// (t) -> { return }
		//int java.util.function.ToIntFunction.applyAsInt( T value )
		sages = Arrays.stream(ages);
		double aver = sages.mapToInt(i -> i) // IntStream로 변환
		                   .average()        // 평균 계산
		                   .orElse(0.0);     // 값 없으면 0.0
		System.out.println(aver);
		
		//EX3. 제일 나이가 많은 사람
		System.out.println("EX3. 제일 나이가 많은 사람 >>");
		sages = Arrays.stream(ages);
		int max = sages.mapToInt(i -> i) //IntStream로 변환
				       .max()       // 집계) max(최대), min(최소), sum(합) 
				       .orElse(-1); // 값 없으면 -1
		System.out.println(max);
		
		//EX4. 나이 정렬해서 리스트로
		System.out.println("EX4. 나이 정렬해서 리스트로 >>");
		List<Integer> slist = Arrays.stream(ages)
				                    .sorted()
				                    .collect( Collectors.toList() );
		System.out.println("정렬된 리스트: "+slist);
		
		//EX5. 20살 이상만 필터링해서 리스트로 수집
		System.out.println("EX5. 20살 이상만 필터링해서 리스트로 수집 >>");
		List<Integer> list20up = Arrays.stream(ages).filter(x->x >= 20).sorted().collect(Collectors.toList());
		System.out.println("20살 이상만 필터링해서 리스트: " + list20up);
		
		
	}
}
