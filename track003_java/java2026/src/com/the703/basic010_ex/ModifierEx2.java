package com.the703.basic010_ex;

import java.util.Iterator;

import com.the703.basic010.Score;

public class ModifierEx2 {
	public static void main(String[] args) {
//		Score iron = new Score();
//		Score hulk = new Score("hulk", 20, 50, 30);

		
		// Score.info()위에 메서드작성해주세요! ##
		// setter를 이용해주세요!
//		iron.setName("iron");
//		iron.setKor(100);
//		iron.setEng(100);
//		iron.setMath(100);
		
//		Score.info(); // 클래스메서드
//		iron.show();
//		hulk.show();
		
		// 배열로 처리?
		Score[] scores = new Score[2];
		scores[0] = new Score();
		scores[1] = new Score("hulk", 20, 50, 30);
		
		scores[0].setName("iron");
		scores[0].setKor(100);
		scores[0].setEng(100);
		scores[0].setMath(100);
		
		Score.info();
		for (int i = 0; i < scores.length; i++) {
			scores[i].show();
		}
	}

}
