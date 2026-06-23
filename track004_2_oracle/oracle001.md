□ 1. oracle
- 데이터 베이스 언어
1) 데이터 정의어 (DDL): Create, Alter, Drop
2) 데이터 조작어 (DML): Insert, Select, Update, Delete
3) 데이터 제어어 (DCL): Grant, Revoke

- 1. oracle 설치
- 2. sql developer 설치 (sql 편집)
- 3. 사용

<실습1>
```sql (cmd)
sqlplus
conn system/1234

-- 유저 만들기 (오라클 12 이상에서 기존방식으로 사용자 생성 허용)
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
create user scott identified by tiger; -- ★

-- 권한 부여
grant connect, resource to scott; -- ★

ALTER USER scott DEFAULT TABLESPACE users QUOTA UNLIMITED ON users; -- 물리적 공간 이용
grant create table to scott; -- ★
```

<실습2>
```sql (sqldeveloper)
--1. 테이블 만들기
-- 컬럼명 자료형 옵션
create table dept(
    deptno number primary key,

);
--2. DML (CRUD)

```


---

□ 2. boot