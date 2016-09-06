INSERT INTO flow_user (id, username, name, email, password, activated, created_date) VALUES (0, 'admin', 'admin',  'admin@mail.me', 'b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1', true, now());
INSERT INTO flow_user (id, username, name, email, password, activated, created_date) VALUES (1, 'user', 'user', 'user@mail.me', 'd6dfa9ff45e03b161e7f680f35d90d5ef51d243c2a8285aa7e11247bc2c92acde0c2bb626b1fac74', true, now());

INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (username,authority) VALUES (0, 'ROLE_USER', 'admin');
INSERT INTO user_authority (username,authority) VALUES (1, 'ROLE_USER', 'user');
INSERT INTO user_authority (username,authority) VALUES (0, 'ROLE_ADMIN', 'admin');