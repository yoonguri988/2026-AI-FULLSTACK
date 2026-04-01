# DAY005

## TODO1: CSS 기본

### 6. CSS (2) block VS inline

1. Q) a 태그에 margin 태그 적용 안 됨, text-align 적용 안 됨, width 적용 안 됨.

display: block    박스( 갈치조림냄비 )               - width 가 있음, 줄바꿈 있음
ex ) div, p, pre

display: inline   박스 안에 내용물( 국물, 갈치, 무 )  - width 가 없음(△), 줄바꿈 없음
ex ) img, a, span, strong


### 7. CSS (2) 내부 적용 / id VS class

1. CSS 적용 방법
 - 인라인 스타일 : tag 안에 직접 적용
 
 ```html
    <p style="color:pink">color</p>
 ```

 2. 내부 스타일 시트 - head안에 style을 사용해 작성
 
 ```html
 <style> p{ color: red; } </style>
 ```

 3. 외부 스타일 시트


---

## TODO2. JAVA 자료형 2


## 복습문제

### 정리문제 (1)

1. 배경을 파란색으로 설정하는 속성은?  background-color: blue;
2. 글자색상을 빨간색으로 지정하는 속성은?  color: red;
3. 글자 크기를 20px로 지정하는 속성은?  font-size: 20px;
4. 글자를 가운데 정렬하는 속성은?  text-align: center;
5. 글자에 밑줄을 추가하는 속성은?  text-decoration: underline;
6. 글꼴을 Arial로 지정하는 속성은?  font-family: Arial;
7. 글자를 굵게 표시하는 속성은?   font-weight: bold;
8. 요소의 가로 길이를 300px로 지정하는 속성은?  width: 300px;
9. 요소의 바깥쪽 여백을 10px로 지정하는 속성은?  margin: 10px;
10. 요소의 안쪽 여백을 15px로 지정하는 속성은?  padding: 15px
11. 요소에 1px 실선 테두리를 추가하는 속성은?  border: 1px soild black;
12. 모서리를 둥글게 10px로 만드는 속성은?  border-radius: 10px;
13. 그림자 효과를 추가하는 속성은?  box-shadow: 0 0 12px rgba(0,0,0,0.1);
14. 천천히 움직이는 장면전환효과를 주는 속성은? transition: all 2s;

### 정리문제 (2)

15.  가로 사이즈 지정가능한것은 block   /    inline  ::: block
16.  a태그에 margin-top 줄수   o   /   x           ::: x
17.  css 적용방법 3가지 (   /    /    )            ::: 인라인, 내부 적용, 외부 적용
18.  css 적용 내부적용방법은 (   ) 태그안에 (   ) 태그 적용해서 사용 ::: head, style

### 정리문제 (3)

1.  연산자의 우선순위를 적으시오.

   () -> 값 (++, --,*,/,%,+,-) -> 비교 (>,<,>=,<=,==,!=) -> 조건 (&& || ?:) -> 대입연산자(=)

2.  다음오류 해결
short sh1 = 1 , sh2=2;
short result = sh1 + sh2;

::: short result = (short)(sh1 + sh2)

3. 필수조건
q1-1 int형 변수 x가 3보다 크고 10보다 작을때 true인 조건식 

::: x > 3 && x < 10

q1-2 char형 변수 ch가 'a' 또는 'A'일때   true인 조건식    

::: ch = 'a' || ch = 'A'

q1-3 char형 변수 ch가 숫자('0'~'9')일때   true인 조건식     

::: '0' <= ch && ch <= '9'

q1-4 char형 변수 ch가 영문자(대문자 또는 소문자) 일때   true인 조건식

::: 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z'