create database DuckDb;

grant all on DuckDb.* to 'admin'@'localhost' identified by 'susan';  
 
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
  PRIMARY KEY (`opportunity_registered_id`)
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
  PRIMARY KEY (`opportunity_submitted_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`opportunity_review` (
  `opportunity_review_id` int(11) NOT NULL AUTO_INCREMENT,
  `opportunity_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `opportunity_review_issue_id` int(11),
  PRIMARY KEY (`opportunity_review_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`opportunity_review_issue` (
  `opportunity_review_issue_id` int(11) NOT NULL AUTO_INCREMENT,
  `opportunity_review_id` int(11) NOT NULL,
  `creation_date` DATE NOT NULL,
  `issue_id` int(11),
  `comment` TEXT,
  `resolution_date` DATE,
  PRIMARY KEY (`opportunity_review_issue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`review_issue` (
  `issue_id` int(11) NOT NULL AUTO_INCREMENT,
  `issue_title` varchar(45) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`issue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;