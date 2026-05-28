-- 1. CRUD (SELECT Subquery)
-- 2. CRUD (SELECT Subquery 연습문제 1~13)
-- 3. CRUD (SELECT Subquery 연습문제 14~25)
-- ________________________________________________________________
-- ________________________________________________________________

-- --------------------------------------------------------
-- --------------------------------------------------------
-- 복습문제1) 
-- +--------+------------+-------+-------+------+
-- | DEPTNO | DNAME      | EMPNO | ENAME | SAL  |
-- +--------+------------+-------+-------+------+
-- |     20 | RESEARCH   |  7566 | JONES | 2975 |
-- |     30 | SALES      |  7698 | BLAKE | 2850 |
-- |     10 | ACCOUNTING |  7782 | CLARK | 2450 |
-- |     20 | RESEARCH   |  7788 | SCOTT | 3000 |
-- |     10 | ACCOUNTING |  7839 | KING  | 5000 |
-- |     20 | RESEARCH   |  7902 | FORD  | 3000 |
-- +--------+------------+-------+-------+------+
-- 6 rows in set (0.00 sec)

-- -- emp ,DEPT 테이블에서  급여가 2000  초과인 사원들의 부서 정보, 사원정보를 다음과 같이   조회하시오.
-- join 4가지 방법을 이용해서 조회하시오. 

select d.deptno, d.dname, e.empno, e.ename, e.sal
from emp e, dept d
where e.deptno = d.deptno
and e.sal > 2000;

select d.deptno, d.dname, e.empno, e.ename, e.sal
from emp e 
join dept d
on e.deptno = d.deptno
where e.sal > 2000;

select d.deptno, d.dname, e.empno, e.ename, e.sal
from emp e 
join dept d
using (deptno)
where e.sal > 2000;

select d.deptno, d.dname, e.empno, e.ename, e.sal
from emp e 
natural join dept d
where e.sal > 2000;




-- --------------------------------------------------------
-- --------------------------------------------------------
-- 복습문제2) 
-- -- emp ,DEPT 테이블에서  각 부서별 평균급여, 최대급여, 최소급여, 사원수를  다음과 같이   조회하시오.
-- -- = 등가조인을 이용하시오.
-- join 4가지 방법을 이용해서 조회하시오.
-- +--------+------------+-----------+---------+---------+-----+
-- | DEPTNO | DNAME      | AVG_SAL   | MAX_SAL | MIN_SAL | CNT |
-- +--------+------------+-----------+---------+---------+-----+
-- |     10 | ACCOUNTING | 2916.6667 |    5000 |    1300 |   3 |
-- |     20 | RESEARCH   | 2175.0000 |    3000 |     800 |   5 |
-- |     30 | SALES      | 1566.6667 |    2850 |     950 |   6 |
-- +--------+------------+-----------+---------+---------+-----+
-- 3 rows in set (0.00 sec) 

select d.deptno, d.dname, avg(sal) `avg_sal`, max(sal) `max_sal`, min(sal) `min_sal`, count(*) `cnt`
from emp e, dept d
where e.deptno = d.deptno
group by d.deptno, d.dname
order by d.deptno;

select d.deptno, d.dname,  avg(sal) `avg_sal`, max(sal) `max_sal`, min(sal) `min_sal`, count(*) `cnt`
from emp e 
join dept d
on e.deptno = d.deptno
group by d.deptno, d.dname
order by d.deptno;

select d.deptno, d.dname, avg(sal) `avg_sal`, max(sal) `max_sal`, min(sal) `min_sal`, count(*) `cnt`
from emp e 
join dept d
using (deptno)
group by d.deptno, d.dname
order by d.deptno;

select d.deptno, d.dname, avg(sal) `avg_sal`, max(sal) `max_sal`, min(sal) `min_sal`, count(*) `cnt`
from emp e 
natural join dept d
group by d.deptno, d.dname
order by d.deptno;


-- --------------------------------------------------------
-- --------------------------------------------------------
-- 복습문제3) 
-- -- emp ,DEPT 테이블에서 모든부서정보와 사원정보를 다음과 같이 부서번호, 사원이름 순으로    조회하시오.
-- -- left , right join을 이용하시오.
-- +--------+------------+-------+--------+-----------+------+
-- | DEPTNO | DNAME      | EMPNO | ENAME  | JOB       | SAL  |
-- +--------+------------+-------+--------+-----------+------+
-- |     10 | ACCOUNTING |  7782 | CLARK  | MANAGER   | 2450 |
-- |     10 | ACCOUNTING |  7839 | KING   | PRESIDENT | 5000 |
-- |     10 | ACCOUNTING |  7934 | MILLER | CLERK     | 1300 |
-- |     20 | RESEARCH   |  7876 | ADAMS  | CLERK     | 1100 |
-- |     20 | RESEARCH   |  7902 | FORD   | ANALYST   | 3000 |
-- |     20 | RESEARCH   |  7566 | JONES  | MANAGER   | 2975 |
-- |     20 | RESEARCH   |  7788 | SCOTT  | ANALYST   | 3000 |
-- |     20 | RESEARCH   |  7369 | SMITH  | CLERK     |  800 |
-- |     30 | SALES      |  7499 | ALLEN  | SALESMAN  | 1600 |
-- |     30 | SALES      |  7698 | BLAKE  | MANAGER   | 2850 |
-- |     30 | SALES      |  7900 | JAMES  | CLERK     |  950 |
-- |     30 | SALES      |  7654 | MARTIN | SALESMAN  | 1250 |
-- |     30 | SALES      |  7844 | TURNER | SALESMAN  | 1500 |
-- |     30 | SALES      |  7521 | WARD   | SALESMAN  | 1250 |
-- |     40 | OPERATIONS |  NULL | NULL   | NULL      | NULL |
-- +--------+------------+-------+--------+-----------+------+
-- 15 rows in set (0.00 sec)

