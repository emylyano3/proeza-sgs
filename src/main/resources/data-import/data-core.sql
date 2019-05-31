/* Telefono Tipo */
	insert into sgs_proeza_test_db.cmn_telefono_tipo (codigo, nombre, descripcion) values ('MPERSONAL','Movil Personal','Celular personal');
	insert into sgs_proeza_test_db.cmn_telefono_tipo (codigo, nombre, descripcion) values ('MTRABAJO','Movil de Trabajo','Celular trabajo');
	insert into sgs_proeza_test_db.cmn_telefono_tipo (codigo, nombre, descripcion) values ('FPERSONAL','Personal','Telefono fijo personal');
	insert into sgs_proeza_test_db.cmn_telefono_tipo (codigo, nombre, descripcion) values ('FTRABAJO','Trabajo','Telefono fijo de trabajo');
/* Telefono Tipo */

/* Medio de pago */
	insert into sgs_proeza_test_db.cmn_medio_pago (codigo, nombre, descripcion) values ('EFT', 'Efectivo', 'Efectivo');
	insert into sgs_proeza_test_db.cmn_medio_pago (codigo, nombre, descripcion) values ('TCR', 'Crédito', 'Tarjeta de crédito');
	insert into sgs_proeza_test_db.cmn_medio_pago (codigo, nombre, descripcion) values ('TDE', 'Débito', 'Tarjeta de débito');
/* Medio de pago */
		
/* Internacionalizacion */
	
/* Localizacion de textos */
	insert into sgs_proeza_test_db.cmn_i18n (id) values (1);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (2);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (3);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (4);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (5);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (6);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (7);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (8);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (9);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (10);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (11);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (12);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (13);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (14);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (15);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (16);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (17);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (18);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (19);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (20);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (21);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (22);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (23);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (24);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (25);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (26);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (27);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (28);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (29);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (30);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (31);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (32);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (33);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (34);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (35);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (36);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (37);
	insert into sgs_proeza_test_db.cmn_i18n (id) values (38);
	
	
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (1,1,'Home','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (2,1,'Home','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (3,2,'User','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (4,2,'Usuario','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (5,3,'Administration','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (6,3,'Administración','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (7,4,'List','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (8,4,'Listado','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (9,5,'Statistics','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (10,5,'Estadísticas','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (11,6,'Access the application home','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (12,6,'Para acceder al inicio de la aplicación','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (13,7,'Access the user home','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (14,7,'Para acceder al home de la funciolidad de usuario','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (15,8,'Access the user administration','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (16,8,'Para acceder a la administración de usuarios','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (17,9,'Access the user list','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (18,9,'Para acceder al listado de usuarios','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (19,10,'Access the article home','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (20,10,'Para acceder al home de articulo','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (21,11,'Access the article list','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (22,11,'Para acceder al listado de articulos','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (23,12,'Access the article stats','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (24,12,'Para acceder a las estadísticas de articulos','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (25,13,'Access the article administration','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (26,13,'Para acceder a la administración de articulos','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (27,14,'Article','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (28,14,'Artículo','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (29,15,'Usuario','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (30,15,'User','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (31,16,'Inicio','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (32,16,'Home','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (33,17,'Artículo','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (34,17,'Product','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (35,18,'Inicio','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (36,18,'Home','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (37,19,'Listado','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (38,19,'List','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (39,20,'Administración','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (40,20,'Management','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (41,21,'Estadísticas','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (42,21,'Statistics','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (43,22,'Página de inicio de usuario','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (44,22,'User home page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (45,23,'Página de listado de usuarios','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (46,23,'User listing page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (47,24,'Página de adminsitración de usuarios','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (48,24,'User administration page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (49,25,'Página de estadísticas de usuarios','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (50,25,'User statistics page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (51,26,'Inicio de la aplicación','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (52,26,'App home','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (53,27,'Página de registro de usuario','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (54,27,'User registration page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (55,28,'Página de login','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (56,28,'Login page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (57,29,'Página de logout','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (58,29,'Logout page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (59,30,'Página inicial del modulo artículos','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (60,30,'Products home page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (61,31,'Página de listado de articulos','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (62,31,'Products listing page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (63,32,'Página de estadísticas de articulos','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (64,32,'Products statistics page','en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (65,33,'Página de administración de articulos','es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (66,33,'Product administration page','en_US');

	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (67, 34, 'Ventas', 'es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (68, 34, 'Sales', 'en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (69, 35, 'Venta rápida efectivo', 'es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (70, 35, 'Quick cash sale', 'en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (71, 36, 'Venta rápida crédito', 'es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (72, 36, 'Quick credit sale', 'en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (73, 37, 'Venta rápida débito', 'es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (74, 37, 'Quick debit sale', 'en_US');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (75, 38, 'Ver imágenes', 'es_AR');
	insert into sgs_proeza_test_db.cmn_traduccion (id, fk_i18n, texto, locale) values (76, 38, 'See images', 'en_US');

/* Internacionalizacion */