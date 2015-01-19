/* Seguridad */

	/* Usuarios */
	insert into seg_proeza_db.usuario (alias, nombre, apellido, email, password) values ('admin','admin','admin', 'admin@proeza.com.ar', '$2a$10$vu.GPiWbD/G7mxM9qjU4R.ZPGiKt1kB.wLyoRsjRbBDCK5PCL3Xu6');
	insert into seg_proeza_db.usuario (alias, nombre, apellido, email, password) values ('user','user','user', 'user@proeza.com.ar', '$2a$10$kykMHqNu59hMkYJ6gUYjgekCK.Mt1q7ZKcMLH3aGT8ReHPnYCO87m');
	
	/* Roles */
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (1,'ROLE_ADMIN_VENTA','Administrador de ventas','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (2,'ROLE_ESTAD_VENTA','Estadista de ventas','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (3,'ROLE_ADMIN_COMPR','Administrador de compras','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (4,'ROLE_ESTAD_COMPR','Estadista de compras','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (5,'ROLE_ADMIN_STOCK','Administrador de stock','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (6,'ROLE_ADMIN_PRODU','Administrador de productos','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (7,'ROLE_CATAL_PRODU','Catálogo de productos','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (8,'ROLE_ESTAD_PRODU','Estadista de productos','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (9,'ROLE_ADMIN_CLIEN','Administrador de clientes','');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (10,'ROLE_ESTAD_CLIEN','Estadista de clientes','');
	/* Roles funcionalidad usuarios */
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (11,'ROLE_VISTA_USUAR','Listado de usuarios','Permite acceder a la pantalla de usuarios en modo vista');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (12,'ROLE_ADMIN_SEGUR','Administrador de seguridad','Permite realizar cambios sobre el esquema de seguridad de la aplicación. ABM de roles y usuarios y asociación entre ellos.');
	insert into seg_proeza_db.rol (id, codigo, nombre, descripcion) values (13,'ROLE_ESTAD_USUAR','Estadista de usuarios','');
	
	/* Asociacion de Usuarios y roles */
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (1, 1, 1);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (2, 1, 2);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (3, 1, 3);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (4, 1, 4);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (5, 1, 5);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (6, 1, 6);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (7, 1, 7);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (8, 1, 8);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (9, 1, 9);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (10, 1, 10);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (11, 1, 11);
	insert into seg_proeza_db.usuario_rol (id, fk_usuario, fk_rol) values (12, 1, 12);
	
/* Seguridad */
