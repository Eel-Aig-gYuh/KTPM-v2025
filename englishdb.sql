-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: englishdb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Noun'),(2,'Verb'),(3,'Adjective'),(4,'Adverb');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choice`
--

DROP TABLE IF EXISTS `choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `choice` (
  `id` varchar(50) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `is_correct` bit(1) DEFAULT NULL,
  `question_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_choice_question_idx` (`question_id`),
  CONSTRAINT `fk_choice_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choice`
--

LOCK TABLES `choice` WRITE;
/*!40000 ALTER TABLE `choice` DISABLE KEYS */;
INSERT INTO `choice` VALUES ('1206ab50-ff0d-4f73-a993-f25abee74bda','is',_binary '\0','fd9a8c35-f69a-4173-8f81-5cf9d3dedb2a'),('196ae587-b058-4aae-9743-13efd89bb7c7','your',_binary '\0','480c2e87-73f7-489f-ba28-3996b760b422'),('1a87eeef-0e9a-4f48-a082-9a2950a05453','are',_binary '\0','a27a1a63-456d-49cb-a34e-88f1d11e6f74'),('27a01cbb-1385-48e5-a354-8046e70d2e97','well',_binary '\0','609163cb-0f6b-44e1-9ab8-073594a47409'),('2ee3358e-703a-4ec2-a737-c77a468a022a','is',_binary '\0','a27a1a63-456d-49cb-a34e-88f1d11e6f74'),('372c7092-0c50-4f1a-b9e9-827093a522c1','beauty',_binary '\0','609163cb-0f6b-44e1-9ab8-073594a47409'),('54a08aa5-060e-4be3-8df9-f432f40be46d','am',_binary '','fd9a8c35-f69a-4173-8f81-5cf9d3dedb2a'),('55c16b9f-9e75-4cec-9949-1dc95c0c5e7a','were',_binary '','a27a1a63-456d-49cb-a34e-88f1d11e6f74'),('59e5b850-fb61-4ea4-aba7-b7a6ece4ebd9','yours',_binary '\0','480c2e87-73f7-489f-ba28-3996b760b422'),('612c2936-8d79-49ef-a4a5-e40d089c72bf','yourself',_binary '','480c2e87-73f7-489f-ba28-3996b760b422'),('73c6a61c-a138-472d-aa61-dbd4b7ae6a09','good',_binary '','609163cb-0f6b-44e1-9ab8-073594a47409'),('9a740b7b-bb94-4194-b42d-80460c3c57e0','my',_binary '','115dd543-06f0-417e-941a-1ec75fde5f64'),('c73fb18b-7018-4d36-80ef-5b60bad6a2d5','you',_binary '\0','480c2e87-73f7-489f-ba28-3996b760b422'),('c83cb011-c2c8-4faa-84cd-d1e5e72de819','myself',_binary '\0','115dd543-06f0-417e-941a-1ec75fde5f64'),('d4909df5-888e-40c0-a747-321c0c5440a5','mine',_binary '\0','115dd543-06f0-417e-941a-1ec75fde5f64'),('d54647da-b136-4294-a13c-6b15c40f74a1','lovely',_binary '\0','609163cb-0f6b-44e1-9ab8-073594a47409'),('dd17efdb-c26e-42c3-aa95-f06360c53275','are',_binary '\0','fd9a8c35-f69a-4173-8f81-5cf9d3dedb2a'),('f0d6f5b2-dc4e-4201-b2cb-89b610f66169','was',_binary '\0','a27a1a63-456d-49cb-a34e-88f1d11e6f74'),('f2f38c43-22cb-4af3-8f18-b1e3f9a6f93c','me',_binary '\0','115dd543-06f0-417e-941a-1ec75fde5f64'),('fd5106f7-bdbf-403b-b5d1-3cee5c44d6e4','was',_binary '\0','fd9a8c35-f69a-4173-8f81-5cf9d3dedb2a');
/*!40000 ALTER TABLE `choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question` (
  `id` varchar(50) NOT NULL,
  `content` text,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_category_idx` (`category_id`),
  CONSTRAINT `fk_question_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('115dd543-06f0-417e-941a-1ec75fde5f64','This is ... book',4),('480c2e87-73f7-489f-ba28-3996b760b422','You should do it ...',4),('609163cb-0f6b-44e1-9ab8-073594a47409','This is a ... book',3),('a27a1a63-456d-49cb-a34e-88f1d11e6f74','Wishing you ... here',2),('fd9a8c35-f69a-4173-8f81-5cf9d3dedb2a','I ... a pupil',2);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-29 16:05:30
