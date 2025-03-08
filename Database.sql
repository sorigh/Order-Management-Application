-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: tpdb
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `purchaseId` int NOT NULL,
  `customerName` varchar(45) DEFAULT NULL,
  `productName` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`purchaseId`),
  KEY `purchaseId_idx` (`purchaseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'Miau Pisu','zgarda ajustabila',45,1,45),(2,'Miau Pisu','minge material',5,2,10),(3,'Ham Cutu','minge material',5,1,5),(4,'Ham Cutu','zgarda ajustabila',45,1,45),(5,'Crina Munteanu','creion mecanic',15.1,1,15.1),(6,'Crina Munteanu','top hartii A4',16.9,1,16.9),(7,'Ioana Brad','banda adeziva transparenta',2.35,2,4.7),(8,'Ioana Brad','creioane 120 culori ArtGram',209,1,209),(9,'Bogdan Mihai','acuarele faber castell',199.99,1,199.99),(10,'Mihai Stoica','capsator Novus B4',101.25,1,101.25),(11,'Mihai Stoica','capse Novus ',2.08,1,2.08),(12,'Emilia Brown','creion mecanic',15.1,1,15.1),(13,'Emilia Brown','banda adeziva transparenta',2.35,1,2.35),(14,'John Doe','creion mecanic',15.1,2,30.2),(15,'Crina Munteanu','top hartii A4',16.9,2,33.8),(16,'Crina Munteanu','acuarele',20,1,20),(17,'Oana Ionescu','acuarele faber castell',199.99,1,199.99);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customerId` int NOT NULL AUTO_INCREMENT,
  `lastName` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Doe','John','Str Turturele','john.doe@gmail.com',40),(2,'Vasiliu','Ion','Str Florilor','vasiliuIon230@gmail.com',45),(3,'Brown','Emilia','Str Copacel','emiBrw44@gmail.com',25),(4,'Munteanu','Crina','Str Fantanele','CrinaMunty@gmail.com',20),(5,'Ionescu','Oana','Str Bradutului','Oana_Ionescu@gmail.com',18),(7,'Stoica','Mihai','Str Observatorului','mihi_stc@gmail.com',25),(8,'Dima','Lenuta','Str Victoriei','lenuDima@gmail.com',23),(9,'Moldovan','Stefana','Str Republicii','stefi__moldo@gmail.com',16),(10,'Stan','Stefana','Str Republicii','ststefi@gmail.com',18),(11,'Mihai','Bogdan','Str Stefan cel mare','mihai.bogdi@gmail.com',22),(12,'Lazar','Dumitru Ion','Str Coacazelor','dumitrulzr@gmail.com',50),(13,'Pisu','Miau','Str Miau Miau','cat_lvr@gmail.com',5),(14,'Cutu','Ham','Str Ham Ham','dog_lvr@gmail.com',6);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'creioane 24 culori/set','Confectionat din lemn, forma hexagonala',5.23,10),(2,'creioane 120 culori ArtGram','Caseta metalica, pigment natural',209,4),(3,'creion mecanic','1mm rotring',15.1,16),(4,'acuarele','cutie plastic, 12 culori',20,6),(5,'acuarele faber castell','48 culori, pensula rezervor inclusa',199.99,1),(6,'top hartii A4','500 coli pentru copiator',16.9,2),(7,'banda adeziva transparenta','dispenser inclus, 48mmX25m',2.35,17),(8,'capsator Novus B4','40 de file, negru sau albastru',101.25,9),(9,'capsator Novus B2','25 de file, bej',58.99,10),(10,'capse Novus ','capse nr 10, 1000 capse/cutie',2.08,4),(11,'minge material','50cm, material sintetic',5,0),(12,'carton colorat','A4, diverse culori',4,20),(13,'zgarda ajustabila','4 marimi, 10 culori',45,3),(14,'calculator buzunar canon','bateriile nu sunt incluse',87.3,2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `purchaseId` int NOT NULL AUTO_INCREMENT,
  `customerId` int NOT NULL,
  `productId` int NOT NULL,
  `amount` int DEFAULT NULL,
  PRIMARY KEY (`purchaseId`),
  KEY `client_id_idx` (`customerId`),
  KEY `product_id_idx` (`productId`),
  CONSTRAINT `client_id` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE,
  CONSTRAINT `product_id` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,13,13,1),(2,13,11,2),(3,14,11,1),(4,14,13,1),(5,4,3,1),(6,4,6,1),(9,11,5,1),(10,7,8,1),(11,7,10,1),(12,3,3,1),(13,3,7,1),(14,1,3,2),(15,4,6,2),(16,4,4,1),(17,5,5,1);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-16 12:35:51
