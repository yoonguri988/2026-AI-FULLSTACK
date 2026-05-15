-- 1. CRUD (INSERT) 
-- 2. CRUD (INSERT 연습문제)
-- 3. CRUD (UPDATE)
-- 4. CRUD (UPDATE 연습문제)
-- 5. CRUD (DELETE)
-- 6. CRUD (DELETE 연습문제)
-- ________________________________________________________________
-- ________________________________________________________________

-- ctrl + 마우스 휠 (화면이 크고 작아짐)
-- ■ 진행   1. CRUD (INSERT)
-- DDL  : CREATE, ALTER, DROP
-- DML  : insert, select, update, delete
-- DCL  : grant, revoke

-- [준비] 다음과 같이 userinfo를  준비합니다.
-- mysql> -- userinfo 구조확인
-- mysql> desc userinfo;
-- +-------+--------------+------+-----+---------+----------------+
-- | Field | Type         | Null | Key | Default | Extra          |
-- +-------+--------------+------+-----+---------+----------------+
-- | no    | int          | NO   | PRI | NULL    | auto_increment |
-- | name  | varchar(100) | NO   |     | NULL    |                |
-- | age   | int          | NO   |     | NULL    |                |
-- +-------+--------------+------+-----+---------+----------------+
-- 3 rows in set (0.00 sec)

-- mysql>
use mbasic;
show tables;

-- users를 userinfo 이름 변경
alter table users rename userinfo;
desc userinfo; 

-- 1. 전체 데이터 입력 - 테이블 구조와 순서를 알고 있다면!
-- -----------------------------------------------
-- INSERT INTO table_name
-- VALUES (value1, value2, value3, ...);
-- -----------------------------------------------
insert into userinfo values (1,'aaa',11);
insert into userinfo values ('aaa',11,1);
-- 10:35:23	insert into userinfo values ('aaa',11,1)
-- Error Code: 1366. Incorrect integer value: 'aaa' for column 'no' at row 1	0.016 sec
insert into userinfo values (1,'bbb',22);
-- 10:37:18	insert into userinfo values (1,'bbb',22)
-- Error Code: 1062. Duplicate entry '1' for key 'userinfo.PRIMARY'	0.000 sec

select * from userinfo;
 
-- - 테이블구조를 만들때 no, name, age 순으로 만들었으므로
--   삽입시에도 그 순서 그대로 입력해야합니다.
--   순서가 바뀌면 오류가 납니다.
-- - 필드가 많아지면 데이터 삽입하기가 힘들어집니다.



-- 2. 원하는 데이터 입력
-- -----------------------------------------------
-- INSERT INTO table_name (column1, column2, column3, ...)
-- VALUES (value1, value2, value3, ...);
-- ----------------------------------------------- 

desc userinfo;

insert into userinfo (name, age) values ('bbb',22); -- O
insert into userinfo (name, age) values (22,'bbb'); -- X
-- 10:41:30	insert into userinfo (name, age) values (22,'bbb')	
-- Error Code: 1366. Incorrect integer value: 'bbb' for column 'age' at row 1	0.000 sec
insert into userinfo (age, name) values (33,'bbb');deptdepttestemptestemp

-- - 원하는 순서를 지정하여 데이터를 삽입하는 것도 가능합니다.
select * from userinfo;




-- ■ 진행   2. CRUD (INSERT 연습문제)
-- ------------------------------------------------------------
-- ------------------------------------------------------------ [연습문제]  STEP1  있다면 PASS
-- > 연습용 테이블의 구조를 복사하여 다음과 같이 준비하시오.
-- emp / dept / salgrade
-- create table userinfo  select * from db4.userinfo where 1=2;


