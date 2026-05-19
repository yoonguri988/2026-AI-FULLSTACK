desc milk;

alter table milk modify mnum int null;
alter table milk modify mtotal int null;

select * from milk;

update milk set mnum = null, mtotal = null where mno = 1;
update milk set mnum = null, mtotal = null where mno = 2;
update milk set mnum = null, mtotal = null where mno = 3;