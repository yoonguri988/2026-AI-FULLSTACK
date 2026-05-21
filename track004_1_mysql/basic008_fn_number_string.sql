-- 1. CRUD (SELECT 복습문제)
-- 2. CRUD (SELECT Function-Number)
-- 3. CRUD (SELECT Function-Number 연습문제)
-- 4. CRUD (SELECT Function-String)
-- 5. CRUD (SELECT Function-String 연습문제)
-- ________________________________________________________________
-- ________________________________________________________________


-- ■ 진행1. CRUD (SELECT 복습문제)

-- Q1. 다음과 같이 테이블을 작성하시오  ( 있다면 pass)
-- mysql> desc userinfo_select;
-- +-------+--------------+------+-----+---------+----------------+
-- | Field | Type         | Null | Key | Default | Extra          |
-- +-------+--------------+------+-----+---------+----------------+
-- | no    | int          | NO   | PRI | NULL    | auto_increment |
-- | name  | varchar(100) | NO   |     | NULL    |                |
-- | age   | int          | NO   |     | NULL    |                |
-- +-------+--------------+------+-----+---------+----------------+
-- 3 rows in set (0.01 sec)
desc userinfo_select;
create table userinfo_select select * from userinfo;
-- create table userinfo_select select * from userinfo where 1=2; -- 해당하는 값이 없어서 구조만 복사

-- Q2. userinfo 테이블을 다음과 같이 수정하시오.
-- mysql> desc userinfo_select;
-- +-------+--------------+------+-----+---------+----------------+
-- | Field | Type         | Null | Key | Default | Extra          |
-- +-------+--------------+------+-----+---------+----------------+
-- | no    | int          | NO   | PRI | NULL    | auto_increment |
-- | name  | varchar(100) | NO   |     | NULL    |                |
-- | age   | int          | YES  |     | NULL    |                |
-- +-------+--------------+------+-----+---------+----------------+
-- 3 rows in set (0.01 sec)
desc userinfo_select;
alter table userinfo_select modify no int not null auto_increment primary key;

-- Q3. 다음과 같이 데이터가 있다
--  AFTER와 같이 데이터를 추가하시오.
-- >> BEFORE)
-- mysql> select * from userinfo_select;
-- +----+--------+-----+
-- | no | name   | age |
-- +----+--------+-----+
-- |  1 | first  |  NULL|
-- |  2 | second |  22 |
-- |  3 | third  |  33 |
-- +----+--------+-----+
-- 6 rows in set (0.01 sec)

-- >> AFTER)
-- mysql> select * from userinfo_select;
-- +----+--------+-----+
-- | no | name   | age |
-- +----+--------+-----+
-- |  1 | first  |  NULL |
-- |  2 | second |  22 |
-- |  3 | third  |  33 |
-- |  4 | fourth |  44 |
-- +----+--------+-----+
-- 6 rows in set (0.01 sec)
-- SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;
delete from userinfo_select where no > 3;

select * from userinfo_select;

update userinfo_select set age = null where no = 1;
insert into userinfo_select values (4, 'fourth', 44);

-- Q4. 이름이 fourth 이고 age가 44인데이터의 no를 10으로 수정하시오.
update userinfo_select
set no = 10
where name = 'fourth'
and age = 44;
-- Q5. no가 10인데이터를 삭제하시오.
delete from userinfo_select
where no = 10;
-- Q6. 나이가 많은순으로 2명을 검색하시오.
select * 
from userinfo_select
order by age desc
limit 2;
-- Q7. 나이가 NULL이 아니고
--     no가 2또는 3중에서
--     이름의 두번째 글자가 e가아닌 데이터를 조회하시오
select *
from userinfo_select
where age is not null
and no in (2,3)
and name not like '_e%';

-- Q8.  emp 테이블에서 GROUP BY절만  사용하여
-- -- 각부서의 직책별 사원수, 가장높은 급여, 급여합, 평균급여를  다음과 같이  사원데이터를  조회하시오.