-- [001]   desc emp;
-- +----------+-------------+------+-----+---------+----------------+
-- | Field    | Type        | Null | Key | Default | Extra          |
-- +----------+-------------+------+-----+---------+----------------+
-- | empno    | int(11)     | NO   | PRI | NULL    | auto_increment |
-- | ename    | varchar(20) | YES  |     | NULL    |                |
-- | job      | varchar(20) | YES  |     | NULL    |                |
-- | mgr      | int(11)     | YES  |     | NULL    |                |
-- | hiredate | date        | YES  |     | NULL    |                |
-- | sal      | int(11)     | YES  |     | NULL    |                |
-- | comm     | int(11)     | YES  |     | NULL    |                |
-- | dept     | int(11)     | YES  |     | NULL    |                |
-- +----------+-------------+------+-----+---------+----------------+
-- 8 rows in set (0.01 sec)

create table emp (
	empno int primary key auto_increment,
    ename varchar(20),
    job varchar(20),
    mgr int,
    hiredate date,
    sal int,
    comm int,
    dept int
);
alter table emp change deptno dept int;
desc emp;

-- [002]  desc dept;
-- +--------+-------------+------+-----+---------+----------------+
-- | Field  | Type        | Null | Key | Default | Extra          |
-- +--------+-------------+------+-----+---------+----------------+
-- | deptno | int(11)     | NO   | PRI | NULL    | auto_increment |
-- | dname  | varchar(20) | NO   |     | NULL    |                |
-- | loc    | varchar(20) | NO   |     | NULL    |                |
-- +--------+-------------+------+-----+---------+----------------+
-- 3 rows in set (0.00 sec)

desc  dept;


-- [003] 다음과 같이 데이터를 삽입하시오
-- mysql> desc salgrade;
-- +-------+---------+------+-----+---------+----------------+
-- | Field | Type    | Null | Key | Default | Extra          |
-- +-------+---------+------+-----+---------+----------------+
-- | grade | int(11) | NO   | PRI | NULL    | auto_increment |
-- | losal | int(11) | YES  |     | NULL    |                |
-- | hisal | int(11) | YES  |     | NULL    |                |
-- +-------+---------+------+-----+---------+----------------+
-- 3 rows in set (0.00 sec)
-- mysql>

desc salgrade;

create table salgrade (
  grade int primary key auto_increment,
  losal int,
  hisal int
);
-- ------------------------------------------------------------
-- ------------------------------------------------------------ [연습문제]    STEP2
-- > 다음과 같이 데이터를 삽입하시오
-- emp / dept / salgrade




-- [001] 다음과 같이 데이터를 삽입하시오
-- mysql> select * from emp;
-- +-------+--------+-----------+------+------------+------+------+------+
-- | empno | ename  | job       | mgr  | hiredate   | sal  | comm | dept |
-- +-------+--------+-----------+------+------------+------+------+------+
-- |  7369 | SMITH  | CLERK     | 7902 | 1980-12-17 |  800 | NULL |   20 |

-- |  7499 | ALLEN  | SALESMAN  | 7698 | 1981-02-20 | 1600 |  300 |   30 |
-- |  7521 | WARD   | SALESMAN  | 7698 | 1981-02-22 | 1250 |  500 |   30 |
-- |  7566 | JONES  | MANAGER   | 7839 | 1981-04-02 | 2975 | NULL |   20 |

-- |  7654 | MARTIN | SALESMAN  | 7698 | 1981-09-28 | 1250 | 1400 |   30 |
-- |  7698 | BLAKE  | MANAGER   | 7839 | 1981-05-01 | 2850 | NULL |   30 |
-- |  7782 | CLARK  | MANAGER   | 7839 | 1981-06-09 | 2450 | NULL |   10 |

-- |  7788 | SCOTT  | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |   20 |
-- |  7839 | KING   | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |   10 |
-- |  7844 | TURNER | SALESMAN  | 7698 | 1981-09-08 | 1500 |    0 |   30 |

-- |  7876 | ADAMS  | CLERK     | 7788 | 1987-05-23 | 1100 | NULL |   20 |
-- |  7900 | JAMES  | CLERK     | 7698 | 1981-12-03 |  950 | NULL |   30 |
-- |  7902 | FORD   | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |   20 |
-- |  7934 | MILLER | CLERK     | 7782 | 1982-01-23 | 1300 | NULL |   10 |
-- +-------+--------+-----------+------+------------+------+------+------+
-- 14 rows in set (0.00 sec)

