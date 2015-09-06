CREATE DATABASE  IF NOT EXISTS `sgs_proeza_db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `sgs_proeza_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sgs_proeza_db
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `art_articulo`
--

DROP TABLE IF EXISTS `art_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_articulo` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Codigo clave subrrogada unica de artículo',
  `modelo` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(300) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fk_rubro` bigint(20) unsigned zerofill NOT NULL,
  `fk_clase` bigint(20) unsigned zerofill NOT NULL,
  `fk_tipo` bigint(20) unsigned zerofill DEFAULT NULL,
  `fk_marca` bigint(20) unsigned zerofill NOT NULL,
  `costo` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Valor de compra del articulo',
  `precio` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Valor de venta del articulo\r',
  `cantidad` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Representa el stock actual del articulo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  UNIQUE KEY `codigo_uk` (`codigo`),
  KEY `clase_idx` (`fk_clase`),
  KEY `rubro_idx` (`fk_rubro`),
  KEY `marca_idx` (`fk_marca`),
  KEY `tipo_idx` (`fk_tipo`),
  CONSTRAINT `articulo_clase` FOREIGN KEY (`fk_clase`) REFERENCES `art_clase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `articulo_marca` FOREIGN KEY (`fk_marca`) REFERENCES `art_marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `articulo_rubro` FOREIGN KEY (`fk_rubro`) REFERENCES `art_rubro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `articulo_tipo` FOREIGN KEY (`fk_tipo`) REFERENCES `art_tipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=897 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_articulo_cliente`
--

