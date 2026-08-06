1. CSR VS SSR
- SSR : 서버가 웹 페이지의 렌더링, 초기 속도가 빠르고 서버 부하가 커지고 깜빡임
- CSR : 브라우저가 웹페이지를 렌더링, 초기속도 느리고 화면 바뀜이 빠름

2. 기술스택 - SSR + CSR 
[PROJECT]
└─ BACK : boot + oracle + jpa + (mybatis) + jwt + redis
└─ FRONT : react + next + antd

1. JAVA17
2. SPRING BOOT (gradle)
3. security + jwt + redis + oauth2.0 + jpa + mybatis + orcale

1) spring boot - 애플리케이션기반의 프레임워크 / 내장 tomcat
2) spring security - 인증, 인가/ 필터체인의 요청보호/ oauth2.0(외부인증-카카오,네이버,구글)와 쉬운 연동
3) mybatis - xml sql 복잡한 쿼리 작성
4) jwt - json web token / 토큰 기반의 인증방식
         서버가 세션을 직접 관리하지 않고, 클라이언트가 토큰을 보관
5) redis - 캐시 / 세션 관리, refresh token을 저장,
           캐싱처리(자주사용하는 값을 미리 넣어놓고 요청이 있을때
           서버 거치지 않고 빠르게 제공)에 활용
6) jpa - sql 작성없이 객체 중심의 처리

1.  SPRING boot  → 애플리케이션 실행기반
2.  SPRING security + jwt/oauth2.0   → 인증/인가 처리
3.  redis   →  토큰/세션/캐시관리
4.  jpa + mybatis  → 데이터베이스 접근 (orm + sql mapper 병행)

##### [실습]  1. 스프링부트 프로젝트 
- [x] 1. 개발개요안내
- [x] 2. java.sun.com - JAVA 17 다운로드 - 설치
- [x] 3. SPRING BOOT   - https://spring.io/ - 다운로드 - 설치
  > 이전버젼
  https://github.com/spring-projects/spring-tools/wiki/Previous-Versions
- [x] 4. SPRING BOOT 프로젝트 만들기
- [x] 5. lombok

##### [실습]  2. docker 설치
- [x] 1. docker 설치
- https://www.docker.com/products/docker-desktop/ (AMD)
- 다운로드 및 설치 -> 1. window 업데이트 / 2. use WSL 2
instead.... 체크 확인

```bash
wsl --update
```
```bash
docker --version
docker ps
```

- [ ] 2. redis 설치 
```
docker pull redis
docker run -d --name my-redis -p 6379:6379 redis

docker exec -it my-redis redis-cli
docker exec -it my-redis redis-cli FLUSHALL
keys *
get 저장이름
```



1.  JWT  VS  세션
- 세션 : 서버 메모리에 사용자 상태를 저장 →  서버확장시 부담  
                                    (서버에서 출입명단 직접 들고 있는 것)
- JWT(Json Web Token) : 토큰 자체에 인증정보를 포함  → 확장성
                                    (사용자가 출입증을 직접 들고다니기)

2.  Access  Token vs  Refresh Token 
1) Access  Token :  짧은 기간 유효(출입증)    → api 호출 시 사용    
2) Refresh Token :  긴   기간 유효(장기체류증) →  redis 냉장고에 안전보관   

3. Redis 사용이유?
- 토큰냉장고 → 장기체류증 안전하게 보관, 필요시 꺼내 씀
- Refresh Token 중앙에서 관리
- TTL(만료 시간)로 자동 만료처리
- 로그아웃 시 즉시 삭제  

```필기
docker pull redis                                  --> 최신버전 redis 다운로드
docker run     -d        --name my-redis -p 6379:6379 redis   
--> 생성 및 실행 백그라운드 생성될이름         내컴퓨터6379 번호로 내부에 6379로 연결

docker exec -it my-redis redis-cli
-->             i: 표준입력, t:가상터미널
docker exec -it my-redis redis-cli FLUSHALL
keys *
get 저장이름
```

