create database DuckDb;
 
CREATE TABLE DuckDb.`duck_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email_address` varchar(45) NOT NULL,
  `address_line1` varchar(45) ,
  `address_line2` varchar(45) ,
  `city` varchar(45) ,
  `state` varchar(2) ,
  `zip_code` varchar(10),	
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `active` boolean NOT NULL default 0,
  `registration_date` DATE NOT NULL,
  `profile_image` varchar(255) ,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`opportunity` (
  `opportunity_id` int(11) NOT NULL AUTO_INCREMENT,
  `opportunity_type` varchar(45) NOT NULL,
  `opportunity_title` varchar(45) NOT NULL,
  `duck_bills` DECIMAL NOT NULL,
  `register_date` DATE NOT NULL,
  `submit_date` DATE NOT NULL,
  `description` TEXT NOT NULL,
  `creator_id` int(11) NOT NULL,
  `payment_method` varchar(45) NOT NULL,
  `opportunity_registered_id` int(11),
  `opportunity_submitted_id` int(11),
  `opportunity_review_id` int(11),
  `winner_id` int(11),
  PRIMARY KEY (`opportunity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`opportunity_registered` (
  `opportunity_registered_id` int(11) NOT NULL AUTO_INCREMENT,
  `opportunity_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `registered_date` DATE NOT NULL,
  PRIMARY KEY (`opportunity_registered_id`),
  INDEX(`user_id`),
  INDEX(`opportunity_id`),
  FOREIGN KEY (`user_id`) REFERENCES DuckDb.`duck_user`(`user_id`),
  FOREIGN KEY (`opportunity_id`) REFERENCES DuckDb.`opportunity`(`opportunity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`opportunity_submitted` (
  `opportunity_submitted_id` int(11) NOT NULL AUTO_INCREMENT,
  `opportunity_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `submission_date` DATE NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `accepted_date` DATE,
  `status` varchar(45),
  `comment` TEXT,
  PRIMARY KEY (`opportunity_submitted_id`),
  INDEX(`user_id`),
  INDEX(`opportunity_id`),
  FOREIGN KEY (`user_id`) REFERENCES DuckDb.`duck_user`(`user_id`),
  FOREIGN KEY (`opportunity_id`) REFERENCES DuckDb.`opportunity`(`opportunity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`opportunity_review_issue` (
  `opportunity_review_issue_id` int(11) NOT NULL AUTO_INCREMENT,
  `opportunity_submitted_id` int(11) NOT NULL,
  `creation_date` DATE NOT NULL,
  `issue_id` int(11),
  `comment` TEXT,
  `resolution_date` DATE,
  PRIMARY KEY (`opportunity_review_issue_id`),
  INDEX(`opportunity_submitted_id`),
  FOREIGN KEY (`opportunity_submitted_id`) REFERENCES DuckDb.`opportunity_submitted`(`opportunity_submitted_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`review_issue` (
  `issue_id` int(11) NOT NULL AUTO_INCREMENT,
  `issue_title` varchar(45) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`issue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`opportunity_time` (
  `opportunity_time_id` int(11) NOT NULL AUTO_INCREMENT,
  `opportunity_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `work_date` DATE NOT NULL,
  `start_time` TIME NOT NULL,
  `end_time` TIME NOT NULL,
  PRIMARY KEY (`opportunity_time_id`),
  INDEX(`user_id`),
  INDEX(`opportunity_id`),
  FOREIGN KEY (`user_id`) REFERENCES DuckDb.`duck_user`(`user_id`),
  FOREIGN KEY (`opportunity_id`) REFERENCES DuckDb.`opportunity`(`opportunity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`mail_message` (
  `mail_message_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
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