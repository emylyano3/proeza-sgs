/* Seguridad */

	/* Usuarios */
	insert into seg_proeza_db.usuario (alias, nombre, apellido, email, password) values ('admin','admin','admin', 'admin@proeza.com.ar', '$2a$10$vu.GPiWbD/G7mxM9qjU4R.ZPGiKt1kB.wLyoRsjRbBDCK5PCL3Xu6');
	insert into seg_proeza_db.usuario (alias, nombre, apellido, email, password) values ('user','user','user', 'user@proeza.com.ar', '$2a$10$kykMHqNu59hMkYJ6gUYjgekCK.Mt1q7ZKcMLH3aGT8ReHPnYCO87m');
	
	/* Roles */
	insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('ROLE_ADMIN','admin','Rol de administrador del sistema (God)');
	insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('ROLE_USER','user','Rol de usuario básico');
	insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('ROLE_USER_ADMIN','user_admin','Rol de administrador de usuarios');
	
	/* Asociacion de Usuarios y roles */
	insert into seg_proeza_db.usuario_rol (id, fk_rol, fk_usuario) values (1, 1, 1);
	insert into seg_proeza_db.usuario_rol (id, fk_rol, fk_usuario) values (2, 2, 1);
	insert into seg_proeza_db.usuario_rol (id, fk_rol, fk_usuario) values (3, 3, 1);
	insert into seg_proeza_db.usuario_rol (id, fk_rol, fk_usuario) values (4, 2, 2);
	
/* Seguridad */
