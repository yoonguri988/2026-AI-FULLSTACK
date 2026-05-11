package com.the703.basic016;

import java.io.*;

public class JavaIO005_buffered {
	public static void main(String[] args) throws IOException {
		//#1. 경로
		String folder_rel = "src/com/the703/basic016/"; // 상대 경로 - 현재작업 폴더 기준
		String file_path  = "io005_buffered.txt";

		File folder = new File(folder_rel);
		File file = new File(folder_rel + file_path);
		
		//#2. 폴더 + 파일 만들기 (mkdirs, createNewFile)
		if(!folder.exists()) folder.mkdirs();
		if(!file.exists()) file.createNewFile();
		System.out.println("... 폴더 + 파일 준비 완료");// ctrl + F11 ※ 새로고침(F5)
		
		//#3. 파일 쓰기  InputStream > [프로그램] > ★OutputStream
		// 1) new FileOutputStream(file)	- byte
		// 2) new OutputStreamWriter(1)		- 인코딩에 맞춰서 문자로 처리
		// 3) new BufferedWriter(2)			- 속도 향상 (한꺼번에 데이터를 받아서 처리)
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		StringBuffer sb = new StringBuffer("나는 너의 바다 그 위에 비가 될게\n"
			                             + "언제라도 내려와 네게 잠겨\n"
			                             + "널 안아줄 수 있게\n"
			                             + "햇살이 널 비출 때 나에게 웃어줄래\n"
			                             + "이제 널 놓지 않아\n"
			                             + "난 떠나지 않아");
		bw.write(sb.toString());
		bw.close();
		System.out.println("... 파일 쓰기 완료");
		//#4. 파일 읽기 ★InputStream > [프로그램] > OutputStream
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = "";
//		StringBuffer sb = new StringBuffer();
		while( (line = br.readLine()) != null) { System.out.println(line); }
		br.close();
		System.out.println("... 파일 읽기 완료");
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
- 스트림이란? 네트워크, 파일, 문자, ... 사용연결통로
- ( new FileInputStream(file) ): byte 단위로 바꿔줌
- new InputStreamReader(new FileInputStream(file)): byte를 문자 스트림으로 변경
- new BufferedReader( new InputStreamReader(new FileInputStream(file)) )
- 1) new FileInputStream(file)	- byte   (0101...)
- 2) new InputStreamReader		- 바이트를 [문자]스트림으로, 인코딩
- 3) new BufferedReader			- 속도 향상, ( a → a, b → b [X])(a,b,c → a,b,c [O])

*/