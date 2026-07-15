0. SSR vs CSR
1) SSR (Server-Side Rendering)
→ 서버가 완성된 html를 만들어서 클라이언트(브라우저)에게 보낸다.
기술 스택: BOOT + security + thymeleaf + oracle + mybatis
- 렌더링주체 : 서버
- 초기로딩   : 빠름
- 화면전환   : 새로고침

2) CSR (Client-Side Rendering)
→ 클라이언트(브라우저) html + js를 이용해서 화면 그리기
기술스택: node(BE) + react(FE)
- 렌더링주체 : 브라우저
- 초기로딩   : 느림
- 화면전환   : 부드러움

> backend  : boot + security + jwt + redis + jpa
> frontend : react + next

## 1. 프로젝트 구조
```markdown
back/
├── config/
│   └── db.js                  # Oracle DB 설정
├── middlewares/
│   └── isAuthenticated.js     # 로그인 인증 미들웨어
├── models/
│   └── users.js               # 사용자 DB 모델 및 쿼리 함수
├── passport/
│   ├── index.js               # Passport 초기화
│   └── local.js               # Local 전략 설정
├── routes/
│   └── user.js                # 사용자 관련 API 라우터
├── node_modules/              # npm 패키지
├── .env                       # 환경변수
├── app.js                     # 서버 진입점
├── package.json               # 프로젝트 설정 및 스크립트
├── package-lock.json          # 패키지 버전 고정
├── test1_model_testUsers.js   # 모델 함수 테스트 스크립트
```
https://nodejs.org/ko
1) 다운로드 및 설치
2) 확인
```
node -v
npm -v
```
3) 관리자 권한으로 power shell 실행
```
```

### 1) 프로젝트 만들기
```js
npm init
```

```js
mkdir back
cd back
npm init
```

### 2) 패키지 설치
```
npm install
```

```
{
  "name": "back",
  "version": "1.0.0",
  "main": "app.js",
  "scripts": {
    "dev": "nodemon app", -- 개발시
    "start": "cross-env NODE_ENV=production pm2 start app.js" -- 운영환경
  },
  "dependencies": {
    "aws-sdk": "^2.710.0",        -- aws 서비스 연동
    "bcrypt": "^5.1.0",           -- 비밀번호 암호화
    "cookie-parser": "^1.4.6",    -- 쿠키 데이터 파싱
    "cors": "^2.8.5",             -- 웹 보안 정책 cors
    "cross-env": "^7.0.3",        -- 운영체제별 환경변수 통일
    "dotenv": "^16.3.1",          -- 환경변수 파일관리
    "express": "^4.21.2",         -- node.js 웹서버 프레임워크 
    "express-session": "^1.17.3", -- 로그인 상태 세션관리
    "helmet": "^7.0.0",           -- 보안관련 http 헤더 설정
    "hpp": "^0.2.3",              -- http 파라미터 오염방지 보안
    "morgan": "^1.10.0",          -- 서버 요청 로그 기록
    "multer": "^1.4.5-lts.1",     -- 파일 업로드 미들웨어
    "oracledb": "^6.4.0",         -- oracle 
    "passport": "^0.7.0",         -- 인증처리
    "passport-local": "^1.0.0",   -- 이메일/비번 로그인
    "pm2": "^5.3.0",              -- 서버 무중단
    "swagger-jsdoc": "^6.2.8",    -- api 문서화엔진
    "swagger-ui-express": "^5.0.1"-- 페이지 처리
  },
  "devDependencies": {
    "nodemon": "^3.0.1"           -- 개발중에 코드변경 자동반영
  }
}
```

### 3) 서버진입점
```
back/
├── app.js                        # 서버 진입점
```

1. app.js
```
```

2. 실행
```
npx nodemon app.js
```

3. 브라우저 실행
브라우저에서 http://localhost:3065

## 2. 개발 

### 1) model
```
back/
├── config/
│   └── db.js                  # Oracle DB 설정
├── models/
│   └── users.js               # 사용자 DB 모델 및 쿼리 함수
├── .env                       # 환경변수
├── test1.js                   # 모델 함수 테스트 스크립트
```