#### [실습] 3. oracle 유저 셋팅

```sql
-- cmd
-- sqlplus
-- conn  system/1234
 
-- 유저만들기 ( 오라클 12 이상에서 기존방식으로 사용자 생성 허용 )
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
create user boot  identified by react;

-- 권한부여
grant  connect , resource  to boot;

ALTER USER boot DEFAULT TABLESPACE users QUOTA UNLIMITED ON users;    -- 물리적공간이용
grant  create table to boot;

```

#### [실습] 4. Boot + React ver1 (기본게시판 + 회원가입)

1. board
- [x] 1. project
- [x] 2. 부품객체: gradle
※ https://mvnrepository.com/: 놓친 부품 챙겨오기
- [x] 3. application.yml ★
```
spring:
  datasource:
    url: jdbc:oracle:thin:@localhost:1521/XE     # jdbc url
    username: boot                               # 사용자 계정
    password: react                              # 비밀번호
    driver-class-name: oracle.jdbc.OracleDriver  # oracle, mysql,,,

  jpa:
    hibernate:
      ddl-auto: update    # 엔티티 변경사항 DB 테이블 자동으로 변경사항 반영
                          # (update: 수정반영, 기존 데이터 유지) / create-drop : 생성 후 삭제, 매번 초기화
                          # 배포할때는 none (기본)
    properties:
      hibernate:
        format_sql: true  # 콘솔 및 로그에 출력되는 sql 들여쓰기 속성
        show_sql: true    # sql 쿼리 문자을 그대로 로그 출력

  servlet:
    multipart:
      enabled: true          # 파일 업로드 처리 기능 활성화
      max-file-size: 10MB    # 업로드하는 최대허용용량
      max-request-size: 20MB # 한번에 전송되는 총 용량

  data:
    redis:
      host: localhost        # redis 연결주소
      port: 6379             # 서버포트
      timeout: 2000          # 

  config:
    import: 
      - optional:application-oauth.yml
      - optional:file:.env[.properties]


mybatis:
  config-location: classpath:mybatis-config.xml # 전역설정파일
  mapper-locations: classpath:mapper/**/*.xml   # 맵퍼 경로패턴
  type-aliases-package: com.thejoa703.domain    # 도메인 설정

jwt:
  issuer: thejoa703                             # jwt 토큰 발행한 주체자
  secret: ${JWT_SECRET}                         # 사용할 비밀키 - 외부환경변수에서 불러와서 설정
  access-token-exp-seconds: 900                 # 유효시간
  refresh-token-exp-seconds: 1209600            #
  header: Authorization                         # 토큰 전달시 http 요청헤더 이름 지정
  prefix: Bearer                                # 토큰 앞에 붙는 이름 (접두사) 

file:
  upload-dir: uploads                           # 업로드된 파일설정 경로

#server:
#  port: 8484s
```

※ (db:table) -> mapper     -> dto  -> service -> controller -> view
※ @Entity    -> repository -> dto  -> service -> controller -> view
- [x] 4. entity (테이블을 객체로 처리 @Entity)
  back1
   └ src/main/java
     └ com.thejoa703.entity
       ─ AppUser
       ─ Post

  A. JPA
   - ORM(Object-Relational Mapping)
   부품객체(자바클래스)와 RDB(관계형데이터베이스)의 불일치 해결을 위해
   SQL 중심이 아니라 객체 중심으로 데이터를 다룰수 있게 해주는 기술
   - 1. @Entity DB의 테이블과 맵핑
   - 테이블 컬럼 변경시 SQL을 일일이 수정할 필요없이 엔티티 클래스만 수정
   - 데이터베이스 방언 (Dialect) 지원 - oracle, mysql 특정 데이터에 종속

  - 2. JpaRepository - db에 접속해서 crud 작업을 처리하는 인터페이스
  - 3. 외래키 설정
    한사람이 여러 글을 작성할 수 있다.
    > AppUser
    @OneToMany

    > Post
    @ManyToOne