-- mysql>
insert into emp values (7369,'SMITH','CLERK',7902,'1980-12-17',800,NULL,20);

insert into emp values (7499,'ALLEN','SALESMAN',7698,'1981-02-20',1600,300,30);
insert into emp values (7521,'WARD','SALESMAN',7698,'1981-02-22',1250,500,30);
insert into emp values (7566,'JONES','MANAGER',7839,'1981-04-02',2975,NULL,20);

insert into emp values (7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250,1400,30);
insert into emp values (7698,'BLAKE','MANAGER',7839,'1981-05-01',2850,NULL,30);
insert into emp values (7782,'CLARK','MANAGER',7839,'1981-06-09',2450,NULL,10);

insert into emp values (7788,'SCOTT','ANALYST',7566,'1987-04-19',3000,NULL,20);
insert into emp values (7839,'KING','PRESIDENT',NULL,'1981-11-17',5000,NULL,10);
insert into emp values (7844,'TURNER','SALESMAN',7698,'1981-09-08',1500,0,30);

insert into emp values (7876,'ADAMS','CLERK',7788,'1987-05-23',1100,NULL,20);
insert into emp values (7900,'JAMES','CLERK',7698,'1981-12-03',950,NULL,30);
insert into emp values (7902,'FORD','ANALYST',7566,'1981-12-03',3000,NULL,20);
insert into emp values (7934,'MILLER','CLERK',7782,'1982-01-23',1300,NULL,10);

select * from emp;
select count(*) from emp;

-- [002] 다음과 같이 데이터를 삽입하시오
-- mysql> select * from dept;
-- +--------+------------+----------+
-- | deptno | dname      | loc      |
-- +--------+------------+----------+
-- |     10 | ACCOUNTING | NEW YORK |
-- |     20 | RESEARCH   | DALLAS   |
-- |     30 | SALES      | CHICAGO  |
-- |     40 | OPERATIONS | BOSTON   |
-- +--------+------------+----------+
-- 4 rows in set (0.00 sec)

-- mysql>
insert into dept values (10,'ACCOUNTING','NEW YORK');
insert into dept values (20,'RESEARCH','DALLAS');
insert into dept values (30,'SALES','CHICAGO');
insert into dept values (40,'OPERATIONS','BOSTON');

select * from dept;

-- [003] 다음과 같이 데이터를 삽입하시오
-- mysql>
-- mysql> select * from salgrade;
-- +-------+-------+-------+
-- | grade | losal | hisal |
-- +-------+-------+-------+
-- |     1 |   700 |  1200 |
-- |     2 |  1201 |  1400 |
-- |     3 |  1401 |  2000 |
-- |     4 |  2001 |  3000 |
-- |     5 |  3001 |  9999 |
-- +-------+-------+-------+
-- 5 rows in set (0.00 sec)

insert into salgrade (losal, hisal) values (700, 1200);
insert into salgrade (losal, hisal) values (1201, 1400);
insert into salgrade (losal, hisal) values (1401, 2000);
insert into salgrade (losal, hisal) values (2001, 3000);
insert into salgrade (losal, hisal) values (3001, 9999);

select * from salgrade;


-- ■ 진행   3. CRUD (UPDATE)
-- DDL :  CREATE, ALTER, DROP
-- DML : insert, select, update, delete
-- DCL  : grant, revoke

-- >>  [진행]
-- DML : insert, select, update, delete
-- 데이터 조작어

-- STEP1) 준비
-- mysql> desc userinfo;
-- +-------+--------------+------+-----+---------+----------------+
-- | Field | Type         | Null | Key | Default | Extra          |
-- +-------+--------------+------+-----+---------+----------------+
-- | no    | int          | NO   | PRI | NULL    | auto_increment |
-- | name  | varchar(100) | NO   |     | NULL    |                |
-- | age   | int          | NO   |     | NULL    |                |
-- +-------+--------------+------+-----+---------+----------------+
-- 3 rows in set (0.00 sec)
-- mysql>
-- SET SQL_SAFE_UPDATES = 0; -- SAFE 안쓸래!
delete from userinfo;
-- 11:42:37	delete from userinfo	
-- Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column.  
--             To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.	0.000 sec
select * from userinfo;