-- >> 결과
-- +--------+-----------+--------+----------+--------+-----------+
-- | deptno | job       | 사원수   | 최고급여   | 급여합   | 평균급여   |
-- +--------+-----------+--------+----------+--------+-----------+
-- |     10 | CLERK     |      1 |     1300 |   1300 | 1300.0000 |
-- |     10 | MANAGER   |      1 |     2450 |   2450 | 2450.0000 |
-- |     10 | PRESIDENT |      1 |     5000 |   5000 | 5000.0000 |
-- |     20 | ANALYST   |      2 |     3000 |   6000 | 3000.0000 |
-- |     20 | CLERK     |      2 |     1100 |   1900 |  950.0000 |
-- |     20 | MANAGER   |      1 |     2975 |   2975 | 2975.0000 |
-- |     30 | CLERK     |      1 |      950 |    950 |  950.0000 |
-- |     30 | MANAGER   |      1 |     2850 |   2850 | 2850.0000 |
-- |     30 | SALESMAN  |      4 |     1600 |   5600 | 1400.0000 |
-- +--------+-----------+--------+----------+--------+-----------+
-- 9 rows in set (0.00 sec)
select deptno, job, count(*) `사원수`, max(sal) `최고급여`, sum(sal) `급여합`, avg(sal) `평균급여`
from emp
group by deptno, job
order by deptno;

-- >> 주어진조건
-- mysql> desc emp;
-- +----------+-------------+------+-----+---------+----------------+
-- | Field    | Type        | Null | Key | Default | Extra          |
-- +----------+-------------+------+-----+---------+----------------+
-- | empno    | int         | NO   | PRI | NULL    | auto_increment |
-- | ename    | varchar(20) | YES  |     | NULL    |                |
-- | job      | varchar(20) | YES  |     | NULL    |                |
-- | mgr      | int         | YES  |     | NULL    |                |
-- | hiredate | date        | YES  |     | NULL    |                |
-- | sal      | int         | YES  |     | NULL    |                |
-- | comm     | int         | YES  |     | NULL    |                |
-- | deptno   | int         | NO   |     | NULL    |                |
-- +----------+-------------+------+-----+---------+----------------+
-- 8 rows in set (0.00 sec)



-- ■ 진행2. CRUD (SELECT Function-Number)
-- >> 데이터베이스언어
-- DDL  : create, alter , drop
-- DML : insert, select # , update, delete
-- DCL  : grant, revoke


-- ==========================
-- #1. Number
-- 1.   ceil     올림
-- 2.   floor   내림
-- 3.   round 반올림
-- 4.   mod(숫자 , 나눌 수)   나머지 연산자

-- = BASIC 
select ceil(1.1) `올림 2`, floor(1.9) `내림 1` , round(1.5) `반올림 2`, mod(10,3) `나머지 1` from dual;

select 
1.2345
, round(12.2545, 1)  -- 소수점 1번째 자리에서 반올림 12.3
, round(15.2345, -1) -- 정수  1번째 자리에서 반올림 20
from dual;
-- +-----------+------------+------------+-----------+
-- | ceil(1.1) | floor(1.9) | round(1.5) | mod(10,3) |
-- +-----------+------------+------------+-----------+
-- |         2 |          1 |          2 |         1 |
-- +-----------+------------+------------+-----------+
-- 1 row in set (0.01 sec)

-- ■ 진행3. CRUD (SELECT Function-Number 연습문제)

-- EX1    123.4578을 ROUND를 이용하여 다음과 같이 출력하시오.
-- +----------+--------+--------+--------+--------+
-- | 123.4578 | ROUND1 | ROUND2 | ROUND3 | ROUND4 |
-- +----------+--------+--------+--------+--------+
-- | 123.4578 |    123 |    120 |  123.5 | 123.46 |
-- +----------+--------+--------+--------+--------+
-- 1 row in set (0.00 sec)

select
123.4578
, round(123.4578,0) `ROUND1`
, round(123.4578,-1) `ROUND2`
, round(123.4578,1) `ROUND3`
, round(123.4578,2) `ROUND4`
from dual;


