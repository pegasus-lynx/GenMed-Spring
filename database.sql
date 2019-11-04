-- MariaDB dump 10.17  Distrib 10.4.6-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: genmed
-- ------------------------------------------------------
-- Server version	10.4.6-MariaDB

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `plot_no` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `latitude` decimal(20,16) DEFAULT NULL,
  `longitude` decimal(20,16) DEFAULT NULL,
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `district` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('QWE123','DBR',25.2618485000000000,82.9838003999999900,3,'DDU','BOK','UPR'),('ASD123','TRE',25.2618497000000000,82.9837998000000000,4,'BOK','BOK','JHK');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `addressOfUser`
--

DROP TABLE IF EXISTS `addressOfUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addressOfUser` (
  `address_type` varchar(122) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`address_id`),
  KEY `address_id` (`address_id`),
  CONSTRAINT `addressOfUser_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `addressOfUser_ibfk_2` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addressOfUser`
--

LOCK TABLES `addressOfUser` WRITE;
/*!40000 ALTER TABLE `addressOfUser` DISABLE KEYS */;
INSERT INTO `addressOfUser` VALUES ('work',2,4);
/*!40000 ALTER TABLE `addressOfUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drugBatch`
--

DROP TABLE IF EXISTS `drugBatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drugBatch` (
  `batch_no` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mfg_date` date NOT NULL,
  `exp_date` date NOT NULL,
  `batch_id` int(11) NOT NULL AUTO_INCREMENT,
  `drug_id` int(11) NOT NULL,
  PRIMARY KEY (`batch_id`),
  KEY `drug_id` (`drug_id`),
  CONSTRAINT `drugBatch_ibfk_1` FOREIGN KEY (`drug_id`) REFERENCES `drugs` (`drug_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugBatch`
--

LOCK TABLES `drugBatch` WRITE;
/*!40000 ALTER TABLE `drugBatch` DISABLE KEYS */;
INSERT INTO `drugBatch` VALUES ('QWER123','2019-01-01','2021-01-01',1,1),('HJKM123','2018-01-01','2022-01-01',2,2),('ZXCV123','2019-01-01','2022-01-01',3,3),('AQRS125','2019-10-01','2020-10-01',4,2),('QSDW980','2019-10-14','2022-10-20',5,5);
/*!40000 ALTER TABLE `drugBatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drugComponents`
--

DROP TABLE IF EXISTS `drugComponents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drugComponents` (
  `comp_id` int(11) NOT NULL AUTO_INCREMENT,
  `comp_name` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`comp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugComponents`
--

LOCK TABLES `drugComponents` WRITE;
/*!40000 ALTER TABLE `drugComponents` DISABLE KEYS */;
INSERT INTO `drugComponents` VALUES (1,'CompA'),(2,'CompB'),(3,'CompC'),(4,'CompT');
/*!40000 ALTER TABLE `drugComponents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drugs`
--

DROP TABLE IF EXISTS `drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drugs` (
  `drug_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(48) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mf_name` varchar(48) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_generic` int(11) NOT NULL,
  `gen_id` int(11) NOT NULL,
  PRIMARY KEY (`drug_id`),
  KEY `gen_id` (`gen_id`),
  CONSTRAINT `drugs_ibfk_1` FOREIGN KEY (`gen_id`) REFERENCES `genericDrugs` (`gen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugs`
--

LOCK TABLES `drugs` WRITE;
/*!40000 ALTER TABLE `drugs` DISABLE KEYS */;
INSERT INTO `drugs` VALUES (1,'DrugA','MakeA',1,1),(2,'DrugB','MakeA',0,1),(3,'DrugC','MakeB',1,2),(4,'DrugD','MakeC',0,3),(5,'DrugQ','MakeQ',0,2);
/*!40000 ALTER TABLE `drugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genericDrugComposition`
--

DROP TABLE IF EXISTS `genericDrugComposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genericDrugComposition` (
  `percent` decimal(5,2) NOT NULL,
  `gen_id` int(11) NOT NULL,
  `comp_id` int(11) NOT NULL,
  PRIMARY KEY (`gen_id`,`comp_id`),
  KEY `comp_id` (`comp_id`),
  CONSTRAINT `genericDrugComposition_ibfk_1` FOREIGN KEY (`gen_id`) REFERENCES `genericDrugs` (`gen_id`),
  CONSTRAINT `genericDrugComposition_ibfk_2` FOREIGN KEY (`comp_id`) REFERENCES `drugComponents` (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genericDrugComposition`
--

LOCK TABLES `genericDrugComposition` WRITE;
/*!40000 ALTER TABLE `genericDrugComposition` DISABLE KEYS */;
INSERT INTO `genericDrugComposition` VALUES (20.00,1,1),(80.00,1,3),(20.00,2,1),(50.00,2,2),(30.00,2,3),(90.00,3,3),(20.00,4,4);
/*!40000 ALTER TABLE `genericDrugComposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genericDrugs`
--

DROP TABLE IF EXISTS `genericDrugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genericDrugs` (
  `gen_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`gen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genericDrugs`
--

LOCK TABLES `genericDrugs` WRITE;
/*!40000 ALTER TABLE `genericDrugs` DISABLE KEYS */;
INSERT INTO `genericDrugs` VALUES (1,'GenA'),(2,'GenB'),(3,'GenC'),(4,'GenW');
/*!40000 ALTER TABLE `genericDrugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemsOrdered`
--

DROP TABLE IF EXISTS `itemsOrdered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemsOrdered` (
  `quantity` int(11) NOT NULL,
  `price` decimal(7,2) NOT NULL,
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`item_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `itemsOrdered_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `itemsOrdered_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `shopInventory` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemsOrdered`
--

LOCK TABLES `itemsOrdered` WRITE;
/*!40000 ALTER TABLE `itemsOrdered` DISABLE KEYS */;
INSERT INTO `itemsOrdered` VALUES (12,120.00,3,1),(20,240.00,3,2);
/*!40000 ALTER TABLE `itemsOrdered` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_status` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_date` date NOT NULL,
  `order_time` time NOT NULL,
  `bill_amount` decimal(8,2) DEFAULT NULL,
  `shop_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `shop_id` (`shop_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'ordered','2019-01-01','00:00:00',0.00,1,2),(3,'ordered','2019-10-14','15:02:19',360.00,1,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `owner` (
  `user_id` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`shop_id`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `owner_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `owner_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner`
--

LOCK TABLES `owner` WRITE;
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
INSERT INTO `owner` VALUES (1,1);
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `comment` varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rating` decimal(3,2) NOT NULL,
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `license` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address_id` int(11) NOT NULL,
  `email_id` varchar(48) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `address_id` (`address_id`),
  CONSTRAINT `shop_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'Dev Medics','WASQ123',3,'dipeshkr.14@gmail.com');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopInventory`
--

DROP TABLE IF EXISTS `shopInventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopInventory` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `shop_id` int(11) NOT NULL,
  `batch_id` int(11) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `shop_id` (`shop_id`),
  KEY `batch_id` (`batch_id`),
  CONSTRAINT `shopInventory_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`),
  CONSTRAINT `shopInventory_ibfk_2` FOREIGN KEY (`batch_id`) REFERENCES `drugBatch` (`batch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopInventory`
--

LOCK TABLES `shopInventory` WRITE;
/*!40000 ALTER TABLE `shopInventory` DISABLE KEYS */;
INSERT INTO `shopInventory` VALUES (1,988,1,1,10.00),(2,980,1,2,12.00),(3,2500,1,3,15.00),(4,5000,1,4,5.00),(5,12345,1,5,6.75);
/*!40000 ALTER TABLE `shopInventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopPhone`
--

DROP TABLE IF EXISTS `shopPhone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopPhone` (
  `phone_no` varchar(13) COLLATE utf8mb4_unicode_ci NOT NULL,
  `shop_id` int(11) NOT NULL,
  PRIMARY KEY (`phone_no`,`shop_id`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `shopPhone_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopPhone`
--

LOCK TABLES `shopPhone` WRITE;
/*!40000 ALTER TABLE `shopPhone` DISABLE KEYS */;
INSERT INTO `shopPhone` VALUES ('+91966123789',1);
/*!40000 ALTER TABLE `shopPhone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `first_name` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Dipesh','Kumar',1,'$2a$10$OCYuNr1AQ8ABt/skCCFLLu1cQlOjeEa2X4H2sEbkXt9F0Pn5pc9nu','dipeshkr.141@gmail.com','shop'),('Kobra','Kai',2,'$2a$10$7m3zoAZNibYPRTFMh5Qkgec3Rxmi.1wyVDPDe1DcaOZq8LK6bBjjS','kobra@gmail.com','user'),('Admin','Admin',3,'$2a$10$FqyaRgyzwzPPAoK1O/ECoOGPyUO6ihia4Cs.nIE3tMvLZqG0laqSC','admin@admin.com','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userPhone`
--

DROP TABLE IF EXISTS `userPhone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userPhone` (
  `phone_no` varchar(13) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`phone_no`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `userPhone_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userPhone`
--

LOCK TABLES `userPhone` WRITE;
/*!40000 ALTER TABLE `userPhone` DISABLE KEYS */;
INSERT INTO `userPhone` VALUES ('+911234456712',2);
/*!40000 ALTER TABLE `userPhone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-05  3:01:58
