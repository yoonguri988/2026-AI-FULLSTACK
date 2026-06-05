package com.the703.dao;

import java.util.List;

import com.the703.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> selectAll();
	public BoardDto select(int bno);
	public int insert(BoardDto dto);
	public int update(BoardDto dto);
	public int delete(int bno);
	public int updateBhitByBno(int bno);
	public BoardDto selectOneByBpass(BoardDto dto);
}
/*
mysql> desc mvcboard2;
+----------+---------------+------+-----+-------------------+-------------------+
| Field    | Type          | Null | Key | Default           | Extra             |
+----------+---------------+------+-----+-------------------+-------------------+
| bno      | int           | NO   | PRI | NULL              | auto_increment    |
| BNAME    | varchar(200)  | NO   |     | NULL              |                   |
| BPASS    | varchar(50)   | NO   |     | NULL              |                   |
| BTITLE   | varchar(1000) | NO   |     | NULL              |                   |
| BCONTENT | text          | NO   |     | NULL              |                   |
| BDATE    | timestamp     | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| BHIT     | int           | NO   |     | 0                 |                   |
| BIP      | varchar(50)   | NO   |     | NULL              |                   |
+----------+---------------+------+-----+-------------------+-------------------+
8 rows in set (0.00 sec)

(해당 번호의 글 읽기, 글 수정, 삭제)
create  : insert into mvcboard2 (bname, bpass,btitle, bcontent, bip) 
          values (#{bname}, #{bpass}, #{btitle}, #{bcontent}, #{bip})

read#1  : select * from mvcboard2 order by bno desc
read#2  : select * from mvcboard2 where bno = #{bno}

update  : update mvcboard2 set btitle=#{btitle}, bcontent=#{bcontent}, bip=#{bip} where bno = #{bno}

delete  : delete from mvcboard2 where bno = #{bno}
 */