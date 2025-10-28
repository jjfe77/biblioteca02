-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca_simple
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `autor` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `editorial` varchar(150) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `anio_edicion` year DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (1,'Cien años de soledad','Gabriel García Márquez','Sudamericana',1967),(2,'El principito','Antoine de Saint-Exupéry','Reynal & Hitchcock',1943),(3,'Rayuela','Julio Cortázar','Sudamericana',1963),(4,'Fahrenheit 451','Ray Bradbury','Ballantine Books',1953);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero_socio` int NOT NULL,
  `id_libro` int NOT NULL,
  `fecha_prestamo` date NOT NULL DEFAULT (curdate()),
  `fecha_devolucion` date NOT NULL DEFAULT ((curdate() + interval 7 day)),
  `devuelto` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `numero_socio` (`numero_socio`),
  KEY `id_libro` (`id_libro`),
  CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`numero_socio`) REFERENCES `usuario` (`numero_socio`),
  CONSTRAINT `prestamo_ibfk_2` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1,1,1,'2025-10-10','2025-10-17',0),(2,1,2,'2025-10-10','2025-10-17',0),(3,2,3,'2025-10-10','2025-10-17',0);
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `numero_socio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `apellido` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `dni` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `direccion` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefono` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mail` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`numero_socio`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Ana','Pérez','30011222','Calle Falsa 123','223360303',NULL),(2,'Luis','Gómez','31222333','Av. Siempreviva 742','223862919','kjlhg@lkjg.com'),(3,'Sofía','Martínez','32233444','Belgrano 450','223233684',NULL),(4,'Juan','Garcia','12450000','Arenales 100','223579667','gfdsa@kj.com'),(5,'Mario','Perez','45123698','Luro 120','223197281','mqt@as.com'),(6,'Trota','Juancito','89123698','Rivadavia 1200','223247404','juancito@trota.com'),(7,'Sosa','Maria Luisa','32659841','Paso 220','223645177','123@123.com'),(8,'Una','Solida','65498787','Cerrito 125','223483676','asd@asd.com'),(12,'Carlos','Lopez','12457896','Cerrito 1250','223482849','pol@pol.com'),(15,'Luis','Maidana','98542136','Rivadavia 210','223963217','qwerty@qwerty.com'),(17,'Luis','Rodriguez','98540136','Rivadavia 2100','223367539','qwerty@qwerty2.com'),(18,'Martin2','Ferchu','12349877','Luro 10','223948043','hola@hola2.com'),(23,'Juan','Pérez','12345678','Calle Falsa 123','223637601','juan.perez@mail.com'),(24,'María','Gómez','87654321','Avenida Siempre Viva 742','223343877','maria.gomez@mail.com'),(25,'Carlos','Rodríguez','11223344','Calle del Sol 50','223806580','carlos.rodriguez@mail.com'),(26,'Lucía','Fernández','44332211','Boulevard Central 99','223001272','lucia.fernandez@mail.com'),(27,'Diego','Sánchez','55667788','Calle Luna 10','223586622','diego.sanchez@mail.com'),(28,'Valeria','Martínez','88776655','Avenida Estrella 7','223929291','valeria.martinez@mail.com'),(29,'Matías','García','33445566','Calle Aurora 12','223886591','matias.garcia@mail.com'),(30,'Sofía','López','66554433','Calle del Mar 25','223645084','sofia.lopez@mail.com'),(31,'Federico','Hernández','99887766','Avenida Libertad 88','223565645','federico.hernandez@mail.com'),(32,'Camila','Ruiz','77665547','Calle Jardín 5','223892977','camila.ruiz@mail.com');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-13  0:28:58