select d.deptno, d.dname, e.empno, e.ename, e.job, e.sal
from dept d
left outer join emp e
on d.deptno = e.deptno
order by d.deptno, e.ename;
 







-- ■진행1. CRUD (SELECT Subquery)
-- [실습] 다음과 같이 데이터를 준비하시오
-- mysql> desc sub_userinfo;
-- +-------+-------------+------+-----+---------+----------------+
-- | Field | Type        | Null | Key | Default | Extra          |
-- +-------+-------------+------+-----+---------+----------------+
-- | no    | int(11)     | NO   | PRI | NULL    | auto_increment |
-- | name  | varchar(20) | NO   |     | NULL    |                |
-- | age   | int(11)     | NO   |     | NULL    |                |
-- | sex   | char(2)     | YES  |     | NULL    |                |
-- | kor   | int(11)     | YES  |     | NULL    |                |
-- | eng   | int(11)     | YES  |     | NULL    |                |
-- | math  | int(11)     | YES  |     | NULL    |                |
-- | ban   | char(2)     | YES  |     | NULL    |                |
-- | sns   | char(2)     | YES  |     | y       |                |
-- +-------+-------------+------+-----+---------+----------------+

-- mysql> select * from sub_userinfo;
-- +----+--------+-----+------+------+------+------+------+------+
-- | no | name   | age | sex  | kor  | eng  | math | ban  | sns  |
-- +----+--------+-----+------+------+------+------+------+------+
-- |  1 | first  |  11 | NULL |  100 |  100 |   99 | A    | n    |
-- |  2 | second |  22 | m    |   89 |   92 |   78 | B    | y    |
-- |  3 | third  |  33 | m    |   90 |   92 |   97 | A    | y    |
-- |  4 | fourth |  44 | f    |   40 |   42 |   67 | C    | n    |
-- |  5 | fifth  |  55 | f    |   89 |   86 |   99 | B    | y    |
-- |  6 | sixth  |  66 | m    |   10 |   20 |   44 | C    | n    |
-- +----+--------+-----+------+------+------+------+------+------+

-- 6 rows in set (0.00 sec)

use mbasic;
CREATE TABLE sub_userinfo
( no  int not null  auto_increment primary key ,
  name varchar(20)  not null ,
  age  int not null ,
  sex  char(2) ,
  kor  int ,
  eng  int   ,
  math int   ,
  ban  char(2),
  sns  char(2) default 'y' );



insert into  sub_userinfo  (name, age , sns , kor , eng, math , ban)
values  ('first'  , 11  , 'n' , 100 , 100 , 99 , 'A');
insert into  sub_userinfo  (name, age, sex , kor , eng, math , ban)
values  ('second' , 22 , 'm' , 89 , 92 , 78 , 'B');
insert into  sub_userinfo  (name, age, sex , kor , eng, math , ban)
values  ('third'  , 33 , 'm' , 90, 92,97,'A');
insert into  sub_userinfo  (name, age, sex , sns , kor , eng, math , ban)
values  ('fourth' , 44 , 'f' , 'n' , 40,42,67 , 'C');
insert into  sub_userinfo  (name, age, sex , kor , eng, math , ban)
values  ('fifth'  , 55 , 'f' , 89 , 86 , 99 , 'B');
insert into  sub_userinfo  (name, age, sex, sns , kor , eng, math , ban)
values  ('sixth'  , 66 , 'm', 'n' , 10,20,44 , 'C');


select * from sub_userinfo;


-- ------------------------------------------------------------
-- ------------------------------------------------------------ [QUESTION]

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- BASIC1. 평균나이 이상인 레코드를 추출하시오.
-- +----+--------+-----+------+------+------+------+------+------+
-- | no | name   | age | sex  | kor  | eng  | math | ban  | sns  |
-- +----+--------+-----+------+------+------+------+------+------+
-- |  4 | fourth |  44 | f    |   40 |   42 |   67 | C    | n    |
-- |  5 | fifth  |  55 | f    |   89 |   86 |   99 | B    | y    |
-- |  6 | sixth  |  66 | m    |   10 |   20 |   44 | C    | n    |
-- +----+--------+-----+------+------+------+------+------+------+
-- 3 rows in set (0.02 sec)
select *
from sub_userinfo
where age >= (select avg(age) from sub_userinfo);

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- BASIC2. first와 같은반친구들의 국어, 영어, 수학점수를 추출하시오.
-- +------+-------+------+------+------+
-- | ban  | name  | kor  | eng  | math |
-- +------+-------+------+------+------+
-- | A    | first |  100 |  100 |   99 |
-- | A    | third |   90 |   92 |   97 |
-- +------+-------+------+------+------+
-- 2 rows in set (0.00 sec)
select ban, name, kor, eng, math
from sub_userinfo
where ban = (select ban from sub_userinfo where name='first');


