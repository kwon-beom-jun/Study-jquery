
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






