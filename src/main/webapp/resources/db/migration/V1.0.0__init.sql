CREATE TABLE PARTY(
  id           INT PRIMARY KEY,
  name         VARCHAR2(100),
  description  VARCHAR2(100),
  date         DATE,
  time         TIME,
  city         VARCHAR2(100),
  address      VARCHAR2(100),
  priv         BOOLEAN,
  image_id     INT,
  organizer_id INT
);

CREATE TABLE IMAGE
(
  id INT PRIMARY KEY,
  name VARCHAR2(80),
);

CREATE TABLE USER
(
  id            INT PRIMARY KEY auto_increment,
  email         VARCHAR2(80),
  hash_password VARCHAR2(500),
  first_name    VARCHAR2(100),
  surname       VARCHAR2(100),
  date_of_birth DATE,
  role_id       INT
);

CREATE TABLE ROLE
(
  id INT PRIMARY KEY,
  name VARCHAR2(50)
);

CREATE TABLE PARTY_USER
(
  party_id INT,
  user_id INT,
  PRIMARY KEY (party_id, user_id)
);
ALTER TABLE PARTY
    ADD FOREIGN KEY (image_id) references IMAGE(id);

ALTER TABLE PARTY
  ADD FOREIGN KEY (organizer_id) references USER (id);

ALTER TABLE USER
    ADD FOREIGN KEY (role_id) references ROLE(id);

ALTER TABLE PARTY_USER
    ADD FOREIGN KEY (party_id) references PARTY(id);

ALTER TABLE PARTY_USER
    ADD FOREIGN KEY (user_id) references USER(id);
