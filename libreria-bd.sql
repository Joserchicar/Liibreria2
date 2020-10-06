-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: libreria
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

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
-- Current Database: `libreria`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `libreria` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;

USE `libreria`;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genero` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `genero_UN` (`genero`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (6,'actual'),(3,'CiFi'),(2,'clasico'),(16,'DarkGoldenRod'),(9,'DarkKhaki'),(12,'DeepPink'),(14,'DimGray'),(4,'epica fantastica'),(10,'GoldenRod'),(13,'HoneyDew'),(11,'Linen'),(1,'novela negra'),(15,'Orchid'),(5,'romantica'),(7,'Silver'),(8,'SpringGreen');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) CHARACTER SET latin1 NOT NULL,
  `genero` int(11) NOT NULL,
  `imagen` varchar(100) DEFAULT NULL,
  `precio` decimal(5,2) NOT NULL,
  `id_Usuario` int(11) NOT NULL,
  `fecha_Creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_Validacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `titulo` (`titulo`),
  KEY `libro_FK` (`genero`),
  KEY `libro_FK_1` (`id_Usuario`),
  CONSTRAINT `libro_FK` FOREIGN KEY (`genero`) REFERENCES `genero` (`id`),
  CONSTRAINT `libro_FK_1` FOREIGN KEY (`id_Usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (1,'El Quijote',2,'\"https://picsum.photos/100/100\";',24.50,1,'2020-07-13 12:34:03','2020-07-13 12:34:03'),(2,'La Celestina',2,'\"https://picsum.photos/100/100\";',18.99,1,'2020-07-13 12:34:03',NULL),(3,'La Regenta',2,'\"https://picsum.photos/100/100\";',16.40,2,'2020-07-13 12:34:03',NULL),(4,'El Lazarillo de Tormes',2,'\"https://picsum.photos/100/100\";',12.20,1,'2020-07-13 12:34:03','2020-07-13 12:34:03'),(5,'Fortunata y Jacinta',2,'\"https://picsum.photos/100/100\";',17.00,3,'2020-07-13 12:34:03',NULL),(6,'El seÃ±or de los anillos',4,'\"https://picsum.photos/100/100\";',24.50,1,'2020-07-13 12:34:03','2020-07-13 12:34:03'),(7,'La ley de Murphy',6,'\"https://picsum.photos/100/100\";',18.00,2,'2020-07-13 12:34:03',NULL),(8,'las bicicletas son para el verano',6,'https://picsum.photos/100/100',24.00,2,'2020-07-22 00:00:00',NULL),(13,'Fundacion',3,'\"https://picsum.photos/100/100\";',18.99,1,'2020-07-13 12:34:03',NULL),(14,'Iste optio cum suscipit quis.',7,'tmp//d070140b15a1cbd6c2588678b5cc4d79.jpg',999.99,5,'2008-02-19 11:41:49','1976-09-23 02:10:23'),(15,'Unde et aspernatur qui et inventore alias quo.',8,'tmp//d816898673d09283a0010c41cad14873.jpg',999.99,6,'1974-04-09 05:31:30','2011-11-12 20:24:00'),(16,'Cum vel sed non.',9,'tmp//ae951c212ac1d80a1d4783188fb65452.jpg',999.99,7,'2000-09-05 10:55:58','2005-02-15 19:29:29'),(17,'Mollitia tempora enim sed dolorem et et.',10,'tmp//1df8c7fa976a380a684ef710106633c5.jpg',3.02,8,'1973-03-07 15:05:49','2007-03-04 04:33:03'),(18,'Fugiat est debitis consequatur eos est.',11,'tmp//2d37098cf3294d2fc8db45bf08d70d21.jpg',999.99,9,'1994-11-05 03:58:35','1990-12-04 08:10:24'),(19,'Rerum est velit occaecati fuga maxime.',12,'tmp//af07c1ef449a0862c2d9fb87c994316c.jpg',160.92,10,'1998-12-15 00:55:37','1978-07-14 00:03:05'),(20,'Rem voluptatem unde velit omnis rerum.',13,'tmp//0e1949f9f630d1823ec4db463e6dab16.jpg',999.99,11,'1996-09-09 09:07:52','1985-09-21 02:00:48'),(21,'Pariatur eum debitis ullam.',14,'tmp//e561d2111e8478c985f2412e4349ca17.jpg',551.83,12,'1972-12-27 00:44:34','2002-05-31 21:00:08'),(22,'Quibusdam officia amet itaque praesentium consequatur qui nihil.',15,'tmp//11fac2095b35bacfbee39d85a2b1e793.jpg',999.99,13,'1982-05-03 14:03:13','1986-12-21 04:50:53'),(23,'Dolores aspernatur eos illum hic sint temporibus.',16,'tmp//ed788974dd88065832b3d50972e4bfef.jpg',999.99,14,'1970-12-21 04:52:18','2018-02-23 13:13:24'),(24,'Sed numquam aliquam quia porro.',7,'tmp//3b8f4895733cd806b3973b974c38e7e9.jpg',662.99,15,'1993-12-17 22:47:09','2001-11-26 02:04:26'),(25,'Ut id ut culpa et libero ut et.',8,'tmp//dbdf42b324dd2bdd1ef11edece45c5ad.jpg',1.83,16,'2017-12-24 14:31:53','1976-09-04 11:08:07'),(26,'Ullam non dignissimos voluptatem voluptate non.',9,'tmp//ba7bf1e176d51bbc026726b589c701af.jpg',178.26,17,'1985-04-14 03:45:43','1979-01-10 13:25:27'),(27,'Consequatur cupiditate suscipit impedit eligendi.',10,'tmp//1f1588c3f6f2eb49a8d24b721cb7d55f.jpg',0.00,18,'1980-01-15 02:59:49','1978-05-09 18:53:33'),(28,'Omnis et est eos ut laudantium et velit quia.',11,'tmp//9a8a7003621f4fa3b416396ca0934307.jpg',999.99,19,'1985-07-18 20:34:40','2017-08-04 01:33:59'),(29,'Quaerat totam deserunt neque.',12,'tmp//32963fc003ca43d6e823bbb9292ec661.jpg',999.99,20,'2008-08-03 00:39:16','2000-04-29 19:12:14'),(30,'Aut esse eius sit rerum quo.',13,'tmp//c582cbc46590bd1eb137e6a3068abc5c.jpg',999.99,21,'1998-01-20 18:36:26','1986-03-03 07:08:39'),(31,'Quibusdam quae illum perspiciatis quisquam.',14,'tmp//be194ad83c19a950a1ffac3119d9f034.jpg',999.99,22,'1992-03-17 23:02:01','1979-05-14 20:09:50'),(32,'Suscipit voluptatem minima in.',15,'tmp//30573075b80d23152a2585025a3c0c6b.jpg',999.99,23,'1994-01-24 06:12:40','2013-10-28 20:35:16'),(33,'Dolores quia assumenda assumenda et necessitatibus ad.',16,'tmp//4312226cda36a5252e1514757b49410f.jpg',999.99,24,'1986-07-24 14:13:05','2015-08-16 17:01:39'),(34,'Impedit molestiae occaecati soluta neque id.',7,'tmp//d9824a37bdedadb6b69bbc2d73238c3d.jpg',0.38,25,'2013-10-15 21:12:46','2000-04-07 22:33:32'),(35,'Perferendis ipsa est aliquid dolorum.',8,'tmp//423a1c518a92b028b875b6a612188de5.jpg',999.99,26,'1996-11-22 08:04:44','1975-10-21 06:24:32'),(36,'Ad praesentium asperiores voluptatem non.',9,'tmp//f0462b70ba441b4da807d75ce46cbc6c.jpg',999.99,27,'1983-04-16 02:38:29','1994-03-20 22:03:15'),(37,'Veritatis sequi possimus sint repellat pariatur vitae quibusdam.',10,'tmp//746d638b23a4400e3b47a6a9d974aed6.jpg',0.00,28,'2002-10-29 20:31:13','1971-07-15 10:36:44'),(38,'Exercitationem amet vel neque quia sunt.',11,'tmp//3dbd30d4cd00068b972eadba0d00c02c.jpg',999.99,29,'1979-05-06 04:08:41','2009-10-24 20:47:15'),(39,'Et voluptatem adipisci aliquid labore perspiciatis.',12,'tmp//60e25a7bbbad7f2b4ec7e320ce1caaae.jpg',999.99,5,'1986-08-03 08:34:23','1984-03-09 20:10:52'),(40,'Distinctio qui laboriosam ut velit ullam amet.',13,'tmp//3bf840e59d2cab8aa0a303dca227ec4b.jpg',0.00,6,'2011-04-07 12:13:35','1994-06-17 12:36:43'),(41,'Inventore quia nihil quia dolor rerum optio.',14,'tmp//0e6eba581301c90ac681b935573c6543.jpg',999.99,7,'1986-07-07 11:20:03','2014-11-17 14:08:05'),(42,'Porro sed non corrupti perferendis magnam dolores.',15,'tmp//f3d0ea72ba96c9ff004fef7848a1a10d.jpg',999.99,8,'1976-02-26 01:23:33','1970-09-16 18:13:09'),(43,'Velit placeat enim quod dolorem quisquam libero molestiae.',16,'tmp//dff4c7f5ae13cf9e26e27de2b58f0a51.jpg',999.99,9,'2015-07-21 02:49:56','1992-10-25 21:11:17'),(44,'Repellat laboriosam eaque sunt consequatur ipsum.',7,'tmp//4a9548de9265dfff634ec19b5c9f12e9.jpg',2.93,10,'2018-03-19 14:02:20','1973-11-27 19:52:57'),(45,'Tempore quisquam dolor deleniti sed id provident.',8,'tmp//20e6418ee4474f60b066df02633e9af9.jpg',127.19,11,'1993-06-17 08:09:51','1993-10-21 21:48:43'),(46,'Nulla dolorem qui libero ad saepe quidem nihil ducimus.',9,'tmp//b31281e1fa56f9f558796051f3751148.jpg',0.00,12,'1993-09-09 07:55:35','2018-05-28 21:13:44'),(47,'Ut dolorum rem exercitationem dignissimos.',10,'tmp//c2634effd2d154f4f1bb91604d1687ad.jpg',999.99,13,'2012-07-16 09:01:52','1979-06-01 16:16:32'),(48,'Est sequi aut quasi laboriosam molestias.',11,'tmp//58f5aa24df29682d3853daf2b5cfb97c.jpg',22.60,14,'1994-11-19 18:02:06','2016-08-14 16:28:12'),(49,'Ducimus aut alias nostrum rerum eum.',12,'tmp//be823176abb855064297f88a4b605026.jpg',0.00,15,'2003-02-24 07:35:48','1987-09-14 13:02:11'),(50,'Quaerat et ab odit distinctio nihil ut.',13,'tmp//ee4362595500fce7e7e6c2595f741bf9.jpg',0.00,16,'1999-09-29 18:28:41','2003-05-24 15:12:14'),(51,'Quae at adipisci eius voluptatum velit fugit in.',14,'tmp//beaec618ec9e7080224efec647f8c876.jpg',999.99,17,'1989-01-10 12:58:52','1998-05-15 10:47:53'),(52,'Vitae velit esse reprehenderit ex.',15,'tmp//f7c5bd199ba979c532e2c0c951933e5c.jpg',999.99,18,'1989-11-26 02:11:07','2002-10-11 15:17:56'),(53,'Corporis accusamus et sit quo sit ullam error quia.',16,'tmp//5df9a57ccb34bdf9c8d5dbd2139f13e1.jpg',999.99,19,'2008-06-29 16:52:41','1987-09-22 18:26:04'),(54,'Ab et deserunt voluptatem repellendus aliquid.',7,'tmp//2bdcf08a9339cc1f172dbea688069b4e.jpg',958.32,20,'2018-06-02 05:36:20','1973-12-10 19:29:22'),(55,'Sed voluptatem nihil soluta voluptatem officia vitae necessitatibus.',8,'tmp//c7fef217fa9de9159fe9f58f56938f48.jpg',999.99,21,'1998-08-30 17:18:53','1994-08-09 19:18:01'),(56,'Enim voluptatem dolores nemo quasi quae et deserunt.',9,'tmp//ad75dadca9a830f2233f9d12503ced3b.jpg',999.99,22,'1998-01-17 17:11:29','2008-12-10 19:34:59'),(57,'Assumenda nam dolor voluptate iusto autem.',10,'tmp//4751cc474fd7be909407d1e05259b888.jpg',0.00,23,'1983-10-22 20:46:57','2013-10-24 04:44:44'),(58,'Autem blanditiis quisquam blanditiis hic aut.',11,'tmp//ee0b765b34def6dd6c8650d0f574a548.jpg',999.99,24,'1998-09-05 08:51:32','2001-01-18 14:47:09'),(59,'Distinctio ad recusandae et dolorum laborum et ea necessitatibus.',12,'tmp//fa5aba6b42c155bc9c80cf2480f9a335.jpg',1.83,25,'2007-07-11 12:37:22','1999-12-12 00:20:36'),(60,'Iure qui quia cupiditate in quisquam eos eum.',13,'tmp//ac2d04b67ae2cf94b195f8f2729332ff.jpg',999.99,26,'2016-07-04 09:29:02','1984-12-07 09:57:42'),(61,'Delectus excepturi incidunt voluptatem qui excepturi illo iure.',14,'tmp//32fd4c4f1c91e13d2b3b25f89715a8fa.jpg',243.77,27,'2009-04-10 08:13:43','2010-05-03 03:05:02'),(62,'Velit debitis et molestiae quia.',15,'tmp//a6317cecd07469e60bba231bde7801d4.jpg',1.82,28,'2001-07-01 02:49:09','2006-11-18 23:29:24'),(63,'Quis consectetur impedit et ut voluptates perspiciatis rerum.',16,'tmp//16bbc1a780a30ec57211abc8b7d2e2c1.jpg',999.99,29,'1971-04-30 09:19:09','1986-11-21 03:28:09');
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rol_UN` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ADMINISTRADOR'),(2,'USUARIO');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `contrasenia` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `imagen` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `id_Rol` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario_UN` (`nombre`),
  KEY `usuario_FK` (`id_Rol`),
  CONSTRAINT `usuario_FK` FOREIGN KEY (`id_Rol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tabla de usuario';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','https://picsum.photos/id/1/100/100',1),(2,'Benito','123456','https://picsum.photos/id/1/100/100',2),(3,'Carolina','123456','https://picsum.photos/id/1/100/100',2),(4,'Daniel','123456','https://picsum.photos/id/1/100/100',2),(5,'Orlando Spinka','e00c2364cf','tmp//3aeebd936c8b522adc975f1f5501b3f3.jpg',2),(6,'Mr. Waldo Hartmann','5b4dc3ff5d','tmp//f05a95d27ad71cc0b90fadad6c4eb2a2.jpg',2),(7,'Keyon Kshlerin','d32f217335','tmp//c28fc8fe43bcdcf1166c600c12cc6a0d.jpg',2),(8,'Ms. Keara Wintheiser V','bd19625fdf','tmp//4979765728e1c3cd8c35f61e71bfd288.jpg',2),(9,'Destiney Leuschke','2b23ba2547','tmp//6beca8c1a340e8195f7752c8fccebbb0.jpg',2),(10,'Amya Bednar','68021b2455','tmp//8a10b2b20828914af7282a7ecc438d3f.jpg',2),(11,'Monte Oberbrunner Jr.','713d56f6d3','tmp//10eee148d832b5972390d2099d2ef8d1.jpg',2),(12,'Clemens Harris','53959ab4da','tmp//63ecdad6e6d720c09aeccc0800fd4a79.jpg',2),(13,'Mr. Orion Frami IV','4012de5497','tmp//443b493f9fbf024a73c1f15e9c3f4a08.jpg',2),(14,'Prof. Dusty Runolfsson','9275744bb3','tmp//771b3d96a8899c8ec7dc2e09071cc9fd.jpg',2),(15,'Royal Bahringer','fb01ae3c6f','tmp//52f7157e86d1453e01db563278b7cc85.jpg',2),(16,'Prof. Tierra Kemmer','b066c7b7e4','tmp//921525e37446859d43c4cb4b4b188a45.jpg',2),(17,'Dr. Kaden Abshire','9da6519cc8','tmp//12caeef92bade2e3b2d03410b521f703.jpg',2),(18,'Ms. Madilyn Rath','4259f469ee','tmp//9626fd63344c272a3219da338fdcbacb.jpg',2),(19,'Edna O\'Hara','cda288f74d','tmp//3b67328f2290998d97fae6416efc5cf7.jpg',2),(20,'Hector Kihn','a5b2dcaafd','tmp//a990d25ebe2e422b7e70e23bf5a5b37d.jpg',2),(21,'Maeve Breitenberg','855cf48efd','tmp//719f1309052395059fadba753bfb106f.jpg',2),(22,'Harrison Ritchie','5d9146a764','tmp//16d75ae1900376dd404664e96bda3234.jpg',2),(23,'Mrs. Lorna Hayes','ff742f1cdc','tmp//c25577c25d3067f10abcf757cd50d895.jpg',2),(24,'Darby Osinski','b689f50b0a','tmp//b55bd4d1d8e5afced23b6fe15ff3b429.jpg',2),(25,'Brittany Jerde','e733a640b7','tmp//fd08214fd7f21c6a617f48d5ecfbe72a.jpg',2),(26,'Prof. Vergie Lebsack','9ad84cffc7','tmp//c491354b0beb9fa0dcc458e0313ff652.jpg',2),(27,'Ms. Creola Boehm','a58cfc6d00','tmp//fa7e03095075091105b0ca61f8fccbb8.jpg',2),(28,'Prof. Concepcion Casper MD','dd0b49c4e1','tmp//c37515f94bf0a7a979418ee5baf602d2.jpg',2),(29,'Keely Simonis','9adc3fdfb8','tmp//623b52199ade9ea3e34b1566d1973183.jpg',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_usuario_libro`
--

DROP TABLE IF EXISTS `v_usuario_libro`;
/*!50001 DROP VIEW IF EXISTS `v_usuario_libro`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_usuario_libro` AS SELECT 
 1 AS `id_usuario`,
 1 AS `total`,
 1 AS `aprobado`,
 1 AS `pendiente`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `libreria`
--

USE `libreria`;

--
-- Final view structure for view `v_usuario_libro`
--

/*!50001 DROP VIEW IF EXISTS `v_usuario_libro`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`debian-sys-maint`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_usuario_libro` AS select `l`.`id_Usuario` AS `id_usuario`,count(`l`.`id_Usuario`) AS `total`,count(`l`.`fecha_Validacion`) AS `aprobado`,sum(isnull(`l`.`fecha_Validacion`)) AS `pendiente` from `libro` `l` group by `l`.`id_Usuario` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-06  9:04:24