-- EX2  다음과 같이 출력하시오.
-- +----------+------+-------+
-- | 123.4578 | CEIL | FLOOR |
-- +----------+------+-------+
-- | 123.4578 |  124 |   123 |
-- +----------+------+-------+
-- 1 row in set (0.00 sec)
select
123.4578
, ceil(123.4578) `CEIL`
, floor(123.4578) `FLOOR`
from dual;


-- ■ 진행4. CRUD (SELECT Function-String)
-- #2. String
-- length 개수
-- upper 대문자 / lower 소문자

-- instr( 문자열, 찾을 문자열 ) - 위치
-- substr( 문자열, 시작, 갯수 )  - 문자열일부분 추출
-- left( 문자열, 갯수)
-- right( 문자열, 갯수)

-- concat ( 문자열, 문자열 ) - 문자열연결

-- trim()  - ltrim, rtrim  공백제거
-- replace (문자열, 찾아서, 바꾸기)
-- repeat (문자열, 몇번)

-- lpad(문자열, 전체자리수, 특정문자)
-- rpad(문자열, 전체자리수, 특정문자)


-- = BASIC
-- mysql> -- 1. length(문자열) 
select length('abc') from dual;
-- +---------------+
-- | length('abc') |
-- +---------------+
-- |             3 |
-- +---------------+
-- 1 row in set (0.00 sec)

-- mysql>
-- mysql> -- 2. upper/lower 
select 'ABC' `DEFAULT`, upper('abc') `s1`, lower('ABC') `s2` from dual;
-- +---------+------+------+
-- | DEFAULT | s1   | s2   |
-- +---------+------+------+
-- | ABC     | ABC  | abc  |
-- +---------+------+------+
-- 1 row in set (0.01 sec)

-- mysql>
-- mysql>
-- mysql> -- 3. 찾기1 - 위치  instr  (문자열, 찾을문자열)
select 
'abc' `DEFAULT`
, instr('abc','b') `b의 위치 - a(1)b(2)` 
, instr('abc','ab') `ab의 위치 - ab` 
, instr('abc','ac') `ac의 위치 - 없으면 0` 
from dual;
-- +----------+-----------+-----------+
-- | b의 위치 | ab의 위치 | ac의 위치 |
-- +----------+-----------+-----------+
-- |        2 |         1 |         0 |
-- +----------+-----------+-----------+
-- 1 row in set (0.00 sec)


-- mysql> -- 4.   찾기 2 - 문자열 left, right, substr 
select
'abc' `DEFAULT`
, left('abc', 1) `l1`
, left('abc', 2) `l2`
, right('abc', 1) `r1`
, right('abc', 2) `r2`
from dual;
-- +------+------+------+------+
-- | l1   | l2   | r1   | r2   |
-- +------+------+------+------+
-- | a    | ab   | c    | bc   |
-- +------+------+------+------+
-- 1 row in set (0.00 sec)

-- mysql>
-- mysql> -- 5. 찾기 3  - substr (문자열, 시작위치, 갯수)
select 
'abcde' `DEFAULT`
,substr('abcde',2,2) `s1` 
,substr('abcde',2,3) `s2` 
,substr('abcde',1,3) `s3` 
from dual;
-- +------+------+------+
-- | s1   | s2   | s3   |
-- +------+------+------+
-- | bc   | bcd  | abc  |
-- +------+------+------+
-- 1 row in set (0.00 sec)

-- mysql>
-- mysql>
-- mysql> -- 6. 문자열 연결   - concat 
select
concat('choco','milk')
from dual;
-- +--------------------------+
-- | concat('choco' , 'milk') |
-- +--------------------------+
-- | chocomilk                |
-- +--------------------------+
-- 1 row in set (0.00 sec)

-- mysql>
-- mysql> -- 7. 공백빼기 - trim 
select
trim(' a b c ')
from dual;
-- +-----------------+
-- | trim(' a b c ') |
-- +-----------------+
-- | a b c           |
-- +-----------------+
-- 1 row in set (0.00 sec)

