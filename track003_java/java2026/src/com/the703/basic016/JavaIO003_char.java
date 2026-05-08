package com.the703.basic016;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

//2. Java I/O 분류
//- byte(모든 종류-그림, 멀티미디어, 문자)	/ char (문자)
//- byte(InputStream/OutputStream)	/ char (Reader/Writer)
public class JavaIO003_char {
	public static void main(String[] args) {
		//#1. 경로
		String folder_rel = "src/com/the703/basic016/"; // 상대 경로 - 현재작업 폴더 기준
		String file_path  = "io003_char.txt";
		//#2. 폴더 + 파일(exists, mkdirs, createNewFile)
		File folder = new File(folder_rel);
		File file = new File(folder_rel + file_path);
		
		try {
			if(!folder.exists()) folder.mkdirs();
			if(!file.exists()) file.createNewFile();
		} catch(IOException e) { e.printStackTrace(); }
		
		System.out.println("폴더/파일 준비완료");
		//#3. char 파일 쓰기	Reader(FileReader)		>   프로그램	>#	 Writer(FileWriter)
		try {
			Writer writer = new FileWriter(file);
			writer.write("너 가는 길이 너무 지치고 힘들 때\n"
					   + "말을 해줘 숨기지 마 넌 혼자가 아니야\n\n"
					   + "우리도 언젠가 흰수염고래처럼 헤엄쳐\n"
					   + "두려움 없이 이 넓은 세상 살아갈 수 있길\n"
					   + "그런 사람이길\n");
			writer.close();
			System.out.println("Writer 쓰기완료");
		} catch (FileNotFoundException e) { e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}
		//#4. char 파일 읽기  Reader(FileReader)	   #>   프로그램	> Writer(FileWriter)
		System.out.println("\nchar 파일 읽기  Reader(FileReader) >>");
		try {
		Reader reader = new FileReader(file);
		int cnt = 0;
		while((cnt = reader.read()) != -1) { System.out.print((char)cnt); }
		reader.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
/*
1. Java I/O
- 입력(input)과 출력(output)
- 두 대상간의 데이터를 주고 받는것
- 스트림이란? 사용 연결통로

	입력 스트림	→	[프로그램]	→	출력 스트림
	InputStream						OutputStream
	Reader							Writer
2. Java I/O 분류
- byte(모든 종류-그림, 멀티미디어, 문자)	/ char (문자)
- byte(InputStream/OutputStream)	/ char (Reader/Writer)

3. 보조 스트림
*/