# DAY 006

## TODO1: HTML & CSS

<ol><ul><li>

<dl><dt><dd>

```css
* {
            /* 모든 태그를 처리 (초기화) */
            margin: 0; /* 브라우저가 가지고 있는 바깥쪽 여백 제거 */
            padding: 0; /* 브라우저가 가지고 있는 안쪽 여백 제거 */
        }
```

###

## TODO2: JAVA CONTROL - IF

### 1. CONTROL - IF

제어문
1. 프로그램 코드 실행 흐름
- 위 -> 아래, 왼쪽 -> 오른쪽
- 제어문은 개발자가 원하는 방향으로 변경할 수 있도록 도와주는 역할

2. 종류
- 조건문 : if, switch
- 반복문 : for, whlie, do while
- 제어 키워드 : break, continue



## 복습 문제

> 정리문제 (1) 
1. 웹페이지의 배경을 하늘색으로 바꾸고 싶다. 어떤 속성을 써야 할까?    ____________ background-color: skyblue;
2. 제목 글자를 초록색으로 바꾸려면?    ____________ color: green;
3. 본문 글자의 크기를 18px로 지정하려면?    ____________ font-size: 18px;
4. 메뉴 글자를 오른쪽으로 정렬하려면?    ____________ text-align: right;
5. 링크에 밑줄을 없애려면?    ____________ text-decoration: underline;
6. 글꼴을 'Times New Roman'으로 바꾸려면?    ____________ font-family: 'Times New Roman'; 
7. 강조하고 싶은 단어를 굵게 표시하려면?    ____________ font-weight: bold;
8. 이미지를 가로 400px로 줄이고 싶다. 어떤 속성?    ____________ width: 400px;
9. 박스 바깥쪽에 20px 여백을 주려면?    ____________ margin: 20px;
10. 버튼 안쪽에 8px 여백을 주려면?    ____________ padding: 8px;
11. 카드 요소에 2px 점선 테두리를 주려면?    ____________ border: 2px dashed black;
12. 프로필 사진 모서리를 둥글게 50%로 만들려면?    ____________ border-radius: 50%;
13. 박스에 살짝 번지는 그림자 효과를 주려면?    ____________ box-shadow: 0 0 10px rgba(0,0,0,0.2);
14. 버튼에 마우스를 올렸을 때 부드럽게 색이 바뀌도록 하려면?    ____________ transition: all 2s;

> 정리문제 (2)
1. css 선택자 유일한 값 ? id
2. css 선택자 여러개 선택시? class 
 
> 정리문제 (3)
1. 숫자를 한개입력받아
2. 1이면 1이다, 2이면 2이다, 3이면 3이다  if로 작성

Scanner sc = new Scanner(System.in);
int num = 0;

num = sc.nextInt();
if(num == 1){
    System.out.println("1이면 1이다");
} else if(num == 2){
    System.out.println("2이면 2이다");
} else if(num == 3){
    System.out.println("3이면 3이다");
}


※ 숙제 ※
연습문제7)  
패키지명 : com.company.java004_ex
클래스명 :  IfEx007
출력내용 :  계산기

1. 정수를 하나 입력해주세요 > 10
2. 정수를 하나 입력해주세요 > 3
3. 연산자를 입력해주세요(+,-,*,/) > +
10+3=13