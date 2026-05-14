## 3. TABLE

### 1. RDBMS (Relational DataBase Management System)

- 관계형 데이타베이스 관리 시스템
- 테이블의 관계
- 속성(필드) 연결

### 2. 테이블 만들기 (집안의 방, 가방 안의 분류표)

DDL[정의] (CREATE, ALTER, DROP), DML[조작] (INSERT, SELECT, UPDATE, DELETE), DCL[제어] (GRANT, REVOKE)
-----------------------------------
CREATE TABLE TABLE명 (
  필드1 자료형 옵션,
  필드2 자료형 옵션
)
-----------------------------------
자료형 :
  A. 숫자: int (정수, 1,2,3, ...), double(실수, 1.23 ...)
  B. 문자: char(고정, 남/여)      , varchar(가변, abc, abcd, abcdefgh)
  C. 날짜: date, datetime
옵션 : 
  필수 입력: not null
  숫자 자동 증가 - auto_increment
  기본 키 - primary key

#### [실습1]

create table t1 (
  name varchar(100) not null, -- 필수 입력
  age int
);

create table t11 (
  no   int         not null,
  name varchar(30) not null
);

create table t12 (
  bookid int         not null,
  title  varchar(100) not null
);

show tables;
desc table

※ ERROR 1046 (3D000): No database selected
use db명

mysql> show databases;
mysql> use mbasic;
mysql> status;  -- 상태 확인

mysql> show tables; -- 테이블 목록 확인
mysql> desc t1;     -- 구조 확인

※ 참고 사항) not null 필수 입력
mysql> insert into t1 (age) values (1);
ERROR 1364 (HY000): Field 'name' doesn't have a default value (값넣어!)

mysql> insert into t1 (name, age) values ('aaa', 1);
Query OK, 1 row affected (0.00 sec)

mysql> insert into t1 (name) values ('bbb');
Query OK, 1 row affected (0.00 sec)

mysql> select * from t1;
+------+------+
| name | age  |
+------+------+
| aaa  |    1 |
| bbb  | NULL |
+------+------+
2 rows in set (0.00 sec)

#### [실습2] auto_increment (숫자 자동증가), primary key(기본 키)
create table t2 (
  jumin int primary key auto_increment,
  name varchar(100) not null,
  age int
);

※ 참고사항)
insert into  t2 (name, age)  values ('aaa' , 1);   -- 숫자자동증가
insert into  t2 (name)  values ('bbb');            -- 숫자자동증가
insert into  t2 (jumin, name, age)  values (1 , 'ccc' , 1);  -- error 기본키
insert into  t2 (jumin, name, age)  values (3 , 'ccc' , 1);  

mysql> select * from t2;
+-------+------+------+
| jumin | name | age  |
+-------+------+------+
|     1 | aaa  |    1 |
|     2 | bbb  | NULL |
|     3 | aaa  |    1 |  <-- aaa, 1 / aaa,1 구분을 해줄수 있는 필드는  jumin  1,3  
+-------+------+------+
2 rows in set (0.00 sec)


------------------------------------------------------------
------------------------------------------------------------ [연습문제]
[001]  다음과 같이 DB와 테이블을 만드시오        >> coffee
커피번호 : cno    int           필수입력     primary key
커피이름 : cname  varchar(50)   필수입력
커피가격 : cprice   int          필수입력
+--------+-------------+------+-----+---------+----------------+
| Field  | Type        | Null | Key | Default | Extra          |
+--------+-------------+------+-----+---------+----------------+
| cno    | int(11)     | NO   | PRI | NULL    | auto_increment |    
| cname  | varchar(50) | NO   |     | NULL    |                |
| cprice | int(11)     | NO   |     | NULL    |                |
+--------+-------------+------+-----+---------+----------------+

create table coffee (
  cno int not null primary key auto_increment,
  cname varchar(50) not null,
  cprice int not null
);

[002] 다음과 같이 DB와 테이블을 만드시오           >> milk
우유번호 : mno      int           필수입력     primary key
우유이름 : mname    varchar(50)  필수입력
우유가격 : mprice   int          필수입력
우유갯수 : mnum     int         필수입력
우유총액 : mtotal   int         필수입력

+--------+-------------+------+-----+---------+----------------+
| Field  | Type        | Null | Key | Default | Extra          |
+--------+-------------+------+-----+---------+----------------+
| mno    | int(11)     | NO   | PRI | NULL    | auto_increment |
| mname  | varchar(50) | NO   |     | NULL    |                |
| mprice | int(11)     | NO   |     | NULL    |                |
| mnum   | int(11)     | NO   |     | NULL    |                |
| mtotal | int(11)     | NO   |     | NULL    |                |
+--------+-------------+------+-----+---------+----------------+

