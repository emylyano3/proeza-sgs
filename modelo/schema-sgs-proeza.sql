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
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulo` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Codigo clave subrrogada unica de artículo',
  `descripcion` varchar(300) COLLATE utf8_spanish_ci NOT NULL,
  `fk_clase` bigint(20) unsigned zerofill NOT NULL,
  `fk_categoria` bigint(20) unsigned zerofill NOT NULL,
  `fk_marca` bigint(20) unsigned zerofill NOT NULL,
  `fk_rubro` bigint(20) unsigned zerofill NOT NULL,
  `costo` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Valor de compra del articulo',
  `precio` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Valor de venta del articulo\r',
  `cantidad` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'Representa el stock actual del articulo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  UNIQUE KEY `codigo_uk` (`codigo`),
  KEY `clase` (`fk_clase`),
  KEY `categoria` (`fk_categoria`),
  KEY `rubro` (`fk_rubro`),
  KEY `marca` (`fk_marca`),
  CONSTRAINT `articulo_categoria` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `articulo_clase` FOREIGN KEY (`fk_clase`) REFERENCES `clase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `articulo_marca` FOREIGN KEY (`fk_marca`) REFERENCES `marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `articulo_rubro` FOREIGN KEY (`fk_rubro`) REFERENCES `rubro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER sgs_proeza_db.tr_registrar_movimiento_articulo
   AFTER UPDATE
   ON sgs_proeza_db.articulo
   FOR EACH ROW
BEGIN
   DECLARE id_tipo_mov   INT;
   DECLARE nuevo_valor   VARCHAR(100);

   IF NEW.cantidad <> OLD.cantidad
   THEN
      /* Busco el tipo de movimiento para la modificacion de stock */
      SELECT id
        INTO id_tipo_mov
        FROM tipo_movimiento tm
       WHERE tm.codigo = 'MS';

      /* Obtengo el nuevo valor del stock */
      SET nuevo_valor = NEW.cantidad;

      /* Inserto el movimiento */
      INSERT INTO movimiento(fk_tipo,
                             fk_articulo,
                             f_movimiento,
                             valor)
           VALUES (id_tipo_mov,
                   NEW.id,
                   sysdate(),
                   nuevo_valor);
   END IF;

   IF NEW.costo <> OLD.costo
   THEN
      /* Busco el tipo de movimiento para la modificacion de costo */
      SELECT id
        INTO id_tipo_mov
        FROM tipo_movimiento tm
       WHERE tm.codigo = 'MC';

      /* Obtengo el nuevo valor del costo */
      SET nuevo_valor = NEW.costo;

      /* Inserto el movimiento */
      INSERT INTO movimiento(fk_tipo,
                             fk_articulo,
                             f_movimiento,
                             valor)
           VALUES (id_tipo_mov,
                   NEW.id,
                   sysdate(),
                   nuevo_valor);
   END IF;

   IF NEW.precio <> OLD.precio
   THEN
      /* Busco el tipo de movimiento para la modificacion de precio */
      SELECT id
        INTO id_tipo_mov
        FROM tipo_movimiento tm
       WHERE tm.codigo = 'MP';

      /* Obtengo el nuevo valor del precio */
      SET nuevo_valor = NEW.precio;

      /* Inserto el movimiento */
      INSERT INTO movimiento(fk_tipo,
                             fk_articulo,
                             f_movimiento,
                             valor)
           VALUES (id_tipo_mov,
                   NEW.id,
                   sysdate(),
                   nuevo_valor);
   END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT 'N/A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria_clase`
--

DROP TABLE IF EXISTS `categoria_clase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_clase` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_categoria` bigint(20) unsigned zerofill NOT NULL,
  `fk_clase` bigint(20) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  KEY `cc_categoria` (`fk_categoria`),
  KEY `cc_clase` (`fk_clase`),
  CONSTRAINT `cc_categoria` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cc_clase` FOREIGN KEY (`fk_clase`) REFERENCES `clase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clase`
--