-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- BASIC3. first와 같은반친구들의  국어평균점수, 영어평균점수, 수학평균점수를 추출하시오.
-- +------+---------+---------+---------+
-- | ban  | kor     | eng     | math    |
-- +------+---------+---------+---------+
-- | A    | 95.0000 | 96.0000 | 98.0000 |
-- +------+---------+---------+---------+
select ban, avg(kor) as kor, avg(eng) as eng, avg(math) as math
from sub_userinfo
where ban = (select ban from sub_userinfo where name='first')
group by ban;



-- ■진행2. CRUD (SELECT Subquery 연습문제1~13)
-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-1
-- -- emp 테이블에서  사원이름이 JONES 인 사원데이터를  조회하시오.
-- +------+
-- | SAL  |
-- +------+
-- | 2975 |
-- +------+
-- 1 row in set (0.00 sec)
select sal
from emp
where ename ='JONES';



-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-2
-- -- emp 테이블에서  급여가 2975보다 높은 사원데이터를  조회하시오.
-- +-------+-------+-----------+------+------------+------+------+--------+
-- | empno | ename | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- |  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- |  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- 3 rows in set (0.00 sec)
select *
from emp
where sal > 2975;

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-3
-- -- emp 테이블에서  사원이름이 JONES의 급여보다 높은 급여를 받는  사원데이터를  조회하시오.
-- +-------+-------+-----------+------+------------+------+------+--------+
-- | empno | ename | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- |  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- |  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- 3 rows in set (0.00 sec)
select *
from emp
where sal > (select sal from emp where ename='JONES');

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-4
-- -- emp 테이블에서   scott보다 빨리 입사한 사원목록을 조회하시오.
-- +-------+--------+-----------+------+------------+------+------+--------+
-- | empno | ename  | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+--------+-----------+------+------------+------+------+--------+
-- |  7369 | SMITH  | CLERK     | 7902 | 1980-12-17 |  800 | NULL |     20 |
-- |  7499 | ALLEN  | SALESMAN  | 7698 | 1981-02-20 | 1600 |  300 |     30 |
-- |  7521 | WARD   | SALESMAN  | 7698 | 1981-02-22 | 1250 |  500 |     30 |
-- |  7566 | JONES  | MANAGER   | 7839 | 1981-04-02 | 2975 | NULL |     20 |
-- |  7654 | MARTIN | SALESMAN  | 7698 | 1981-09-28 | 1250 | 1400 |     30 |
-- |  7698 | BLAKE  | MANAGER   | 7839 | 1981-05-01 | 2850 | NULL |     30 |
-- |  7782 | CLARK  | MANAGER   | 7839 | 1981-06-09 | 2450 | NULL |     10 |
-- |  7839 | KING   | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- |  7844 | TURNER | SALESMAN  | 7698 | 1981-09-08 | 1500 |    0 |     30 |
-- |  7900 | JAMES  | CLERK     | 7698 | 1981-12-03 |  950 | NULL |     30 |
-- |  7902 | FORD   | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- |  7934 | MILLER | CLERK     | 7782 | 1982-01-23 | 1300 | NULL |     10 |
-- +-------+--------+-----------+------+------------+------+------+--------+
-- 12 rows in set (0.00 sec)
select *
from emp
where hiredate < (select hiredate from emp where ename='SCOTT');



-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-5
-- -- emp 테이블에서  20번 부서에 속한 사원중
-- -- 전체 사원의 평균급여보다 높은 급여를 받는 사원정보와 소속부서 정보를 조회하시오.
-- +-------+-------+---------+------+--------+----------+--------+
-- | EMPNO | ENAME | JOB     | SAL  | DEPTNO | DNAME    | LOC    |
-- +-------+-------+---------+------+--------+----------+--------+
-- |  7902 | FORD  | ANALYST | 3000 |     20 | RESEARCH | DALLAS |
-- |  7788 | SCOTT | ANALYST | 3000 |     20 | RESEARCH | DALLAS |
-- |  7566 | JONES | MANAGER | 2975 |     20 | RESEARCH | DALLAS |
-- +-------+-------+---------+------+--------+----------+--------+
-- 3 rows in set (0.00 sec)
select e.empno, e.ename, e.job, e.sal, d.deptno, d.dname, d.loc
from emp e
join dept d
using (deptno)
where e.deptno = 20
and e.sal > (select avg(sal) from emp)
order by e.sal desc;



