insert into users (id, username, password, mobileNo, mobileCode, enabled, loginTime, createTime, updateTime) values (1, 'admin', '$2a$10$vdofqsql8857rag.ew5ieexvio6mpatab1m/smiz6ffhpnceprusq', '15262689012', '123456', 1, '2019-10-14t21:32:29', '2019-10-14t21:32:29', '2019-10-14t21:32:29');
insert into users (id, username, password, mobileNo, mobileCode, enabled, loginTime, createTime, updateTime) values (2, 'ethan', '$2a$10$vdofqsql8857rag.ew5ieexvio6mpatab1m/smiz6ffhpnceprusq', '15262689012', '123456', 1, '2019-10-14t21:32:29', '2019-10-14t21:32:29', '2019-10-14t21:32:29');
insert into users (id, username, password, mobileNo, mobileCode, enabled, loginTime, createTime, updateTime) values (3, 'disabled', '$2a$10$vdofqsql8857rag.ew5ieexvio6mpatab1m/smiz6ffhpnceprusq', '15262689012', '123456', 1, '2019-10-14t21:32:29', '2019-10-14t21:32:29', '2019-10-14t21:32:29');

INSERT INTO AUTHORITYS (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITYS (ID, NAME) VALUES (2, 'ROLE_ADMIN');
INSERT INTO AUTHORITYS (ID, NAME) VALUES (3, 'ROLE_OPERATOR');

INSERT INTO USER_AUTHORITYS (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITYS (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITYS (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITYS (USER_ID, AUTHORITY_ID) VALUES (3, 1);
