package com.the703.basic001;

class A {
	class B {}
}
public class Basic002_1 {
	class D {
		class E {}
	}
}

// 1. 소스코드 파일 명 
//    Basic002_1
// 2. 생성되는 클래스 코드의 갯수는?
//    5개
// 3. 바이트 코드 파일명
//    A.class, A$B.class, 
//    Basic002_1.class, Basic002_1$D.class, Basic002_1$D$E.class