-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-6
-- -- emp 테이블에서  부서번호가 20 또는 30인 사원정보를  조회하시오.
-- +-------+--------+----------+------+------------+------+------+--------+
-- | empno | ename  | job      | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+--------+----------+------+------------+------+------+--------+
-- |  7369 | SMITH  | CLERK    | 7902 | 1980-12-17 |  800 | NULL |     20 |
-- |  7499 | ALLEN  | SALESMAN | 7698 | 1981-02-20 | 1600 |  300 |     30 |
-- |  7521 | WARD   | SALESMAN | 7698 | 1981-02-22 | 1250 |  500 |     30 |
-- |  7566 | JONES  | MANAGER  | 7839 | 1981-04-02 | 2975 | NULL |     20 |
-- |  7654 | MARTIN | SALESMAN | 7698 | 1981-09-28 | 1250 | 1400 |     30 |
-- |  7698 | BLAKE  | MANAGER  | 7839 | 1981-05-01 | 2850 | NULL |     30 |
-- |  7788 | SCOTT  | ANALYST  | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7844 | TURNER | SALESMAN | 7698 | 1981-09-08 | 1500 |    0 |     30 |
-- |  7876 | ADAMS  | CLERK    | 7788 | 1987-05-23 | 1100 | NULL |     20 |
-- |  7900 | JAMES  | CLERK    | 7698 | 1981-12-03 |  950 | NULL |     30 |
-- |  7902 | FORD   | ANALYST  | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- +-------+--------+----------+------+------------+------+------+--------+
-- 11 rows in set (0.00 sec)
select *
from emp
where deptno in (20,30);




--  --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-7
-- -- emp 테이블에서  부서번호 별로 최대급여를 조회하시오.
-- +----------+
-- | MAX(SAL) |
-- +----------+
-- |     5000 |
-- |     3000 |
-- |     2850 |
-- +----------+
-- 3 rows in set (0.00 sec)
select max(sal)
from emp
group by deptno
order by 1 desc;



-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-8
-- -- emp 테이블에서  각 부서별 최고 급여와 동일한 급여를 받는 사원정보를   조회하시오.
-- +-------+-------+-----------+------+------------+------+------+--------+
-- | empno | ename | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- |  7698 | BLAKE | MANAGER   | 7839 | 1981-05-01 | 2850 | NULL |     30 |
-- |  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- |  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- 4 rows in set (0.00 sec)
select *
from emp
where (deptno, sal) in ( select deptno, max(sal) from emp group by deptno);


-- --------------------------------------------------------
-- --------------------------------------------------------
-- --------------------------------------------------------
-- --------------------------------------------------------
-- --------------------------------------------------------
-- --------------------------------------------------------
-- 참고))))

-- [기본]  ANY,ALL
-- [기본]  ANY,ALL
-- [SUBQUERY]
-- - 메인쿼리안에 또다른 쿼리가 있으것
-- - 반드시 서브쿼리를 괄호로 묶는다
-- - 메인쿼리보다 서브쿼리가 먼저 실행되어 실행된 결과를 메인쿼리의 조건으로 사용한다.
-- - 서브리의 결과가 한개이상이 반환될때  in, ANY(SOME), ALL , EXISTS 을 사용할수 있다.
-- -                        한개일때                  = 을 사용한다.

-- ##  다중행연산자
-- in, ANY(SOME), ALL , EXISTS

-- 1. IN               : 메인쿼리의 데이터가 서브쿼리의 결과중 ( 하나라도 일치한데이터가 있다면 TRUE )
-- 2. ANY, SOME : 메인쿼리의 조건식을 만족하는 서브쿼리의 결과가 하나이상이면 TRUE
-- 3. ALL             : 메인쿼리의 조건식을 서브쿼리의 결과모두가 만족하면 TRUE
-- 4. EXISTS        :  서브쿼리의 결과가 존재하면 TRUE

-- ANY  :  하나라도 일치하면 참
--     컬럼명   <   ANY (1,2,3)    --   최대값보다 작다                 |(1)   |(2)   |(3)  ★
--     컬럼명   >   ANY (1,2,3)    --   최소값보다  크다              ★ |(1)   |(2)   |(3)

-- ALL    :   모두일치
--     컬럼명   <   ALL (1,2,3)    --   최소값보다  작다               ★ |(1)   |(2)   |(3)
--     컬럼명   >   ALL (1,2,3)    --   최대값보다 크다                  |(1)   |(2)   |(3)★

create table  atest   as                select   1  num  from  dual
                    union all    select   2       from  dual   
                    union all    select   3       from  dual   
                    union all    select   4       from  dual   
                    union all    select   5       from  dual   
                    union all    select   6       from  dual;

select * from atest;

-- Q1) 
select num  from atest  where num in(3,4,5); -- 3,4,5    

select   num 
from     atest  
where    num < any(    select num  from atest  where num in(3,4,5)    )  -- 각각 보다(any) 작은 것들의 조건 -> 합집합
order by num;

-- Q2) 
select   num 
from     atest  
where    num > any(    select num  from atest  where num in(3,4,5)    )   -- 각각 보다(any) 큰 것들을 조건 -> 합집합
order by num;


