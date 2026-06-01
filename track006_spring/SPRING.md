1. SPRING?

--------------------------------
#1. SPRING?
--------------------------------
▶ Step0. jsp
1. mvc1    -  jsp( controller )역할
2. mvc2 - servlet( controller )역할

예) JSP = html + java → web


▶ Step1.  framework
1. framework  [  디자인패턴 + 라이브러리 = 프레임워크 ]
2. 소프트웨어 개발의 뼈대역할



--------------------------------
#2. DI
--------------------------------
▶ Step2.  ioc + di
1. ioc
 - inversion of control
 - 제어의 역전
 - 인스턴스의 생성~소멸까지 생명주기를 개발자가 아니라 컨테이너가 하는 것
 - pojo ( plain old java object)

  class A{}
  A a1 = new A()

  생성 → 초기화 → 서비스  → 소멸
    ↑                                      ↓
     ----------------------------------
 
   ioc가 아닌경우   - [ 개발자★  → ( Class A)  
                                → ( Class B)
                                  → ( Class C)     ] 컨테이너

   ioc인      경우   - [ 개발자  ← ( Class A)  
                                ← ( Class B)
                                  ← ( Class C)★     ] 컨테이너



2. di ( dependency injection : 의존성주입 )
- 각 클래스간의 의존관계를  [설정파일]을 통해 [컨테이너]가 자동으로 연결
  장점 : 코드단순화 / 결합도 제거



▶ Step3.  실습
===================
실습(1) 설치
===================
* 버젼다운그레이드
  0. java.sun.com   -  java11   / build path
   JAVA_HOME   C:\Program Files\Java\jdk-11
   path                      %JAVA_HOME%\bin             - 맨위로
              
  1. 스프링 3버젼 다운로드
  2. 다운로드 경로
      https://github.com/spring-attic/toolsuite-distribution/wiki/Spring-Tool-Suite-3
  3. 압축 - 경로짧게 / 공백, 한글,특수기호(-) 경로에있으면 빼기 
 

===================
실습(2) spring setting
===================
    1. dynamic web project - spring001
    2. configure  - [Convert to Maven Project]
    3. spring      - add Spring project Nature
    4. java se-11 / project facts, build path
    5. build path - add Libraries - JUnit 4

    구조확인
   1. pom.xml         설치 다운로드
              2. src/main/java   실제 자바파일들 위치
              3. src/test/java    실제 테스트파일위치
              4. src/main/webapp   jsp 파일들위치

   연습문제)  ex1  프로젝트만들기


===================
실습(3)  SPRING 정리
===================
1.  프레임워크
   - 소프트웨어개발의 뼈대역할 [디자인패턴 + 라이브러리]
2.  IOC
   - 인스턴스 생성~소멸까지 생명주기를 스프링이 관리 →
3.  DI
   - 각클래스의 의존관계를  [설정파일]을 통해서 컨테이너가 자동연결
4.  BEAN
   -  스프링이 관리하는 객체 (부품)
   - beanFactory   ← ApplicationContext

    [AnimalFarm]  (사용)→    [<<interface>>Animal]   
     ↑(삽입)                   ↑(구현)       ↑ (구현)   
    [beans.xml ]  (생성)→     [Cat     /   Dog]

    >> di? 각클래스의 의존관계를 [설정파일]을통해서 컨테이너가 자동연결


    [실습]
    https://projectlombok.org/download

    1)버전에 맞게 다운로드
    2)c 드라이브에 위치
    3)cmd
    java -jar lombok-1.18.18.jar
    4)sts 설치 위치 - 이클립스 선택 - install

6. di 연습문제)

1) ex1 스프링프로젝트
2) 구성확인

[IceCreamShop]  (사용) →   [<<interface>> IceCream]   
   ↑(삽입)                     ↑(구현)         ↑(구현)   
 [beans.xml] (생성)    →   [ Vanilla / Chocolate ]

 >> di? 각 클래스의 의존관계를 [설정파일]을 통해 컨테이너가 자동연결

1) 인터페이스
com.company.ioctest

public interface IceCream {
    public String flavor();
    public String scoop();
    public String melt();
}
2) 구현클래스
com.company.ioctest

public class Vanilla implements IceCream {
    @Override public String flavor() { return "Vanilla-flavor"; }
    @Override public String scoop()  { return "Vanilla-scoop"; }
    @Override public String melt()   { return "Vanilla-melt"; }
}

public class Chocolate implements IceCream {
    @Override public String flavor() { return "Chocolate-flavor"; }
    @Override public String scoop()  { return "Chocolate-scoop"; }
    @Override public String melt()   { return "Chocolate-melt"; }
}


3) 사용클래스 - IceCreamShop

package com.company.ioc;
public class IceCreamShop {
    private String shopName;
    private IceCream iceCream;

    public IceCreamShop() { super(); }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public IceCream getIceCream() { return iceCream; }
    public void setIceCream(IceCream iceCream) { this.iceCream = iceCream; }

    public String serveFlavor() { return shopName + ">" + iceCream.flavor(); }
    public String serveScoop()  { return shopName + ">" + iceCream.scoop(); }
    public String serveMelt()   { return shopName + ">" + iceCream.melt(); }

    public void print() {
        System.out.println(serveFlavor());
        System.out.println(serveScoop());
        System.out.println(serveMelt());
    }
}



QUESTION1) DI - property 를 이용하여 셋팅하고 JUnit Test를 하시오
--1-1 com.company.ioc1 [IceCreamShop, Vanilla , Chocolate]
public class IceCreamShop {
    private String shopName;
    private IceCream1 iceCream;
}

--1-2 com.company.config [test1.xml]
<!-- Vanilla vanilla = new Vanilla() -->
<!-- Chocolate chocolate = new Chocolate() -->
<!-- IceCreamShop shop = new IceCreamShop()
     shop.setShopName("SweetHouse");
     shop.setIceCream(vanilla);
-->

--1-3 JUnit Test  
public void test1() {
    IceCreamShop shop = (IceCreamShop) context.getBean("iceCreamShop");
    shop.print();
}


QUESTION2) DI - component-scan, properties 를 이용해서 셋팅하고 JUnit Test를 하시오
--1-1 com.company.config [test2.xml]
<context:component-scan base-package="com.company.ioc2"/>
<context:property-placeholder location="classpath:shop.properties"/>

--1-2 com.company.ioc2 [IceCreamShop, Vanilla , Chocolate]
 
--1-3 JUnit Test  
public void test1() {
    IceCreamShop shop = (IceCreamShop) context.getBean("iceCreamShop");
    shop.print();
}