#### 1. id: node / pass: react oracle 유저 만들기
```sql
-- cmd
-- sqlplus
-- conn system/1234

-- 유저만들기 ( 오라클 12 이상에서 기존방식으로 사용자 생성 허용 )
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
create user node  identified by react;

-- 권한부여
grant  connect , resource  to node;

ALTER USER node DEFAULT TABLESPACE users QUOTA UNLIMITED ON users;    -- 물리적공간이용
grant  create table to node;

```

#### 2. appuser 테이블
```sql
DROP  TABLE APPUSER  CASCADE CONSTRAINTS;

CREATE TABLE APPUSER (
    APP_USER_ID     NUMBER NOT NULL,
    EMAIL           VARCHAR2(255) NOT NULL UNIQUE,
    PASSWORD        VARCHAR2(255) NOT NULL,
    NICKNAME        VARCHAR2(100),
    MOBILE          VARCHAR2(20),
    MBTI_TYPE_ID    NUMBER,
    UFILE           VARCHAR2(255),
    CREATED_AT      DATE DEFAULT SYSDATE,
    CONSTRAINT PK_APPUSER PRIMARY KEY (APP_USER_ID)
);

CREATE SEQUENCE APPUSER_SEQ;

desc APPUSER;
```

#### 3. .env
```
# 세션 쿠키 암호화 키
COOKIE_SECRET=appsecret

# Oracle DB 접속 정보
DB_USER=node
DB_PASSWORD=react
DB_CONNECT=localhost:1521/XE
```

#### 4. [config] - db.js

#### 5. [models] - users.js
```
// 1. create - insert
insert into APPUSER (APP_USER_ID, EMAIL, PASSWORD, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT) 
values (APPUSER_SEQ.NEXTVAL, :email, :password, :nickname, :mobile, :mbtiTypeId, :ufile, :createdAt)
// 2. 사용자 조회 - email
SELECT APP_USER_ID, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT 
FROM APPUSER 
WHERE EMAIL = :email
// 3. 사용자 조회 - id
SELECT APP_USER_ID, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT 
FROM APPUSER 
WHERE APP_USER_ID = :id
// 4. 로그인 - pass / 로그아웃

// 5. 전체 조회
SELECT APP_USER_ID, EMAIL, NICKNAME, MOBILE, MBTI_TYPE_ID, UFILE, CREATED_AT 
FROM APPUSER 
ORDER BY CREATED_AT DESC
// 6. 닉네임수정
UPDATE APPUSER 
SET NICKNAME = :nickname 
WHERE APP_USER_ID = :id
// 7. 사용자 삭제
DELETE FROM APPUSER 
WHERE APP_USER_ID = :id
// 8. 닉네임조회
SELECT APP_USER_ID, EMAIL, NICKNAME 
FROM APPUSER 
WHERE NICKNAME = :nickname
```

#### 6. 모델함수 테스트

### 2) controller
```markdown
back/
├── middlewares/
│   └── isAuthenticated.js     # 로그인 인증 미들웨어
├── passport/
│   ├── index.js               # Passport 초기화
│   └── local.js               # Local 전략 설정
├── routes/
│   └── user.js                # 사용자 관련 API 라우터 
├── app.js
```
1. [routers] - user.js
```
주소경로
post : /user/register (requestBody)
post : /user/login    (requestBody)
post : /user/logout   
get  : /user/
patch: /user/{id}/nickname
※비교  /user/nickname?id=1
delete: /user/{id}

patch: /user/{id}/nickname   ← rest 방식   데이터 접근 방식 : url 자원소스가 포함
※비교  /user/nickname?id=1   ← 쿼리스트링   데이터 접근 방식 : url?key=value

http 표준프로토콜 사용
- GET(조회) POST(생성) FATCH/PUT(수정) DELETE(삭제)

```

2. app.js
3. [passport] - index.js / local.js
4. [middlewares] - isAuthenticated.js