-- mysql> 
select
concat('#',trim(' a b c '),'#') -- trim 양쪽 공백
,concat('#',ltrim(' a b c '),'#') -- trim 왼쪽 공백
,concat('#',rtrim(' a b c '),'#') -- trim 오른쪽 공백
from dual;
-- +---------+----------+----------+
-- | t1      | t2       | t3       |
-- +---------+----------+----------+
-- | #a b c# | #a b c # | # a b c# |
-- +---------+----------+----------+
-- 1 row in set (0.00 sec)

-- mysql>
-- mysql> -- 8. 찾아서 바꾸기 - replace (문자열, 찾아서, 바꾸기)
select 
replace('hello sally' , 'sally','a')
from dual;
-- +--------------------------------------+
-- | replace('hello sally' , 'sally','a') |
-- +--------------------------------------+
-- | hello a                              |
-- +--------------------------------------+
-- 1 row in set (0.00 sec)

-- mysql>
-- mysql> -- 9. 반복 repeat (문자열, 숫자만큼)
select 
repeat('*',5)
from dual;
-- +---------------+
-- | repeat('*',5) |
-- +---------------+
-- | *****         |
-- +---------------+
-- 1 row in set (0.00 sec)

-- mysql> -- 10. 빈칸채우기 
-- lpad(문자열, 숫자길이, 채울 문자열)
-- rpad(문자열, 숫자길이, 채울 문자열)
select
lpad('ABC' , 5, '#')
, rpad('ABC' , 5, '#')
from dual;
-- +----------------------+----------------------+
-- | lpad('ABC' , 5, '#') | rpad('ABC' , 5, '#') |
-- +----------------------+----------------------+
-- | ##ABC                | ABC##                |
-- +----------------------+----------------------+
-- 1 row in set (0.00 sec)

-- mysql>





-- ■ 진행5. CRUD (SELECT Function-Number 연습문제)

-- mysql> desc fn_select_userinfo2;
-- +-------+-------------+------+-----+---------+-------+
-- | Field | Type        | Null | Key | Default | Extra |
-- +-------+-------------+------+-----+---------+-------+
-- | no    | int         | NO   |     | 0       |       |
-- | name  | varchar(20) | NO   |     | NULL    |       |
-- | age   | int         | NO   |     | NULL    |       |
-- | email | varchar(20) | NO   |     | NULL    |       |
-- +-------+-------------+------+-----+---------+-------+
-- 4 rows in set (0.01 sec)

create table fn_select_userinfo2 select * from userinfo_select where 1=2;
desc fn_select_userinfo2;

alter table fn_select_userinfo2 modify name varchar(20) not null;
alter table fn_select_userinfo2 modify age int not null;
alter table fn_select_userinfo2 add email varchar(20) not null;

-- mysql> select * from fn_select_userinfo2;
-- +----+------+-----+---------------+
-- | no | name | age | email         |
-- +----+------+-----+---------------+
-- |  1 | aaa  |  11 | aaa@gmail.com |
-- |  2 | bbb  |  22 | bbb@gmail.com |
-- |  3 | ccc  |  33 | ccc@gmail.com |
-- |  4 | ddd  |  44 | ddd@gmail.com |
-- |  5 | abc  |  55 | abc@gmail.com |
-- |  6 | bca  |  66 | bca@gmail.com |
-- +----+------+-----+---------------+
-- 6 rows in set (0.00 sec)

insert into fn_select_userinfo2 values (1, 'aaa', 11, 'aaa@gmail.com');
insert into fn_select_userinfo2 values (2, 'bbb', 22, 'bbb@gmail.com');
insert into fn_select_userinfo2 values (3, 'ccc', 33, 'ccc@gmail.com');
insert into fn_select_userinfo2 values (4, 'ddd', 44, 'ddd@gmail.com');
insert into fn_select_userinfo2 values (5, 'abc', 55, 'abc@gmail.com');
insert into fn_select_userinfo2 values (6, 'bca', 66, 'bca@gmail.com');

