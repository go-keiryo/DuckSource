create database DuckDb;

grant all on DuckDb.* to 'admin'@'localhost' identified by 'susan';  
 
CREATE TABLE DuckDb.`duck_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email_address` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE DuckDb.`opportunity` (
  `opportunity_id` int(11) NOT NULL AUTO_INCREMENT,
  `opportunity_type` varchar(45) NOT NULL,
  `opportunity_title` varchar(45) NOT NULL,
  `duck_bills` DECIMAL NOT NULL,
  `register_date` DATE NOT NULL,
  `submit_date` DATE NOT NULL,
  `description` varchar(255) NOT NULL,
  `creator_id` int(11),
  PRIMARY KEY (`opportunity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;