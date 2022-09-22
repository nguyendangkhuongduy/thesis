/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `council`
--

DROP TABLE IF EXISTS `council`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `council` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `faculty_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4n23y3scwyiw5uyd4217eedyr` (`faculty_id`),
  CONSTRAINT `FK4n23y3scwyiw5uyd4217eedyr` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `council`
--

LOCK TABLES `council` WRITE;
/*!40000 ALTER TABLE `council` DISABLE KEYS */;
/*!40000 ALTER TABLE `council` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `council_position`
--

DROP TABLE IF EXISTS `council_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `council_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `council_id` int(11) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5w8roggbnwgf1pj6ncj72ksqw` (`council_id`),
  KEY `FK9f2gievhpif0dtb72ieqivvmm` (`position_id`),
  KEY `FK7mhpdg1yvrb52cxgb0qjof6w1` (`user_id`),
  CONSTRAINT `FK5w8roggbnwgf1pj6ncj72ksqw` FOREIGN KEY (`council_id`) REFERENCES `council` (`id`),
  CONSTRAINT `FK7mhpdg1yvrb52cxgb0qjof6w1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK9f2gievhpif0dtb72ieqivvmm` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `council_position`
--

LOCK TABLES `council_position` WRITE;
/*!40000 ALTER TABLE `council_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `council_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_for_thesis`
--

DROP TABLE IF EXISTS `position_for_thesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_for_thesis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_for_thesis`
--

LOCK TABLES `position_for_thesis` WRITE;
/*!40000 ALTER TABLE `position_for_thesis` DISABLE KEYS */;
/*!40000 ALTER TABLE `position_for_thesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oeej9` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `council_position_id` int(11) DEFAULT NULL,
  `thesis_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4cvjecp9orjr3w02a2bqxc8pj` (`council_position_id`),
  KEY `FKf5hrea7x5o90im57586kcwq8w` (`thesis_id`),
  CONSTRAINT `FK4cvjecp9orjr3w02a2bqxc8pj` FOREIGN KEY (`council_position_id`) REFERENCES `council_position` (`id`),
  CONSTRAINT `FKf5hrea7x5o90im57586kcwq8w` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_detail`
--

DROP TABLE IF EXISTS `score_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score1` float NOT NULL,
  `score2` float NOT NULL,
  `score3` float NOT NULL,
  `score4` float NOT NULL,
  `score_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1uqvdwjgbgcj341ghe1hcesj0` (`score_id`),
  CONSTRAINT `FK1uqvdwjgbgcj341ghe1hcesj0` FOREIGN KEY (`score_id`) REFERENCES `score` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_detail`
--

LOCK TABLES `score_detail` WRITE;
/*!40000 ALTER TABLE `score_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `score_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis`
--

DROP TABLE IF EXISTS `thesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thesis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `total_score` float DEFAULT NULL,
  `council_id` int(11) DEFAULT NULL,
  `faculty_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ud1l4x0qcakqj7y2ypkr1ojc` (`council_id`),
  KEY `FKlffobvdiuo5s0s13d8g7rh8j7` (`faculty_id`),
  CONSTRAINT `FK2ud1l4x0qcakqj7y2ypkr1ojc` FOREIGN KEY (`council_id`) REFERENCES `council` (`id`),
  CONSTRAINT `FKlffobvdiuo5s0s13d8g7rh8j7` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis`
--

LOCK TABLES `thesis` WRITE;
/*!40000 ALTER TABLE `thesis` DISABLE KEYS */;
/*!40000 ALTER TABLE `thesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis_position`
--

DROP TABLE IF EXISTS `thesis_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thesis_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thesis_id` int(11) DEFAULT NULL,
  `thesis_position` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9gdp6andfvseq2bb9l545admk` (`thesis_id`),
  KEY `FK9tj0y8ctg7ludgxrxtwj55x33` (`thesis_position`),
  KEY `FK80r0y1ucartrc3eec82kdj2i5` (`user_id`),
  CONSTRAINT `FK80r0y1ucartrc3eec82kdj2i5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK9gdp6andfvseq2bb9l545admk` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`),
  CONSTRAINT `FK9tj0y8ctg7ludgxrxtwj55x33` FOREIGN KEY (`thesis_position`) REFERENCES `position_for_thesis` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis_position`
--

LOCK TABLES `thesis_position` WRITE;
/*!40000 ALTER TABLE `thesis_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `thesis_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  KEY `FKdbv8tdyltxa1qjmfnj9oboxse` (`roles_id`),
  CONSTRAINT `FKdbv8tdyltxa1qjmfnj9oboxse` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `faculty_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FK8qkbqvhy7o4vqv850rirhn5hg` (`faculty_id`),
  CONSTRAINT `FK8qkbqvhy7o4vqv850rirhn5hg` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


-- initial data insert into db by default

INSERT INTO roles (id, name) VALUES (1, "ROLE_ADMIN");
INSERT INTO roles (id, name) VALUES (2, "ROLE_MANAGER");
INSERT INTO roles (id, name) VALUES (3, "ROLE_ASSOCIATE");

INSERT INTO faculty (id, name) VALUES (1, "CNTT");
INSERT INTO faculty (id, name) VALUES (2, "Kế Toán");
INSERT INTO faculty (id, name) VALUES (3, "Nam Á");


INSERT INTO council (id, active, created_date, name, faculty_id)
VALUES (1, true, "2022-02-01", "khanhtran", "1");
INSERT INTO council (id, active, created_date, name, faculty_id)
VALUES (2, true, "2022-02-01", "khanhtran1", "2");
INSERT INTO council (id, active, created_date, name, faculty_id)
VALUES (3, true, "2022-02-01", "khanhtran2", "3");


INSERT INTO users (id, username, email, active, password, full_name, phone, gender, created_date)
VALUES (1, "admin", "khanh.tran@test.com", true, "$2a$10$1/XHX1tbJP4vJ/0VSRcS.e/HtBTtDTxCgITVHoEHbR3TTJjPNgVoO", "khanh", "03333333", "male", "2022-02-01");

INSERT INTO users (id, username, email, active, password, full_name, phone, gender, created_date, faculty_id)
VALUES (2, "manager", "khanh.tran@test1.com", true, "$2a$10$1/XHX1tbJP4vJ/0VSRcS.e/HtBTtDTxCgITVHoEHbR3TTJjPNgVoO", "khanh1", "0333333311", "male", "2022-02-01", "1");

INSERT INTO users (id, username, email, active, password, full_name, phone, gender, created_date, faculty_id)
VALUES (3, "associate", "khanh.tran@test2.com", true, "$2a$10$1/XHX1tbJP4vJ/0VSRcS.e/HtBTtDTxCgITVHoEHbR3TTJjPNgVoO", "khanh2", "033333331122", "male", "2022-02-01", "2");

INSERT INTO user_roles (user_id, roles_id) VALUES (1,1);
INSERT INTO user_roles (user_id, roles_id) VALUES (2,2);
INSERT INTO user_roles (user_id, roles_id) VALUES (3,3);

INSERT INTO thesis (id, active, created_date, name, total_score, council_id, faculty_id)
VALUES (1, true, "2022-02-01", "duy", "9", "1", "1");
INSERT INTO thesis (id, active, created_date, name, total_score, council_id, faculty_id)
VALUES (2, true, "2022-02-01", "duy1", "9", "2", "2");
INSERT INTO thesis (id, active, created_date, name, total_score, council_id, faculty_id)
VALUES (3, true, "2022-02-01", "duy2", "9", "3", "3");