-- Q3) 
select   num 
from     atest  
where    num < all(    select num  from atest  where num in(3,4,5)    )  -- 모두(all)보다 작은 것들 조건 -> 교집합
order by num;


-- Q4) 
select   num 
from     atest  
where    num > all(    select num  from atest  where num in(3,4,5)    )  -- 모두(all)보다 큰것 조건 -> 교집합
order by num;
                    
-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-9
-- -- emp 테이블에서  ANY를 이용하여 각 부서별 최고 급여와 동일한 급여를 받는 사원정보를   조회하시오.
-- -- ※ ANY , SOME 연산자는 서브쿼리가 반환한 여러결과값 중
-- -- 메인쿼리와 조건식을 사용한 결과가 하나라도 TRUE라면 메인쿼리조건식을 TRUE로 반환해주는 연산자
-- +-------+-------+-----------+------+------------+------+------+--------+
-- | empno | ename | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- |  7698 | BLAKE | MANAGER   | 7839 | 1981-05-01 | 2850 | NULL |     30 |
-- |  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- |  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- 4 rows in set (0.00 sec)
select *
from emp e1
where sal = any ( select max(sal) from emp e2 where e1.deptno = e2.deptno group by deptno);


-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-10
-- -- emp 테이블에서 SOME를 이용하여 각 부서별 최고 급여와 동일한 급여를 받는 사원정보를   조회하시오.
-- -- ※ ANY , SOME 연산자는 서브쿼리가 반환한 여러결과값 중
-- -- 메인쿼리와 조건식을 사용한 결과가 하나라도 TRUE라면 메인쿼리조건식을 TRUE로 반환해주는 연산자
-- +-------+-------+-----------+------+------------+------+------+--------+
-- | empno | ename | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- |  7698 | BLAKE | MANAGER   | 7839 | 1981-05-01 | 2850 | NULL |     30 |
-- |  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- |  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- 4 rows in set (0.00 sec)
select *
from emp e1
where e1.sal = some ( select max(sal) from emp e2 where e1.deptno = e2.deptno group by deptno);

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-11
-- -- emp 테이블에서  부서번호가 30인 사원의 급여를  조회하시오.
-- +------+
-- | SAL  |
-- +------+
-- | 1600 |
-- | 1250 |
-- | 1250 |
-- | 2850 |
-- | 1500 |
-- |  950 |
-- +------+
-- 6 rows in set (0.00 sec)
select sal
from emp
where deptno = 30;


-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-12
-- emp 테이블에서 ANY를 이용하여 30번 부서의 최고 급여보다 작은 급여를 받는 사원정보
-- -- ※ ANY , SOME 연산자는 서브쿼리가 반환한 여러결과값 중
-- -- 메인쿼리와 조건식을 사용한 결과가 하나라도 TRUE라면 메인쿼리조건식을 TRUE로 반환해주는 연산자
-- +-------+--------+----------+------+------------+------+------+--------+
-- | empno | ename  | job      | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+--------+----------+------+------------+------+------+--------+
-- |  7369 | SMITH  | CLERK    | 7902 | 1980-12-17 |  800 | NULL |     20 |
-- |  7900 | JAMES  | CLERK    | 7698 | 1981-12-03 |  950 | NULL |     30 |
-- |  7876 | ADAMS  | CLERK    | 7788 | 1987-05-23 | 1100 | NULL |     20 |
-- |  7521 | WARD   | SALESMAN | 7698 | 1981-02-22 | 1250 |  500 |     30 |
-- |  7654 | MARTIN | SALESMAN | 7698 | 1981-09-28 | 1250 | 1400 |     30 |
-- |  7934 | MILLER | CLERK    | 7782 | 1982-01-23 | 1300 | NULL |     10 |
-- |  7844 | TURNER | SALESMAN | 7698 | 1981-09-08 | 1500 |    0 |     30 |
-- |  7499 | ALLEN  | SALESMAN | 7698 | 1981-02-20 | 1600 |  300 |     30 |
-- |  7782 | CLARK  | MANAGER  | 7839 | 1981-06-09 | 2450 | NULL |     10 |
-- +-------+--------+----------+------+------------+------+------+--------+
-- 9 rows in set (0.00 sec)

select *
from emp e1
where sal < any (select max(sal) from emp e2 where e1.deptno = e2.deptno group by deptno)
order by sal;