DROP TABLE IF EXISTS `clase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clase` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT 'N/A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `fk_persona` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla persona. Guarda la relacion de herencia con dicha tabla.',
  KEY `cliente_persona` (`fk_persona`),
  CONSTRAINT `cliente_persona` FOREIGN KEY (`fk_persona`) REFERENCES `articulo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene los clientes de la aplicacion.\r\nGuarda una relacion de herencia con la tabla persona.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `fecha` date NOT NULL COMMENT 'Fecha de  compra',
  `importe` double(10,2) NOT NULL COMMENT 'Monto de compra',
  `fk_medio_pago` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla medio_pago',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo` (`codigo`),
  KEY `compra_uk` (`fk_medio_pago`),
  CONSTRAINT `compra_uk` FOREIGN KEY (`fk_medio_pago`) REFERENCES `medio_pago` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Compra de articulos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `compra_articulo`
--

DROP TABLE IF EXISTS `compra_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra_articulo` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_articulo` bigint(20) unsigned zerofill NOT NULL,
  `fk_compra` bigint(20) unsigned zerofill NOT NULL,
  `cantidad` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER sgs_proeza_db.tr_actualizar_stock_por_nueva_compra
   AFTER INSERT
   ON sgs_proeza_db.compra_articulo
   FOR EACH ROW
BEGIN
   CALL sp_quitar_del_stock(NEW.fk_articulo, - NEW.cantidad);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER sgs_proeza_db.tr_actualizar_stock_por_compra_modificada
   AFTER UPDATE
   ON sgs_proeza_db.compra_articulo
   FOR EACH ROW
BEGIN
   CALL sp_quitar_del_stock(NEW.fk_articulo,  OLD.cantidad - NEW.cantidad);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER sgs_proeza_db.tr_actualizar_stock_por_compra_anulada
   AFTER DELETE
   ON sgs_proeza_db.compra_articulo
   FOR EACH ROW
BEGIN
   CALL sp_quitar_del_stock(OLD.fk_articulo, OLD.cantidad);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `direccion` varchar(100) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Direccion de correo electronico',
  `fk_persona` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla persona',
  PRIMARY KEY (`id`),
  KEY `email_persona` (`fk_persona`),
  CONSTRAINT `email_persona` FOREIGN KEY (`fk_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene los emails ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marca` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT 'N/A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo` (`codigo`),
  UNIQUE KEY `id_uk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medio_pago`
--

DROP TABLE IF EXISTS `medio_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medio_pago` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `codigo` varchar(10) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Codigo clave subrrogada unica',
  `nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del medio de pago',
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla maestro con los medios de pago disponibles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimiento` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_tipo` bigint(20) unsigned zerofill NOT NULL,
  `fk_articulo` bigint(20) unsigned zerofill NOT NULL,
  `f_movimiento` date NOT NULL,
  `valor` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT '""',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  KEY `tipo` (`fk_tipo`),
  KEY `articulo` (`fk_articulo`),
  CONSTRAINT `movimiento_articulo` FOREIGN KEY (`fk_articulo`) REFERENCES `articulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `movimiento_tipo` FOREIGN KEY (`fk_tipo`) REFERENCES `tipo_movimiento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre de la persona',
  `apellido` varchar(50) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Apellido de la persona',
  `sexo` varchar(1) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'M' COMMENT 'Sexo de la persona',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla que representa persona de manera asbtracta. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `fk_persona` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK de la tabla persona. Guarda relacion de herencia.',
  KEY `proveedor_persona` (`fk_persona`),
  CONSTRAINT `proveedor_persona` FOREIGN KEY (`fk_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene datos de los proveedores del usuario';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rubro`
--

DROP TABLE IF EXISTS `rubro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rubro` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT 'N/A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rubro_categoria`
--

