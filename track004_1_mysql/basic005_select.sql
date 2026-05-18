-- SELECT   검색필드1, 검색필드2,,,
-- FROM    테이블명
-- WHERE where_condition
-- ORDER BY   [ASC | DESC]  -- 정렬
-- LIMIT offset;


-- ■ 1. 전체데이터 검색
-- select * from 테이블명
select * from userinfo;

-- ■ 2. 필드검색
-- select  필드1, 필드2  from 테이블명;
select no, name from userinfo;
select no, age from userinfo;

-- ■ 3. 중복빼기 distinct
-- select   distinct 필드1 from 테이블명;
select * from userinfo;

insert into userinfo (name, age) values ('first', 11);
insert into userinfo (name, age) values ('first', 11);

select name from userinfo;
select distinct name from userinfo; -- 중복 빼기
-- select no, distinct name from userinfo; -- error
select distinct no, name from userinfo; -- 가능
-- select 전체 절에 적용되는 distinct

-- ■ 4. 별명 as
-- select  필드1 `별명1`    , 필드2     `별명2`  from 테이블명;
-- select  필드1 as `별명1` , 필드2  as `별명2`  from 테이블명;
select no as `번호`, name as `이름`, age as `나이` from userinfo;
select no `번호`, name `이름`, age `나이` from userinfo;

select no , age*100/0.9 from userinfo; -- alias 안하면 수식 자체가 출력됨
select no `번호`, age*100/0.9 `나이값 계산` from userinfo;
-- ■ 5. 비교연산자
-- select  필드1, 필드2 ,,, from 테이블명  where 컬럼명 비교연산자 조건;
select * from userinfo where name  = 'second';
select * from userinfo where name != 'second';
select * from userinfo where name <> 'second'; -- 권장사항
-- select * from userinfo where name ^= 'second'; -- 옛날 버전

select * from userinfo where age < 33;
select * from userinfo where age <= 33;
select * from userinfo where age > 33;
select * from userinfo where age >= 33;
--    where   비교연산자
--    컬럼 =   조건  : 같다
--    컬럼 <> 조건  : 다르다
--    컬럼 != 조건  : 다르다
--    컬럼 <  조건  : 작다
--    컬럼 <=  조건  : 작거나 같다
--    컬럼 >  조건    : 크다
--    컬럼 >=  조건  : 크거나 같다


-- ■ SQL  SELECT(6) 논리연산자
-- [ SELECT  [필드명] FROM  [테이블이름] WHERE 컬럼명 비교연산자 조건 (AND 또는 OR) 컬럼명 비교연산자 조건  ] 
select * from userinfo where age >= 22 and age <= 33;

-- ■ SQL  SELECT(7) 범위로 추출(BETWEEN연산자)
-- [ SELECT  [필드명] FROM  [테이블이름] WHERE 컬럼명 BETWEEN 시작값 AND 끝값  ] 
select * from userinfo where age between 22 and        33; -- 22와 33 포함 O
select * from userinfo where      age >= 22 and age <= 33;

-- ■ SQL  SELECT(8) 일치하는 레코드추출(IN연산자)
--  [ SELECT  [필드명] FROM  [테이블이름] WHERE 컬럼명 IN(값1, 값2, 값3,,,)  ] 
select * from userinfo where age in (22,44);


-- ■ SQL  SELECT(9) 일치하는 레코드추출(OR연산자)
--  [ SELECT  [필드명] FROM  [테이블이름] WHERE 컬럼명 = 값1 or 컬럼명=값2 ] 
select * from userinfo where age = 22 or age = 33;

-- ■ SQL  SELECT(10) 추출조건으로 NULL지정
-- NULL과 같다는 것을 확인하고 싶을때 (=연산자 사용못함)
-- [ SELECT  [필드명] FROM  [테이블이름] WHERE 컬럼명 IS NULL]
-- SELECT * FROM select_userinfo   WHERE age is null ;
alter table  userinfo modify age int null; -- 빈칸 허용(공간은 있지만 데이터가 없음)

insert into userinfo (name) values ('aaa');
insert into userinfo (name) values ('bbb');

select * from userinfo ;
-- ■ SQL  SELECT(11) 문자열 필터링(LIKE문)
-- 1. a (a로 시작하는 문자열), %b (b로 끝나는), %a% (a가 포함되는), _a% (두번째 글자가 a인)
-- 2. ename >= 'C' (첫문자 C와 같거나 뒤쪽인 사원) (C,D,E,F ...)

select * from userinfo where name = 'a%'; -- a% 와 완전히 같은 문자열 (똑같아)
select * from userinfo where name like 'a%'; -- a로 시작하는 문자열 (비슷한)
select * from userinfo where name like '%b'; -- b로 끝나는 문자열 (비슷한)
select * from userinfo where name like '%b%'; -- b가 포함된 문자열 (비슷한)
select * from userinfo where name like '_i%'; -- 두번째 문자가 i 인 문자열 (비슷한)

-- ■ SQL  SELECT(12)  NOT (반대)
-- 컬럼명 NOT IN (값 ,값,...)
-- 컬럼명 NOT between 최소 AND 최대
-- 컬럼명 NOT LIKE '검색문자열'
--  

select * from userinfo where age >= 22 and age <= 33; -- 22~33살 사이
select * from userinfo where age between 22 and 33;   -- 22~33살 사이

select * from userinfo where age not between 22 and 33; -- 22~33살 사이가 아닌

select * from userinfo where age = 22 or age = 44; -- 22살 이거나 44살 (or)
select * from userinfo where age in (22,44);       -- 22살 이거나 44살 (in)

select * from userinfo where age not in (22,44);   -- 22살,44살이 아닌

select * from userinfo where name like 'f%';       -- 이름이 f 로 시작하는 
select * from userinfo where name not like 'f%';   -- 이름이 f 로 시작하지 않는

select * from userinfo where age is null;          -- age 값이 없는 애들
select * from userinfo where age is not null;      -- age 값이 있는 애들
