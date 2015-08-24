/* Telefono Tipo */
	insert into sgs_proeza_db.cmn_telefono_tipo (codigo, nombre, descripcion) values ('MPERSONAL','Movil Personal','Celular personal');
	insert into sgs_proeza_db.cmn_telefono_tipo (codigo, nombre, descripcion) values ('MTRABAJO','Movil de Trabajo','Celular trabajo');
	insert into sgs_proeza_db.cmn_telefono_tipo (codigo, nombre, descripcion) values ('FPERSONAL','Personal','Telefono fijo personal');
	insert into sgs_proeza_db.cmn_telefono_tipo (codigo, nombre, descripcion) values ('FTRABAJO','Trabajo','Telefono fijo de trabajo');
/* Telefono Tipo */

/* Medio de pago */
	insert into sgs_proeza_db.cmn_medio_pago (codigo, nombre, descripcion) values ('EFT', 'Efectivo', 'Efectivo');
	insert into sgs_proeza_db.cmn_medio_pago (codigo, nombre, descripcion) values ('TCR', 'Crédito', 'Tarjeta de crédito');
	insert into sgs_proeza_db.cmn_medio_pago (codigo, nombre, descripcion) values ('TDE', 'Débito', 'Tarjeta de débito');
/* Medio de pago */
		
/* Internacionalizacion */
	
/* Localizacion de textos */
	insert into sgs_proeza_db.cmn_i18n (id) values (1);
	insert into sgs_proeza_db.cmn_i18n (id) values (2);
	insert into sgs_proeza_db.cmn_i18n (id) values (3);
	insert into sgs_proeza_db.cmn_i18n (id) values (4);
	insert into sgs_proeza_db.cmn_i18n (id) values (5);
	insert into sgs_proeza_db.cmn_i18n (id) values (6);
	insert into sgs_proeza_db.cmn_i18n (id) values (7);
	insert into sgs_proeza_db.cmn_i18n (id) values (8);
	insert into sgs_proeza_db.cmn_i18n (id) values (9);
	insert into sgs_proeza_db.cmn_i18n (id) values (10);
	insert into sgs_proeza_db.cmn_i18n (id) values (11);
	insert into sgs_proeza_db.cmn_i18n (id) values (12);
	insert into sgs_proeza_db.cmn_i18n (id) values (13);
	insert into sgs_proeza_db.cmn_i18n (id) values (14);
	insert into sgs_proeza_db.cmn_i18n (id) values (15);
	insert into sgs_proeza_db.cmn_i18n (id) values (16);
	insert into sgs_proeza_db.cmn_i18n (id) values (17);
	insert into sgs_proeza_db.cmn_i18n (id) values (18);
	insert into sgs_proeza_db.cmn_i18n (id) values (19);
	insert into sgs_proeza_db.cmn_i18n (id) values (20);
	insert into sgs_proeza_db.cmn_i18n (id) values (21);
	
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (1,1,'Home','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (2,1,'Home','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (3,2,'User','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (4,2,'Usuario','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (5,3,'Administration','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (6,3,'Administración','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (7,4,'List','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (8,4,'Listado','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (9,5,'Statistics','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (10,5,'Estadísticas','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (11,6,'Access the application home','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (12,6,'Para acceder al inicio de la aplicación','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (13,7,'Access the user home','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (14,7,'Para acceder al home de la funciolidad de usuario','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (15,8,'Access the user administration','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (16,8,'Para acceder a la administración de usuarios','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (17,9,'Access the user list','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (18,9,'Para acceder al listado de usuarios','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (19,10,'Access the article home','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (20,10,'Para acceder al home de articulo','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (21,11,'Access the article list','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (22,11,'Para acceder al listado de articulos','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (23,12,'Access the article stats','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (24,12,'Para acceder a las estadísticas de articulos','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (25,13,'Access the article administration','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (26,13,'Para acceder a la administración de articulos','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (27,14,'Article','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (28,14,'Artículo','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (29,15,'Usuario','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (30,15,'User','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (31,16,'Inicio','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (32,16,'Home','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (33,17,'Artículo','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (34,17,'Product','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (35,18,'Inicio','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (36,18,'Home','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (37,19,'Listado','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (38,19,'List','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (39,20,'Administración','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (40,20,'Management','en_US');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (41,21,'Estadísticas','es_AR');
	insert into sgs_proeza_db.cmn_traduccion (id, fk_i18n, texto, locale) values (42,21,'Statistics','en_US');
/* Internacionalizacion */