create table milk (
  mno int not null primary key auto_increment,
  mname varchar(50) not null,
  mprice int not null, 
  mnum int not null, 
  mtotal int not null
);

[003] 다음과 같이 DB와 테이블을 만드시오    >> score
+----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| sno      | int(11)     | NO   | PRI | NULL    | auto_increment |
| sname    | varchar(20) | NO   |     | NULL    |                |
| sjava    | int(11)     | NO   |     | NULL    |                |
| sjsp     | int(11)     | NO   |     | NULL    |                |
| sspring  | int(11)     | NO   |     | NULL    |                |
| sproject | int(11)     | NO   |     | NULL    |                |
| sstotal  | int(11)     | YES  |     | NULL    |                |
| ssavg    | int(11)     | YES  |     | NULL    |                |
| semail   | varchar(50) | YES   |     | NULL    |                |
+----------+-------------+------+-----+---------+----------------+

create table score (
  sno int not null primary key auto_increment,
  sname varchar(20) not null,
  sjava int not null,
  sspring int not null,
  sproject int not null,
  sstotal int,
  ssavg int,
  semail varchar(50)
);

[004]  다음과 같이 DB와 테이블을 만드시오      >> emp
mysql> desc emp;
+----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| empno    | int(11)     | NO   | PRI | NULL    | auto_increment |
| ename    | varchar(20) | YES  |     | NULL    |                |
| job      | varchar(20) | YES  |     | NULL    |                |
| mgr      | int(11)     | YES  |     | NULL    |                |
| hiredate | date        | YES  |     | NULL    |                |
| sal      | int(11)     | YES  |     | NULL    |                |
| comm     | int(11)     | YES  |     | NULL    |                |
| deptno   | int(11)     | YES  |     | NULL    |                |
+----------+-------------+------+-----+---------+----------------+
8 rows in set (0.01 sec)

mysql>
create table emp (
  empno int primary key auto_increment,
  ename varchar(20),
  job varchar(20),
  mgr int,
  hiredate date,
  sal int,
  comm int,
  deptno int
);





[005]  다음과 같이 DB와 테이블을 만드시오     >> dept
mysql> desc dept;
+--------+-------------+------+-----+---------+----------------+
| Field  | Type        | Null | Key | Default | Extra          |
+--------+-------------+------+-----+---------+----------------+
| deptno | int(11)     | NO   | PRI | NULL    | auto_increment |
| dname  | varchar(20) | NO   |     | NULL    |                |
| loc    | varchar(20) | NO   |     | NULL    |                |
+--------+-------------+------+-----+---------+----------------+
3 rows in set (0.00 sec)

create table dept (
  deptno int primary key auto_increment,
  dname varchar(20) not null,
  loc varchar(20) not null
);


[006]  다음과 같이 DB와 테이블을 만드시오    >> salagrade
mysql> desc salgrade;
+-------+---------+------+-----+---------+----------------+
| Field | Type    | Null | Key | Default | Extra          |
+-------+---------+------+-----+---------+----------------+
| grade | int(11) | NO   | PRI | NULL    | auto_increment |
| losal | int(11) | YES  |     | NULL    |                |
| hisal | int(11) | YES  |     | NULL    |                |
+-------+---------+------+-----+---------+----------------+
3 rows in set (0.02 sec)

mysql>
create table salgrade (
  grade int primary key auto_increment,
  losal int,
  hisal int
);

1. 데이터 베이스 언어
- DDL: CREATE, ALTER(#), DROP -> CAD



1) ALTER 문법
https://dev.mysql.com/doc/refman/8.0/en/table.html

help alter
help alter TABLE;

ALTER TABLE 테이블명
     ADD        추가필드명   자료형    옵션
     DROP      삭제필드명
     CHANGE  수정할필드명   새로넣을필드명   자료형  옵션
     MODIFY   수정할필드명   자료형  옵션
     RENAME  새로운테이블명

#1) 필드 추가
ALTER TABLE userinfo ADD uno int;
ALTER TABLE userinfo ADD uno2 int first; -- 맨앞에
ALTER TABLE userinfo ADD email varchar(100) after name; -- name 뒤에
 

#2) 필드 삭제
ALTER TABLE USERINFO DROP UNO;
ALTER TABLE USERINFO DROP UNO2; -- UNO2 삭제

