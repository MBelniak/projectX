INSERT INTO ROLE(id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLE(id, name) VALUES (2, 'USER');
INSERT INTO USER (email, hash_password, first_name, surname, date_of_birth, role_id) values
  ('belniakm@wp.pl', '$2a$10$jOO4eES08e8iwhIWGVv.C.RXYvvwZdOAtviaaVCTHgjQyecHtVFmO', 'Michal', 'Belniak', '1997-07-09',
   1);
INSERT INTO USER (email, hash_password, first_name, surname, date_of_birth, role_id) values
  ('rubik40s@wp.pl', '$2a$10$Z05ke/mQS6oFT/lHUL6Z9.Y2WzUVzg2BAA.KNoV8Tu7nrN21.Xfn.', 'Michal', 'Belniak', '1997-07-09',
   2);