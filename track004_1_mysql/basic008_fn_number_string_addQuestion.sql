-- 29. 부서별 평균 급여를 조회하시오.  
-- +--------+------------+-----------+
-- | deptno | dname      | 평균급여  |
-- +--------+------------+-----------+
-- |     10 | ACCOUNTING |   2916.7  |
-- |     20 | RESEARCH   |   2175.0  |
-- |     30 | SALES      |   1566.7  |
-- +--------+------------+-----------+

select d.deptno, d.dname, round(avg(e.sal),1) `평균급여`
from emp e
inner join dept d
on e.deptno = d.deptno
group by deptno
order by deptno;

-- 30. 부서별로 가장 급여가 많은 사원의 이름과 급여를 조회하시오.  
-- +--------+-------+------+
-- | deptno | ename | sal  |
-- +--------+-------+------+
-- |     10 | KING  | 5000 |
-- |     20 | SCOTT | 3000 |
-- |     30 | BLAKE | 2850 |
-- +--------+-------+------+
SELECT DEPTNO, ENAME, SAL
FROM (
SELECT DEPTNO, ENAME, MAX(SAL) AS SAL, RANK() OVER (PARTITION BY DEPTNO ORDER BY MAX(SAL) DESC) AS RNK
FROM EMP
GROUP BY DEPTNO, ENAME
) S
WHERE S.RNK = 1;

-- 31. 부서별로 가장 이름이 긴 사원의 이름과 부서명을 조회하시오.  
-- +------------+--------+----------+
-- | dname      | ename  | 이름길이 |
-- +------------+--------+----------+
-- | ACCOUNTING | MILLER |        6 |
-- | RESEARCH   | MARTIN |        6 |
-- | SALES      | MARTIN |        6 |
-- +------------+--------+----------+
SELECT D.DNAME, S.ENAME, MAX_LEN_ENAME
FROM (
SELECT DEPTNO, ENAME, MAX(LENGTH(ENAME)) AS MAX_LEN_ENAME, RANK() OVER (PARTITION BY DEPTNO ORDER BY MAX(LENGTH(ENAME)) DESC) AS RNK
FROM EMP
GROUP BY DEPTNO, ENAME
) S
INNER JOIN DEPT D
ON D.DEPTNO = S.DEPTNO
WHERE S.RNK = 1;

-- 32. 각 부서별로 평균 급여보다 많은 사원의 이름과 급여를 조회하시오.  
-- +--------+-------+------+
-- | deptno | ename | sal  |
-- +--------+-------+------+
-- |     10 | KING  | 5000 |
-- |     20 | SCOTT | 3000 |
-- |     20 | FORD  | 3000 |
-- |     30 | BLAKE | 2850 |
-- +--------+-------+------+
select m.deptno, m.ename, m.sal
from emp m
inner join (
select deptno, avg(sal) as sal_avg
from emp
group by deptno
) s
on s.deptno = m.deptno
where s.sal_avg <= m.sal
order by deptno;

-- 33. 부서별로 가장 최근에 입사한 사원의 이름과 입사일을 조회하시오.  
-- +--------+-------+------------+
-- | deptno | ename | hiredate   |
-- +--------+-------+------------+
-- |     10 | CLARK | 1981-06-09 |
-- |     20 | SCOTT | 1987-07-13 |
-- |     30 | JAMES | 1981-12-03 |
-- +--------+-------+------------+
select m.deptno, m.ename, m.hiredate
from emp m
inner join (
select deptno, max(hiredate) as lately
from emp
group by deptno
) s
on s.deptno = m.deptno
where s.lately = m.hiredate
order by deptno;


select deptno, ename, max(hiredate) as lately
from emp
group by deptno, ename
order by 1,3 desc;