-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-13
-- -- emp 테이블에서  30번 부서 사원들의 최소급여보다 많은 급여를 받는  사원데이터를  조회하시오.
-- +-------+--------+-----------+------+------------+------+------+--------+
-- | empno | ename  | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+--------+-----------+------+------------+------+------+--------+
-- |  7839 | KING   | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- |  7788 | SCOTT  | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7902 | FORD   | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- |  7566 | JONES  | MANAGER   | 7839 | 1981-04-02 | 2975 | NULL |     20 |
-- |  7698 | BLAKE  | MANAGER   | 7839 | 1981-05-01 | 2850 | NULL |     30 |
-- |  7782 | CLARK  | MANAGER   | 7839 | 1981-06-09 | 2450 | NULL |     10 |
-- |  7499 | ALLEN  | SALESMAN  | 7698 | 1981-02-20 | 1600 |  300 |     30 |
-- |  7844 | TURNER | SALESMAN  | 7698 | 1981-09-08 | 1500 |    0 |     30 |
-- |  7934 | MILLER | CLERK     | 7782 | 1982-01-23 | 1300 | NULL |     10 |
-- |  7521 | WARD   | SALESMAN  | 7698 | 1981-02-22 | 1250 |  500 |     30 |
-- |  7654 | MARTIN | SALESMAN  | 7698 | 1981-09-28 | 1250 | 1400 |     30 |
-- |  7876 | ADAMS  | CLERK     | 7788 | 1987-05-23 | 1100 | NULL |     20 |
-- +-------+--------+-----------+------+------------+------+------+--------+
-- 12 rows in set (0.00 sec)

select * 
from emp 
where sal > any (select sal from emp where deptno = 30)
order by sal desc;

-- ■진행3. CRUD (SELECT Subquery 연습문제 14~25)
-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-14
-- -- emp 테이블에서  부서번호가 30번인 사원들의 최소급여보다 더 적은 급여를 받는 사원을  조회하시오.
-- -- ※ ALL 연산자는 서브쿼리가 반환한 여러결과값 모든조건을 만족해야지  TRUE로 반환해주는 연산자
-- +-------+-------+-------+------+------------+------+------+--------+
-- | empno | ename | job   | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+-------+-------+------+------------+------+------+--------+
-- |  7369 | SMITH | CLERK | 7902 | 1980-12-17 |  800 | NULL |     20 |
-- +-------+-------+-------+------+------------+------+------+--------+
-- 1 row in set (0.00 sec)

select *
from emp
where sal < all (select sal from emp where deptno = 30);



-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-15
-- -- emp 테이블에서  부서번호가 30번인 사원들의 최대급여보다 더 많이 급여를 받는 사원을  조회하시오.
-- -- ※ ALL 연산자는 서브쿼리가 반환한 여러결과값 모든조건을 만족해야지  TRUE로 반환해주는 연산자
-- +-------+-------+-----------+------+------------+------+------+--------+
-- | empno | ename | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- |  7566 | JONES | MANAGER   | 7839 | 1981-04-02 | 2975 | NULL |     20 |
-- |  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- |  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- 4 rows in set (0.00 sec)
select *
from emp
where sal > all (select sal from emp where deptno = 30)
order by sal;

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-16
-- -- emp 테이블에서  부서번호가 10인  부서명이 존재하다면  그부서의 사원데이터를  조회하시오.
-- -- ※  EXISTS 서브쿼리에 결과값이 하나이상 존재하면 조건식이 모두 TRUE , 존재하지 않으면 FALSE
-- +-------+--------+-----------+------+------------+------+------+--------+
-- | empno | ename  | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+--------+-----------+------+------------+------+------+--------+
-- |  7369 | SMITH  | CLERK     | 7902 | 1980-12-17 |  800 | NULL |     20 |
-- |  7499 | ALLEN  | SALESMAN  | 7698 | 1981-02-20 | 1600 |  300 |     30 |
-- |  7521 | WARD   | SALESMAN  | 7698 | 1981-02-22 | 1250 |  500 |     30 |
-- |  7566 | JONES  | MANAGER   | 7839 | 1981-04-02 | 2975 | NULL |     20 |
-- |  7654 | MARTIN | SALESMAN  | 7698 | 1981-09-28 | 1250 | 1400 |     30 |
-- |  7698 | BLAKE  | MANAGER   | 7839 | 1981-05-01 | 2850 | NULL |     30 |
-- |  7782 | CLARK  | MANAGER   | 7839 | 1981-06-09 | 2450 | NULL |     10 |
-- |  7788 | SCOTT  | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7839 | KING   | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- |  7844 | TURNER | SALESMAN  | 7698 | 1981-09-08 | 1500 |    0 |     30 |
-- |  7876 | ADAMS  | CLERK     | 7788 | 1987-05-23 | 1100 | NULL |     20 |
-- |  7900 | JAMES  | CLERK     | 7698 | 1981-12-03 |  950 | NULL |     30 |
-- |  7902 | FORD   | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- |  7934 | MILLER | CLERK     | 7782 | 1982-01-23 | 1300 | NULL |     10 |
-- +-------+--------+-----------+------+------------+------+------+--------+
-- 14 rows in set (0.00 sec)
select * from emp
where exists (select dname from dept where deptno = 10);

-- SELECT *
--   FROM EMP
--  WHERE EXISTS (SELECT DNAME
--                  FROM DEPT
--                 WHERE DEPTNO = 10);


-- -- emp 테이블에서  부서번호가 10인  부서명이 존재하다면  그부서의 사원데이터를  조회하시오.
-- -- ※  EXISTS 서브쿼리에 결과값이 하나이상 존재하면 조건식이 모두 TRUE , 존재하지 않으면 FALSE
-- -- 부서명이 존재하므로 데이터를 출력할수 있음.


