create table mbasic.userinfo_e
select * from userinfo;

alter table userinfo_e modify 
no int primary key auto_increment;

desc userinfo_e;

 alter table mvcboard2 modify bno int primary key auto_increment;
 
 select * from mvcboard2 order by bno desc;
 
 desc mvcboard2;
 
 
 alter table mvcboard2