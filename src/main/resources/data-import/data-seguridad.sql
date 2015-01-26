/* Seguridad */

	/* Usuarios */
	insert into seg_proeza_db.usuario (id, alias, nombre, apellido, email, password) values (100, 'user_admin','user_admin','user_admin', 'user_admin@proeza.com.ar', '$2a$10$oTBcDbxPZlyEhjK4/Q8.o.PCiqc.LwOg5IgF7e4VWAzkDMlVr0cvW');
	insert into seg_proeza_db.usuario (id, alias, nombre, apellido, email, password) values (101, 'prod_admin','prod_admin','prod_admin', 'prod_admin@proeza.com.ar', '$2a$10$gstdn6.1OVcOmjSxx36kJuHoH9seRgbSNYQjVTNavTBVGGxcrT6VC');
	
	/* Roles */
	/* Venta */
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (01,'ROLE_VENTA_ADMIN','Administrador de ventas','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (02,'ROLE_VENTA_ESTAD','Estadista de ventas','');
	/* Compra */
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (11,'ROLE_COMPR_ADMIN','Administrador de compras','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (12,'ROLE_COMPR_ESTAD','Estadista de compras','');
	/* Stock */
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (21,'ROLE_STOCK_ADMIN','Administrador de stock','');
	/* Producto */
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (31,'ROLE_PRODU_ADMIN','Administrador de productos','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (32,'ROLE_PRODU_CATAL','Catálogo de productos','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (33,'ROLE_PRODU_ESTAD','Estadista de productos','');
	/* Usuario */
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (41,'ROLE_USUAR_ADMIN','Administrador de la seguridad del sistema','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (42,'ROLE_USUAR_ESTAD','Estadista de usuarios','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (43,'ROLE_USUAR_LISTA','Listado de usuarios','');
	
	/* Asociacion de Usuarios y roles */
	/* user_admin */
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (1, 100, 41);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (2, 100, 42);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (3, 100, 43);
	
	/* prod_admin */
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (4, 101, 31);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (5, 101, 32);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (6, 101, 33);
	
/* Seguridad */
