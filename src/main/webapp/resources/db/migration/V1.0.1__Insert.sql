INSERT INTO ROLE(id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLE(id, name) VALUES (2, 'USER');
INSERT INTO USER(id, email, hash_password, first_name, surname, role_id) values (1, 'belniakm@wp.pl', '1234', 'Michal', 'Belniak', 1);
INSERT INTO USER(id, email, hash_password, first_name, surname, role_id) values (2, 'rubik40s@wp.pl', '1234', 'Michal', 'Belniak', 2);