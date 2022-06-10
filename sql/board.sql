-- board

desc board;

select * from board;

select * from board a, user b 
where a.user_no = b.no
order by g_no desc , o_no asc;

select * from board a, user b 
where a.user_no = b.no
order by g_no desc , o_no asc limit 1, 5 ;

insert into board values (null,'하하하','안녕하세요.','0',now(),(select max(g_no) from board a)+1,'1','1','2');

select contents from board where no = 1;

select max(g_no)+1 from board;

delete from board where no = 2 ;

select no, title, contents, user_no, g_no from board where no = 1;

select * from board a, user b where a.user_no=b.no and a.no = 6;

select a.no, a.title, a.contents, a.user_no, b.no from board a, user b where a.user_no = b.no and a.no = 6;

update board set hit = hit+1 where no = 6;

select * from board where g_no = 6;	

select no, title, contents, user_no, g_no, o_no, depth from board where no = 31;

update board set o_no= o_no+1 where g_no = 31 and depth != 1;

select * from board where concat(title,contents) like '%''뭐''%';

select * from board a, user b 
where a.user_no = b.no
	and concat(title,contents) like concat('%','뭐','%')
order by g_no desc , o_no asc;
select * from board where contents like '뭐''%';

select count(*) as totalpage, (count(*)/5) as pagecount  from board;
