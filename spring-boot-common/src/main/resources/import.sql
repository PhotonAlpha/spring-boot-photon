-- insert into fc_users (id, username, password, mobile_no, mobile_code, enabled, login_time, create_time, update_time) values (1, 'admin', '$2a$10$vdofqsql8857rag.ew5ieexvio6mpatab1m/smiz6ffhpnceprusq', '15262689010', '123456', 1, '2019-10-14 21:32:29', '2019-10-14 21:32:29', '2019-10-14 21:32:29');
-- insert into fc_users (id, username, password, mobile_no, mobile_code, enabled, login_time, create_time, update_time) values (2, 'ethan', '$2a$10$vdofqsql8857rag.ew5ieexvio6mpatab1m/smiz6ffhpnceprusq', '15262689011', '123456', 1, '2019-10-14 21:32:29', '2019-10-14 21:32:29', '2019-10-14 21:32:29');
-- insert into fc_users (id, username, password, mobile_no, mobile_code, enabled, login_time, create_time, update_time) values (3, 'disabled', '$2a$10$vdofqsql8857rag.ew5ieexvio6mpatab1m/smiz6ffhpnceprusq', '15262689012', '123456', 1, '2019-10-14 21:32:29', '2019-10-14 21:32:29', '2019-10-14 21:32:29');

INSERT INTO fc_authoritys (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO fc_authoritys (ID, NAME) VALUES (2, 'ROLE_ADMIN');
INSERT INTO fc_authoritys (ID, NAME) VALUES (3, 'ROLE_OPERATOR');

-- INSERT INTO fc_user_authoritys (USER_ID, AUTHORITY_ID) VALUES (1, 1);
-- INSERT INTO fc_user_authoritys (USER_ID, AUTHORITY_ID) VALUES (1, 2);
-- INSERT INTO fc_user_authoritys (USER_ID, AUTHORITY_ID) VALUES (2, 1);
-- INSERT INTO fc_user_authoritys (USER_ID, AUTHORITY_ID) VALUES (3, 1);
