SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id                    INT(11) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  PRIMARY KEY (id)
) AUTO_INCREMENT=1;

DROP TABLE IF EXISTS cars;

CREATE TABLE cars (
  id	                INT(11) NOT NULL AUTO_INCREMENT,
  user_id               INT(11),
  number                VARCHAR(50),
  brand                 VARCHAR(50),
  PRIMARY KEY (id),
  CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
  REFERENCES users (id)
);

SET FOREIGN_KEY_CHECKS = 1;