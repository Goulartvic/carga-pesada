-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: cargapesada
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(40) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `street` varchar(40) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (31,'Santa Catarina','São José','President Kenedi',231,7),(32,'Santa Catarina','Floripa','Rua das Joaninhas',6312,8),(33,'Santa Catarina','Floripa','Partida',1231,NULL),(34,'Santa Catarina','Biguaçu','Chegada',2312,NULL),(35,'Santa Catarina','Floripa','Rua',231,9),(37,'1','1','1',1,11),(38,'1','1','11',1,12),(39,'SC','Floripa','1',1,13),(40,'1','2','1',1,NULL),(41,'1','1','1',1,NULL),(42,'1','1','1',1,NULL),(43,'2','2','1',2,NULL),(44,'1','1','11',1,NULL),(45,'2','2','1',2,NULL),(46,'1','1','1',1,NULL),(47,'2','2','1',2,NULL),(48,'1','1','1',1,NULL),(49,'2','2','1',22,NULL),(50,'1','1','12',2,NULL),(51,'2','2','2',2,NULL);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `departure` int(11) DEFAULT NULL,
  `arrival` int(11) DEFAULT NULL,
  `vehicle` varchar(20) DEFAULT NULL,
  `worker` int(11) DEFAULT NULL,
  `customer` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `departure` (`departure`),
  KEY `arrival` (`arrival`),
  KEY `vehicle` (`vehicle`),
  KEY `worker` (`worker`),
  KEY `customer` (`customer`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`departure`) REFERENCES `address` (`address_id`),
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`arrival`) REFERENCES `address` (`address_id`),
  CONSTRAINT `request_ibfk_3` FOREIGN KEY (`vehicle`) REFERENCES `vehicle` (`plate`),
  CONSTRAINT `request_ibfk_4` FOREIGN KEY (`worker`) REFERENCES `user` (`user_id`),
  CONSTRAINT `request_ibfk_5` FOREIGN KEY (`customer`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (13,33,34,'HDA1321',7,8,1),(16,38,43,'1',13,12,3),(17,37,43,'1',13,12,4),(18,37,49,'2',13,12,3),(19,50,51,'2',13,12,4);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `phone_number` varchar(40) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'1243412','trabalha10','trabalhador','123','321423',2,0),(8,'21424131','Contratante','contratante','123','436274',1,0),(9,'asdasf','Vic','vic','123','3524',1,0),(11,'1','1','userP','111','1',2,0),(12,'1111','userC','user','111','1',1,0),(13,'1111','UserPP','userP2','111','1',2,2.3125);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `brand` varchar(40) DEFAULT NULL,
  `model` varchar(40) DEFAULT NULL,
  `plate` varchar(20) NOT NULL,
  `available` tinyint(1) DEFAULT NULL,
  `vehicle_size` int(11) DEFAULT NULL,
  `intercity` tinyint(1) DEFAULT NULL,
  `km_price` double DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`plate`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES ('1','1','1',1,1,0,100,13),('Teste','Teste','1111',1,2,1,100,11),('22','2','2',1,2,0,200,13),('3','3','3',1,3,0,300,13),('Ford','F1000','HDA1321',0,2,0,2.4,7);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-25 21:39:03
