###  Spring
■ 기술스택 : spring + mysql  + mybatis  + security + jstl + ajax

- [ ] 1. project 만들기
    1. dynamic web project - project2
    2. configure  - [Convert to Maven Project]
    3. spring      - add Spring project Nature
    4. java se-11 / project facts, build path
    5. JUnit4 추가
    6. resources 추가

> src/main/resources 
-> Soure Folder 추가

```xml
	<resources>
		<resource>
			<directory>src/main/resources</directory>
		</resource>
	</resources> 
```

- [ ] 2. pom.xml
: 부품 객체 다운로드 - Maven Dependencies
:각각의 부품들이 무슨역할, 어떤 부품들이 있는지 확인



```xml
  <dependencies>
   <!-- TEST Unit  --> 
   <!-- https://mvnrepository.com/artifact/junit/junit -->
   <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
   </dependency>
   
   <!-- spring di, context-info  --> 
   <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
   <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.3.27.RELEASE</version>
   </dependency>
   
   
   <!-- spring test tool --> 
   <!-- https://mvnrepository.com/artifact/org.springframework/spring-test -->
   <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>4.3.27.RELEASE</version>
      <scope>test</scope>
   </dependency>
   
   <!-- LOMBOK : getters/setters, constructor, toString -->      
   <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
       <version>1.18.18</version>
       <scope>provided</scope>
   </dependency>   
    
    <!--  MYSQL/ORACLE    
   <dependency>
      <groupId>com.oracle.database.jdbc</groupId>
      <artifactId>ojdbc11</artifactId>
      <version>21.9.0.0</version>
   </dependency> -->
   
   <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.28</version>
   </dependency>
   
   <!-- jdbc -->
   <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-jdbc</artifactId>
       <version>4.3.20.RELEASE</version>
   </dependency>
         
   <!-- MYBATIS - mapper -->
   <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
   <dependency>
       <groupId>org.mybatis</groupId>
       <artifactId>mybatis</artifactId>
       <version>3.5.6</version>
   </dependency>
   
   <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
   <dependency>
       <groupId>org.mybatis</groupId>
       <artifactId>mybatis-spring</artifactId>
       <version>2.0.6</version>
   </dependency> 
   
   
   <!-- HikariCP : connector pool  -->
   <!-- https://mvnrepository.com/artifact/com.zaxxer/HikariCP -->
   <dependency>
       <groupId>com.zaxxer</groupId>
       <artifactId>HikariCP</artifactId>
       <version>2.7.4</version>
   </dependency>
      
   <!--  sql query  -->   
   <!-- https://mvnrepository.com/artifact/org.bgee.log4jdbc-log4j2/log4jdbc-log4j2-jdbc4 -->
   <dependency>
       <groupId>org.bgee.log4jdbc-log4j2</groupId>
       <artifactId>log4jdbc-log4j2-jdbc4</artifactId>
       <version>1.16</version>
   </dependency>
      
         
   <dependency>
     <groupId>org.apache.logging.log4j</groupId>
     <artifactId>log4j-core</artifactId>
     <version>2.17.2</version>
   </dependency>
   <dependency>
     <groupId>org.apache.logging.log4j</groupId>
     <artifactId>log4j-api</artifactId>
     <version>2.17.2</version>
   </dependency> 
   <dependency>
     <groupId>org.apache.logging.log4j</groupId>
     <artifactId>log4j-slf4j-impl</artifactId>
     <version>2.17.2</version>
   </dependency>       
      
      
   
   <!-- spring-webmvc -->   
   <!-- spring-webmvc -->   
   <!-- spring-webmvc -->    
   <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-webmvc</artifactId>
       <version>4.3.27.RELEASE</version>
     </dependency>
   <!-- jstl -->    
   <!-- jstl -->    
     <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
   <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
   </dependency>
   
   <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
   </dependency>     
      
      
<!-- img upload -->   
<!-- img upload -->    
   <!-- commons-fileupload -->
   <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
   </dependency>

   <!-- commons-io -->
   <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.11.0</version>
   </dependency>
<!-- img upload -->   
<!-- img upload -->          
      
      
      <!-- SECURITY -->
      <!-- SECURITY -->
      <!-- SECURITY -->
      <!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-core -->
      <dependency>
         <groupId>org.springframework.security</groupId>
         <artifactId>spring-security-web</artifactId>
         <version>4.2.2.RELEASE</version>
         <!-- <version>5.0.7.RELEASE</version> -->
      </dependency>
      <dependency>
         <groupId>org.springframework.security</groupId>
         <artifactId>spring-security-config</artifactId>
         <version>4.2.2.RELEASE</version>
      </dependency>
      <dependency>
         <groupId>org.springframework.security</groupId>
         <artifactId>spring-security-core</artifactId>
         <version>4.2.2.RELEASE</version>
      </dependency>
      <dependency>
         <groupId>org.springframework.security</groupId>
         <artifactId>spring-security-taglibs</artifactId>
         <version>4.2.2.RELEASE</version>
      </dependency>
      <!-- SECURITY -->
      <!-- SECURITY -->
      <!-- SECURITY -->      
      <!-- jackson -->
      <!-- jackson -->
      
      <!-- https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-mapper-asl -->
      <!-- <dependency> <groupId>org.codehaus.jackson</groupId> <artifactId>jackson-mapper-asl</artifactId> 
         <version>1.9.13</version> </dependency> -->
      <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-databind</artifactId>
         <version>2.17.2</version> <!-- 최신 안정 버전 사용 -->
      </dependency>
      <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-core</artifactId>
         <version>2.17.2</version>
      </dependency>
      <dependency>
         <groupId>com.fasterxml.jackson.core</groupId>
         <artifactId>jackson-annotations</artifactId>
         <version>2.17.2</version>
      </dependency>
      
  </dependencies>
  
```


