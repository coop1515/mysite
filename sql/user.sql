-- UserRepository
desc user;

insert into user values (null, '관리자','admin@mysite.com','1234','male',now());

-- delete from user where no = 3;

select * from user;

-- login
select no, name from user where email = 'coop1515@naver.com' and password = '1234';