-- mysql> select * from userinfo;
-- +----+--------+-----+
-- | no | name   | age |
-- +----+--------+-----+
-- |  1 | first  |  11 |
-- |  2 | second |  22 |
-- |  3 | third  |  33 |
-- |  4 | fourth |  44 |
-- +----+--------+-----+
-- 4 rows in set (0.00 sec)

-- >  1. 테이블복사해오기
 insert into userinfo (no, name, age) values (1,'first', 11);
 insert into userinfo (no, name, age) values (2,'second', 22);
 insert into userinfo (no, name, age) values (3,'third', 33);
 insert into userinfo (no, name, age) values (4,'fourth', 44);

select * from userinfo;
-- #Q  userinfo  구조+데이터 복사해서
-- >  2. 업데이트 (update) 
-- ----------------------------------
-- UPDATE 테이블명
-- SET 컬럼1 = VALUE1, 컬럼2 = VALUE2,,,
-- WHERE 조건절
-- -----------------------------------

update userinfo set age = 0;
update userinfo set age = 10, name = 'fff' where no=1;
update userinfo set age = 22  where no=2 and name='second';

update userinfo set age =11, name='first' where no =1;
update userinfo set age =10, name='three' where no = 3;

-- - no가 3이고 age가 10인  유저의 이름을 'third' , 나이를 33살로 수정합니다.
update userinfo 
SET name='third', age=33 
where no=3 and age=10;

-- - no가 4이고 name 'first'인  유저의 이름을 'fourth' , 나이를 44살로 수정합니다.
update userinfo 
SET name='fourth', age=44 
where no=4 and name='first';



-- ■ 진행   4. CRUD (UPDATE 연습문제)
-- ------------------------------------------------------------
-- ------------------------------------------------------------ [연습문제]

-- * update_score
-- >> 000 테이블준비
--   create table update_score  select * from score;
--   alter table update_score modify  sno int not null  auto_increment primary key;
desc update_score;

create table update_score  select * from score;
alter table update_score modify  sno int not null  auto_increment primary key;

-- [000-1] 다음과 같이 테이블을 만드시오    >> score
-- +----------+-------------+------+-----+---------+----------------+
-- | Field    | Type        | Null | Key | Default | Extra          |
-- +----------+-------------+------+-----+---------+----------------+
-- | sno      | int(11)     | NO   | PRI | NULL    | auto_increment |
-- | sname    | varchar(20) | NO   |     | NULL    |                |
-- | sjava    | int(11)     | NO   |     | NULL    |                |
-- | sjsp     | int(11)     | NO   |     | NULL    |                |
-- | sspring  | int(11)     | NO   |     | NULL    |                |
-- | sproject | int(11)     | NO   |     | NULL    |                |
-- | sstotal  | int(11)     | YES  |     | NULL    |                |
-- | ssavg    | int(11)     | YES  |     | NULL    |                |
-- | semail   | varchar(50) | YES   |     | NULL    |                |
-- +----------+-------------+------+-----+---------+----------------+

-- [000-2] 다음과 같이 데이터를 삽입하시오
-- mysql> select * from score;
-- +-----+-------+-------+------+---------+----------+---------+-------+--------+
-- | sno | sname | sjava | sjsp | sspring | sproject | sstotal | ssavg | semail |
-- +-----+-------+-------+------+---------+----------+---------+-------+--------+
-- |   1 | aaa   |   100 |  100 |     100 |      100 |    NULL |  NULL | NULL |
-- |   2 | bbb   |    90 |   98 |      88 |      100 |    NULL |  NULL | NULL |
-- |   3 | ccc   |    70 |   20 |      78 |       80 |    NULL |  NULL | NULL |
-- |   4 | ddd   |    78 |   89 |      68 |       98 |    NULL |  NULL | NULL |
-- |   5 | abc   |    89 |   98 |      69 |       77 |    NULL |  NULL |NULL |
-- +-----+-------+-------+------+---------+----------+---------+-------+--------+
desc score;

