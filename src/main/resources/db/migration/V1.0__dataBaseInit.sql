CREATE TABLE user
(
  user_id    INT(11) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) DEFAULT NULL,
  last_name  VARCHAR(255) DEFAULT NULL,
  password   VARCHAR(255) DEFAULT NULL,
  email      VARCHAR(255) DEFAULT NULL,
  UNIQUE (email),
  PRIMARY KEY (user_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE stock
(
  stock_id INT(11) NOT NULL AUTO_INCREMENT,
  name     VARCHAR(255) DEFAULT NULL,
  price    DOUBLE       DEFAULT NULL,
  PRIMARY KEY (stock_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE alarm
(
  alarm_id             INT(11) NOT NULL AUTO_INCREMENT,
  base_stock_price     DOUBLE       DEFAULT NULL,
  percentage_threshold DOUBLE       DEFAULT NULL,
  alarm_type           VARCHAR(255) DEFAULT NULL,
  enabled              BOOLEAN      DEFAULT NULL,
  user_id              INT(11) NOT NULL,
  stock_id             INT(11) NOT NULL,
  INDEX user_ind (user_id),
  FOREIGN KEY (user_id)
    REFERENCES user (user_id)
    ON DELETE CASCADE,
  INDEX stock_ind (stock_id),
  FOREIGN KEY (stock_id)
    REFERENCES stock (stock_id)
    ON DELETE CASCADE,
  PRIMARY KEY (alarm_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;