select * from fn_select_userinfo2;

-- 1. 유저의 이름의 글자수를 조회하시오.
-- +------+------+
-- | 이름 | 갯수 |
-- +------+------+
-- | aaa  |    3 |
-- | bbb  |    3 |
-- | ccc  |    3 |
-- | ddd  |    3 |
-- | abc  |    3 |
-- | bca  |    3 |
-- +------+------+
-- 6 rows in set (0.00 sec)

select name `이름`, length(name) `갯수`
from fn_select_userinfo2;

-- 2. 유저의 이름과 이름의  첫번째 글자 , 마지막글자 를 조회하시오. (left, right)
-- +------+-------------+------------+
-- | name | 첫번째 글자 | 마지막글자 |
-- +------+-------------+------------+
-- | aaa  | a           | a          |
-- | bbb  | b           | b          |
-- | ccc  | c           | c          |
-- | ddd  | d           | d          |
-- | abc  | a           | c          |
-- | bca  | b           | a          |
-- +------+-------------+------------+
-- 6 rows in set (0.00 sec)
select name, left(name,1) `첫번째 글자`, right(name,1) `마지막글자`
from fn_select_userinfo2;

-- 3. 유저이름의 aaa인 유저를 찾아서 'aaa 1등'으로 변경하시오.
-- +------+-----------------------------------+
-- | name | replace( name, 'aaa' , 'aaa 1등') |
-- +------+-----------------------------------+
-- | aaa  | aaa 1등                           |
-- | bbb  | bbb                               |
-- | ccc  | ccc                               |
-- | ddd  | ddd                               |
-- | abc  | abc                               |
-- | bca  | bca                               |
-- +------+-----------------------------------+
-- 6 rows in set, 1 warning (0.00 sec)
select name, replace(name, 'aaa', 'aaa 1등')
from fn_select_userinfo2;

-- 4. 다음과 같이 직업을 출력하시오.   (concat)
-- +---------------------+
-- | 직업                |
-- +---------------------+
-- | aaa는 개발자입니다. |
-- | bbb는 개발자입니다. |
-- | ccc는 개발자입니다. |
-- | ddd는 개발자입니다. |
-- | abc는 개발자입니다. |
-- | bca는 개발자입니다. |
-- +---------------------+
-- 6 rows in set, 1 warning (0.00 sec)
select concat(name, '는 개발자입니다.') `직업`
from fn_select_userinfo2;

-- 5. 이름을 대문자로 조회하시오.
-- +-------------+
-- | upper(name) |
-- +-------------+
-- | AAA         |
-- | BBB         |
-- | CCC         |
-- | DDD         |
-- | ABC         |
-- | BCA         |
-- +-------------+
-- 6 rows in set (0.00 sec)
select upper(name)
from fn_select_userinfo2;

--  6. 이름을 소문자로 조회하시오.
-- +-------------+
-- | lower(name) |
-- +-------------+
-- | aaa         |
-- | bbb         |
-- | ccc         |
-- | ddd         |
-- | abc         |
-- | bca         |
-- +-------------+
-- 6 rows in set (0.00 sec)
select lower(name)
from fn_select_userinfo2;

-- 7. 나이가 40대이상의 유저의 이름과 b의 위치를  조회하시오. (instr)
-- +------+-----------------+
-- | name | instr(name,'b') |
-- +------+-----------------+
-- | ddd  |               0 |
-- | abc  |               2 |
-- | bca  |               1 |
-- +------+-----------------+
-- 3 rows in set (0.03 sec)
select name, instr(name, 'b')
from fn_select_userinfo2
where age >= 40;

-- 8. [upgrade] 나이가 40대이상의 유저의 이름과 b의 위치를  조회시 나이 많은 순으로 2분을 조회하시오(instr)
-- +------+-----------------+
-- | name | instr(name,'b') |
-- +------+-----------------+
-- | bca  |               1 |
-- | abc  |               2 |
-- +------+-----------------+
-- 2 rows in set (0.02 sec)

