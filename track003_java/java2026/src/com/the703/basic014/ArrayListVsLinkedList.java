package com.the703.basic014;

import java.util.*;

public class ArrayListVsLinkedList {
	public static void main(String[] args) {
		//ArrayList 와 LinkedList 차이점을 설명
		//ArrayList : 배열기반으로 동작, 검색속도 빠름, 삽입/삭제 느림
		//LinkedList: 노드기반 연결구조, 삽입/삭제시 빠름, 검색 느림
		
		int size = 10000;
		//1. ArrayList 테스트
		List<Integer> arrayList = new ArrayList<>();
		
		long start = System.currentTimeMillis(); //###
		for (int i = 0; i < size; i++) { arrayList.add(i); }
		long end = System.currentTimeMillis();   //###
		
		System.out.println("ArrayList 삽입 시간 : " + (end-start) + "ms");
		//2. LinkedList 테스트
		List<Integer> linkedList = new LinkedList<>();
		
		start = System.currentTimeMillis(); //###
		for (int i = 0; i < size; i++) { linkedList.add(i); }
		end = System.currentTimeMillis();   //###
		
		System.out.println("linkedList 삽입 시간 : " + (end-start) + "ms");
		//3. 중간삽입 테스트
		// -- arrayList
		start = System.currentTimeMillis(); //###
		for (int i = 0; i < size; i++) { arrayList.add(size/2, i); }
		end = System.currentTimeMillis();   //###
		
		System.out.println("ArrayList 중간삽입 시간 : " + (end-start) + "ms");
		//-- linkedList
		start = System.currentTimeMillis(); //###
		for (int i = 0; i < size; i++) { linkedList.add(size/2, i); }
		end = System.currentTimeMillis();   //###
		
		System.out.println("linkedList 중간삽입 시간 : " + (end-start) + "ms");
		
		
	}
}
