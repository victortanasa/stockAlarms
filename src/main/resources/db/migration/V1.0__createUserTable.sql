CREATE TABLE user
(
  user_id  INT(11) NOT NULL AUTO_INCREMENT,
  name     VARCHAR(255) DEFAULT NULL,
  password VARCHAR(255) DEFAULT NULL,
  email    VARCHAR(255) DEFAULT NULL,
  UNIQUE (email),
  PRIMARY KEY (user_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE alarm
(
  alarm_id            INT(11) NOT NULL AUTO_INCREMENT,
  stock_name          VARCHAR(255) DEFAULT NULL,
  stock_value         DOUBLE       DEFAULT NULL,
  percentage_increase DOUBLE       DEFAULT NULL,
  percentage_decrease DOUBLE       DEFAULT NULL,
  enabled             BOOLEAN      DEFAULT NULL,
  user_id             INT(11) NOT NULL,
  INDEX user_ind (user_id),
  FOREIGN KEY (user_id)
    REFERENCES user (user_id)
    ON DELETE CASCADE,
  PRIMARY KEY (alarm_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;