-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-17
-- -- emp 테이블에서  부서번호가 50인  부서명이 존재하다면  그부서의 사원데이터를  조회하시오.
-- -- ※  EXISTS 서브쿼리에 결과값이 하나이상 존재하면 조건식이 모두 TRUE , 존재하지 않으면 FALSE
-- 선택된 행 없음
select * from emp
where exists (select dname from dept where deptno = 50);

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-18 ★★★★★
-- -- emp 테이블에서   부서별 부서번호와 최대급여가  같은사원을 조회하시오.
-- +-------+-------+-----------+------+------------+------+------+--------+
-- | empno | ename | job       | mgr  | hiredate   | sal  | comm | deptno |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- |  7698 | BLAKE | MANAGER   | 7839 | 1981-05-01 | 2850 | NULL |     30 |
-- |  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000 | NULL |     20 |
-- |  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000 | NULL |     10 |
-- |  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000 | NULL |     20 |
-- +-------+-------+-----------+------+------------+------+------+--------+
-- 4 rows in set (0.00 sec)
select *
from emp e1
where sal >= all (select sal from emp e2 where e1.deptno = e2.deptno group by deptno);

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-19
-- -- emp ,DEPT 테이블에서  부서번호가 10인 사원의 정보와 부서의 정보를  조회하시오.
-- -- FROM 절에서 부서번호가 10인 사원정보를 구하는 인라인뷰(FROM절에 사용하는 서브쿼리)를 이용하세요.
-- +-------+--------+--------+------------+----------+
-- | empno | ename  | deptno | dname      | loc      |
-- +-------+--------+--------+------------+----------+
-- |  7782 | CLARK  |     10 | ACCOUNTING | NEW YORK |
-- |  7839 | KING   |     10 | ACCOUNTING | NEW YORK |
-- |  7934 | MILLER |     10 | ACCOUNTING | NEW YORK |
-- +-------+--------+--------+------------+----------+
-- 3 rows in set (0.00 sec)

select e.empno, e.ename, d.deptno, d.dname, d.loc
from (
select empno, ename, deptno 
from emp
where deptno = 10
) e
join dept d
using (deptno)
;



-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-20
-- -- emp , SALGRADE 테이블에서  서브쿼리를 이용하여 다음과 같이 조회하시오
-- -- 열영역 안에서 사용하는 스칼라 쿼리
-- -- 힌트1. SALGRADE테이블엥서  최저급여와 최대급여 사이에 해당하는 급여의 등급
-- -- 힌트2. DEPT테이블에서  사원테이블의 부서번호와 부서테이블의 부서번호가 같은 부서이름
-- +-------+--------+-----------+------+----------+--------+------------+
-- | EMPNO | ENAME  | JOB       | SAL  | SALGRADE | DEPTNO | DNAME      |
-- +-------+--------+-----------+------+----------+--------+------------+
-- |  7369 | SMITH  | CLERK     |  800 |        1 |     20 | RESEARCH   |
-- |  7499 | ALLEN  | SALESMAN  | 1600 |        3 |     30 | SALES      |
-- |  7521 | WARD   | SALESMAN  | 1250 |        2 |     30 | SALES      |
-- |  7566 | JONES  | MANAGER   | 2975 |        4 |     20 | RESEARCH   |
-- |  7654 | MARTIN | SALESMAN  | 1250 |        2 |     30 | SALES      |
-- |  7698 | BLAKE  | MANAGER   | 2850 |        4 |     30 | SALES      |
-- |  7782 | CLARK  | MANAGER   | 2450 |        4 |     10 | ACCOUNTING |
-- |  7788 | SCOTT  | ANALYST   | 3000 |        4 |     20 | RESEARCH   |
-- |  7839 | KING   | PRESIDENT | 5000 |        5 |     10 | ACCOUNTING |
-- |  7844 | TURNER | SALESMAN  | 1500 |        3 |     30 | SALES      |
-- |  7876 | ADAMS  | CLERK     | 1100 |        1 |     20 | RESEARCH   |
-- |  7900 | JAMES  | CLERK     |  950 |        1 |     30 | SALES      |
-- |  7902 | FORD   | ANALYST   | 3000 |        4 |     20 | RESEARCH   |
-- |  7934 | MILLER | CLERK     | 1300 |        2 |     10 | ACCOUNTING |
-- +-------+--------+-----------+------+----------+--------+------------+
-- 14 rows in set (0.00 sec)

select e.empno, e.ename, e.job, e.sal
    , (select s.grade from salgrade s where e.sal between s.losal and s.hisal) as salgrade
    , e.deptno
    , (select dname from dept d where d.deptno = e.deptno) as dname
from emp e;




-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-21
-- -- emp ,DEPT 테이블에서  ALLEN과 같은 직책인  사원데이터를  다음과 같이  조회하시오.
-- +----------+-------+--------+------+--------+-------+
-- | JOB      | EMPNO | ENAME  | SAL  | DEPTNO | DNAME |
-- +----------+-------+--------+------+--------+-------+
-- | SALESMAN |  7844 | TURNER | 1500 |     30 | SALES |
-- | SALESMAN |  7654 | MARTIN | 1250 |     30 | SALES |
-- | SALESMAN |  7521 | WARD   | 1250 |     30 | SALES |
-- | SALESMAN |  7499 | ALLEN  | 1600 |     30 | SALES |
-- +----------+-------+--------+------+--------+-------+
-- 4 rows in set (0.00 sec)

