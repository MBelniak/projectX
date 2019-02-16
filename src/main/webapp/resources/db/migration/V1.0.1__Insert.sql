INSERT INTO ROLE(id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLE(id, name) VALUES (2, 'USER');
INSERT INTO USER(id, email, hash_password, first_name, surname, role_id) values (1, 'belniakm@wp.pl', '$2a$10$jOO4eES08e8iwhIWGVv.C.RXYvvwZdOAtviaaVCTHgjQyecHtVFmO', 'Michal', 'Belniak', 1);
INSERT INTO USER(id, email, hash_password, first_name, surname, role_id) values (2, 'rubik40s@wp.pl', '$2a$10$Z05ke/mQS6oFT/lHUL6Z9.Y2WzUVzg2BAA.KNoV8Tu7nrN21.Xfn.', 'Michal', 'Belniak', 2);