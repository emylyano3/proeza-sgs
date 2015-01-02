SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema seg_proeza_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `seg_proeza_db` ;
CREATE SCHEMA IF NOT EXISTS `seg_proeza_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `seg_proeza_db` ;

-- -----------------------------------------------------
-- Table `seg_proeza_db`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `seg_proeza_db`.`usuario` ;

CREATE TABLE IF NOT EXISTS `seg_proeza_db`.`usuario` (
  `id` BIGINT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `alias` VARCHAR(12) NOT NULL,
  `nombre` VARCHAR(50) NULL DEFAULT 'N/A',
  `apellido` VARCHAR(50) NULL DEFAULT 'N/A',
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `seg_proeza_db`.`usuario` (`id` ASC);

CREATE UNIQUE INDEX `alias_UNIQUE` ON `seg_proeza_db`.`usuario` (`alias` ASC);


-- -----------------------------------------------------
-- Table `seg_proeza_db`.`rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `seg_proeza_db`.`rol` ;

CREATE TABLE IF NOT EXISTS `seg_proeza_db`.`rol` (
  `id` BIGINT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) NOT NULL UNIQUE,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `seg_proeza_db`.`rol` (`id` ASC);


-- -----------------------------------------------------
-- Table `seg_proeza_db`.`usuario_rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `seg_proeza_db`.`usuario_rol` ;

CREATE TABLE IF NOT EXISTS `seg_proeza_db`.`usuario_rol` (
  `id` BIGINT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `fk_usuario` BIGINT ZEROFILL UNSIGNED NOT NULL,
  `fk_rol` BIGINT ZEROFILL UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `usuario`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `seg_proeza_db`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rol`
    FOREIGN KEY (`fk_rol`)
    REFERENCES `seg_proeza_db`.`rol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_uk` ON `seg_proeza_db`.`usuario_rol` (`id` ASC);

CREATE INDEX `usuario_idx` ON `seg_proeza_db`.`usuario_rol` (`fk_usuario` ASC);

CREATE INDEX `rol_idx` ON `seg_proeza_db`.`usuario_rol` (`fk_rol` ASC);


--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol`
     VALUES (00000000000000000001,
             'ROLE_ADMIN',
             'admin',
             'Rol de administrador del sistema (God)'),
            (00000000000000000002,
             'ROLE_USER',
             'user',
             'Rol de usuario básico'),
            (00000000000000000003,
             'ROLE_USER_ADMIN',
             'user_admin',
             'Rol de administrador de usuarios');

/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario`
     VALUES (00000000000000000001,
             'admin',
             'admin',
             'admin',
             'admin@proeza.com.ar',
             'admin'),
            (00000000000000000002,
             'user',
             'user',
             'user',
             'user@proeza.com.ar',
             'user');

/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol`
        VALUES (00000000000000000001,
                00000000000000000001,
                00000000000000000001),
               (00000000000000000002,
                00000000000000000001,
                00000000000000000002),
               (00000000000000000003,
                00000000000000000001,
                00000000000000000003),
               (00000000000000000004,
                00000000000000000002,
                00000000000000000002);

/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
