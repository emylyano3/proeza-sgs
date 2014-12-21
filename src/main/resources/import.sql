/* Seguridad */

	/* Usuarios */
	insert into seg_proeza_db.usuario (alias, nombre, apellido, email, password) values ('admin','admin','admin', 'admin@proeza.com.ar', 'admin');
	insert into seg_proeza_db.usuario (alias, nombre, apellido, email, password) values ('user','user','user', 'user@proeza.com.ar', 'user');
	
	/* Roles */
	insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('ROLE_ADMIN','admin','Rol de administrador del sistema (God)');
	insert into seg_proeza_db.rol (codigo, nombre, descripcion) values ('ROLE_USER','user','Rol de usuario b�sico');
	
	/* Asociacion de Usuarios y roles */
	insert into seg_proeza_db.usuario_rol (fk_rol, fk_usuario) values (1, 1);
	insert into seg_proeza_db.usuario_rol (fk_rol, fk_usuario) values (2, 1);
	insert into seg_proeza_db.usuario_rol (fk_rol, fk_usuario) values (2, 2);
	
	/* Funcionalidades */
	insert into seg_proeza_db.funcionalidad (codigo, nombre, descripcion) values ('F_HOME','Home','Start point de la aplicacion');
	
	/* Asociacion de Funcionalidadeds y roles */
	insert into seg_proeza_db.funcionalidad_rol (fk_rol, fk_funcionalidad) values (1, 1);
	insert into seg_proeza_db.funcionalidad_rol (fk_rol, fk_funcionalidad) values (2, 1);

/* Seguridad */

/* Sistema */

	insert into sgs_proeza_db.item_menu(codigo, tooltip, texto, link, icono) values ('M_HOME', 'Men� para volver al inicio de la aplicaci�n','Inicio', 'home', null);
	insert into sgs_proeza_db.pagina (codigo, nombre, descripcion) values ('P_HOME','Inicio','Inicio de la aplicaci�n');
	insert into sgs_proeza_db.menu_pagina (fk_pagina, fk_item_menu) values (1,1);

/* Sistema */

/* Negocio */

	insert into sgs_proeza_db.clase (codigo, nombre, descripcion) values ('REEL','Reel','Reeles en general');
	
/* Negocio */