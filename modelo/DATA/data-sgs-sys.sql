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
-- Dumping data for table `sys_item`
--

LOCK TABLES `sys_item` WRITE;
/*!40000 ALTER TABLE `sys_item` DISABLE KEYS */;
INSERT INTO `sys_item` VALUES
(00000000000000000001,'MI_HOME','Inicio','Para acceder al inicio de la aplicación','fa fa-home','/home'),
(00000000000000000002,'MI_USUA','Usuario','Para acceder al home de usuario','fa fa-users','#'),
(00000000000000000003,'MI_USUA_HOME','Usuario','Para acceder al home de usuario','fa fa-home','/usuario/home'),
(00000000000000000004,'MI_ARTI','Artículo','Para agupar los items de articulo','fa fa-barcode','#'),
(00000000000000000005,'MI_ARTI_HOME','Home','Para acceder al home de articulo','','/articulo/home'),
(00000000000000000006,'MI_ARTI_LIST','Listado','Para acceder al listado de articulos','','/articulo/listado'),
(00000000000000000007,'MI_ARTI_STATS','Estadísticas','Para acceder a las estadísticas de articulos','','/articulo/stats'),
(00000000000000000008,'MI_ARTI_ADMIN','Administración','Para acceder a la administración de articulos','','/articulo/admin');
/*!40000 ALTER TABLE `sys_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_item_rol`
--

LOCK TABLES `sys_item_rol` WRITE;
/*!40000 ALTER TABLE `sys_item_rol` DISABLE KEYS */;
INSERT INTO `sys_item_rol` VALUES
(00000000000000000001,00000000000000000002,00000000000000000041),
(00000000000000000002,00000000000000000002,00000000000000000042),
(00000000000000000003,00000000000000000002,00000000000000000043),
(00000000000000000004,00000000000000000003,00000000000000000041),
(00000000000000000005,00000000000000000003,00000000000000000042),
(00000000000000000006,00000000000000000003,00000000000000000043),
(00000000000000000007,00000000000000000004,00000000000000000031),
(00000000000000000008,00000000000000000004,00000000000000000032),
(00000000000000000009,00000000000000000004,00000000000000000033),
(00000000000000000010,00000000000000000005,00000000000000000031),
(00000000000000000011,00000000000000000005,00000000000000000032),
(00000000000000000012,00000000000000000005,00000000000000000033),
(00000000000000000013,00000000000000000006,00000000000000000031),
(00000000000000000014,00000000000000000006,00000000000000000032),
(00000000000000000015,00000000000000000006,00000000000000000033),
(00000000000000000016,00000000000000000007,00000000000000000031),
(00000000000000000017,00000000000000000007,00000000000000000033),
(00000000000000000018,00000000000000000008,00000000000000000031);
/*!40000 ALTER TABLE `sys_item_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_item_subitem`
--

LOCK TABLES `sys_item_subitem` WRITE;
/*!40000 ALTER TABLE `sys_item_subitem` DISABLE KEYS */;
INSERT INTO `sys_item_subitem` VALUES
(00000000000000000001,00000000000000000002,00000000000000000001,0),
(00000000000000000002,00000000000000000004,00000000000000000005,0),
(00000000000000000003,00000000000000000004,00000000000000000006,1),
(00000000000000000004,00000000000000000004,00000000000000000007,2),
(00000000000000000005,00000000000000000004,00000000000000000008,3);
/*!40000 ALTER TABLE `sys_item_subitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES
(00000000000000000001,'M_LEFT_MAIN','Menú Principal','Para acceder al inicio de la aplicación','SIDE_MENU_LEFT',NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_menu_item`
--

LOCK TABLES `sys_menu_item` WRITE;
/*!40000 ALTER TABLE `sys_menu_item` DISABLE KEYS */;
INSERT INTO `sys_menu_item` VALUES
(00000000000000000001,00000000000000000001,00000000000000000001,0),
(00000000000000000002,00000000000000000001,00000000000000000002,1),
(00000000000000000003,00000000000000000001,00000000000000000004,2);
/*!40000 ALTER TABLE `sys_menu_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_pagina`
--

LOCK TABLES `sys_pagina` WRITE;
/*!40000 ALTER TABLE `sys_pagina` DISABLE KEYS */;
INSERT INTO `sys_pagina` VALUES
(00000000000000000001,'usuario','home','Página de inicio de las funcionalidades de usuario','Usuario', 'Inicio'),
(00000000000000000002,'usuario','listado','Página con la que se realiza el listado de usuarios','Usuario', 'Listado'),
(00000000000000000003,'usuario','admin','Página con la que se realiza la adminsitración de usuarios','Usuario', 'Administración'),
(00000000000000000004,'usuario','estadisticas','Página con la que se accede a las estadísticas de usuarios','Usuario', 'Estadísticas'),
(00000000000000000005,'root','home','Inicio de la aplicación','Inicio',''),
(00000000000000000006,'root','register','Página de registro de usuario','',''),
(00000000000000000007,'root','login','Página de Ingreso al sistema','',''),
(00000000000000000008,'root','logout','Página que se muestra al usuario luego de su salida del sistema','',''),
(00000000000000000009,'articulo','home','Página inicial de las funcionalidades de articulo','Artículo','Inicio'),
(00000000000000000010,'articulo','listado','Página donde se ofrecen funcionalidades de listado de articulos','Artículo','Listado'),
(00000000000000000011,'articulo','stats','Página donde se acceden a las estadísticas de articulos','Artículo','Estadísticas'),
(00000000000000000012,'articulo','admin','Página donde se administran los articulos','Artículo','Administración');
/*!40000 ALTER TABLE `sys_pagina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_pagina_menu`
--

LOCK TABLES `sys_pagina_menu` WRITE;
/*!40000 ALTER TABLE `sys_pagina_menu` DISABLE KEYS */;
INSERT INTO `sys_pagina_menu` VALUES
(00000000000000000001,00000000000000000001,00000000000000000001),
(00000000000000000002,00000000000000000002,00000000000000000001),
(00000000000000000003,00000000000000000003,00000000000000000001),
(00000000000000000004,00000000000000000004,00000000000000000001),
(00000000000000000005,00000000000000000005,00000000000000000001),
(00000000000000000006,00000000000000000006,00000000000000000001),
(00000000000000000007,00000000000000000009,00000000000000000001),
(00000000000000000008,00000000000000000010,00000000000000000001),
(00000000000000000009,00000000000000000011,00000000000000000001),
(00000000000000000010,00000000000000000012,00000000000000000001);
/*!40000 ALTER TABLE `sys_pagina_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sys_pagina_rol`
--

LOCK TABLES `sys_pagina_rol` WRITE;
/*!40000 ALTER TABLE `sys_pagina_rol` DISABLE KEYS */;
INSERT INTO `sys_pagina_rol` VALUES
(00000000000000000001,00000000000000000001,00000000000000000041),
(00000000000000000002,00000000000000000002,00000000000000000041),
(00000000000000000003,00000000000000000003,00000000000000000041),
(00000000000000000004,00000000000000000004,00000000000000000041),
(00000000000000000005,00000000000000000009,00000000000000000031),
(00000000000000000006,00000000000000000010,00000000000000000031),
(00000000000000000007,00000000000000000011,00000000000000000031),
(00000000000000000008,00000000000000000012,00000000000000000031),
(00000000000000000009,00000000000000000009,00000000000000000033),
(00000000000000000010,00000000000000000010,00000000000000000033),
(00000000000000000011,00000000000000000011,00000000000000000033),
(00000000000000000012,00000000000000000009,00000000000000000032),
(00000000000000000013,00000000000000000010,00000000000000000032);
/*!40000 ALTER TABLE `sys_pagina_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-03 10:20:52
