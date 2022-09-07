-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: thesisdata
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `council` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `active` int NOT NULL,
  `faculty_id` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `faculty_id_in_council_idx` (`faculty_id`),
  CONSTRAINT `faculty_id_in_council` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `council`
--

LOCK TABLES `council` WRITE;
/*!40000 ALTER TABLE `council` DISABLE KEYS */;
INSERT INTO `council` VALUES (1,'hội đồng 1',0,1,'2021-11-28'),(2,'hội đồng 2',0,2,'2021-12-29'),(3,'hội đồng 3',0,6,'2022-01-01'),(4,'hội đồng 4',1,7,'2022-03-09'),(5,'hội đồng 5',1,2,'2022-06-17'),(6,'hội đồng 6',1,6,'2022-08-10'),(7,'hội đồng 7',1,1,'2022-07-29'),(8,'hội đồng 8',1,1,'2022-08-16'),(9,'hội đồng 9',0,11,'2022-04-04'),(10,'hội đồng 10',0,18,'2022-03-01'),(11,'hội đồng 11',0,16,'2022-06-21'),(12,'hội đồng 12',1,13,'2022-08-09'),(13,'hội đồng 13',0,8,'2022-07-07'),(14,'hội đồng 14',1,9,'2022-08-31'),(15,'hội đồng 15',1,11,'2022-09-04'),(16,'hội đồng 16',1,6,'2022-09-04'),(17,'hội đồng 17',0,17,'2022-05-05'),(18,'hội đồng 18',0,3,'2022-03-22'),(19,'hội đồng 19',1,5,'2022-09-06'),(20,'hội đồng 20',1,10,'2022-08-01');
/*!40000 ALTER TABLE `council` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `council_position`
--

DROP TABLE IF EXISTS `council_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `council_position` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `council_id` int NOT NULL,
  `position_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_in _cp_idx` (`user_id`),
  KEY `council_id_in_cp_idx` (`council_id`),
  KEY `position_id_in_cp_idx` (`position_id`),
  CONSTRAINT `council_id_in_cp` FOREIGN KEY (`council_id`) REFERENCES `council` (`id`),
  CONSTRAINT `position_id_in_cp` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `user_id_in _cp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `council_position`
--

LOCK TABLES `council_position` WRITE;
/*!40000 ALTER TABLE `council_position` DISABLE KEYS */;
INSERT INTO `council_position` VALUES (1,12,1,1),(2,26,1,2),(3,27,1,3),(4,28,1,4),(5,11,2,1),(6,22,2,2),(7,23,2,3),(8,13,3,1),(9,30,3,2),(10,31,3,3),(11,32,3,4),(12,33,3,5),(13,14,4,1),(14,48,4,2),(15,49,4,3),(16,11,5,1),(17,22,5,2),(18,23,5,3),(19,24,5,4),(20,13,6,1),(21,30,6,2),(22,31,6,3),(24,26,7,2),(25,27,7,3),(26,28,7,4),(27,29,7,5),(28,29,8,1),(29,28,8,2),(30,27,8,3),(31,3,20,1);
/*!40000 ALTER TABLE `council_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Công nghệ thông tin'),(2,'Ngoại ngữ'),(3,'Kinh tế và quản lý công'),(4,'Xã hội học'),(5,'Kế toán Kiểm toán'),(6,'Luật'),(7,'Công nghệ sinh học'),(8,'Văn hóa và du lịch'),(9,'Tài chính ngân hàng'),(10,'Quản trị kinh doanh'),(11,'Xây dựng'),(12,'Đào tạo đặc biệt'),(13,'Điện tử Viễn thông'),(14,'Kỹ thuật và Quản lý Công nghiệp'),(15,'Khoa học môi trường'),(16,'Lao động và công đoàn'),(17,'Khoa học thể thao'),(18,'Báo chí và truyền thông');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Chủ tịch'),(2,'Thưc ký'),(3,'Giáo viên phản biện'),(4,'Thành viên 1(nếu có)'),(5,'Thành viên 2(nếu có)');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `positionforthesis`
--

DROP TABLE IF EXISTS `positionforthesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `positionforthesis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `positionforthesis`
--

LOCK TABLES `positionforthesis` WRITE;
/*!40000 ALTER TABLE `positionforthesis` DISABLE KEYS */;
INSERT INTO `positionforthesis` VALUES (1,'giáo viên hướng dẫn'),(2,'sinh viên');
/*!40000 ALTER TABLE `positionforthesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `thesis_id` int DEFAULT NULL,
  `council_position_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `council_pos_id_in_score_idx` (`council_position_id`),
  KEY `thesis_id_sc_idx` (`thesis_id`),
  CONSTRAINT `council_pos_sc` FOREIGN KEY (`council_position_id`) REFERENCES `council_position` (`id`),
  CONSTRAINT `thesis_id_sc` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,5),(6,2,6),(7,2,7),(8,3,8),(9,3,9),(10,3,10),(11,3,11),(12,3,12),(13,4,13),(14,4,14),(15,4,15),(16,5,20),(17,5,21),(18,5,22);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_detail`
--

DROP TABLE IF EXISTS `score_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score_detail` (
  `score_id` int NOT NULL,
  `score_1` float NOT NULL,
  `score_2` float NOT NULL,
  `score_3` float NOT NULL,
  `score_4` float NOT NULL,
  PRIMARY KEY (`score_id`),
  CONSTRAINT `scoreID` FOREIGN KEY (`score_id`) REFERENCES `score` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_detail`
--

LOCK TABLES `score_detail` WRITE;
/*!40000 ALTER TABLE `score_detail` DISABLE KEYS */;
INSERT INTO `score_detail` VALUES (1,8,7,7,8),(2,6,5,6,5),(3,7,7,7,8),(4,9,9,8,7),(5,6,4,5,5),(6,6,7,8,7),(7,8,8,7,7),(8,7,8,8,7),(9,6,5,5,5),(10,4,5,5,6),(11,8,8,7,9),(12,9,9,9,7),(13,6,9,7,6),(14,6,8,6,5),(15,8,8,4,7),(16,9,8,6,6),(17,6,7,8,9),(18,6,7,8,9);
/*!40000 ALTER TABLE `score_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis`
--

DROP TABLE IF EXISTS `thesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thesis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `total_score` float DEFAULT NULL,
  `year` int DEFAULT NULL,
  `faculty_id` int DEFAULT NULL,
  `council_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `faculty_id_in_thesis_idx` (`faculty_id`),
  KEY `c_id_idx` (`council_id`),
  CONSTRAINT `c_id` FOREIGN KEY (`council_id`) REFERENCES `council` (`id`),
  CONSTRAINT `faculty_id_in_thesis` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis`
--

LOCK TABLES `thesis` WRITE;
/*!40000 ALTER TABLE `thesis` DISABLE KEYS */;
INSERT INTO `thesis` VALUES (1,'khóa luận 1',9,2022,1,NULL),(2,'khóa luận 2',10,2022,2,NULL),(3,'khóa luận 3',5,2021,6,NULL),(4,'khóa luận 4',5,2022,7,NULL),(5,'khóa luận 5',4,2021,2,NULL),(6,'khóa luận 6',2,2020,6,NULL),(7,'khóa luận 7',6,2022,1,NULL),(8,'khóa luận 8',7,2022,1,NULL),(9,'khóa luận 9',4,2020,3,NULL),(10,'khóa luận 10',8,2020,5,1),(11,'khóa luận 11',7,2020,7,7),(12,'khóa luận 12',8,2021,3,1),(13,'khóa luận 13',9,2021,12,7),(14,'khóa luận 14',8,2020,14,NULL),(15,'khóa luận 15',9,2021,12,NULL),(16,'khóa luận 16',6,2021,11,NULL),(17,'khóa luận 17',5,2022,2,NULL);
/*!40000 ALTER TABLE `thesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis_position`
--

DROP TABLE IF EXISTS `thesis_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thesis_position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `thesis_position` int DEFAULT NULL,
  `thesis_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_in_tp_idx` (`user_id`),
  KEY `thesis_pos_in_tp_idx` (`thesis_position`),
  KEY `thesis_id_in_tp_idx` (`thesis_id`),
  CONSTRAINT `thesis_id_in_tp` FOREIGN KEY (`thesis_id`) REFERENCES `thesis` (`id`),
  CONSTRAINT `thesis_pos_in_tp` FOREIGN KEY (`thesis_position`) REFERENCES `positionforthesis` (`id`),
  CONSTRAINT `user_id_in_tp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis_position`
--

LOCK TABLES `thesis_position` WRITE;
/*!40000 ALTER TABLE `thesis_position` DISABLE KEYS */;
INSERT INTO `thesis_position` VALUES (1,12,1,1),(2,34,2,1),(3,10,2,1),(5,42,2,2),(6,43,2,2),(7,26,1,3),(8,41,2,3),(9,11,1,4),(10,36,2,4),(11,35,2,4),(12,22,1,5),(13,40,2,5),(14,23,1,6),(15,44,2,6),(16,30,1,7),(17,8,2,7),(18,9,2,7),(19,31,1,8),(20,37,2,8),(21,32,1,9),(22,38,2,9),(23,46,2,9),(24,33,1,10),(25,47,2,10),(26,27,1,11),(27,43,2,11),(28,30,1,12),(29,15,2,12),(30,16,2,12);
/*!40000 ALTER TABLE `thesis_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_role` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `faculty_id` int DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `user_faculty_id_idx` (`faculty_id`),
  CONSTRAINT `user_faculty_id` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$Ws/MaKcA3QbIoHTJuhDZI.VodTjSc1NgP486la5BdTtR1Xc3lbDBe','Khương Duy','ROLE_ADMIN',NULL,'https://res.cloudinary.com/dbkgejwir/image/upload/v1661088442/lfpusdpt4yijogjygsug.jpg','2022-01-01',''),(2,'giaovu','$2a$10$Ws/MaKcA3QbIoHTJuhDZI.VodTjSc1NgP486la5BdTtR1Xc3lbDBe','Khương Duy','ROLE_GiaoVu',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01',''),(3,'giangvien','$2a$10$Ws/MaKcA3QbIoHTJuhDZI.VodTjSc1NgP486la5BdTtR1Xc3lbDBe','Khương Duy','ROLE_GiangVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01',''),(4,'sinhvien','$10$Ws/MaKcA3QbIoHTJuhDZI.VodTjSc1NgP486la5BdTtR1Xc3lbDBe','Khương Duy','ROLE_SinhVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01',''),(6,'diid','$2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','khương duy','ROLE_GiaoVu',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-08-19','khuongduy24076@gmail.com'),(7,'sv1','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trịnh Minh Thư','ROLE_SinhVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(8,'sv2','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Ngô Gia Hân','ROLE_SinhVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(9,'sv3','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trần Minh Long','ROLE_SinhVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(10,'sv4','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Tô Ái Như','ROLE_SinhVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(11,'gv_nn1','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Lê Thị Hồng Hoa','ROLE_GiangVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(12,'gv_it1','$2a$10$Ws/MaKcA3QbIoHTJuhDZI.VodTjSc1NgP486la5BdTtR1Xc3lbDBe','Nguyễn Bảo Long','ROLE_GiangVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(13,'gv_l1','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Hồ Thị Cẩm Loan','ROLE_GiangVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(14,'gv_sh1','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Lê Ngọc Thịnh','ROLE_GiangVien',7,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(15,'sv5','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Huỳnh Duy Khang','ROLE_SinhVien',7,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(16,'sv6','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Hoàng Minh Đăng','ROLE_SinhVien',7,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(17,'sv7','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Nguyễn Thái Trường Quân','ROLE_SinhVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(18,'gvv_it1','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Lê Yến Nhi','ROLE_GiaoVu',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(19,'gvv_sh1','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trịnh Mai Linh','ROLE_GiaoVu',7,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(20,'gvv_nn1','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Cao Lê Tuấn Anh','ROLE_GiaoVu',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(21,'gvv_l1','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Huỳnh Minh Sơn','ROLE_GiaoVu',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(22,'gv_nn2','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Nguyễn Minh Duy','ROLE_GiangVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(23,'gv_nn3','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Đỗ Trường An','ROLE_GiangVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(24,'gv_nn4','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Phạm Vũ Diệp','ROLE_GiangVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(25,'gv_nn5','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Lê Thảo Hân','ROLE_GiangVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(26,'gv_it2','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Huỳnh Hồ Khắc Huy','ROLE_GiangVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(27,'gv_it3','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Nguyễn Lê Tuyên','ROLE_GiangVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(28,'gv_it4','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Lê Huỳnh Sơn','ROLE_GiangVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(29,'gv_it5','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Nguyễn Thị Ngọc Thi','ROLE_GiangVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(30,'gv_l2','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Phạm Thái Kiều Anh','ROLE_GiangVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(31,'gv_l3','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Lê Ngọc Minh','ROLE_GiangVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(32,'gv_l4','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trần Minh Tuấn','ROLE_GiangVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(33,'gv_l5','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Ngô Bảo Anh','ROLE_GiangVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(34,'sv8','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trương Phú Vĩnh','ROLE_SinhVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(35,'sv9','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Nguyễn Thị Yến Vy','ROLE_SinhVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(36,'sv10','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Nguyễn Thị Kim Linh','ROLE_SinhVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(37,'sv11','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trần Lê Quốc Bảo','ROLE_SinhVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(38,'sv12','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trần Thị Mỹ Ngân','ROLE_SinhVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(39,'sv13','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Châu Thị Diễm Trang','ROLE_SinhVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(40,'sv14','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Nguyễn Mi Duyên','ROLE_SinhVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(41,'sv15','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Nguyễn Thị Cẩm Thùy','ROLE_SinhVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(42,'sv16','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Đỗ Thị Thúy Hồng','ROLE_SinhVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(43,'sv17','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Huỳnh Trung Phong','ROLE_SinhVien',1,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(44,'sv18','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Nguyễn Minh Hiếu','ROLE_SinhVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(45,'sv19','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trương Quang Hiển','ROLE_SinhVien',2,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(46,'sv20','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Đinh Vũ Hạ','ROLE_SinhVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(47,'sv21','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trương Quốc Vương','ROLE_SinhVien',6,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(48,'gv_sh2','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Lê Thị Ngọc Thắm','ROLE_GiangVien',7,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(49,'gv_sh3','2a$10$CUpa.dhQFQFzeDX.WNVw4urGnrptLyz5ASwMxAhR62QEn/8K5TUA.','Trần Linh','ROLE_GiangVien',7,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248722/r8sjly3st7estapvj19u.jpg','2022-01-01','khuongduy2461@gmail.com'),(53,'nhadaden','$2a$10$gPDaUn/Zx.4qs2Po7gLzL.b1kMU9SbGjbmQrzU5zSIeuJGm5YnOGi','nhã da đen','ROLE_SinhVien',14,'https://res.cloudinary.com/dbkgejwir/image/upload/v1661271348/lrffugkwgmqhme7rr7kv.jpg','2022-08-23','khuongduy24444401@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-03 21:37:12