- [ ] 3. web.xml
1. UTF-8 인코딩

```xml
<!-- UTF-8 인코딩 -->
   <filter>
       <filter-name>encodingFilter</filter-name>
       <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
       <init-param>
           <param-name>encoding</param-name>
           <param-value>UTF-8</param-value>
       </init-param>
       <init-param>
           <param-name>forceEncoding</param-name>
           <param-value>true</param-value>
       </init-param>
   </filter>
   
   <filter-mapping>
       <filter-name>encodingFilter</filter-name>
       <url-pattern>/*</url-pattern>
   </filter-mapping>
```

2. 스프링 구동 (ctrl + space) -> #contextloaderlistener

```xml
<param-value>classpath:config/*-context.xml</param-value>

<!-- security -->
   <filter>
      <filter-name>springSecurityFilterChain</filter-name>
      <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
   </filter>
   <filter-mapping>
      <filter-name>springSecurityFilterChain</filter-name>
      <url-pattern>/*</url-pattern>
   </filter-mapping>
   
```

3. 경로 처리 (ctrl + space) -> #dispatcherservlet

```xml
<param-value>classpath:config/servlet-context.xml</param-value>
<!-- -->
<url-pattern>/</url-pattern>
```

- [ ] 4. confing  
    1. root-context.xml
beans, context, jdbc, mybatis

    2. servlet-context.xml
    (1번 복사)
    
    3. security-context.xml
    (1번 복사)



- [ ] 5. 각종설정파일
1. root-context.xml
1-1. config/mybatis-config.xml
1-2. config/test-mapper.xml
1-3. config/db.properties
1-4. com.the703.dao.Mapper ->@Mapper

1-5. resources/log4j2.properties
1-6. resources/log4jdbc.log4j2.properties


- [ ] 6. 부품테스트
..................................

 [ ] 7. 개발 - Model
    0) 테이블
    1) boardDto   -  mybatis-config.xml
    2) BoardMapper , board-mapper.xml   - root-context.xml
    3) BoardService, BoardServiceImpl

- [ ] 8. 개발 - Controller
    4) BoardController  - Service 사용 -  security-context.xml (허용)

- [ ] 9. 개발 - View
    5) [view]-[board]     - csrf
 