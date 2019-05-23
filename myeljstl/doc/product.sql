
-- 회원정보
drop TABLE customer;


create table customer (
    id varchar2(16),
    pass varchar2(16),
    name varchar2(30),
    address varchar2(100),
    constraint customet_pk PRIMARY KEY(id)
);



commit;

insert into customer(id, pass, name)
values ('test', 'testp', '테스트');

select * from customer;



-- 상품

--** 상품분류(PRODUCT_CATEGORY) TABLE (부모엔터티) **

-- 컬럼
-- (PK)분류번호[CATE_NO] : CHAR(1)
-- (NN)분류명[CATE_NAME] : VARCHAR2(30)

-- DATA
-- D, 음료
-- F, 음식
-- G, 상품


-- ** 상품목록(PRODUCT) TABLE (자식엔터티) **

-- 컬럼
-- (PK)상품번호[PROD_NO] : VARCHAR2(5)
-- (NN)상품명[PROD_NAME]: VARCHAR2(30)
-- (NN, CHK(>=0))가격[PROD_PRICE]: NUMBER(5)
-- 상세정보[PROD_DETAIL]: VARCHAR2(50)
-- (FK,NN)상품분류[PROD_CATE]

-- DATA
--001, 아메리카노, D, 2500, ...
--002, 아이스아메리카노, D, 2500, ...
--003, 라테, D, 3000, ...
--004, 치즈케이크, F, 4000, ...




create table PRODUCT_CATEGORY (
    CATE_NO CHAR(1),
    CATE_NAME VARCHAR2(30) NOT NULL
);


create table PRODUCT (
    PROD_NO VARCHAR2(5),
    PROD_NAME VARCHAR2(30) NOT NULL,
    PROD_PRICE NUMBER(5) NOT NULL CHECK (PROD_PRICE >= 0),
    PROD_DETAIL VARCHAR2(50),
    PROD_CATE NOT NULL
    constraint PRODUCT_PK PRIMARY KEY(PROD_NO)
);


ALTER TABLE PRODUCT
   ADD
      CONSTRAINT FK_PRODUCT_CATEGORY_TO_PRODUCT
      FOREIGN KEY (
         PROD_CATE
      )
      REFERENCES PRODUCT_CATEGORY (
         PROD_CATE
      );

ALTER TABLE PRODUCT
   ADD
      CONSTRAINT PRODUCT_PK
      PRIMARY KEY (
         PROD_NO
      );

ALTER TABLE PRODUCT_CATEGORY
   ADD
      CONSTRAINT PRODUCT_CATEGORY_PK
      PRIMARY KEY (
         CATE_NO
      );


      
INSERT INTO PRODUCT_CATEGORY (CATE_NO, CATE_NAME)
VALUES ('D', '음료');

INSERT INTO PRODUCT_CATEGORY (CATE_NO, CATE_NAME)
VALUES ('F', '음식');

INSERT INTO PRODUCT_CATEGORY (CATE_NO, CATE_NAME)
VALUES ('G', '상품');

INSERT INTO PRODUCT(PROD_NO, PROD_NAME, PROD_PRICE, PROD_DETAIL, PROD_CATE)
VALUES ('001', '아메리카노', 2500, '', 'D');

INSERT INTO PRODUCT(PROD_NO, PROD_NAME, PROD_PRICE, PROD_DETAIL, PROD_CATE)
VALUES ('002', '아이스아메리카노', 2500, '', 'D');

INSERT INTO PRODUCT(PROD_NO, PROD_NAME, PROD_PRICE, PROD_DETAIL, PROD_CATE)
VALUES ('003', '라테', 3000, '', 'D');

INSERT INTO PRODUCT(PROD_NO, PROD_NAME, PROD_PRICE, PROD_DETAIL, PROD_CATE)
VALUES ('004', '치즈케이크', 4000, '', 'F');



-- select *
select cate_no, cate_name, prod_no, prod_name, prod_price, prod_detail
from product p join product_category pc
on p.prod_cate = pc.cate_no
order by cate_no, prod_name;

-- D카테고리에 해당 상품목록
select cate_no, cate_name, pro_co, prod_name, prod_price, prod_detail
from product p join product_category pc
on p.prod_cate = pc.cate_no
where cate_no = 'D'
order by cate_no, prod_name;


-- D카테고리에 해당 총상품가격을 출력하시오
select sum(prod_price)
from product p join product_category pc
on p.prod_cate = pc.cate_no
where cate_no = 'D';


-- 카테고리별 총상품가격을 출력하시오
select cate_no, sum(prod_price)
from product p join product_category pc
on p.prod_cate = pc.cate_no
group by cate_no;
















/*
상품목록(PRODUCT)
* pk             NN            NN                        NN,FK
varchar2(5)   varchar2(30)   number(5)   varchar2(50)   char(1)
prod_no      prod_name     prod_price    prod_detail    prod_cate
상품번호         상품명           가격        상세정보         분류    
001           아메리카노         2500         ....           D      
002          아이스 아메리카노    2500         ....           D       
003            라떼             3000         ....           D      
004            치즈케익          4000         ....           F             
*/



drop table product;

CREATE TABLE PRODUCT (
	prod_no varchar2(5), /* 상품번호 */
	prod_name VARCHAR2(30), /* 상품명 */
	prod_price number(5), /* 가격 */
	prod_detail VARCHAR(50), /* 상세정보 */
	prod_cate char(1) /* 분류 */
);

ALTER TABLE product
	ADD
		CONSTRAINT prod_no_pk
		PRIMARY KEY (
			prod_no
		);

ALTER TABLE PRODUCT
	ADD
		CONSTRAINT pk_PRODUCT_CATEGORY_TO_PRODUCT
		FOREIGN KEY (
			prod_cate
		)
		REFERENCES PRODUCT_CATEGORY (
			cate_no
		);
		
insert into PRODUCT(prod_no, prod_name, prod_price, prod_detail, prod_cate) values('001', '아메리카노', 2500, '없음', 'D');
insert into PRODUCT(prod_no, prod_name, prod_price, prod_detail, prod_cate) values('002', '아이스 아메리카노', 2500, '없음', 'D');
insert into PRODUCT(prod_no, prod_name, prod_price, prod_detail, prod_cate) values('003', '라떼', 3000, '없음', 'D');
insert into PRODUCT(prod_no, prod_name, prod_price, prod_detail, prod_cate) values('004', '치즈케익', 4000, '없음', 'F');


select * from PRODUCT;


/*
상품분류(대 다의 관계 부모)(PRODUCT_CATEGORY)
* pk            NN
char(1)       varchar2(30)
cate_no         cate_name
분류번호         분류명    
D               음료
F               음식
G               상품
*/

CREATE TABLE PRODUCT_CATEGORY (
	cate_no char(1), /* 분류번호 */
	cate_name varchar2(30) /* 분류명 */
);

ALTER TABLE PRODUCT_CATEGORY
	ADD
		CONSTRAINT cate_no_pk
		PRIMARY KEY (
			cate_no
		);


insert into PRODUCT_CATEGORY(cate_no, cate_name) values('D', '음료');
insert into PRODUCT_CATEGORY(cate_no, cate_name) values('F', '음식');
insert into PRODUCT_CATEGORY(cate_no, cate_name) values('G', '상품');

select * from PRODUCT_CATEGORY;

commit;