- [ ] 5. DTO 
  back1
   └ src/main/java
     └ com.thejoa703.dto
       └─ UserDto
       └─ PostDto

```java
DTO class
- RequestDto class   // 요청따로
- ResponseDto class  // 응답따로
```
- [x] 6. Repository 
  back1
   └ src/main/java
     └ com.thejoa703.repository
       └─ AppUserRepository
       └─ PostRepository
https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

- [x] 7. Service
  back1
   └ src/main/java
     └ com.thejoa703.service
       ─ UserService
       ─ PostService

★ 더티 체킹

더티 체킹(Dirty Checking)은 JPA에서 트랜잭션이 끝나는 시점에 조회했던 엔티티의 값이 변경되었다면, 별도의 save()나 update() 쿼리 없이 알아서 데이터베이스에 UPDATE 쿼리를 날려주는 기능입니다.

★ 동작 방식  
> 1. postRepository.findById(postId)로 엔티티를 조회하면, JPA는 이 시점의 최초 상태를 스냅샷으로 만들어 영속성 컨텍스트에 저장합니다.
> 2. post.setContent(content)로 엔티티의 값을 수정합니다.
> 3. 메서드가 정상 종료되어 @Transactional 트랜잭션이 커밋될 때, JPA는 최초 스냅샷과 현재 엔티티의 상태를 비교(체킹)합니다.
> 4. 값이 다르면 변경된 부분을 감지하고 자동으로 UPDATE 쿼리를 생성해서 데이터베이스에 반영합니다.

- [x] 8. Controller
```
back1
  └ src/main/java
  └ com.thejoa703.controller
  ─ UserController
  ─ PostController

  1. User Api    - 사용자 관련 API
  - POST   /api/users        회원가입
  - GET    /api/users/{id}   사용자 단건조회

  2. Post API     - 게시글 관련 API
  - GET        /api/posts/{id}      게시글 단건 조회
  - PUT        /api/posts/{id}      게시글 수정
  - DELETE     /api/posts/{id}      게시글 삭제
  - GET        /api/posts           전체 게시글 조회
  - POST       /api/posts           게시글 작성
```
※ Swagger는 갱신 안되므로 서버 다시 재부팅

- [x] 9. View
1. 회원가입
   ↓
2. 마이페이지
   ↓
3. 글쓰기
   ↓
4. 글수정   
   ↓
5. 글삭제    

Step1) 프로젝트 만들기
```
mkdir front1
cd front1
npm init
```

Step2) 기본 셋팅(store)
```
npm install
```
```md
front/
├── .next/                  # Next.js 빌드 결과물 (자동 생성, 배포 시 사용)
├── components/         # 재사용 가능한 UI 컴포넌트 폴더
│   └── Layout.js         # 페이지 공통 레이아웃 컴포넌트
├── node_modules/       # 설치된 npm 패키지들
├── pages/                  # Next.js 라우팅 기반 페이지 폴더
│   ├── posts/             
│      └──new.js       #  글쓰기 파일
│   ├── _app.js             # 전체 앱의 공통 설정 (Redux Provider, 글로벌 스타일 등)
│   ├── join.js              # 회원가입
│   ├── mypage.js         # 마이페이지
│   └── index.js            # 메인 페이지
├── reducers/               # Redux 리듀서 폴더
│   ├── __tests__/       
│   │   ├── post.test.js        # 게시판 테스트 코드 
│   │   └── user.test.js        # 리듀서 테스트 코드
│   ├── index.js            # 루트 리듀서 (combineReducers)
│   ├── authReducer.js             # 사용자 관련 리듀서
│   └── postReducer.js             # 게시판 관련 리듀서 
├── sagas/                  # Redux-Saga 폴더
│   ├── __tests__/       
│   │   ├── post.test.js     # 게시판 사가 테스트 코드
│   │   └── user.test.js      #  유저   사가  테스트 코드
│   ├── index.js            # 루트 사가
│   ├── authSaga.js             # 사용자 관련 사가
│   └── postSaga.js             # 게시판 관련 사가 
├── store/                  # Redux 스토어 설정 폴더
│   ├── configureStore.js   # Redux 스토어 설정
│   └── configureStore.test.js # 스토어 테스트 코드
├── styles/                 # CSS 스타일 폴더
│   └── globals.css         # 글로벌 스타일
├── .babelrc                # Babel 설정 파일
├── .eslintrc               # ESLint 설정 파일
├── package-lock.json       # npm 의존성 잠금 파일
├── package.json            # 프로젝트 메타 정보 및 의존성
└── setupTests.js           #  테스트 환경 설정 파일

```


