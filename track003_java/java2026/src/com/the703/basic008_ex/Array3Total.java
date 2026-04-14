package com.the703.basic008_ex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Array3Total {
	public static void main(String[] args) {
	    String[] name={"아이언맨","헐크","캡틴","토르","호크아이"};
	    int[] kor={100,100,100,70,35};   
	    int[] eng={100,100,100,80,35};
	    int[] mat={100,100,100,60,35};
	    int[] aver = new int[5];
	    int[] rank = {1,1,1,1,1};
	    TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
	    
	    //처리
	    for (int i = 0; i < aver.length; i++) {
			aver[i] = (kor[i]+eng[i]+mat[i])/3;
			set.add(aver[i]);
		}
	    
	    // 등수 구하기
	    List<Integer> list = new ArrayList<>(set);
	    for(int i = 0; i <aver.length; i++) {
	    	rank[i] = list.indexOf(aver[i])+1;
	    }
	    
	    //출력
	    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
	    		         + "이름\t국어\t영어\t수학\t평균\t등수\t합격여부\t장학생\t랭킹\n"
	    		         + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
	    for (int i = 0; i < aver.length; i++) {
			System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\t%s\t%s", //                  합격 여부 + 재시험
					name[i], kor[i], eng[i], mat[i],aver[i], rank[i], aver[i]<60?"불합격":kor[i]<40||eng[i]<40||mat[i]<40?"재시험":"합격", aver[i]<95?"----\t":"장학생\t");
		    // 랭킹: 별의 갯수 = 평균 / 10;
			for(int j = 0; j < aver[i]/10; j++) {
				System.out.print("*"+(j == aver[i]/10-1?"\n":""));
			}
		}
	    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	}
}
/*
 
*/