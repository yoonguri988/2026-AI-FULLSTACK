package com.the703.basic016;

import java.io.File;
import java.io.IOException;

public class JavaIO001 {
	public static void main(String[] args) {
		//1. 경로체크
		String folder_abs = "C:\\file\\"; // 절대 경로 - 시스템의 폴더 기준
		String folder_rel = "src/com/the703/basic016/"; // 상대 경로 - 현재작업 폴더 기준
		String file_path  = "io001.txt";
		
		//2. 폴더 + 파일 준비
		File folder = new File(folder_rel);
		File file   = new File(folder_rel + file_path);
		
		//3. 폴더가 없으면(exists) 폴더(mkdirs) 및 파일 만들기(createNewFile)
		try {
			if(!folder.exists()) { folder.mkdirs(); }    //mkdir는 경로 없으면 오류남
			if(!file.exists()) { file.createNewFile(); }
			
		} catch (IOException e) { e.printStackTrace(); }
		
		System.out.println("폴더/파일 준비완료");
	} //
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
3. 보조 스트림
*/