select name, instr(name, 'b')
from fn_select_userinfo2
where age >= 40
order by age desc
limit 2;

-- 9. [upgrade] 유저의 이름과 이름의  첫번째 글자 , 마지막글자 를 조회하시오.
-- +------+------+
-- | name | test |
-- +------+------+
-- | aaa  | a*a  |
-- | bbb  | b*b  |
-- | ccc  | c*c  |
-- | ddd  | d*d  |
-- | abc  | a*c  |
-- | bca  | b*a  |
-- +------+------+
-- 6 rows in set (0.00 sec)
select name, concat(left(name, 1),'*',right(name, 1)) as test
from fn_select_userinfo2;

-- 10. 테이블 select_userinfo복사해서 한개더 만들기
-- mysql> select * from select_userinfo2;
-- +----+-------+-----+---------------+
-- | no | name  | age | email         |
-- +----+-------+-----+---------------+
-- |  1 | aaaa  |  11 | aaa@gmail.com |
-- |  2 | bbaab |  22 | bbb@gmail.com |
-- |  3 | ccaac |  33 | ccc@gmail.com |
-- |  4 | daadd |  44 | abc@gmail.com |
-- |  5 | abc   |  55 | abc@gmail.com |
-- |  6 | baaca |  66 | bca@gmail.com |
-- +----+-------+-----+---------------+
-- 6 rows in set (0.00 sec)
create table select_userinfo2 select * from fn_select_userinfo2;
desc select_userinfo2;

select * from select_userinfo2;
update select_userinfo2 set name='aaaa' where no = 1;
update select_userinfo2 set name='bbaab' where no = 2;
update select_userinfo2 set name='ccaac' where no = 3;
update select_userinfo2 set name='daadd' where no = 4;
update select_userinfo2 set name='abc' where no = 5;
update select_userinfo2 set name='baaca' where no = 6;

-- +-------+-------+
-- | name  | test  |
-- +-------+-------+
-- | aaaa  | a**a  |
-- | bbaab | b***b |
-- | ccaac | c***c |
-- | daadd | d***d |
-- | abc   | a*c   |
-- | baaca | b***a |
-- +-------+-------+
select name, concat(left(name, 1),repeat('*',length(name)-2),right(name, 1)) as test
from select_userinfo2;

-- 11. 모든  유저의 평균 나이를 구하시오.   
-- +-----------+
-- | 평균나이  |
-- +-----------+
-- |   38.5    |
-- +-----------+
select round(avg(age),1) `평균나이`
from fn_select_userinfo2;

-- 12. 나이가 40대 이상인 유저의 수를 구하시오.   
-- +--------+
-- | 인원수 |
-- +--------+
-- |   3    |
-- +--------+
select count(*)`인원수`
from fn_select_userinfo2
where age >= 40;


-- 13. 이메일이 `gmail.com`인 유저의 최대 나이를 구하시오.   
-- +-----------+
-- | 최대나이  |
-- +-----------+
-- |    66     |
-- +-----------+
select max(age) `최대나이`
from fn_select_userinfo2
where instr(email,'gmail.com') > 0;

-- 14. 이름 길이가 3글자인 유저들의 최소 나이를 구하시오.   
-- +-----------+
-- | 최소나이  |
-- +-----------+
-- |    11     |
-- +-----------+
select min(age) `최소나이`
from fn_select_userinfo2
where length(name) = 3;


-- 15. 나이가 30대 이상인 유저들의 평균 나이를 소수점 1자리까지 반올림하여 구하시오.  
-- +-----------+
-- | 평균나이  |
-- +-----------+
-- |   49.0    |
-- +-----------+
select round(avg(age),0) `평균나이`
from fn_select_userinfo2
where age >= 30;

-- 16. 이름에 'b'가 포함된 유저들의 수를 구하시오.  
-- +-----------+
-- | b포함인원 |
-- +-----------+
-- |     3     |
-- +-----------+
select count(*) `b포함인원`
from fn_select_userinfo2
where instr(name,'b') > 0;

