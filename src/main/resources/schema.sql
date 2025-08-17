-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: meta
-- ------------------------------------------------------
-- Server version	5.7.44-log

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


CREATE USER 'root'@'::1' IDENTIFIED BY 'tu_password';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'::1';
FLUSH PRIVILEGES;

--
-- Table structure for table `agenda`
--

DROP TABLE IF EXISTS `agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `id_vendedor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsu2vmjpcnr9f52tgf5mfp5cyu` (`id_vendedor`),
  CONSTRAINT `FKsu2vmjpcnr9f52tgf5mfp5cyu` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda`
--

LOCK TABLES `agenda` WRITE;
/*!40000 ALTER TABLE `agenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `agenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ajuste_lote`
--

DROP TABLE IF EXISTS `ajuste_lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ajuste_lote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_deposito` bigint(20) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6wefpq36ldlsioxkfps31lgf9` (`id_deposito`),
  KEY `FKpl746laub4tc4yx8ykwd6jryw` (`id_usuario`),
  CONSTRAINT `FK6wefpq36ldlsioxkfps31lgf9` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FKpl746laub4tc4yx8ykwd6jryw` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ajuste_lote`
--

LOCK TABLES `ajuste_lote` WRITE;
/*!40000 ALTER TABLE `ajuste_lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `ajuste_lote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ajuste_precio`
--

DROP TABLE IF EXISTS `ajuste_precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ajuste_precio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `id_item_compra` bigint(20) DEFAULT NULL,
  `id_precio` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5xopl4euluw16trfuxryuckme` (`id_item_compra`),
  KEY `FKpi5amnwboaf9d4qwkj9aiuehc` (`id_precio`),
  CONSTRAINT `FK5xopl4euluw16trfuxryuckme` FOREIGN KEY (`id_item_compra`) REFERENCES `item_compra` (`id`),
  CONSTRAINT `FKpi5amnwboaf9d4qwkj9aiuehc` FOREIGN KEY (`id_precio`) REFERENCES `precio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ajuste_precio`
--

LOCK TABLES `ajuste_precio` WRITE;
/*!40000 ALTER TABLE `ajuste_precio` DISABLE KEYS */;
INSERT INTO `ajuste_precio` VALUES (1,'2024-02-22',NULL,1);
/*!40000 ALTER TABLE `ajuste_precio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ajuste_stock`
--

DROP TABLE IF EXISTS `ajuste_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ajuste_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `confirmado` bit(1) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_deposito` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpy43yngvwqpbahcavmrlqtaf7` (`id_deposito`),
  CONSTRAINT `FKpy43yngvwqpbahcavmrlqtaf7` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ajuste_stock`
--

LOCK TABLES `ajuste_stock` WRITE;
/*!40000 ALTER TABLE `ajuste_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `ajuste_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anticipo_cliente`
--

DROP TABLE IF EXISTS `anticipo_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anticipo_cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `importe` decimal(19,2) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_cuenta` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt2w1j5tf81op1h52j133myxxm` (`id_cliente`),
  KEY `FKgxl5sgkqq0vy6b71ulnkx6v9v` (`id_cuenta`),
  CONSTRAINT `FKgxl5sgkqq0vy6b71ulnkx6v9v` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKt2w1j5tf81op1h52j133myxxm` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anticipo_cliente`
--

LOCK TABLES `anticipo_cliente` WRITE;
/*!40000 ALTER TABLE `anticipo_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `anticipo_cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `anticipo_cliente_aft_insert` AFTER INSERT ON `anticipo_cliente` FOR EACH ROW begin 
	insert into movimiento_saldo_cliente(credito,debito,fecha,id_anticipo_cliente,id_cuenta,id_cliente)
								values(new.importe,0,new.fecha,new.id,new.id_cuenta,new.id_cliente);
	insert into movimiento_caja(credito,debito,fecha,observacion,id_anticipo_cliente,cuenta_id)
						values(new.importe,0,new.fecha,concat('Anticipo de cliente nro: ',new.id),new.id,new.id_cuenta);
	
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `anticipo_cliente_aft_upd` AFTER UPDATE ON `anticipo_cliente` FOR EACH ROW begin 
	delete from movimiento_saldo_cliente where id_anticipo_cliente = new.id;
    delete from movimiento_caja where id_anticipo_cliente = new.id;
    insert into movimiento_saldo_cliente(credito,debito,fecha,id_anticipo_cliente,id_cuenta,id_cliente)
								values(new.importe,0,new.fecha,new.id,new.id_cuenta,new.id_cliente);
	insert into movimiento_caja(credito,debito,fecha,observacion,id_anticipo_cliente,cuenta_id)
						values(new.importe,0,new.fecha,concat('Anticipo de cliente nro: ',new.id),new.id,new.id_cuenta);
	
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `anticipo_cliente_bef_del` BEFORE DELETE ON `anticipo_cliente` FOR EACH ROW begin
	delete from movimiento_saldo_cliente where id_anticipo_cliente = old.id;
    delete from movimiento_caja where id_anticipo_cliente = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `apertura`
--

DROP TABLE IF EXISTS `apertura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apertura` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs45aiy1i1xo5hmp1b4i6lm02` (`id_usuario`),
  CONSTRAINT `FKs45aiy1i1xo5hmp1b4i6lm02` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apertura`
--

LOCK TABLES `apertura` WRITE;
/*!40000 ALTER TABLE `apertura` DISABLE KEYS */;
/*!40000 ALTER TABLE `apertura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditoria`
--

DROP TABLE IF EXISTS `auditoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `precio` varchar(255) DEFAULT NULL,
  `producto` varchar(255) DEFAULT NULL,
  `registro` bigint(20) DEFAULT NULL,
  `tabla` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria`
--

LOCK TABLES `auditoria` WRITE;
/*!40000 ALTER TABLE `auditoria` DISABLE KEYS */;
INSERT INTO `auditoria` VALUES (1,'37926103','2024-07-23','08:05:12',NULL,'PRUEBA',43,'PRODUCTO','INSERT','Carlos'),(2,NULL,'2024-07-23','08:24:13',NULL,NULL,3,'VENTA','INSERT','Carlos'),(3,'37926103','2024-07-23','08:24:13',NULL,'PRUEBA',3,'ITEM_VENTA','INSERT','Carlos'),(4,'738502657','2024-07-23','08:34:37',NULL,'Cream Blush/Rubor en crema 4 tonos',44,'PRODUCTO','INSERT','Carlos'),(5,'738502657','2024-07-23','08:48:39',NULL,'Cream Blush/Rubor en crema 4 tonos',44,'PRODUCTO','UPDATE','Carlos'),(6,'738502657','2024-07-23','08:48:53',NULL,'Cream Blush/Rubor en crema 4 tonos',44,'PRODUCTO','UPDATE','Carlos');
/*!40000 ALTER TABLE `auditoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banco`
--

DROP TABLE IF EXISTS `banco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banco`
--

LOCK TABLES `banco` WRITE;
/*!40000 ALTER TABLE `banco` DISABLE KEYS */;
/*!40000 ALTER TABLE `banco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cajero`
--

DROP TABLE IF EXISTS `cajero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cajero` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `documento` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cajero`
--

LOCK TABLES `cajero` WRITE;
/*!40000 ALTER TABLE `cajero` DISABLE KEYS */;
INSERT INTO `cajero` VALUES (1,'','5301a62d-69e3-4aad-a59b-0e1a5d3bb6e0','Admin'),(2,'','XX','CARLOS');
/*!40000 ALTER TABLE `cajero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clase_grupal`
--

DROP TABLE IF EXISTS `clase_grupal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clase_grupal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora_desde` time NOT NULL,
  `hora_hasta` time NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `id_instructor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7dwo253vn0uj65p8oguk0tb9k` (`id_instructor`),
  CONSTRAINT `FK7dwo253vn0uj65p8oguk0tb9k` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clase_grupal`
--

LOCK TABLES `clase_grupal` WRITE;
/*!40000 ALTER TABLE `clase_grupal` DISABLE KEYS */;
/*!40000 ALTER TABLE `clase_grupal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `saldo` decimal(19,2) DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `documento` varchar(255) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_ultimo_pago` date DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tarifa` decimal(19,2) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `id_precio` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK43hv47abh92jf1yy6bhhhh8ej` (`id_precio`),
  CONSTRAINT `FK43hv47abh92jf1yy6bhhhh8ej` FOREIGN KEY (`id_precio`) REFERENCES `precio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,0.00,'','','575017','2024-02-22',NULL,'','Prueba',0.00,'',1),(3,0.00,'','','xx','2024-07-23',NULL,'','PRUEBA2',0.00,'44',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobro`
--

DROP TABLE IF EXISTS `cobro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cobro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `id_cajero` bigint(20) DEFAULT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_cuenta` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  `id_planilla` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36u7vo5j66h10rw239i0q34ux` (`id_cajero`),
  KEY `FKe3dl2iwi0tntc8k49ioemolps` (`id_cliente`),
  KEY `FK5xn2cxfahlctg10cbc5llm5xo` (`id_cuenta`),
  KEY `FK7epypvr22l5ugcu5ll6302hvu` (`id_usuario`),
  KEY `FKr6xb9yeq10pquuagtttrq4taa` (`id_moneda`),
  KEY `FK88jc25y3a48kr9k9somayu0om` (`id_planilla`),
  CONSTRAINT `FK36u7vo5j66h10rw239i0q34ux` FOREIGN KEY (`id_cajero`) REFERENCES `cajero` (`id`),
  CONSTRAINT `FK5xn2cxfahlctg10cbc5llm5xo` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FK7epypvr22l5ugcu5ll6302hvu` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK88jc25y3a48kr9k9somayu0om` FOREIGN KEY (`id_planilla`) REFERENCES `planilla` (`id`),
  CONSTRAINT `FKe3dl2iwi0tntc8k49ioemolps` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKr6xb9yeq10pquuagtttrq4taa` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobro`
--

LOCK TABLES `cobro` WRITE;
/*!40000 ALTER TABLE `cobro` DISABLE KEYS */;
INSERT INTO `cobro` VALUES (1,'2024-02-22',12000.00,1,1,1,2,NULL,NULL);
/*!40000 ALTER TABLE `cobro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobro_servicio`
--

DROP TABLE IF EXISTS `cobro_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cobro_servicio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `observacion` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `importe` decimal(19,2) NOT NULL,
  `saldo` decimal(19,2) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_cuenta` bigint(20) NOT NULL,
  `id_vendedor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsif3tpe62rxucmgkd3og91fhj` (`id_cliente`),
  KEY `FKc4k454sxi774u9m85ubggnhkk` (`id_cuenta`),
  KEY `FK1nwyy98lee5rbcuqeav0fhp1l` (`id_vendedor`),
  CONSTRAINT `FK1nwyy98lee5rbcuqeav0fhp1l` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`),
  CONSTRAINT `FKc4k454sxi774u9m85ubggnhkk` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKsif3tpe62rxucmgkd3og91fhj` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobro_servicio`
--

LOCK TABLES `cobro_servicio` WRITE;
/*!40000 ALTER TABLE `cobro_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobro_servicio` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cobro_servicio_aft_insert` AFTER INSERT ON `cobro_servicio` FOR EACH ROW begin
	declare v_porcentaje decimal(19,2);
    declare v_credito decimal(19,2);
	insert into 
		movimiento_caja (credito,debito,fecha,observacion,cuenta_id,id_cobro_servicio)
	values
		(new.importe,0,new.fecha,concat('Cobro de servicio nro:',new.id),new.id_cuenta,new.id);
    
    if(new.saldo>0)then
		if(new.importe >0)then
			insert into movimiento_saldo_cliente(credito,debito,fecha,id_cobro_servicio,id_cuenta,id_cliente)
								values(0,new.saldo,new.fecha,new.id,new.id_cuenta,new.id_cliente);
		else
			insert into movimiento_saldo_cliente(credito,debito,fecha,id_cobro_servicio,id_cuenta,id_cliente)
								values(0,new.valor,new.fecha,new.id,new.id_cuenta,new.id_cliente);
        end if;
		
    end if;
	
	select porcentaje into v_porcentaje from vendedor where id = new.id_vendedor;
    set v_credito = ((new.importe*v_porcentaje)/100);
    insert into movimiento_vendedor(credito,debito,fecha,observacion,id_cobro_servicio,id_vendedor)
		values(v_credito,0,new.fecha,concat('Cobro de servicio nro:',new.id),new.id,new.id_vendedor);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cobro_servicio_aft_update` AFTER UPDATE ON `cobro_servicio` FOR EACH ROW begin 
	declare v_porcentaje decimal(19,2);
    declare v_credito decimal(19,2);
    
	delete from movimiento_caja where id_cobro_servicio = new.id;
    delete from movimiento_vendedor where id_cobro_servicio = new.id;
    delete from movimiento_saldo_cliente where id_cobro_servicio = new.id;
  
	insert into 
		movimiento_caja (credito,debito,fecha,observacion,cuenta_id,id_cobro_servicio)
	values
		(new.importe,0,new.fecha,concat('Cobro de servicio nro:',new.id),new.id_cuenta,new.id);
	select porcentaje into v_porcentaje from vendedor where id = new.id_vendedor;
    set v_credito = ((new.importe*v_porcentaje)/100);
    insert into movimiento_vendedor(credito,debito,fecha,observacion,id_cobro_servicio,id_vendedor)
		values(v_credito,0,new.fecha,concat('Cobro de servicio nro:',new.id),new.id,new.id_vendedor);
        
	if(new.saldo>0)then
		if(new.importe >0)then
			insert into movimiento_saldo_cliente(credito,debito,fecha,id_cobro_servicio,id_cuenta,id_cliente)
								values(0,new.saldo,new.fecha,new.id,new.id_cuenta,new.id_cliente);
		else
			insert into movimiento_saldo_cliente(credito,debito,fecha,id_cobro_servicio,id_cuenta,id_cliente)
								values(0,new.valor,new.fecha,new.id,new.id_cuenta,new.id_cliente);
        end if;
		
    end if;
    
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cobro_servicio_bef_del` BEFORE DELETE ON `cobro_servicio` FOR EACH ROW begin 
	delete from movimiento_caja where id_cobro_servicio = old.id;
    delete from movimiento_vendedor where id_cobro_servicio = old.id;
    delete from movimiento_saldo_cliente where id_cobro_servicio =old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `comision_tarjeta`
--

DROP TABLE IF EXISTS `comision_tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comision_tarjeta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `importe` decimal(35,16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comision_tarjeta`
--

LOCK TABLES `comision_tarjeta` WRITE;
/*!40000 ALTER TABLE `comision_tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `comision_tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `factura` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `total` decimal(35,16) DEFAULT NULL,
  `id_deposito` bigint(20) NOT NULL,
  `id_precio` bigint(20) DEFAULT NULL,
  `id_proveedor` bigint(20) NOT NULL,
  `plazo` int(11) DEFAULT NULL,
  `vencimiento` date DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4q0yvjmtkobemc1txgf4mko2c` (`id_deposito`),
  KEY `FKpqqctfk6y53plrr2jpwj8gi3f` (`id_precio`),
  KEY `FKo158ix00ljn91uet4xv15fq7o` (`id_proveedor`),
  KEY `FK8behd8jb4vyeclw9y488oji44` (`id_moneda`),
  CONSTRAINT `FK4q0yvjmtkobemc1txgf4mko2c` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FK8behd8jb4vyeclw9y488oji44` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKo158ix00ljn91uet4xv15fq7o` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`),
  CONSTRAINT `FKpqqctfk6y53plrr2jpwj8gi3f` FOREIGN KEY (`id_precio`) REFERENCES `precio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (2,'001-004-0000686','2024-02-19',6818800.0000000000000000,3,NULL,1,NULL,NULL,NULL),(3,'001-004-0000686','2024-02-19',9240000.0000000000000000,3,NULL,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER compra_aft_insert  AFTER  INSERT ON compra
FOR EACH ROW 
begin 
	insert into cuenta_por_pagar(fecha,vencimiento,importe,importe_pagado,id_compra,id_moneda)values
    (new.fecha,new.vencimiento,new.total,0,new.id,new.id_moneda);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER compra_aft_up  AFTER  UPDATE ON compra
FOR EACH ROW 
begin 
	update cuenta_por_pagar set importe = new.total,vencimiento = new.vencimiento,id_moneda = new.id_moneda where id_compra = new.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `compra_bef_del` BEFORE DELETE ON `compra` FOR EACH ROW begin 
	delete from cuenta_por_pagar where id_compra = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `condicion`
--

DROP TABLE IF EXISTS `condicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `condicion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `condicion_cobro` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `comision` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `condicion`
--

LOCK TABLES `condicion` WRITE;
/*!40000 ALTER TABLE `condicion` DISABLE KEYS */;
INSERT INTO `condicion` VALUES (1,0,'Efectivo',NULL),(2,1,'Tarjeta',NULL),(3,2,'Transferencia',NULL);
/*!40000 ALTER TABLE `condicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consorcio`
--

DROP TABLE IF EXISTS `consorcio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consorcio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_fin` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `meses` int(11) DEFAULT NULL,
  `monto_mensual` decimal(19,2) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `terminado` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consorcio`
--

LOCK TABLES `consorcio` WRITE;
/*!40000 ALTER TABLE `consorcio` DISABLE KEYS */;
/*!40000 ALTER TABLE `consorcio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cotizacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dividir` bit(1) NOT NULL,
  `fecha` date NOT NULL,
  `multiplicar` bit(1) NOT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `id_moneda_destino` bigint(20) NOT NULL,
  `id_moneda_origen` bigint(20) NOT NULL,
  `modificado` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKninkgjcohq0kg5ljddlgggckg` (`id_moneda_destino`),
  KEY `FK5bu6folvvke840m90tbnm8v1d` (`id_moneda_origen`),
  CONSTRAINT `FK5bu6folvvke840m90tbnm8v1d` FOREIGN KEY (`id_moneda_origen`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKninkgjcohq0kg5ljddlgggckg` FOREIGN KEY (`id_moneda_destino`) REFERENCES `moneda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1307 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `caja_cobranza` bit(1) NOT NULL,
  `identificador` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `saldo` decimal(19,2) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  `id_timbrado` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4k0q1rpmxbvg5blt32h7x5yf` (`id_moneda`),
  KEY `FK41rrtt88su7556icgblyl93yd` (`id_timbrado`),
  CONSTRAINT `FK41rrtt88su7556icgblyl93yd` FOREIGN KEY (`id_timbrado`) REFERENCES `timbrado` (`id`),
  CONSTRAINT `FKt4k0q1rpmxbvg5blt32h7x5yf` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (1,'','','','Caja',12000.00,1,NULL),(2,'','','','CAJA SUCURSAL',0.00,NULL,NULL);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_por_cobrar`
--

DROP TABLE IF EXISTS `cuenta_por_cobrar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_por_cobrar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `importe` decimal(19,2) DEFAULT NULL,
  `importe_cobrado` decimal(19,2) DEFAULT NULL,
  `id_venta` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK502tfi0maax9uqvfji8oyk3jm` (`id_venta`),
  KEY `FK9l59cth5kn6cbe913076pjqfu` (`id_moneda`),
  CONSTRAINT `FK502tfi0maax9uqvfji8oyk3jm` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id`),
  CONSTRAINT `FK9l59cth5kn6cbe913076pjqfu` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_por_cobrar`
--

LOCK TABLES `cuenta_por_cobrar` WRITE;
/*!40000 ALTER TABLE `cuenta_por_cobrar` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_por_cobrar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_por_pagar`
--

DROP TABLE IF EXISTS `cuenta_por_pagar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_por_pagar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `importe` decimal(19,2) DEFAULT NULL,
  `importe_pagado` decimal(19,2) DEFAULT NULL,
  `id_compra` bigint(20) DEFAULT NULL,
  `vencimiento` date DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf10aw9jp18xuq13nri3et3dr0` (`id_compra`),
  KEY `FK2fjkflkjo43iubckstp60gfeq` (`id_moneda`),
  CONSTRAINT `FK2fjkflkjo43iubckstp60gfeq` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKf10aw9jp18xuq13nri3et3dr0` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_por_pagar`
--

LOCK TABLES `cuenta_por_pagar` WRITE;
/*!40000 ALTER TABLE `cuenta_por_pagar` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_por_pagar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposito`
--

DROP TABLE IF EXISTS `deposito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deposito` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `stock_negativo` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposito`
--

LOCK TABLES `deposito` WRITE;
/*!40000 ALTER TABLE `deposito` DISABLE KEYS */;
INSERT INTO `deposito` VALUES (3,'','CENTRAL',''),(4,'','D\'hermosa','\0'),(5,'','SUCURSAL','');
/*!40000 ALTER TABLE `deposito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_type` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nombre_corto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extracto_producto`
--

DROP TABLE IF EXISTS `extracto_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extracto_producto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entrada` decimal(19,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_detalle` bigint(20) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `registro` bigint(20) DEFAULT NULL,
  `salida` decimal(19,2) DEFAULT NULL,
  `id_deposito` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK29dg1p7vqic0srqhtgnmqu99g` (`id_deposito`),
  KEY `FK5a8i23cixbbwxw988uv5uuaqj` (`id_producto`),
  CONSTRAINT `FK29dg1p7vqic0srqhtgnmqu99g` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FK5a8i23cixbbwxw988uv5uuaqj` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extracto_producto`
--

LOCK TABLES `extracto_producto` WRITE;
/*!40000 ALTER TABLE `extracto_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `extracto_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gasto`
--

DROP TABLE IF EXISTS `gasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gasto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `factura` varchar(255) DEFAULT NULL,
  `fecha` date NOT NULL,
  `importe` decimal(19,2) NOT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `cuenta_id` bigint(20) NOT NULL,
  `proveedor_id` bigint(20) NOT NULL,
  `id_condicion` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  `id_planilla` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgabfs28m4jmp2m3devp98j94p` (`cuenta_id`),
  KEY `FK7o2u0decsi5akhp2jmecbo3d6` (`proveedor_id`),
  KEY `FKtelumb58xbj93vjdfudmy9cux` (`id_condicion`),
  KEY `FKbpchkab5sbvv9894ptrn4s6ju` (`id_moneda`),
  KEY `FKbc4bnml77jnobfbomm0covv3s` (`id_planilla`),
  CONSTRAINT `FK7o2u0decsi5akhp2jmecbo3d6` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`),
  CONSTRAINT `FKbc4bnml77jnobfbomm0covv3s` FOREIGN KEY (`id_planilla`) REFERENCES `planilla` (`id`),
  CONSTRAINT `FKbpchkab5sbvv9894ptrn4s6ju` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKgabfs28m4jmp2m3devp98j94p` FOREIGN KEY (`cuenta_id`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKtelumb58xbj93vjdfudmy9cux` FOREIGN KEY (`id_condicion`) REFERENCES `condicion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gasto`
--

LOCK TABLES `gasto` WRITE;
/*!40000 ALTER TABLE `gasto` DISABLE KEYS */;
/*!40000 ALTER TABLE `gasto` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER gasto_bef_ins  before  INSERT ON gasto
FOR EACH ROW 
begin
	declare v_id_planilla bigint;
    select id into v_id_planilla from planilla where id_cuenta=new.cuenta_id and fecha = new.fecha and cerrado = false;
    if(v_id_planilla is not null) then
		set new.id_planilla = v_id_planilla;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `gasto_af_ins` AFTER INSERT ON `gasto` FOR EACH ROW begin
	insert into movimiento_caja (fecha,observacion,cuenta_id,credito,debito,gasto_id)values(new.fecha,'gasto',new.cuenta_id,0,new.importe,new.id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER gasto_bef_upd  before  UPDATE ON gasto
FOR EACH ROW 
begin 
declare v_id_planilla bigint;
	set new.id_planilla = null;
    select id into v_id_planilla from planilla where id_cuenta=new.cuenta_id and fecha = new.fecha and cerrado = false;
    if(v_id_planilla is not null) then
		set new.id_planilla = v_id_planilla;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `gasto_af_upd` AFTER UPDATE ON `gasto` FOR EACH ROW begin 
	delete from movimiento_caja where gasto_id = new.id;
    insert into movimiento_caja (fecha,observacion,cuenta_id,gasto_id,credito,debito)values(new.fecha,'gasto',new.cuenta_id,new.id,0,new.importe);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `gasto_bef_del` BEFORE DELETE ON `gasto` FOR EACH ROW begin 
	delete from movimiento_caja where gasto_id = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'Administradores'),(2,'Administracion'),(3,'SUCURSAL');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_permiso`
--

DROP TABLE IF EXISTS `grupo_permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo_permiso` (
  `id_grupo` bigint(20) NOT NULL,
  `id_permiso` bigint(20) NOT NULL,
  KEY `FKa0gybcyjxiqiuqx0ge0im65i2` (`id_permiso`),
  KEY `FKauh6pcsqd8s86o6odi3v95unj` (`id_grupo`),
  CONSTRAINT `FKa0gybcyjxiqiuqx0ge0im65i2` FOREIGN KEY (`id_permiso`) REFERENCES `permiso` (`id`),
  CONSTRAINT `FKauh6pcsqd8s86o6odi3v95unj` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_permiso`
--

LOCK TABLES `grupo_permiso` WRITE;
/*!40000 ALTER TABLE `grupo_permiso` DISABLE KEYS */;
INSERT INTO `grupo_permiso` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(1,46),(1,47),(1,48),(1,49),(1,50),(1,51),(1,52),(1,53),(1,54),(1,55),(1,56),(1,57),(1,58),(1,59),(1,60),(1,61),(1,62),(1,63),(1,64),(1,65),(1,66),(1,67),(1,68),(1,69),(1,70),(1,71),(1,72),(1,73),(1,74),(1,75),(1,76),(1,77),(1,78),(1,79),(1,80),(1,81),(1,82),(1,83),(1,84),(1,85),(1,86),(1,87),(1,88),(1,89),(1,90),(1,91),(1,92),(1,93),(1,94),(1,95),(1,96),(1,97),(1,98),(1,99),(1,100),(1,101),(1,102),(1,103),(1,104),(1,105),(1,106),(1,107),(1,108),(1,109),(1,110),(1,111),(1,112),(1,113),(1,114),(1,115),(1,116),(1,117),(1,118),(1,119),(1,120),(1,121),(1,122),(1,123),(1,124),(1,125),(1,126),(1,127),(1,128),(1,129),(1,130),(1,131),(1,132),(1,133),(1,134),(1,135),(1,136),(1,137),(1,138),(1,139),(1,140),(1,141),(1,142),(1,143),(1,144),(1,145),(1,146),(1,147),(1,148),(1,149),(1,150),(1,151),(1,152),(1,153),(1,154),(1,155),(1,156),(1,157),(1,158),(1,159),(1,160),(1,161),(1,162),(1,163),(1,164),(1,165),(1,166),(1,167),(1,168),(1,169),(1,170),(1,172),(1,173),(1,174),(1,175),(1,176),(1,177),(1,178),(1,179),(1,180),(1,181),(1,182),(1,183),(1,184),(1,185),(1,186),(1,187),(1,188),(1,189),(1,190),(1,191),(1,192),(1,193),(1,194),(1,195),(1,196),(1,197),(1,198),(1,199),(1,200),(1,201),(1,202),(1,203),(1,204),(2,11),(2,12),(2,17),(2,19),(2,20),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,42),(2,43),(2,44),(2,45),(2,46),(2,47),(2,48),(2,49),(2,50),(2,51),(2,52),(2,53),(2,54),(2,55),(2,56),(2,57),(2,58),(2,59),(2,60),(2,61),(2,62),(2,63),(2,64),(2,65),(2,66),(2,67),(2,68),(2,69),(2,74),(2,75),(2,76),(2,77),(2,78),(2,79),(2,80),(2,81),(2,86),(2,88),(2,90),(2,91),(2,92),(2,93),(2,94),(2,95),(2,96),(2,97),(2,98),(2,99),(2,100),(2,101),(2,102),(2,103),(2,104),(2,105),(2,110),(2,112),(2,114),(2,115),(2,116),(2,117),(2,118),(2,119),(2,120),(2,121),(2,122),(2,123),(2,128),(2,129),(2,130),(2,131),(2,132),(2,133),(2,134),(2,135),(2,136),(2,137),(2,138),(2,139),(2,140),(2,141),(2,142),(2,143),(2,144),(2,145),(2,146),(2,147),(2,148),(2,149),(2,150),(2,151),(2,152),(2,153),(2,154),(2,156),(2,157),(2,158),(2,159),(2,160),(2,161),(2,162),(2,163),(2,164),(2,165),(2,168),(2,169),(2,170),(2,171),(2,177),(2,178),(2,179),(3,34),(3,35),(3,36),(3,37),(3,43),(3,47),(3,52),(3,98),(3,99),(3,110),(3,111),(3,114),(3,115),(3,116),(3,119),(3,128),(3,129),(3,130),(3,136),(3,137),(3,144),(3,145),(3,146),(3,149),(3,150),(3,153),(3,172),(3,173),(3,177),(3,189),(3,197);
/*!40000 ALTER TABLE `grupo_permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `documento` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `porcentaje` decimal(19,2) DEFAULT NULL,
  `saldo` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_agenda`
--

DROP TABLE IF EXISTS `item_agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_agenda` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` int(11) DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_agenda` bigint(20) DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3mxcdjt32yy19s0rig0s6lcbu` (`id_agenda`),
  KEY `FKnm5fc5wdj573m7xwumc6igxhy` (`id_cliente`),
  CONSTRAINT `FK3mxcdjt32yy19s0rig0s6lcbu` FOREIGN KEY (`id_agenda`) REFERENCES `agenda` (`id`),
  CONSTRAINT `FKnm5fc5wdj573m7xwumc6igxhy` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_agenda`
--

LOCK TABLES `item_agenda` WRITE;
/*!40000 ALTER TABLE `item_agenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_agenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_ajuste_lote`
--

DROP TABLE IF EXISTS `item_ajuste_lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_ajuste_lote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `nro_lote` varchar(255) DEFAULT NULL,
  `vencimiento` date DEFAULT NULL,
  `id_ajuste_lote` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrdsxgkej5uewvy1mpjcsw67u4` (`id_ajuste_lote`),
  KEY `FKjp2fu5kfgaswk2ma1sqku6v7u` (`id_producto`),
  CONSTRAINT `FKjp2fu5kfgaswk2ma1sqku6v7u` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKrdsxgkej5uewvy1mpjcsw67u4` FOREIGN KEY (`id_ajuste_lote`) REFERENCES `ajuste_lote` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_ajuste_lote`
--

LOCK TABLES `item_ajuste_lote` WRITE;
/*!40000 ALTER TABLE `item_ajuste_lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_ajuste_lote` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger item_ajuste_lote_aft_insert after insert on item_ajuste_lote 
for each row
begin 
declare v_entrada decimal(19,2) default 0 ;
declare v_salida decimal(19,2) default 0;
declare v_fecha date;
declare v_deposito bigint;

select id_deposito,fecha into v_deposito,v_fecha from ajuste_lote where id = new.id_ajuste_lote;
if(new.cantidad<0)then
	set v_salida = new.cantidad *-1;
else
	set v_entrada = new.cantidad;
end if;


insert into movimiento_lote (entrada,fecha,observacion,registro,salida,vencimiento,id_item_ajuste_lote,id_producto,id_deposito,nro_lote)
				values(v_entrada,v_fecha,'Ajuste de lotes',new.id_ajuste_lote,v_salida,new.vencimiento,new.id,new.id_producto,v_deposito,new.nro_lote);

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger item_ajuste_lote_bef_del before delete on item_ajuste_lote
for each row
begin 
delete from movimiento_lote where id_item_ajuste_lote = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_ajuste_precio`
--

DROP TABLE IF EXISTS `item_ajuste_precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_ajuste_precio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `costo` decimal(19,2) DEFAULT NULL,
  `precio_minimo` decimal(19,2) DEFAULT NULL,
  `precio_producto` decimal(19,2) DEFAULT NULL,
  `utilidad` decimal(19,2) DEFAULT NULL,
  `id_ajuste_precio` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeit19r5tbvwbfci9n1uc5n82n` (`id_ajuste_precio`),
  KEY `FKqomm0wnq5pdc0898hjdyybdff` (`id_producto`),
  CONSTRAINT `FKeit19r5tbvwbfci9n1uc5n82n` FOREIGN KEY (`id_ajuste_precio`) REFERENCES `ajuste_precio` (`id`),
  CONSTRAINT `FKqomm0wnq5pdc0898hjdyybdff` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_ajuste_precio`
--

LOCK TABLES `item_ajuste_precio` WRITE;
/*!40000 ALTER TABLE `item_ajuste_precio` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_ajuste_precio` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_ajuste_precio_aft_ins` AFTER INSERT ON `item_ajuste_precio` FOR EACH ROW begin 
	declare v_id_precio bigint;
    declare v_id_producto bigint;
    select id_precio into v_id_precio from ajuste_precio where id= new.id_ajuste_precio;
    select count(id_producto) into v_id_producto from item_precio where id_precio = v_id_precio and id_producto = new.id_producto;
    if(v_id_producto =0)then
		insert into item_precio(costo,precio_producto,id_precio,id_producto,precio_minimo)
			values(new.costo,new.precio_producto,v_id_precio,new.id_producto,new.precio_minimo);
    else 
		update item_precio set precio_producto = new.precio_producto,precio_minimo = new.precio_minimo ,costo = new.costo
		where id_producto = new.id_producto and id_precio = v_id_precio;
    end if;
	
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_ajuste_precio_af_del` AFTER DELETE ON `item_ajuste_precio` FOR EACH ROW begin 
	declare v_id_ajuste_precio bigint;
    declare v_id_precio bigint;
    declare v_costo_producto decimal(19,2);
    declare v_costo decimal(19,2);
    declare v_precio_producto decimal(19,2);
    
    select id_precio into v_id_precio from ajuste_precio where id = old.id_ajuste_precio;
    select ifnull(max(id_ajuste_precio),0) into v_id_ajuste_precio from ajuste_precio 
    join item_ajuste_precio on ajuste_precio.id = item_ajuste_precio.id_ajuste_precio
	where ajuste_precio.id_precio = v_id_precio and item_ajuste_precio.id_producto = old.id_producto;
    
    if(v_id_ajuste_precio =0)then
		select costo into v_costo_producto from producto where id = old.id_producto;
		update item_precio set costo = v_costo_producto , precio_producto = 0 where id_producto = old.id_producto;
	else
		select costo,precio_producto into v_costo,v_precio_producto from item_ajuste_precio where id_ajuste_precio = v_id_ajuste_precio and id_producto = old.id_producto;
        update item_precio set costo = v_costo, precio_producto = v_precio_producto where id_producto = old.id_producto;
    end if;
    
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_ajuste_stock`
--

DROP TABLE IF EXISTS `item_ajuste_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_ajuste_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `restar` decimal(19,2) DEFAULT NULL,
  `sumar` decimal(19,2) DEFAULT NULL,
  `id_ajuste_stock` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq50249tmasgst9jxk1xfaio3k` (`id_ajuste_stock`),
  KEY `FK7qju2842iuc2eo327k87l6kg8` (`id_producto`),
  CONSTRAINT `FK7qju2842iuc2eo327k87l6kg8` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKq50249tmasgst9jxk1xfaio3k` FOREIGN KEY (`id_ajuste_stock`) REFERENCES `ajuste_stock` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_ajuste_stock`
--

LOCK TABLES `item_ajuste_stock` WRITE;
/*!40000 ALTER TABLE `item_ajuste_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_ajuste_stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER item_ajuste_stock_aft_insert  AFTER  INSERT ON item_ajuste_stock
FOR EACH ROW 
begin 
	declare v_deposito bigint;
    declare v_confirmado bit(1);
    declare v_fecha date;
    declare v_existe int;
    select id_deposito,confirmado,fecha into v_deposito,v_confirmado,v_fecha from ajuste_stock where id = new.id_ajuste_stock;
	select count(id_producto) into v_existe from stock_deposito where id_producto = new.id_producto and id_deposito = v_deposito;
		if(new.sumar>0  and new.restar =0) then 
			if(v_existe>0) then
			update stock_deposito set cantidad = f_cantidad_stock(new.id_producto, v_deposito)  where id_producto = new.id_producto and id_deposito = v_deposito;
            else
				insert into stock_deposito (cantidad,reserva,id_deposito,id_producto)values(f_cantidad_stock(new.id_producto, v_deposito),0,v_deposito,new.id_producto);
            end if;
            insert into extracto_producto(entrada,fecha,id_detalle,observacion,registro,salida,id_deposito,id_producto)
				values(new.sumar,v_fecha,new.id,'Ajuste de stock',new.id_ajuste_stock,0,v_deposito,new.id_producto);
		end if;
		if(new.restar >0  and new.sumar =0)then
			if(v_existe>0)then
				update stock_deposito set cantidad = f_cantidad_stock(new.id_producto, v_deposito)  
                where id_producto = new.id_producto and id_deposito = v_deposito;
            else
				insert into stock_deposito (cantidad,reserva,id_deposito,id_producto)values(f_cantidad_stock(new.id_producto, v_deposito),0,v_deposito,new.id_producto);
            end if;
            insert into extracto_producto(entrada,fecha,id_detalle,observacion,registro,salida,id_deposito,id_producto)
				values(0,v_fecha,new.id,'Ajuste de stock',new.id_ajuste_stock,new.restar,v_deposito,new.id_producto);
		end if;
    
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_ajuste_stock_bef_del` BEFORE DELETE ON `item_ajuste_stock` FOR EACH ROW begin 
	delete from extracto_producto where id_detalle = old.id and observacion = 'Ajuste de stock' and id_producto = old.id_producto;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_ajuste_stock_aft_del` AFTER DELETE ON `item_ajuste_stock` FOR EACH ROW begin
	declare v_deposito bigint;
    declare v_confirmado bit(1);
    select id_deposito into v_deposito from ajuste_stock where id = old.id_ajuste_stock;
    select confirmado into v_confirmado from ajuste_stock where id = old.id_ajuste_stock;
    
		
		if(old.sumar>0  and old.restar =0) then 
			update stock_deposito set cantidad = f_cantidad_stock(old.id_producto, v_deposito) where id_producto = old.id_producto and id_deposito = v_deposito;
		end if;
		if(old.restar >0  and old.sumar =0)then
			update stock_deposito set cantidad = f_cantidad_stock(old.id_producto, v_deposito) where id_producto = old.id_producto and id_deposito = v_deposito;
		end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_apertura`
--

DROP TABLE IF EXISTS `item_apertura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_apertura` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `importe` decimal(19,2) DEFAULT NULL,
  `id_apertura` bigint(20) DEFAULT NULL,
  `id_condicion` bigint(20) DEFAULT NULL,
  `id_cuenta` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqf0lmpboy70x8kjrh5ltfwyd0` (`id_apertura`),
  KEY `FK32e360ufbcl9cyni9ug0sysmn` (`id_condicion`),
  KEY `FKcuciv8amvv4n2v1ttwsh2f2og` (`id_cuenta`),
  KEY `FK6v2axq1f1ke42fao7ib01yckw` (`id_moneda`),
  CONSTRAINT `FK32e360ufbcl9cyni9ug0sysmn` FOREIGN KEY (`id_condicion`) REFERENCES `condicion` (`id`),
  CONSTRAINT `FK6v2axq1f1ke42fao7ib01yckw` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKcuciv8amvv4n2v1ttwsh2f2og` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKqf0lmpboy70x8kjrh5ltfwyd0` FOREIGN KEY (`id_apertura`) REFERENCES `apertura` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_apertura`
--

LOCK TABLES `item_apertura` WRITE;
/*!40000 ALTER TABLE `item_apertura` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_apertura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_clase_grupal`
--

DROP TABLE IF EXISTS `item_clase_grupal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_clase_grupal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_clase_grupal` bigint(20) DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2nj3ohnkjdvgp6k6nf1mg4iv0` (`id_clase_grupal`),
  KEY `FKaio9oln1upod9tstqpi7k8c3h` (`id_cliente`),
  CONSTRAINT `FK2nj3ohnkjdvgp6k6nf1mg4iv0` FOREIGN KEY (`id_clase_grupal`) REFERENCES `clase_grupal` (`id`),
  CONSTRAINT `FKaio9oln1upod9tstqpi7k8c3h` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_clase_grupal`
--

LOCK TABLES `item_clase_grupal` WRITE;
/*!40000 ALTER TABLE `item_clase_grupal` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_clase_grupal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_cobro`
--

DROP TABLE IF EXISTS `item_cobro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_cobro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `importe` decimal(19,2) DEFAULT NULL,
  `id_cobro` bigint(20) DEFAULT NULL,
  `id_venta` bigint(20) DEFAULT NULL,
  `id_comision_tarjeta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlkjqb1lihhc80et1r6oct2gr5` (`id_cobro`),
  KEY `FK2m15mrmeq7ke9ugnx7bl5ld0f` (`id_venta`),
  KEY `FK64pidq1rbu84s9f7288378e7r` (`id_comision_tarjeta`),
  CONSTRAINT `FK2m15mrmeq7ke9ugnx7bl5ld0f` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id`),
  CONSTRAINT `FK64pidq1rbu84s9f7288378e7r` FOREIGN KEY (`id_comision_tarjeta`) REFERENCES `comision_tarjeta` (`id`),
  CONSTRAINT `FKlkjqb1lihhc80et1r6oct2gr5` FOREIGN KEY (`id_cobro`) REFERENCES `cobro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_cobro`
--

LOCK TABLES `item_cobro` WRITE;
/*!40000 ALTER TABLE `item_cobro` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_cobro` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`::1`*/ /*!50003 TRIGGER item_cobro_aft_insert  AFTER  INSERT ON item_cobro
FOR EACH ROW 
begin
	declare v_cuenta bigint;
    declare v_timbrado bigint;
    declare v_numeracion_ini int;
    declare v_factura bit(1);
    declare v_nro_factura  int;
    declare v_factura_venta int;
    
    select id_cuenta into v_cuenta from cobro where id = new.id_cobro;
    select id_timbrado into v_timbrado from cuenta where id = v_cuenta;
    select numeracion_ini into v_numeracion_ini from timbrado where id = v_timbrado;
    select factura into v_factura from venta where id = new.id_venta and condicion_venta = 0;
    select ifnull(nro_factura,0) into v_factura_venta from venta where id = new.id_venta;

    if((v_factura = true) and (v_factura_venta = 0))then
		select ifnull(max(nro_factura),0) into v_nro_factura from venta where id_timbrado = v_timbrado;
        if(v_nro_factura =0) then
			update venta set id_timbrado = v_timbrado, nro_factura = v_numeracion_ini where id = new.id_venta;
        else
			update venta set id_timbrado = v_timbrado , nro_factura = v_nro_factura+1 where id = new.id_venta;
        end if;
    end if;
	update cuenta_por_cobrar set importe_cobrado = importe_cobrado+new.importe where id_venta = new.id_venta;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_cobro_aft_del` AFTER DELETE ON `item_cobro` FOR EACH ROW begin 
	update cuenta_por_cobrar set importe_cobrado = importe_cobrado - old.importe where id_venta = old.id_venta;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_cobro_importe`
--

DROP TABLE IF EXISTS `item_cobro_importe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_cobro_importe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boleta` varchar(255) DEFAULT NULL,
  `cotizacion_vuelto` decimal(35,16) DEFAULT NULL,
  `importe` decimal(35,16) DEFAULT NULL,
  `importe_cobrado` decimal(35,16) DEFAULT NULL,
  `importems` decimal(35,16) DEFAULT NULL,
  `valor_cotizacion` decimal(35,16) DEFAULT NULL,
  `vuelto` decimal(35,16) DEFAULT NULL,
  `vueltoms` decimal(35,16) DEFAULT NULL,
  `id_banco` bigint(20) DEFAULT NULL,
  `id_cobro` bigint(20) DEFAULT NULL,
  `id_condicion` bigint(20) DEFAULT NULL,
  `id_cuenta` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  `id_moneda_vuelto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4imqjv09avn0u5cfvv7at2tqy` (`id_banco`),
  KEY `FKrr1i77m1ryn4nrqfvlpiswi9l` (`id_cobro`),
  KEY `FK1aejry1w15qv9xmkhdihjpqty` (`id_condicion`),
  KEY `FK8pr07usxdkdge82i9u2p8dwlj` (`id_cuenta`),
  KEY `FKmxsiwodp6cdkveqrj90u2wwsv` (`id_moneda`),
  KEY `FK11dfg8ilu0qtqugmgr2q2svmx` (`id_moneda_vuelto`),
  CONSTRAINT `FK11dfg8ilu0qtqugmgr2q2svmx` FOREIGN KEY (`id_moneda_vuelto`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FK1aejry1w15qv9xmkhdihjpqty` FOREIGN KEY (`id_condicion`) REFERENCES `condicion` (`id`),
  CONSTRAINT `FK4imqjv09avn0u5cfvv7at2tqy` FOREIGN KEY (`id_banco`) REFERENCES `banco` (`id`),
  CONSTRAINT `FK8pr07usxdkdge82i9u2p8dwlj` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKmxsiwodp6cdkveqrj90u2wwsv` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKrr1i77m1ryn4nrqfvlpiswi9l` FOREIGN KEY (`id_cobro`) REFERENCES `cobro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_cobro_importe`
--

LOCK TABLES `item_cobro_importe` WRITE;
/*!40000 ALTER TABLE `item_cobro_importe` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_cobro_importe` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`::1`*/ /*!50003 TRIGGER item_cobro_importe_aft_insert  AFTER  INSERT ON item_cobro_importe
FOR EACH ROW 
begin 
	declare v_fecha date;
    declare v_id_moneda_cuenta bigint;
    declare v_credito decimal(35,16);
    declare v_cliente varchar(255);
    declare v_vuelto_cotizado decimal(35,16);
    
    select id_moneda into v_id_moneda_cuenta from cuenta where id = new.id_cuenta;
    select fecha into v_fecha from cobro where id = new.id_cobro;
    if((v_id_moneda_cuenta<>new.id_moneda) and (new.id_moneda_vuelto <>new.id_moneda)) then 
		/*convertir el valor_cotizacion en el valor de la moneda del importe*/
		set v_vuelto_cotizado = f_cotizar(new.id_moneda_vuelto,new.id_moneda,v_fecha,new.vuelto,0);
        /*sacar la diferencia con el importe y el vuelto con la moneda del importe*/
        set v_credito = (f_cotizar(new.id_moneda,v_id_moneda_cuenta,v_fecha,new.importe_cobrado,0))-
					(f_cotizar(new.id_moneda,v_id_moneda_cuenta,v_fecha,v_vuelto_cotizado,0));
    else
		/*si la moneda del vuelto es igual a la moneda del importe o la moneda del importe es igual a la moneda de la cuenta*/
		set v_credito = (f_cotizar(new.id_moneda,v_id_moneda_cuenta,v_fecha,new.importe_cobrado,0))-
						(f_cotizar(new.id_moneda_vuelto,v_id_moneda_cuenta,v_fecha,new.vuelto,0));
	end if;
	select cliente.nombre into v_cliente from cobro left join cliente on cobro.id_cliente = cliente.id
		where cobro.id = new.id_cobro;
	insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_item_cobro_importe,id_moneda_cuenta, id_moneda_cobro,valor_cotizacion)
		values(v_credito,0,v_fecha,concat('cobro nro:',new.id_cobro,' Cliente: ',v_cliente),new.id_cuenta,new.id,v_id_moneda_cuenta,new.id_moneda,new.valor_cotizacion);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_cobro_importe_bef_del` BEFORE DELETE ON `item_cobro_importe` FOR EACH ROW begin 
	delete from movimiento_caja where id_item_cobro_importe =old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_compra`
