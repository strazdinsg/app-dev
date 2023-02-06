-- This is the database schema that is used to set up the tables and data
-- The application assumes this structure of the DB
-- Author: Girts Strazdins, 2022-02-08

DROP TABLE IF EXISTS `book_borrower`;
DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `borrowers`;

CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `YEAR` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `borrowers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(200) DEFAULT NULL,
  `last_name` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `book_borrower` (
  `book_id` int(11) NOT NULL,
  `borrower_id` int(11) NOT NULL,
  PRIMARY KEY (`book_id`,`borrower_id`),
  KEY `borrower_id` (`borrower_id`),
  CONSTRAINT `book_borrower_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `book_borrower_ibfk_2` FOREIGN KEY (`borrower_id`) REFERENCES `borrowers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `books` VALUES (1,'12 Rules For Life',2019),(2,'Computer Networks: A Top Down approach',2018),(3,'Learning Web Design',2018);

INSERT INTO `borrowers` VALUES (1,'Chuck','Dorris','Ddd street 16'),(2,'Ada','Lovelace','Long Street 5'),(3,'John','Doe','Dough Street 12'),(4,'Jack','Johnson','JJ 56');

INSERT INTO `book_borrower` VALUES (1,1),(1,3),(2,2),(2,3);
