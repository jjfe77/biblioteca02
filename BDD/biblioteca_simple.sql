-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca_simple
-- ------------------------------------------------------
-- Server version	8.0.39

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

use `juanjo_biblioteca`;
DROP TABLE IF EXISTS libro;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `id_libro` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `titulo` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `autor` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `genero` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `editorial` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `anio` int DEFAULT NULL,
  `AÑO` int DEFAULT NULL,
  PRIMARY KEY (`id_libro`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (1,'978-001','Historia Universal','Juan Pérez','Historia','Editorial Alfa',2010,NULL),(2,'978-002','Historia de América','Ana Gómez','Historia','Editorial Beta',2012,NULL),(3,'978-003','Historia de Europa','Luis Martínez','Historia','Editorial Gamma',2015,NULL),(4,'978-004','Programación en C','Carlos Torres','Informática','Editorial Delta',2018,NULL),(5,'978-005','Algoritmos y Estructuras de Datos','María López','Informática','Editorial Épsilon',2020,NULL),(6,'978-006','Bases de Datos','José Fernández','Informática','Editorial Zeta',2019,NULL),(7,'978-007','Electrónica Básica','Pedro Sánchez','Electrónica','Editorial Eta',2011,NULL),(8,'978-008','Circuitos y Señales','Laura Díaz','Electrónica','Editorial Theta',2014,NULL),(9,'978-009','Microcontroladores','Ricardo Ruiz','Electrónica','Editorial Iota',2016,NULL),(10,'978-010','Matemática Discreta','Verónica Pérez','Matemática','Editorial Kappa',2013,NULL),(11,'978-011','Cálculo I','Fernando Gómez','Matemática','Editorial Lambda',2012,NULL),(12,'978-012','Cálculo II','Claudia Rojas','Matemática','Editorial Mu',2015,NULL),(13,'978-013','Historia de la Ciencia','Sofía Torres','Historia','Editorial Nu',2017,NULL),(14,'978-014','Redes de Computadoras','Miguel Herrera','Informática','Editorial Xi',2018,NULL),(15,'978-015','Sistemas Digitales','Patricia Molina','Electrónica','Editorial Omicron',2019,NULL),(16,'978-016','Teoría de Números','Javier Ramírez','Matemática','Editorial Pi',2011,NULL),(17,'978-017','Inteligencia Artificial','Lorena Castro','Informática','Editorial Rho',2021,NULL),(18,'978-018','Historia Contemporánea','Diego Flores','Historia','Editorial Sigma',2014,NULL),(19,'978-019','Matemática Aplicada','Natalia Moreno','Matemática','Editorial Tau',2016,NULL),(20,'978-020','Electrónica Avanzada','Gustavo Jiménez','Electrónica','Editorial Upsilon',2020,NULL),(21,'978-999','Historia de la Tv','Juan Perez','Documental','Alfaguara',0,2022),(22,'100','102','hola','aaa','asdf',0,2050),(23,'978-958','La Primera Prueba','Desconocido','Fantastico','ISFT204',0,2025);
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
  `LIBRO_ID_LIBRO` int DEFAULT NULL,
  `USUARIO_NUMERO_SOCIO` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `numero_socio` (`numero_socio`),
  KEY `id_libro` (`id_libro`),
  KEY `FK_prestamo_LIBRO_ID_LIBRO` (`LIBRO_ID_LIBRO`),
  KEY `FK_prestamo_USUARIO_NUMERO_SOCIO` (`USUARIO_NUMERO_SOCIO`),
  CONSTRAINT `fk_prestamo_libro` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_prestamo_LIBRO_ID_LIBRO` FOREIGN KEY (`LIBRO_ID_LIBRO`) REFERENCES `libro` (`id_libro`),
  CONSTRAINT `FK_prestamo_numero_socio` FOREIGN KEY (`numero_socio`) REFERENCES `usuario` (`numero_socio`),
  CONSTRAINT `FK_prestamo_USUARIO_NUMERO_SOCIO` FOREIGN KEY (`USUARIO_NUMERO_SOCIO`) REFERENCES `usuario` (`numero_socio`),
  CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`numero_socio`) REFERENCES `usuario` (`numero_socio`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (6,27,3,'2025-11-07','2025-11-14',0,NULL,NULL),(8,33,7,'2025-11-07','2025-11-13',0,NULL,NULL),(11,34,3,'2025-11-07','2025-11-14',0,NULL,NULL),(12,2,18,'2025-11-07','2025-11-14',0,NULL,NULL),(13,1,19,'2025-11-07','2025-11-14',0,NULL,NULL),(14,38,17,'2025-11-07','2025-11-14',0,NULL,NULL),(17,37,17,'2025-11-07','2025-11-14',0,NULL,NULL);
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
  `nombre` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellido` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dni` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `direccion` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefono` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mail` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`numero_socio`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Pérez','Ana','30011222','Calle Falsa 123','1145678901','ana@perez.com'),(2,'Gómez','Luis','31222333','Av. Siempreviva 7422','1145123456','kjlhg@lkjg.com'),(3,'Martínez','Sofía','32233444','Belgrano 450','1167894561','martinez@sofia.com'),(4,'Juancito','Garcia','12450000','Arenales 100','223123457','gfdsa@kj.com'),(5,'Mario','Perez','45123698','Luro 158','223 789632','mqt@as.com'),(6,'Juancito','Trota','89123698','Rivadavia 1200','223 900000','juancito@trota.com'),(7,'Maria Luisa','Sosa','32659841','Paso 220','223 230548','123@123.com'),(12,'Carlos','Lopez','12457896','Cerrito 1250','223 125963','pol@pol.com'),(17,'Luis','Rodriguez','98540136','Rivadavia 2100','223 526300','qwerty@qwerty2.com'),(18,'Martin2','Ferchu','12349877','Luro 10','223 452103','hola@hola2.com'),(23,'Juan','Pérez','12345678','Calle Falsa 123','223 123456','juan.perez@mail.com'),(24,'María','Gómez','87654321','Avenida Siempre Viva 742','223 654321','maria.gomez@mail.com'),(25,'Carlos','Rodríguez','11223344','Calle del Sol 50','223 112233','carlos.rodriguez@mail.com'),(26,'Lucía','Fernández','44332211','Boulevard Central 99','223 445566','lucia.fernandez@mail.com'),(27,'Diego','Sánchez','55667788','Calle Luna 10','223778892','diego.sanchez@mail.com'),(28,'Valeria','Martínez','88776655','Avenida Estrella 7','223 667788','valeria.martinez@mail.com'),(29,'Matías','García','33445566','Calle Aurora 12','223 334455','matias.garcia@mail.com'),(30,'Sofía','López','66554433','Calle del Mar 25','223 556677','sofia.lopez@mail.com'),(31,'Federico','Hernández','99887766','Avenida Libertad 88','223 889900','federico.hernandez@mail.com'),(32,'Camila','Ruiz','77665544','Calle Jardín 5','223 223344','camila.ruiz@mail.com'),(33,'Alberto','Mendoza','25361497','Moreno 105','223 123456','moreno@alberto.com'),(34,'Julian','Marsoline','23548741','Falucho 25','2235523688','julian@marsolin.com'),(35,'Marta','Aparicio','26357159','Garay 503','2235265874','aparicio@marta.com'),(36,'Juan','Roltre','45236987','Paso 256','2235154789','juan@roltre.com'),(37,'Garcito','Mario','36925874','Alberti 78','2235698744','garcito@mariocom'),(38,'Lucas','Brunca','41258963','Edison 10259','2235123987','lucas@brunca.com');
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

-- Dump completed on 2025-11-08  0:30:12
