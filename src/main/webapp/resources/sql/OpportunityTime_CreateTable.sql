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
