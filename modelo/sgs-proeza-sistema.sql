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
-- Table structure for table `sys_item`
--

DROP TABLE IF EXISTS `sys_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_item` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Pk de la tabla',
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Clave unica de la tabla',
  `texto` varchar(200) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Texto del item',
  `tooltip` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Tooltip del item de menu',
  `icono` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Ruta al icono del item',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene la relacion de los item de menus con los roles de acceso.\r\nDe esta forma se puede saber que item se deben mostrar a cada rol y por ende a cada usuario.';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla con los menues de la aplicacion. Cada menu se puede asociar a diferentes paginas; esa relacion se realiza a través de la tabla sys_pagina_menu';
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
  `index` int(3) NOT NULL COMMENT 'Representa el indice del item dentro del menu. \r\nSe utiliza para el ordenamiento de los items.',
  PRIMARY KEY (`id`),
  KEY `mi_menu` (`fk_menu`),
  KEY `mi_item_idx` (`fk_item`),
  CONSTRAINT `mi_item` FOREIGN KEY (`fk_item`) REFERENCES `sys_item` (`id`),
  CONSTRAINT `mi_menu` FOREIGN KEY (`fk_menu`) REFERENCES `sys_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene las relaciones entre los menues y los items de menu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_pagina`
--

DROP TABLE IF EXISTS `sys_pagina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_pagina` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Pk de la tabla',
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Clave unica de la tabla',
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL COMMENT 'El nombre legible que tiene la pagina',
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Descripcion de la pagina',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pagina_uk` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene todas las paginas que componen la aplicacion.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_pagina_menu`
--

DROP TABLE IF EXISTS `sys_pagina_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_pagina_menu` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `fk_page` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de paginas',
  `fk_menu` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de menues',
  PRIMARY KEY (`id`),
  KEY `pm_pagina_idx` (`fk_page`),
  KEY `pm_menu_idx` (`fk_menu`),
  CONSTRAINT `pm_menu` FOREIGN KEY (`fk_menu`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `pm_pagina` FOREIGN KEY (`fk_page`) REFERENCES `sys_pagina` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Asociacion entre las paginas y los menues';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-02 11:15:15
