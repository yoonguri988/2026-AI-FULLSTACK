-- ★ 다음과 같이 테이블을 준비해주세요!
-- mysql> desc milk_order;
-- +-------+--------------+------+-----+-------------------+-------------------+
-- | Field | Type         | Null | Key | Default           | Extra             |
-- +-------+--------------+------+-----+-------------------+-------------------+
-- | ono   | int          | NO   | PRI | NULL              | auto_increment    |
-- | oname | varchar(20)  | NO   |     | NULL              |                   |
-- | onum  | int          | NO   |     | NULL              |                   |
-- | odate | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
-- | oip   | varchar(100) | NO   |     | NULL              |                   |
-- +-------+--------------+------+-----+-------------------+-------------------+
-- 5 rows in set (0.00 sec)
create table milk_order (
  ono int not null primary key auto_increment,
  oname varchar(20)  not null,
  onum int  not null,
  odate datetime default CURRENT_TIMESTAMP,
  oip varchar(100) not null
);

desc milk_order;

-- Q1.  milk_order 값삽입.  insert 구문 완성    (oname, onum, oip)     'white' , 2,  '127.0.0.1'
insert into milk_order (oname, onum, oip) values ( 'white', 2, '127.0.0.1');
-- Q2.  milk_order ono가 1인데이터 조회 
select * from milk_order where ono = 1;
-- Q3.  milk_order 전체데이터조회
select * from milk_order;
-- Q4.  milk_order 해당번호의 이름과 갯수 수정
update milk_order
set oname = ?, onum=?
where ono = ?
-- Q5.  milk_order 해당번호의 데이터 삭제
delete from milk_order where ono = ?;