alter table score add sjsp int not null;
alter table score modify sjsp int not null after sjava;

insert into score (sno, sname, sjava, sjsp, sspring, sproject) values(1, 'aaa', 100,100,100,100);
insert into score (sno, sname, sjava, sjsp, sspring, sproject) values(2, 'bbb', 90,98,88,100);
insert into score (sno, sname, sjava, sjsp, sspring, sproject) values(3, 'ccc', 70,20,78,80);
insert into score (sno, sname, sjava, sjsp, sspring, sproject) values(4, '78', 100,89,68,98);
insert into score (sno, sname, sjava, sjsp, sspring, sproject) values(5, '89', 100,98,69,77);

select * from update_score;
-- [001]   이름이 ccc인 사람의 sjava=90 , sjsp=90, sspring=90으로 수정하시오
update update_score
set sjava=90 , sjsp=90, sspring=90
where sname='ccc';

-- [002]  모든학생의 semail의 값을 admin@gamil.com으로 수정하시오
update update_score
set semail = 'admin@gamil.com';


-- [003]  성적의 총합(sstotal)을 계산해서 넣기   sjava+sjsp+sspring+sproject
 update update_score
 set sstotal = sjava+sjsp+sspring+sproject;
 

-- [004]  성적의 평균을 계산해서 넣기 , ssavg = sstotal/4
  update update_score
 set ssavg = sstotal/4;

-- [005]  성적의 평균이 ssavg 100점인 학생의 email을 first@gmail.com으로 수정하시오
update update_score
set semail = 'first@gmail.com'
where ssavg = 100;
 
-- [006]  이름이 bbb인 학생의 sjava 점수를 92 , sjsp점수를 78로 , 총점, 평균을  수정하시오
update update_score
set sjava = 92, sjsp = 78, sstotal = sjava+sjsp+sspring+sproject, ssavg = sstotal/4
where sname = 'bbb';
-- [007]  성적의 평균이 ssavg 89.5점인 학생의 semail을 second@gmail.com로 , sname을 second로 수정하시오
update update_score
set semail = 'second@gmail.com', sname='second'
where ssavg = 89.5;

-- [008]   sname이 ccc인 학생의 semail을  ccc@gmail.com으로 수정하시오
update update_score
set semail = 'ccc@gmail.com'
where sname = 'ccc';
 
-- [009]   sproject점수가 80점미만인 학생의 semail을 blackstdudent@gmail.com으로 수정하시오
update update_score
set semail =  'blackstdudent@gmail.com'
where sproject < 80;
 
-- [010]   ssavg이89.5이고 sproject가 98인 학생의 semail을  hello@gmail.com으로 수정하시오
update update_score
set semail =  'hello@gmail.com'
where ssavg = 89.5
and sproject = 98;
 
alter table update_score modify ssavg double;
desc update_score;
select * from update_score;


-- ■ 진행   5. CRUD (DELETE)
-- >  3. 삭제 (delete)
-- -------------------------------------------
-- = DELETE  FROM  tbl_name  WHERE condition;
-- -------------------------------------------
-- >  #  userinfo 테이블복사해서
--         delete_userinfo 테이블 만들기  ( 구조 + 데이터 )
create table userinfo2 select * from userinfo; -- 구조 + 데이터

desc userinfo2; -- PK, auto_increment
alter table userinfo2 modify no int auto_increment primary key;

select * from userinfo2;

delete from userinfo2 where no=4 and age=44 and name='fourth';

create table delete_userinfo select * from userinfo;

select * from delete_userinfo;

--     #1.  나이가 11살인 유저 데이터 삭제 
delete from delete_userinfo where age = 11;
--     #2. 이름이 second이고 나이가 22살인 유저의 데이터 삭제 
delete from delete_userinfo where name = 'second' and age = 22;
--     #3. 전체데이터 삭제 
delete from delete_userinfo;




