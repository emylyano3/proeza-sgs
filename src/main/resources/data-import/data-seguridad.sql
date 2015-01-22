/* Seguridad */

	/* Usuarios */
	insert into seg_proeza_db.usuario (id, alias, nombre, apellido, email, password) values (1, 'user_admin','user_admin','user_admin', 'user_admin@proeza.com.ar', '$2a$10$oTBcDbxPZlyEhjK4/Q8.o.PCiqc.LwOg5IgF7e4VWAzkDMlVr0cvW');
	
	/* Roles */
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (1,'ROLE_VENTA_ADMIN','Administrador de ventas','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (2,'ROLE_VENTA_ESTAD','Estadista de ventas','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (3,'ROLE_COMPR_ADMIN','Administrador de compras','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (4,'ROLE_COMPR_ESTAD','Estadista de compras','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (5,'ROLE_STOCK_ADMIN','Administrador de stock','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (6,'ROLE_PRODU_ADMIN','Administrador de productos','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (7,'ROLE_PRODU_CATAL','Catálogo de productos','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (8,'ROLE_PRODU_ESTAD','Estadista de productos','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (9,'ROLE_CLIEN_ADMIN','Administrador de clientes','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (10,'ROLE_CLIEN_ESTAD','Estadista de clientes','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (11,'ROLE_SEGUR_ADMIN','Administrador de seguridad','Permite realizar cambios sobre el esquema de seguridad de la aplicación. ABM de roles y usuarios y asociación entre ellos.');
	
	/* Asociacion de Usuarios y roles */
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (1, 1, 11);
	
/* Seguridad */
