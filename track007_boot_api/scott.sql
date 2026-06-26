--SQL> desc sboard2;
-- 이름                                      널?      유형
-- ----------------------------------------- -------- ----------------------------
-- ID                                        NOT NULL NUMBER
-- APP_USER_ID                               NOT NULL NUMBER
-- BTITLE                                    NOT NULL VARCHAR2(1000)
-- BCONTENT                                  NOT NULL CLOB
-- BPASS                                     NOT NULL VARCHAR2(255)
-- BFILE                                              VARCHAR2(255)
-- BHIT                                               NUMBER
-- BIP                                       NOT NULL VARCHAR2(255)
-- CREATED_AT                                         DATE

drop table sboard2;
create table sboard2 (
  ID number not null primary key,
  APP_USER_ID number not null,
  BTITLE varchar2(1000) not null,
  BCONTENT clob not null,
  BPASS varchar2(255) not null,
  BFILE varchar2(255) default 'the703.png',
  BHIT number default 0,
  BIP varchar2(255) not null,
  CREATED_AT date default sysdate
);

create sequence sboard2_seq
start with 1 
increment by 1
nocache
nocycle
;


--1) crud
-- * 데이터 삽입
  insert into  sboard2 ( ID                          ,  APP_USER_ID ,  BTITLE  ,  BCONTENT  ,  BPASS  ,  BFILE  ,  BIP  )
  values               ( sboard2_seq.nextval   ,  1001    ,  'title'   , 'bcontent'  ,   '1111' ,   '1.png' ,   '127.0.0.1'   );

--* 전체select  ( 페이징 )
  select *  from  sboard2  order by id   desc;

  -- mysql ( oracle에서는 동작안함)
  select *  from  sboard2  order by id   desc 
  OFFSET 0 ROWS         --  OFFSET 0 ROWS: 건너뛸 행의 개수 (0부터 시작)
  FETCH NEXT 10 ROWS ONLY;  -- FETCH NEXT 10 ROWS ONLY: 가져올 행의 개수
  
  
--  OFFSET 0 ROWS: 건너뛸 행의 개수 (0부터 시작)
--  FETCH NEXT 10 ROWS ONLY: 가져올 행의 개수
--  최신글부터 10개   0, 10   /  10,10  / 20, 10
  
  select count(*) from sboard2;
  
-- oracle 11g 이하
select * from(
    select row_number() over(order by id desc)  as rnum, 
    id, app_user_id, btitle, bcontent, bpass, bfile, bip, bhit, created_at
    from  sboard2
) A  
where  A.rnum  between  0  and 10;
  
--* 해당번호의 select
  select *  from  sboard2    where  id=1;
  
--* 해당번호 조회수 올리기
  update  sboard2   set   bhit = bhit + 1  where  id=1;

--* 해당번호 업데이트
  update  sboard2  set  btitle='new' , bcontent='new' , bfile='2.png'  where  id=1;
  
--* 해당번호 삭제
  delete  from  sboard2   where  id=1;
  
  
  select * from sboard2;
  