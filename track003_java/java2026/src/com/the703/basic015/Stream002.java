package com.the703.basic015;

import java.util.*;
import java.util.stream.*;

public class Stream002 {
	public static void main(String[] args) {
	     Integer[]     arr  = {1,2,5,4,3,4,5,1,2,1,1,1,1,1,1};   
	     List<Integer> list = Arrays.asList(arr);
	     
	     //1단계 stream
	     Stream<Integer> iarr = Arrays.stream(arr);
	     Stream<Integer> ilist = list.stream();
	     
	     //2단계 중간연산
	     // (t) -> { return true/false; }
	     // boolean java.util.function.Predicate.test( T t ) 
	     iarr = iarr.filter((t) -> t % 2 == 1)   // 홀수 필터링 15351111111
	    		    .distinct()                  // 중복제거 153
	    		    .sorted()                    // 정렬    135
	    		    .limit(2)                    // 갯수제한 13
	    		    .skip(1);                    // skip   3
	     
	     ilist = ilist.filter((t) -> t % 2 == 1) // 홀수 필터링 15351111111
		    		  .distinct()                // 중복제거 153
		    		  .sorted()                  // 정렬    135
		    		  .limit(2)                  // 갯수제한 13
		    		  .skip(1);                  // skip   3
	     
	     //3단계 최종연산
	     // (t) -> {}
	     // void java.util.function.Consumer.accept( T t )
	     iarr.forEach(System.out::print);
	     System.out.println();
	     ilist.forEach(System.out::print);
	}
}
