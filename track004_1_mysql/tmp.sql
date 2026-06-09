create table mbasic.userinfo_e
select * from userinfo;

alter table userinfo_e modify 
no int primary key auto_increment;

desc userinfo_e;

 alter table mvcboard2 modify bno int primary key auto_increment;
 
 select * from mvcboard2 order by bno desc;
 
 desc mvcboard2;
 
 

 
 alter table mvcboard2 change column BFILEPATH BFILE varchar(500) default 'the703.png';

alter table mvcboard2 add bfile varchar(500) default 'the703.png';

select * from users;



create table authorities (
 email varchar(50) not null,
 auth varchar(50) not null
);

insert authorities (email, auth) values ('first@gmail.com','ROLE_ADMIN');


select u.email, u.bpass, a.auth
from users u
join authorities a
using (email);

select u.email, u.bpass, a.auth
from users u
left join authorities a
on u.email = a.email;

alter table users   modify  bpass varchar(500) not null;