#3) 필드 수정 (CHANGE) - 필드명, 자료형 옵션 수정
> ALTER TABLE USERINFO CHANGE OLDNAME NEWNAME 자료형 옵션
ALTER TABLE USERINFO CHANGE EMAIL EMAIL2 VARCHAR(50);
※ EMAIL2 -> EMAIL로 바꾸기

#4) 필드 수정 (MODIFY) - 자료형 옵션 수정 (ADD, DROP, CHANGE | MODIFY)
ALTER TABLE USERINFO MODIFIY EMAIL VARCHAR(20) NOT NULL;

ALTER TABLE USERINFO DROP EMAIL;

#5) 테이블명 수정 (ADD, DROP, CHANGE | MODIFY, RENAME)
ALTER TABLE USERINFO RENAME USERS;

---

ALTER TABLE tbl_name
    [alter_option [, alter_option] ...]
    [partition_options]

alter_option: {
    table_options
  | ADD [COLUMN] col_name column_definition
        [FIRST | AFTER col_name]
  | ADD [COLUMN] (col_name column_definition,...)
  | ADD {INDEX | KEY} [index_name]
        [index_type] (key_part,...) [index_option] ...
  | ADD {FULLTEXT | SPATIAL} [INDEX | KEY] [index_name]
        (key_part,...) [index_option] ...
  | ADD [CONSTRAINT [symbol]] PRIMARY KEY
        [index_type] (key_part,...)
        [index_option] ...
  | ADD [CONSTRAINT [symbol]] UNIQUE [INDEX | KEY]
        [index_name] [index_type] (key_part,...)
        [index_option] ...
  | ADD [CONSTRAINT [symbol]] FOREIGN KEY
        [index_name] (col_name,...)
        reference_definition
  | ADD [CONSTRAINT [symbol]] CHECK (expr) [[NOT] ENFORCED]
  | DROP {CHECK | CONSTRAINT} symbol
  | ALTER {CHECK | CONSTRAINT} symbol [NOT] ENFORCED
  | ALGORITHM [=] {DEFAULT | INSTANT | INPLACE | COPY}
  | ALTER [COLUMN] col_name {
        SET DEFAULT {literal | (expr)}
      | SET {VISIBLE | INVISIBLE}
      | DROP DEFAULT
    }
  | ALTER INDEX index_name {VISIBLE | INVISIBLE}
  | CHANGE [COLUMN] old_col_name new_col_name column_definition
        [FIRST | AFTER col_name]
  | [DEFAULT] CHARACTER SET [=] charset_name [COLLATE [=] collation_name]
  | CONVERT TO CHARACTER SET charset_name [COLLATE collation_name]
  | {DISABLE | ENABLE} KEYS
  | {DISCARD | IMPORT} TABLESPACE
  | DROP [COLUMN] col_name
  | DROP {INDEX | KEY} index_name
  | DROP PRIMARY KEY
  | DROP FOREIGN KEY fk_symbol
  | FORCE
  | LOCK [=] {DEFAULT | NONE | SHARED | EXCLUSIVE}
  | MODIFY [COLUMN] col_name column_definition
        [FIRST | AFTER col_name]
  | ORDER BY col_name [, col_name] ...
  | RENAME COLUMN old_col_name TO new_col_name
  | RENAME {INDEX | KEY} old_index_name TO new_index_name
  | RENAME [TO | AS] new_tbl_name
  | {WITHOUT | WITH} VALIDATION
}

partition_options:
    partition_option [partition_option] ...

partition_option: {
    ADD PARTITION (partition_definition)
  | DROP PARTITION partition_names
  | DISCARD PARTITION {partition_names | ALL} TABLESPACE
  | IMPORT PARTITION {partition_names | ALL} TABLESPACE
  | TRUNCATE PARTITION {partition_names | ALL}
  | COALESCE PARTITION number
  | REORGANIZE PARTITION partition_names INTO (partition_definitions)
  | EXCHANGE PARTITION partition_name WITH TABLE tbl_name [{WITH | WITHOUT} VALIDATION]
  | ANALYZE PARTITION {partition_names | ALL}
  | CHECK PARTITION {partition_names | ALL}
  | OPTIMIZE PARTITION {partition_names | ALL}
  | REBUILD PARTITION {partition_names | ALL}
  | REPAIR PARTITION {partition_names | ALL}
  | REMOVE PARTITIONING
}

key_part: {col_name [(length)] | (expr)} [ASC | DESC]

index_type:
    USING {BTREE | HASH}

index_option: {
    KEY_BLOCK_SIZE [=] value
  | index_type
  | WITH PARSER parser_name
  | COMMENT 'string'
  | {VISIBLE | INVISIBLE}
}

