/* Seguridad */

	/* Usuarios */
	insert into sgs_proeza_test_db.seg_usuario (id, alias, nombre, apellido, email, password) values (100, 'user_admin','user_admin','user_admin', 'user_admin@proeza.com.ar', '$2a$10$oTBcDbxPZlyEhjK4/Q8.o.PCiqc.LwOg5IgF7e4VWAzkDMlVr0cvW');
	insert into sgs_proeza_test_db.seg_usuario (id, alias, nombre, apellido, email, password) values (101, 'prod_admin','prod_admin','prod_admin', 'prod_admin@proeza.com.ar', '$2a$10$gstdn6.1OVcOmjSxx36kJuHoH9seRgbSNYQjVTNavTBVGGxcrT6VC');
	insert into sgs_proeza_test_db.seg_usuario (id, alias, nombre, apellido, email, password) values (102, 'prod_viewer','prod_viewer','prod_viewer', 'prod_viewer@proeza.com.ar', '$2a$10$WwVnE7OeUpe1a892TJrbEOUEzTFKWiHLIM0w9WNKyxZmyGM8vmwk2');
	insert into sgs_proeza_test_db.seg_usuario (id, alias, nombre, apellido, email, password) values (103, 'prod_stat','prod_stat','prod_stat', 'prod_stat@proeza.com.ar', '$2a$10$LGpaazPGhg3U8cqTEb5bq.65GKpqYlEoRG7xEBEtnJ0ZiNN5CFz/S');
	insert into sgs_proeza_test_db.seg_usuario (id, alias, nombre, apellido, email, password) values (104, 'admin','admin','admin', 'admin@proeza.com.ar', '$2a$10$N9ba82wZVlNGgMgcO24XBeGwcE6LkEPkWvuFI8I7efa5F8VgOWXEq');
	
	/* Roles */
	/* Venta */
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (51,'ROLE_VENTA_ADMIN','Ventas Administrador','');
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (52,'ROLE_VENTA_ESTAD','Ventas Estadista','');
	/* Compra */
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (11,'ROLE_COMPR_ADMIN','Compras Administrador','');
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (12,'ROLE_COMPR_ESTAD','Compras Estadista','');
	/* Producto */
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (31,'ROLE_PRODU_ADMIN','Productos Administrador','');
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (32,'ROLE_PRODU_CATAL','Productos Catálogo','');
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (33,'ROLE_PRODU_ESTAD','Productos Estadista','');
	/* Usuario */
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (41,'ROLE_USUAR_ADMIN','Usuarios Administrador','');
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (42,'ROLE_USUAR_ESTAD','Usuarios Estadista','');
	insert into sgs_proeza_test_db.seg_rol (id, codigo, nombre, descripcion) values (43,'ROLE_USUAR_LISTA','Usuarios Listado','');

	/* Asociacion de Usuarios y roles */
	/* user_admin */
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (100, 41);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (100, 42);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (100, 43);
	
	/* prod_admin */
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (101, 31);
	
	/* prod_stat */
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (102, 32);
	
	/* prod_viewer */
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (103, 33);
	
	/* admin */
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 41);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 42);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 43);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 31);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 32);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 33);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 12);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 12);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 51);
	insert into sgs_proeza_test_db.seg_usuario_rol (fk_usuario, fk_rol) values (104, 52);
	
/* Seguridad */
