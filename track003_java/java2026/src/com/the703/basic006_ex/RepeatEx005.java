package com.the703.basic006_ex;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RepeatEx005 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//변수
		int len = -1;
		String str = "", result = "회문 입니다."; 
		//입력
		System.out.print("문자열을 입력 > ");
		str = sc.next();
		//처리
		len = str.length();
		for(int i = 0; i <= len/2; i++) {
			if(str.charAt(i) != str.charAt(len-i-1)) {
				result = "회문이 아닙니다.";
				break;
			}
		}
		//출력
		System.out.println(result);
		
		// 두번째 문제
		
		Map<Character, Integer> map = new HashMap<>();
		char maxCh = '\u0000';
		int maxIn = Integer.MIN_VALUE;
		for(char ch : str.toCharArray()) {
			if(map.get(ch) == null) map.put(ch, 1);
			else {
				maxCh = ch;
				maxIn = map.get(ch)+ 1;
				map.put(ch, map.get(ch)+ 1);
			}
		}
		
		System.out.println("문자별 빈도수:");
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			char key = entry.getKey();
			int val = entry.getValue();
			System.out.printf("%c : %d\n",key,val);
		}
		
	}
}
//1. 문자열 입력 받기
//2. 회문인지 아닌지 판별
//3. 토마토 0 기러기0 appa 0 apple X

/*
문자열 입력 : programming
문자별 빈도수:
p : 1
r : 2
o : 1
g : 2
a : 1 (0)
m : 2
i : 1
n : 1
가장 많이 나온 문자: 'r' (2번)
 */