-- -- 17. 나이가 가장 많은 유저의 이름과 나이를 조회하시오.  
-- +------+-----+
-- | name | age |
-- +------+-----+
-- | bca  |  66 |
-- +------+-----+
select name, age
from fn_select_userinfo2
where age = (select max(age) from fn_select_userinfo2)
;

-- 18. 유저 이름의 글자 수 평균을 구하고, 소수점 1자리까지 반올림하시오.  
-- +-------------+
-- | 평균글자수  |
-- +-------------+
-- |     3.0     |
-- +-------------+
select round(avg(length(name)),1) `평균글자수`
from fn_select_userinfo2;

-- 19. 나이가 40대 이상인 유저들의 이름을 대문자로 변환하여 조회하시오.  
-- +-------------+
-- | 대문자이름  |
-- +-------------+
-- | DDD         |
-- | ABC         |
-- | BCA         |
-- +-------------+
select upper(name) `대문자이름`
from fn_select_userinfo2
where age >= 40;

-- 20. 유저 이름의 첫 글자와 마지막 글자를 합쳐서 출력하고, 그 중 글자 수가 3 이상인 유저만 조회하시오.  
-- +------+------+
-- | name | test |
-- +------+------+
-- | aaa  | a-a  |
-- | bbb  | b-b  |
-- | ccc  | c-c  |
-- | ddd  | d-d  |
-- | abc  | a-c  |
-- | bca  | b-a  |
-- +------+------+
select name, concat(left(name,1),'-', right(name,1)) `test`
from fn_select_userinfo2
where length(name) >= 3;

-- 21. 가장 나이가 많은 유저의 이름을 조회하시오.   
-- +------+
-- | name |
-- +------+
-- | bca  |
-- +------+

select name
from fn_select_userinfo2
where age = (select max(age) from fn_select_userinfo2);


-- 22. 평균 나이보다 많은 유저들의 이름과 나이를 조회하시오.  
-- +------+-----+
-- | name | age |
-- +------+-----+
-- | ddd  |  44 |
-- | abc  |  55 |
-- | bca  |  66 |
-- +------+-----+
select name, age
from fn_select_userinfo2
where age >= (select avg(age) from fn_select_userinfo2);

-- 23. 가장 짧은 이름을 가진 유저의 이름과 나이를 조회하시오.   
-- +------+-----+
-- | name | age |
-- +------+-----+
-- | aaa  |  11 |
-- | bbb  |  22 |
-- | ccc  |  33 |
-- | ddd  |  44 |
-- | abc  |  55 |
-- | bca  |  66 |
-- +------+-----+
select name, age
from fn_select_userinfo2
where length(name) = (select min(length(name)) from fn_select_userinfo2);


-- 24. 가장 긴 이름을 가진 유저의 이름과 이메일을 조회하시오.  
-- +-------+---------------+
-- | name  | email         |
-- +-------+---------------+
-- | aaaaa | aaa@gmail.com |
-- | bbaab | bbb@gmail.com |
-- | ccaac | ccc@gmail.com |
-- | ddddd | ddd@gmail.com |
-- | baaca | bca@gmail.com |
-- +-------+---------------+
select name, email
from select_userinfo2
where length(name) = (select max(length(name)) from select_userinfo2);

-- 25. 가장 나이가 적은 유저의 이름과 나이를 조회하시오.   
-- +------+-----+
-- | name | age |
-- +------+-----+
-- | aaa  |  11 |
-- +------+-----+
select name, age
from fn_select_userinfo2
where age = (select min(age) from fn_select_userinfo2);

-- 26. 이름에 'a'가 포함된 유저들의 평균 나이를 구하시오.  
-- +-----------+
-- | 평균나이  |
-- +-----------+
-- |   44.0    |
-- +-----------+
select round(avg(age),1) `평균나이`
from fn_select_userinfo2
where instr(name, 'a') > 0
;

-- select * from dept;