Step3) reducer
Step4) saga
Step4) view

```
├── pages/                  # Next.js 라우팅 기반 페이지 폴더
│   ├── posts/             
│      └──new.js       #  글쓰기 파일
│   ├── _app.js             # 전체 앱의 공통 설정 (Redux Provider, 글로벌 스타일 등)
│   ├── join.js              # 회원가입
│   ├── mypage.js         # 마이페이지
│   └── index.js            # 메인 페이지
  ```
<Link href="/">          index.js     # 메인페이지
<Link href="/mypage">    mypage.js    # 마이페이지
<Link href="/signup">    signup.js    # 회원가입
<Link href="/post/new">  posts/new.js # 글쓰기 파일

## (1) : 회원가입 + board (CRUD) - 보안빠진 버전
## (2) : 멤버기능 + board (이미지업로드, 해쉬태그, 좋아요, 팔로우)(CRUD)
boot2  - 프로젝트 만들기
- table -> mapper -> service -> controller
- @Entity -> repository -> service -> restController

1. 유저는 많은 글을 쓸 수 있다.
<AppUser> -> <Post>

<AppUser>
@OneToMany( mappedBy = "user" ,cascade = CascadeType.ALL, orphanRemoval = true )
private List<Post> posts = new ArrayList<>(); 

<Post>
@ManyToOne   //1. 다대일
@JoinColumn(name="APP_USER_ID" , nullable = false)
private AppUser user; 

2. 글은 많은 이미지를 갖는다.
<Post> -> <Image>
3. 글은 많은 해쉬태그를 갖는다 / 해쉬태그는 많은 글을 갖는다.
1) 다 : 다
2) 중간 테이블
<Post> -> <Hashtag>
@ManyToMany
<Hashtag> -> <Post>
1 test
1 like
1 hot
2 test
2 like
2 hot

2) 글은 많은 좋아요를 갖는다.
하나의 글에 여러 유저가 좋아요를 누른다.
<Post>                                        <POST_LIKE>
@OneToMany List<POST_LIKE> likes;             @ManyToOne AppUser user;
<AppUser>                                     <POST_LIKE>
@OneToMany List<POST_LIKE> likes;             @ManyToOne Post post;


3) 리트윗

4) 팔로우

front2 - 프로젝트 복사하기


#### [실습] 5. Boot + React + session/cookie ver2 (기본게시판, 회원가입, 이미지/해쉬태그/좋아요/팔로우)
※entity -> repository -> service -> controller

회원가입 ( 이메일 중복 검사, 닉네임 중복 검사)
  ↓
로그인
  ↓
마이페이지 (닉네임 변경, 프로필이미지 변경, 회원 탈퇴) ※ 팔로워 / 팔로잉
1) UserRequestDto / UserResponseDto
UserRequestDto <email, password, nickname, ★image (ufile:Multipart 빠짐) / provider, moblie, mbtitype>
UserResponseDto <email, role, nickname, ★image  ufile / provider, moblie, mbtitype>
2) LoginRequst <email, password, provider>

게시글 관리
게시글 작성
  ↓
게시글 목록 (전체글 / 좋아요한글/ 내글+리트윗)
1. 각세부내용/수정/삭제
2. 좋아요, 리트윗, 댓글

3. FRONT 개발
1

#### [실습] 6. Boot + React + jwt + security + redis - ver3 (기본게시판, 회원가입, 이미지/해쉬태그/좋아요/팔로우)