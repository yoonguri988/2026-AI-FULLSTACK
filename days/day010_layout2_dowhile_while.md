# DAY010

## TODO1: CSS

### 

## TODO2: JAVA

###

### 복습 문제

1. 레이아웃잡는방법은? (  float  ,   position   ,   display   )
2. box를 왼쪽, 오른쪽에 붙이는 방법은? float: left, float: right
3. 다음박스를 왼쪽 오른쪽에 배치하려고 한다. 들어가야하는 코드는?
<style>
   .left{ float: left; }     .right{ float: right; }    .clear{ clear: both; }
</style>
<div class="box">
  <div class="left">LEFT</div>
  <div class="right">RIGHT</div>
  <div class="clear">안따라갈려고</div>
</div>

4. 다음li를 왼쪽으로 붙이려고한다. 코드는?
<style>
  ul{ overflow: hidden; }    li{ float: left; }
</style>
<ul>
 <li>ONE </li><li>TWO</li><li>THREE</li>
</ul>

5. 내가 원하는 위치에 top, right, bottom, left 속성을 줘서 원하는 위치에 배치하는 방법은?
 - 부모박스에  (  position: realtive;  )코드를 사용해   기준점을 잡고
 - 자식콘텐츠로  (   position: absolute;   )를 사용해 배치한다.

6.  스크롤할 때 상단에 계속 붙어 있도록 만드는 방법은?   position: fixed;

■ 2. java
1. if버젼에 해당하는 다음에 연결해서 문제를 작성하시오.
    문자를 한개 입력받아 a이면 apple , b이면 banana, c이면 coconut
2. switch버젼에 해당하는 다음에 연결해서 문제를 작성하시오.
    문자를 한개 입력받아 a이면 apple , b이면 banana, c이면 coconut

    char ch='\u0000';
    Scanner scanner =new Scanner(System.in);

    System.out.println("a,b,c 중에 입력 > ");  
    ch = scanner.next().charAt(0);
    
3. for, while, do while 버젼으로  문제를 풀으시오!  
     1  2  3  4  5