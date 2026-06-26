desc appuser;

create table appuser (
  APP_USER_ID NUMBER(5) NOT NULL PRIMARY KEY,
  EMAIL VARCHAR2(100) NOT NULL,
  PASSWORD VARCHAR2(100),
  MBTI_TYPE_ID NUMBER(3),
  CREATED_AT DATE,
  UFILE VARCHAR2(255),
  MOBILE VARCHAR2(50),
  NICKNAME VARCHAR2(50),
  PROVIDER VARCHAR2(50) NOT NULL,
  PROVIDER_ID VARCHAR2(100)
);

create sequence appuser_seq;

CREATE TABLE authorities (
    AUTH_ID NUMBER(5) NOT NULL PRIMARY KEY,
    EMAIL VARCHAR2(255),
    AUTH VARCHAR(255) NOT NULL,
    APP_USER_ID NUMBER(5)
);

create sequence authorities_seq;


-- 회원가입
INSERT INTO appuser (APP_USER_ID, EMAIL, PASSWORD, MBTI_TYPE_ID, CREATED_AT, UFILE, MOBILE, NICKNAME, PROVIDER, PROVIDER_ID)
VALUES (appuser_seq.nextval, 'first@gmail.com', '111', 1, sysdate, '1.png', '01011111111', 'first', 'the703', 't7-1');

-- 로그인 (이메일로 이메일, 비번, 권한)
SELECT * 
FROM appuser u
LEFT JOIN authorities a
ON a.EMAIL = u.EMAIL
WHERE u.EMAIL = 'first@gmail.com' AND u.PASSWORD = '111';

-- 이메일로 유저 찾기
SELECT * FROM appuser where email='first@gmail.com';

-- 이메일 중복검사
SELECT count(*) FROM appuser where email='first@gmail.com';

-- 회원 수정
update appuser
set password='2222',
mbti_type_id=2,
ufile='2.png',
nickname='second',
mobile='01022222222',
provider='naver',
provider_id='n-1'
where app_user_id = 1;

-- 회원 삭제
DELETE FROM  appuser WHERE email = 'first@gmail.com';

-- 권한 삽입
INSERT INTO authorities (AUTH_ID, EMAIL, AUTH)
VALUES (authorities_seq.nextval, 'first@gmail.com', 'ROLE_MEMBER');

-- 권한 삭제
DELETE FROM  authorities WHERE email = 'first@gmail.com';