table_options:
    table_option [[,] table_option] ...

table_option: {
    AUTOEXTEND_SIZE [=] value
  | AUTO_INCREMENT [=] value
  | AVG_ROW_LENGTH [=] value
  | [DEFAULT] CHARACTER SET [=] charset_name
  | CHECKSUM [=] {0 | 1}
  | [DEFAULT] COLLATE [=] collation_name
  | COMMENT [=] 'string'
  | COMPRESSION [=] {'ZLIB' | 'LZ4' | 'NONE'}
  | CONNECTION [=] 'connect_string'
  | {DATA | INDEX} DIRECTORY [=] 'absolute path to directory'
  | DELAY_KEY_WRITE [=] {0 | 1}
  | ENCRYPTION [=] {'Y' | 'N'}
  | ENGINE [=] engine_name
  | ENGINE_ATTRIBUTE [=] 'string'
  | INSERT_METHOD [=] { NO | FIRST | LAST }
  | KEY_BLOCK_SIZE [=] value
  | MAX_ROWS [=] value
  | MIN_ROWS [=] value
  | PACK_KEYS [=] {0 | 1 | DEFAULT}
  | PASSWORD [=] 'string'
  | ROW_FORMAT [=] {DEFAULT | DYNAMIC | FIXED | COMPRESSED | REDUNDANT | COMPACT}
  | SECONDARY_ENGINE_ATTRIBUTE [=] 'string'
  | STATS_AUTO_RECALC [=] {DEFAULT | 0 | 1}
  | STATS_PERSISTENT [=] {DEFAULT | 0 | 1}
  | STATS_SAMPLE_PAGES [=] value
  | TABLESPACE tablespace_name [STORAGE {DISK | MEMORY}]
  | UNION [=] (tbl_name[,tbl_name]...)
}


-- 1. 테이블 준비
-- mysql> desc userinfo;
-- create table userinfo(
--     no        int                not null   primary  key  auto_increment,
--     name    varchar(100)   not null ,
--     age      int                not null
-- );

#### [연습문제]
>>>> 연습문제1)
[001]  다음과 같이 테이블을 준비하시오    >> alter_coffee
mysql> desc alter_coffee;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| cno    | int(11)     | YES  |     | NULL    |       |
| cname  | varchar(20) | YES  |     | NULL    |       |
| cprice | int(11)     | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)


[002] 다음과 같이 DB와 테이블을 수정하시오  [TABLE명 : alter_coffee] -  ALTER TABLE
연습문제1) cno, cname,cprice필드를 ( not null )으로 수정
ALTER TABLE ALTER_COFFEE MODIFY CNO INT NOT NULL;
ALTER TABLE ALTER_COFFEE MODIFY CNAME VARCHAR(20) NOT NULL;
ALTER TABLE ALTER_COFFEE MODIFY CPRICE INT NOT NULL;

연습문제2) 쿠폰필드  cgift    문자열고정(10)  미필수로 추가
ALTER TABLE ALTER_COFFEE ADD CGIFT CHAR(10);

연습문제3) 쿠폰필드  cgift를  ccoupon으로 바꾸기
ALTER TABLE ALTER_COFFEE CHANGE CGIFT CCOUPON CHAR(10);

연습문제4) 쿠폰필드 ccoupon삭제
ALTER TABLE ALTER_COFFEE DROP CCOUPON;

연습문제5) cno를 cprice뒤로이동
ALTER TABLE ALTER_COFFEE MODIFY CNO INT AFTER CPRICE;

연습문제6) cno를 맨위로
ALTER TABLE ALTER_COFFEE MODIFY CNO INT FIRST;

연습문제7) cno를 primary key 추가
ALTER TABLE ALTER_COFFEE MODIFY CNO INT PRIMARY KEY;

연습문제8) alter_coffee테이블의 이름을 alter_coffee2로 바꾸기
ALTER TABLE ALTER_COFFEE RENAME ALTER_COFFEE2;

연습문제9) 다음과 같이 최종본으로 테이블만들기
ALTER TABLE ALTER_COFFEE2 MODIFY CNO INT AUTO_INCREMENT;

mysql> desc alter_coffee2;
+--------+-------------+------+-----+---------+----------------+
| Field  | Type        | Null | Key | Default | Extra          |
+--------+-------------+------+-----+---------+----------------+
| cno    | int(11)     | NO   | PRI | NULL    | auto_increment |
| cname  | varchar(20) | NO   |     | NULL    |                |
| cprice | int(11)     | NO   |     | NULL    |                |
+--------+-------------+------+-----+---------+----------------+
3 rows in set (0.00 sec)