select e.job, e.empno, e.ename, e.sal, d.deptno, d.dname
from emp e
join dept d using (deptno)
where e.job = (select job from emp where ename='ALLEN')
order by 2 desc;

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-22
-- -- emp ,DEPT , SALGRADE테이블에서  평균 급여보다 많은 급여를 받는 사원들의 정보를  다음과 같이  조회하시오.
-- -- 급여가 많은순으로 정렬 만약에 같은 급여가 있다면 사원번호를 오른차순으로 정렬하시오.
-- +-------+-------+------------+------------+----------+------+-------+
-- | EMPNO | ENAME | DNAME      | HIREDATE   | LOC      | SAL  | GRADE |
-- +-------+-------+------------+------------+----------+------+-------+
-- |  7839 | KING  | ACCOUNTING | 1981-11-17 | NEW YORK | 5000 |     5 |
-- |  7788 | SCOTT | RESEARCH   | 1987-04-19 | DALLAS   | 3000 |     4 |
-- |  7902 | FORD  | RESEARCH   | 1981-12-03 | DALLAS   | 3000 |     4 |
-- |  7566 | JONES | RESEARCH   | 1981-04-02 | DALLAS   | 2975 |     4 |
-- |  7698 | BLAKE | SALES      | 1981-05-01 | CHICAGO  | 2850 |     4 |
-- |  7782 | CLARK | ACCOUNTING | 1981-06-09 | NEW YORK | 2450 |     4 |
-- +-------+-------+------------+------------+----------+------+-------+
-- 6 rows in set (0.00 sec)
select e.empno, e.ename, d.dname, e.hiredate, d.loc, e.sal
    , ( select s.grade from salgrade s where e.sal between s.losal and s.hisal) as grade
from emp e
join dept d using (deptno)
where sal > (select avg(sal) from emp)
order by e.sal desc, e.empno asc
;


-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-23
-- -- emp , DEPT테이블에서  10번부서에 근무하는 사원중
-- -- 30번 부서에는 존재하지 않는 직책을 가진 사원들의 정보를 다음과 같이  조회하시오.
-- +-------+-------+-----------+--------+------------+----------+
-- | EMPNO | ENAME | JOB       | DEPTNO | DNAME      | LOC      |
-- +-------+-------+-----------+--------+------------+----------+
-- |  7839 | KING  | PRESIDENT |     10 | ACCOUNTING | NEW YORK |
-- +-------+-------+-----------+--------+------------+----------+
-- 1 row in set (0.00 sec)

select * from emp where deptno = 10 
and job not in (select job from emp where deptno = 30);

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-24
-- -- emp , SALGRADE테이블에서
-- --직책이 SALESMAN 인 사람들의 최고 급여보다 높은 급여를  받는  사원들의 정보를 다음과 같이  조회하시오.
-- -- 다중행 함수 사용하지 않는 방법
-- +-------+-------+------+-------+
-- | EMPNO | ENAME | SAL  | GRADE |
-- +-------+-------+------+-------+
-- |  7566 | JONES | 2975 |     4 |
-- |  7698 | BLAKE | 2850 |     4 |
-- |  7782 | CLARK | 2450 |     4 |
-- |  7788 | SCOTT | 3000 |     4 |
-- |  7839 | KING  | 5000 |     5 |
-- |  7902 | FORD  | 3000 |     4 |
-- +-------+-------+------+-------+
-- 6 rows in set (0.00 sec)
select e.empno, e.ename, e.sal
    , (select s.grade from salgrade s where e.sal between s.losal and s.hisal) as grade
from emp e
where e.sal > (select max(sal) from emp where job = 'SALESMAN');

-- --------------------------------------------------------
-- --------------------------------------------------------
-- -- 연습문제-25
-- -- emp , SALGRADE테이블에서
-- --직책이 SALESMAN 인 사람들의 최고 급여보다 높은 급여를  받는  사원들의 정보를 다음과 같이  조회하시오.
-- --다중행 함수 사용하는 방법
-- +-------+-------+------+-------+
-- | EMPNO | ENAME | SAL  | GRADE |
-- +-------+-------+------+-------+
-- |  7566 | JONES | 2975 |     4 |
-- |  7698 | BLAKE | 2850 |     4 |
-- |  7782 | CLARK | 2450 |     4 |
-- |  7788 | SCOTT | 3000 |     4 |
-- |  7839 | KING  | 5000 |     5 |
-- |  7902 | FORD  | 3000 |     4 |
-- +-------+-------+------+-------+
-- 6 rows in set (0.00 sec)
select e.empno, e.ename, e.sal
    , (select s.grade from salgrade s where e.sal between s.losal and s.hisal) as grade
from emp e
where e.sal > all (select sal from emp where job = 'SALESMAN');
