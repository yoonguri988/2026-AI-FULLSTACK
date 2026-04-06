# DAY 008

## TODO1 CSS 기본

### 7. 가상 선택자

선택자:
- 일반 태그 선택자: p
- 아이디 선택자 : #id
- 클래스 선택자: .class
- 가상 선택자: : :hover, :first-child, :last-child

- [ ] 1. CSS 정리 파일 확인

## TODO2: JAVA CONTROL - SWITCH

■1. html + css

  1. css 선택자 - 태그선택자, 아이디선택자, 클래스선택자 , (         : 예  :hover, :first-child)

  **p, #id, .class, :hover, :first-child, :last-child**

  2. 내부적용을 이용해서 다음 css를 적으시오. 
      h1 중앙정렬, 글자색상 : #34495e, 아래쪽여백 : 40px 
          **h1{ text-align: center; color: #34495e ;    margin-bottom : 40px }**
  
  3. 여러개의 div태그에 .portfolio라는 클래스를 적용하고  
    각각의 배경을다르게 설정하려고 한다.  .p1은 배경 red,    .p2는 gold 
    html 설정에 css를 적용하는 코드를 적으시오

      **<div class="portfolio p1"></div>**
      **<div class="portfolio p2"></div>**

  
  4.    .portfolio마우스를 올렸을때    확대 + 회전 + 밝기 + 그림자 강조  css를 적으시오.
  5.   가상선택자의 종류는? 
    5-1.   마우스를 올렸을때 **:hover**
    5-2.   부모안에서 첫번째 자식요소 **:first-child**
    5-3.   부모안에서 마지막 자식요소 **:last-child**

  6.   .portfolio ul태그의 첫번째 li를 선택해서 좋아하는 배경색으로 넣기 
  **.portfolio ul li:first-child { background-color: gold; }**
  

■2.  java

1. if 버젼
    1을 입력받으면 1이다   / 2를 입력받으면 2이다 / 3을 입력받으면 3이다.
2. switch 버젼
    위의 내용을 switch 버젼으로 
3. 다음 무한반복을 빠져나오는 코드를 적으시오
      int a = -1;
      
      for(;;){ 
         System.out.println("빠져나오실래요?  0이면 종료");
         a = scanner.nextInt();
         if(a==0) {        }
      }