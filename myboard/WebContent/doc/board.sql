DROP TABLE repboard;

/*답변형 게시판*/
CREATE TABLE repboard (
 BOARD_SEQ              NUMBER(10)  primary key, 
 PARENT_SEQ             NUMBER(10),  /** 원글인 경우:0, 답글인 경우: 부모 원글 번호 **/
 BOARD_SUBJECT        VARCHAR2(255),
 BOARD_WRITER          VARCHAR2(100),
 BOARD_CONTENTS     VARCHAR2(4000),
 BOARD_DATE            TIMESTAMP(6),
 BOARD_PASSWORD    VARCHAR2(20),
 BOARD_VIEWCOUNT    NUMBER(6)
);

DELETE FROM repboard;
COMMIT;

insert into repboard values(1, 0, '1', '1', '1', systimestamp, '1', 0); 

insert into repboard values(2, 0, '2', '2', '2', systimestamp, '2', 0);

insert into repboard values(3, 1, '1-r1', '1-r1', '1-r1', systimestamp, '1-r1', 0);

insert into repboard values(4, 2, '2-r1', '2-r1', '2-r1', systimestamp, '2-r1', 0);

insert into repboard values(5, 0, '3', '3', '3', systimestamp, '3', 0);

insert into repboard values(6, 4, '2-r1-r1', '2-r1-r1', '2-r1-r1', systimestamp, '2-r1-r1', 0);

insert into repboard values(7, 2, '2-r2', '2-r2', '2-r2', systimestamp, '2-r2', 0);

insert into repboard values(8, 1, '1-r2', '1-r2', '1-r2', systimestamp, '1-r2', 0);
COMMIT;

select * from repboard;

desc repboard;


/*
실행되는 순서
                                  rownum 발급.
|------------ selection -------------||---prejentation---|
from -> where -> group by -> having -> select -> order by
*/




/*오라클 의사컬럼(pseudo column) - rownum : 행번호.*/
select rownum, board_seq, board_date
from repboard
order by board_date desc;

/*이 서브쿼리를 인라인 뷰라고 함.*/
select rownum, board_seq, to_char(board_date, 'hh:mm:ss')
from (select board_seq, board_date
    from repboard
    order by board_date desc);

/*1~4 행 추출.*/    
select rownum, board_seq, to_char(board_date, 'hh:mm:ss')
from (select board_seq, board_date
    from repboard
    order by board_date desc)
where rownum between 1 and 4;

/*5~8 행 추출 못하는 이유는 로우넘이 1부터 시작. 위에는 1부터 시작해서 1식 로우가 증가하므로 4까지 찾아냄.*/
select rownum, board_seq, to_char(board_date, 'hh:mm:ss')
from (select board_seq, board_date
    from repboard
    order by board_date desc)
where rownum between 5 and 8;


/*5~8 행 추출.*/
select *
from (select rownum r, board_seq, to_char(board_date, 'hh:mm:ss')
     from (select board_seq, board_date
     from repboard
     order by board_date desc)) -- 1~8까지 발급 완료함.
where r between 5 and 8;




/*원글부터 찾음.*/
select board_seq, parent_seq, to_char(board_date, 'hh:mm:ss')
from repboard
/*오라클만 사용 가능 어디서 부터 시작할지.*/
start with parent_seq = 0
/*원글이 가지고있는 board-seq를 찾아라.*/
connect by prior board_seq = parent_seq
/*같은 레벨에서 정렬을 해줌.*/
order siblings by board_seq desc;

/*
board_seq / parent_seq
   5     -     0
   2     -     0
   7     -     2
   4     -     2
   6     -     4
   1     -     0
   8     -     1
*/



/*1~4 행 추출.*/
select rownum, board_seq, parent_seq, to_char(board_date, 'hh:mm:ss')
from (
    select board_seq, parent_seq, board_date
    from repboard
    start with parent_seq = 0
    connect by prior board_seq = parent_seq
    order siblings by board_seq desc)
where rownum between 1 and 4;




/*5~8 행 추출. 틀림!!*/
select *
from (select rownum r, board_seq, parent_seq, to_char(board_date, 'hh:mm:ss')
        from (
        select board_seq, parent_seq, to_char(board_date, 'hh:mm:ss')
        from repboard
        start with parent_seq = 0
        connect by prior board_seq = parent_seq
        order siblings by board_seq desc)) -- 1~8까지 발급 완료함.
where r between 5 and 8;

/*5~8 행 추출.*/
select *
    from(select rownum r, board_seq, parent_seq, to_char(board_date, 'hh:mm:ss')
        from repboard
        start with parent_seq = 0
        connect by prior board_seq = parent_seq
        order SIBLINGS by board_seq DESC)
where r between 5 and 8;

create SEQUENCE board_seq START WITH 9;

commit;








