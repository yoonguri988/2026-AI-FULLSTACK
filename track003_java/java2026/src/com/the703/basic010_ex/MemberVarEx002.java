package com.the703.basic010_ex;

class Student {
	// 인스턴스변수
	String name = "홍길동";
	// 인스턴스변수
	int kor = 90;
	// 인스턴스변수
	int eng = 85;
	// 클래스변수
	static int studentCount = 0;
	// 클래스변수
//	static int total = kor + eng; // 클래스 변수는 method 영역에 저장되는 정보, heap영역에 저장되는 인스턴스 변수인 kor, eng가 저장되지 않았기 때문
	// 클래스변수
	static int maxScore = 100;

	//생성자
	public Student() {
		studentCount++;
	}

	// 인스턴스 메서드
	public int getTotalScore() {
		return kor + eng;
	}

	// 클래스 메서드
	public static void showStudentCount() {
		System.out.println("전체 학생 수: " + studentCount);
	}

	// 클래스 메서드
	public static void showName() {
//		System.out.println(name); // 클래스 변수는 method 영역에 저장되는 정보, heap영역에 저장되는 인스턴스 변수인 name이 저장되지 않았기 때문
	}

	// 인스턴스 메서드
	public void showInfo() {
		System.out.println("이름: " + name);
		System.out.println("총점: " + getTotalScore());
	}
}

public class MemberVarEx002 {
	public static void main(String[] args) {
		// 지역변수
		Student s1 = new Student();
		// 지역변수
		Student s2 = new Student();

		s1.showInfo();
		Student.showStudentCount();
	}
}
//////////////////////////////////////////////////////
/* [RUNTIME DATA AREA]
-------------------------------------------------------------------------
[METHOD:정보, static, final] Student.class, MemberVarEx002.class
Student.studentCount = 0; Student.maxScore = 100; Student.total = 0;
Student.showStudentCount(); Student.showName();
-------------------------------------------------------------------------
[HEAP:동적]                          |  [STACK:지역]
2번지: {name="홍길동", kor=90, eng=85}   ← s2(2번지)
1번지: {name="홍길동", kor=90, eng=85}   ← s1(1번지)
									   main#2
-------------------------------------------------------------------------
*/
//////////////////////////////////////////////////////

/*
-- class Student 작성해주세요

- 문제 1. 다음 코드에서 인스턴스변수, 클래스변수, 지역변수를 구분하시오.  ( 보관되는 영역도 추가 )
- 문제 2. 인스턴스메서드와 클래스메서드를 구분하시오.  
- 문제 3. 오류가 발생하는 이유를 설명하시오.
- 문제 4. runtime data area 위치영역 그림그리기

class Student {
    String name = "홍길동";        
    int kor = 90;                  
    int eng = 85;                 
    static int studentCount = 0;    

    static int total = kor + eng;   

    static int maxScore = 100;     

    public Student() {
        studentCount++;             
    }

    public int getTotalScore() {
        return kor + eng;        
    }

    public static void showStudentCount() {
        System.out.println("전체 학생 수: " + studentCount);  
    }

   public static void showName() {
         System.out.println(name);  
   }

    public void showInfo() {
        System.out.println("이름: " + name);            
        System.out.println("총점: " + getTotalScore());    
    }
}

public class MemberVarEx002 {
    public static void main(String[] args) {
        Student s1 = new Student();     
        Student s2 = new Student();     

        s1.showInfo();                  
        Student.showStudentCount();    
    }
}
*/