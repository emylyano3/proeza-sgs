-- MySQL dump 10.13  Distrib 5.6.22, for Win32 (x86)
--
-- Host: localhost    Database: pescalotodo
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos` (
  `idArt` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) DEFAULT 'N/A',
  `idTipo` int(11) NOT NULL,
  `idMarca` int(11) NOT NULL,
  `idCategoria` int(11) NOT NULL,
  `costo` float DEFAULT '0',
  `precio` float NOT NULL DEFAULT '0',
  `stock` int(11) DEFAULT '0',
  `idImagenPreset` int(11) DEFAULT NULL,
  PRIMARY KEY (`idArt`),
  KEY `idTipo` (`idTipo`),
  KEY `idMarca` (`idMarca`),
  KEY `idCategoria` (`idCategoria`),
  KEY `articulos_ibfk_4_idx` (`idImagenPreset`),
  CONSTRAINT `articulos_ibfk_1` FOREIGN KEY (`idTipo`) REFERENCES `tipoarticulo` (`idTipo`),
  CONSTRAINT `articulos_ibfk_2` FOREIGN KEY (`idMarca`) REFERENCES `marcas` (`idMarca`),
  CONSTRAINT `articulos_ibfk_3` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `articulos_ibfk_4` FOREIGN KEY (`idImagenPreset`) REFERENCES `imagen_articulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=897 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulos`
--

LOCK TABLES `articulos` WRITE;
/*!40000 ALTER TABLE `articulos` DISABLE KEYS */;
INSERT INTO `articulos` VALUES (37,'Nacional',5,96,32,0,79,0,NULL),(38,'Capa',71,96,31,0,290,0,NULL),(42,'Desert -7',9,99,32,0,1800,0,NULL),(45,'Cowl 350',9,99,32,0,1100,0,NULL),(46,'Loch 250',9,99,32,0,850,0,NULL),(47,'Inca 200',9,99,32,0,550,0,NULL),(51,'grande ',10,103,28,0,490,0,NULL),(52,' chico',10,103,28,0,410,0,NULL),(55,'22 /',14,57,28,0,395,0,NULL),(56,'460 L',14,57,28,0,490,0,NULL),(57,'430 L',14,57,28,0,435,0,NULL),(58,'350 L',14,57,28,0,350,0,NULL),(59,'310 1/2 bandeja',14,57,28,0,190,0,NULL),(60,'320',14,57,28,0,165,0,NULL),(62,'35lts',28,57,32,0,650,0,NULL),(63,'26 lts',28,57,32,0,490,0,NULL),(64,'14 lts',28,57,32,0,420,0,NULL),(65,'5 Litros',28,57,32,0,350,0,NULL),(68,'50 qt con ruedas',28,33,32,0,1390,0,NULL),(69,'48 qt',28,33,32,0,1320,0,NULL),(71,'16qt',28,33,32,0,720,0,NULL),(72,'5 Qt',28,33,32,0,440,0,NULL),(73,'28 lts',28,47,32,0,490,0,NULL),(80,'Mango largo',29,42,28,0,0,0,NULL),(81,'Mango corto',29,42,28,0,0,0,NULL),(83,'Spining',25,24,28,0,300,0,NULL),(88,'1 lts  con bandolera',76,107,32,0,90,0,NULL),(89,'1 1/2 lts con bandolera ',76,107,32,0,110,0,NULL),(91,'Termica  1 lts',76,107,32,0,210,0,NULL),(97,'Todos los talles',32,24,28,0,950,0,NULL),(104,'1',33,65,32,0,5,0,NULL),(105,'U',33,65,32,0,9,0,NULL),(106,'Angulo 30 cm',33,65,32,0,13,0,NULL),(109,'Grande',43,109,32,0,69,0,NULL),(112,'1 Kg',79,109,32,0,510,0,NULL),(119,'Reina del PLata 425',23,88,28,0,1240,0,NULL),(120,'Speed Fish 400',23,88,28,0,1400,0,NULL),(121,'Scalibur 420',23,88,28,0,1400,0,NULL),(123,'White Spider',23,88,28,0,1320,0,NULL),(124,'Majestic 425',23,88,28,0,1370,0,NULL),(125,'The Arrecife 410',23,88,28,0,1050,0,NULL),(126,'Cochico 420',23,88,28,0,1240,0,NULL),(127,'Chasico 410',23,88,28,0,1070,0,NULL),(128,'River Head 430',23,88,28,0,1180,0,NULL),(129,'Cronos 430',23,88,28,0,1410,0,NULL),(130,'Lisa 430',23,88,28,0,1490,0,NULL),(131,'Air Blue 410',23,88,28,0,1310,0,NULL),(132,'Pulse 425',23,50,28,0,1400,0,NULL),(133,'Force 420',23,50,28,0,1600,0,NULL),(134,'Sakura 420',23,50,28,0,1400,0,NULL),(136,'Revolution 400',23,100,28,0,2000,0,NULL),(139,'Flecha de Plata 430',23,88,28,0,1500,0,NULL),(140,'Flecha de Plata 410',23,88,28,0,1200,0,NULL),(142,'Dark Water 320',23,88,28,0,950,0,NULL),(143,'Dark Water 360',23,88,28,0,1050,0,NULL),(144,'Variant',23,88,28,0,1600,0,NULL),(148,'Surpress 400',23,88,28,0,1550,0,NULL),(149,'Mar Caspio 400',23,88,28,0,880,0,NULL),(150,'CBR',84,88,28,0,700,0,NULL),(151,'Billy fish',84,88,28,0,650,0,NULL),(152,'Pejerrey Limite 360',84,88,28,0,700,0,NULL),(153,'Zamba 400',84,88,28,0,740,0,NULL),(174,'Dessert 195',88,112,28,0,1120,0,NULL),(175,'Rooster 195',88,112,28,0,990,0,NULL),(179,'Discovery 210',88,88,28,0,890,0,NULL),(185,'Hurricane Max 240',88,88,28,0,1100,0,NULL),(186,'Hurricane Max 300',88,88,28,0,1290,0,NULL),(193,'Surf Force 390',88,88,28,0,1610,0,NULL),(202,'Atractive 210',88,88,28,0,850,0,NULL),(203,'Atractive 240',88,88,28,0,950,0,NULL),(206,'Scorpius 210',88,50,28,0,1100,0,NULL),(231,'Grande',94,40,32,0,290,0,NULL),(232,'Mediana',94,40,32,0,250,0,NULL),(233,'Chica',94,40,32,0,220,0,NULL),(234,'Mini',94,40,32,0,160,0,NULL),(247,'Extreme 52qt ',28,33,32,0,1890,0,NULL),(248,'Angulo 23 cm',33,65,32,0,12,0,NULL),(255,'Termo 1.8 lts',28,33,32,0,285,0,NULL),(259,'2.20 2.40 mts',82,109,28,0,49,0,NULL),(260,'1.60 1.80 2mts',82,109,28,0,45,0,NULL),(261,'1.20 1.40 mts',82,109,28,0,39,0,NULL),(263,'Doble',85,96,32,0,100,0,NULL),(265,'Sinetic 240',88,112,28,0,1210,0,NULL),(271,'Hercules 390',24,88,28,0,790,0,NULL),(274,'Strong 3905',24,99,28,0,760,0,NULL),(277,'Dessert 210',88,112,28,0,1150,0,NULL),(279,'8 pers 3x2x180',16,65,32,0,3900,0,NULL),(280,'6 pers 240x2x180',16,65,32,0,3300,0,NULL),(281,'4 pers 190x2x165',16,65,32,0,2900,0,NULL),(298,'Valija 4 bancos',45,109,32,0,790,0,NULL),(301,'Legionario eco ',37,99,28,0,89,0,NULL),(315,'Stratus 195 ',20,99,28,0,840,0,NULL),(316,'Stratus 210',20,99,28,0,950,0,NULL),(327,'American Sea 50 215',20,88,28,0,790,0,NULL),(330,'Navegator 40  215',20,88,28,0,890,0,NULL),(331,'Mtx 210',20,88,28,0,990,0,NULL),(332,'Mtx 190 ',20,88,28,0,790,0,NULL),(339,'Green River 200',20,72,28,0,1250,0,NULL),(342,'Chico ',10,117,28,0,0,0,NULL),(343,'Undercast 210',20,88,28,0,1190,0,NULL),(368,'B O G 210 ',20,88,28,0,600,0,NULL),(369,'Corrientes 240 ',18,88,28,0,790,0,NULL),(379,'Familiar doble manta C/ desmontae',9,96,32,0,590,0,NULL),(380,'Scout C/ largo',9,96,32,0,480,0,NULL),(381,'Junior c/ corto',9,96,32,0,330,0,NULL),(382,'Doble ',99,96,32,0,110,0,NULL),(397,'Mesa TBS 807 Plegable (mesa sin bancos)or',45,99,32,0,950,0,NULL),(399,'JMP 400',22,88,28,0,510,0,NULL),(401,'B O G 240',20,88,28,0,700,0,NULL),(403,'Lisa 410',23,88,28,0,1390,0,NULL),(408,'Titan 270',103,81,28,0,510,0,NULL),(409,'Titan 300',103,81,28,0,590,0,NULL),(410,'Absolut 270',103,99,28,0,470,0,NULL),(413,'994 Poncho Aprobado Niño',104,78,31,0,265,0,73),(414,'992 Poncho Aprobado Adulto',104,78,31,0,310,0,72),(420,'Con botas',68,100,28,0,990,0,NULL),(432,'Alpha 400',22,99,28,0,490,0,NULL),(434,'Malivet 350',24,99,28,0,760,0,NULL),(436,'Blue Line 300',18,99,28,0,650,0,NULL),(437,'Blue Line 270',18,99,28,0,600,0,NULL),(438,'Blue Line 240',18,99,28,0,550,0,NULL),(454,'Rio Parana 210 m',18,88,28,0,440,0,NULL),(462,'Rio Parana 270 m',18,88,28,0,570,0,NULL),(463,'Rio Parana 240 m',18,88,28,0,490,0,NULL),(467,'Coral 360',19,99,28,0,890,0,NULL),(469,'Team Competition 430',23,88,28,0,1425,0,NULL),(471,'Dia Crystal 430',23,112,28,0,1590,0,NULL),(473,'Speedmaster 430 ',23,112,28,0,1500,0,NULL),(477,'Hot River 300',22,88,28,0,400,0,NULL),(478,'Hot River 350',22,88,28,0,450,0,NULL),(479,'Flecha de Oro 430',23,88,28,0,1490,0,NULL),(480,'Minotauro 400',23,88,28,0,1390,0,NULL),(482,'Coast 360',22,88,28,0,790,0,NULL),(483,'Todos los talles',65,100,28,0,290,0,NULL),(494,'De motor ',91,97,31,0,100,0,NULL),(523,'1413 Acople Lavamotor',115,78,31,0,90,0,39),(525,'1341 De mano (roja) x unidad ',118,78,31,0,360,0,38),(530,'396 400 Kg (Relación 5:1)',125,78,31,0,470,0,46),(531,'397 800 Kg (Relación 4:1) ',125,78,31,0,630,0,47),(533,'541 Embutir Bronce Cromado',57,78,31,0,170,0,59),(534,'545 Exterior Bronce Cromado',57,78,31,0,175,0,60),(536,'1131 ski L - 3 cinturones (Aprobados por P.N.A) ',104,13,31,0,710,0,66),(538,'1130 ski M - 3 cinturones (Aprobados por P.N.A) ',104,13,31,0,690,0,65),(539,'1129 ski S - 3 cinturones (Aprobados por P.N.A) ',104,13,31,0,640,0,64),(540,'1128 ski XS - 3 cinturones (Aprobados por P.N.A) ',104,13,31,0,610,0,63),(541,'1416 Grande Nacional (Con resorte)',126,78,31,0,95,0,52),(543,'1512 12 litros (L: 35 cm X A. 26 cm X Al: 25 cm)',127,78,31,0,495,0,74),(544,'1513 24 litros  (L: 47 cm X A. 32 cm X Al: 25 cm)',127,78,31,0,790,0,75),(552,'9 Qt',28,33,32,0,560,0,NULL),(556,'Todos los talles',130,24,32,0,80,0,NULL),(557,'Gravity 250',9,99,32,0,660,0,NULL),(558,'Telecaster 400',24,81,28,0,890,0,NULL),(563,'1104 Torpedo Baywatch ',104,78,31,0,340,0,68),(564,'Todos los talles',65,52,28,0,600,0,NULL),(567,'225 Carcasa de chapa 1 7/8 50mm ',136,78,31,0,210,0,54),(568,'222 Carcasa de fundicion 1 7/8',136,78,31,0,210,0,58),(580,'Andes 4p',16,99,32,0,4290,0,NULL),(583,'Screen House (300X300X200)',16,99,32,0,5300,0,NULL),(584,'California (3.00X2.00X2.00) ',16,99,32,0,6850,0,NULL),(585,'Patagonia 6p (5.00 X 2.20 X 2.00)',16,99,32,0,7290,0,NULL),(586,'Martial 6p (240 X280+120X180)',16,99,32,0,5800,0,NULL),(587,'Tundra 6p (3.40X2.70X1.80)',16,99,32,0,4900,0,NULL),(592,'Terra 3p (1.50X2.00X1.05)',16,99,32,0,1900,0,NULL),(593,'Explorer 3p (1.50X2.50X1.20)',16,99,32,0,2350,0,NULL),(594,'Dome II 3p',16,99,32,0,2250,0,NULL),(596,'Aconcagua plus 4p',16,99,32,0,2900,0,NULL),(598,'Alpha 400',22,118,28,0,590,0,NULL),(602,'Pejerrey 4500',23,130,28,0,1490,0,NULL),(603,'Camou 400',23,132,28,0,1350,0,NULL),(604,'Black Fish',23,88,28,0,1300,0,NULL),(607,'368 Naranja U para jet ski ',170,78,31,0,155,0,8),(610,'364 Proa recto c/pared c/tapas REDONDAS laterales(85 mm)',170,78,31,0,125,0,7),(615,'335 Recto (200 cm)',170,78,31,0,145,0,5),(616,'331 Doble mini cono (85 mm) ',170,78,31,0,90,0,1),(617,'365 Proa doble mini cono c/tapas REDONDAS laterales(85 mm)',169,78,31,0,160,0,9),(618,'334 Doble cono (265 cm)',170,78,31,0,290,0,4),(619,'336 Recto c/ pared',170,78,31,0,65,0,6),(622,'333 Doble cono (190 cm)',170,78,31,0,230,0,3),(623,'332 Doble cono (150 cm)',170,78,31,0,150,0,2),(625,'342 Repuesto Goma Sola',139,78,31,0,115,0,62),(629,'433 Para Malacate 6m',111,78,31,0,130,0,37),(630,'434 7.50 Metros (reforzada) ',111,78,31,0,200,0,35),(631,'481 Con crike 6 Metros (40 MM) REFORZADA ',111,78,31,0,220,0,36),(632,'1344 27,5 mts (oficial) Para salvavida circular',145,78,31,0,85,0,33),(633,'229 Cromada 1 7/8\"',112,78,31,0,120,0,32),(641,'1696 Canadiense 120',148,78,31,0,165,0,48),(642,'1698 Doble Plana 210',148,78,31,0,280,0,49),(643,'1700 Doble Desarmable 210',148,78,31,0,330,0,50),(644,'1702 Doble Cucharita 210',148,78,31,0,330,0,51),(648,'101 Danfor 4kg',71,78,31,0,390,0,21),(649,'100 Danfor 2kg',71,78,31,0,180,0,20),(651,'121 Imperdible A7 (RA 7) recom. embarcaciones de 4,5 a 5,7 mts ',71,78,31,0,225,0,16),(652,'120 Imperdible A5 (RA 5) recom. embarcaciones de 3 a 4,5mts ',71,78,31,0,165,0,15),(653,'102 Danfor 6kg',71,78,31,0,530,0,22),(654,'147 Plegable 6 kg estrella',71,78,31,0,640,0,30),(655,'146 Plegable 4 kg estrella',71,78,31,0,460,0,27),(656,'144 Plegable 2.50kg estrella',71,78,31,0,280,0,26),(657,'143 Plegable 1.50kg estrella',71,78,31,0,215,0,24),(658,'223 Traba Enganche',114,78,31,0,45,0,56),(661,'Rio Parana 330 m',18,88,28,0,750,0,NULL),(662,'Rio Parana 3 m',18,88,28,0,650,0,NULL),(667,'Surf max 390',88,88,28,0,1600,0,NULL),(759,'Absolut 300',103,99,28,0,510,0,NULL),(765,'IMS 210',88,133,28,0,1100,0,NULL),(766,'IMS 190',88,133,28,0,890,0,NULL),(782,'430 S (1/2 bandeja)',14,57,28,0,435,0,NULL),(796,'Individual',162,24,28,0,95,0,NULL),(813,'297 5 puntos PVC para amurar',106,78,31,0,190,0,44),(818,'Agressor 300',22,99,28,0,470,0,NULL),(822,'998 Circular Aprobado 55 cm. naranja (mod. parana No solas) ',104,78,31,0,750,0,57),(827,'Director Plegable 65283',61,99,32,0,685,0,NULL),(832,'Cadaques 5p',16,99,32,0,4850,0,NULL),(833,'Caravan (1.20+2.60+1.50X2.80X1.90)',16,99,32,0,6800,0,NULL),(834,'Bravo 5p',16,99,32,0,4000,0,NULL),(841,'Supra 210',20,88,28,0,890,0,NULL),(848,'173 Galvanizada 2 mts.',120,78,31,0,180,0,34),(849,'220 Seguridad Trailer Galvanizada (con gancho)',120,78,31,0,180,0,31),(854,'Pejerrey Limite 400',84,88,28,0,740,0,NULL),(855,'123 Imperdible A13 (RA13) recom. embarcaciones de 6,3 a 7,5 mts ',71,78,31,0,300,0,14),(856,'122 Imperdible A10 (RA 10) recom. embarcaciones de 5,7 a 6,3mts ',71,78,31,0,260,0,13),(857,'298 7 puntos Alum. para amurar',106,78,31,0,290,0,43),(858,'295 5 puntos Area redonda',106,78,31,0,70,0,45),(860,'226 Carcasa de chapa 1 7/8 65mm ',113,78,31,0,230,0,55),(861,'1132 ski XL - 3 cinturones (Aprobados por P.N.A) ',104,13,31,0,750,0,67),(862,'DYW 330',22,99,28,0,650,0,NULL),(863,'Titan 240',103,81,28,0,440,0,NULL),(864,'lunchera ',28,47,32,0,330,0,NULL),(865,'Astra 195',88,112,28,0,780,0,NULL),(869,'the runner 210',20,112,28,0,1100,0,NULL),(870,'Telecaster 360',22,81,28,0,780,0,NULL),(875,'titan 360',24,81,28,0,750,0,NULL),(876,'10 Lts.',28,47,32,0,390,0,NULL),(878,'highlang450',9,99,32,0,1300,0,NULL),(879,'doble cucharita desarmable',148,78,31,0,390,0,NULL),(880,'Carrick 2.10',18,99,28,0,390,0,NULL),(881,'Rio Parana 3.60',18,88,28,0,850,0,NULL),(882,'de motor',91,58,31,0,125,0,NULL),(883,'de motor',91,49,31,0,125,0,NULL),(884,'Coas.mar azul 390',18,88,28,0,890,0,NULL),(885,'Salmon 2.10',88,50,28,0,790,0,NULL),(886,'con soporte abrasadera',139,78,31,0,250,0,NULL),(887,'soldar',139,78,31,0,210,0,NULL),(888,'1325',10,99,28,0,615,0,NULL),(889,'',137,109,28,0,79,0,NULL),(890,'larga sin pantalon',172,100,28,0,190,0,NULL),(891,'marina 100qt',28,33,32,0,3700,0,NULL),(892,'Ciclone 4',22,99,28,0,420,0,NULL),(894,'Alpine',16,88,32,0,690,0,NULL),(895,'grasa pata',91,78,31,0,110,0,NULL),(896,'2 kg',79,109,32,0,570,0,NULL);
/*!40000 ALTER TABLE `articulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atributo_articulo`
--

DROP TABLE IF EXISTS `atributo_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atributo_articulo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ATAR_NOMB` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atributo_articulo`
--

