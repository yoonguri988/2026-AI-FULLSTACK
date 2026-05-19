-- 1. 복습문제
-- 2. CRUD (SELECT ORDER BY , LIMIT)
-- 3. CRUD (SELECT 연습문제)
 
-- ________________________________________________________________
-- ________________________________________________________________

-- ■ 진행1. CRUD 복습문제
-- > 복습문제
--  다음의 결과가나오게 조회하시오.
-- emp 테이블에서
-- 추가수당(comm) 이 없고
-- 상급자(mgr)은 존재하며
-- 직책(JOB) 'MANAGER', 'CLERK' 중에서
-- 사원이름(ename)의 두번째 글자가 L이아닌
-- 사원데이터를  조회하시오

select * from emp
where comm is null
and mgr is not null
and job in ('MANAGER', 'CLERK')
and ename not like '_L%';

 