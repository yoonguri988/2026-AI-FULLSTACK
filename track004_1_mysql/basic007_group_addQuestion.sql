desc group_userinfo;

-- [question11]  반별 국영수 총점 평균을 구하고, 평균이 80점 이상인 반만 출력하세요.
-- +------+-----------+
-- | ban  | 평균총점  |
-- +------+-----------+
-- | A    | 192.6667  |
-- | B    | 178.3333  |
-- +------+-----------+
select ban, sum(kor+eng+math)/3 `평균총점`
from group_userinfo
group by ban
HAVING sum(kor+eng+math)/3 >= 80
order by 1 ASC;
;

desc group_userinfo;
-- [question12]  반별로 국영수 총점이 가장 높은 학생의 이름과 점수를 출력하세요.
-- +------+--------+------+
-- | ban  | name   | 총점 |
-- +------+--------+------+
-- | A    | third  | 279  |
-- | B    | fifth  | 274  |
-- | C    | fourth | 149  |
-- +------+--------+------+
select ban, name, kor+eng+math
from group_userinfo m
where kor+eng+math = (
select max(kor+eng+math)
from group_userinfo s
where s.ban = m.ban
group by ban
)
group by ban, name, (kor+eng+math)
order by ban; 

-- [question13]  전체 학생 중 평균 점수가 가장 높은 반을 출력하세요.
-- +------+-----------+
-- | ban  | 평균점수  |
-- +------+-----------+
-- | A    | 96.3333   |
-- +------+-----------+ 
--  
select ban, avg(tot) `평균점수`
from (
select ban, sum(kor+eng+math)/3 as tot
from group_userinfo 
group by ban, name
) t
group by ban
order by 2 desc, 1
limit 1
;


-- [question14]  반별로 여학생(f)과 남학생(m)의 수를 각각 출력하세요.
-- +------+----------+----------+
-- | ban  | 남학생수 | 여학생수 |
-- +------+----------+----------+
-- | A    |        1 |        0 |
-- | B    |        1 |        1 |
-- | C    |        1 |        1 |
-- +------+----------+----------+
--  
select ban, count(case when sex='m' then 1 end) `남학생 수`, count(case when sex='f' then 1 end) `여학생 수`
from group_userinfo
group by ban;


-- ---------------- ----------------------------------------
-- --------------------------------------------------------  
-- -- 연습문제-21 ★★★
-- 각 부서에서 평균 급여가 가장 높은 직책을 조회하시오.
-- +--------+-----------+-----------+
-- | deptno | job       | 평균급여  |
-- +--------+-----------+-----------+
-- |     10 | PRESIDENT | 5000.0000 |
-- |     20 | ANALYST   | 3000.0000 |
-- |     30 | MANAGER   | 2850.0000 |
-- +--------+-----------+-----------+

select * from

(select deptno, job, max(sal) from emp
group by deptno, job
) t
;

select deptno, job, avg(sal) from emp m
where sal = (select max(sal) from emp s where s.deptno = m.deptno group by deptno)
group by deptno, job
order by deptno asc, sal desc;

-- --------------------------------------------------------
-- --------------------------------------------------------  
-- -- 연습문제-22 
--  전체 직원 중 급여가 상위 3위인 직원의 이름과 급여를 조회하시오.
-- +--------+------+
-- | ename  | sal  |
-- +--------+------+
-- | KING   | 5000 |
-- | SCOTT  | 3000 |
-- | FORD   | 3000 |
-- +--------+------+
select ename, sal
from (select ename, sal, rank() over (order by sal desc) as ranking from emp) sub
where ranking < 3
;

-- --------------------------------------------------------
-- --------------------------------------------------------  
-- -- 연습문제-23 ★★★★
-- 각 부서별로 급여가 가장 높은 직원의 이름과 급여를 조회하시오.
-- +--------+--------+------+
-- | deptno | ename  | sal  |
-- +--------+--------+------+
-- |     10 | KING   | 5000 |
-- |     20 | SCOTT  | 3000 |
-- |     30 | BLAKE  | 2850 |
-- +--------+--------+------+

select deptno, ename, sal
from emp
where sal in (select max(sal) from emp group by deptno)
order by 1;




-- --------------------------------------------------------
-- --------------------------------------------------------  
-- -- 연습문제-24
-- 각 부서별 평균 급여와 전체 평균 급여를 비교하여, 부서 평균이 전체 평균보다 높은 부서만 조회하시오.
-- +--------+-----------+
-- | deptno | 부서평균  |
-- +--------+-----------+
-- |     10 | 2916.6667 |
-- +--------+-----------+

select deptno, avg(sal)
from emp
group by deptno
having avg(sal) > (select avg(sal) from emp)
order by deptno;



-- --------------------------------------------------------
-- --------------------------------------------------------  
-- --  연습문제-25
-- 윈도우 함수 ( RANK() OVER   )를 사용하여 직원별 급여 순위를 조회하시오. 
-- +--------+------+----------+
-- | ename  | sal  | 급여순위 |
-- +--------+------+----------+
-- | KING   | 5000 |        1 |
-- | SCOTT  | 3000 |        2 |
-- | FORD   | 3000 |        2 |
-- | JONES  | 2975 |        4 |
-- | BLAKE  | 2850 |        5 |
-- | ...    | ...  |      ... |
-- +--------+------+----------+   

select ename, sal, rank() over (order by sal desc) `급여 순위`
from emp;

