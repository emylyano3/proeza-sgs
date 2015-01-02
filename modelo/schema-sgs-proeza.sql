SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sgs_proeza_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sgs_proeza_db` ;
CREATE SCHEMA IF NOT EXISTS `sgs_proeza_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `sgs_proeza_db` ;

-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`categoria` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`categoria` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT 'N/A',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`categoria` (`id` ASC);

CREATE UNIQUE INDEX `codigo` ON `sgs_proeza_db`.`categoria` (`codigo` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`clase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`clase` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`clase` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT 'N/A',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`clase` (`id` ASC);

CREATE UNIQUE INDEX `codigo` ON `sgs_proeza_db`.`clase` (`codigo` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`marca` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`marca` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' UNIQUE NOT NULL,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT 'N/A',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`marca` (`id` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`rubro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`rubro` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`rubro` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT 'N/A',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`rubro` (`id` ASC);

CREATE UNIQUE INDEX `codigo` ON `sgs_proeza_db`.`rubro` (`codigo` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`articulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`articulo` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`articulo` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL COMMENT 'Codigo clave subrrogada unica de artículo',
  `descripcion` VARCHAR(300) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `fk_clase` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `fk_categoria` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `fk_marca` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `fk_rubro` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `costo` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Valor de compra del articulo',
  `precio` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Valor de venta del articulo\r',
  `cantidad` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'Representa el stock actual del articulo',
  PRIMARY KEY (`id`),
  CONSTRAINT `articulo_categoria`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `sgs_proeza_db`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `articulo_clase`
    FOREIGN KEY (`fk_clase`)
    REFERENCES `sgs_proeza_db`.`clase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `articulo_marca`
    FOREIGN KEY (`fk_marca`)
    REFERENCES `sgs_proeza_db`.`marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `articulo_rubro`
    FOREIGN KEY (`fk_rubro`)
    REFERENCES `sgs_proeza_db`.`rubro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`articulo` (`id` ASC);

CREATE UNIQUE INDEX `codigo_uk` ON `sgs_proeza_db`.`articulo` (`codigo` ASC);

CREATE INDEX `clase` ON `sgs_proeza_db`.`articulo` (`fk_clase` ASC);

CREATE INDEX `categoria` ON `sgs_proeza_db`.`articulo` (`fk_categoria` ASC);

CREATE INDEX `rubro` ON `sgs_proeza_db`.`articulo` (`fk_rubro` ASC);

CREATE INDEX `marca` ON `sgs_proeza_db`.`articulo` (`fk_marca` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`categoria_clase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`categoria_clase` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`categoria_clase` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `fk_categoria` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `fk_clase` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `cc_categoria`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `sgs_proeza_db`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cc_clase`
    FOREIGN KEY (`fk_clase`)
    REFERENCES `sgs_proeza_db`.`clase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`categoria_clase` (`id` ASC);

CREATE INDEX `cc_categoria` ON `sgs_proeza_db`.`categoria_clase` (`fk_categoria` ASC);

CREATE INDEX `cc_clase` ON `sgs_proeza_db`.`categoria_clase` (`fk_clase` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`medio_pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`medio_pago` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`medio_pago` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `codigo` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL COMMENT 'Codigo clave subrrogada unica',
  `nombre` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL COMMENT 'Nombre del medio de pago',
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci
COMMENT = 'Tabla maestro con los medios de pago disponibles';

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`medio_pago` (`id` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`compra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`compra` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`compra` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'PK de la tabla',
  `codigo` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' UNIQUE NOT NULL,
  `fecha` DATE NOT NULL COMMENT 'Fecha de  compra',
  `importe` DOUBLE(10,2) NOT NULL COMMENT 'Monto de compra',
  `fk_medio_pago` BIGINT(20) UNSIGNED ZEROFILL NOT NULL COMMENT 'FK a la tabla medio_pago',
  PRIMARY KEY (`id`),
  CONSTRAINT `compra_uk`
    FOREIGN KEY (`fk_medio_pago`)
    REFERENCES `sgs_proeza_db`.`medio_pago` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci
COMMENT = 'Compra de articulos';

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`compra` (`id` ASC);

CREATE INDEX `compra_uk_idx` ON `sgs_proeza_db`.`compra` (`fk_medio_pago` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`compra_articulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`compra_articulo` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`compra_articulo` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `fk_articulo` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `fk_compra` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `cantidad` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `sgs_proeza_db`.`compra_articulo` (`id` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`tipo_movimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`tipo_movimiento` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`tipo_movimiento` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `nombre` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL,
  `descripcion` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT 'N/A',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`tipo_movimiento` (`id` ASC);

CREATE UNIQUE INDEX `codigo` ON `sgs_proeza_db`.`tipo_movimiento` (`codigo` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`movimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`movimiento` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`movimiento` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `fk_tipo` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `fk_articulo` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `f_movimiento` DATE NOT NULL,
  `valor` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL DEFAULT '""',
  PRIMARY KEY (`id`),
  CONSTRAINT `movimiento_articulo`
    FOREIGN KEY (`fk_articulo`)
    REFERENCES `sgs_proeza_db`.`articulo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `movimiento_tipo`
    FOREIGN KEY (`fk_tipo`)
    REFERENCES `sgs_proeza_db`.`tipo_movimiento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`movimiento` (`id` ASC);

CREATE INDEX `tipo` ON `sgs_proeza_db`.`movimiento` (`fk_tipo` ASC);

CREATE INDEX `articulo` ON `sgs_proeza_db`.`movimiento` (`fk_articulo` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`rubro_categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`rubro_categoria` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`rubro_categoria` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `fk_categoria` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `fk_rubro` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `rc_categoria`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `sgs_proeza_db`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rc_rubro`
    FOREIGN KEY (`fk_rubro`)
    REFERENCES `sgs_proeza_db`.`rubro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`rubro_categoria` (`id` ASC);

CREATE INDEX `categoria` ON `sgs_proeza_db`.`rubro_categoria` (`fk_categoria` ASC);

CREATE INDEX `rubro` ON `sgs_proeza_db`.`rubro_categoria` (`fk_rubro` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`venta` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`venta` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'Pk de la tabla',
  `codigo` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NOT NULL COMMENT 'Codigo clave subrrogada unica',
  `fecha` DATE NOT NULL COMMENT 'Fecha de la compra',
  `importe` DOUBLE(10,2) NOT NULL COMMENT 'Importe total de la venta',
  `fk_medio_pago` BIGINT(20) UNSIGNED ZEROFILL NOT NULL COMMENT 'FK a la tabla de medio de pagos',
  PRIMARY KEY (`id`),
  CONSTRAINT `venta_mp`
    FOREIGN KEY (`fk_medio_pago`)
    REFERENCES `sgs_proeza_db`.`medio_pago` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`venta` (`id` ASC);

CREATE INDEX `venta_mp_idx` ON `sgs_proeza_db`.`venta` (`fk_medio_pago` ASC);


-- -----------------------------------------------------
-- Table `sgs_proeza_db`.`venta_articulo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sgs_proeza_db`.`venta_articulo` ;

CREATE TABLE IF NOT EXISTS `sgs_proeza_db`.`venta_articulo` (
  `id` BIGINT(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `fk_venta` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `fk_articulo` BIGINT(20) UNSIGNED ZEROFILL NOT NULL,
  `cantidad` INT(10) UNSIGNED NOT NULL COMMENT 'Cantidad de articulos de un tipo en la venta',
  PRIMARY KEY (`id`),
  CONSTRAINT `va_articulo`
    FOREIGN KEY (`fk_articulo`)
    REFERENCES `sgs_proeza_db`.`articulo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `va_venta`
    FOREIGN KEY (`fk_venta`)
    REFERENCES `sgs_proeza_db`.`venta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE UNIQUE INDEX `id_uk` ON `sgs_proeza_db`.`venta_articulo` (`id` ASC);

CREATE INDEX `venta` ON `sgs_proeza_db`.`venta_articulo` (`fk_venta` ASC);

CREATE INDEX `articulo` ON `sgs_proeza_db`.`venta_articulo` (`fk_articulo` ASC);

--
-- TABLAS DE SISTEMA
--

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla con los menues de la aplicacion. Cada menu se puede asociar a diferentes paginas; esa relacion se realiza a travÃ©s de la tabla sys_pagina_menu';
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
