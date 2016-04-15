CREATE TABLE DuckDb.`mail_message` (
  `mail_message_id` int(11) NOT NULL AUTO_INCREMENT,
  `sent_id` int(11) NOT NULL,
  `sent` DATETIME NOT NULL,
  `send_to` TEXT NOT NULL,
  `subject` varchar(45),
  `body` LONGTEXT NOT NULL,
  PRIMARY KEY (`mail_message_id`),
  INDEX(`sent_id`),
  FOREIGN KEY (`sent_id`) REFERENCES DuckDb.`duck_user`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`mail_distribution` (
  `mail_distribution_id` int(11) NOT NULL AUTO_INCREMENT,
  `mail_message_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`mail_distribution_id`),
  INDEX(`mail_message_id`),
  INDEX(`user_id`),
  FOREIGN KEY (`mail_message_id`) REFERENCES DuckDb.`mail_message`(`mail_message_id`),
  FOREIGN KEY (`user_id`) REFERENCES DuckDb.`duck_user`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`mail_box` (
  `mail_box_id` int(11) NOT NULL AUTO_INCREMENT,
  `mail_message_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `folder` varchar(45),
  `is_read` boolean NOT NULL default 0, 
  PRIMARY KEY (`mail_box_id`),
  INDEX(`mail_message_id`),
  INDEX(`user_id`),
  FOREIGN KEY (`mail_message_id`) REFERENCES DuckDb.`mail_message`(`mail_message_id`),
  FOREIGN KEY (`user_id`) REFERENCES DuckDb.`duck_user`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;