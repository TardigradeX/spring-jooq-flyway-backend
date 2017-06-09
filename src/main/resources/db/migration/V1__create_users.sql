CREATE SEQUENCE user_id START WITH 1;

CREATE TABLE users (
  id BIGINT NOT NULL PRIMARY KEY,
  emailid VARCHAR(250) NOT NULL ,
  password_hash VARCHAR(500) NOT NULL,
  activated BOOLEAN DEFAULT false,
  name VARCHAR(500),
  role VARCHAR(10)
);

--ADD ADMIN
INSERT INTO users (id, emailid, password_hash, activated, name, role )
VALUES  (user_id.NEXTVAL, 'admin@test.de', '$2a$10$5RBHY3h10aUhpCSgLFFiQO0Xm5UG9x52A0EhH.PR9stPv5GlzV5/u', TRUE, 'admin', 'ADMIN');

--ADD DEFAULT USER
INSERT INTO users (id, emailid, password_hash, activated, name, role )
VALUES  (user_id.NEXTVAL, 'user@test.de', '$2a$10$5RBHY3h10aUhpCSgLFFiQO0Xm5UG9x52A0EhH.PR9stPv5GlzV5/u', TRUE, 'user', 'USER');