-- ■ 진행   6. CRUD (DELETE 연습문제)
--    * delete_emp
-- >> 000 테이블준비
--    create table delete_emp  select * from emp;
--    alter table delete_emp modify empno int not null  auto_increment primary key;
create table delete_emp  select * from emp;
alter table delete_emp modify empno int not null  auto_increment primary key;

-- [000-1]  다음과 같이 DB와 테이블을 만드시오      >> emp
-- mysql> desc emp;
-- +----------+-------------+------+-----+---------+----------------+
-- | Field    | Type        | Null | Key | Default | Extra          |
-- +----------+-------------+------+-----+---------+----------------+
-- | empno    | int(11)     | NO   | PRI | NULL    | auto_increment |
-- | ename    | varchar(20) | YES  |     | NULL    |                |
-- | job      | varchar(20) | YES  |     | NULL    |                |
-- | mgr      | int(11)     | YES  |     | NULL    |                |
-- | hiredate | date        | YES  |     | NULL    |                |
-- | sal      | int(11)     | YES  |     | NULL    |                |
-- | comm     | int(11)     | YES  |     | NULL    |                |
-- | deptno   | int(11)     | YES  |     | NULL    |                |
-- +----------+-------------+------+-----+---------+----------------+
-- 8 rows in set (0.01 sec)
-- mysql>


-- [000-2] 다음과 같이 데이터를 삽입하시오
-- mysql> select * from emp;
-- +-------+--------+-----------+------+------------+------+------+------+
-- | empno | ename  | job       | mgr  | hiredate   | sal  | comm | dept |
-- +-------+--------+-----------+------+------------+------+------+------+
-- |  7369 | SMITH  | CLERK     | 7902 | 1980-12-17 |  800 | NULL |   20 |
-- |  7499 | ALLEN  | SALESMAN  | 7698 | 1981-02-20 | 1600 |  300 |   30 |
-- |  7521 | WARD   | SALESMAN  | 7698 | 1981-02-22 | 1250 |  500 |   30 |
-- |  7566 | JONES  | MANAGER   | 7839 | 1981-04-02 | 2975 | NULL |   20 |
-- |  7654 | MARTIN | SALESMAN  | 7698 | 1981-09-28 | 1250 | 1400 |   30 |
-- |  7698 | BLAKE  | MANAGER   | 7839 | 1981-05-01 | 2850 | NULL |   30 |
-- |  7782 | CLARK  | MANAGER   | 7839 | 1981-06-09 | 2450 | NULL |   10 |
-- |  7788 | SCOTT  | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |   20 |
-- |  7839 | KING   | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |   10 |
-- |  7844 | TURNER | SALESMAN  | 7698 | 1981-09-08 | 1500 |    0 |   30 |
-- |  7876 | ADAMS  | CLERK     | 7788 | 1987-05-23 | 1100 | NULL |   20 |
-- |  7900 | JAMES  | CLERK     | 7698 | 1981-12-03 |  950 | NULL |   30 |
-- |  7902 | FORD   | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |   20 |
-- |  7934 | MILLER | CLERK     | 7782 | 1982-01-23 | 1300 | NULL |   10 |
-- +-------+--------+-----------+------+------------+------+------+------+
-- 14 rows in set (0.00 sec)

-- mysql>


select * from delete_emp;
-- [001] [TABLE : delete_emp] (직책(JOB)이  'SALESMAN'인 데이터를 삭제하시오.  ) 
delete from delete_emp where job = 'SALESMAN';
-- [002] [TABLE : delete_emp] (직책(JOB)이  'MANAGER'이고  이름(ENAME)이 'JONES'인 데이터를 삭제하시오.  ) 
delete from delete_emp where job = 'MANAGER' and ename = 'JONES';
-- [003] [TABLE : delete_emp] (delete_emp 테이블의 모든 데이터를 삭제하시오. )
delete from delete_emp;