LOCK TABLES `atributo_articulo` WRITE;
/*!40000 ALTER TABLE `atributo_articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `atributo_articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atributos_articulo`
--

DROP TABLE IF EXISTS `atributos_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atributos_articulo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idArticulo` int(11) NOT NULL,
  `idAtributo` int(11) NOT NULL,
  `valor` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idAtributo_UNIQUE` (`idAtributo`),
  UNIQUE KEY `idArticulo_UNIQUE` (`idArticulo`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `FK_ATAR_ARTI` FOREIGN KEY (`idArticulo`) REFERENCES `articulos` (`idArt`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ATAR_ATRI` FOREIGN KEY (`idAtributo`) REFERENCES `atributo_articulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atributos_articulo`
--

LOCK TABLES `atributos_articulo` WRITE;
/*!40000 ALTER TABLE `atributos_articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `atributos_articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (28,'Pesca'),(31,'Nautica'),(32,'Camping'),(33,'Cuchilleria');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagen_articulo`
--

DROP TABLE IF EXISTS `imagen_articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagen_articulo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `idArticulo` int(10) NOT NULL,
  `thumb` mediumblob NOT NULL,
  `image` longblob NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ID_ARTI` (`idArticulo`),
  CONSTRAINT `FK_IA_ARTI` FOREIGN KEY (`idArticulo`) REFERENCES `articulos` (`idArt`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagen_articulo`
--



--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcas` (
  `idMarca` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`idMarca`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES (8,'Abu Garcia'),(9,'Ailen'),(10,'Airon'),(12,'Alfers'),(13,'Aloft'),(14,'AMC'),(15,'Amnesia'),(16,'Anacron'),(17,'Andujar'),(18,'Aquatic'),(19,'Arbolito'),(20,'Arcos'),(21,'Arimar'),(22,'Asso'),(23,'Atahualpa'),(24,'Aural'),(25,'Bamboo'),(26,'Banax'),(27,'Bando'),(28,'Barracuda'),(29,'Bock Plast'),(30,'Brogas'),(31,'Campingaz'),(32,'Cel'),(33,'Coleman'),(34,'Cribal'),(35,'Criterio'),(36,'Daiwa'),(37,'Dam'),(38,'Del'),(39,'Doite'),(40,'Donato'),(41,'El Fin'),(42,'Enmar'),(43,'Eskiltuna'),(44,'Evinrude'),(45,'Excel'),(46,'Foco'),(47,'Helatodo'),(48,'Herring'),(49,'Johnson'),(50,'Kunnan'),(51,'Lagarto'),(52,'Lembu'),(53,'Lexus'),(54,'Loomis'),(55,'Luna'),(56,'Marcel'),(57,'Mauri'),(58,'Mercury'),(59,'Mitchell'),(60,'Montagne'),(61,'Muela'),(62,'Multipesca'),(63,'Mundial'),(64,'Mustad'),(65,'Nahuel'),(66,'NG'),(67,'Nieto'),(68,'Nitanyl'),(69,'Nitrolite'),(70,'Okuma'),(71,'OMC'),(72,'Omoto'),(73,'Pamela'),(74,'Penn'),(75,'Peter'),(76,'Rapala'),(77,'Rayglon'),(78,'Roan'),(79,'Shimano'),(80,'Sousa'),(81,'Spinit'),(82,'Starlight'),(83,'Steel'),(84,'Storm'),(85,'Strike Pro'),(86,'Sufix'),(87,'Super Flot'),(88,'Surfish'),(89,'Tica'),(90,'Tortuga'),(91,'Triple Fish'),(92,'Valiparri'),(93,'Venado'),(94,'Victorinox'),(96,'Wild Life'),(97,'Yamaha'),(98,'YPF'),(99,'Waterdog'),(100,'Albatros'),(102,'Predator'),(103,'Depredador'),(104,'Misi'),(105,'Pescalotodo'),(106,'Milenium'),(107,'Clarks'),(108,'Faba'),(109,'Sin Marca'),(110,'05'),(111,'Tech'),(112,'Silstar'),(114,'Aruba'),(116,'Kennedy'),(117,'Nuevo Milenio'),(118,'Flounder '),(119,'Doble T'),(121,'Champion'),(122,'Tommy '),(123,'Marine Sport'),(124,'Marine Sport'),(125,'Marine Sport'),(126,'flueger'),(127,'Flueger'),(128,'Soprano'),(129,'Montague'),(130,'Bambu'),(131,'Flonal'),(132,'Flonal'),(133,'Master fish'),(134,'Natway'),(135,'Northern Diver');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoarticulo`
--

DROP TABLE IF EXISTS `tipoarticulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoarticulo` (
  `idTipo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTipo`),
  KEY `idCategoria` (`idCategoria`),
  CONSTRAINT `tipoarticulo_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoarticulo`
--

LOCK TABLES `tipoarticulo` WRITE;
/*!40000 ALTER TABLE `tipoarticulo` DISABLE KEYS */;
INSERT INTO `tipoarticulo` VALUES (5,'Aislante',NULL),(6,'Anzuelo',NULL),(7,'Balde',NULL),(8,'Balin (no Gasti)',NULL),(9,'Bolsa de dormir',NULL),(10,'Bolso de pesca',NULL),(11,'Bolso termico',NULL),(12,'Boya',NULL),(13,'Cable de acero',NULL),(14,'Caja de pesca',NULL),(15,'Cargador de pilas',NULL),(16,'Carpa',NULL),(17,'Cartucho de gas',NULL),(18,'Cania 2 tramos fibra',NULL),(19,'Cania de Lanzar',NULL),(20,'Cania Embarcado',NULL),(21,'Cania Spinning',NULL),(22,'Cania Telescopica Fibra',NULL),(23,'Cania Telescopica Grafito',NULL),(24,'Cania Telescopica Mar',NULL),(25,'Chaleco',NULL),(26,'Cocina',NULL),(28,'Conservadora',NULL),(29,'Copo',NULL),(30,'Cuchara',NULL),(31,'Cuchillo',NULL),(32,'Enterito termico',NULL),(33,'Estaca',NULL),(34,'Farol',NULL),(35,'Gabetero',NULL),(36,'Garrafa',NULL),(37,'Gorro',NULL),(38,'Hoja',NULL),(39,'Inflador',NULL),(40,'Leader',NULL),(41,'Linterna',NULL),(42,'Marmita',NULL),(43,'Mechero',NULL),(44,'Medio mundo',NULL),(45,'Mesa de camping',NULL),(46,'Micro fibra',NULL),(47,'Mochila',NULL),(48,'Molde plomada',NULL),(49,'Nautica',NULL),(50,'Parante',NULL),(51,'Parrilla',NULL),(52,'Piedra afilar',NULL),(53,'Pila recargable',NULL),(54,'Pinza',NULL),(55,'Porta brazolada',NULL),(56,'Porta cania',NULL),(57,'Posa Cania',NULL),(60,'Senuelo (de Canuela)',NULL),(61,'Sillon',NULL),(62,'Sombrilla',NULL),(63,'Tablita',NULL),(64,'Tanza',NULL),(65,'Traje de agua',NULL),(66,'Tubo porta cania',NULL),(67,'Vara',NULL),(68,'Wader',NULL),(70,'Zanahoria',NULL),(71,'Ancla',NULL),(73,'Banco tijera',NULL),(75,'Colchoneta',NULL),(76,'Cantimplora',NULL),(77,'Disgregante',NULL),(79,'Garrafas',NULL),(81,'Mecha',NULL),(82,'Funda',NULL),(83,'Impermeabilizante',NULL),(84,'Cania grafito y fibra',NULL),(85,'Materos',NULL),(88,'Cania 2 tramos grafito',NULL),(91,'Aceite',NULL),(92,'Canias',NULL),(94,'Lunchera',NULL),(95,'Cania',NULL),(96,'Pasahilos',NULL),(97,'Reel',NULL),(98,'Gorro Legionario',NULL),(99,'Portatermo ',NULL),(100,'Playero',NULL),(101,'Colchon inflable',NULL),(102,'Reel',NULL),(103,'Cania telescopica de variada',NULL),(104,'Salvavidas',NULL),(105,'Sillon director',NULL),(106,'Ficha luz',NULL),(107,'Pala',NULL),(111,'Cinta',NULL),(112,'Bocha',NULL),(113,'Porta bocha',NULL),(114,'Cunia',NULL),(115,'Acople',NULL),(117,'Cunia',NULL),(118,'Bengalas',NULL),(119,'Banderas',NULL),(120,'Cadenas',NULL),(121,'Mosquetones',NULL),(122,'Tapones',NULL),(123,'Hombre al agua',NULL),(124,'Toletes',NULL),(125,'Malacate',NULL),(126,'Pera nafta',NULL),(127,'Tanque de nafta',NULL),(128,'Rueda timonera',NULL),(129,'Sobre',NULL),(130,'Medias termicas',NULL),(134,'Termo',NULL),(136,'Portabocha',NULL),(137,'Poncho',NULL),(138,'Rodillo',NULL),(139,'V de Proa',NULL),(140,'Ficha luz',NULL),(142,'Cintas',NULL),(143,'Espejos seniales',NULL),(144,'Conectores',NULL),(145,'Cabo Flotante',NULL),(146,'Chapon Rodillo',NULL),(148,'Palas Aluminio',NULL),(149,'Soga ',NULL),(150,'Chapon rodillo',NULL),(151,'Bomba de Achique',NULL),(154,'Ancla Capa',NULL),(157,'Vidrio farol',NULL),(158,'Guantes',NULL),(159,'Posa cania',NULL),(162,'Ropa Termica',NULL),(163,'Balanza',NULL),(164,'Jarro',NULL),(165,'Cania',NULL),(169,'Rodillo plastico',NULL),(170,'Rodillo Goma',NULL),(171,'capa',NULL),(172,'Capa',NULL);
/*!40000 ALTER TABLE `tipoarticulo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-26 15:36:53