DROP TABLE IF EXISTS `art_articulo_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_articulo_cliente` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_articulo` bigint(20) unsigned zerofill NOT NULL,
  `fk_cliente` bigint(20) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_pk` (`id`),
  KEY `ac_articulo_idx` (`fk_articulo`),
  KEY `ac_cliente_idx` (`fk_cliente`),
  CONSTRAINT `ac_articulo` FOREIGN KEY (`fk_articulo`) REFERENCES `art_articulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ac_cliente` FOREIGN KEY (`fk_cliente`) REFERENCES `art_cliente` (`fk_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_articulo_proveedor`
--

DROP TABLE IF EXISTS `art_articulo_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_articulo_proveedor` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_articulo` bigint(20) unsigned zerofill NOT NULL,
  `fk_proveedor` bigint(20) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  KEY `proveedor_idx` (`fk_proveedor`),
  KEY `articulo_idx` (`fk_articulo`),
  CONSTRAINT `fk_articulo` FOREIGN KEY (`fk_articulo`) REFERENCES `art_articulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_proveedor` FOREIGN KEY (`fk_proveedor`) REFERENCES `art_proveedor` (`fk_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_clase`
--

DROP TABLE IF EXISTS `art_clase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_clase` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT 'N/A',
  `fk_rubro` bigint(20) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `clase_uk` (`codigo`,`fk_rubro`),
  KEY `nombre_idx` (`nombre`),
  KEY `rubro_idx` (`fk_rubro`),
  CONSTRAINT `clase_rubro` FOREIGN KEY (`fk_rubro`) REFERENCES `art_rubro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_cliente`
--

DROP TABLE IF EXISTS `art_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_cliente` (
  `fk_persona` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla persona. Guarda la relacion de herencia con dicha tabla.',
  KEY `cliente_persona` (`fk_persona`),
  CONSTRAINT `cliente_persona` FOREIGN KEY (`fk_persona`) REFERENCES `cmn_persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene los clientes de la aplicacion.\r\nGuarda una relacion de herencia con la tabla persona.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_compra`
--

DROP TABLE IF EXISTS `art_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_compra` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `fecha` date NOT NULL COMMENT 'Fecha de  compra',
  `importe` double(10,2) NOT NULL COMMENT 'Monto de compra',
  `fk_medio_pago` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla medio_pago',
  `fk_proveedor` bigint(20) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_uk` (`codigo`),
  KEY `medio_pago_idx` (`fk_medio_pago`),
  KEY `proveedor_idx` (`fk_proveedor`),
  CONSTRAINT `compra_mp` FOREIGN KEY (`fk_medio_pago`) REFERENCES `cmn_medio_pago` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `compra_proveedor` FOREIGN KEY (`fk_proveedor`) REFERENCES `art_proveedor` (`fk_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Compra de articulos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_compra_articulo`
--

DROP TABLE IF EXISTS `art_compra_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_compra_articulo` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_articulo` bigint(20) unsigned zerofill NOT NULL,
  `fk_compra` bigint(20) unsigned zerofill NOT NULL,
  `cantidad` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `compra_idx` (`fk_compra`),
  KEY `articulo_idx` (`fk_articulo`),
  CONSTRAINT `ca_articulo` FOREIGN KEY (`fk_articulo`) REFERENCES `art_articulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ca_compra` FOREIGN KEY (`fk_compra`) REFERENCES `art_compra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_marca`
--

DROP TABLE IF EXISTS `art_marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_marca` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT 'N/A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_uk` (`codigo`),
  UNIQUE KEY `id_uk` (`id`),
  KEY `nombre_idx` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_proveedor`
--

DROP TABLE IF EXISTS `art_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_proveedor` (
  `fk_persona` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK de la tabla persona. Guarda relacion de herencia.',
  KEY `proveedor_persona` (`fk_persona`),
  CONSTRAINT `proveedor_persona` FOREIGN KEY (`fk_persona`) REFERENCES `cmn_persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene datos de los proveedores del usuario';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_resource`
--

DROP TABLE IF EXISTS `art_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_resource` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_owner` bigint(20) unsigned zerofill NOT NULL,
  `media_type` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `f_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nombre` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `data` longblob NOT NULL,
  `preview` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_rubro`
--

DROP TABLE IF EXISTS `art_rubro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_rubro` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT 'N/A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_uk` (`codigo`),
  KEY `nombre_idx` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_tipo`
--

DROP TABLE IF EXISTS `art_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_tipo` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT 'N/A',
  `fk_clase` bigint(20) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tipo_uk` (`codigo`,`fk_clase`),
  KEY `nombre_idx` (`nombre`),
  KEY `clase_idx` (`fk_clase`),
  CONSTRAINT `tipo_clase` FOREIGN KEY (`fk_clase`) REFERENCES `art_clase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Clasificacion dentro de clase. Indica para determinada clase de articulos, de que tipo son. Ejemplo: Rubro Vahiculo, clase automovil, tipo camioneta.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_venta`
--

DROP TABLE IF EXISTS `art_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_venta` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Pk de la tabla',
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Codigo clave subrrogada unica',
  `fecha` date NOT NULL COMMENT 'Fecha de la compra',
  `importe` decimal(10,2) NOT NULL COMMENT 'Importe total de la venta',
  `fk_medio_pago` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de medio de pagos',
  `fk_cliente` bigint(20) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `medio_pago_idx` (`fk_medio_pago`),
  KEY `cliente_idx` (`fk_cliente`),
  CONSTRAINT `venta_cliente` FOREIGN KEY (`fk_cliente`) REFERENCES `art_cliente` (`fk_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `venta_mp` FOREIGN KEY (`fk_medio_pago`) REFERENCES `cmn_medio_pago` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `art_venta_articulo`
--

DROP TABLE IF EXISTS `art_venta_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `art_venta_articulo` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_venta` bigint(20) unsigned zerofill NOT NULL,
  `fk_articulo` bigint(20) unsigned zerofill NOT NULL,
  `cantidad` int(10) unsigned NOT NULL COMMENT 'Cantidad de articulos de un tipo en la venta',
  PRIMARY KEY (`id`),
  KEY `venta_idx` (`fk_venta`),
  KEY `articulo_idx` (`fk_articulo`),
  CONSTRAINT `va_articulo` FOREIGN KEY (`fk_articulo`) REFERENCES `art_articulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `va_venta` FOREIGN KEY (`fk_venta`) REFERENCES `art_venta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_email`
--

DROP TABLE IF EXISTS `cmn_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_email` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `direccion` varchar(100) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Direccion de correo electronico',
  `fk_persona` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla persona',
  PRIMARY KEY (`id`),
  KEY `email_persona` (`fk_persona`),
  CONSTRAINT `email_persona` FOREIGN KEY (`fk_persona`) REFERENCES `cmn_persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene los emails ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_error`
--

DROP TABLE IF EXISTS `cmn_error`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_error` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `mensaje` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `excepcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `stack` varchar(9999) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_excepcion` (`excepcion`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_i18n`
--

DROP TABLE IF EXISTS `cmn_i18n`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_i18n` (
  `id` bigint(20) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  KEY `id_idx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_medio_pago`
--

DROP TABLE IF EXISTS `cmn_medio_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_medio_pago` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `codigo` varchar(10) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Codigo clave subrrogada unica',
  `nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del medio de pago',
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla maestro con los medios de pago disponibles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_moneda`
--

DROP TABLE IF EXISTS `cmn_moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_moneda` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `signo` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `codigo` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_movimiento`
--

DROP TABLE IF EXISTS `cmn_movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_movimiento` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `tipo_mov` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `fk_entidad` bigint(20) unsigned zerofill NOT NULL,
  `tipo_entidad` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valor_ant` varchar(100) COLLATE utf8_spanish_ci,
  `valor_post` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `entidad_idx` (`fk_entidad`),
  KEY `tipo_mov` (`tipo_mov`)
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_persona`
--

DROP TABLE IF EXISTS `cmn_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_persona` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre de la persona',
  `apellido` varchar(50) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Apellido de la persona',
  `sexo` varchar(1) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'M' COMMENT 'Sexo de la persona',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla que representa persona de manera asbtracta. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_telefono`
--

DROP TABLE IF EXISTS `cmn_telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_telefono` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `pref_internacional` varchar(3) COLLATE utf8_spanish_ci NOT NULL DEFAULT '+54' COMMENT 'Prefijo internacional del numero telefonico',
  `pref_area` varchar(3) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Prefijo de area del numero telefonico',
  `numero` varchar(10) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Numero telefonico',
  `fk_tipo` bigint(20) unsigned zerofill NOT NULL,
  `fk_persona` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla persona',
  PRIMARY KEY (`id`),
  KEY `persona_idx` (`fk_persona`),
  KEY `tipo_telefono_idx` (`fk_tipo`),
  CONSTRAINT `telefono_persona` FOREIGN KEY (`fk_persona`) REFERENCES `cmn_persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `telefono_tt` FOREIGN KEY (`fk_tipo`) REFERENCES `cmn_telefono_tipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla que define telefono';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_telefono_tipo`
--

DROP TABLE IF EXISTS `cmn_telefono_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_telefono_tipo` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_uk` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmn_traduccion`
--

DROP TABLE IF EXISTS `cmn_traduccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmn_traduccion` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_i18n` bigint(20) unsigned zerofill NOT NULL,
  `locale` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `texto` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_i18n_locale` (`fk_i18n`,`locale`),
  KEY `idx_i18n_locale` (`fk_i18n`,`locale`),
  KEY `fk_trad_loca_idx` (`locale`),
  CONSTRAINT `fk_trad_i18n` FOREIGN KEY (`fk_i18n`) REFERENCES `cmn_i18n` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_item`
--

DROP TABLE IF EXISTS `sys_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_item` (
  `id` bigint(20) unsigned zerofill NOT NULL COMMENT 'Pk de la tabla',
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Clave unica de la tabla',
  `fk_i18n_texto` bigint(20) NOT NULL COMMENT 'Texto del item',
  `fk_i18n_tooltip` bigint(20) DEFAULT NULL COMMENT 'Tooltip del item de menu',
  `icono` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Ruta al icono del item',
  `link` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Enlace del item de menu',
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_uk` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene todos los items de los menus de la aplicacion. La asociacion menu item se realiza a traves de la tabla sys_menu_item';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_item_rol`
--

DROP TABLE IF EXISTS `sys_item_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_item_rol` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Pk de la tabla',
  `fk_item` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de items',
  `fk_rol` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de roles',
  PRIMARY KEY (`id`),
  KEY `ir_rol` (`fk_rol`),
  KEY `ir_item_idx` (`fk_item`),
  CONSTRAINT `ir_item` FOREIGN KEY (`fk_item`) REFERENCES `sys_item` (`id`),
  CONSTRAINT `ir_rol` FOREIGN KEY (`fk_rol`) REFERENCES `seg_proeza_db`.`rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene la relacion de los item de menus con los roles de acceso.\r\nDe esta forma se puede saber que item se deben mostrar a cada rol y por ende a cada usuario.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_item_subitem`
--

DROP TABLE IF EXISTS `sys_item_subitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_item_subitem` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_item` bigint(20) unsigned zerofill NOT NULL,
  `fk_subitem` bigint(20) unsigned zerofill NOT NULL,
  `indice` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  KEY `item_idx` (`fk_item`),
  KEY `subitem_idx` (`fk_subitem`),
  CONSTRAINT `isi_item` FOREIGN KEY (`fk_item`) REFERENCES `sys_item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `isi_subitem` FOREIGN KEY (`fk_subitem`) REFERENCES `sys_item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Pk de la tabla',
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Clave alfanumerica unica de menu',
  `texto` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Texto del menu',
  `tooltip` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Tooltip del menu',
  `tipo` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Tipo de menu. LEFT_MENU, RIGH_MENU, etc.',
  `icono` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Ruta al icono del menu',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_uk` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla con los menues de la aplicacion. Cada menu se puede asociar a diferentes paginas; esa relacion se realiza a través de la tabla sys_pagina_menu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_menu_item`
--

DROP TABLE IF EXISTS `sys_menu_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu_item` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `fk_menu` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de menues',
  `fk_item` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de items de menu',
  `indice` int(3) NOT NULL COMMENT 'Representa el indice del item dentro del menu. \r\nSe utiliza para el ordenamiento de los items.',
  PRIMARY KEY (`id`),
  KEY `mi_menu` (`fk_menu`),
  KEY `mi_item_idx` (`fk_item`),
  CONSTRAINT `mi_item` FOREIGN KEY (`fk_item`) REFERENCES `sys_item` (`id`),
  CONSTRAINT `mi_menu` FOREIGN KEY (`fk_menu`) REFERENCES `sys_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene las relaciones entre los menues y los items de menu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_pagina`
--

DROP TABLE IF EXISTS `sys_pagina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_pagina` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Pk de la tabla',
  `grupo` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Grupo al que pertenece la pagina. Indica funcionalidad de un conjunto de paginas.',
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL COMMENT 'El nombre legible que tiene la pagina',
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Descripcion de la pagina',
  `fk_i18n_titulo` bigint(20) unsigned zerofill DEFAULT NULL COMMENT 'Título de la pagina. Corresponde a una version legible por usuario del grupo al que pertenece la página',
  `fk_i18n_subtitulo` bigint(20) unsigned zerofill DEFAULT NULL COMMENT 'Subtitulo de la página. Corresponde a una versión legible por usuario del nombre de la página',
  PRIMARY KEY (`id`),
  UNIQUE KEY `grupo_nombre` (`grupo`,`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene todas las paginas que componen la aplicacion.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_pagina_menu`
--

DROP TABLE IF EXISTS `sys_pagina_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_pagina_menu` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `fk_pagina` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de paginas',
  `fk_menu` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de menues',
  PRIMARY KEY (`id`),
  KEY `pagina_idx` (`fk_pagina`),
  KEY `menu_idx` (`fk_menu`),
  CONSTRAINT `pm_menu` FOREIGN KEY (`fk_menu`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `pm_pagina` FOREIGN KEY (`fk_pagina`) REFERENCES `sys_pagina` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Asociacion entre las paginas y los menues';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_pagina_rol`
--

DROP TABLE IF EXISTS `sys_pagina_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_pagina_rol` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_pagina` bigint(20) unsigned zerofill NOT NULL,
  `fk_rol` bigint(20) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pagina_idx` (`fk_pagina`),
  KEY `rol_idx` (`fk_rol`),
  CONSTRAINT `pr_pagina` FOREIGN KEY (`fk_pagina`) REFERENCES `sys_pagina` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pr_rol` FOREIGN KEY (`fk_rol`) REFERENCES `seg_proeza_db`.`rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Relaciona las paginas de la aplicacion con los roles de usuario. Permite saber que usuario puede acceder a que pagina.';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-06 14:55:00
