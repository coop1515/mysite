-- board

desc board;

select * from board;

select * from board a, user b 
where a.user_no = b.no
order by g_no desc , o_no asc;

insert into board values (null,'하하하','안녕하세요.','0',now(),(select max(g_no) from board a)+1,'1','1','2');

select contents from board where no = 1;

select max(g_no)+1 from board;

delete from board where no = 2 ;

select no, title, contents, user_no from board where no = 6;

select * from board a, user b where a.user_no=b.no and a.no = 6;

select a.no, a.title, a.contents, a.user_no, b.no from board a, user b where a.user_no = b.no and a.no = 6;

update board set title = '헤헤', contents = '잘가' where no = 6;