--

DROP TABLE IF EXISTS `item_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `precio` decimal(35,16) DEFAULT NULL,
  `precio_venta` decimal(35,16) DEFAULT NULL,
  `id_compra` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn8wdo52uyexk2b61mf8p2xxby` (`id_compra`),
  KEY `FK79f2nvgou6yv1vepy6txvo3cr` (`id_producto`),
  CONSTRAINT `FK79f2nvgou6yv1vepy6txvo3cr` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKn8wdo52uyexk2b61mf8p2xxby` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_compra`
--

LOCK TABLES `item_compra` WRITE;
/*!40000 ALTER TABLE `item_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_compra` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_compra_bef_ins` BEFORE INSERT ON `item_compra` FOR EACH ROW begin 
	
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER item_compra_af_ins  AFTER  INSERT ON item_compra
FOR EACH ROW 
begin 
	declare v_deposito bigint;
    declare v_contador_cantidad decimal(19,2);
    declare v_fecha_compra date;
    declare v_total_cantidad decimal(19,2);
    declare v_sumatoria_actual decimal(19,2);
    declare v_costo_actual decimal (19,2);
    declare v_costo_calculo int;
	declare v_sumatoria_entrante decimal(19,2);
    declare v_cantidad decimal(19,2);
    declare v_sumatoria decimal(19,2);
    declare v_costo_promedio decimal(19,2);
    declare v_fecha date;
    declare max_id_seguimiento_costo bigint;
    declare v_id_precio_venta_compra bigint;
	declare v_id_ajuste_precio bigint;
    declare v_costo_para_ajuste decimal(35,16);
	declare v_id_extracto_producto_compra bigint;
    declare v_id_extracto_producto_venta bigint;
    declare v_new_id_venta bigint;
	declare v_id_venta bigint;
    declare v_moneda_compra bigint;
    declare v_moneda_producto bigint;
    declare v_costo_producto_cotizado decimal(35,16);
    
    
    select id_deposito,id_moneda,fecha into v_deposito,v_moneda_compra,v_fecha_compra from compra where id = new.id_compra;
    select id_moneda into v_moneda_producto from producto  where id = new.id_producto;
    select count(id) into v_contador_cantidad from stock_deposito where id_producto =new.id_producto and id_deposito =v_deposito;
    set v_costo_producto_cotizado = f_cotizar(v_moneda_compra, v_moneda_producto, v_fecha_compra, new.precio, 0);
    if(v_contador_cantidad=0)then
		insert into stock_deposito values(0,new.cantidad,0,v_deposito,new.id_producto);
	else
		update stock_deposito set cantidad = cantidad+new.cantidad where id_deposito = v_deposito and id_producto = new.id_producto;
    end if;
   insert into extracto_producto(entrada,fecha,id_detalle,observacion,registro,salida,id_deposito,id_producto)
				values(new.cantidad,v_fecha_compra,new.id,'Compra',new.id_compra,0,v_deposito,new.id_producto);
                

select costo_calculo into v_costo_calculo from producto where id = new.id_producto;
    select fecha into v_fecha from compra where id = new.id_compra;
   
		select max(venta.id) into v_id_venta from venta join item_venta on venta.id=item_venta.id_venta 
												where fecha <= v_fecha and item_venta.id_producto = new.id_producto;
                                                
        select max(id) into v_id_extracto_producto_compra from extracto_producto where observacion = 'compra' and registro = new.id_compra
					and id_producto = new.id_producto;
		select id into v_id_extracto_producto_venta from extracto_producto where observacion = 'venta' and registro = v_id_venta
			and id_producto = new.id_producto;                                        
		
        while v_id_extracto_producto_venta>v_id_extracto_producto_compra do
			set v_new_id_venta = v_id_venta;
			select max(venta.id) into v_id_venta from venta join item_venta on venta.id=item_venta.id_venta 
										where fecha <= v_fecha and item_venta.id_producto = new.id_producto and venta.id < v_new_id_venta;	
			select id into v_id_extracto_producto_compra from extracto_producto where observacion = 'compra' and registro = new.id_compra
				and id_producto = new.id_producto;
			select id into v_id_extracto_producto_venta from extracto_producto where observacion = 'venta' and registro = v_id_venta
				and id_producto = new.id_producto;
		end while;
        select sum(case when observacion='compra' and registro<new.id_compra then entrada else 0 end)-
			   sum( case when observacion ='venta' and registro<=v_id_venta then salida else 0 end) into v_total_cantidad 
               
		from extracto_producto where id_producto = new.id_producto ;
         if(v_total_cantidad < 0) then
			set v_total_cantidad =0;
		 end if;
        set v_costo_actual =0;
        select max(id) into max_id_seguimiento_costo from seguimiento_costo where id_producto = new.id_producto 
			and fecha<=v_fecha	and registro <>new.id_compra and observacion ='Compra';
		select ifnull((costo_calculo),0) into v_costo_actual from seguimiento_costo where id= max_id_seguimiento_costo and id_producto = new.id_producto; 
        set v_sumatoria_actual = (v_total_cantidad*v_costo_actual);
        set v_sumatoria_entrante = (new.cantidad*v_costo_producto_cotizado);
        set v_sumatoria = (v_sumatoria_actual+v_sumatoria_entrante);
        set v_cantidad = (v_total_cantidad + new.cantidad);
        set v_costo_promedio = (v_sumatoria/v_cantidad);
    if(v_costo_calculo = 0) then
		update producto set costo = v_costo_producto_cotizado where id = new.id_producto;
        set v_costo_para_ajuste = v_costo_producto_cotizado;
         insert into seguimiento_costo(
						id_producto,
                        cantidad_actual,
                        costo_actual,
                        cantidad_compra,
                        precio_compra,
						sumatoria_actual,
                        sumatoria_compra,
                        costo_total,
						cantidad_total,
                        costo_calculo,
                        fecha,
                        registro,
                        observacion,detalle)values(
							new.id_producto,v_total_cantidad,v_costo_actual,new.cantidad,v_costo_producto_cotizado,v_sumatoria_actual,v_sumatoria_entrante,
                            v_sumatoria,v_cantidad,v_costo_promedio,v_fecha,new.id_compra,'Compra',new.id
                        );
    end if;
    
    if(v_costo_calculo = 1)then
        update producto set costo = v_costo_promedio where id = new.id_producto;
        set v_costo_para_ajuste = v_costo_promedio;
        insert into seguimiento_costo(
						id_producto,
                        cantidad_actual,
                        costo_actual,
                        cantidad_compra,
                        precio_compra,
						sumatoria_actual,
                        sumatoria_compra,
                        costo_total,
						cantidad_total,
                        costo_calculo,
                        fecha,
                        registro,
                        observacion,detalle)values(
                        new.id_producto,v_total_cantidad,v_costo_actual,new.cantidad,v_costo_producto_cotizado,v_sumatoria_actual,v_sumatoria_entrante,
                        v_sumatoria,v_cantidad,v_costo_promedio,v_fecha,new.id_compra,'Compra',new.id
                        );
        
    end if;
   
	select id_precio into v_id_precio_venta_compra from compra where id = new.id_compra ;
    if(v_id_precio_venta_compra is not null  ) then 
        
		insert into ajuste_precio(fecha,id_precio,id_item_compra)values(curdate(),v_id_precio_venta_compra,new.id);
        SELECT LAST_INSERT_ID() into v_id_ajuste_precio;
        insert into item_ajuste_precio(costo,precio_minimo,precio_producto,utilidad,id_ajuste_precio,id_producto)
						   values(v_costo_para_ajuste,0,new.precio_venta,(new.precio_venta-v_costo_para_ajuste),v_id_ajuste_precio,new.id_producto);
    end if;
    
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_compra_bef_del` BEFORE DELETE ON `item_compra` FOR EACH ROW begin 
	declare v_id_ajuste_precio bigint;
	declare v_deposito bigint;
    select id_deposito into v_deposito from compra where id = old.id_compra;
	update stock_deposito set cantidad = cantidad-old.cantidad where id_deposito = v_deposito and id_producto = old.id_producto;
    delete from extracto_producto where registro = old.id_compra and observacion ='compra' and id_detalle = old.id;
    select id into v_id_ajuste_precio from ajuste_precio where id_item_compra = old.id;
    delete from item_ajuste_precio where id_ajuste_precio = v_id_ajuste_precio;
    delete from ajuste_precio where id_item_compra = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER item_compra_af_delete  AFTER  DELETE ON item_compra
FOR EACH ROW 
begin 
	declare v_max_id_compra bigint;
    declare v_precio_ultima_compra decimal(19,2);
    declare v_costo_calculo int;
    declare v_costo_producto decimal(35,16);
    declare v_cantidad_ultima_compra decimal(35,16);
    declare v_total_cantidad decimal(35,16);
    declare v_sumatoria_actual decimal(35,16);
    declare v_sumatoria_entrante decimal(35,16);
    declare v_sumatoria decimal(35,16);
    declare v_cantidad decimal(35,16);
    declare v_costo_promedio decimal(35,16);
    declare v_max_id_seguimiento_costo bigint;
    declare v_moneda_compra bigint;
    declare v_fecha date;
    declare v_moneda_producto bigint;
    declare v_precio_cotizado decimal(35,16);
    
	select max(id_compra) into v_max_id_compra from item_compra where id_producto = old.id_producto and id <>old.id;
    select id_moneda,fecha into v_moneda_compra,v_fecha from compra where id = v_max_id_compra;
    select id_moneda into v_moneda_producto from producto where id = old.id_producto;
    select precio into v_precio_ultima_compra from item_compra where id_compra = v_max_id_compra and id_producto = old.id_producto;
    select cantidad into v_cantidad_ultima_compra from item_compra where id_compra = v_max_id_compra and id_producto = old.id_producto;
    select costo_calculo into v_costo_calculo from producto where id = old.id_producto;
    set  v_precio_cotizado = f_cotizar(v_moneda_compra, v_moneda_producto, v_fecha, v_precio_ultima_compra, 0);
	if(v_costo_calculo = 0)then
		update producto set costo = v_precio_cotizado where id = old.id_producto;
        delete from seguimiento_costo where observacion ='Compra' and registro = old.id_compra and detalle = old.id;
    end if;
    if(v_costo_calculo = 1) then
		
        delete from seguimiento_costo where observacion ='Compra' and registro = old.id_compra and detalle = old.id;
        select max(id) into v_max_id_seguimiento_costo from seguimiento_costo where id_producto = old.id_producto;
        select costo_calculo into v_costo_promedio from seguimiento_costo where id = v_max_id_seguimiento_costo  and id_producto = old.id_producto;
        if(v_costo_promedio is null)then        
			set v_costo_promedio =0;
        end if;
        update producto set costo = v_costo_promedio where id = old.id_producto;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_consorcio`
--

DROP TABLE IF EXISTS `item_consorcio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_consorcio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meses` int(11) DEFAULT NULL,
  `monto` decimal(19,2) DEFAULT NULL,
  `procesado` bit(1) NOT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `id_consorcio` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3nvrl6f6e4n3b5ni55vc8wvqy` (`id_cliente`),
  KEY `FK2yei1wvthsr0gaw100wieol1v` (`id_consorcio`),
  CONSTRAINT `FK2yei1wvthsr0gaw100wieol1v` FOREIGN KEY (`id_consorcio`) REFERENCES `consorcio` (`id`),
  CONSTRAINT `FK3nvrl6f6e4n3b5ni55vc8wvqy` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_consorcio`
--

LOCK TABLES `item_consorcio` WRITE;
/*!40000 ALTER TABLE `item_consorcio` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_consorcio` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_consorcio_af_upd` AFTER UPDATE ON `item_consorcio` FOR EACH ROW begin 
	declare v_cantidad integer;
    select count(id) into v_cantidad from item_consorcio where id_consorcio = new.id_consorcio and procesado = false;
    if(v_cantidad =0) then
		update consorcio set terminado = true where id = new.id_consorcio;
	else
		update consorcio set terminado = false where id = new.id_consorcio;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_consorcio_importe`
--

DROP TABLE IF EXISTS `item_consorcio_importe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_consorcio_importe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `monto` decimal(19,2) DEFAULT NULL,
  `monto_cobrado` decimal(19,2) DEFAULT NULL,
  `saldo` decimal(19,2) DEFAULT NULL,
  `id_cuenta` bigint(20) DEFAULT NULL,
  `id_item_consorcio` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5bpij4l8msjk0a59uw1i1nvcv` (`id_cuenta`),
  KEY `FKqj4uu0clcawphykh3f2citf6i` (`id_item_consorcio`),
  CONSTRAINT `FK5bpij4l8msjk0a59uw1i1nvcv` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKqj4uu0clcawphykh3f2citf6i` FOREIGN KEY (`id_item_consorcio`) REFERENCES `item_consorcio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_consorcio_importe`
--

LOCK TABLES `item_consorcio_importe` WRITE;
/*!40000 ALTER TABLE `item_consorcio_importe` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_consorcio_importe` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_consorcio_importe_af_upd` AFTER UPDATE ON `item_consorcio_importe` FOR EACH ROW begin 
	declare v_cliente varchar(250);
    declare v_consorcio varchar(250);
    
    select cliente.nombre into v_cliente from item_consorcio,cliente
	where cliente.id = item_consorcio.id_cliente and item_consorcio.id = new.id_item_consorcio;
    
    select consorcio.nombre into v_consorcio from consorcio,item_consorcio,item_consorcio_importe
	where consorcio.id = item_consorcio.id_consorcio and item_consorcio.id = item_consorcio_importe.id_item_consorcio
	and item_consorcio_importe.id = new.id;
    
    delete from movimiento_caja where id_item_consorcio_importe = new.id;
    if(new.monto_cobrado>0)then
		insert into movimiento_caja (credito,debito,fecha,observacion,cuenta_id,id_item_consorcio_importe)
		values(new.monto_cobrado,0,curdate(),concat('Consorcio: ',new.id,', ',v_consorcio,', ',' cliente: ',v_cliente),new.id_cuenta,new.id);
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_pago`
--

DROP TABLE IF EXISTS `item_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_pago` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `importe` decimal(19,2) DEFAULT NULL,
  `id_compra` bigint(20) DEFAULT NULL,
  `id_pago` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk6xy60m7yvn7dp1wpiv79y0d1` (`id_compra`),
  KEY `FKkorvhs3lvv52jvgwbjpb0vrib` (`id_pago`),
  CONSTRAINT `FKk6xy60m7yvn7dp1wpiv79y0d1` FOREIGN KEY (`id_compra`) REFERENCES `compra` (`id`),
  CONSTRAINT `FKkorvhs3lvv52jvgwbjpb0vrib` FOREIGN KEY (`id_pago`) REFERENCES `pago` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_pago`
--

LOCK TABLES `item_pago` WRITE;
/*!40000 ALTER TABLE `item_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_pago` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_pago_aft_insert` AFTER INSERT ON `item_pago` FOR EACH ROW begin
	update cuenta_por_pagar set importe_pagado = importe_pagado+new.importe where id_compra = new.id_compra;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_pago_aft_del` AFTER DELETE ON `item_pago` FOR EACH ROW begin
	update cuenta_por_pagar set importe_pagado = importe_pagado - old.importe where id_compra = old.id_compra;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_pago_importe`
--

DROP TABLE IF EXISTS `item_pago_importe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_pago_importe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `importe` decimal(19,2) DEFAULT NULL,
  `id_cuenta` bigint(20) DEFAULT NULL,
  `id_pago` bigint(20) DEFAULT NULL,
  `importe_ms` decimal(19,2) DEFAULT NULL,
  `id_condicion` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKglgh672jnch3a2b1keixblr3t` (`id_cuenta`),
  KEY `FKdj9omrnrlm1soqpxrg28me1hl` (`id_pago`),
  KEY `FKbycrw0muia9mrr23m7vecvn0k` (`id_condicion`),
  KEY `FKjlmv19hyb3cdei7hc594xs8l4` (`id_moneda`),
  CONSTRAINT `FKbycrw0muia9mrr23m7vecvn0k` FOREIGN KEY (`id_condicion`) REFERENCES `condicion` (`id`),
  CONSTRAINT `FKdj9omrnrlm1soqpxrg28me1hl` FOREIGN KEY (`id_pago`) REFERENCES `pago` (`id`),
  CONSTRAINT `FKglgh672jnch3a2b1keixblr3t` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKjlmv19hyb3cdei7hc594xs8l4` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_pago_importe`
--

LOCK TABLES `item_pago_importe` WRITE;
/*!40000 ALTER TABLE `item_pago_importe` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_pago_importe` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_pago_importe_aft_ins` AFTER INSERT ON `item_pago_importe` FOR EACH ROW begin 
	declare v_fecha date;
    select fecha into v_fecha from pago where id = new.id_pago;
    insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_item_pago_importe)
	values(0,new.importe,v_fecha,concat('Pago a proveedor nro:',new.id_pago),new.id_cuenta,new.id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `item_pago_importe_bef_del` BEFORE DELETE ON `item_pago_importe` FOR EACH ROW begin 
	delete from movimiento_caja where id_item_pago_importe = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_planilla`
--

DROP TABLE IF EXISTS `item_planilla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_planilla` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `importe` decimal(35,16) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  `id_planilla` bigint(20) DEFAULT NULL,
  `id_condicion` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK69ieohjm3ie2dvcxj171i7225` (`id_moneda`),
  KEY `FKiu4v5f67ea3600qc0q6pfsbbv` (`id_planilla`),
  KEY `FKmmo18pt1pbgcpnp076gv37mks` (`id_condicion`),
  CONSTRAINT `FK69ieohjm3ie2dvcxj171i7225` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKiu4v5f67ea3600qc0q6pfsbbv` FOREIGN KEY (`id_planilla`) REFERENCES `planilla` (`id`),
  CONSTRAINT `FKmmo18pt1pbgcpnp076gv37mks` FOREIGN KEY (`id_condicion`) REFERENCES `condicion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_planilla`
--

LOCK TABLES `item_planilla` WRITE;
/*!40000 ALTER TABLE `item_planilla` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_planilla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_planilla_rendicion`
--

DROP TABLE IF EXISTS `item_planilla_rendicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_planilla_rendicion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `importe` decimal(35,16) DEFAULT NULL,
  `id_condicion` bigint(20) DEFAULT NULL,
  `id_cuenta` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  `id_planilla` bigint(20) DEFAULT NULL,
  `importe_mn` decimal(19,2) DEFAULT NULL,
  `id_cuenta_destino` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqb6gdbdmjn1n3pnk4anj02dir` (`id_condicion`),
  KEY `FKn668g6ggkc3lw3yw8kenhee1i` (`id_cuenta`),
  KEY `FK6jfxg8egkrayhl3ofywbgrwmp` (`id_moneda`),
  KEY `FK1bmwr546chju3vlonuonwoodj` (`id_planilla`),
  KEY `FK8ngivr2f8ah7pnvmwcw2ers41` (`id_cuenta_destino`),
  CONSTRAINT `FK1bmwr546chju3vlonuonwoodj` FOREIGN KEY (`id_planilla`) REFERENCES `planilla` (`id`),
  CONSTRAINT `FK6jfxg8egkrayhl3ofywbgrwmp` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FK8ngivr2f8ah7pnvmwcw2ers41` FOREIGN KEY (`id_cuenta_destino`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKn668g6ggkc3lw3yw8kenhee1i` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKqb6gdbdmjn1n3pnk4anj02dir` FOREIGN KEY (`id_condicion`) REFERENCES `condicion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_planilla_rendicion`
--

LOCK TABLES `item_planilla_rendicion` WRITE;
/*!40000 ALTER TABLE `item_planilla_rendicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_planilla_rendicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_precio`
--

DROP TABLE IF EXISTS `item_precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_precio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `costo` decimal(19,2) DEFAULT NULL,
  `precio_minimo` decimal(19,2) DEFAULT NULL,
  `precio_producto` decimal(19,2) DEFAULT NULL,
  `id_precio` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgqdqg6sc9lm1dppyxap65i5hp` (`id_precio`),
  KEY `FKajttx1g7pga839o0j2p05auee` (`id_producto`),
  CONSTRAINT `FKajttx1g7pga839o0j2p05auee` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKgqdqg6sc9lm1dppyxap65i5hp` FOREIGN KEY (`id_precio`) REFERENCES `precio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_precio`
--

LOCK TABLES `item_precio` WRITE;
/*!40000 ALTER TABLE `item_precio` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_precio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_presupuesto_compra`
--

DROP TABLE IF EXISTS `item_presupuesto_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_presupuesto_compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `precio` decimal(19,2) DEFAULT NULL,
  `id_presupuesto_compra` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4p9w0dpqfodovn98vklnaj5s` (`id_presupuesto_compra`),
  KEY `FKpy0v7mxlc9s61ny24sxys3dcn` (`id_producto`),
  CONSTRAINT `FK4p9w0dpqfodovn98vklnaj5s` FOREIGN KEY (`id_presupuesto_compra`) REFERENCES `presupuesto_compra` (`id`),
  CONSTRAINT `FKpy0v7mxlc9s61ny24sxys3dcn` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_presupuesto_compra`
--

LOCK TABLES `item_presupuesto_compra` WRITE;
/*!40000 ALTER TABLE `item_presupuesto_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_presupuesto_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_presupuesto_venta`
--

DROP TABLE IF EXISTS `item_presupuesto_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_presupuesto_venta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `costo` decimal(19,2) DEFAULT NULL,
  `precio` decimal(19,2) DEFAULT NULL,
  `id_presupuesto_venta` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoxgd98gf6wvdtu7sl5n7rytv9` (`id_presupuesto_venta`),
  KEY `FK4atiemu6jnpmvymjs3agxblnm` (`id_producto`),
  CONSTRAINT `FK4atiemu6jnpmvymjs3agxblnm` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKoxgd98gf6wvdtu7sl5n7rytv9` FOREIGN KEY (`id_presupuesto_venta`) REFERENCES `presupuesto_venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_presupuesto_venta`
--

LOCK TABLES `item_presupuesto_venta` WRITE;
/*!40000 ALTER TABLE `item_presupuesto_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_presupuesto_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_producto_codigo`
--

DROP TABLE IF EXISTS `item_producto_codigo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_producto_codigo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo_alternativo` varchar(255) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoy6uql7uk0br3lfk9cu3aec7` (`id_producto`),
  CONSTRAINT `FKoy6uql7uk0br3lfk9cu3aec7` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_producto_codigo`
--

LOCK TABLES `item_producto_codigo` WRITE;
/*!40000 ALTER TABLE `item_producto_codigo` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_producto_codigo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_transferencia_stock`
--

DROP TABLE IF EXISTS `item_transferencia_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_transferencia_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `id_transferencia_stock` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8o3mm7rfeg9h0mu3gotfps7ag` (`id_producto`),
  KEY `FK61aqn9slkxrulk2vsprw4f1ky` (`id_transferencia_stock`),
  CONSTRAINT `FK61aqn9slkxrulk2vsprw4f1ky` FOREIGN KEY (`id_transferencia_stock`) REFERENCES `transferencia_stock` (`id`),
  CONSTRAINT `FK8o3mm7rfeg9h0mu3gotfps7ag` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_transferencia_stock`
--

LOCK TABLES `item_transferencia_stock` WRITE;
/*!40000 ALTER TABLE `item_transferencia_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_transferencia_stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger item_transferencia_stock_aft_insert after insert on item_transferencia_stock
for each row
begin 
	declare v_deposito_origen bigint;
    declare v_deposito_destino bigint;
    declare v_existe_deposito_origen bigint;
    declare v_existe_deposito_destino bigint;
    declare v_es_servicio bit(1);
    declare v_fecha date;
    
    
    select id_deposito_origen,id_deposito_destino,fecha into v_deposito_origen,v_deposito_destino,v_fecha
		from transferencia_stock where id = new.id_transferencia_stock;
	select count(id) into v_existe_deposito_origen from stock_deposito where id_deposito = v_deposito_origen and id_producto = new.id_producto;
    select count(id) into v_existe_deposito_destino from stock_deposito where id_deposito = v_deposito_destino and id_producto = new.id_producto;
    select servicio into v_es_servicio from producto where id = new.id_producto;
    
    if(v_es_servicio = false) then
		if(v_existe_deposito_origen = 0) then
				insert into stock_deposito(cantidad,reserva,id_deposito,id_producto)values(new.cantidad*-1,0,v_deposito_origen,new.id_producto);
		else
			update stock_deposito set cantidad = cantidad - new.cantidad where id_deposito = v_deposito_origen and id_producto = new.id_producto;
		end if;
        if(v_existe_deposito_destino = 0) then
				insert into stock_deposito(cantidad,reserva,id_deposito,id_producto)values(new.cantidad,0,v_deposito_destino,new.id_producto);
		else
			update stock_deposito set cantidad = cantidad + new.cantidad where id_deposito = v_deposito_destino and id_producto = new.id_producto;
		end if;
    end if;
    insert into extracto_producto(entrada,fecha,id_detalle,observacion,registro,salida,id_deposito,id_producto)
				values(new.cantidad,v_fecha,new.id,'transferencia_stock',new.id_transferencia_stock,0,v_deposito_destino,new.id_producto);
    
    insert into extracto_producto(entrada,fecha,id_detalle,observacion,registro,salida,id_deposito,id_producto)
				values(0,v_fecha,new.id,'transferencia_stock',new.id_transferencia_stock,new.cantidad,v_deposito_origen,new.id_producto);
    
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger item_transferencia_stock_bef_del before delete on item_transferencia_stock
for each row
begin 
	declare v_deposito_origen bigint;
    declare v_deposito_destino bigint;
    
    select id_deposito_origen,id_deposito_destino into v_deposito_origen,v_deposito_destino
		from transferencia_stock where id = old.id_transferencia_stock;
	update stock_deposito set cantidad = cantidad -old.cantidad where id_deposito = v_deposito_destino and id_producto = old.id_producto;
    update stock_deposito set cantidad = cantidad +old.cantidad where id_deposito = v_deposito_origen and id_producto = old.id_producto;
    delete from extracto_producto where observacion = 'transferencia_stock' and id_detalle = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_venta`
--

DROP TABLE IF EXISTS `item_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_venta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `costo` decimal(19,2) DEFAULT NULL,
  `precio` decimal(19,2) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `id_venta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2493gmia9rkiwljisaomg6rqb` (`id_producto`),
  KEY `FKsmr7t0i049e2n4diuv9w5y7hn` (`id_venta`),
  CONSTRAINT `FK2493gmia9rkiwljisaomg6rqb` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKsmr7t0i049e2n4diuv9w5y7hn` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_venta`
--

LOCK TABLES `item_venta` WRITE;
/*!40000 ALTER TABLE `item_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER item_venta_aft_ins  AFTER  INSERT ON item_venta
FOR EACH ROW 
begin
    /*declaracion de variables*/ 
	declare v_deposito bigint;
    declare v_fecha date;
    declare v_vendedor bigint;
    declare v_porcentaje_vendedor decimal(19,2);
    declare v_sub_total decimal(19,2);
    declare v_comision_producto decimal(19,2);
    declare v_servicio bit(1);
    declare v_importe_comision decimal(19,2);
    declare v_count bigint;
    /*fin de declaracion de variables*/

    select id_deposito,fecha,id_vendedor into v_deposito, v_fecha,v_vendedor  from venta where id = new.id_venta;
    select comision into v_comision_producto from producto where id = new.id_producto;
	select servicio into v_servicio from producto where id = new.id_producto;
	set v_sub_total = (new.cantidad* new.precio);
    select count(id) into v_count from stock_deposito where id_producto = new.id_producto and id_deposito = v_deposito;
    if(v_servicio = false)then
		 insert into extracto_producto(entrada,fecha,id_detalle,observacion,registro,salida,id_deposito,id_producto)
		values(0,v_fecha,new.id,'Venta',new.id_venta,new.cantidad,v_deposito,new.id_producto);
        if(v_count = 0) then
			insert into stock_deposito(cantidad,reserva,id_producto,id_deposito)values(new.cantidad*-1,0,new.id_producto,v_deposito);
        else
			update stock_deposito set cantidad = cantidad-new.cantidad where id_deposito = v_deposito and id_producto = new.id_producto;
		end if;
    end if;
    
    if(v_comision_producto>0)then
		set v_importe_comision = ((v_sub_total *v_comision_producto)/100);
        insert into movimiento_vendedor (credito,debito,fecha,observacion,id_item_venta,id_vendedor)
			values(v_importe_comision,0,v_fecha,concat('Comision de venta: ',new.id_venta),new.id,v_vendedor);
    end if;
    
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER item_venta_bef_del  BEFORE  DELETE ON item_venta
FOR EACH ROW 
begin 
declare v_deposito bigint;
    select id_deposito into v_deposito from venta where id = old.id_venta;
	update stock_deposito set cantidad = cantidad+old.cantidad where id_deposito = v_deposito and id_producto = old.id_producto;
