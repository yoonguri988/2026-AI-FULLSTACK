package com.the703.basic016;

import java.io.*;

public class JavaIO004_img {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//#1. 경로
	    String origin = "src/com/the703/basic016/yumi.jpg";  // 상대경로- 현재작업 폴더기준
	    String target1  = "src/com/the703/basic016/yumi_1.jpg";
	    String target2  = "src/com/the703/basic016/yumi_2.jpg";
		
		//#2. byte 이미지파일 원본 읽어들여서 쓰기
		// InputStream > [프로그램] > OutputStream
		InputStream bis = new FileInputStream(origin);    // 원본 읽어들여서    (read)
		OutputStream bos = new FileOutputStream(target1); // yumi_1.jpg 쓰기  (write)
		
		int cnt1 = 0;
		while((cnt1 = bis.read()) != -1) { // 원본 읽어들여서
			bos.write((byte) cnt1); // yumi_1.jpg 쓰기
		}
		bis.close(); bos.close();
		System.out.println(">> byte 이미지 복사 완료!");
		
		//#3. char 이미지파일 원본 읽어들여서 쓰기
		// Reader > [프로그램] > Writer
		Reader cr = new FileReader(origin);
		Writer cw = new FileWriter(target2);
		
		int cnt2 = 0;
		while((cnt2 = cr.read()) != -1) { // 원본 읽어들여서
			cw.write((char) cnt2); // yumi_2.jpg 쓰기
		}
		cw.close(); cr.close();
		System.out.println(">> char 이미지 복사 완료!");
	}
}