DROP TABLE IF EXISTS `rubro_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rubro_categoria` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_categoria` bigint(20) unsigned zerofill NOT NULL,
  `fk_rubro` bigint(20) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  KEY `categoria` (`fk_categoria`),
  KEY `rubro` (`fk_rubro`),
  CONSTRAINT `rc_categoria` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rc_rubro` FOREIGN KEY (`fk_rubro`) REFERENCES `rubro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `link` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Enlace del item de menu',
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_uk` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene todos los items de los menus de la aplicacion. La asociacion menu item se realiza a traves de la tabla sys_menu_item';
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene la relacion de los item de menus con los roles de acceso.\r\nDe esta forma se puede saber que item se deben mostrar a cada rol y por ende a cada usuario.';
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene las relaciones entre los menues y los items de menu';
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Contiene todas las paginas que componen la aplicacion.';
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
  KEY `pm_pagina_idx` (`fk_pagina`),
  KEY `pm_menu_idx` (`fk_menu`),
  CONSTRAINT `pm_menu` FOREIGN KEY (`fk_menu`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `pm_pagina` FOREIGN KEY (`fk_pagina`) REFERENCES `sys_pagina` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Asociacion entre las paginas y los menues';
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
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `pr_pagina_idx` (`fk_pagina`),
  KEY `pr_rol_idx` (`fk_rol`),
  CONSTRAINT `pr_pagina` FOREIGN KEY (`fk_pagina`) REFERENCES `sys_pagina` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pr_rol` FOREIGN KEY (`fk_rol`) REFERENCES `seg_proeza_db`.`rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Relaciona las paginas de la aplicacion con los roles de usuario. Permite saber que usuario puede acceder a que pagina.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `telefono`
--

DROP TABLE IF EXISTS `telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefono` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `pref_internacional` varchar(3) COLLATE utf8_spanish_ci NOT NULL DEFAULT '+54' COMMENT 'Prefijo internacional del numero telefonico',
  `pref_area` varchar(3) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Prefijo de area del numero telefonico',
  `numero` varchar(10) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Numero telefonico',
  `fk_persona` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla persona',
  PRIMARY KEY (`id`),
  KEY `telefono_persona` (`fk_persona`),
  CONSTRAINT `telefono_persona` FOREIGN KEY (`fk_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla que define telefono';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_movimiento`
--

DROP TABLE IF EXISTS `tipo_movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_movimiento` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT 'N/A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Pk de la tabla',
  `codigo` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Codigo clave subrrogada unica',
  `fecha` date NOT NULL COMMENT 'Fecha de la compra',
  `importe` double(10,2) NOT NULL COMMENT 'Importe total de la venta',
  `fk_medio_pago` bigint(20) unsigned zerofill NOT NULL COMMENT 'FK a la tabla de medio de pagos',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  KEY `venta_mp_idx` (`fk_medio_pago`),
  CONSTRAINT `venta_mp` FOREIGN KEY (`fk_medio_pago`) REFERENCES `medio_pago` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `venta_articulo`
--

DROP TABLE IF EXISTS `venta_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta_articulo` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fk_venta` bigint(20) unsigned zerofill NOT NULL,
  `fk_articulo` bigint(20) unsigned zerofill NOT NULL,
  `cantidad` int(10) unsigned NOT NULL COMMENT 'Cantidad de articulos de un tipo en la venta',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_uk` (`id`),
  KEY `venta` (`fk_venta`),
  KEY `articulo` (`fk_articulo`),
  CONSTRAINT `va_articulo` FOREIGN KEY (`fk_articulo`) REFERENCES `articulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `va_venta` FOREIGN KEY (`fk_venta`) REFERENCES `venta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER sgs_proeza_db.tr_actualizar_stock_por_nueva_venta
   AFTER INSERT
   ON sgs_proeza_db.venta_articulo
   FOR EACH ROW
BEGIN
	Call sp_quitar_del_stock(NEW.fk_articulo, NEW.cantidad);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER sgs_proeza_db.tr_actualizar_stock_por_venta_modificada
   AFTER UPDATE
   ON sgs_proeza_db.venta_articulo
   FOR EACH ROW
BEGIN
	Call sp_quitar_del_stock(NEW.fk_articulo, NEW.cantidad - OLD.cantidad);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER sgs_proeza_db.tr_actualizar_stock_por_venta_anulada
   AFTER DELETE 
   ON sgs_proeza_db.venta_articulo
   FOR EACH ROW
BEGIN
	Call sp_quitar_del_stock(OLD.fk_articulo, - OLD.cantidad);
END */;;
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

-- Dump completed on 2015-01-08 12:56:43
