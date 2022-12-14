-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: inventario
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `cod_admin` varchar(7) NOT NULL,
  `nombre_admin` varchar(45) NOT NULL,
  `dni_admin` int NOT NULL,
  `celular_admin` int NOT NULL,
  `nivel_admin` varchar(20) NOT NULL,
  PRIMARY KEY (`cod_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `cod_cliente` varchar(45) NOT NULL,
  `nombre_cli` varchar(45) DEFAULT NULL,
  `dni_cli` int DEFAULT NULL,
  `celular_cli` int DEFAULT NULL,
  PRIMARY KEY (`cod_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('C001','Uriel Martin',987489784,78456548),('C002','Juan Perez Torres',78946589,987125465);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `det_factura`
--

DROP TABLE IF EXISTS `det_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `det_factura` (
  `cod_fac` varchar(7) NOT NULL,
  `cod_pro` varchar(7) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `cantidad_pro` int DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `precio_total` int DEFAULT NULL,
  PRIMARY KEY (`cod_fac`,`cod_pro`),
  KEY `fk_det_factura_factura_idx` (`cod_fac`),
  KEY `fk_det_factura_producto1_idx` (`cod_pro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `det_factura`
--

LOCK TABLES `det_factura` WRITE;
/*!40000 ALTER TABLE `det_factura` DISABLE KEYS */;
INSERT INTO `det_factura` VALUES ('F000001','P002','Monitor',2,750,1500),('F000002','P001','Mouse - Glorius Model O',1,365,365),('F000002','P005','Monitor - Dell S24 22 HG',1,1450,1450),('F000002','P009','Teclado - Razer Deathstalker V2 Pro',1,780,780);
/*!40000 ALTER TABLE `det_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encargado`
--

DROP TABLE IF EXISTS `encargado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `encargado` (
  `cod_encar` varchar(7) NOT NULL,
  `nombre_encar` varchar(45) DEFAULT NULL,
  `dni_encar` int DEFAULT NULL,
  `celular_encar` int DEFAULT NULL,
  PRIMARY KEY (`cod_encar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encargado`
--

LOCK TABLES `encargado` WRITE;
/*!40000 ALTER TABLE `encargado` DISABLE KEYS */;
INSERT INTO `encargado` VALUES ('AD001','Juan Perez',89784562,987456789),('AD002','Gabriel Torres',87891242,986745889),('AD003','Julio Martinez',78947845,987485984);
/*!40000 ALTER TABLE `encargado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `cod_fac` varchar(7) NOT NULL,
  `cod_encar` varchar(7) NOT NULL,
  `cod_cliente` varchar(45) NOT NULL,
  `nombre_cli` varchar(45) DEFAULT NULL,
  `total_fac` int DEFAULT NULL,
  `fecha_fac` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_fac`,`cod_encar`,`cod_cliente`),
  KEY `fk_factura_cliente1_idx` (`cod_cliente`),
  KEY `fk_factura_encargado1_idx` (`cod_encar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES ('F000001','AD001','C001','Uriel Martin',1770,'08/11/2022'),('F000002','AD002','C002','Juan Perez Torres',3062,'07/12/2022');
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `cod_pro` varchar(7) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `precio` int NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`cod_pro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('P001','Mouse - Glorius Model O',365,9),('P002','Monitor - Viotek GFI 27 DBXA',1600,8),('P003','Teclado - Razer Huntsman V2 Analog.',650,8),('P004','Mouse - Logitech G502 X PLUS',550,10),('P005','Monitor - Dell S24 22 HG',1450,9),('P006','Teclado - Corsair K100 Air Wireless',590,8),('P007','Mouse - SteelSeries Rival 3',480,10),('P008','Monitor - Samsung 49-Inch Odyssey G9 Gaming',1400,10),('P009','Teclado - Razer Deathstalker V2 Pro',780,9);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-14  0:21:43
