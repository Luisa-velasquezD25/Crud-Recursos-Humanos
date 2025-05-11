CREATE DATABASE  IF NOT EXISTS `recursos_humanos` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `recursos_humanos`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: recursos_humanos
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `id_funcionario` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_identificacion` varchar(50) NOT NULL,
  `numero_identificacion` varchar(100) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `estado_civil` varchar(50) DEFAULT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id_funcionario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'CC','123456789','Carlos','Ramírez','Casado','Masculino','Calle 123 #45-67','3111234567','1990-05-10'),(2,'TI','987654321','Laura','Gómez','Soltera','Femenino','Carrera 10 #20-30','3209876543','1997-09-21'),(8,'CC','123456789','Juan','Pérez','Soltero','Masculino','Calle 123','3001234567','2025-05-08'),(11,'CC','345678921','Carlos','Ortiz','Casado','Masculino','cr 21 #40-21','322548743','2025-05-08'),(13,'CC','789012345','Paola','Alvarez','Comprometida','Femenino','Calle 33-67','3008945671','2000-05-01');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_familiar`
--

DROP TABLE IF EXISTS `grupo_familiar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_familiar` (
  `id_familiar` int(11) NOT NULL AUTO_INCREMENT,
  `id_funcionario` int(11) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `parentesco` varchar(45) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id_familiar`),
  KEY `fk_grupo_familiar_funcionario_idx` (`id_funcionario`),
  CONSTRAINT `fk_grupo_familiar_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_familiar`
--

LOCK TABLES `grupo_familiar` WRITE;
/*!40000 ALTER TABLE `grupo_familiar` DISABLE KEYS */;
INSERT INTO `grupo_familiar` VALUES (1,1,'Ana','Ramírez','Hermana','1995-08-15'),(2,2,'Mateo','Gómez','Hijo','2010-12-01'),(3,1,'Ana','Ramírez','Hermana','1995-08-15'),(4,2,'Mateo','Gómez','Hijo','2010-12-01');
/*!40000 ALTER TABLE `grupo_familiar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion_academica`
--

DROP TABLE IF EXISTS `informacion_academica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacion_academica` (
  `id_academico` int(11) NOT NULL AUTO_INCREMENT,
  `universidad` varchar(100) NOT NULL,
  `nivel_estudio` varchar(45) DEFAULT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `id_funcionario` int(11) NOT NULL,
  PRIMARY KEY (`id_academico`),
  KEY `fk_informacion_academica_funcionario1` (`id_funcionario`),
  CONSTRAINT `fk_informacion_academica_funcionario1` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion_academica`
--

LOCK TABLES `informacion_academica` WRITE;
/*!40000 ALTER TABLE `informacion_academica` DISABLE KEYS */;
INSERT INTO `informacion_academica` VALUES (1,'Universidad de Antioquia','Pregrado','Ingeniería de Sistemas',1),(2,'Universidad Nacional','Maestría','Administración de Empresas',2);
/*!40000 ALTER TABLE `informacion_academica` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-10 18:56:24