delete from extracto_producto where observacion = 'Venta' and id_detalle = old.id;
delete from movimiento_vendedor where id_item_venta = old.id;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `item_venta_lote`
--

DROP TABLE IF EXISTS `item_venta_lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_venta_lote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `nro_lote` varchar(255) DEFAULT NULL,
  `vencimiento` date DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `id_venta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6sdw02p7c86go1mgie8382ta7` (`id_producto`),
  KEY `FKsijx81ji6pndcuy9ydwwmb4ul` (`id_venta`),
  CONSTRAINT `FK6sdw02p7c86go1mgie8382ta7` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKsijx81ji6pndcuy9ydwwmb4ul` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_venta_lote`
--

LOCK TABLES `item_venta_lote` WRITE;
/*!40000 ALTER TABLE `item_venta_lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_venta_lote` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger item_venta_lote_aft_insert after insert on item_venta_lote
for each row
begin 
declare v_fecha date;
declare v_deposito bigint;

select fecha,id_deposito into v_fecha,v_deposito from venta where id = new.id_venta;

insert into movimiento_lote(entrada,fecha,nro_lote,observacion,registro,salida,vencimiento,id_deposito,id_producto,id_item_venta_lote)
values(0,v_fecha,new.nro_lote,'venta',new.id_venta,new.cantidad,new.vencimiento,v_deposito,new.id_producto,new.id);	
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger item_venta_lote_bef_del before delete on item_venta_lote
for each row
begin 
	delete from movimiento_lote where id_item_venta_lote = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `licencia`
--

DROP TABLE IF EXISTS `licencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `licencia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bloqueado` bit(1) NOT NULL,
  `dias` bigint(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `licencia`
--

LOCK TABLES `licencia` WRITE;
/*!40000 ALTER TABLE `licencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `licencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `licencia_fecha`
--

DROP TABLE IF EXISTS `licencia_fecha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `licencia_fecha` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `licencia_fecha`
--

LOCK TABLES `licencia_fecha` WRITE;
/*!40000 ALTER TABLE `licencia_fecha` DISABLE KEYS */;
/*!40000 ALTER TABLE `licencia_fecha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lote`
--

DROP TABLE IF EXISTS `lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `nro_lote` varchar(255) DEFAULT NULL,
  `vencimiento` date DEFAULT NULL,
  `id_deposito` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj98hjla7i0n0ca8pggey91d4p` (`id_deposito`),
  KEY `FKcn55butngt8wrmglewkbyewaa` (`id_producto`),
  CONSTRAINT `FKcn55butngt8wrmglewkbyewaa` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKj98hjla7i0n0ca8pggey91d4p` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lote`
--

LOCK TABLES `lote` WRITE;
/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marca` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcacion`
--

DROP TABLE IF EXISTS `marcacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `game_dosa` int(11) DEFAULT NULL,
  `game_dosb` int(11) DEFAULT NULL,
  `game_tresa` int(11) DEFAULT NULL,
  `game_tresb` int(11) DEFAULT NULL,
  `game_unoa` int(11) DEFAULT NULL,
  `game_unob` int(11) DEFAULT NULL,
  `puntoa` int(11) DEFAULT NULL,
  `puntob` int(11) DEFAULT NULL,
  `terminado` bit(1) NOT NULL,
  `terminado_game_dos` bit(1) NOT NULL,
  `terminado_game_tres` bit(1) NOT NULL,
  `terminado_game_uno` bit(1) NOT NULL,
  `id_equipo_a` bigint(20) NOT NULL,
  `id_equipo_b` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi8npw4i48n06vp9f20vb9m5at` (`id_equipo_a`),
  KEY `FK65244gv717x2dr26jpt4acf9y` (`id_equipo_b`),
  CONSTRAINT `FK65244gv717x2dr26jpt4acf9y` FOREIGN KEY (`id_equipo_b`) REFERENCES `equipo` (`id`),
  CONSTRAINT `FKi8npw4i48n06vp9f20vb9m5at` FOREIGN KEY (`id_equipo_a`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcacion`
--

LOCK TABLES `marcacion` WRITE;
/*!40000 ALTER TABLE `marcacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `marcacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membresia`
--

DROP TABLE IF EXISTS `membresia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membresia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `fecha_estimado` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `importe` decimal(19,2) NOT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `cliente_id` bigint(20) NOT NULL,
  `cuenta_id` bigint(20) NOT NULL,
  `id_instructor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn9dtmm0r7gyisg712tmg106um` (`cliente_id`),
  KEY `FKbnv61x6oa3utm46c0li4sb10d` (`cuenta_id`),
  KEY `FKakgp5a5dppkerv0oqf9ckhqhg` (`id_instructor`),
  CONSTRAINT `FKakgp5a5dppkerv0oqf9ckhqhg` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id`),
  CONSTRAINT `FKbnv61x6oa3utm46c0li4sb10d` FOREIGN KEY (`cuenta_id`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKn9dtmm0r7gyisg712tmg106um` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membresia`
--

LOCK TABLES `membresia` WRITE;
/*!40000 ALTER TABLE `membresia` DISABLE KEYS */;
/*!40000 ALTER TABLE `membresia` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `membresia_bef_ins` BEFORE INSERT ON `membresia` FOR EACH ROW begin
	if(new.fecha is null) then
		set new.fecha = curdate();
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `membresia_af_ins` AFTER INSERT ON `membresia` FOR EACH ROW begin
	declare v_porcentaje decimal(19,2);
    declare v_max_fecha date;
	insert into movimiento_caja (fecha,observacion,cuenta_id,membresia_id,credito,debito)values(new.fecha,'membresia',new.cuenta_id,new.id,new.importe,0);
    
    if(new.id_instructor is not null)then
		select porcentaje into v_porcentaje from instructor where id = new.id_instructor;
		insert into mov_instructor(fecha,credito,debito,observacion,id_instructor,id_membresia)values
								  (new.fecha,((new.importe*v_porcentaje)/100),0,'Membresia',new.id_instructor,new.id);
    end if;
    select max(fecha) into v_max_fecha from membresia where cliente_id = new.cliente_id;
    update cliente set fecha_ultimo_pago= v_max_fecha where id = new.cliente_id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `membresia_af_upd` AFTER UPDATE ON `membresia` FOR EACH ROW begin 
	declare v_porcentaje decimal(19,2);
     declare v_max_fecha date;
	delete from movimiento_caja where membresia_id = new.id;
    insert into movimiento_caja (fecha,observacion,cuenta_id,membresia_id,credito,debito)
    values(new.fecha,'membresia',new.cuenta_id,new.id,new.importe,0);
    if(new.id_instructor is not null)then
		select porcentaje into v_porcentaje from instructor where id = new.id_instructor;
		delete from mov_instructor where id_membresia=new.id ;
        insert into mov_instructor(fecha,credito,debito,observacion,id_instructor,id_membresia)values
								  (new.fecha,((new.importe*v_porcentaje)/100),0,'Membresia',new.id_instructor,new.id);
    end if;
    select max(fecha) into v_max_fecha from membresia where cliente_id = new.cliente_id;
    update cliente set fecha_ultimo_pago= v_max_fecha where id = new.cliente_id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `membresia_bef_dele` BEFORE DELETE ON `membresia` FOR EACH ROW begin 
	delete from movimiento_caja where membresia_id = old.id;
    if(old.id_instructor is not null)then
		delete from mov_instructor where id_membresia=old.id ;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `membresia_af_del` AFTER DELETE ON `membresia` FOR EACH ROW begin 
declare v_max_fecha date;
select max(fecha) into v_max_fecha from membresia where cliente_id = old.cliente_id;
update cliente set fecha_ultimo_pago = v_max_fecha where id = old.cliente_id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `moneda`
--

DROP TABLE IF EXISTS `moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moneda` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `decimales` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `sigla` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moneda`
--

LOCK TABLES `moneda` WRITE;
/*!40000 ALTER TABLE `moneda` DISABLE KEYS */;
INSERT INTO `moneda` VALUES (1,0,'Guaranies','GS'),(2,2,'Reales','RS'),(3,2,'Dolares','US');
/*!40000 ALTER TABLE `moneda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mora_cliente`
--

DROP TABLE IF EXISTS `mora_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mora_cliente` (
  `id` bigint(20) NOT NULL,
  `cliente` varchar(255) DEFAULT NULL,
  `dias` int(11) DEFAULT NULL,
  `documento` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fecha_estimado` date DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mora_cliente`
--

LOCK TABLES `mora_cliente` WRITE;
/*!40000 ALTER TABLE `mora_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `mora_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mov_instructor`
--

DROP TABLE IF EXISTS `mov_instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mov_instructor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `credito` decimal(19,2) DEFAULT NULL,
  `debito` decimal(19,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_instructor` bigint(20) DEFAULT NULL,
  `id_membresia` bigint(20) DEFAULT NULL,
  `id_pago_instructor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrkthg4hwb600uxl5tqjsyvan` (`id_instructor`),
  KEY `FKk1ov08xyftneoj83n7pekwmgy` (`id_membresia`),
  KEY `FK232ebj69u6w20kob3276drc19` (`id_pago_instructor`),
  CONSTRAINT `FK232ebj69u6w20kob3276drc19` FOREIGN KEY (`id_pago_instructor`) REFERENCES `pago_instructor` (`id`),
  CONSTRAINT `FKk1ov08xyftneoj83n7pekwmgy` FOREIGN KEY (`id_membresia`) REFERENCES `membresia` (`id`),
  CONSTRAINT `FKrkthg4hwb600uxl5tqjsyvan` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mov_instructor`
--

LOCK TABLES `mov_instructor` WRITE;
/*!40000 ALTER TABLE `mov_instructor` DISABLE KEYS */;
/*!40000 ALTER TABLE `mov_instructor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mov_instructor_af_insert` AFTER INSERT ON `mov_instructor` FOR EACH ROW begin
	
    if(new.credito >0)then
		update instructor set saldo = saldo+new.credito where id = new.id_instructor;
    end if;
    if(new.debito>0)then
		update instructor set saldo = saldo-new.debito where id = new.id_instructor;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `mov_instructor_bef_del` BEFORE DELETE ON `mov_instructor` FOR EACH ROW begin
	if(old.credito>0)then
		update instructor set saldo = saldo -old.credito where id= old.id_instructor;
    end if;
    if(old.debito>0)then
		update instructor set saldo = saldo +old.debito where id= old.id_instructor;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `movimiento_caja`
--

DROP TABLE IF EXISTS `movimiento_caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimiento_caja` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `credito` decimal(19,2) DEFAULT NULL,
  `debito` decimal(19,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `valor_cotizacion` decimal(19,2) DEFAULT NULL,
  `id_anticipo_cliente` bigint(20) DEFAULT NULL,
  `id_cobro_servicio` bigint(20) DEFAULT NULL,
  `cuenta_id` bigint(20) DEFAULT NULL,
  `gasto_id` bigint(20) DEFAULT NULL,
  `id_item_cobro_importe` bigint(20) DEFAULT NULL,
  `id_item_consorcio_importe` bigint(20) DEFAULT NULL,
  `id_item_pago_importe` bigint(20) DEFAULT NULL,
  `membresia_id` bigint(20) DEFAULT NULL,
  `id_moneda_cobro` bigint(20) DEFAULT NULL,
  `id_moneda_cuenta` bigint(20) DEFAULT NULL,
  `id_pago_instructor` bigint(20) DEFAULT NULL,
  `id_pago_vendedor` bigint(20) DEFAULT NULL,
  `id_transferencia_efectivo` bigint(20) DEFAULT NULL,
  `id_item_planilla` bigint(20) DEFAULT NULL,
  `id_item_planilla_rendicion` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3rmffhnwr27ce11pbqn3xof9` (`id_anticipo_cliente`),
  KEY `FKnu6qo70m6yw1ygjx7qyscouyv` (`id_cobro_servicio`),
  KEY `FKawkdoocun7mwv1e2e4q3pepp4` (`cuenta_id`),
  KEY `FKkpdh7yhlxi9e0jewonyjya4c3` (`gasto_id`),
  KEY `FKmxcncwf92m7kkrr4nnyf0q0y7` (`id_item_cobro_importe`),
  KEY `FKj39s23hbovn9hqkpcplrjwcw3` (`id_item_consorcio_importe`),
  KEY `FKkc8y7ufwk8ngqlq9s23dimuoo` (`id_item_pago_importe`),
  KEY `FKd4s8ovnp6c8cdk7c27e6efane` (`membresia_id`),
  KEY `FKms2vc5c0461np2pnukprgjqpy` (`id_moneda_cobro`),
  KEY `FK5us11dmyof8bd395pu7pjdbej` (`id_moneda_cuenta`),
  KEY `FKdix3pfno86xxa5h0jesafvxvo` (`id_pago_instructor`),
  KEY `FKsco8csefk1tf7ac258svd3c07` (`id_pago_vendedor`),
  KEY `FKla1ur268q6ulbwnuv3drwdk6t` (`id_transferencia_efectivo`),
  KEY `FKh7s4lvc23m6tf79abp880jt0u` (`id_item_planilla`),
  KEY `FK8biat7rej98ugb7hnipifh3nk` (`id_item_planilla_rendicion`),
  CONSTRAINT `FK3rmffhnwr27ce11pbqn3xof9` FOREIGN KEY (`id_anticipo_cliente`) REFERENCES `anticipo_cliente` (`id`),
  CONSTRAINT `FK5us11dmyof8bd395pu7pjdbej` FOREIGN KEY (`id_moneda_cuenta`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FK8biat7rej98ugb7hnipifh3nk` FOREIGN KEY (`id_item_planilla_rendicion`) REFERENCES `item_planilla_rendicion` (`id`),
  CONSTRAINT `FKawkdoocun7mwv1e2e4q3pepp4` FOREIGN KEY (`cuenta_id`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKd4s8ovnp6c8cdk7c27e6efane` FOREIGN KEY (`membresia_id`) REFERENCES `membresia` (`id`),
  CONSTRAINT `FKdix3pfno86xxa5h0jesafvxvo` FOREIGN KEY (`id_pago_instructor`) REFERENCES `pago_instructor` (`id`),
  CONSTRAINT `FKh7s4lvc23m6tf79abp880jt0u` FOREIGN KEY (`id_item_planilla`) REFERENCES `item_planilla` (`id`),
  CONSTRAINT `FKj39s23hbovn9hqkpcplrjwcw3` FOREIGN KEY (`id_item_consorcio_importe`) REFERENCES `item_consorcio_importe` (`id`),
  CONSTRAINT `FKkc8y7ufwk8ngqlq9s23dimuoo` FOREIGN KEY (`id_item_pago_importe`) REFERENCES `item_pago_importe` (`id`),
  CONSTRAINT `FKkpdh7yhlxi9e0jewonyjya4c3` FOREIGN KEY (`gasto_id`) REFERENCES `gasto` (`id`),
  CONSTRAINT `FKla1ur268q6ulbwnuv3drwdk6t` FOREIGN KEY (`id_transferencia_efectivo`) REFERENCES `transferencia_efectivo` (`id`),
  CONSTRAINT `FKms2vc5c0461np2pnukprgjqpy` FOREIGN KEY (`id_moneda_cobro`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKmxcncwf92m7kkrr4nnyf0q0y7` FOREIGN KEY (`id_item_cobro_importe`) REFERENCES `item_cobro_importe` (`id`),
  CONSTRAINT `FKnu6qo70m6yw1ygjx7qyscouyv` FOREIGN KEY (`id_cobro_servicio`) REFERENCES `cobro_servicio` (`id`),
  CONSTRAINT `FKsco8csefk1tf7ac258svd3c07` FOREIGN KEY (`id_pago_vendedor`) REFERENCES `pago_vendedor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento_caja`
--

LOCK TABLES `movimiento_caja` WRITE;
/*!40000 ALTER TABLE `movimiento_caja` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento_caja` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `movimiento_caja_af_ins` AFTER INSERT ON `movimiento_caja` FOR EACH ROW begin
if new.credito>0 then
	update cuenta set saldo = saldo + new.credito where id = new.cuenta_id;
end if;
if new.debito>0 then
	update cuenta set saldo = saldo - new.debito where id = new.cuenta_id;
end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `movimiento_caja_bef_dele` BEFORE DELETE ON `movimiento_caja` FOR EACH ROW begin 
	if old.credito>0 then
		update cuenta set saldo = saldo - old.credito where id = old.cuenta_id;
    end if;
    if old.debito>0 then
		update cuenta set saldo = saldo + old.debito where id = old.cuenta_id;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `movimiento_lote`
--

DROP TABLE IF EXISTS `movimiento_lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimiento_lote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entrada` decimal(19,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `nro_lote` varchar(255) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `registro` bigint(20) DEFAULT NULL,
  `salida` decimal(19,2) DEFAULT NULL,
  `vencimiento` date DEFAULT NULL,
  `id_deposito` bigint(20) DEFAULT NULL,
  `id_item_ajuste_lote` bigint(20) DEFAULT NULL,
  `id_item_venta_lote` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtdfbmp3gyodqw1te9r5kvdk1` (`id_deposito`),
  KEY `FKevbv298j09fkk28twv251ibnr` (`id_item_ajuste_lote`),
  KEY `FKjvtgv3pg8vp5xt7q4uydcpkiv` (`id_item_venta_lote`),
  KEY `FKox0bhfjv5435xqut4948af3w6` (`id_producto`),
  CONSTRAINT `FKevbv298j09fkk28twv251ibnr` FOREIGN KEY (`id_item_ajuste_lote`) REFERENCES `item_ajuste_lote` (`id`),
  CONSTRAINT `FKjvtgv3pg8vp5xt7q4uydcpkiv` FOREIGN KEY (`id_item_venta_lote`) REFERENCES `item_venta_lote` (`id`),
  CONSTRAINT `FKox0bhfjv5435xqut4948af3w6` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKtdfbmp3gyodqw1te9r5kvdk1` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento_lote`
--

LOCK TABLES `movimiento_lote` WRITE;
/*!40000 ALTER TABLE `movimiento_lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento_lote` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger movimiento_lote_aft_insert after insert on movimiento_lote
for each row
begin 
declare v_cantidad decimal(19,2) default 0;
declare v_count bigint;
if(new.salida>0)then
	set v_cantidad = new.salida *-1;
else
	set v_cantidad = new.entrada;
end if;
SELECT 
    COUNT(id)
INTO v_count FROM
    lote
WHERE
    id_producto = new.id_producto
        AND nro_lote = new.nro_lote
        AND id_deposito = new.id_deposito;
if(v_count=0)then
	insert into lote(cantidad,nro_lote,vencimiento,id_deposito,id_producto)
					values(v_cantidad,new.nro_lote,new.vencimiento,new.id_deposito,new.id_producto);
else
	update lote set cantidad = cantidad+v_cantidad where id_producto = new.id_producto and nro_lote = new.nro_lote and id_deposito = new.id_deposito;
end if;
	
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger movimiento_lote_bef_delete before delete on movimiento_lote
for each row
begin 
	declare v_cantidad decimal(19,2) default 0;
    if(old.entrada >0) then 
		set v_cantidad = old.entrada;
    else
		set v_cantidad = old.salida*-1;
    end if;
    update lote set cantidad = cantidad -v_cantidad 
		where id_producto = old.id_producto and nro_lote = old.nro_lote and id_deposito = old.id_deposito;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `movimiento_nc_venta`
--

DROP TABLE IF EXISTS `movimiento_nc_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimiento_nc_venta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `nc_venta` bigint(20) DEFAULT NULL,
  `id_item_venta` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `id_venta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1iqm2kefkwuuarmogrrj43vh` (`id_item_venta`),
  KEY `FK4w73uxo6qqyys613p8oen9k9d` (`id_producto`),
  KEY `FKbyfxp26j3sji0b4edi4fl77gy` (`id_venta`),
  CONSTRAINT `FK1iqm2kefkwuuarmogrrj43vh` FOREIGN KEY (`id_item_venta`) REFERENCES `item_venta` (`id`),
  CONSTRAINT `FK4w73uxo6qqyys613p8oen9k9d` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKbyfxp26j3sji0b4edi4fl77gy` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento_nc_venta`
--

LOCK TABLES `movimiento_nc_venta` WRITE;
/*!40000 ALTER TABLE `movimiento_nc_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento_nc_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento_saldo_cliente`
--

DROP TABLE IF EXISTS `movimiento_saldo_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimiento_saldo_cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `credito` decimal(19,2) DEFAULT NULL,
  `debito` decimal(19,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_anticipo_cliente` bigint(20) DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `id_cobro_servicio` bigint(20) DEFAULT NULL,
  `id_cuenta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK47s8x6k1y5lp6aae3y6nohwff` (`id_anticipo_cliente`),
  KEY `FK2i27h63kif5p232xjuwjorymr` (`id_cliente`),
  KEY `FKhih9i1wb3y76v6j7tqdi12uu7` (`id_cobro_servicio`),
  KEY `FKprlivb4cvltlta3c3uy1lurky` (`id_cuenta`),
  CONSTRAINT `FK2i27h63kif5p232xjuwjorymr` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FK47s8x6k1y5lp6aae3y6nohwff` FOREIGN KEY (`id_anticipo_cliente`) REFERENCES `anticipo_cliente` (`id`),
  CONSTRAINT `FKhih9i1wb3y76v6j7tqdi12uu7` FOREIGN KEY (`id_cobro_servicio`) REFERENCES `cobro_servicio` (`id`),
  CONSTRAINT `FKprlivb4cvltlta3c3uy1lurky` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento_saldo_cliente`
--

LOCK TABLES `movimiento_saldo_cliente` WRITE;
/*!40000 ALTER TABLE `movimiento_saldo_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento_saldo_cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `movimiento_saldo_cliente_aft_ins` AFTER INSERT ON `movimiento_saldo_cliente` FOR EACH ROW begin 
	if(new.credito >0)then
		update cliente set saldo = saldo + new.credito where id = new.id_cliente;
    end if;
    if(new.debito>0) then 
		update cliente set saldo = saldo - new.debito
        where id = new.id_cliente;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `movimiento_saldo_cliente_aft_del` AFTER DELETE ON `movimiento_saldo_cliente` FOR EACH ROW begin
	if(old.credito>0)then
		update cliente set saldo = saldo-old.credito where id=old.id_cliente;
    end if;
    if(old.debito>0)then
		update cliente set saldo = saldo+old.debito where id=old.id_cliente;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `movimiento_vendedor`
--

DROP TABLE IF EXISTS `movimiento_vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimiento_vendedor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `credito` decimal(19,2) DEFAULT NULL,
  `debito` decimal(19,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_cobro_servicio` bigint(20) DEFAULT NULL,
  `id_pago_vendedor` bigint(20) DEFAULT NULL,
  `id_vendedor` bigint(20) DEFAULT NULL,
  `id_item_venta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9xn97y74ivuxgc4pfk2qfocb5` (`id_cobro_servicio`),
  KEY `FK7b9h20uyfg5tlkn2uueqvwlnl` (`id_pago_vendedor`),
  KEY `FKsvnjf97avo9aulpj9elwnm8th` (`id_vendedor`),
  KEY `FK4vjlvlf4ub9jd0ol88ifg9wgq` (`id_item_venta`),
  CONSTRAINT `FK4vjlvlf4ub9jd0ol88ifg9wgq` FOREIGN KEY (`id_item_venta`) REFERENCES `item_venta` (`id`),
  CONSTRAINT `FK7b9h20uyfg5tlkn2uueqvwlnl` FOREIGN KEY (`id_pago_vendedor`) REFERENCES `pago_vendedor` (`id`),
  CONSTRAINT `FK9xn97y74ivuxgc4pfk2qfocb5` FOREIGN KEY (`id_cobro_servicio`) REFERENCES `cobro_servicio` (`id`),
  CONSTRAINT `FKsvnjf97avo9aulpj9elwnm8th` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento_vendedor`
--

LOCK TABLES `movimiento_vendedor` WRITE;
/*!40000 ALTER TABLE `movimiento_vendedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento_vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `movimiento_vendedor_aft_insert` AFTER INSERT ON `movimiento_vendedor` FOR EACH ROW begin 
	update vendedor set saldo = saldo + new.credito where id = new.id_vendedor;
    update vendedor set saldo = saldo - new.debito where id = new.id_vendedor;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `movimiento_vendedor_aft_del` AFTER DELETE ON `movimiento_vendedor` FOR EACH ROW begin 
	if (old.id_pago_vendedor is null) then
		update vendedor set saldo = saldo - old.credito where id = old.id_vendedor;
	end if;
    if (old.id_cobro_servicio is null) then
		update vendedor set saldo = saldo + old.debito where id = old.id_vendedor;
	end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `notificacion`
--

DROP TABLE IF EXISTS `notificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `autorizado` bit(1) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `mensaje` varchar(255) DEFAULT NULL,
  `precio` decimal(19,2) DEFAULT NULL,
  `precio_lista` decimal(19,2) DEFAULT NULL,
  `precio_minimo` decimal(19,2) DEFAULT NULL,
  `rechazado` bit(1) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `id_vendedor` bigint(20) DEFAULT NULL,
  `id_vendedor_solicitante` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf5y0yivy242c26p9pj3m2ai0b` (`id_producto`),
  KEY `FKamydrto37fjuc5pkbsn1auho6` (`id_vendedor`),
  KEY `FKnep2mli5qwludwnq2p7gh5869` (`id_vendedor_solicitante`),
  CONSTRAINT `FKamydrto37fjuc5pkbsn1auho6` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`),
  CONSTRAINT `FKf5y0yivy242c26p9pj3m2ai0b` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKnep2mli5qwludwnq2p7gh5869` FOREIGN KEY (`id_vendedor_solicitante`) REFERENCES `vendedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacion`
--

LOCK TABLES `notificacion` WRITE;
/*!40000 ALTER TABLE `notificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `notificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pago` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `id_proveedor` bigint(20) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt68doobsq241tbtrtki8u1hhl` (`id_proveedor`),
  KEY `FKo7iaqgbbd954xkdo7n1pd6qr7` (`id_usuario`),
  KEY `FKlvd20yw3hsuui0o8y1fn2q14a` (`id_moneda`),
  CONSTRAINT `FKlvd20yw3hsuui0o8y1fn2q14a` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKo7iaqgbbd954xkdo7n1pd6qr7` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKt68doobsq241tbtrtki8u1hhl` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago_instructor`
--

DROP TABLE IF EXISTS `pago_instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pago_instructor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `observacion` varchar(255) DEFAULT NULL,
  `fecha` date NOT NULL,
  `importe` decimal(19,2) NOT NULL,
  `id_cuenta` bigint(20) NOT NULL,
  `id_instructor` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq34dgs94qve5hfeysokc87axj` (`id_cuenta`),
  KEY `FKs0p653mwi5t215gh3rypmsrem` (`id_instructor`),
  CONSTRAINT `FKq34dgs94qve5hfeysokc87axj` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKs0p653mwi5t215gh3rypmsrem` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago_instructor`
--

LOCK TABLES `pago_instructor` WRITE;
/*!40000 ALTER TABLE `pago_instructor` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago_instructor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pago_instructor_af_insert` AFTER INSERT ON `pago_instructor` FOR EACH ROW begin 
	insert into mov_instructor (credito,debito,fecha,observacion,id_instructor,id_pago_instructor)values(0,new.importe,new.fecha,'pag Instructor',new.id_instructor,new.id);
    insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_pago_instructor)values(0,new.importe,new.fecha,'pag. Instructor',new.id_cuenta,new.id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pago_instructor_af_upd` AFTER UPDATE ON `pago_instructor` FOR EACH ROW begin
	delete from mov_instructor where id_pago_instructor = new.id;
    insert into mov_instructor (credito,debito,fecha,observacion,id_instructor,id_pago_instructor)values(0,new.importe,new.fecha,'pag Instructor',new.id_instructor,new.id);
    delete from movimiento_caja where id_pago_instructor = new.id;
    insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_pago_instructor)values(0,new.importe,new.fecha,'pag. Instructor',new.id_cuenta,new.id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pago_instructor_bef_delete` BEFORE DELETE ON `pago_instructor` FOR EACH ROW begin
	delete from mov_instructor where id_pago_instructor =old.id;
    delete from movimiento_caja where id_pago_instructor =old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `pago_vendedor`
--

DROP TABLE IF EXISTS `pago_vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pago_vendedor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `importe` decimal(19,2) NOT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_cuenta` bigint(20) NOT NULL,
  `id_vendedor` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81ylcwojl4myririjkpls8agf` (`id_cuenta`),
  KEY `FKglpdxc7ddcl38vkcobkry0k28` (`id_vendedor`),
  CONSTRAINT `FK81ylcwojl4myririjkpls8agf` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKglpdxc7ddcl38vkcobkry0k28` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago_vendedor`
--

LOCK TABLES `pago_vendedor` WRITE;
/*!40000 ALTER TABLE `pago_vendedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago_vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pago_vendedor_aft_ins` AFTER INSERT ON `pago_vendedor` FOR EACH ROW begin 
	insert into movimiento_vendedor
		  (credito,debito,fecha,observacion,id_pago_vendedor,id_vendedor)
    values(0,new.importe,new.fecha,concat('Pago a vendedor:',new.id),new.id,new.id_vendedor);
    insert into movimiento_caja
		(credito,debito,fecha,observacion,cuenta_id,id_pago_vendedor)
	values(0,new.importe,new.fecha,concat('Pago a vendedor:',new.id),new.id_cuenta,new.id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pago_vendedor_aft_upd` AFTER UPDATE ON `pago_vendedor` FOR EACH ROW begin 
	delete from movimiento_vendedor where id_pago_vendedor = new.id;
    insert into movimiento_vendedor(credito,debito,fecha,observacion,id_vendedor,id_pago_vendedor)
    values(0,new.importe,new.fecha,concat('Pago a vendedor:',new.id),new.id_vendedor,new.id);
    
    delete from movimiento_caja where id_pago_vendedor = new.id;
    insert into movimiento_caja
		(credito,debito,fecha,observacion,cuenta_id,id_pago_vendedor)
	values(0,new.importe,new.fecha,concat('Pago a vendedor:',new.id),new.id_cuenta,new.id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pago_vendedor_before_del` BEFORE DELETE ON `pago_vendedor` FOR EACH ROW begin 
	delete from movimiento_vendedor where id_pago_vendedor = old.id;
    delete from movimiento_caja where id_pago_vendedor = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `parametro`
--

DROP TABLE IF EXISTS `parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `empresa` varchar(255) DEFAULT NULL,
  `actividad` varchar(255) DEFAULT NULL,
  `hora_desde` time DEFAULT NULL,
  `hora_hasta` time DEFAULT NULL,
  `inter_hora` time DEFAULT NULL,
  `mostrar_fondo` bit(1) NOT NULL,
  `mostrar_hora` bit(1) NOT NULL,
  `ruc` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `abre_planilla` bit(1) NOT NULL,
  `cotizacion_automatica` bit(1) NOT NULL,
  `version` varchar(255) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK869a645ac64r94lmk60m1sf8e` (`id_cliente`),
  KEY `FKneww9hipf8a659gqkh4n75ey9` (`id_moneda`),
  CONSTRAINT `FK869a645ac64r94lmk60m1sf8e` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKneww9hipf8a659gqkh4n75ey9` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro`
--

LOCK TABLES `parametro` WRITE;
/*!40000 ALTER TABLE `parametro` DISABLE KEYS */;
INSERT INTO `parametro` VALUES (1,'Empresa X','venta ',NULL,NULL,NULL,'\0','\0','*********','*************',NULL,'','',NULL,1);
/*!40000 ALTER TABLE `parametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametro_compra`
--

DROP TABLE IF EXISTS `parametro_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametro_compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agregar_precio` bit(1) NOT NULL,
  `id_deposito` bigint(20) DEFAULT NULL,
  `id_precio` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3wb3nptc79fnf1l2vo2xwuo8u` (`id_deposito`),
  KEY `FKquvm3k3ae8ycaqfvoqk4jxacl` (`id_precio`),
  KEY `FKdhw1vgr0aa2lhb174cj7k5n7o` (`id_usuario`),
  CONSTRAINT `FK3wb3nptc79fnf1l2vo2xwuo8u` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FKdhw1vgr0aa2lhb174cj7k5n7o` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKquvm3k3ae8ycaqfvoqk4jxacl` FOREIGN KEY (`id_precio`) REFERENCES `precio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro_compra`
--

LOCK TABLES `parametro_compra` WRITE;
/*!40000 ALTER TABLE `parametro_compra` DISABLE KEYS */;
INSERT INTO `parametro_compra` VALUES (1,'\0',3,NULL,1);
/*!40000 ALTER TABLE `parametro_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametro_venta`
--

DROP TABLE IF EXISTS `parametro_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametro_venta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cobro_venta` bit(1) NOT NULL,
  `cobro_venta_mobile` bit(1) NOT NULL,
  `ticket_cinco` bit(1) NOT NULL,
  `ticket_generico` bit(1) NOT NULL,
  `ticket_siete` bit(1) NOT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `id_condicion` bigint(20) DEFAULT NULL,
  `id_deposito` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  `id_precio` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh0ol25fv3mosb1rr91cbhy5l0` (`id_cliente`),
  KEY `FKhj59iwhc1iwji0hmo2uk5mx1d` (`id_condicion`),
  KEY `FKgy93cf4k4m9r26xvaa98fm3ov` (`id_deposito`),
  KEY `FK3fb49s72f4unpecsp8fgdnkor` (`id_moneda`),
  KEY `FKc4eumt6iqu6n1v6aqn7exw7c6` (`id_precio`),
  KEY `FKig07dg2gjo598vgmbhfw3c2f8` (`id_usuario`),
  CONSTRAINT `FK3fb49s72f4unpecsp8fgdnkor` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKc4eumt6iqu6n1v6aqn7exw7c6` FOREIGN KEY (`id_precio`) REFERENCES `precio` (`id`),
  CONSTRAINT `FKgy93cf4k4m9r26xvaa98fm3ov` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FKh0ol25fv3mosb1rr91cbhy5l0` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKhj59iwhc1iwji0hmo2uk5mx1d` FOREIGN KEY (`id_condicion`) REFERENCES `condicion` (`id`),
  CONSTRAINT `FKig07dg2gjo598vgmbhfw3c2f8` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro_venta`
--

LOCK TABLES `parametro_venta` WRITE;
/*!40000 ALTER TABLE `parametro_venta` DISABLE KEYS */;
INSERT INTO `parametro_venta` VALUES (1,'\0','\0','\0','\0','\0',1,1,5,1,1,1);
/*!40000 ALTER TABLE `parametro_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso`
--

DROP TABLE IF EXISTS `permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permiso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso`
--

LOCK TABLES `permiso` WRITE;
/*!40000 ALTER TABLE `permiso` DISABLE KEYS */;
INSERT INTO `permiso` VALUES (1,'ROLE_CONFIGURACION'),(2,'ROLE_CONFIGURACION_PARAMETRO'),(3,'ROLE_CONFIGURACION_PARAMETRO_GUARDAR'),(4,'ROLE_CONFIGURACION_PARAMETRO_VENTA'),(5,'ROLE_CONFIGURACION_PARAMETRO_VENTA_GUARDAR'),(6,'ROLE_CONFIGURACION_PARAMETRO_COMPRA'),(7,'ROLE_CONFIGURACION_PARAMETRO_COMPRA_GUARDAR'),(8,'ROLE_CONFIGURACION_ACTUALIZAR_LICENCIA'),(9,'ROLE_CREDENCIALES'),(10,'ROLE_CREDENCIALES_USUARIOS'),(11,'ROLE_CREDENCIALES_USUARIOS_GUARDAR'),(12,'ROLE_CREDENCIALES_USUARIOS_MODIFICAR'),(13,'ROLE_CREDENCIALES_USUARIOS_ELIMINAR'),(14,'ROLE_CREDENCIALES_GRUPOS'),(15,'ROLE_CREDENCIALES_GRUPOS_GUARDAR'),(16,'ROLE_CREDENCIALES_GRUPOS_MODIFICAR'),(17,'ROLE_CREDENCIALES_GRUPOS_ELIMINAR'),(18,'ROLE_CREDENCIALES_CAMBIAR_PASSWORD'),(19,'ROLE_FICHAS'),(20,'ROLE_FICHAS_RECALCULAR_STOCK'),(21,'ROLE_FICHAS_INSTRUCTORES'),(22,'ROLE_FICHAS_INSTRUCTORES_GUARDAR'),(23,'ROLE_FICHAS_INSTRUCTORES_MODIFICAR'),(24,'ROLE_FICHAS_INSTRUCTORES_ELIMINAR'),(25,'ROLE_FICHAS_CLASES_GRUPALES'),(26,'ROLE_FICHAS_CLASES_GRUPALES_GUARDAR'),(27,'ROLE_FICHAS_CLASES_GRUPALES_MODIFICAR'),(28,'ROLE_FICHAS_CLASES_GRUPALES_ELIMINAR'),(29,'ROLE_FICHAS_AGENDAR_CITAS'),(30,'ROLE_FICHAS_CONSORCIOS'),(31,'ROLE_FICHAS_CONSORCIOS_GUARDAR'),(32,'ROLE_FICHAS_CONSORCIOS_MODIFICAR'),(33,'ROLE_FICHAS_CONSORCIOS_ELIMINAR'),(34,'ROLE_STOCK'),(35,'ROLE_STOCK_PRODUCTOS'),(36,'ROLE_STOCK_PRODUCTOS_GUARDAR'),(37,'ROLE_STOCK_PRODUCTOS_MODIFICAR'),(38,'ROLE_STOCK_PRODUCTOS_ELIMINAR'),(39,'ROLE_STOCK_AJUSTE_STOCK'),(40,'ROLE_STOCK_AJUSTE_STOCK_GUARDAR'),(41,'ROLE_STOCK_AJUSTE_STOCK_MODIFICAR'),(42,'ROLE_STOCK_AJUSTE_STOCK_ELIMINAR'),(43,'ROLE_STOCK_MARCAS'),(44,'ROLE_STOCK_MARCAS_GUARDAR'),(45,'ROLE_STOCK_MARCAS_MODIFICAR'),(46,'ROLE_STOCK_MARCAS_ELIMINAR'),(47,'ROLE_STOCK_DEPOSITOS'),(48,'ROLE_STOCK_DEPOSITOS_GUARDAR'),(49,'ROLE_STOCK_DEPOSITOS_MODIFICAR'),(50,'ROLE_STOCK_DEPOSITOS_ELIMINAR'),(51,'ROLE_STOCK_LISTA_PRODUCTO'),(52,'ROLE_FINANCIERO'),(53,'ROLE_FINANCIERO_CAJEROS'),(54,'ROLE_FINANCIERO_CAJEROS_GUARDAR'),(55,'ROLE_FINANCIERO_CAJEROS_MODIFICAR'),(56,'ROLE_FINANCIERO_CAJEROS_ELIMINAR'),(57,'ROLE_FINANCIERO_BANCOS'),(58,'ROLE_FINANCIERO_BANCOS_GUARDAR'),(59,'ROLE_FINANCIERO_BANCOS_MODIFICAR'),(60,'ROLE_FINANCIERO_BANCOS_ELIMINAR'),(61,'ROLE_FINANCIERO_CAJEROS'),(62,'ROLE_FINANCIERO_CAJEROS_GUARDAR'),(63,'ROLE_FINANCIERO_CAJEROS_MODIFICAR'),(64,'ROLE_FINANCIERO_CAJEROS_ELIMINAR'),(65,'ROLE_FINANCIERO_PAGO_INSTRUCTOR'),(66,'ROLE_FINANCIERO_CUENTAS'),(67,'ROLE_FINANCIERO_CUENTAS_GUARDAR'),(68,'ROLE_FINANCIERO_CUENTAS_MODIFICAR'),(69,'ROLE_FINANCIERO_CUENTAS_ELIMINAR'),(70,'ROLE_FINANCIERO_MEMBRESIAS'),(71,'ROLE_FINANCIERO_MEMBRESIAS_GUARDAR'),(72,'ROLE_FINANCIERO_MEMBRESIAS_MODIFICAR'),(73,'ROLE_FINANCIERO_MEMBRESIAS_ELIMINAR'),(74,'ROLE_FINANCIERO_COBRO_SERVICIOS'),(75,'ROLE_FINANCIERO_COBRO_SERVICIOS_GUARDAR'),(76,'ROLE_FINANCIERO_COBRO_SERVICIOS_MODIFICAR'),(77,'ROLE_FINANCIERO_COBRO_SERVICIOS_ELIMINAR'),(78,'ROLE_FINANCIERO_GASTOS'),(79,'ROLE_FINANCIERO_GASTOS_GUARDAR'),(80,'ROLE_FINANCIERO_GASTOS_MODIFICAR'),(81,'ROLE_FINANCIERO_GASTOS_ELIMINAR'),(82,'ROLE_FINANCIERO_PAGO_INSTRUCTOR'),(83,'ROLE_FINANCIERO_PAGO_INSTRUCTOR_GUARDAR'),(84,'ROLE_FINANCIERO_PAGO_INSTRUCTOR_MODIFICAR'),(85,'ROLE_FINANCIERO_PAGO_INSTRUCTOR_ELIMINAR'),(86,'ROLE_FINANCIERO_PAGO_VENDEDORES'),(87,'ROLE_FINANCIERO_PAGO_VENDEDORES_GUARDAR'),(88,'ROLE_FINANCIERO_PAGO_VENDEDORES_MODIFICAR'),(89,'ROLE_FINANCIERO_PAGO_VENDEDORES_ELIMINAR'),(90,'ROLE_FINANCIERO_TRANSFERENCIA_EFECTIVO'),(91,'ROLE_FINANCIERO_TRANSFERENCIA_EFECTIVO_GUARDAR'),(92,'ROLE_FINANCIERO_TRANSFERENCIA_EFECTIVO_MODIFICAR'),(93,'ROLE_FINANCIERO_TRANSFERENCIA_EFECTIVO_ELIMINAR'),(94,'ROLE_FINANCIERO_ANTICIPO_CLIENTE'),(95,'ROLE_FINANCIERO_ANTICIPO_CLIENTE_GUARDAR'),(96,'ROLE_FINANCIERO_ANTICIPO_CLIENTE_MODIFICAR'),(97,'ROLE_FINANCIERO_ANTICIPO_CLIENTE_ELIMINAR'),(98,'ROLE_FINANCIERO_COBRO_CLIENTE'),(99,'ROLE_FINANCIERO_COBRO_CLIENTE_GUARDAR'),(100,'ROLE_FINANCIERO_COBRO_CLIENTE_MODIFICAR'),(101,'ROLE_FINANCIERO_COBRO_CLIENTE_ELIMINAR'),(102,'ROLE_FINANCIERO_PAGO_PROVEEDOR'),(103,'ROLE_FINANCIERO_PAGO_PROVEEDOR_GUARDAR'),(104,'ROLE_FINANCIERO_PAGO_PROVEEDOR_MODIFICAR'),(105,'ROLE_FINANCIERO_PAGO_PROVEEDOR_ELIMINAR'),(106,'ROLE_FINANCIERO_MONEDAS'),(107,'ROLE_FINANCIERO_MONEDAS_GUARDAR'),(108,'ROLE_FINANCIERO_MONEDAS_MODIFICAR'),(109,'ROLE_FINANCIERO_MONEDAS_ELIMINAR'),(110,'ROLE_FINANCIERO_COTIZACION'),(111,'ROLE_FINANCIERO_COTIZACION_GUARDAR'),(112,'ROLE_FINANCIERO_COTIZACION_MODIFICAR'),(113,'ROLE_FINANCIERO_COTIZACION_ELIMINAR'),(114,'ROLE_VENTAS'),(115,'ROLE_VENTAS_VENTAS'),(116,'ROLE_VENTAS_VENTAS_GUARDAR'),(117,'ROLE_VENTAS_VENTAS_MODIFICAR'),(118,'ROLE_VENTAS_VENTAS_ELIMINAR'),(119,'ROLE_VENTAS_VENTAS_IMPRIMIR'),(120,'ROLE_VENTAS_VENTAS_MOBILE'),(121,'ROLE_VENTAS_VENTAS_MOBILE_GUARDAR'),(122,'ROLE_VENTAS_VENTAS_MOBILE_MODIFICAR'),(123,'ROLE_VENTAS_VENTAS_MOBILE_ELIMINAR'),(124,'ROLE_VENTAS_TIMBRADOS'),(125,'ROLE_VENTAS_TIMBRADOS_GUARDAR'),(126,'ROLE_VENTAS_TIMBRADOS_MODIFICAR'),(127,'ROLE_VENTAS_TIMBRADOS_ELIMINAR'),(128,'ROLE_VENTAS_CLIENTES'),(129,'ROLE_VENTAS_CLIENTES_GUARDAR'),(130,'ROLE_VENTAS_CLIENTES_MODIFICAR'),(131,'ROLE_VENTAS_CLIENTES_ELIMINAR'),(132,'ROLE_VENTAS_VENDEDORES'),(133,'ROLE_VENTAS_VENDEDORES_GUARDAR'),(134,'ROLE_VENTAS_VENDEDORES_MODIFICAR'),(135,'ROLE_VENTAS_VENDEDORES_ELIMINAR'),(136,'ROLE_VENTAS_PRECIOS'),(137,'ROLE_VENTAS_PRECIOS_GUARDAR'),(138,'ROLE_VENTAS_PRECIOS_MODIFICAR'),(139,'ROLE_VENTAS_PRECIOS_ELIMINAR'),(140,'ROLE_VENTAS_AJUSTE_PRECIO'),(141,'ROLE_VENTAS_AJUSTE_PRECIO_GUARDAR'),(142,'ROLE_VENTAS_AJUSTE_PRECIO_MODIFICAR'),(143,'ROLE_VENTAS_AJUSTE_PRECIO_ELIMINAR'),(144,'ROLE_COMPRAS'),(145,'ROLE_COMPRAS_PROVEEDORES'),(146,'ROLE_COMPRAS_PROVEEDORES_GUARDAR'),(147,'ROLE_COMPRAS_PROVEEDORES_MODIFICAR'),(148,'ROLE_COMPRAS_PROVEEDORES_ELIMINAR'),(149,'ROLE_COMPRAS_COMPRAS'),(150,'ROLE_COMPRAS_COMPRAS_GUARDAR'),(151,'ROLE_COMPRAS_COMPRAS_MODIFICAR'),(152,'ROLE_COMPRAS_COMPRAS_ELIMINAR'),(153,'ROLE_REPORTES'),(154,'ROLE_REPORTES_MORA_CLIENTES'),(155,'ROLE_REPORTES_EXTRACTO_INSTRUCTOR'),(156,'ROLE_REPORTES_EXTRACTO_PRODUCTO'),(157,'ROLE_REPORTES_SEGUIMIENTO_COSTO'),(158,'ROLE_REPORTES_TOTALES_VENTAS'),(159,'ROLE_REPORTES_VENTAS_POR_PRODUCTO'),(160,'ROLE_REPORTES_INVENTARIO'),(161,'ROLE_REPORTES_STOCK_VALORIZADO'),(162,'ROLE_REPORTES_CUENTAS_POR_COBRAR'),(163,'ROLE_INICIO_CLIENTES'),(164,'ROLE_INICIO_GASTOS'),(165,'ROLE_INICIO_MOVIMIENTO_CAJA'),(166,'ROLE_INICIO_MEMBRESIA'),(167,'ROLE_INICIO_CITAS'),(168,'ROLE_INICIO_VENTAS'),(169,'ROLE_INICIO_VENTAS_MOBILE'),(170,'ROLE_INICIO_COBRO_CLIENTE'),(171,'ROLE_REPORTES_CUENTAS_POR_PAGAR'),(172,'ROLE_FINANCIERO_PLANILLA'),(173,'ROLE_FINANCIERO_PLANILLA_GUARDAR'),(174,'ROLE_FINANCIERO_PLANILLA_MODIFICAR'),(175,'ROLE_FINANCIERO_PLANILLA_ELIMINAR'),(176,'ROLE_FICHAS_RECALCULAR_MOVIMIETO_VENDEDOR'),(177,'ROLE_STOCK_TRANSFERENCIA_STOCK'),(178,'ROLE_STOCK_TRANSFERENCIA_STOCK_GUARDAR'),(179,'ROLE_STOCK_TRANSFERENCIA_STOCK_MODIFICAR'),(180,'ROLE_STOCK_TRANSFERENCIA_STOCK_ELIMINAR'),(181,'ROLE_STOCK_AJUSTE_LOTE'),(182,'ROLE_STOCK_AJUSTE_LOTE_GUARDAR'),(183,'ROLE_STOCK_AJUSTE_LOTE_MODIFICAR'),(184,'ROLE_STOCK_AJUSTE_LOTE_ELIMINAR'),(185,'ROLE_VENTAS_NOTA_CREDITO'),(186,'ROLE_VENTAS_NOTA_CREDITO_GUARDAR'),(187,'ROLE_VENTAS_NOTA_CREDITO_MODIFICAR'),(188,'ROLE_VENTAS_NOTA_CREDITO_ELIMINAR'),(189,'ROLE_VENTAS_PRESUPUESTO_VENTAS'),(190,'ROLE_VENTAS_PRESUPUESTO_VENTAS_GUARDAR'),(191,'ROLE_VENTAS_PRESUPUESTO_VENTAS_MODIFICAR'),(192,'ROLE_VENTAS_PRESUPUESTO_VENTAS_ELIMINAR'),(193,'ROLE_COMPRAS_PRESUPUESTO_COMPRAS'),(194,'ROLE_COMPRAS_PRESUPUESTO_COMPRAS_GUARDAR'),(195,'ROLE_COMPRAS_PRESUPUESTO_COMPRAS_MODIFICAR'),(196,'ROLE_COMPRAS_PRESUPUESTO_COMPRAS_ELIMINAR'),(197,'ROLE_REPORTES_ARQUEO_CAJA'),(198,'ROLE_REPORTES_VENTA_POR_VENDEDOR_COMISION'),(199,'ROLE_REPORTES_LISTADO_AJUSTE_PRECIO'),(200,'ROLE_REPORTES_AUDITORIA'),(201,'ROLE_REPORTES_COBROS_REALIZADOS'),(202,'ROLE_REPORTES_EXTRACTO_VENDEDORES'),(203,'ROLE_REPORTES_MOVIMIENTO_LOTE'),(204,'ROLE_REPORTES_LISTADO_LOTE');
/*!40000 ALTER TABLE `permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planilla`
--

DROP TABLE IF EXISTS `planilla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planilla` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cerrado` bit(1) NOT NULL,
  `diferencia` decimal(35,16) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `total` decimal(35,16) DEFAULT NULL,
  `total_rendido` decimal(35,16) DEFAULT NULL,
  `id_cuenta` bigint(20) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `fecha_cierre` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK45nwfitrpq0tsnq56wlja355y` (`id_cuenta`),
  KEY `FKt89ituk4sv5jxa1avaal73qgs` (`id_usuario`),
  CONSTRAINT `FK45nwfitrpq0tsnq56wlja355y` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKt89ituk4sv5jxa1avaal73qgs` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planilla`
--

LOCK TABLES `planilla` WRITE;
/*!40000 ALTER TABLE `planilla` DISABLE KEYS */;
/*!40000 ALTER TABLE `planilla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precio`
--

DROP TABLE IF EXISTS `precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc3ek4q1jo3u2v8usq6hqadtev` (`id_moneda`),
  CONSTRAINT `FKc3ek4q1jo3u2v8usq6hqadtev` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precio`
--

LOCK TABLES `precio` WRITE;
/*!40000 ALTER TABLE `precio` DISABLE KEYS */;
INSERT INTO `precio` VALUES (1,'Lista General',1),(2,'Lista atacado',1);
/*!40000 ALTER TABLE `precio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto_compra`
--

DROP TABLE IF EXISTS `presupuesto_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presupuesto_compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `id_deposito` bigint(20) NOT NULL,
  `id_moneda` bigint(20) NOT NULL,
  `id_proveedor` bigint(20) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm3scfufp5xhb6cyoa92ps7ecm` (`id_deposito`),
  KEY `FKjslb0abnousdtve65ik5dgkde` (`id_moneda`),
  KEY `FKo6xwuq227odeonc5i34strt7l` (`id_proveedor`),
  KEY `FKljt2ot7y6283dts121vnak88n` (`id_usuario`),
  CONSTRAINT `FKjslb0abnousdtve65ik5dgkde` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKljt2ot7y6283dts121vnak88n` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKm3scfufp5xhb6cyoa92ps7ecm` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FKo6xwuq227odeonc5i34strt7l` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto_compra`
--

LOCK TABLES `presupuesto_compra` WRITE;
/*!40000 ALTER TABLE `presupuesto_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `presupuesto_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto_venta`
--

DROP TABLE IF EXISTS `presupuesto_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presupuesto_venta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `condicion_venta` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `id_deposito` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  `id_precio` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_vendedor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa3dw7i2e32e22lgmanqp3ui4k` (`id_cliente`),
  KEY `FKaiucfu4qlb7f09j3a0a028n97` (`id_deposito`),
  KEY `FKibmn4p2d98rkpciq94whi214p` (`id_moneda`),
  KEY `FKokiuwnwfj8ygl0i15uv1i1jg8` (`id_precio`),
  KEY `FK42qacw0xka6yqrg8hkdav2xhl` (`id_usuario`),
  KEY `FKc6i8jybklj67ljjyklb8pisvq` (`id_vendedor`),
  CONSTRAINT `FK42qacw0xka6yqrg8hkdav2xhl` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKa3dw7i2e32e22lgmanqp3ui4k` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKaiucfu4qlb7f09j3a0a028n97` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FKc6i8jybklj67ljjyklb8pisvq` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`),
  CONSTRAINT `FKibmn4p2d98rkpciq94whi214p` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKokiuwnwfj8ygl0i15uv1i1jg8` FOREIGN KEY (`id_precio`) REFERENCES `precio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto_venta`
--

LOCK TABLES `presupuesto_venta` WRITE;
/*!40000 ALTER TABLE `presupuesto_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `presupuesto_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `costo` decimal(35,16) DEFAULT NULL,
  `costo_calculo` int(11) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `gravado` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `pesable` bit(1) NOT NULL,
  `porc_gravado` int(11) DEFAULT NULL,
  `id_marca` bigint(20) NOT NULL,
  `comision` decimal(19,2) DEFAULT NULL,
  `con_lote` bit(1) NOT NULL,
  `servicio` bit(1) NOT NULL,
  `stock_minimo` decimal(19,2) DEFAULT NULL,
  `id_moneda` bigint(20) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpmfw7ds3rfuwge05ehb216r82` (`id_marca`),
  KEY `FKjhnhu11h92o6gjd4eha5xux79` (`id_usuario`),
  KEY `FKqqodql4mhtwlwonp3m18cx1w8` (`id_moneda`),
  CONSTRAINT `FKjhnhu11h92o6gjd4eha5xux79` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKpmfw7ds3rfuwge05ehb216r82` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id`),
  CONSTRAINT `FKqqodql4mhtwlwonp3m18cx1w8` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `producto_aft_upd` AFTER UPDATE ON `producto` FOR EACH ROW begin 
	update item_precio set costo = new.costo where id_producto = new.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `documento` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'','80109602-2','Ibra import S.R.L.'),(2,'','xx','PRUEBA');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seguimiento_costo`
--

DROP TABLE IF EXISTS `seguimiento_costo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seguimiento_costo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad_actual` decimal(19,2) DEFAULT NULL,
  `cantidad_compra` decimal(19,2) DEFAULT NULL,
  `cantidad_total` decimal(19,2) DEFAULT NULL,
  `costo_actual` decimal(35,16) DEFAULT NULL,
  `costo_calculo` decimal(35,16) DEFAULT NULL,
  `costo_total` decimal(35,16) DEFAULT NULL,
  `detalle` bigint(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `precio_compra` decimal(19,2) DEFAULT NULL,
  `registro` bigint(20) DEFAULT NULL,
  `sumatoria_actual` decimal(35,16) DEFAULT NULL,
  `sumatoria_compra` decimal(35,16) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKojkli03oa2jk5k6841p0sgw5t` (`id_producto`),
  CONSTRAINT `FKojkli03oa2jk5k6841p0sgw5t` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimiento_costo`
--

LOCK TABLES `seguimiento_costo` WRITE;
/*!40000 ALTER TABLE `seguimiento_costo` DISABLE KEYS */;
/*!40000 ALTER TABLE `seguimiento_costo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_deposito`
--

DROP TABLE IF EXISTS `stock_deposito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_deposito` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(19,2) DEFAULT NULL,
  `reserva` decimal(19,2) DEFAULT NULL,
  `id_deposito` bigint(20) DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKce97vpsm0h0itjqbpd1uh4qcv` (`id_deposito`),
  KEY `FKe4cwj1t160vgscejjovi2pgfm` (`id_producto`),
  CONSTRAINT `FKce97vpsm0h0itjqbpd1uh4qcv` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FKe4cwj1t160vgscejjovi2pgfm` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_deposito`
--

LOCK TABLES `stock_deposito` WRITE;
/*!40000 ALTER TABLE `stock_deposito` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_deposito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timbrado`
--

DROP TABLE IF EXISTS `timbrado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timbrado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `expedicion` varchar(255) NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `numeracion_fin` int(11) NOT NULL,
  `numeracion_ini` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timbrado`
--

LOCK TABLES `timbrado` WRITE;
/*!40000 ALTER TABLE `timbrado` DISABLE KEYS */;
/*!40000 ALTER TABLE `timbrado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencia_efectivo`
--

DROP TABLE IF EXISTS `transferencia_efectivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transferencia_efectivo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `importe` decimal(19,2) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_cuenta_destino` bigint(20) NOT NULL,
  `id_cuenta_origen` bigint(20) NOT NULL,
  `id_condicion` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  `id_moneda_destino` bigint(20) DEFAULT NULL,
  `id_planilla` bigint(20) DEFAULT NULL,
  `id_planilla_destino` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK96e4hbhjipcjdifreln7xev0m` (`id_cuenta_destino`),
  KEY `FKafmuj4unae1sdbd7a00gys6mv` (`id_cuenta_origen`),
  KEY `FKje5f71gemh3cvgve4kh64aq93` (`id_condicion`),
  KEY `FKprwmeamrl25lm1mgjfbc2ul9u` (`id_moneda`),
  KEY `FKkj5m3wbjkb2s74c7iyaph7r43` (`id_moneda_destino`),
  KEY `FK6ab7dgssl38lmir1dgpjcyo2x` (`id_planilla`),
  KEY `FK4xgqkpbbxv0mr04gc83jiu68g` (`id_planilla_destino`),
  CONSTRAINT `FK4xgqkpbbxv0mr04gc83jiu68g` FOREIGN KEY (`id_planilla_destino`) REFERENCES `planilla` (`id`),
  CONSTRAINT `FK6ab7dgssl38lmir1dgpjcyo2x` FOREIGN KEY (`id_planilla`) REFERENCES `planilla` (`id`),
  CONSTRAINT `FK96e4hbhjipcjdifreln7xev0m` FOREIGN KEY (`id_cuenta_destino`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKafmuj4unae1sdbd7a00gys6mv` FOREIGN KEY (`id_cuenta_origen`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKje5f71gemh3cvgve4kh64aq93` FOREIGN KEY (`id_condicion`) REFERENCES `condicion` (`id`),
  CONSTRAINT `FKkj5m3wbjkb2s74c7iyaph7r43` FOREIGN KEY (`id_moneda_destino`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKprwmeamrl25lm1mgjfbc2ul9u` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencia_efectivo`
--

LOCK TABLES `transferencia_efectivo` WRITE;
/*!40000 ALTER TABLE `transferencia_efectivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `transferencia_efectivo` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger transferencia_efectivo_bef_ins before insert on transferencia_efectivo
for each row
begin 
	declare v_id_planilla_cuenta_origen bigint;
    declare v_id_planilla_cuenta_destino bigint;
    select id into v_id_planilla_cuenta_origen from planilla where id_cuenta = new.id_cuenta_origen and cerrado = 0;
    select id into v_id_planilla_cuenta_destino from planilla where id_cuenta = new.id_cuenta_destino and cerrado = 0;
	set new.id_planilla = v_id_planilla_cuenta_origen;
    set new.id_planilla_destino = v_id_planilla_cuenta_destino;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER transferencia_efectivo_aft_insert  AFTER  INSERT ON transferencia_efectivo
FOR EACH ROW 
begin 
declare v_cuenta_origen varchar(255);
declare v_cuenta_destino varchar(255);
declare v_id_moneda_cuenta_origen bigint;
declare v_id_moneda_cuenta_destino bigint;
declare v_cobranza_origen bit(1);
declare v_cobranza_destino bit(1);
select nombre into v_cuenta_origen from cuenta where id = new.id_cuenta_origen;
select nombre into v_cuenta_destino from cuenta where id = new.id_cuenta_destino;
select id_moneda into v_id_moneda_cuenta_origen from cuenta where id = new.id_cuenta_origen;
select id_moneda into v_id_moneda_cuenta_destino from cuenta where id = new.id_cuenta_destino;
select caja_cobranza into v_cobranza_origen from cuenta where id = new.id_cuenta_origen;
select caja_cobranza into v_cobranza_destino from cuenta where id = new.id_cuenta_destino;


if(v_cobranza_origen = true)then
	insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_transferencia_efectivo)
	values(0,f_cotizar(new.id_moneda_destino, v_id_moneda_cuenta_origen, new.fecha, new.importe, 0),new.fecha,
    concat('transferencia a la cuenta: ',v_cuenta_destino),new.id_cuenta_origen,new.id);
else
	insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_transferencia_efectivo)
	values(0,new.importe,new.fecha, concat('transferencia a la cuenta: ',v_cuenta_destino),new.id_cuenta_origen,new.id);
end if;
if(v_cobranza_destino = true) then
	insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_transferencia_efectivo)
	values(f_cotizar(new.id_moneda, v_id_moneda_cuenta_destino, new.fecha, new.importe, 0),0,new.fecha,concat('transferencia desde cuenta: ',v_cuenta_origen),new.id_cuenta_destino,new.id);
else
	 insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_transferencia_efectivo)
	values(f_cotizar(new.id_moneda, new.id_moneda_destino, new.fecha, new.importe, 0),0,new.fecha,concat('transferencia desde cuenta: ',v_cuenta_origen),new.id_cuenta_destino,new.id);
end if;

    

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger transferencia_efectivo_bef_up before update on transferencia_efectivo
for each row
begin 
	declare v_id_planilla_cuenta_origen bigint;
    declare v_id_planilla_cuenta_destino bigint;
    select id into v_id_planilla_cuenta_origen from planilla where id_cuenta = new.id_cuenta_origen and cerrado = 0;
    select id into v_id_planilla_cuenta_destino from planilla where id_cuenta = new.id_cuenta_destino and cerrado = 0;
	set new.id_planilla = v_id_planilla_cuenta_origen;
    set new.id_planilla_destino = v_id_planilla_cuenta_destino;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER transferencia_efectivo_aft_upd  AFTER  UPDATE ON transferencia_efectivo
FOR EACH ROW 
begin
declare v_cuenta_origen varchar(255);
declare v_cuenta_destino varchar(255);
declare v_id_moneda_cuenta_origen bigint;
declare v_id_moneda_cuenta_destino bigint;
declare v_cobranza_origen bit(1);
declare v_cobranza_destino bit(1);

select nombre into v_cuenta_origen from cuenta where id = new.id_cuenta_origen;
select nombre into v_cuenta_destino from cuenta where id = new.id_cuenta_destino;
select id_moneda into v_id_moneda_cuenta_origen from cuenta where id = new.id_cuenta_origen;
select id_moneda into v_id_moneda_cuenta_destino from cuenta where id = new.id_cuenta_destino;
select caja_cobranza into v_cobranza_origen from cuenta where id = new.id_cuenta_origen;
select caja_cobranza into v_cobranza_destino from cuenta where id = new.id_cuenta_destino;

delete from movimiento_caja where id_transferencia_efectivo = new.id;

if(v_cobranza_origen = true)then
	insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_transferencia_efectivo)
	values(0,f_cotizar(new.id_moneda_destino, v_id_moneda_cuenta_origen, new.fecha, new.importe, 0),new.fecha,
    concat('transferencia a la cuenta: ',v_cuenta_destino),new.id_cuenta_origen,new.id);
else
	insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_transferencia_efectivo)
	values(0,new.importe,new.fecha, concat('transferencia a la cuenta: ',v_cuenta_destino),new.id_cuenta_origen,new.id);
end if;
if(v_cobranza_destino = true) then
	insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_transferencia_efectivo)
	values(f_cotizar(new.id_moneda, v_id_moneda_cuenta_destino, new.fecha, new.importe, 0),0,new.fecha,concat('transferencia desde cuenta: ',v_cuenta_origen),new.id_cuenta_destino,new.id);
else
	 insert into movimiento_caja(credito,debito,fecha,observacion,cuenta_id,id_transferencia_efectivo)
	values(f_cotizar(new.id_moneda, new.id_moneda_destino, new.fecha, new.importe, 0),0,new.fecha,concat('transferencia desde cuenta: ',v_cuenta_origen),new.id_cuenta_destino,new.id);
end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `transferencia_efectivo_bef_del` BEFORE DELETE ON `transferencia_efectivo` FOR EACH ROW begin 
	delete from movimiento_caja where id_transferencia_efectivo = old.id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `transferencia_stock`
--

DROP TABLE IF EXISTS `transferencia_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transferencia_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `id_deposito_destino` bigint(20) NOT NULL,
  `id_deposito_origen` bigint(20) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKexqhvh1so1ensjqc8804098u5` (`id_deposito_destino`),
  KEY `FKmyjvne5l1whsd8qs30t4k2ynn` (`id_deposito_origen`),
  KEY `FKjk3eiatlbsc3eosssdww0g56g` (`id_usuario`),
  CONSTRAINT `FKexqhvh1so1ensjqc8804098u5` FOREIGN KEY (`id_deposito_destino`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FKjk3eiatlbsc3eosssdww0g56g` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKmyjvne5l1whsd8qs30t4k2ynn` FOREIGN KEY (`id_deposito_origen`) REFERENCES `deposito` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencia_stock`
--

LOCK TABLES `transferencia_stock` WRITE;
/*!40000 ALTER TABLE `transferencia_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `transferencia_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `supervisor` bit(1) NOT NULL,
  `id_cajero` bigint(20) DEFAULT NULL,
  `id_cuenta` bigint(20) DEFAULT NULL,
  `id_instructor` bigint(20) DEFAULT NULL,
  `id_vendedor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfdqcjs9ps4wpqcfj8fu3fjlfg` (`id_cajero`),
  KEY `FK16y6ku0k2alscgp71om4gtm08` (`id_cuenta`),
  KEY `FKru36s3e0nynlerk2slt7pvnft` (`id_instructor`),
  KEY `FKe1xxh6fkgw92aw917uapa6s30` (`id_vendedor`),
  CONSTRAINT `FK16y6ku0k2alscgp71om4gtm08` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`),
  CONSTRAINT `FKe1xxh6fkgw92aw917uapa6s30` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`),
  CONSTRAINT `FKfdqcjs9ps4wpqcfj8fu3fjlfg` FOREIGN KEY (`id_cajero`) REFERENCES `cajero` (`id`),
  CONSTRAINT `FKru36s3e0nynlerk2slt7pvnft` FOREIGN KEY (`id_instructor`) REFERENCES `instructor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'','root','$2a$10$j9pqGfg6IdOUtVsDbVWZHOSWFoU2ItY2fxt.jYLHeOyM6wQlVU6Za','',1,1,NULL,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_grupo`
--

DROP TABLE IF EXISTS `usuario_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_grupo` (
  `id_usuario` bigint(20) NOT NULL,
  `id_grupo` bigint(20) NOT NULL,
  KEY `FKcu6om65mvqr6ct95ijgqgx7ww` (`id_grupo`),
  KEY `FK9huj1upwjyabwkwnpnhnernnu` (`id_usuario`),
  CONSTRAINT `FK9huj1upwjyabwkwnpnhnernnu` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKcu6om65mvqr6ct95ijgqgx7ww` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_grupo`
--

LOCK TABLES `usuario_grupo` WRITE;
/*!40000 ALTER TABLE `usuario_grupo` DISABLE KEYS */;
INSERT INTO `usuario_grupo` VALUES (1,1);
/*!40000 ALTER TABLE `usuario_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `documento` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `porcentaje` decimal(19,2) DEFAULT NULL,
  `saldo` decimal(19,2) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `supervisor` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (1,'','Xx','Admin',0.00,0.00,NULL,'\0');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `condicion_venta` int(11) DEFAULT NULL,
  `factura` bit(1) NOT NULL,
  `fecha` date NOT NULL,
  `impreso` bit(1) NOT NULL,
  `nro_factura` int(11) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_consorcio` bigint(20) DEFAULT NULL,
  `id_deposito` bigint(20) NOT NULL,
  `id_precio` bigint(20) NOT NULL,
  `id_timbrado` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_vendedor` bigint(20) NOT NULL,
  `nc` bit(1) NOT NULL,
  `nc_venta` bigint(20) DEFAULT NULL,
  `id_moneda` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsor2qmi3thao7a8or49vlohp9` (`id_cliente`),
  KEY `FKfs2f5oobxwmpg4y8ym3tgcn6s` (`id_consorcio`),
  KEY `FKrjh5frf97mub9l16fn1yioou4` (`id_deposito`),
  KEY `FKoglm8arrei74pu408vodlqnhj` (`id_precio`),
  KEY `FKran5dsvfe9cd0orijicsxf6tq` (`id_timbrado`),
  KEY `FKoilu1fdfgmu7sfe0spen005ms` (`id_usuario`),
  KEY `FKcfsi2siyp2yu3eaij0j6ivmmg` (`id_vendedor`),
  KEY `FKewdow2pwbrbt0swkpww7b2clk` (`id_moneda`),
  CONSTRAINT `FKcfsi2siyp2yu3eaij0j6ivmmg` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedor` (`id`),
  CONSTRAINT `FKewdow2pwbrbt0swkpww7b2clk` FOREIGN KEY (`id_moneda`) REFERENCES `moneda` (`id`),
  CONSTRAINT `FKfs2f5oobxwmpg4y8ym3tgcn6s` FOREIGN KEY (`id_consorcio`) REFERENCES `consorcio` (`id`),
  CONSTRAINT `FKoglm8arrei74pu408vodlqnhj` FOREIGN KEY (`id_precio`) REFERENCES `precio` (`id`),
  CONSTRAINT `FKoilu1fdfgmu7sfe0spen005ms` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKran5dsvfe9cd0orijicsxf6tq` FOREIGN KEY (`id_timbrado`) REFERENCES `timbrado` (`id`),
  CONSTRAINT `FKrjh5frf97mub9l16fn1yioou4` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`),
  CONSTRAINT `FKsor2qmi3thao7a8or49vlohp9` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER venta_aft_insert  AFTER  INSERT ON venta
FOR EACH ROW 
begin
	declare v_total_consorcio decimal(19,2);
    declare v_total_venta decimal (19,2);
    if(new.id_consorcio is not null) then
		select (consorcio.monto_mensual*consorcio.meses) into v_total_consorcio 
		from consorcio left join item_consorcio on consorcio.id = item_consorcio.id_consorcio
		where item_consorcio.id_cliente =new.id_cliente and consorcio.id = new.id_consorcio; 
        if(v_total_consorcio<new.total) then
			set v_total_venta = new.total-v_total_consorcio;
            insert into cuenta_por_cobrar (fecha,importe,importe_cobrado,id_venta,id_moneda)
			values(new.fecha,v_total_venta,0,new.id,new.id_moneda);
        end if;
        update item_consorcio set procesado = true where id_cliente = new.id_cliente and id_consorcio = new.id_consorcio;
    else
		insert into cuenta_por_cobrar (fecha,importe,importe_cobrado,id_venta,id_moneda)
		values(new.fecha,new.total,0,new.id,new.id_moneda);
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER venta_aft_upd  AFTER  UPDATE ON venta
FOR EACH ROW 
begin
	declare v_total_consorcio decimal(19,2);
    declare v_total_venta decimal (19,2);
	if(old.id_consorcio is not null) then
		if(new.id_consorcio is not null) then
			select (consorcio.monto_mensual*consorcio.meses) into v_total_consorcio 
			from consorcio left join item_consorcio on consorcio.id = item_consorcio.id_consorcio
			where item_consorcio.id_cliente =new.id_cliente and consorcio.id = new.id_consorcio;
            
            if(v_total_consorcio<new.total) then
				set v_total_venta = new.total-v_total_consorcio;
				update cuenta_por_cobrar set importe = v_total_venta where id_venta = new.id;
			end if;
            update item_consorcio set procesado = true where id_cliente = new.id_cliente and id_consorcio = new.id_consorcio;
		else
			insert into cuenta_por_cobrar (fecha,importe,importe_cobrado,id_venta)
			values(new.fecha,new.total,0,new.id);
			update item_consorcio set procesado = false where id_cliente = old.id_cliente and id_consorcio = old.id_consorcio;
        end if;
	else
		if(new.id_consorcio is not null) then
			select (consorcio.monto_mensual*consorcio.meses) into v_total_consorcio 
			from consorcio left join item_consorcio on consorcio.id = item_consorcio.id_consorcio
			where item_consorcio.id_cliente =new.id_cliente and consorcio.id = new.id_consorcio;
            
            if(v_total_consorcio<new.total) then
				set v_total_venta = new.total-v_total_consorcio;
				update cuenta_por_cobrar set importe = v_total_venta where id_venta = new.id;
			else
				delete from cuenta_por_cobrar where id_venta = new.id;
			end if;
            update item_consorcio set procesado = true where id_cliente = new.id_cliente and id_consorcio = new.id_consorcio;
            
		else
			update cuenta_por_cobrar set importe = new.total where id_venta = new.id;
		end if;
    end if;
	call pr_movimiento_vendedor_venta(new.id,new.fecha,new.id_vendedor);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER venta_bef_del  BEFORE  DELETE ON venta
FOR EACH ROW 
begin 

    delete from cuenta_por_cobrar where id_venta = old.id;
    update item_consorcio set procesado = false where id_cliente = old.id_cliente and id_consorcio = old.id_consorcio;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'meta'
--
/*!50003 DROP FUNCTION IF EXISTS `f_cantidad_stock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `f_cantidad_stock`(v_producto bigint, v_deposito bigint) RETURNS decimal(19,3)
begin 
	declare v_cantidad decimal(19,3);
	select(
		ifnull((select sum(cantidad) from item_compra,compra where compra.id = item_compra.id_compra and id_producto = v_producto and id_deposito = v_deposito),0)-
		ifnull((select sum(cantidad) from item_venta,venta where id_producto = v_producto and id_deposito =v_deposito and venta.id = item_venta.id_venta),0)+
		ifnull((select sum(sumar) from item_ajuste_stock,ajuste_stock where id_producto =v_producto and id_deposito = v_deposito and item_ajuste_stock.id_ajuste_stock = ajuste_stock.id),0)-
		ifnull((select sum(restar) from item_ajuste_stock , ajuste_stock where id_producto =v_producto and id_deposito =v_deposito and item_ajuste_stock.id_ajuste_stock = ajuste_stock.id),0)
	) into v_cantidad;
	return v_cantidad;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `f_cotizar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`::1` FUNCTION `f_cotizar`(a_moneda_origen bigint,a_moneda_destino bigint,a_fecha date,a_valor decimal(35,16),a_cotizacion decimal(35,16)) RETURNS decimal(35,16)
begin 
	declare v_valor decimal(35,16);
    declare v_valor_cotizacion decimal(35,16);
    declare v_dividir bit(1);
    declare v_multiplicar bit(1);
    declare v_cantidad int;
    if(a_moneda_origen = a_moneda_destino)then
		set v_valor = a_valor;
	else
		if(a_cotizacion =0) then
			select count(*) into v_cantidad  from cotizacion where id_moneda_origen = a_moneda_origen
					and id_moneda_destino = a_moneda_destino and fecha = a_fecha;
			if(v_cantidad>0)then
				select valor,dividir,multiplicar into v_valor_cotizacion,v_dividir,v_multiplicar from cotizacion where id_moneda_origen = a_moneda_origen
					and id_moneda_destino = a_moneda_destino and fecha = a_fecha;
				if(v_dividir=true)then
					set v_valor = a_valor / v_valor_cotizacion;
				end if;
				if(v_multiplicar=true)then
					set v_valor = a_valor * v_valor_cotizacion;
				end if;
			else
				set v_valor = 0;
			end if;
        else
			select count(*) into v_cantidad  from cotizacion where id_moneda_origen = a_moneda_origen
					and id_moneda_destino = a_moneda_destino and fecha = a_fecha;
            if(v_cantidad>0) then
				select valor,dividir,multiplicar into v_valor_cotizacion,v_dividir,v_multiplicar from cotizacion where id_moneda_origen = a_moneda_origen
						and id_moneda_destino = a_moneda_destino and fecha = a_fecha;
				if(v_dividir=true)then
					set v_valor = a_valor / a_cotizacion;
				end if;
				if(v_multiplicar=true)then
					set v_valor = a_valor * a_cotizacion;
				end if;
			else
				set v_valor =0;
            end if;
        end if;
    end if;
    return v_valor;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calclular_costo_venta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`::1` PROCEDURE `calclular_costo_venta`(a_producto bigint,a_fecha_desde date , a_fecha_hasta date)
begin
	declare v_max_id bigint;
    declare v_contador bigint;
    declare v_id_extracto bigint;
    declare v_fecha_extracto date;
    declare v_id_detalle_extracto bigint;
    declare v_id_detalle_extracto_compra bigint;
    declare v_costo_calculo decimal(19,2);
    
	drop temporary table if exists extracto_producto_venta;
    create temporary table extracto_producto_venta(id bigint, id_extracto bigint,fecha date, id_detalle bigint);
    set @id:= 0;
    
    insert into extracto_producto_venta select
    @id := @id+1 as id
	,ep.id as id_extracto
    ,ep.fecha
    ,ep.id_detalle
	from extracto_producto ep where id_producto = a_producto and fecha between(a_fecha_desde)and(a_fecha_hasta) 
															 and observacion = 'venta' order by fecha asc;
	select count(id) into v_max_id from extracto_producto_venta; 
    set v_contador =1;
    
    while(v_contador <= v_max_id) do
		select id_extracto,fecha,id_detalle into v_id_extracto, v_fecha_extracto, v_id_detalle_extracto
			from extracto_producto_venta where id = v_contador;
            
		select max(id_detalle) into v_id_detalle_extracto_compra from extracto_producto 
			where fecha = v_fecha_extracto and id < v_id_extracto and observacion = 'compra' and id_producto = a_producto;
            
        if(v_id_detalle_extracto_compra is null) then 
			select max(id_detalle) into v_id_detalle_extracto_compra from extracto_producto 
				where id < v_id_extracto  and id_producto = a_producto and observacion = 'compra';
			if(v_id_detalle_extracto_compra is not null) then
				select costo_calculo into v_costo_calculo from seguimiento_costo where id_producto = a_producto 
                and detalle = v_id_detalle_extracto_compra and observacion = 'compra';
            else
				set v_costo_calculo = 0;
            end if;
		else
			select costo_calculo into v_costo_calculo from seguimiento_costo where id_producto = a_producto 
                and detalle = v_id_detalle_extracto_compra and observacion = 'compra';
        end if;
        update item_venta set costo = v_costo_calculo where id = v_id_detalle_extracto;
        set v_contador =v_contador+1;
    end while;
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `calcular_costo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`::1` PROCEDURE `calcular_costo`(a_producto bigint)
begin
	declare v_costo_calculo int;
    declare v_id_compra bigint;
    declare v_precio decimal(19,2);
    declare v_contador bigint;
    declare v_cantidad bigint;
	declare v_cantidad_compra decimal(19,2);
    declare v_precio_compra decimal(19,2);
    declare v_max_segumiento_costo bigint;
    declare v_cantidad_actual decimal(19,2);
    declare v_fecha date;
    declare v_costo_actual decimal(19,2);
    declare v_id_item_compra bigint;
    declare v_id_venta bigint;
    declare v_id bigint;
	declare v_id_extracto_producto_compra bigint;
    declare v_id_extracto_producto_venta bigint;
    declare v_new_id_venta bigint;
    set sql_safe_updates =0;
    select costo_calculo into v_costo_calculo from producto where id = a_producto;
    if(v_costo_calculo = 0)then
		select max(id_compra) into v_id_compra from item_compra where id_producto = a_producto;
        select precio into v_precio from item_compra where id_compra = v_id_compra and id_producto = a_producto;
        if(v_precio is null) then
			update producto set costo = 0 where id = a_producto;
		else
			update producto set costo = v_precio where id = a_producto;
        end if;
        
    end if;
    if(v_costo_calculo = 1) then
		delete from seguimiento_costo where id_producto = a_producto;
		drop  table if exists temp_item_compra;
        drop temporary table if exists temp_item_compra_sid;
        create temporary table temp_item_compra_sid(cantidad decimal(19,2),precio decimal (19,2),
												fecha date,id_compra bigint,id_producto bigint,id_item_compra bigint);
        create  table temp_item_compra(id bigint,cantidad decimal(19,2),precio decimal (19,2),
												fecha date,id_compra bigint,id_producto bigint,id_item_compra bigint);
		
        set @id:=0;
		insert into temp_item_compra_sid select item_compra.cantidad as cantidad,item_compra.precio,compra.fecha,compra.id as id_compra,
										id_producto,item_compra.id as id_item_compra
										from compra,item_compra where compra.id = item_compra.id_compra
											and item_compra.id_producto = a_producto order by fecha;
		insert into  temp_item_compra select @id := @id+1 as id,cantidad,precio,fecha,id_compra,id_producto,id_item_compra
			from temp_item_compra_sid;
		select count(id) into v_cantidad from temp_item_compra;
        set v_contador = 1;
        while(v_contador <= v_cantidad) do
			select cantidad,precio,fecha,id_compra,id_item_compra
				into  v_cantidad_compra,v_precio_compra,v_fecha,v_id_compra,v_id_item_compra from temp_item_compra where id = v_contador;
			if(v_contador =1) then
				insert into seguimiento_costo(id_producto,cantidad_actual,costo_actual,cantidad_compra,precio_compra,sumatoria_actual,sumatoria_compra,costo_total,
					cantidad_total,costo_calculo,fecha,registro,observacion,detalle)
                    values(a_producto,0,0,v_cantidad_compra,v_precio_compra,0,(v_cantidad_compra*v_precio_compra),(0+(v_cantidad_compra*v_precio_compra)),
                    0+v_cantidad_compra,((v_cantidad_compra*v_precio_compra)/(v_cantidad_compra)),v_fecha,v_id_compra,'Compra',v_id_item_compra);
			else
				drop table if exists id_de_venta ;
                create table id_de_venta(id bigint,fecha_compra date);
				select max(id) into v_max_segumiento_costo from seguimiento_costo where id_producto = a_producto ;
                
                select max(venta.id) into v_id_venta from venta join item_venta on venta.id=item_venta.id_venta 
												where fecha <= v_fecha and item_venta.id_producto = a_producto;
                                                
                select id into v_id_extracto_producto_compra from extracto_producto where observacion = 'compra' and registro = v_id_compra
					and id_producto = a_producto;
                    
				select id into v_id_extracto_producto_venta from extracto_producto where observacion = 'venta' and registro = v_id_venta
					and id_producto = a_producto;
                    
				while v_id_extracto_producto_venta>v_id_extracto_producto_compra do
                
					set v_new_id_venta = v_id_venta;
					select max(venta.id) into v_id_venta from venta join item_venta on venta.id=item_venta.id_venta 
												where fecha <= v_fecha and item_venta.id_producto = a_producto and venta.id < v_new_id_venta;
                                                
					select id into v_id_extracto_producto_compra from extracto_producto where observacion = 'compra' and registro = v_id_compra
						and id_producto = a_producto;
                        
					select id into v_id_extracto_producto_venta from extracto_producto where observacion = 'venta' and registro = v_id_venta
						and id_producto = a_producto;
                        
                end while;
                
                insert into id_de_venta values(v_id_venta,v_fecha);
                
				select sum(case when observacion='compra' and registro<v_id_compra then entrada else 0 end)-
					   sum( case when observacion ='venta' and registro<=v_id_venta then salida else 0 end) into v_cantidad_actual 
				from extracto_producto where id_producto = a_producto ;
                
                    if(v_cantidad_actual < 0) then
						set v_cantidad_actual =0;
                    end if;
                    
				select costo_calculo into v_costo_actual  from seguimiento_costo where id = v_max_segumiento_costo and id_producto = a_producto;
				insert into seguimiento_costo(
						id_producto,
                        cantidad_actual,
                        costo_actual,
                        cantidad_compra,
                        precio_compra,
						sumatoria_actual,
                        sumatoria_compra,
                        costo_total,
						cantidad_total,
                        costo_calculo,fecha,registro,observacion,detalle)values(
                        a_producto,
                        v_cantidad_actual,
                        v_costo_actual,
                        v_cantidad_compra,
                        v_precio_compra,
						(v_cantidad_actual*v_costo_actual),
                        (v_cantidad_compra*v_precio_compra),
                        (((v_cantidad_actual*v_costo_actual))+((v_cantidad_compra*v_precio_compra))),
                        (v_cantidad_actual+v_cantidad_compra),
                        (((((v_cantidad_actual*v_costo_actual))+((v_cantidad_compra*v_precio_compra))))/(v_cantidad_actual+v_cantidad_compra))
                        ,v_fecha,v_id_compra,'Compra',v_id_item_compra);
                
                
            end if;
            
            set v_contador = v_contador +1;
        end while;
    end if;
    select max(id) into v_max_segumiento_costo from seguimiento_costo where id_producto = a_producto;
    select costo_calculo into v_costo_actual from seguimiento_costo where id = v_max_segumiento_costo and id_producto = a_producto;
    if(v_costo_actual is null)then
		set v_costo_actual =0;
	end if;
    update  producto set costo = v_costo_actual where id = a_producto;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pr_adicionar_total_cobro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_adicionar_total_cobro`()
begin 
	declare v_count bigint;
    declare v_id_cobro bigint;
    declare v_importe decimal(19,2);
	select count(id) into v_count from cobro where total is null or total =0;
	while v_count>0 do
		select id into v_id_cobro from cobro where total is null or total =0 limit 1;
        select sum(importe) into v_importe from item_cobro where id_cobro = v_id_cobro;
        update cobro set total = v_importe where id = v_id_cobro;
        select count(id) into v_count from cobro where total is null or total =0;
	end while;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pr_calcular_stock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_calcular_stock`(a_producto bigint,a_deposito bigint)
begin
	declare v_cantidad decimal(19,2);
    declare v_count bigint;
    declare v_servicio bit(1);
    
    select count(id) into v_count from stock_deposito where id_producto = a_producto and id_deposito = a_deposito;
    select servicio into v_servicio from producto where id = a_producto;
    if(v_servicio = true)then
		set v_cantidad =0;
    else
		select f_cantidad_stock(a_producto,a_deposito) into v_cantidad;
    end if;
    
    if(v_count = 0) then
		insert into stock_deposito (cantidad,reserva,id_producto,id_deposito) 
			values(v_cantidad,0,a_producto,a_deposito);
    else
		update stock_deposito set cantidad = v_cantidad where id_producto = a_producto and id_deposito = a_deposito;
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pr_movimiento_vendedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_movimiento_vendedor`(a_vendedor bigint)
begin 
DECLARE fin INT DEFAULT 0;
declare v_fecha date;
declare v_venta bigint;
declare v_item_venta bigint;
declare v_credito decimal(35,16);
declare v_debito decimal(35,16);
declare v_vendedor bigint;
declare v_pago bigint;
declare v_observacion varchar(255);
declare v_total_credito decimal(35,16) default 0;
declare v_total_debito decimal(35,16) default 0;

declare cursor_vendedores cursor  for 
	select v.fecha as fecha, v.id as venta,iv.id as item_venta ,((iv.cantidad*iv.precio)*p.comision)/100 as credito,0 as debito
		,v.id_vendedor as vendedor, null pago,concat('Comision de venta: ',v.id) as observacion
from venta v join item_venta iv on v.id = iv.id_venta
	join producto p on p.id = iv.id_producto 
where v.id_vendedor = a_vendedor
union all
select pv.fecha as fecha , null as venta, null as item_venta,0 as credito, pv.importe as debito,pv.id_vendedor as vendedor,pv.id as pago
,concat('Pago a vendedor: ',pv.id) as observacion
from pago_vendedor pv
where id_vendedor = a_vendedor
order by fecha;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
delete from movimiento_vendedor where id_vendedor = a_vendedor;

open cursor_vendedores;
	leer_vendedores:LOOP
		fetch cursor_vendedores into  v_fecha , v_venta , v_item_venta , v_credito , v_debito, v_vendedor, v_pago,v_observacion;
		if fin =1 then
			leave leer_vendedores;
        end if;
         insert into movimiento_vendedor(fecha,debito,credito,observacion,id_pago_vendedor,id_vendedor,id_item_venta)
									values(v_fecha,v_debito,v_credito,v_observacion,v_pago,v_vendedor,v_item_venta);
		set v_total_credito = v_total_credito +v_credito;
        set v_total_debito = v_total_debito + v_debito;
    END loop leer_vendedores;
close cursor_vendedores;
	update vendedor set saldo = v_total_credito-v_total_debito where id = a_vendedor;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pr_movimiento_vendedor_venta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_movimiento_vendedor_venta`(a_venta bigint,a_fecha date,a_vendedor bigint)
begin 
DECLARE fin INT DEFAULT 0;
 declare v_id_item_venta bigint;
 declare v_sub_total decimal(35,16);
 declare v_id_producto bigint;
 declare v_id_venta bigint;
declare v_comision_producto decimal(35,16);
declare v_comision decimal(35,16);
	declare cursor_item_venta cursor for 
		select id,(cantidad*precio) as subtotal,id_producto,id_venta from item_venta where id_venta = a_venta;
	declare continue handler for not found set fin =1;
    open cursor_item_venta;
		leer_item_venta: loop
        fetch cursor_item_venta into  v_id_item_venta,v_sub_total,v_id_producto,v_id_venta;
			if(fin=1)then
				leave leer_item_venta;
            end if;
            select comision into v_comision_producto from producto where id = v_id_producto;
            if(v_comision_producto>0)then
				delete from movimiento_vendedor where id_item_venta = v_id_item_venta;
				set v_comision  = (v_sub_total*v_comision_producto)/100;
                insert into movimiento_vendedor (credito,debito,fecha,observacion,id_item_venta,id_vendedor)
			values(v_comision,0,a_fecha,concat('Comision de venta: ',a_venta),v_id_item_venta,a_vendedor);
            end if;
        end loop leer_item_venta;
    close cursor_item_venta;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pr_recalcula_all_stock` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pr_recalcula_all_stock`(a_deposito bigint)
begin 
	DECLARE fin INT DEFAULT 0;
    declare v_id_producto bigint;
    declare v_count bigint;
	declare cursor_producto cursor for select id from producto;
    declare continue handler for not found set fin = 1;
    open cursor_producto;
    leer_producto: loop
    fetch cursor_producto into v_id_producto;
    if fin = 1 then
		leave leer_producto;
    end if;
    select count(id) into v_count from stock_deposito where id_producto = v_id_producto and id_deposito = a_deposito;
    if(v_count =0)then
		insert into stock_deposito (cantidad,reserva,id_producto,id_deposito)
			values(f_cantidad_stock(v_id_producto,a_deposito),0,v_id_producto,a_deposito);
    else
		update stock_deposito set cantidad = f_cantidad_stock(v_id_producto,a_deposito) 
			where id_producto = v_id_producto and id_deposito =a_deposito;
    end if;
    
    end loop leer_producto;
    close cursor_producto;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `recalcular_costo_de_todos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`::1` PROCEDURE `recalcular_costo_de_todos`()
begin
	declare contador bigint;
    declare v_max_producto bigint;
	declare v_id_producto bigint;
	
	drop temporary table if exists todo_producto;
    create temporary table todo_producto(id bigint ,id_producto bigint);
    set @id := 0;
    insert into todo_producto select @id := @id+1 as id,id as id_producto from producto;
    select count(id) into v_max_producto from todo_producto;
    set contador = 1;
    while (contador <= v_max_producto) do
		select id_producto into v_id_producto from todo_producto where id = contador;
        call calcular_costo(v_id_producto);
         set contador = contador +1;
    end while;
   
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `recalcular_costo_venta_de_todos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`::1` PROCEDURE `recalcular_costo_venta_de_todos`(a_fecha_desde date,a_fecha_hasta date)
begin
	declare contador bigint;
    declare v_max_producto bigint;
	declare v_id_producto bigint;
	
	drop temporary table if exists todo_producto;
    create temporary table todo_producto(id bigint ,id_producto bigint);
    set @id := 0;
    insert into todo_producto select @id := @id+1 as id,id as id_producto from producto;
    select count(id) into v_max_producto from todo_producto;
    set contador = 1;
    while (contador <= v_max_producto) do
		select id_producto into v_id_producto from todo_producto where id = contador;
        call calclular_costo_venta(v_id_producto,a_fecha_desde , a_fecha_hasta);
         set contador = contador +1;
    end while;
   
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `recalcular_saldo_caja` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`::1` PROCEDURE `recalcular_saldo_caja`(a_cuenta bigint(20))
begin
	declare v_total decimal(19,2);
	select sum(importe) into v_total from membresia where cuenta_id = a_cuenta;
    update cuenta set saldo = v_total where id = a_cuenta;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-08 17:03:36
