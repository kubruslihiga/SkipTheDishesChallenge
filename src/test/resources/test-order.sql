insert into std_order (id, code, status, create_user_id, created_time) values (1, 'COD_ORDER_1', 'CREATED', 1, PARSEDATETIME('12/03/2018', 'dd/MM/yyyy'));
insert into std_order (id, code, status, create_user_id, created_time) values (2, 'COD_ORDER_2', 'CANCELED', 2, PARSEDATETIME('12/03/2018', 'dd/MM/yyyy'));
insert into std_order (id, code, status, create_user_id, created_time) values (3, 'COD_ORDER_3', 'PAID', 3, PARSEDATETIME('12/03/2018', 'dd/MM/yyyy'));
insert into std_order (id, code, status, create_user_id, created_time) values (4, 'COD_ORDER_4', 'NOT_PAID', 4, PARSEDATETIME('12/03/2018', 'dd/MM/yyyy'));
insert into std_order (id, code, status, create_user_id, created_time) values (5, 'COD_ORDER_5', 'REVERTED', 5, PARSEDATETIME('12/03/2018', 'dd/MM/yyyy'));

insert into std_order_product (order_id, product_id) values (1, 1);
insert into std_order_product (order_id, product_id) values (2, 2);
insert into std_order_product (order_id, product_id) values (3, 3);
insert into std_order_product (order_id, product_id) values (4, 4);
insert into std_order_product (order_id, product_id) values (5, 5);