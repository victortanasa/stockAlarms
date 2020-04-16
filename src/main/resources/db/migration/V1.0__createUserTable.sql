CREATE TABLE `user`
(
  `id`       int(11) NOT NULL AUTO_INCREMENT,
  `name`     varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email`    varchar(255) DEFAULT NULL,
  UNIQUE (email),
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
