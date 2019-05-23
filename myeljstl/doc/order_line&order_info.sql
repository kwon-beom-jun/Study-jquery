
-- 주문 기본 테이블 (ORDER_INFO)
-- (PK) 주문번호[ORDER_NO] : NUMBER
-- 주문자ID[ORDER_ID] : 
-- 주문시간[ORDER_DT] : DATE  /  DEFAULT: SYSDATE
drop table order_info;
drop table order_LINE;

commit;


create table ORDER_INFO (
   ORDER_NO NUMBER,
   ORDER_ID VARCHAR2(16),
   ORDER_DT DATE DEFAULT sysdate,
   CONSTRAINT ORDER_INFO_PK PRIMARY KEY(ORDER_NO),
   CONSTRAINT ORDER_ID_FK FOREIGN KEY(ORDER_ID) REFERENCES CUSTOMER(ID)
);

        
select * from order_info;
select * from order_line;

CREATE SEQUENCE ORDER_SEQ
START WITH 0 
INCREMENT BY 1 NOMAXVALUE MINVALUE 0;

drop SEQUENCE order_seq;




-- 주문 기본 테이블 (ORDER_LINE)
-- 주문번호[ORDER_NO] : 
-- 주문상품번호[ORDER_PROD_NO] : 
-- 주문수량[ORDER_QUANTITY] : 

CREATE TABLE ORDER_LINE (
   ORDER_NO NUMBER,
   ORDER_PROD_NO VARCHAR(5),
   ORDER_QUANTITY NUMBER(2),
   CONSTRAINT ORDER_LINE_PK PRIMARY KEY(ORDER_NO, ORDER_PROD_NO),
   CONSTRAINT ORDER_NO_FK FOREIGN KEY(ORDER_NO) REFERENCES ORDER_INFO(ORDER_NO),
   CONSTRAINT ORDER_PROD_NO_FK FOREIGN KEY(ORDER_PROD_NO) REFERENCES PRODUCT(PROD_NO)
);




INSERT INTO ORDER_INFO(ORDER_NO, ORDER_ID)
VALUES (ORDER_SEQ.NEXTVAL, 'test');

-- 시퀀스의 CURRVAL를 사용하려면, 같은 커넥션 안에서 NEXTVAL 후 CURRVAL 사용해야 함
INSERT INTO ORDER_LINE(ORDER_NO, ORDER_PROD_NO, ORDER_QUANTITY)
VALUES (ORDER_SEQ.CURRVAL, '003', 1);   
INSERT INTO ORDER_LINE(ORDER_NO, ORDER_PROD_NO, ORDER_QUANTITY)
VALUES (ORDER_SEQ.CURRVAL, '002', 2);   



-- id 주문내역 보기
select info.order_no, order_dt, prod_no, prod_name, prod_price, order_quantity
from order_info info join order_line line
on info.order_no = line.order_no
join product p
on line.order_prod_no = p.prod_no
where order_id = 'test'
order by order_dt desc, prod_no;

































-- 틀림...
drop table order_info;

create table order_info(
    order_no number,
    order_id varchar2(10),
    order_dt DATE DEFAULT sysdate,
    CONSTRAINT order_no_pk PRIMARY KEY (order_no),
    CONSTRAINT order_id_fk FOREIGN KEY (order_id) REFERENCES customer (id)
);

ALTER TABLE order_info
	ADD
		CONSTRAINT order_no_pk
		PRIMARY KEY (
			order_no
		);

insert into order_info(order_no,order_id) values(1,'뿡뿡뿡');

select * from order_info;

--------------------------------------------------------------------

drop table order_line;

create table order_line(
    order_no number,
    order_prod_no varchar2(5),
    order_quantity number(2),
    CONSTRAINT order_line_pk PRIMARY KEY (order_no, order_prod_no),
    CONSTRAINT order_no_fk FOREIGN KEY (order_no) REFERENCES order_info(order_no),
    CONSTRAINT order_prod_no_fk FOREIGN KEY (order_pord_no) REFERENCES product(prod_no)
);
-- 테이블 레벨 제약 조건.

select * from order_line;

create sequence order_seq; -- 디폴트로 1부터 시작함.

INSERT INTO order_info(order_no, order_id) VALUES (order_seq.NEXTVAL, '뿡뿡뿡'); -- 시퀀스

INSERT INTO oreder_line(order_no, porder_prode_no, order_quantity) VALUES (odrer_seq.CURRVAL, '003', 1); -- 현재 일렬번호를 가져오려면 CURRVAL(NEXTVAL부터 사용해야함.)
INSERT INTO oreder_line(order_no, porder_prode_no, order_quantity) VALUES (odrer_seq.CURRVAL, '002', 2);












